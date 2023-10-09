package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultasService;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalladoConsulta;
import med.voll.api.domain.consulta.MotivoCancelacion;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Consulta", description = "Administración de consultas.")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultasService service;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Operation(summary = "Registrar una nueva consulta")
    @ApiResponse(responseCode = "201", description = "Consulta registrada con éxito")
    @PostMapping
    @Transactional
    public ResponseEntity<String> agendar(
            @RequestBody @Valid @Schema(example = "{\"idPaciente\": 0, \"idMedico\": 0, \"fecha\": \"dd/MM/yyyy HH:mm\"}") DatosAgendarConsulta datos) {
        System.out.println(datos);

        service.agendar(datos);

        return ResponseEntity.ok("Consulta registrada con éxito");
    }

    @Operation(summary = "Cancelar una consulta")
    @ApiResponse(responseCode = "204", description = "Consulta cancelada")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarConsulta(@PathVariable Long id,
            @RequestBody @Schema(example = "Opciones: PACIENTE_DESISTIO, MEDICO_CANCELO, OTROS") String motivo) {
        Consulta consulta = consultaRepository.getReferenceById(id);
        MotivoCancelacion motivoDelete = MotivoCancelacion.valueOf(motivo);
        consulta.cancelar(motivoDelete);
        return ResponseEntity.noContent().build();
    }

}
