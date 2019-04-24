package com.ezops.assignments.titanic.controller;

import com.ezops.assignments.titanic.model.Passenger;
import com.ezops.assignments.titanic.repository.PassengerRepo;
import com.ezops.assignments.titanic.utils.RestResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TitanicPassengersControllerTest {

    @InjectMocks
    TitanicPassengersController titanicPassengersController;

    @Mock
    PassengerRepo passengerRepo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listPassengers() {
        RestResponse response;
        Passenger passenger = buildPassenger();
    }

    private Passenger buildPassenger() {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setSurvived("survived");
        passenger.setPclass(1);
        passenger.setName("name");
        passenger.setPclass(new Integer(1));
        passenger.setSex("sex");
        passenger.setAge("10");
        passenger.setSibSp(new Integer(1));
        passenger.setParch(new Integer(1));
        passenger.setTicket("ticket");
        passenger.setFare(1.0);
        passenger.setCabin("cabin");
        passenger.setEmbarked("embarked");
        return passenger;
    }
}
