package com.debora.os20.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debora.os20.domain.Cliente;
import com.debora.os20.domain.OS;
import com.debora.os20.domain.Tecnico;
import com.debora.os20.domain.enuns.Prioridade;
import com.debora.os20.domain.enuns.Status;
import com.debora.os20.repositories.ClienteRepository;
import com.debora.os20.repositories.OSRepository;
import com.debora.os20.repositories.TecnicoRepository;

@Service
public class DBServices {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Valdir Cesar", "521.058.490-91", "(88) 98888-8888");
		Tecnico t2 = new Tecnico(null, "Linus Torvals", "132.765.950-60", "(88) 98888-6666");
		Cliente c1 = new Cliente(null, "Betina Campos", "561.293.280-44", "(77) 97777-7777");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
