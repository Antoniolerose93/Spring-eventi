package it.progettoeventi.eventi.model;


import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Inserisci il nome dell'evento")
    private String name;


    @NotBlank(message= "Inserisci una descrizione")
    @Size(min =2, max = 200)
    private String description;

    @Future(message = "La data non può essere nel passato")
    private LocalDate date;

    @NotNull(message ="Inserire il numero massimo di biglietti acquistabili da un singolo cliente")
    private Integer maxTicketsForClient;

    private String picture;

    @NotBlank(message ="Inserisci il luogo dell'evento")
    private String location;

    @NotBlank(message="Inserisci correttamente la città")
    private String city;

    @Min(value =1)
    private int totalTickets;

    @NotNull
    @Min(value = 0, message="Il prezzo non può essere negativo")
    private BigDecimal price;

    private int soldTickets = 0;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(int soldTickets) {
        this.soldTickets = soldTickets;
    }

    @ManyToOne
    @JoinColumn(name ="id_event_category", nullable = false)
    @NotNull(message="La categoria dell'evento è obbligatoria")
    private EventsCategories eventCategory;

 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }


    public EventsCategories getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventsCategories eventCategory) {
        this.eventCategory = eventCategory;
    }

    public Integer getMaxTicketsForClient() {
        return maxTicketsForClient;
    }

    public void setMaxTicketsForClient(int maxTicketsForClient) {
        this.maxTicketsForClient = maxTicketsForClient;
    }


}
