package med.voll.api.domain.medico;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MedicoRepositoryTest {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Debería retornar null cuando el medico ya posea una consulta en ese día y horario.")
    void testSeleccionarMedicoConEspecialdadEnFechaDenegacion() {

        // Given
        var fecha = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("Jeremías", "jere@hotmail.com", "34123456", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Roberto", "roberto@hotmail.com", "34654321");
        registrarConsulta(medico, paciente, fecha);

        // When
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialdadEnFecha(Especialidad.CARDIOLOGIA, fecha);

        // Then
        Assertions.assertNull(medicoLibre);

    }

    @Test
    @DisplayName("Debería retornar el médico seleccionado para la consulta.")
    void testSeleccionarMedicoConEspecialdadEnFechaAceptacion() {

        // Given
        var fecha = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("Jeremías", "jere@hotmail.com", "34123456", Especialidad.CARDIOLOGIA);

        // When
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialdadEnFecha(Especialidad.CARDIOLOGIA, fecha);

        // Then
        Assertions.assertEquals(medicoLibre, medico);

    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registrarMedico(String nombre, String email, String dni, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, dni, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String dni) {
        var paciente = new Paciente(datosPaciente(nombre, email, dni));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String dni, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                dni,
                "61999999999",
                especialidad,
                datosDireccion());

    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String dni) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                "61999999999",
                dni,
                datosDireccion());

    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion("local", "azul", "acapulco", "321", "12");
    }
}
