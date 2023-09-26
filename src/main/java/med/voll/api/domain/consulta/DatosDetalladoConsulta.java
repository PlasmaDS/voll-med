package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DatosDetalladoConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {

}
