package org.red.repersitory;

import org.red.entity.Recepteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Recepteur, Long> {
    Optional<Recepteur> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}