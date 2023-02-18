package co.microservicios.documents.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.microservicios.documents.business.ControllerBusiness;
import co.microservicios.documents.dto.GeneratePDFRequestDTO;
import co.microservicios.documents.model.DocumentEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Accion exitosa"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Api(value = "app-tra-documents-service-api ApiController", description = "Test",  tags = {"",""})
public class ApiController {

    @Autowired
    ControllerBusiness controllerBusiness;

	@Value("${spring.application.version}")
	private String version;

	@GetMapping("version")
    public ResponseEntity<String> version() {
        return new ResponseEntity(version, HttpStatus.OK);
    }



	@ApiOperation(value = "Genera un documento pdf a partir de una plantilla en .docx (base64)")
	@PostMapping("generate/pdf")
    public String getProducto(@RequestBody GeneratePDFRequestDTO request) {
       return controllerBusiness.generateDoc(request);
    }

	@ApiOperation(value = "Obtener lista de plantillas",notes = "Estos cambios se almacenan en una base de datos H2 (Memoria)")
	@GetMapping("template")
    public ResponseEntity<DocumentEntity> getTemplates() {
        return new ResponseEntity(controllerBusiness.getDataDocument(), HttpStatus.OK);
    }

	@ApiOperation(value = "Guardar una plantilla",notes = "Estos cambios se almacenan en una base de datos H2 (Memoria)")
	@PostMapping("template")
    public void saveDocument(@RequestBody DocumentEntity data) {
        controllerBusiness.saveDocument(data);
    }

}
