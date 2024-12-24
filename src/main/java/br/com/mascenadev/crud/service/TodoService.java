package br.com.mascenadev.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mascenadev.crud.domain.Todo;
import br.com.mascenadev.crud.repository.TodoRepository;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    // Injeção de dependência via construtor sem a necessidade de anotação
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {

        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list() {

        Sort sort = Sort.by("prioridade")
                .descending()
                .and(Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(Long id, Todo todo) {

        Optional<Todo> obj = todoRepository.findById(id);

        if (obj.isEmpty()) {
            ResponseEntity.notFound().build();
            return list();
        }

        if (obj.isPresent()) {
            Todo entity = obj.get();
            entity.setNome(todo.getNome());
            entity.setDescricao(todo.getDescricao());
            entity.setRealizado(todo.isRealizado());
            entity.setPrioridade(todo.getPrioridade());
            todoRepository.save(entity);
        }
        return list();
    }

    public List<Todo> delete(Long id) {

        todoRepository.deleteById(id);
        return list();
    }
}