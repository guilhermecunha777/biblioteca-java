package com.biblioteca.Repository;

import com.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Livro findById(long id);
}