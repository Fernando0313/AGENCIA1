package idat.com.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaisDTORequest {
	
	private Integer idPaisDTO;
	
	@NotBlank(message = "El campo nombreDTO no puede estar en blanco")
	@NotNull(message = "El campo nombreDTO no puede ser nulo")
	private String nombreDTO;
	
	/*@NotBlank(message = "El campo id_paisDTO es requerido")
	@Positive(message = "Este campo solo acepta numeros positivos")
	private Integer id_paisDTO;*/
}
