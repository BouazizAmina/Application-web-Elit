package org.red.repersitory;


import com.querydsl.jpa.impl.JPAQuery;


import lombok.var;
import org.red.entity.Cotisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LocalRepositopry{

    private EntityManager entityManager;
 @Autowired
 ContactERepoersitory contactERepoersitory;

    public List<Cotisation> Find(){

       return entityManager.createQuery("select a from Cotisation a ")
                .getResultList();

    }



public void findlby(){






}


}
