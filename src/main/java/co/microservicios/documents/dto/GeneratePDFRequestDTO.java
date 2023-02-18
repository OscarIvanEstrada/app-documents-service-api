package co.microservicios.documents.dto;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "GeneratePDF Description")
public class GeneratePDFRequestDTO {

    @ApiModelProperty(value = "Data Exmaple for  Template WORD in BASE64 or null", required = false, example = "Template WORD in BASE64 or null",allowEmptyValue = true)
    private String template;
    
    @ApiModelProperty(value = "Data Exmaple for data value", required = false, example = "{\r\n"
    		+ "    	   \"KEY_NOMBRE\":\"Oscar\",		\r\n"
    		+ "    	\"KEY_APELLIDO\": \"Estrada\"\r\n"
    		+ "    	  }")
    private Map<String, String> data;
	
}
