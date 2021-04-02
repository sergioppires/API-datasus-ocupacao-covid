package com.sergioppires.hospitaltracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sergioppires.hospitaltracker.command.DataSusCommand;
import com.sergioppires.hospitaltracker.domain.HospitalData;

import java.text.ParseException;
import java.util.List;

public interface DataSus {

    List<HospitalData> getDataSus(DataSusCommand command) throws JsonProcessingException, ParseException;

}
