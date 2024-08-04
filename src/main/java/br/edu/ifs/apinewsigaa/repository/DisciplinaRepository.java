package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaModel, Integer> {
    @Query(
            value = """
                    SELECT d.*
                        FROM disciplina d
                        JOIN turma t
                            ON t.id_Disciplina = d.id
                        JOIN matricula m
                            ON m.id_Turma = t.id AND m.id_Aluno = :p1
                        WHERE t.data_Fim > :p2
                    """,
            nativeQuery = true
    )
    Page<DisciplinaModel> obterDisciplinasAtuaisPorAluno(
            @Param("p1") int idAluno,
            @Param("p2") Date dataAtual,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT d.*
                        FROM disciplina d
                        JOIN turma t
                            ON t.id_Disciplina = d.id
                        JOIN matricula m
                            ON m.id_Turma = t.id AND m.id_Aluno = :p1
                    """,
            nativeQuery = true
    )
    Page<DisciplinaModel> obterHistoricoDeDisciplinasPorAluno(
            @Param("p1") int idAluno,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT d.*
                        FROM disciplina d
                        JOIN turma t
                            ON t.id_Disciplina = d.id AND t.id_Professor = :p1
                        WHERE t.data_Fim > :p2
                    """,
            nativeQuery = true
    )
    Page<DisciplinaModel> obterDisciplinasAtuaisPorProfessor(
            @Param("p1") int idProfessor,
            @Param("p2") Date dataAtual,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT d.*
                        FROM disciplina d
                        JOIN turma t
                            ON t.id_Disciplina = d.id AND t.id_Professor = :p1
                    """,
            nativeQuery = true
    )
    Page<DisciplinaModel> obterHistoricoDeDisciplinasPorProfessor(
            @Param("p1") int idProfessor,
            Pageable pageable
    );
}
