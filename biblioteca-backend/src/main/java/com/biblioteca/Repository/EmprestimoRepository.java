package com.biblioteca.Repository;

import com.biblioteca.Model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByDataEntregaBefore(LocalDate dataAtual);
    List<Emprestimo> findByDevolvido(boolean devolvido);
    List<Emprestimo> findByDataEntregaBeforeAndDevolvidoFalse(LocalDate data);
    List<Emprestimo> findAllByOrderByDevolvidoAsc();
}