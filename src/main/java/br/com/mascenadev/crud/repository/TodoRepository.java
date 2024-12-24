package br.com.mascenadev.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mascenadev.crud.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
