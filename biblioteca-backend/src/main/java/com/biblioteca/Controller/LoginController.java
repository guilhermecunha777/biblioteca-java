package com.biblioteca.Controller;

import com.biblioteca.Model.Aluno;
import com.biblioteca.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String ra = body.get("ra");
        String senha = body.get("senha");

        Optional<Aluno> aluno = alunoRepository.findByRaAndSenha(ra, senha);

        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("RA ou senha inválidos");
        }
    }
}
