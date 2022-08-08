package idat.com.dto.response;

import idat.com.model.Pais;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OrigenDTO {
	private Integer idOrigenDTO;
	private String nombreDTO;
	private Pais paisDTO;
	
	public OrigenDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdOrigenDTO() {
		return idOrigenDTO;
	}

	public void setIdOrigenDTO(Integer idOrigenDTO) {
		this.idOrigenDTO = idOrigenDTO;
	}

	public String getNombreDTO() {
		return nombreDTO;
	}

	public void setNombreDTO(String nombreDTO) {
		this.nombreDTO = nombreDTO;
	}

	public Pais getPaisDTO() {
		return paisDTO;
	}

	public void setPaisDTO(Pais paisDTO) {
		this.paisDTO = paisDTO;
	}
	
}
