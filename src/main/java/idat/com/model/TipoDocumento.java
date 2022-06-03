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

@Entity
@Table(name = "tipo_documento")
@Data
@NoArgsConstructor
public class TipoDocumento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String tipo;
private String estado;

@OneToMany(mappedBy = "tipoDocumento")
private List<Usuario> usuario = new ArrayList<>();
@OneToMany(mappedBy = "tipoDocumento")
private List<DetalleVenta> detalleVenta = new ArrayList<>();
}
