package com.wshop.app.web.Trigger;

import com.wshop.app.bo.*;
import com.wshop.app.service.*;
import com.wshop.app.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: han
 * Date: 16-8-6
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class MonthCountJob {
    private static Logger logger = Logger.getLogger(DayMoneyCountJob.class);

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
                if(marketValue.getMarket()>0)  {
                    MarketRate marketRate = getPersonalMarketRate(marketValue.getMid(),marketValue.getMarket());
                    if(marketRate !=null ){
                    myMoney = marketValue.getMarket() * marketRate.getPercent();
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

    private void getTotalMoney(long id){
        MarketValue marketValue =marketValueService.getMarketValueById(id);
        double countMoney = 0;
        countMoney  = marketValue.getPerson_money()+marketValue.getTeam_money() +marketValue.getLeader_money();
        marketValueService.updatePersonMoneyById(id,countMoney);
        marketValueService.updateMarketValueStatus(id,4);
        addMoneyRecord(id,countMoney,5,"","","+","个人总计提成");
    }
    /**
     * 团队提成计算
     * @param uid
     */
    private void getTeamMarket(long uid){
        List<MarketValue> userMarkets = marketValueService.getUserMarketValue(uid,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        MarketValue myMarkeValue = marketValueService.getMarketValueByUser(uid,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        if(userMarkets != null && userMarkets.size() > 0){
            double  countTeamMarket=0,countPushMoney = 0,countTeamPushMoney = 0,leaderCount = 0;

            for (MarketValue userMarket : userMarkets) {

                countTeamMarket  = countTeamMarket + userMarket.getTeam_market();

                MarketRate marketRate1 = getPersonalMarketRate(0,userMarket.getTeam_market());
                double  pushMoney = 0;
                if(marketRate1 != null ){
                    pushMoney  = marketRate1.getPercent() * userMarket.getTeam_market();
                }
              //  logger.info("pushMoney: " + pushMoney);
                countTeamPushMoney = countTeamPushMoney + pushMoney;
              //  logger.info("countTeamPushMoney: " + countTeamPushMoney);
                //计算满足条件的数量
                if(userMarket.getTeam_market() >= 50000){
                    leaderCount++;
                }

            }

            //个人业绩 和小组业绩相加
            countTeamMarket = countTeamMarket + myMarkeValue.getMarket();
            countTeamPushMoney = countTeamPushMoney + myMarkeValue.getPerson_money();

            if(myMarkeValue.getMarket() >= 50000){
                leaderCount++;
            }

            MarketRate marketRate = getPersonalMarketRate(0,countTeamMarket);
            if(marketRate != null ){
                countPushMoney = countTeamMarket * marketRate.getPercent();
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
    private void leaderPushMoney(long uid,double money,String username,String orderId){

        if(money != 0){
            marketValueService.updateLeaderMoneyByUser(uid,money,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        }
        //-- 团队业绩增加
        addMoneyRecord(uid,money,4,username,orderId,"+","团队领导奖励提成");
    }

    /**
     * 团队提成
     * @param uid
     * @param money
     * @param username
     * @param orderId
     */
    private void teamPushMoney(long uid,double money,String username,String orderId){

        if(money != 0){
            marketValueService.updateTeamMoneyByUser(uid,money,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        }
        //-- 团队业绩增加
        addMoneyRecord(uid,money,3,username,orderId,"+","团队提成");
    }

    /**
     * 个人提成
     * @param uid
     * @param money
     * @param username
     * @param orderId
     */
    private void personalPushMoney(long uid,double money,String username,String orderId){

        if(money != 0){
            marketValueService.updatePersonMoneyByUser(uid,money,DateUtil.getNowYear(),DateUtil.getBeforeMonth());
        }
        //-- 个人业绩增加
        addMoneyRecord(uid,money,2,username,orderId,"+","个人提成");
    }
    /**
     * 个人提层费率
     * @param mid
     * @param money
     * @return
     */
    private MarketRate getPersonalMarketRate(long mid,double money){

        List<MarketRate>  marketRates = marketRateService.getMarketRateByUser(mid);
        if(marketRates.size() == 0){
            marketRates = marketRateService.getDefaultMarketRate();
        }

        for(MarketRate marketRate:marketRates){

              if(marketRate.getPerformance_from() <= money && marketRate.getPerformance_to() > money){
                 return  marketRate;
              }

             if(marketRate.getPerformance_to() == 0 && marketRate.getPerformance_from() <= money){
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
    private void addMoneyRecord(long uid,double money,int type,String username,String orderId,String change_flag,String remark){
        DecimalFormat df   = new DecimalFormat("######0.00");
        df.format(money);

        MoneyRecord moneyRecord = new MoneyRecord();
        moneyRecord.setUid(uid);
        moneyRecord.setUser_name(username);
        moneyRecord.setOrderId(orderId);
        moneyRecord.setChange_flag(change_flag);
        moneyRecord.setType(type);
        moneyRecord.setMoney(money);
        moneyRecord.setRemark(remark);
        moneyRecord.setAdd_time(DateUtil.getSysDate());
        moneyRecordService.addMoneyRecord(moneyRecord);
    }


}
