package idat.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idat.com.model.Destino;

public interface DestinoRepository extends JpaRepository<Destino, Integer>{
public boolean existsById(Integer id);
public Boolean existsBynombre(String nombre);
public Destino findByNombre(String nombre);
}
