package idat.com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "vuelos")
@Table
@Data
@NoArgsConstructor
public class Vuelos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "id_origen",
			nullable = false, 
			unique = false,
			foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_origen) references origen(id)"))
	private Origen origen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "id_destino",
			nullable = false, 
			unique = false,
			foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_destino) references destino(id)"))
	private Destino destino;
	
	private Date fechaSalida;
	private Date fechaRegreso;
	private Integer cantAsientos;
	private Integer cantAsientosDisponibles;
	private String estado;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "vuelo_tpago", joinColumns = @JoinColumn(name = "vuelo_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tpago_id", referencedColumnName = "id"))
	private Set<TipoPago> tpago = new HashSet<TipoPago>();
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "vuelo_ancillaries", joinColumns = @JoinColumn(name = "vuelo_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ancillaries_id", referencedColumnName = "id"))
	private Set<Ancillaries> ancillaries = new HashSet<Ancillaries>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "id_empresa",
			nullable = false, 
			unique = false,
			foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_empresa) references empresa(id)"))
	private Empresa empresa;
	
	@OneToMany(mappedBy = "vuelo")
	private List<VentaTotal> ventaTotal = new ArrayList<VentaTotal>();
}
