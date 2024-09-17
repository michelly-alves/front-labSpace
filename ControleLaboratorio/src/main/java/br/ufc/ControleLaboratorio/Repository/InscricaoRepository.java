package br.ufc.ControleLaboratorio.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.ControleLaboratorio.Model.Inscricao;


@Repository
public interface InscricaoRepository extends CrudRepository<Inscricao, Integer> {


    }
