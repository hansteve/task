package com.wshop.service.impl;

import com.wshop.model.MoneyRecord;
import com.wshop.dao.MoneyRecordMapper;
import com.wshop.service.MoneyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by andy on 2016/3/6.
 */
@Service
public class MoneyRecordServiceImpl implements MoneyRecordService {

   /* @Autowired
    MoneyRecordMapper moneyRecordDao;
*/
    @Override
    public void addMoneyRecord(MoneyRecord moneyRecord) {
       // moneyRecordDao.addMoneyRecord(moneyRecord);
    }

   /* @Override
    public void deleteMoneyRecord(MoneyRecord moneyRecord) {
        moneyCountDAO.deleteByPrimaryKey(moneyRecord.getId());
    }*/
}
