package it.progettoeventi.eventi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.progettoeventi.eventi.model.Events;
import it.progettoeventi.eventi.repository.EventsRepository;
@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;
    
    public Events createEvent(Events event){
        
        event.setSoldTickets(0);
        Optional<Events> existingEvent = eventsRepository.findByNameAndCityAndDate(
        
        event.getName(),
        event.getCity(),
        event.getDate()
        
        );

        if (existingEvent.isPresent()){
            throw new IllegalArgumentException("Lo stesso evento non può svolgersi più volte nello stesso giorno nella stessa città");
        }
        
        return eventsRepository.save(event);
    }

    public List<Events> findByKeyword(String keyword){
        if(keyword == null || keyword.isBlank()){
            return eventsRepository.findAll();
        
        }
            return eventsRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCityContainingIgnoreCase(keyword, keyword, keyword);
    }


    public Events findById(Integer id){
        return eventsRepository.findById(id)
        .orElseThrow(() -> 
        new IllegalArgumentException("Elemento non trovato"));    
    }


    public List<Events> findAll(){
        return eventsRepository.findAll();
    }
        
    public int showAvailableTickets(Events event){
        return event.getTotalTickets() - event.getSoldTickets();
    }
    

   public Events editEvents(Integer id, Events formEvent) {
    
    Events existingEvent = eventsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Evento non trovato "));
    Optional<Events> duplicate = eventsRepository.findByNameAndCityAndDate(
        formEvent.getName(), 
        formEvent.getCity(), 
        formEvent.getDate()
    );

    if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
        throw new IllegalArgumentException("Evento già esistente in quella data in quella città");
    }

    existingEvent.setName(formEvent.getName());
    existingEvent.setCity(formEvent.getCity());
    existingEvent.setDescription(formEvent.getDescription());
    existingEvent.setPicture(formEvent.getPicture());
    existingEvent.setDate(formEvent.getDate());
    existingEvent.setTotalTickets(formEvent.getTotalTickets());

    return eventsRepository.save(existingEvent);
}

    public void delete(Integer id) {
    if(!eventsRepository.existsById(id)){
        throw new IllegalArgumentException("Elemento non trovato");
    }
    eventsRepository.deleteById(id);
    }

}
