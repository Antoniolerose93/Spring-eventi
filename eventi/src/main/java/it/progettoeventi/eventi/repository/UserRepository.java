package it.progettoeventi.eventi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.progettoeventi.eventi.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional <User> findByUsername (String username);

}
