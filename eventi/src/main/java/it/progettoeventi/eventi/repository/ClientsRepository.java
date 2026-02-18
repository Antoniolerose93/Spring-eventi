package it.progettoeventi.eventi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.progettoeventi.eventi.model.Clients;


public interface ClientsRepository extends JpaRepository<Clients, Integer> {

List<Clients> findByNameContainingIgnoreCase (String name);

List<Clients> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);

Optional<Clients> findByMail(String mail);

}
