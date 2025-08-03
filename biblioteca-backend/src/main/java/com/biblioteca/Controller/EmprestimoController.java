package com.biblioteca.Controller;

import com.biblioteca.Model.Aluno;
import com.biblioteca.Model.Emprestimo;
import com.biblioteca.Model.Livro;
import com.biblioteca.Repository.AlunoRepository;
import com.biblioteca.Repository.EmprestimoRepository;
import com.biblioteca.Repository.LivroRepository;
import com.biblioteca.dto.EmprestimoDTO;
import com.biblioteca.dto.EmprestimoVencidoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarEmprestimo(@RequestBody EmprestimoDTO dto) {

        Aluno aluno = alunoRepository.findByRa(dto.getRa())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setAluno(aluno);
        emprestimo.setLivro(livro);
        emprestimo.setDataRetirada(dto.getDataRetirada());
        emprestimo.setDataEntrega(dto.getDataEntrega());
        emprestimo.setDevolvido(false);

        emprestimoRepository.save(emprestimo);

        return ResponseEntity.ok("Empréstimo cadastrado com sucesso");
    }

    @GetMapping("/listar")
    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<String> marcarcomoDevolvido(@PathVariable Long id) {
        Optional<Emprestimo> optionalEmprestimo = emprestimoRepository.findById(id);

        if (optionalEmprestimo.isPresent()) {
            Emprestimo emprestimo = optionalEmprestimo.get();
            emprestimo.setDevolvido(true);
            emprestimoRepository.save(emprestimo);
            return ResponseEntity.ok("emprestimo marcado como devolvido.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vencidos")
    public List<EmprestimoVencidoDTO> listarEmprestimosVencidos() {
        LocalDate hoje = LocalDate.now();
        List<Emprestimo> vencidos = emprestimoRepository.findByDataEntregaBefore(hoje);

        return vencidos.stream()
                .map(e -> new EmprestimoVencidoDTO(
                        e.getId(),
                        e.getDataRetirada(),
                        e.getDataEntrega(),
                        e.getAluno().getRa(),
                        e.getLivro().getId(),
                        "Atenção: empréstimo vencido"
                ))
                .collect(Collectors.toList());
    }
}
