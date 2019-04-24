package com.ezops.assignments.titanic.controller;


import com.ezops.assignments.titanic.entity.PieChartSeriesEntity;
import com.ezops.assignments.titanic.service.HighChartsService;
import com.ezops.assignments.titanic.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author yao.chen
 */

@RestController
@RequestMapping("/titanic/chart")
public class TitanicChartController {
    static Logger logger = LoggerFactory.getLogger(TitanicChartController.class);

    @Autowired
    private HighChartsService highChartsService;

    /**
     *  get the bar chart data of the Titanic Survived attribute
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value ="bar/survived",method = RequestMethod.GET)
    public  ResponseEntity<RestResponse>  getSurvicedBarChart(){
        logger.debug("/titanic/chart/bar/survived");
        RestResponse response = new RestResponse<>(RestResponse.STATUS_FAIL);
        try {
            Map<String,Object> data = highChartsService.getSurvicedBarChart();
            if(data!=null){
                response = new RestResponse<>(RestResponse.STATUS_SUCCESS,data);
            }
        }
        catch (Exception e){
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            response = new RestResponse<>(RestResponse.STATUS_FAIL,e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<RestResponse>(response,response.getError_code());
    }


    /**
     *  get the pie chart data of the Titanic Survived attribute
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value ="/pie/survived",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> getSurvicedPieChart() {
        logger.debug("/titanic/chart/pie/survived");

        RestResponse response = new RestResponse<>(RestResponse.STATUS_FAIL);
        try {
            List<PieChartSeriesEntity> data = highChartsService.getSurvicedPieChart();
            if(data!=null){
                response = new RestResponse<>(RestResponse.STATUS_SUCCESS,data);
            }
        }
        catch (Exception e){
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            response = new RestResponse<>(RestResponse.STATUS_FAIL,e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<RestResponse>(response,response.getError_code());
    }

}
