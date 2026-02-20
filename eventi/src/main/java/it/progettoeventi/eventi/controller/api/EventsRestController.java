package it.progettoeventi.eventi.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.progettoeventi.eventi.model.Events;
import it.progettoeventi.eventi.service.EventsService;

@RestController
@CrossOrigin
@RequestMapping("/api/events")
public class EventsRestController {

    @Autowired
    private EventsService eventsService;

    @GetMapping
    public List<Events> showEventsOutside(@RequestParam(name ="keyword", required =false) String keyword){
            return eventsService.findByKeyword(keyword);
       
    }

}
