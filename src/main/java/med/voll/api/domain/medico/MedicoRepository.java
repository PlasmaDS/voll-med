package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
            SELECT m FROM Medico m WHERE m.activo = 1 AND m.especialidad=:especialidad AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c c.data=:fecha
            ) ORDER BY rand() LIMIT 1
            """)
    Medico seleccionarMedicoConEspecialdadEnFecha(Especialidad especialidad, LocalDateTime fecha);

}
