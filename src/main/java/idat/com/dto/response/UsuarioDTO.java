package idat.com.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {


	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private String documento;
	private String email;
	
}
