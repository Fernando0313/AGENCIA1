package idat.com.dto.response;


import java.util.HashSet;
import java.util.Set;

import idat.com.model.Rol;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioLogin {

	private Integer idDTO;
	private String nombreDTO;
	private String apPaternoDTO;
	private String apMaternoDTO;
	private String documentoDTO;
	private String emailDTO;
	private String contrasenaDTO;
	private Set<Rol> rolesDTO = new HashSet<Rol>();
	
}
