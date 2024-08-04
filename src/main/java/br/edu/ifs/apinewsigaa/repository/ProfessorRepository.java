package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Integer> {
    Optional<ProfessorModel> findByMatricula(int matricula);

    @Query(
            value = """
                    SELECT p.*
                        FROM professor p
                        JOIN turma t
                            ON t.id_Professor = p.id AND t.id_Disciplina = :p1
                    """,
            nativeQuery = true
    )
    Page<ProfessorModel> obterProfessoresPorDisciplina(
            @Param("p1") int idDisciplina,
            Pageable pageable
    );
}
