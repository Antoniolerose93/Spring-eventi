package it.progettoeventi.eventi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.progettoeventi.eventi.model.Clients;
import it.progettoeventi.eventi.model.Events;
import it.progettoeventi.eventi.model.Tickets;
import it.progettoeventi.eventi.service.ClientsService;


@Controller
@RequestMapping("/clienti")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @GetMapping("/")
    public String listClients(Model model){
        List <Clients> allClients = clientsService.findAll();
        model.addAttribute("clients", allClients);
        return "clienti/index";
    }

    @GetMapping("/storico/{id}")
    public String showClientTickets(@PathVariable("id") Integer id, Model model) {
    Clients client = clientsService.findById(id);
    
    Map<Events, Integer> group = new HashMap<>();

    if(client.getTickets() !=null){
    
    for (Tickets t : client.getTickets()) {
        Events ev = t.getEvent();
        
        group.put(ev, group.getOrDefault(ev, 0) + 1);
        }
    }

    model.addAttribute("client", client);
    model.addAttribute("mappaBiglietti", group); 
    
    return "clienti/storico";
}
    
}
