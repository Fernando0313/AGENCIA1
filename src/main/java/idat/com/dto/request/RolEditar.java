package idat.com.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RolEditar {
	
	@NotNull(message = "El id no puede ser nulo")
	private Integer idDTO;
	@NotNull(message = "El nomre del rol no puede ser nulo")
	@NotBlank(message = "El rol no puede estar en blanco")
	private String nombreDTO;
	@NotNull(message = "El estado del rol no puede ser nulo")
	@NotBlank(message = "El estado no puede estar en blanco")
	private String estadoDTO;
}
