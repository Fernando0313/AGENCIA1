package idat.com.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CiudadDTORequest {

	private Integer idNombreDTO;
	
	@NotBlank(message = "El campo nombreDTO no puede estar en blanco")
	@NotNull(message = "El campo nombreDTO no puede ser nulo")
	private String nombreDTO;
	
	@Positive(message = "Solo se aceptan numeros positivos enteros")
	@NotNull(message = "El campo id_paisDTO no puede ser nulo")
	private Integer id_paisDTO;
}
