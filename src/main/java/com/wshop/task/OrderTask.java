package com.wshop.task;

import com.wshop.model.MarketValue;
import com.wshop.model.MoneyRecord;
import com.wshop.model.Order;
import com.wshop.model.User;
import com.wshop.service.*;
import com.wshop.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@Component
public class OrderTask {

    private static final Logger logger = LoggerFactory.getLogger(OrderTask.class);

    @Autowired
    MoneyRecordService moneyRecordService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    MarketValueService marketValueService;

    @Autowired
    MarketRateService marketRateService;

    //订单自动自动计算 ： 0-24点 每五分钟执行一次
    @Scheduled(cron="0 0/2 * * * ?")
    protected void execute(){

        try {
            logger.info("订单定时任务...");
            //获取当月支付的未处理订单
            List<Order> orders =  orderService.getOrderByMonth();
            DecimalFormat df   = new DecimalFormat("######0.00");

            if(orders != null && orders.size()>0){
                for(Order order:orders){
                    User user =  userService.getUserById(order.getUserId());

                    if(user.getType() == 2 ){

                        //1--个人业绩计算
                        personalMoney(user.getMid(),user.getId(),order.getOrderprice(),user.getNickname(),order.getOrderid());

                        //--团队业绩计算
                        groupMoney(user.getMid(),user.getId(),user.getParentid(),order.getOrderprice(),user.getNickname(),order.getOrderid());

                    }

                    orderService.updateOrderStatus(order.getOrderid(),1);
                    logger.info("订单号："+order.getOrderid()+" 已处理!");
                }
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    /**
     * 个人业绩计算
     * @param uid
     * @param money
     * @param username
     * @param orderId
     */
    private void personalMoney(Integer mid,Integer uid,BigDecimal money,String username,String orderId){
        MarketValue nowMarketValue = marketValueService.getNowMarketValueByUser(uid);

        if(nowMarketValue == null){
            addMarketValue(mid,uid,money);
        } else{
            marketValueService.updateMarketValueByUser(uid,money,DateUtil.getNowYear(),DateUtil.getNowMonth());
        }
        //-- 个人业绩增加
        addMoneyRecord(uid,money,0,username,orderId,"+","个人业绩");

    }

    /**
     * 团队业绩计算
     * @param uid
     * @param money
     * @param username
     * @param orderId
     */
    private void groupMoney(Integer mid, Integer uid, Integer parentId, BigDecimal money, String username, String orderId){
        MarketValue nowMarketValue = marketValueService.getNowMarketValueByUser(uid);

        if(nowMarketValue == null){
            addMarketValue(mid,uid,new BigDecimal(0));
            marketValueService.updateTeamMarketValueByUser(uid,money,DateUtil.getNowYear(),DateUtil.getNowMonth());
        } else{
            marketValueService.updateTeamMarketValueByUser(uid,money,DateUtil.getNowYear(),DateUtil.getNowMonth());
        }

        //-- 团队业绩增加
        addMoneyRecord(uid,money,1,username,orderId,"+","团队业绩");

        User parentUser =  userService.getUserById(parentId);
        if(parentUser != null){
            if(parentUser.getType() == 2 ){
                groupMoney(parentUser.getMid(),parentUser.getId(),parentUser.getParentid(),money,parentUser.getNickname(),orderId);
            }
        }

    }

    private void addMoneyRecord(Integer uid,BigDecimal money,int type,String username,String orderId,String change_flag,String remark){
        DecimalFormat df   = new DecimalFormat("######0.00");
        df.format(money);

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUid(uid);
        moneyRecord.setUserName(username);
        moneyRecord.setOrderid(orderId);
        moneyRecord.setChangeFlag(change_flag);
        moneyRecord.setType(type);
        moneyRecord.setMoney(money);
        moneyRecord.setRemark(remark);
        moneyRecord.setAddTime(DateUtil.getDate());
        moneyRecordService.addMoneyRecord(moneyRecord);
    }

    private void addMarketValue(Integer mid,Integer uid,BigDecimal money){
        DecimalFormat df   = new DecimalFormat("######0.00");
        df.format(money);

        MarketValue marketValue = new MarketValue();
        marketValue.setMid(mid);
        marketValue.setUid(uid);
        marketValue.setYear(DateUtil.getNowYear());
        marketValue.setMonth(DateUtil.getNowMonth());
        marketValue.setMarket(money);
        marketValue.setAddTime(DateUtil.getDate());
        marketValueService.addMarketValueByUser(marketValue);
    }

}
