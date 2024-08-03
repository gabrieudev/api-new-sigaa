package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Integer> {

}
