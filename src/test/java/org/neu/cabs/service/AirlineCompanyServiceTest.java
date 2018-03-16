package org.neu.cabs.service;

import javafx.scene.control.Alert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neu.cabs.orm.AirlineCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AirlineCompanyServiceTest {
    @Autowired
    AirlineCompanyService airlineCompanyService;

    @Test
    public void findAll() {
//        List<AirlineCompany> airlineCompanies = airlineCompanyService.getAllAirlineCompany();
//        assertEquals(airlineCompanies, new AirlineCompany());
        AirlineCompany airlineCompany = airlineCompanyService.getAirlineCompanyById(1);
        assertEquals(airlineCompany, new AirlineCompany());
    }
}