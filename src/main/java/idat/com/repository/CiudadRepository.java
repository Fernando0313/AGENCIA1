package idat.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.com.model.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

}
