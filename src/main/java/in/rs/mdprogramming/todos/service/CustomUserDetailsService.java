package in.rs.mdprogramming.todos.service;

import in.rs.mdprogramming.todos.model.User;
import in.rs.mdprogramming.todos.repository.UserRepository;
import in.rs.mdprogramming.todos.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * loadUserByUsername method finds a record from users database tables to build a UserDetails object for authentication.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow( () ->  new UsernameNotFoundException(String.format("Korisnik sa korisničkim imenom %s nije pronadjen", username)));

        return UserPrinciple.build(user);
    }

}
