package com.sergioppires.hospitaltracker.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergioppires.hospitaltracker.command.DataSusCommand;
import com.sergioppires.hospitaltracker.domain.DataSusResponse;
import com.sergioppires.hospitaltracker.domain.HospitalData;
import com.sergioppires.hospitaltracker.service.DataSus;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataSusImpl implements DataSus {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<HospitalData> getDataSus(DataSusCommand command) throws JsonProcessingException, ParseException {
        String url = "https://elastic-leitos.saude.gov.br/leito_ocupacao/_search?size=10000";
        HttpEntity<String> request = new HttpEntity<>(returnPayload(command.getEstado()));
        ResponseEntity<String> output = restTemplate.postForEntity(url, request, String.class);
        String rawData = output.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(rawData);
        JsonNode teste = jsonNode.get("hits");
        List<HospitalData> hospitalDataList = new ArrayList<>();
        teste.get("hits").forEach(data ->{
            try {
                DataSusResponse response = mapper.readValue(data.toString(), DataSusResponse.class);
                if(response.get_source().getEstadoSigla().equals(command.getEstado())){
                    hospitalDataList.add(response.get_source());
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return hospitalDataList;
    }

    private String returnPayload(String estado) throws ParseException {
        return  "{\n" +
                "      \"query\": {\n" +
                "        \"bool\": {\n" +
                "          \"should\": [\n" +
                "            { \"match\": { \"estado\": \"" + estado + "\" }},\n" +
                "            { \"match\": { \"dataNotificacaoOcupacao\": \"" + LocalDate.now() + "\" }}\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    }";
    }
}
