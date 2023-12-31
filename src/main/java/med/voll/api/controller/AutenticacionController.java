package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import med.voll.api.domain.usuarios.DatosAutenticacionUsuario;
import med.voll.api.domain.usuarios.Usuario;
import med.voll.api.infra.security.DatosJWTToken;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Tag(name = "Autenticación", description = "Obtención de token que permite la utilización del resto de la API.")
    @Operation(summary = "Iniciar sesión")
    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(
            @RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.user(),
                datosAutenticacionUsuario.password()); // Genera el token con el usuario y la contraseña enviadas (POST)
        var usuarioAutenticado = authenticationManager.authenticate(authToken); // Autenticación
                                                                                // authenticationManager.authenticate(authToken)
        var JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }

}
