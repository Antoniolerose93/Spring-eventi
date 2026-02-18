package it.progettoeventi.eventi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.progettoeventi.eventi.model.Clients;
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

    
}
