package it.progettoeventi.eventi.service;

import java.time.LocalDate;
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

    public List<Events> findByFilters(String keyword, String city, LocalDate date) {
    
        if (keyword != null && !keyword.isBlank()) {
            return eventsRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCityContainingIgnoreCase(keyword, keyword, keyword);
        }

        if (city != null && !city.isBlank() && date != null) {
            return eventsRepository.findByCityContainingIgnoreCaseAndDate(city, date);
        }

        if (city != null && !city.isBlank()) {
            return eventsRepository.findByCityContainingIgnoreCase(city);
        }
   
        if (date != null) {
            return eventsRepository.findByDate(date);
        }

            return eventsRepository.findAll();
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
    

    public Events editEvents (Integer id, Events formEvent){
        Events existingEvent = eventsRepository.findById(formEvent.getId())
            .orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
        
        existingEvent.setDescription(formEvent.getDescription());
        existingEvent.setPicture(formEvent.getPicture());
        existingEvent.setDate(formEvent.getDate());
        return eventsRepository.save(existingEvent);
    }

    public void saveEvent(Events event) {
    eventsRepository.save(event);
}

    public void delete(Integer id) {
    if(!eventsRepository.existsById(id)){
        throw new IllegalArgumentException("Elemento non trovato");
    }
    eventsRepository.deleteById(id);
    }

}
