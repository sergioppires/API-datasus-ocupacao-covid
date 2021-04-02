package com.sergioppires.hospitaltracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sergioppires.hospitaltracker.command.DataSusCommand;
import com.sergioppires.hospitaltracker.domain.HospitalData;
import com.sergioppires.hospitaltracker.service.DataSus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

@Controller
public class HospitalDataController {

    @Autowired
    private DataSus dataSus;

    @PostMapping(value = "/teste")
    public ResponseEntity postHospitalNearLocation(@RequestBody DataSusCommand command) throws JsonProcessingException, ParseException {
        List<HospitalData> hospitalDataList = dataSus.getDataSus(command);
        hospitalDataList.sort(Comparator.comparingInt((HospitalData o) -> Integer.parseInt(o.getObitos())));
        return new ResponseEntity(hospitalDataList, HttpStatus.ACCEPTED);
    }

}
