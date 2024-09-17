package br.ufc.ControleLaboratorio.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.ControleLaboratorio.Model.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Integer> {
	
	Optional<Professor> findById(String email);

    void deleteById(String email);

    Optional<Professor> findUserByNome(String nome);

    Optional<Professor> findUserByEmail(String email);

    Professor saveAndFlush(Professor professor);


    }
