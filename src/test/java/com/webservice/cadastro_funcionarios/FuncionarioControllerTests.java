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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
		var funcionario = CreateFuncionario();
		when(_funcionarioRepository.findAll()).thenReturn(List.of(funcionario));

		// Act
		var funcionarios = _funcionarioController.ListarFuncionarios();

		// Assert
		assertEquals(HttpStatus.OK, funcionarios.getStatusCode());
		assertEquals(1, funcionarios.getBody().size());
		verify(_funcionarioRepository, times(1)).findAll();
	}

	@Test
	void NaoDeveRetornarFuncionariosQuandoNaoExistir() {

		// Arrange
		when(_funcionarioRepository.findAll()).thenReturn(List.of());

		// Act
		var funcionarios = _funcionarioController.ListarFuncionarios();

		// Assert
		assertEquals(HttpStatus.OK, funcionarios.getStatusCode());
		assertEquals(0, funcionarios.getBody().size());
		verify(_funcionarioRepository, times(1)).findAll();
	}

	@Test
	void DeveRetornarExceptionQuandoListarFuncionarios() {

		// Arrange
		when(_funcionarioRepository.findAll()).thenThrow(new IllegalArgumentException());

		// Act
		assertThrows(RuntimeException.class, ()  -> _funcionarioController.ListarFuncionarios());

		// Assert
		verify(_funcionarioRepository, times(1)).findAll();
	}

	@Test
	void DeveObterFuncionarioPorIdComSucesso() {

		// Arrange
		var funcionario = CreateFuncionario();
		when(_funcionarioRepository.findById(funcionario.getId())).thenReturn(Optional.of(funcionario));

		// Act
		var result = _funcionarioController.ObterFuncionarioPorId(funcionario.getId());

		// Assert
		assertEquals(HttpStatus.OK, result.getStatusCode());
		verify(_funcionarioRepository, times(1)).findById(funcionario.getId());
	}

	@Test
	void DeveObterNotFoundAoBuscarFuncionarioPorId() {

		// Arrange
		var funcionarioId = UUID.randomUUID();
		when(_funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.empty());

		// Act
		var result = _funcionarioController.ObterFuncionarioPorId(funcionarioId);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		verify(_funcionarioRepository, times(1)).findById(funcionarioId);
	}

	@Test
	void DeveRetornarExceptionQuandoObterFuncionarioPorId() {

		// Arrange
		var funcionarioId = UUID.randomUUID();
		when(_funcionarioRepository.findById(funcionarioId)).thenThrow(new IllegalArgumentException());

		// Act
		assertThrows(RuntimeException.class, () -> _funcionarioController.ObterFuncionarioPorId(funcionarioId));

		// Assert
		verify(_funcionarioRepository, times(1)).findById(funcionarioId);
	}

	Funcionario CreateFuncionario() {

		Funcionario funcionario = new Funcionario();

		funcionario.setId(UUID.randomUUID());
		funcionario.setNome("João");
		funcionario.setEmail("joao@gmail.com");
		funcionario.setDocumento("6G56SDFGH");
		funcionario.setDataCadastro(LocalDate.now());
		funcionario.setDataNascimento(LocalDate.EPOCH);
		funcionario.setStatus(true);
		funcionario.setSalario(1000f);
		funcionario.setCargo(Cargo.ANALISTA);

		return funcionario;
	}
}
