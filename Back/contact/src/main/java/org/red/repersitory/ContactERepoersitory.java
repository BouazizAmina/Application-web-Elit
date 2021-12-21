package org.red.repersitory;

import com.querydsl.core.types.dsl.Param;
import org.red.entity.Cotisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactERepoersitory extends JpaRepository<Cotisation,Integer> , QuerydslPredicateExecutor<Cotisation>{

    @Query("select a from Cotisation a where a.id =?1")
    Optional<Cotisation> findCotisationById(Integer id) ;

    @Query("select a from Cotisation a where a.taux =?1")
    List<Cotisation> findCotisationByTaux(String taux);
}