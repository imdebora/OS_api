package com.debora.os20.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debora.os20.domain.Pessoa;
import com.debora.os20.domain.Tecnico;
import com.debora.os20.dtos.TecnicoDTO;
import com.debora.os20.repositories.PessoaRepository;
import com.debora.os20.repositories.TecnicoRepository;
import com.debora.os20.services.exceptions.DataIntegratyViolationException;
import com.debora.os20.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	/*
	 * Busca o Tecnico pelo ID
	 */
	public Tecnico findById(Integer id) {

		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));

	}

	/*
	 * Busca todos os Tecnicos da base de dados
	 */
	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
	/*
	 * Cria um Tecnico
	 */
	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	/*
	 * Atualiza um Tecnico
	 */
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {

			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}

	/*
	 * Deleta um Tecnico pelo ID
	 */
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui Ordens de Serviços, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	/*
	 * Busca um Tecnico pelo CPF
	 */
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;
	}

}
