package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Integer> {
    void deleteByMatricula(String matricula);

    Optional<AlunoModel> findByMatricula(String matricula);

    Boolean existsByMatricula(String matricula);

    Boolean existsByCpf(String cpf);

    List<AlunoModel> findByNomeContaining(String nome);

    List<AlunoModel> findByOrderByNomeDesc();

    Optional<AlunoModel> findByEmail(String email);

    @Query(value = """
                select *
                    from aluno
                    where email = :email)
            """, nativeQuery = true)
    List<AlunoModel> ObterAlunoPorEmail(@Param("email") String email);

    @Query(
            value = """
                    SELECT a.*
                        FROM aluno a
                        JOIN matricula m
                            ON m.idAluno = a.id
                        JOIN turma t
                            ON t.idDisciplina = :p1;
                    """,
            nativeQuery = true
    )
    Page<AlunoModel> obterAlunosPorDisciplina(
            @Param("p1") int idDisciplina,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT a.*
                        FROM aluno a
                        JOIN matricula m
                            ON m.idAluno = a.id
                        JOIN turma t
                            ON t.idDisciplina = m.idDisciplina AND t.idProfessor = :p1
                        WHERE t.dataFim > :p2;
                    """,
            nativeQuery = true
    )
    Page<AlunoModel> obterAlunosLecionadosAtualmentePorProfessor(
            @Param("p1") int idProfessor,
            @Param("p2") Date dataAtual,
            Pageable pageable
    );
}
