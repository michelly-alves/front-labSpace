package br.ufc.ControleLaboratorio.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufc.ControleLaboratorio.Model.Inscricao;
import br.ufc.ControleLaboratorio.Repository.InscricaoRepository;

@CrossOrigin(origins = "http://localhost:4200")@RestController
@RequestMapping("/api/inscricao")
public class InscricaoController {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @GetMapping
    public List<Inscricao> getAllInscricoes() {
        return (List<Inscricao>) inscricaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> getInscricaoById(@PathVariable("id") int id) {
        Optional<Inscricao> inscricao = inscricaoRepository.findById(id);
        if (inscricao.isPresent()) {
            return ResponseEntity.ok(inscricao.get());
        } else {
        	throw new RuntimeException("Professor com ID " + id + " não encontrado.");
        }
    }

    @PostMapping
    public Inscricao save(@RequestBody Inscricao inscricao) {
        return inscricaoRepository.save(inscricao);
    }

    @PutMapping("/{id}")
    public Inscricao update(@PathVariable int id, @RequestBody Inscricao inscricaoDetails) {
        Optional<Inscricao> optionalInscricao = inscricaoRepository.findById(id);
        if (optionalInscricao.isPresent()) {
            Inscricao inscricao = optionalInscricao.get();
            
            inscricao.setAluno(inscricaoDetails.getAluno());
            inscricao.setEvento(inscricaoDetails.getEvento());
            inscricao.setStatus(inscricaoDetails.getStatus());

            return inscricaoRepository.save(inscricao);
        } else {
            throw new RuntimeException("Inscrição com ID " + id + " não encontrada.");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        if (inscricaoRepository.existsById(id)) {
            inscricaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Inscrição com ID " + id + " não encontrada.");
        }
    }
}
