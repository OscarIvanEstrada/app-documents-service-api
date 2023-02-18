package co.microservicios.documents.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ProductoRequestDTO Description")
public class ProductoRequestDTO {

    @ApiModelProperty(value = "Data Exmaple for ProductoRequestDTO", required = true, example = "nombre")
    private String nombre;
	
    @ApiModelProperty(value = "Data Exmaple for ProductoRequestDTO", required = true, example = "descripcion")
    private String descripcion;
	
}
