package co.microservicios.documents.exceptions;

import co.microservicios.documents.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException{

	 private ErrorEnum error;
     private String message;


}

