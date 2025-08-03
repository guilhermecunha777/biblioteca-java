package com.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_ra", referencedColumnName = "ra")
    @JsonIgnoreProperties("emprestimos")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    @JsonIgnoreProperties("emprestimos")
    private Livro livro;

    @NotNull(message = "A data de retirada é obrigatória")
    @PastOrPresent(message = "A data de retirada não pode ser no futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataRetirada;

    @NotNull(message = "A data de entrega é obrigatória")
    @Future(message = "A data de entrega deve ser no futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataEntrega;

    @NotNull
    private boolean devolvido = false;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public LocalDate getDataRetirada() { return dataRetirada; }
    public void setDataRetirada(LocalDate dataRetirada) { this.dataRetirada = dataRetirada; }

    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }

    public boolean isDevolvido() { return devolvido; }
    public void setDevolvido(boolean devolvido) { this.devolvido = devolvido; }
}
