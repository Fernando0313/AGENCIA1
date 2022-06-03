package idat.com.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoDocumentoRegistro {
	@NotNull(message = "El nombre del documento no puede ser nulo")
	@NotBlank(message = "El nombre del documento no puede esta bacio")
	private String tipoDTO;
	@NotNull(message = "El estado del documento no puede ser nulo")
	@NotBlank(message = "El estado del documento no puede esta bacio")
	private String estadoDTO;
}
