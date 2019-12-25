package in.rs.mdprogramming.todos.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * * JwtAuthTokenFilter extends OncePerRequestFilter -> Executes once per request.
 *  * This is a filter base class that is used to guarantee a single execution per request dispatch.
 *  * It provides a doFilterInternal method with HttpServletRequest and HttpServletResponse arguments.
 *  * Inside JwtAuthTokenFilter class, the doFilterInternal method will:
 *  * 	- get JWT token from header
 *  * 	- validate JWT
 *  * 	- parse username from validated JWT
 *  * 	- load data from users table, then build an authentication object
 *  * 	- set the authentication object to Security Context
 */
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private static final Logger logger =
            LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Autowired
    private JwtTokenValidator tokenValidator;

    @Autowired
    private UsernameFromTokenProvider usernameFromTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {

            String jwt = getJwt(request);
            if (jwt != null && tokenValidator.validateJwtToken(jwt)) {

                String username = usernameFromTokenProvider.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
        }

        filterChain.doFilter(request, response);

    }

    /**
     *
     * @param request
     * @return
     */
    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }
}
