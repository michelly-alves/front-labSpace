package br.ufc.ControleLaboratorio.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.ControleLaboratorio.Model.Aluno;
import br.ufc.ControleLaboratorio.Repository.AlunoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    
    @Autowired
    AlunoRepository alunoRepository;
    
    @GetMapping
    public List<Aluno> GetAllAlunos() {
        return (List<Aluno>) alunoRepository.findAll();
    }
    
    @PostMapping
    public Aluno save(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

  @PostMapping("/login")
public ResponseEntity<?> login(@RequestParam String email, @RequestParam String senha) {
    Optional<Aluno> aluno = alunoRepository.findByEmail(email);
    if (aluno.isPresent() && aluno.get().getSenha().equals(senha)) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login realizado com sucesso.");
        response.put("alunoId", aluno.get().getId());
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos.");
    }
}

    
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable("id") int id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        } else {
        	throw new RuntimeException("Aluno com ID " + id + " não encontrado.");
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Aluno> getAlunoByEmail(@PathVariable("email") String email) {
        Optional<Aluno> aluno = alunoRepository.findByEmail(email);
        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @PutMapping("/email")
public ResponseEntity<Aluno> updateAlunoByEmail(@RequestParam("email") String email, @RequestBody Aluno alunoDetails) {
    Optional<Aluno> alunoOptional = alunoRepository.findByEmail(email);
    if (alunoOptional.isPresent()) {
        Aluno aluno = alunoOptional.get();
        aluno.setNome(alunoDetails.getNome());
        aluno.setMatricula(alunoDetails.getMatricula());
        aluno.setSenha(alunoDetails.getSenha());
        alunoRepository.save(aluno);
        return ResponseEntity.ok(aluno);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}


    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable("id") int id, @RequestBody Aluno alunoDetails) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            aluno.setNome(alunoDetails.getNome());
            aluno.setEmail(alunoDetails.getEmail());
            aluno.setSenha(alunoDetails.getSenha());
            aluno.setMatricula(alunoDetails.getMatricula());
            alunoRepository.save(aluno);
            return ResponseEntity.ok(aluno);
        } else {
        	throw new RuntimeException("Aluno com ID " + id + " não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable("id") int id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
        	throw new RuntimeException("Aluno com ID " + id + " não encontrado.");
        }
    }
}
