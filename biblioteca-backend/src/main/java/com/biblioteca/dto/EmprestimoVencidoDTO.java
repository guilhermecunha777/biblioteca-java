package com.biblioteca.dto;

import java.time.LocalDate;

public class EmprestimoVencidoDTO {

    private Long id;
    private LocalDate dataRetirada;
    private LocalDate dataEntrega;
    private String ra;
    private Long livroId;
    private String status;

    public EmprestimoVencidoDTO(Long id, LocalDate dataRetirada, LocalDate dataEntrega, String ra, Long livroId, String status) {
        this.id = id;
        this.dataRetirada = dataRetirada;
        this.dataEntrega = dataEntrega;
        this.ra = ra;
        this.livroId = livroId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
