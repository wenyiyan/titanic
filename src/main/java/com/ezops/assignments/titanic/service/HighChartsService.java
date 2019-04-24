package com.ezops.assignments.titanic.service;


import com.ezops.assignments.titanic.entity.BarChartSeriesEntity;
import com.ezops.assignments.titanic.entity.PieChartSeriesEntity;
import com.ezops.assignments.titanic.repository.PassengerRepo;
import org.decimal4j.util.DoubleRounder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * @author yao.chen
 */
@Component
public class HighChartsService {
    static Logger logger = LoggerFactory.getLogger(HighChartsService.class);

    @Autowired
    private PassengerRepo passengerRepo;

    /**
     * construct BarChart data
     * @return
     */
    public Map<String,Object> getSurvicedBarChart(){

        int survivedCount = passengerRepo.getCountSurvived("yes");
        int notsurvivedCount = passengerRepo.getCountSurvived("no");
        logger.debug(String.valueOf(survivedCount));
        logger.debug(String.valueOf(notsurvivedCount));

        //cateogories
        List<String> categories = Arrays.asList("Survived","Perished");
        Map<String,Object> barChartResult = new HashMap();
        List<BarChartSeriesEntity> barChartData = new ArrayList<>();

        //BarChartSeriesEntity
        BarChartSeriesEntity survivedBarChartSeries = new BarChartSeriesEntity("Passengers", Arrays.asList(survivedCount,notsurvivedCount));
        barChartData.add(survivedBarChartSeries);

        //barChartResult
        barChartResult.put("categories",categories);
        barChartResult.put("series",barChartData);
        return barChartResult;
    }



    /**
     * construct PieChart data
     * @return
     */
    public List<PieChartSeriesEntity> getSurvicedPieChart(){
       //calculate percentages for both survived and  perished passengers
       double survivedCount = (double)passengerRepo.getCountSurvived("yes");
       double notsurvivedCount = (double)passengerRepo.getCountSurvived("no");
       double totalCount = survivedCount + notsurvivedCount;
       double surviedPercentage = DoubleRounder.round(survivedCount/totalCount*100,2);
       double notSurviedPercentage = DoubleRounder.round(notsurvivedCount/totalCount*100,2);

       List<PieChartSeriesEntity> pieChartData = new ArrayList<>();
       // check which group should set sliced and selected to true
       if(survivedCount > notsurvivedCount) {
           PieChartSeriesEntity pieChartSeriesEntitySurvived = new PieChartSeriesEntity("Survived", surviedPercentage,true,true);
           PieChartSeriesEntity pieChartSeriesEntityNotSurvived = new PieChartSeriesEntity("Perished", notSurviedPercentage,false,false);
           pieChartData.add(pieChartSeriesEntitySurvived);
           pieChartData.add(pieChartSeriesEntityNotSurvived);
       }
       else {
           PieChartSeriesEntity pieChartSeriesEntitySurvived = new PieChartSeriesEntity("Survived", surviedPercentage,false,false);
           PieChartSeriesEntity pieChartSeriesEntityNotSurvived = new PieChartSeriesEntity("Perished", notSurviedPercentage,true,true);
           pieChartData.add(pieChartSeriesEntitySurvived);
           pieChartData.add(pieChartSeriesEntityNotSurvived);
       }
       return pieChartData;
    }


}
