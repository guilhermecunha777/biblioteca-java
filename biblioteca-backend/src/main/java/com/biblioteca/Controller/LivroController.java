package com.biblioteca.Controller;

import com.biblioteca.Model.Livro;
import com.biblioteca.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping("/cadastrar")
    public Livro cadastrarLivro(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @GetMapping("/listar")
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public Livro buscarporcodigo(@PathVariable long id) {
        return livroRepository.findById(id);
    }
}
