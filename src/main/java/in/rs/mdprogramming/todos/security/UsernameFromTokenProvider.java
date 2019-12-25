package in.rs.mdprogramming.todos.security;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;

/**
 * This class provides username from token
 */
public class UsernameFromTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
