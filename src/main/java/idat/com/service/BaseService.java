package idat.com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface BaseService<E> {
	public List<E> obtenerTodo();
	public Optional<E> obtenerPorId(Integer id);
	public Optional<E> obtenerPorName(String name);
	public E guardar (E entity);
	public E actualizar (E entity);
	public HashMap<String, String> eliminarPorId(Integer id);
	
}