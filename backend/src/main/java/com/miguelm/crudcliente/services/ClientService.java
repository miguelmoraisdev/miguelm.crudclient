package com.miguelm.crudcliente.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miguelm.crudcliente.dto.ClientDTO;
import com.miguelm.crudcliente.entities.Client;
import com.miguelm.crudcliente.repositories.ClientRepository;
import com.miguelm.crudcliente.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> clientList = repository.findAll();
		List<ClientDTO> clientListDTO = clientList.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
		return clientListDTO;
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		ClientDTO dto = new ClientDTO(entity);
		return dto;
	}

}
