package it.progettoeventi.eventi.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tickets")
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_event", nullable = false)
    private Events event;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable =false)
    private Clients client;
    
    public Tickets(){

    }

    public Tickets(Events event, Clients client) {
    this.event = event;
    this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public Clients getClient(){
        return client;
    }
    
    public void setClient(Clients client){
        this.client = client;

    }

    

}