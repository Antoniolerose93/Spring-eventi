package it.progettoeventi.eventi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.progettoeventi.eventi.model.Clients;
import it.progettoeventi.eventi.model.Events;
import it.progettoeventi.eventi.model.Tickets;
import it.progettoeventi.eventi.repository.TicketsRepository;


@Service
public class TicketsService {

    @Autowired
    private TicketsRepository ticketsRepository;

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private EventsService eventsService;


    //creo un metodo per la vendita dei biglietti.
    //GLi passo il form del cliente, l'eventid e la quantità di biglietti
    @Transactional
    public void purchaseTicket(Clients clientForm, Integer eventId, Integer quantityOfTickets){
        Events event = eventsService.findById(eventId);
        
        limitOfTickets(clientForm.getMail(), event, quantityOfTickets);
        
        if (event.getSoldTickets() + quantityOfTickets > event.getTotalTickets()){
            throw new IllegalStateException("Posti insufficienti. Sono ancora disponibili: " + (event.getTotalTickets() - event.getSoldTickets()) + ""+ "posti");
        }
    //Verifico se esiste il cliente, recupero il metodo getOrCreateClient
        Clients savedClient = clientsService.getOrCreateClient(clientForm);

    for (int i = 0; i < quantityOfTickets; i++){
        finalizePurchase(event, savedClient);
    }        
      clientsService.saveClient(savedClient); // Salva il nuovo numero di biglietti del cliente
      eventsService.saveEvent(event); // Salva il nuovo numero di biglietti venduti dell'evento
      ù
    }

    public Tickets finalizePurchase (Events event, Clients client){
        Tickets ticket = new Tickets(event, client);
        event.setSoldTickets(event.getSoldTickets()+1);
        client.setNPurchasedTickets(client.getNPurchasedTickets() + 1);
        return ticketsRepository.save(ticket);
    }

    private void limitOfTickets(String mail, Events event, Integer quantityOfTickets){
    Integer alreadyPurchased = ticketsRepository.countByClientMailAndEventId(mail, event.getId());
        if(alreadyPurchased + quantityOfTickets > event.getMaxTicketsForClient()){
            throw new IllegalArgumentException("Limite biglietti acquistabili raggiunto");
        }

    }
       
    // Trova tutti i biglietti
    public List<Tickets> findAll() {
        return ticketsRepository.findAll();
    }

    // Trova biglietto per ID
    public Tickets findById(Integer id) {
        return ticketsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Biglietto non trovato"));
    }

    // Trova biglietto per cliente e evento
    public Tickets findByClientAndEvent(Clients client, Events event) {
        return ticketsRepository.findByClientAndEvent(client, event)
            .orElseThrow(() -> new IllegalArgumentException
            ("Questo cliente non ha un biglietto per questo evento"));
    }

    

    // Elimina biglietto
    public void delete(Integer id) {
        if (!ticketsRepository.existsById(id)) {
            throw new IllegalArgumentException("Biglietto non trovato");
        }
        ticketsRepository.deleteById(id);
    }

}
