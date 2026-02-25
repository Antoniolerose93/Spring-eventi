package it.progettoeventi.eventi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.progettoeventi.eventi.model.EventsCategories;
import it.progettoeventi.eventi.repository.EventsCategoriesRepository;

@Service
public class EventsCategoriesService {

    @Autowired
    private EventsCategoriesRepository eventsCategoriesRepository;

    public List<EventsCategories> findAll(){
        return eventsCategoriesRepository.findAll();
    }

    public List<EventsCategories> findByKeyword (String keyword){
        if(keyword == null || keyword.isBlank()){
            return eventsCategoriesRepository.findAll();
        }
        return eventsCategoriesRepository.findByNameContainingIgnoreCase(keyword);
    
    }
    
    public Optional<EventsCategories> findByName(String name){
            return eventsCategoriesRepository.findByName(name);
    }


    public EventsCategories findById(Integer id){
        return eventsCategoriesRepository.findById(id)
        .orElseThrow(() -> 
        new IllegalArgumentException("Elemento non trovato"));
    }

    public EventsCategories createEventCategory(EventsCategories eventsCategories){
        Optional<EventsCategories> optExistingCategoryEvent = eventsCategoriesRepository.findByName(eventsCategories.getName());
        if(optExistingCategoryEvent.isPresent()){
            throw new IllegalArgumentException("Questa categoria è già esistente");
        }    
        return eventsCategoriesRepository.save(eventsCategories);
    }

    public void deleteEventCategory(Integer id) {
    if(!eventsCategoriesRepository.existsById(id)){
        throw new IllegalArgumentException("Elemento non trovato");
    }
    eventsCategoriesRepository.deleteById(id);
    }

}
