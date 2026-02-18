package it.progettoeventi.eventi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.progettoeventi.eventi.model.Clients;
import it.progettoeventi.eventi.repository.ClientsRepository;

@Service
public class ClientsService {

    @Autowired 
    private ClientsRepository clientsRepository;

    public Clients getOrCreateClient(Clients clientForm) {
        return clientsRepository.findByMail(clientForm.getMail())
                .orElseGet(() -> clientsRepository.save(clientForm));
    }

    public Clients findById(Integer id){
        return clientsRepository.findById(id)
        .orElseThrow(() -> 
        new IllegalArgumentException("Elemento non trovato"));    
    }

    public List<Clients> findAll(){
        return clientsRepository.findAll();
    }

    public void updateTicketCounter(Clients client, int quantity) {
        // Usiamo il valore attuale e aggiungiamo la quantità acquistata ora
        client.setNPurchasedTickets(client.getNPurchasedTickets() + quantity);
        clientsRepository.save(client);
    }

    public void saveClient(Clients client) {
    clientsRepository.save(client);
}



}
