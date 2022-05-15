package idat.com.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaisDTORequest {

	@NotBlank(message = "El campo nombreDTO es requerido")
	@NotNull(message = "El campo nombreDTO no puede ser nulo")
	private String nombreDTO;
	
	@NotNull(message = "El campo nombreDTO22 no puede ser nulo")
	private String nombre222DTO;
	
	/*@NotBlank(message = "El campo id_paisDTO es requerido")
	@Positive(message = "Este campo solo acepta numeros positivos")
	private Integer id_paisDTO;*/
}
