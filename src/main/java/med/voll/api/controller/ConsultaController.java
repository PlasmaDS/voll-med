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

    private MotivoCancelacion motivo;

    @Operation(summary = "Registrar una nueva consulta")
    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalladoConsulta> agendar(@RequestBody @Valid DatosAgendarConsulta datos) {
        System.out.println(datos);

        var response = service.agendar(datos);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cancelar una consulta")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarConsulta(@PathVariable Long id) {
        Consulta consulta = consultaRepository.getReferenceById(id);
        consulta.cancelar(motivo);
        return ResponseEntity.noContent().build();
    }

}
