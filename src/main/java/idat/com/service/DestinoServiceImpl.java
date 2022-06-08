package idat.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.com.repository.DestinoRepository;

@Service
public class DestinoServiceImpl {

	@Autowired
	private DestinoRepository repo;
}
