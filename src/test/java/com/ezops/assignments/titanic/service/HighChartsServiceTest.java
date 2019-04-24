package com.ezops.assignments.titanic.service;

import com.ezops.assignments.titanic.entity.BarChartSeriesEntity;
import com.ezops.assignments.titanic.entity.PieChartSeriesEntity;
import com.ezops.assignments.titanic.repository.PassengerRepo;
import org.decimal4j.util.DoubleRounder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HighChartsServiceTest {
    @InjectMocks
    HighChartsService highChartsService;

    @Mock
    PassengerRepo passengerRepo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSurvicedBarChartTest() {
        int survivedCount = 1;
        int notsurvivedCount = 1;

        when(passengerRepo.getCountSurvived("yes")).thenReturn(survivedCount);
        when(passengerRepo.getCountSurvived("no")).thenReturn(notsurvivedCount);

        List<String> categories = Arrays.asList("Survived","Perished");
        BarChartSeriesEntity survivedBarChartSeries = new BarChartSeriesEntity("Passengers", Arrays.asList(survivedCount,notsurvivedCount));
        List<BarChartSeriesEntity> barChartData = new ArrayList<>();
        Map<String,Object> res;

        res = highChartsService.getSurvicedBarChart();

        List<BarChartSeriesEntity> series = (List<BarChartSeriesEntity>)res.get("series");

        assertEquals(res.size(), 2);
        assertEquals(res.get("categories"), categories);
        assertEquals(series.get(0).getName(), survivedBarChartSeries.getName());
        assertEquals(series.get(0).getData(), survivedBarChartSeries.getData());
    }

    @Test
    public void getSurvicedPieChart_survived_equal_to_not_survived() {
        int survivedCount = 1;
        int notsurvivedCount = 1;

        when(passengerRepo.getCountSurvived("yes")).thenReturn(survivedCount);
        when(passengerRepo.getCountSurvived("no")).thenReturn(notsurvivedCount);

        double totalCount = survivedCount + notsurvivedCount;
        double surviedPercentage = DoubleRounder.round(survivedCount/totalCount*100,2);
        double notSurviedPercentage = DoubleRounder.round(notsurvivedCount/totalCount*100,2);

        List<PieChartSeriesEntity> res = highChartsService.getSurvicedPieChart();

        List<PieChartSeriesEntity> pieChartData = new ArrayList<>();

        PieChartSeriesEntity pieChartSeriesEntitySurvived = new PieChartSeriesEntity("Survived", surviedPercentage,false,false);
        PieChartSeriesEntity pieChartSeriesEntityNotSurvived = new PieChartSeriesEntity("Perished", notSurviedPercentage,true,true);
        pieChartData.add(pieChartSeriesEntitySurvived);
        pieChartData.add(pieChartSeriesEntityNotSurvived);

        assertEquals(res.get(0).getName(), pieChartData.get(0).getName());
        assertEquals(res.get(0).getSelected(), pieChartData.get(0).getSelected());
        assertEquals(res.get(1).getName(), pieChartData.get(1).getName());
        assertEquals(res.get(1).getSelected(), pieChartData.get(1).getSelected());
    }

    @Test
    public void getSurvicedPieChart_survived_greater_than_not_survived() {
        int survivedCount = 2;
        int notsurvivedCount = 1;

        when(passengerRepo.getCountSurvived("yes")).thenReturn(survivedCount);
        when(passengerRepo.getCountSurvived("no")).thenReturn(notsurvivedCount);

        double totalCount = survivedCount + notsurvivedCount;
        double surviedPercentage = DoubleRounder.round(survivedCount/totalCount*100,2);
        double notSurviedPercentage = DoubleRounder.round(notsurvivedCount/totalCount*100,2);

        List<PieChartSeriesEntity> res = highChartsService.getSurvicedPieChart();

        List<PieChartSeriesEntity> pieChartData = new ArrayList<>();

        PieChartSeriesEntity pieChartSeriesEntitySurvived = new PieChartSeriesEntity("Survived", surviedPercentage,true,true);
        PieChartSeriesEntity pieChartSeriesEntityNotSurvived = new PieChartSeriesEntity("Perished", notSurviedPercentage,false,false);
        pieChartData.add(pieChartSeriesEntitySurvived);
        pieChartData.add(pieChartSeriesEntityNotSurvived);

        assertEquals(res.get(0).getName(), pieChartData.get(0).getName());
        assertEquals(res.get(0).getSelected(), pieChartData.get(0).getSelected());
        assertEquals(res.get(1).getName(), pieChartData.get(1).getName());
        assertEquals(res.get(1).getSelected(), pieChartData.get(1).getSelected());
    }
}
