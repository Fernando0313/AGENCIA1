package idat.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "pais")
@Table
@Data
@NoArgsConstructor
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nombre;
	
	@OneToMany(mappedBy = "pais")
	private List<Ciudad> ciudad =new ArrayList<Ciudad>();
	
	
	@OneToMany(mappedBy = "pais")
	private List<Origen> origen =new ArrayList<Origen>();
	
	@OneToMany(mappedBy = "pais")
	private List<Destino> destino =new ArrayList<Destino>();
	
	
}
