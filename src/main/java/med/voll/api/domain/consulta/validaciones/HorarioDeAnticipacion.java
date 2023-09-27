package med.voll.api.domain.consulta.validaciones;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import java.time.Duration;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas {
    public void validar(DatosAgendarConsulta datos) {

        var horaActual = LocalDateTime.now();
        var horarioConsulta = datos.fecha();
        var diferenciaDe30Min = Duration.between(horaActual, horarioConsulta).toMinutes() < 30;

        if (diferenciaDe30Min) {
            throw new ValidationException("La consulta debe reservarse con al menos 30 minutos de anticipación");
        }
    }
}
