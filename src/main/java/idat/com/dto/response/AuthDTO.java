package idat.com.dto.response;

import java.util.HashSet;
import java.util.Set;

import idat.com.model.Rol;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthDTO {

	private String emailDTO;
	private String contrasenaDTO;
	private Set<Rol> rolSetDTO = new HashSet<Rol>();
}
