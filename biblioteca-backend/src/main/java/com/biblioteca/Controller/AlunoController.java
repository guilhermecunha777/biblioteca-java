package com.biblioteca.Controller;

import com.biblioteca.Model.Aluno;
import com.biblioteca.Model.Emprestimo;
import com.biblioteca.Repository.AlunoRepository;
import com.biblioteca.dto.AlunoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/listar")
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();

        String raGerado = "ALUNO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        aluno.setRa(raGerado);

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setTelefone(dto.getTelefone());
        aluno.setSenha(dto.getSenha());
        aluno.setDataNascimento(dto.getDataNascimento());

        alunoRepository.save(aluno);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }
}

