package it.progettoeventi.eventi.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clients")
public class Clients {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank(message = "Il nome è obbligatorio")
    private String name;

    @NotNull
    @NotBlank(message = "Il cognome è obbligatorio")
    private String surname;

    @NotNull
    @NotBlank
    private String mail;
    
    private int nPurchasedTickets = 0;

    @OneToMany(mappedBy = "client") // "client" è il nome del campo nell'Entity Tickets
    private List<Tickets> tickets;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getNPurchasedTickets() {
        return nPurchasedTickets;
    }

    public void setNPurchasedTickets(int nPurchasedTickets) {
        this.nPurchasedTickets = nPurchasedTickets;
    }

    public void incrementPurchasedTickets() {
    this.nPurchasedTickets++;
    }

    public List<Tickets> getTickets() {
    return tickets;
    }

}
