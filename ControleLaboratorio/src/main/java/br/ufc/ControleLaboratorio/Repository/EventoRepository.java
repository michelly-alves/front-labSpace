package br.ufc.ControleLaboratorio.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.ControleLaboratorio.Model.Evento;


@Repository
public interface EventoRepository extends CrudRepository<Evento, Integer> {


    }
