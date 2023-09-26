package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuarioRepository;

@Component // Es el estereotipo mas genérico de Spring
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired // Siempre es mejor con un constructor, esto es por fines de rapidez
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        // Obtener el token del header
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);
            // System.out.println(token);
            // System.out.println(tokenService.getSubject(token)); // Este usuario tiene
            // sesión?
            if (subject != null) {
                var usuario = usuarioRepository.findByUser(subject);
                // Forzamos el inicio de sesión
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}