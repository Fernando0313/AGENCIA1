package idat.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.com.model.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
	
	public boolean existsById(Integer id);
	public Boolean existsByNombre(String nombre);
	
}
