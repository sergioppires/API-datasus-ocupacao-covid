package com.sergioppires.hospitaltracker.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DataSusResponse {

    private String _index;
    private String _type;
    private String _id;
    private String _score;
    private HospitalData _source;

}
