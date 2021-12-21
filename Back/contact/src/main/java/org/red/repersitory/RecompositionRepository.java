package org.red.repersitory;

import org.red.entity.Cotisation;
import org.red.entity.Reconposations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecompositionRepository extends JpaRepository<Reconposations,Integer> {
    @Query("select a from Reconposations a where a.typere = ?1")
    List<Reconposations> findCotisationsByType(String typere);

    @Query("select a from Reconposations a where a.id_cotisations = ?1")
    Reconposations findTyperedeCotisation(Integer id);
}
