package idat.com.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol")
@Data
@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true) // important
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String rol;
	private String Estado;
	/*@ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Set<Usuario> usuarios = new HashSet<Usuario>(); */
	
	
	
}
