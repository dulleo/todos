package in.rs.mdprogramming.todos.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * This object will be returned by server once an authentication is successful
 * It contains
 * 	- jwt token
 * 	- Schema type token
 * 	- username
 * 	- Array of User's authorities
 *
 * @author duskol Dec 24, 2019
 *
 */
public class JwtResponse {

    private String token;
    private String tokenType = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.username = username;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getUsername() {
        return username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
