package idat.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "pais")
@Table
@Data
@NoArgsConstructor
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_pais;
	
	@Column
	private String nombre;
	
	@OneToMany(mappedBy = "pais")
	private List<Ciudad> ciudad =new ArrayList<Ciudad>();
	
	
	
}
