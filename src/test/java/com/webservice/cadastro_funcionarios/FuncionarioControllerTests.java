package com.webservice.cadastro_funcionarios;

import com.webservice.cadastro_funcionarios.controllers.FuncionarioController;
import com.webservice.cadastro_funcionarios.interfaces.FuncionarioRepository;
import com.webservice.cadastro_funcionarios.models.Cargo;
import com.webservice.cadastro_funcionarios.models.Funcionario;
import com.webservice.cadastro_funcionarios.services.EnderecoService;
import com.webservice.cadastro_funcionarios.services.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioControllerTests {

	@Mock
	private FuncionarioService _funcionarioService;
	private FuncionarioRepository _funcionarioRepository;
	private EnderecoService _enderecoService;

	@InjectMocks
	private FuncionarioController _funcionarioController;

	@BeforeEach
	void setUp() {
		_funcionarioService = Mockito.mock(FuncionarioService.class);
		_funcionarioRepository = Mockito.mock(FuncionarioRepository.class);
		_enderecoService =  Mockito.mock(EnderecoService.class);

		_funcionarioController = new FuncionarioController(_funcionarioService, _funcionarioRepository, _enderecoService);
	}

	@Test
	void DeveRetornarFuncionariosQuandoExistirem() {

		// Arrange
		var funcionario = new Funcionario();
		funcionario.setId(UUID.randomUUID());
		funcionario.setNome("João");
		funcionario.setEmail("joao@gmail.com");
		funcionario.setDocumento("6G56SDFGH");
		funcionario.setDataCadastro(LocalDate.now());
		funcionario.setDataNascimento(LocalDate.EPOCH);
		funcionario.setStatus(true);
		funcionario.setSalario(1000f);
		funcionario.setCargo(Cargo.ANALISTA);

		when(_funcionarioRepository.findAll()).thenReturn(List.of(funcionario));

		// Act
		var funcionarios = _funcionarioController.ListarFuncionarios();

		// Assert
		assertEquals(HttpStatus.OK, funcionarios.getStatusCode());
		verify(_funcionarioRepository, times(1)).findAll();
	}
}
