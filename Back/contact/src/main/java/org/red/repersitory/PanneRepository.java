package org.red.repersitory;

import org.red.entity.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanneRepository extends JpaRepository<Panne,Integer> {
}
