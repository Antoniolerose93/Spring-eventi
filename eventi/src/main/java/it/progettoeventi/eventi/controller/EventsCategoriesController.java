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

import it.progettoeventi.eventi.model.EventsCategories;
import it.progettoeventi.eventi.service.EventsCategoriesService;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/categorie-eventi")
public class EventsCategoriesController {

    @Autowired
    private EventsCategoriesService eventsCategoriesService;

    @GetMapping("/")
    public String indexEventsCategories(
        @RequestParam (required = false) String keyword,Model model) {
        
        List<EventsCategories> list = eventsCategoriesService.findByKeyword(keyword);
        model.addAttribute("eventsCategoriesList", list);

        model.addAttribute("eventsCategoriesObj", new EventsCategories());
        model.addAttribute("keyword", keyword);
        
        return "categorieEventi/index";
    }
    
    
    @PostMapping("/create")
    public String saveEventsCategories(
        @Valid @ModelAttribute("eventsCategoriesObj") EventsCategories formEventsCategories, 
        BindingResult bindingResult, 
        Model model, 
        RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            model.addAttribute("eventsCategoriesList", eventsCategoriesService.findAll());
            return "categorieEventi/index";
        } 
        try {
            eventsCategoriesService.createEventCategory(formEventsCategories);
            redirectAttributes.addFlashAttribute("successMessage", "Categoria di eventi aggiunta con successo");
            return "redirect:/categorie-eventi/";

        } catch (IllegalArgumentException e1) {
            model.addAttribute("errorMessage", e1.getMessage());
            model.addAttribute("eventsCategoriesList", eventsCategoriesService.findAll()); 
            //getMessage deriva dal messaggio nell'eccezione di EventsCategoriesService, recupera la stringa passata nel throw
            return "categorieEventi/index"; 
        
        }

    }

    @PostMapping("/delete/{id}")
    public String deleteCategoryEvent(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            eventsCategoriesService.deleteEventCategory(id);
            redirectAttributes.addFlashAttribute("successMessage", "Categoria eliminata con successo");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
            
        return "redirect:/categorie-eventi/";

    }

}
