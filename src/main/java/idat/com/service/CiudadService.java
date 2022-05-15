package idat.com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.model.Ciudad;
import idat.com.repository.CiudadRepository;

@Service
public class CiudadService implements BaseService<Ciudad>{

	@Autowired
	CiudadRepository repo;
	
	@Override
	public List<Ciudad> obtenerTodo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ciudad> obtenerPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ciudad> obtenerPorName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ciudad guardar(Ciudad entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public Ciudad actualizar(Ciudad entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> eliminarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
