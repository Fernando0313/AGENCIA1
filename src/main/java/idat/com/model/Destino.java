package idat.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "destino")
@Table
@Data
@NoArgsConstructor
public class Destino {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	
	@OneToMany(mappedBy = "destino")
	private List<Vuelos> vuelo = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "id_pais",
			nullable = false, 
			unique = false,
			foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_pais) references pais(id)"))
	private Pais pais;

}
