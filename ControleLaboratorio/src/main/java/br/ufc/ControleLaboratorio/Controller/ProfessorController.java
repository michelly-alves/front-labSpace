package br.ufc.ControleLaboratorio.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.ControleLaboratorio.Model.Professor;
import br.ufc.ControleLaboratorio.Repository.ProfessorRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
    
    @Autowired
    ProfessorRepository professorRepository;
    
    @GetMapping
    public List<Professor> GetAllProfessores() {
        return (List<Professor>) professorRepository.findAll();
    }
    
    @PostMapping
    public Professor save(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable("id") int id) {
        Optional<Professor> professor = professorRepository.findById(id);
        if (professor.isPresent()) {
            return ResponseEntity.ok(professor.get());
        } else {
        	throw new RuntimeException("Professor com ID " + id + " não encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable("id") int id, @RequestBody Professor professorDetails) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            professor.setNome(professorDetails.getNome());
            professor.setEmail(professorDetails.getEmail());
            professorRepository.save(professor);
            return ResponseEntity.ok(professor);
        } else {
        	throw new RuntimeException("Professor com ID " + id + " não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("id") int id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
        	throw new RuntimeException("Professor com ID " + id + " não encontrado.");
        }
    }
}
