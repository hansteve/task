package com.wshop.task;

import com.wshop.model.*;
import com.wshop.service.*;
import com.wshop.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: han
 * Date: 16-8-6
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MonthTask {
    private static Logger logger = Logger.getLogger(MonthTask.class);

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
    @Scheduled(cron="0 0/5 * * * ?")
    protected void execute(){

        try {
            //获取上个月市场情况
             List<MarketValue> marketValues =  marketValueService.getBeforeMarketValueAll(DateUtil.getNowYear(),DateUtil.getBeforeMonth(),0);
             DecimalFormat df   = new DecimalFormat("######0.00");

            // -个人提成
            logger.info("个人提成计算：");
            if(marketValues != null && marketValues.size()> 0){
            for(MarketValue marketValue:marketValues){

                double myMoney = 0 ;
                if( Double.valueOf(marketValue.getMarket().doubleValue()) > 0)  {
                    MarketRate marketRate = getPersonalMarketRate(marketValue.getMid(),marketValue.getMarket().doubleValue());
                    if(marketRate !=null ){
                    myMoney = marketValue.getMarket().doubleValue() * marketRate.getPercent().doubleValue();
                    df.format(myMoney);

                    //-- 个人提成增加
                    logger.info("个人提成uid：" + marketValue.getUid() +",money:" + myMoney);
                    personalPushMoney(marketValue.getUid(),myMoney,"","");
                    }else{
                        logger.error("提成费率错误！");
                    }
                }
                 marketValueService.updateMarketValueStatus(marketValue.getId(),1);
            }


            // -团队提成
            List<MarketValue> marketValues2 =  marketValueService.getBeforeMarketValueAll(DateUtil.getNowYear(),DateUtil.getBeforeMonth(),1);
            logger.info("团队提成计算：");
            for(MarketValue marketValue:marketValues2){
                getTeamMarket(marketValue.getUid());
                marketValueService.updateMarketValueStatus(marketValue.getId(),3);
            }


            // -当月个人总计提层数量
            List<MarketValue> marketValues3 =  marketValueService.getBeforeMarketValueAll(DateUtil.getNowYear(),DateUtil.getBeforeMonth(),3);
            logger.info("个人总计提成计算：");
            for(MarketValue marketValue:marketValues3){
                 getTotalMoney(marketValue.getId());
            }

         }

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    private void getTotalMoney(int id){
        MarketValue marketValue = marketValueService.getMarketValueById(id);
        double countMoney = 0;
        countMoney  = marketValue.getPersonMoney().doubleValue()+marketValue.getTotalMoney().doubleValue() +marketValue.getLeaderMoney().doubleValue();
        marketValueService.updatePersonMoneyById(id,countMoney);
        marketValueService.updateMarketValueStatus(id,4);
        addMoneyRecord(id,new BigDecimal(countMoney),5,"","","+","个人总计提成");
    }
    /**
     * 团队提成计算
     * @param uid
     */
    private void getTeamMarket(int uid){
        List<MarketValue> userMarkets = marketValueService.getUserMarketValue(uid,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        MarketValue myMarkeValue = marketValueService.getMarketValueByUser(uid,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        if(userMarkets != null && userMarkets.size() > 0){
            double  countTeamMarket=0,countPushMoney = 0,countTeamPushMoney = 0,leaderCount = 0;

            for (MarketValue userMarket : userMarkets) {

                countTeamMarket  = countTeamMarket + userMarket.getTeamMarket().doubleValue();

                MarketRate marketRate1 = getPersonalMarketRate(0,userMarket.getTeamMarket().doubleValue());
                double  pushMoney = 0;
                if(marketRate1 != null ){
                    pushMoney  = marketRate1.getPercent().doubleValue() * userMarket.getTeamMarket().doubleValue();
                }
              //  logger.info("pushMoney: " + pushMoney);
                countTeamPushMoney = countTeamPushMoney + pushMoney;
              //  logger.info("countTeamPushMoney: " + countTeamPushMoney);
                //计算满足条件的数量
                if(userMarket.getTeamMarket().doubleValue() >= 50000){
                    leaderCount++;
                }

            }

            //个人业绩 和小组业绩相加
            countTeamMarket = countTeamMarket + myMarkeValue.getMarket().doubleValue();
            countTeamPushMoney = countTeamPushMoney + myMarkeValue.getPersonMoney().doubleValue();

            if(myMarkeValue.getMarket().doubleValue() >= 50000){
                leaderCount++;
            }

            MarketRate marketRate = getPersonalMarketRate(0,countTeamMarket);
            if(marketRate != null ){
                countPushMoney = countTeamMarket * marketRate.getPercent().doubleValue();
               // logger.info("countPushMoney: " + countPushMoney +" = countTeamMarket:" + countTeamMarket + " * marketRate:"+marketRate.getPercent() );
               // logger.info("countTeamPushMoney: " + countTeamPushMoney );

                //个人业绩增加
                if(countPushMoney > countTeamPushMoney){
                    teamPushMoney(uid,countPushMoney - countTeamPushMoney,"","");
                    logger.info("团队提成uid：" + uid +",money:" + String.valueOf(countPushMoney - countTeamPushMoney));
                }
            }


            //领导奖励
            if(leaderCount >=2){
                leaderPushMoney(uid,2500*(leaderCount-1),"","");
                logger.info("领导奖励 uid：" + uid +",money:" + String.valueOf(2500*(leaderCount-1)));
            }

        }
    }

   /* private void countPushMoney(long uid,double money,String username,String orderId){

        if(money != 0){
            marketValueService.updateLeaderMoneyByUser(uid,money,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        }
        //-- 团队业绩增加
        addMoneyRecord(uid,money,5,username,orderId,"+","个人总计提成");
    }*/

    /**
     *
     * @param uid
     * @param money
     * @param username
     * @param orderId
     */
    private void leaderPushMoney(int uid,double money,String username,String orderId){

        if(money != 0){
            marketValueService.updateLeaderMoneyByUser(uid,money,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        }
        //-- 团队业绩增加
        addMoneyRecord(uid,new BigDecimal(money),4,username,orderId,"+","团队领导奖励提成");
    }

    /**
     * 团队提成
     * @param uid
     * @param money
     * @param username
     * @param orderId
     */
    private void teamPushMoney(int uid,double money,String username,String orderId){

        if(money != 0){
            marketValueService.updateTeamMoneyByUser(uid,money,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        }
        //-- 团队业绩增加
        addMoneyRecord(uid,new BigDecimal(money),3,username,orderId,"+","团队提成");
    }

    /**
     * 个人提成
     * @param uid
     * @param money
     * @param username
     * @param orderId
     */
    private void personalPushMoney(int uid,double money,String username,String orderId){

        if(money != 0){
            marketValueService.updatePersonMoneyByUser(uid,money,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        }
        //-- 个人业绩增加
        addMoneyRecord(uid,new BigDecimal(money),2,username,orderId,"+","个人提成");
    }
    /**
     * 个人提层费率
     * @param mid
     * @param money
     * @return
     */
    private MarketRate getPersonalMarketRate(int mid,double money){

        List<MarketRate>  marketRates = marketRateService.getMarketRateByUser(mid);
        if(marketRates.size() == 0){
            marketRates = marketRateService.getDefaultMarketRate();
        }

        for(MarketRate marketRate:marketRates){

              if(marketRate.getPerformanceFrom().doubleValue() <= money && marketRate.getPerformanceTo().doubleValue() > money){
                 return  marketRate;
              }

             if(marketRate.getPerformanceTo().doubleValue() == 0 && marketRate.getPerformanceFrom().doubleValue() <= money){
               return  marketRate;
             }

            continue;
        }
        return  null;
    }

    /**
     * 新增金钱变动记录
     * @param uid
     * @param money
     * @param type
     * @param username
     * @param orderId
     * @param change_flag
     * @param remark
     */
    private void addMoneyRecord(int uid,BigDecimal money,int type,String username,String orderId,String change_flag,String remark){
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


}
