# 🎟️ Ticket Management App

Web application sviluppata con Spring Boot per la gestione e vendita di biglietti per eventi e spettacoli.

L'applicazione consente agli amministratori di gestire gli eventi e agli utenti registrati di acquistare biglietti con aggiornamento dinamico della disponibilità.

---

## 🚀 Tecnologie Utilizzate

- Java  
- Spring Boot  
- Spring Security  
- Hibernate / JPA  
- Thymeleaf  
- Bootstrap  
- MySQL    
---

## 👥 Gestione Ruoli

L'applicazione implementa un sistema di autenticazione e autorizzazione tramite Spring Security, con due ruoli principali:

### ⚙️ Admin
- Creazione eventi
- Modifica eventi
- Eliminazione eventi
- Visualizzazione storico acquisti degli utenti

### 👤 User
- Visualizzazione eventi
- Dettaglio evento
- Acquisto biglietti

---

## ✨ Funzionalità Principali

- Autenticazione e autorizzazione basata su ruoli
- CRUD completo degli eventi (Admin)
- Storico acquisti per utente
- Limite massimo di biglietti acquistabili per evento
- Aggiornamento in tempo reale dei biglietti disponibili
- Interfaccia responsive con Bootstrap
