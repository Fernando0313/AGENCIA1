package idat.com.dto.response;

import java.util.ArrayList;
import java.util.List;

import idat.com.model.Ciudad;
import idat.com.model.Destino;
import idat.com.model.Origen;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaisDTOResponse {

	private Integer idPaisDTO;
	private String nombreDTO;
	
	private List<Ciudad> ciudadDTO = new ArrayList<>();
	private List<Origen> origenDTO = new ArrayList<>();
	private List<Destino> destinoDTO = new ArrayList<>();
	
	
}

