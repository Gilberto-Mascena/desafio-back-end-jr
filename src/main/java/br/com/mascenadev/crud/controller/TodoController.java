package br.com.mascenadev.crud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mascenadev.crud.domain.Todo;
import br.com.mascenadev.crud.service.TodoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    // Injeção de dependência via construtor sem a necessidade de anotação
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    @Transactional
    List<Todo> create(@RequestBody @Valid Todo todo) {

        return todoService.create(todo);
    }

    @GetMapping
    List<Todo> list() {

        return todoService.list();
    }

    @PutMapping("/{id}")
    @Transactional
    List<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {

        return todoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    @Transactional
    List<Todo> delete(@PathVariable Long id) {

        return todoService.delete(id);
    }
}
