package co.microservicios.documents.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ProductoResponseDTO Description")
public class ProductoResponseDTO {

    @ApiModelProperty(value = "Data Exmaple for ProductoResponseDTO", required = true, example = "nombre")
    private String nombre;
    @ApiModelProperty(value = "Data Exmaple for ProductoResponseDTO", required = true, example = "descripcion")
    private String descripcion;
}
