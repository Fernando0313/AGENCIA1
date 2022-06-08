package idat.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.com.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer>{

	public Boolean existsByNombre(String nombre);
	public boolean existsById(Integer id);
	public Pais findByNombre(String nombre);
}
