package com.wshop.service.impl;

import com.wshop.model.MarketRate;
import com.wshop.dao.MarketRateMapper;
import com.wshop.service.MarketRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: han
 * Date: 16-8-5
 * Time: 上午8:44
 */

@Service
public class MarketRateServiceImpl implements MarketRateService{

     @Autowired
     MarketRateMapper marketRateDAO;

    @Override
    public List<MarketRate> getMarketRateByUser(Integer mid) {
       return marketRateDAO.getMarketRateByUser(mid);
    }

    @Override
    public List<MarketRate> getDefaultMarketRate(){
        //return marketRateDAO.getMarketRateByUser(0);
        return null;
    }
}
