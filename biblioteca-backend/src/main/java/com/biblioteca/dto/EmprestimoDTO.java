package com.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class EmprestimoDTO {
    private String ra;
    private Long livroId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataRetirada;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataEntrega;

    // Getters e setters
    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }

    public Long getLivroId() { return livroId; }
    public void setLivroId(Long livroId) { this.livroId = livroId; }

    public LocalDate getDataRetirada() { return dataRetirada; }
    public void setDataRetirada(LocalDate dataRetirada) { this.dataRetirada = dataRetirada; }

    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }
}
