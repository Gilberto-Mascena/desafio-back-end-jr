package br.com.mascenadev.crud.repository;

import br.com.mascenadev.crud.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
