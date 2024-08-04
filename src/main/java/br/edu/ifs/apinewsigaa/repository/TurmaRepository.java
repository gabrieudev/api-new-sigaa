package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.TurmaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaModel, Integer> {
    Page<TurmaModel> findByIdProfessor(int idProfessor, Pageable pageable);

    Page<TurmaModel> findByIdDisciplina(int idDisciplina, Pageable pageable);
}
