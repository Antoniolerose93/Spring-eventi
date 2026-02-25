package it.progettoeventi.eventi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import it.progettoeventi.eventi.model.Events;
import it.progettoeventi.eventi.service.EventsCategoriesService;
import it.progettoeventi.eventi.service.EventsService;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/eventi")
public class EventsController {

    @Autowired 
    private EventsService eventsService;

    @Autowired 
    private EventsCategoriesService eventsCategoriesService;

    @GetMapping("/")
        public String indexEvents(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String city,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        Model model) {

        List<Events> events = eventsService.findByKeyword(keyword);

        model.addAttribute("events", events);
        model.addAttribute("keyword", keyword);
        model.addAttribute("city", city);
        model.addAttribute("date", date);

    return "eventi/index";
   
    }
    
    @GetMapping("/show/{id}")
    public String showEvents(@PathVariable("id")Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Events events = eventsService.findById(id);
            int available = eventsService.showAvailableTickets(events);
            model.addAttribute("events", events);
            model.addAttribute("available", available);
            return "eventi/show";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/eventi/";
        }
    }
    
    @GetMapping("/create")
    public String createEvents(Model model){
        model.addAttribute("events", new Events());
        model.addAttribute("allEventsCategories", eventsCategoriesService.findAll());
        return "eventi/create";
    }

    @PostMapping("/create")
    public String saveEvents
    (@Valid @ModelAttribute("events") Events formEvents, 
    BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            model.addAttribute("allEventsCategories", eventsCategoriesService.findAll());
            return "eventi/create";
        } 
        try{
            eventsService.createEvent(formEvents);
            redirectAttributes.addFlashAttribute("successMessage", "Evento aggiunto con successo");
            return "redirect:/eventi/";
        } catch(IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("allEventsCategories", eventsCategoriesService.findAll());
            return "eventi/create";
        }

    }
   
    @GetMapping("/edit/{id}")
        public String editEvents(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
            try{
                Events event = eventsService.findById(id);
                model.addAttribute("events", event);
                return "eventi/edit";
            } catch(IllegalArgumentException e){
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/eventi/";
            }
        }

    @PostMapping("/edit/{id}")
        public String updateEvents(
            @PathVariable Integer id, @Valid 
            @ModelAttribute("events") Events formEvents, 
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

            if (bindingResult.hasErrors()){
                model.addAttribute("events", formEvents);
                return "eventi/edit";
            }

            try {
                eventsService.editEvents(id, formEvents);
                redirectAttributes.addFlashAttribute("successMessage", "Evento modificato con successo");
                return "redirect:/eventi/";
            } catch (IllegalArgumentException e) {
                model.addAttribute("errorMessage", e.getMessage());
                return "eventi/edit";
            }
    }
    
    @PostMapping("/delete/{id}")
    public String deleteEvents(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            eventsService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Evento eliminato con successo");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
            
        return "redirect:/eventi/";

    }
    

}
