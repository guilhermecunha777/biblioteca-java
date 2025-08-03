package com.biblioteca.Repository;

import com.biblioteca.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Optional<Aluno> findByRaAndSenha(String ra, String senha);
    Optional<Aluno> findByRa(String ra);
}