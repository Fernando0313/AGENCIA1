package idat.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.com.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	

}
