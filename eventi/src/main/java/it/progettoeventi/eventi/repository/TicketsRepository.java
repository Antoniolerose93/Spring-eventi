package it.progettoeventi.eventi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.progettoeventi.eventi.model.Clients;
import it.progettoeventi.eventi.model.Events;
import it.progettoeventi.eventi.model.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, Integer> {

Optional<Tickets> findByClientAndEvent (Clients client, Events event);

List<Tickets> findByClient(Clients client);

List<Tickets> findByEvent (Events event);

Integer countByClientMailAndEventId(String mail, Integer eventId);


}
