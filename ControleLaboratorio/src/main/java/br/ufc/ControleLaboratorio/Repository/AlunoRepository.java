package br.ufc.ControleLaboratorio.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.ControleLaboratorio.Model.Aluno;

@Repository
	public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

    Optional<Aluno> findByEmail(String email);

	    }
