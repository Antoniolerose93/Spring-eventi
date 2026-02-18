package it.progettoeventi.eventi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.progettoeventi.eventi.model.EventsCategories;


public interface EventsCategoriesRepository extends JpaRepository<EventsCategories, Integer> {

    List<EventsCategories> findByNameContainingIgnoreCase(String keyword);

    Optional<EventsCategories> findByName(String name);
    

}
