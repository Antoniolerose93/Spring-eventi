package it.progettoeventi.eventi.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.progettoeventi.eventi.model.Events;




public interface EventsRepository extends JpaRepository<Events, Integer> {

        List<Events> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCityContainingIgnoreCase(String name, String description, String city);

       


        Optional <Events> findByNameAndCityAndDate(String name, String city, LocalDate date);

}
