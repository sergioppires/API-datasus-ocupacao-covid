package com.sergioppires.hospitaltracker.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HospitalData {

    private String estado;
    private String estadoSigla;
    private String municipio;
    private String cnes;
    private String nomeCnes;
    private String dataNotificacaoOcupacao;
    private String ofertaRespiradores;
    private String ofertaHospCli;
    private String ofertaHospUti;
    private String ofertaSRAGCli;
    private String ofertaSRAGUti;
    private String ocupHospCli;
    private String ocupHospUti;
    private String ocupSRAGCli;
    private String ocupSRAGUti;
    private String altas;
    private String obitos;
    private String ocupacaoInformada;
    private String algumaOcupacaoInformada;

}
