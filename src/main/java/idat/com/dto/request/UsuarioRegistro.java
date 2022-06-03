package idat.com.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioRegistro {

	@NotBlank(message = "El campo nombreDTO no puede estar en blanco")
	@NotNull(message = "El campo nombreDTO no puede ser nulo")
	private String nombreDTO;
	
	@NotBlank(message = "El campo apPaternoDTO no puede estar en blanco")
	@NotNull(message = "El campo apPaternoDTO no puede ser nulo")
	private String apPaternoDTO;
	
	@NotBlank(message = "El campo apMaternoDTO no puede estar en blanco")
	@NotNull(message = "El campo apMaternoDTO no puede ser nulo")
	private String apMaternoDTO;
	
	@NotBlank(message = "El campo documentoDTO no puede estar en blanco")
	@NotNull(message = "El campo documentoDTO no puede ser nulo")
	@Size(min = 6,max = 12,message = "Solo se acepta DNI o PASAPORTE")
	private String documentoDTO;
	
	
	@Size(min = 7,max = 9,message = "Ingreso mal su numero telefonico. Len 7 o 9")
	private String telefonoDTO;
	
	@NotBlank(message = "El campo emailDTO no puede estar en blanco")
	@NotNull(message = "El campo emailDTO no puede ser nulo")
	@Email
	private String emailDTO;
	
	@NotBlank(message = "El campo estadolDTO no puede estar en blanco")
	@NotNull(message = "El campo estadoDTO no puede ser nulo")
	private String estadoDTO;
	
	@NotBlank(message = "El campo contrasenaDTO no puede estar en blanco")
	@NotNull(message = "El campo contrasenaDTO no puede ser nulo")
	private String contrasenaDTO;
	
	@NotNull(message = "El campo idRolDTO no puede ser nulo")
	private Integer idRolDTO;
	
	@NotNull(message = "El campo idEmpresaDTO no puede ser nulo")
	private Integer idEmpresaDTO;
	@NotNull(message = "El campo idTipoDocumento no puede ser nulo")
	private Integer idTipoDocumento;
	
}
