package org.red.repersitory;

import com.querydsl.core.types.dsl.Param;
import org.red.entity.Cotisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactERepoersitory extends JpaRepository<Cotisation,Integer> , QuerydslPredicateExecutor<Cotisation>{


    List<Cotisation> findCotisationById(Integer g);


    List<Cotisation> findCotisationByIdAndTaux(Integer g,String ert);

    @Query("select a from Cotisation a where a.taux =?1")
    List<Cotisation> finredouane(String eryr);




    @Query("select a from Cotisation a where a.taux in (?1)")
    List<Cotisation> finrte(List<String> ere);


    Optional<Cotisation> findCotisationsByidAndTaux(Integer id ,String taux);




}


