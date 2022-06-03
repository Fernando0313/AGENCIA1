package idat.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "empresa")
@Table
@Data
@NoArgsConstructor
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String razonSocial;
	private String estado;
	
	@OneToMany(mappedBy = "empresa")
	private List<Vuelos> vuelos = new ArrayList<>();
	@OneToMany(mappedBy = "empresa")
	private List<Usuario> usuario = new ArrayList<>();
	

}
