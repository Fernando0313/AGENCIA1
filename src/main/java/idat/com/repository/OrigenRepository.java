package idat.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.com.model.Destino;
import idat.com.model.Origen;

@Repository
public interface OrigenRepository extends JpaRepository<Origen, Integer> {
	public boolean existsById(Integer id);
	public Boolean existsBynombre(String nombre);
	public Destino findByNombre(String nombre);
}
