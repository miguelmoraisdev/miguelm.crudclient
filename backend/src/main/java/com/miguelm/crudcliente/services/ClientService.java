package com.miguelm.crudcliente.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miguelm.crudcliente.dto.ClientDTO;
import com.miguelm.crudcliente.entities.Client;
import com.miguelm.crudcliente.repositories.ClientRepository;
import com.miguelm.crudcliente.services.exceptions.DatabaseException;
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

	@Transactional
	public ClientDTO insert(ClientDTO objDTO) {
		Client client = new Client();
		client.setName(objDTO.getName());
		client.setCpf(objDTO.getCpf());
		client.setIncome(objDTO.getIncome());
		client.setBirthDate(objDTO.getBirthDate());
		client.setChildren(objDTO.getChildren());
		client = repository.save(client);
		objDTO = new ClientDTO(client);
		return objDTO;
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO objDTO) {
		Client client = new Client();
		try {
			client = repository.getOne(id);
			client.setName(objDTO.getName());
			client.setCpf(objDTO.getCpf());
			client.setIncome(objDTO.getIncome());
			client.setBirthDate(objDTO.getBirthDate());
			client.setChildren(objDTO.getChildren());
			client = repository.save(client);
			objDTO = new ClientDTO(client);
			return objDTO;
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
		
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation");
		}	
	}

}
