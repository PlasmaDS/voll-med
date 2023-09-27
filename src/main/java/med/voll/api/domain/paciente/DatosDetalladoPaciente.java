package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.Direccion;

public record DatosDetalladoPaciente(String nombre, String email, String telefono, String dni,
        Direccion direccion) {
    public DatosDetalladoPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getTelefono(), paciente.getDni(),
                paciente.getDireccion());
    }
}