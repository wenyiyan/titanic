package com.ezops.assignments.titanic.controller;

import com.ezops.assignments.titanic.entity.PieChartSeriesEntity;
import com.ezops.assignments.titanic.service.HighChartsService;
import com.ezops.assignments.titanic.utils.RestResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TitanicChartControllerTest {

    @InjectMocks
    TitanicChartController titanicChartController;

    @Mock
    HighChartsService highChartsService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSurvicedBarChartTest() throws Exception {
        ResponseEntity<RestResponse> responseEntity;
        Map<String, Object> data = new HashMap<>();
        data.put("test", new String("test"));

        when(highChartsService.getSurvicedBarChart()).thenReturn(data);

        responseEntity = titanicChartController.getSurvicedBarChart();
        RestResponse response = responseEntity.getBody();
        assertEquals(response.getStatus(), RestResponse.STATUS_SUCCESS);
        assertEquals(response.getData(), data);
    }

    @Test
    public void getSurvicedBarChartTest_null() throws Exception {
        ResponseEntity<RestResponse> responseEntity;
        Map<String, Object> data = null;

        when(highChartsService.getSurvicedBarChart()).thenReturn(data);

        responseEntity = titanicChartController.getSurvicedBarChart();
        RestResponse response = responseEntity.getBody();
        assertEquals(response.getStatus(), RestResponse.STATUS_FAIL);
    }

    @Test
    public void getSurvicedPieChart() throws Exception {
        ResponseEntity<RestResponse> responseEntity;
        List<PieChartSeriesEntity> data = new ArrayList<>();
        PieChartSeriesEntity pieChartSeriesEntity = new PieChartSeriesEntity("name", 1.0, true, false);
        data.add(pieChartSeriesEntity);

        when(highChartsService.getSurvicedPieChart()).thenReturn(data);

        responseEntity = titanicChartController.getSurvicedPieChart();
        RestResponse response = responseEntity.getBody();
        assertEquals(response.getStatus(), RestResponse.STATUS_SUCCESS);
        assertEquals(response.getData(), data);
    }

    @Test
    public void getSurvicedPieChart_null() throws Exception {
        ResponseEntity<RestResponse> responseEntity;
        List<PieChartSeriesEntity> data = null;

        when(highChartsService.getSurvicedPieChart()).thenReturn(data);

        responseEntity = titanicChartController.getSurvicedPieChart();
        RestResponse response = responseEntity.getBody();
        assertEquals(response.getStatus(), RestResponse.STATUS_FAIL);
    }
}
