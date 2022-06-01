package idat.com.dto.response;

import java.util.ArrayList;
import java.util.List;

import idat.com.model.Ciudad;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaisDTOResponse {

	private Integer idPaisDTO;
	private String nombreDTO;
	
	private List<Ciudad> ciudad = new ArrayList<>();
	
	
}

