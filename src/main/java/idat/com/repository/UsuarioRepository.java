package idat.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.com.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByDocumentoOrEmail(String documento, String email);
	
	public Boolean existsByDocumento(String documento);
	
	public Boolean existsByEmail(String email);
	
	public Usuario findByDocumento(String documento);
	
	public Usuario findByEmail(String email);
}
