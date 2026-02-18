package it.progettoeventi.eventi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.progettoeventi.eventi.model.Clients;
import it.progettoeventi.eventi.model.Events;
import it.progettoeventi.eventi.service.EventsService;
import it.progettoeventi.eventi.service.TicketsService;
import jakarta.validation.Valid;



@Controller
@RequestMapping("biglietti")
public class TicketsController {

    @Autowired
    private EventsService eventsService;


    @Autowired
    private TicketsService ticketsService;

   @GetMapping("acquista/{eventId}")
   public String showPurchaseForm(
        @PathVariable Integer eventId,
        Model model) {
        Events event = eventsService.findById(eventId);
        model.addAttribute("events", event);
        model.addAttribute("clientForm", new Clients());      
        return "biglietti/acquista";
   }
   
   @PostMapping("/acquista/{eventId}")
   public String processPurchase(
        @PathVariable Integer eventId,
        @RequestParam ("quantityOfTickets")Integer quantityOfTickets,
        @Valid @ModelAttribute ("clientForm") Clients clientForm,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes){
            if (bindingResult.hasErrors()){
                Events event = eventsService.findById(eventId);
                model.addAttribute("events", event);

                return "biglietti/acquista";
            }
            try {
                ticketsService.purchaseTicket(clientForm, eventId, quantityOfTickets);
                redirectAttributes.addFlashAttribute("successMessage", "Biglietto acquistato con successo");
                return "redirect:/eventi/";
            
            } catch (IllegalStateException | IllegalArgumentException e) {
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("events", eventsService.findById(eventId));
                return "biglietti/acquista";
            
            }
        
        }

        @GetMapping("/")
        public String indexTickets(Model model){
            List <Events> allEvents = eventsService.findAll();
            model.addAttribute("eventsList", allEvents);
            return "biglietti/index";
        }
   
}
