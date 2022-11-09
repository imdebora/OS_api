package com.debora.os20.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debora.os20.domain.Pessoa;
import com.debora.os20.domain.Cliente;
import com.debora.os20.dtos.ClienteDTO;
import com.debora.os20.repositories.PessoaRepository;
import com.debora.os20.repositories.ClienteRepository;
import com.debora.os20.services.exceptions.DataIntegratyViolationException;
import com.debora.os20.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	/*
	 * Busca o Cliente pelo ID
	 */
	public Cliente findById(Integer id) {

		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	/*
	 * Busca todos os Clientes da base de dados
	 */
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	/*
	 * Cria um Cliente
	 */
	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	/*
	 * Atualiza um Cliente
	 */
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {

			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}

	/*
	 * Deleta um Cliente pelo ID
	 */
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Pessoa possui Ordens de Serviços, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	/*
	 * Busca um Cliente pelo CPF
	 */
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;
	}

}
