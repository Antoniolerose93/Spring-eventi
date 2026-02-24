package it.progettoeventi.eventi.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.progettoeventi.eventi.model.User;
import it.progettoeventi.eventi.repository.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username); //cerco l'utente nel db con l'username
        if(userOpt.isPresent()){
            return new DatabaseUserDetails(userOpt.get()); //se esiste prendo l'oggetto user dentro l'optional e lo trasformo nel formato
            //che si aspetta spring security (UserDetails)

        } else{
            throw new UsernameNotFoundException("Username non trovato");
        }
    }


}
