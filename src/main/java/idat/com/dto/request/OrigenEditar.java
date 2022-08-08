package idat.com.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class OrigenEditar {
	@NotNull(message = "El idOrigenDTO no puede ser nulo")
	private Integer idOrigenDTO;
	@NotNull(message = "El nombreDTO no puede ser nulo")
	@NotBlank(message = "El nombre DTO no puede estar en blanco")
	private String nombreDTO;
	@NotNull(message = "El idPaisDTO no puede ser nulo")
	private Integer idPaisDTO;
}
