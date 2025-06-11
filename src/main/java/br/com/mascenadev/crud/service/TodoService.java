package br.com.mascenadev.crud.service;

import br.com.mascenadev.crud.domain.Todo;
import br.com.mascenadev.crud.dtos.TodoRequestDTO;
import br.com.mascenadev.crud.dtos.TodoResponseDTO;
import br.com.mascenadev.crud.exception.TodoNaoEncontradoException;
import br.com.mascenadev.crud.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócio das operações CRUD para a entidade {@link Todo}.
 * Gerencia a comunicação entre o controlador e o repositório,
 * aplicando regras de negócio e tratamento de exceções.
 *
 * @author Gilberto Dev
 * @see br.com.mascenadev.crud.domain.Todo
 * @see br.com.mascenadev.crud.repository.TodoRepository
 * @see br.com.mascenadev.crud.dtos.TodoRequestDTO
 * @see br.com.mascenadev.crud.dtos.TodoResponseDTO
 * @see br.com.mascenadev.crud.exception.TodoNaoEncontradoException
 * @since 1.0.0
 */
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    /**
     * Construtor para injeção de dependência do repositório de Todo.
     *
     * @param todoRepository O repositório de Todo.
     */
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    /**
     * Cria uma nova tarefa no sistema.
     * Recebe um DTO de requisição, converte-o para entidade, salva no banco de dados,
     * e retorna um DTO de resposta da tarefa criada.
     *
     * @param requestDTO O DTO de requisição contendo os dados da nova tarefa.
     * @return O DTO de resposta da tarefa criada.
     */
    @Transactional
    public TodoResponseDTO create(TodoRequestDTO requestDTO) {
        Todo todo = requestDTO.toEntity();
        Todo savedTodo = todoRepository.save(todo);
        return new TodoResponseDTO(savedTodo);
    }

    /**
     * Lista todas as tarefas existentes, ordenadas por prioridade (descendente) e título (ascendente).
     * Converte as entidades {@link Todo} para {@link TodoResponseDTO}.
     *
     * @return Uma lista de DTOs de resposta de tarefas.
     */
    public List<TodoResponseDTO> listAll() {
        Sort sort = Sort.by("prioridade")
                .descending()
                .and(Sort.by("titulo").ascending());
        return todoRepository.findAll(sort).stream().map(TodoResponseDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Busca uma tarefa específica pelo seu ID.
     *
     * @param id O ID da tarefa a ser buscada.
     * @return O DTO de resposta da tarefa encontrada.
     * @throws TodoNaoEncontradoException Se a tarefa com o ID fornecido não for encontrada.
     */
    public TodoResponseDTO findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNaoEncontradoException(id));
        return new TodoResponseDTO(todo);
    }

    /**
     * Atualiza uma tarefa existente.
     * Recebe o ID da tarefa e um DTO de requisição com os dados atualizados.
     * Lança {@link TodoNaoEncontradoException} se a tarefa não existir.
     *
     * @param id         O ID da tarefa a ser atualizada.
     * @param requestDTO O DTO de requisição com os dados para atualização.
     * @return O DTO de resposta da tarefa atualizada.
     * @throws TodoNaoEncontradoException Se a tarefa com o ID fornecido não for encontrada.
     */
    @Transactional
    public TodoResponseDTO update(Long id, TodoRequestDTO requestDTO) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNaoEncontradoException(id));
        existingTodo.setTitulo(requestDTO.getTitulo());
        existingTodo.setDescricao(requestDTO.getDescricao());
        existingTodo.setRealizado(requestDTO.getRealizado());
        existingTodo.setPrioridade(requestDTO.getPrioridade());

        Todo updatedTodo = todoRepository.save(existingTodo);
        return new TodoResponseDTO(updatedTodo);
    }

    /**
     * Deleta uma tarefa pelo seu ID.
     * Lança {@link TodoNaoEncontradoException} se a tarefa não existir.
     *
     * @param id O ID da tarefa a ser deletada.
     * @throws TodoNaoEncontradoException Se a tarefa com o ID fornecido não for encontrada.
     */
    @Transactional
    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNaoEncontradoException(id);
        }
        todoRepository.deleteById(id);
    }
}