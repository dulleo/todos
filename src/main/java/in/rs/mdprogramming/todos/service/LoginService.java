package in.rs.mdprogramming.todos.service;

import in.rs.mdprogramming.todos.dto.UserLoginDTO;
import in.rs.mdprogramming.todos.response.JwtResponse;
import in.rs.mdprogramming.todos.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 *
 * @author duskol Sep 20, 2019
 *
 */
@Service
public class LoginService implements LoginServiceInterface {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public JwtResponse login(@Valid UserLoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return jwtResponse;
    }
}
