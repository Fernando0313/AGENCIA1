package idat.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ciudad")
@Table
@Data
@NoArgsConstructor
public class Ciudad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_ciudad;
	
	@Column
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "id_pais",
			nullable = false, 
			unique = false,
			foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_pais) references pais(id_pais)"))
	private Pais pais;

	
	
	
}
