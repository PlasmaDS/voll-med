package med.voll.api.domain.consulta.validaciones;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelarConsulta;

@Component("ValidadorAnticipacionEnCancelacion")
public class ValidadorAnticipacionEnCancelacion implements ValidadorCancelacionDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelarConsulta datos) {
        var consulta = repository.getReferenceById(datos.idConsulta());
        var horaActual = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(horaActual, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new ValidationException("La consulta solo puede ser cancelada con un minimo de 24h de anticipación.");
        }

    }

}
