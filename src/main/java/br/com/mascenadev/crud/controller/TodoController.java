package br.com.mascenadev.crud.controller;

import br.com.mascenadev.crud.dtos.TodoRequestDTO;
import br.com.mascenadev.crud.dtos.TodoResponseDTO;
import br.com.mascenadev.crud.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * **Controlador REST para gerenciar operações relacionadas a Tarefas (Todos).**
 * <p>
 * Esta classe atua como a camada de entrada da API, lidando com requisições HTTP
 * para as operações CRUD (Criar, Listar, Buscar, Atualizar, Deletar) de tarefas.
 * Ela utiliza DTOs (Data Transfer Objects) para padronizar a entrada e saída de dados
 * da API, garantindo a validação dos dados de entrada e a formatação das respostas.
 * </p>
 * <p>
 * O controlador delega a lógica de negócio principal e o tratamento de exceções
 * para a camada de serviço ({@link TodoService}), mantendo-se focado em tarefas
 * de roteamento HTTP e conversão de dados.
 * </p>
 *
 * @author Gilberto Dev
 * @see br.com.mascenadev.crud.service.TodoService
 * @see br.com.mascenadev.crud.dtos.TodoRequestDTO
 * @see br.com.mascenadev.crud.dtos.TodoResponseDTO
 * @see org.springframework.web.bind.annotation.RestController
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since 1.0.0
 */
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    /**
     * Construtor para injeção de dependência do serviço de Todo.
     * O Spring injeta automaticamente uma instância de {@link TodoService}.
     *
     * @param todoService O serviço de Todo responsável pela lógica de negócio.
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Cria uma nova tarefa no sistema.
     * <p>
     * Recebe um {@link TodoRequestDTO} no corpo da requisição, que é validado
     * automaticamente pelas anotações de validação. Delega a lógica de criação
     * ao {@link TodoService} e retorna um {@link TodoResponseDTO} da tarefa
     * recém-criada com o status HTTP 201 Created. O cabeçalho 'Location'
     * na resposta aponta para o URI do novo recurso.
     * </p>
     *
     * @param requestDTO DTO contendo os dados da nova tarefa a ser criada.
     * @return {@link ResponseEntity} com status 201 (Created) e o {@link TodoResponseDTO} da tarefa.
     * Em caso de falha de validação, {@link br.com.mascenadev.crud.exception.GlobalExceptionHandler}
     * irá interceptar e retornar um 400 Bad Request.
     */
    @PostMapping
    public ResponseEntity<TodoResponseDTO> create(@RequestBody @Valid TodoRequestDTO requestDTO) {
        TodoResponseDTO createdTodo = todoService.create(requestDTO);
        URI location = URI.create("/todos/" + createdTodo.getId());
        return ResponseEntity.created(location).body(createdTodo);
    }

    /**
     * Lista todas as tarefas existentes no sistema.
     * <p>
     * Delega a busca ao {@link TodoService} e retorna uma lista de
     * {@link TodoResponseDTO}s com o status HTTP 200 OK.
     * </p>
     *
     * @return {@link ResponseEntity} com status 200 (OK) e uma {@link List} de {@link TodoResponseDTO}s.
     */
    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> list() {
        List<TodoResponseDTO> allTodo = todoService.listAll();
        return ResponseEntity.ok(allTodo);
    }

    /**
     * Atualiza uma tarefa existente pelo seu identificador único.
     * <p>
     * Recebe o ID da tarefa como parte da URL e um {@link TodoRequestDTO} no corpo
     * da requisição com os dados a serem atualizados, que é validado.
     * Delega a atualização ao {@link TodoService} e retorna o
     * {@link TodoResponseDTO} da tarefa atualizada com o status HTTP 200 OK.
     * </p>
     *
     * @param id         O identificador único da tarefa a ser atualizada.
     * @param requestDTO DTO contendo os dados atualizados da tarefa.
     * @return {@link ResponseEntity} com status 200 (OK) e o {@link TodoResponseDTO} da tarefa atualizada.
     * Em caso de tarefa não encontrada, {@link br.com.mascenadev.crud.exception.GlobalExceptionHandler}
     * irá interceptar e retornar um 404 Not Found.
     * Em caso de falha de validação, um 400 Bad Request.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TodoRequestDTO requestDTO) {
        TodoResponseDTO updatedTodo = todoService.update(id, requestDTO);
        return ResponseEntity.ok(updatedTodo);
    }

    /**
     * Busca uma tarefa específica pelo seu identificador único.
     * <p>
     * Recebe o ID da tarefa como parte da URL. Delega a busca ao {@link TodoService}
     * e retorna o {@link TodoResponseDTO} da tarefa encontrada com o status HTTP 200 OK.
     * </p>
     *
     * @param id O identificador único da tarefa a ser buscada.
     * @return {@link ResponseEntity} com status 200 (OK) e o {@link TodoResponseDTO} da tarefa encontrada.
     * Em caso de tarefa não encontrada, {@link br.com.mascenadev.crud.exception.GlobalExceptionHandler}
     * irá interceptar e retornar um 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> findById(@PathVariable Long id) {
        TodoResponseDTO foundAll = todoService.findById(id);
        return ResponseEntity.ok(foundAll);
    }


    /**
     * Deleta uma tarefa existente pelo seu identificador único.
     * <p>
     * Recebe o ID da tarefa como parte da URL. Delega a operação de exclusão
     * ao {@link TodoService}. Em caso de sucesso, retorna um {@link ResponseEntity}
     * com status HTTP 204 No Content, indicando que a requisição foi processada
     * com sucesso e não há conteúdo a ser retornado.
     * </p>
     *
     * @param id O identificador único da tarefa a ser deletada.
     * @return {@link ResponseEntity} com status 204 (No Content).
     * Em caso de tarefa não encontrada, {@link br.com.mascenadev.crud.exception.GlobalExceptionHandler}
     * irá interceptar e retornar um 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
