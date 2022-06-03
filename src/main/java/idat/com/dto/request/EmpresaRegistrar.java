package idat.com.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpresaRegistrar {

	@NotNull(message="El nombre de la empresa no puede ser nulo")
	@NotBlank(message="El nombre de la empresa no puede estar en blanco")
	private String nombreDTO;
	@NotNull(message="La razon social de la empresa no puede ser nulo")
	@NotBlank(message="La razon social de la empresa no puede estar en blanco")
	private String razonSocialDTO;
	@NotNull(message="El estado de la empresa no puede ser nulo")
	@NotBlank(message="El estado de la empresa no puede estar en blanco")
	private String estadoDTO;
}

	