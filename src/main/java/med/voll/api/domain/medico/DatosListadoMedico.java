package med.voll.api.domain.medico;

public record DatosListadoMedico(Long id, String nombre, String email, String dni, String especialidad) {
    public DatosListadoMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getDni(),
                medico.getEspecialidad().toString());
    }
}
