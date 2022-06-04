package idat.com.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioLogin {

	@NotNull(message = "Este cmpo no puede ser nulo, documentoOrEmailDTO")
	@NotBlank(message = "Llene este campo con un documento o email documentoOrEmailDTO")
	private String documentoOrEmailDTO;
	@NotNull(message = "Este cmpo no puede ser nulo, contrasenaDTO")
	@NotBlank(message = "Llene este campo con un documento o email contrasenaDTO")
	private String contrasenaDTO;
}
