package br.ufc.ControleLaboratorio.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.ControleLaboratorio.Model.Evento;
import br.ufc.ControleLaboratorio.Repository.EventoRepository;

@CrossOrigin(origins = "http://localhost:4200")@RestController
@RequestMapping("/api/evento")
public class EventoController {
    
    @Autowired
    EventoRepository eventoRepository;
    
    @GetMapping
    public List<Evento> getAllEventos() {
        return (List<Evento>) eventoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Evento> getEventoById(@PathVariable int id) {
        return eventoRepository.findById(id);
    }
    
    @PostMapping
    public Evento save(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }
    
    @PutMapping("/{id}")
    public Evento update(@PathVariable int id, @RequestBody Evento eventoDetails) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);
        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();

            evento.setNome(eventoDetails.getNome());
            evento.setDescricao(eventoDetails.getDescricao());
            evento.setLocal(eventoDetails.getLocal());
            evento.setData(eventoDetails.getData());
            evento.setHora(eventoDetails.getHora());
            evento.setProfessor(eventoDetails.getProfessor()); 
            eventoRepository.save(evento);
            return (evento);
        } else {
        	throw new RuntimeException("Evento com ID " + id + " não encontrado.");
        }
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Evento com ID " + id + " não encontrado.");
        }
    }
}
