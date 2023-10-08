package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DatosCancelarConsulta(Long id, @NotNull MotivoCancelacion motivoCancelacion) {

    public Long idConsulta() {
        return id;
    }

    public MotivoCancelacion motivo() {
        return motivoCancelacion;
    }
}
