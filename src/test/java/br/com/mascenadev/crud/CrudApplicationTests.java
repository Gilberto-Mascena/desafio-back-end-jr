package br.com.mascenadev.crud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.mascenadev.crud.domain.Todo;
import jakarta.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrudApplicationTests {

	@Resource
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {

		var todo = new Todo("Tudo 6", "Tudo 6", true, 1);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo("Tudo 6")
				.jsonPath("$[0].descricao").isEqualTo(todo.getNome())
				.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testCreateTodoFail() {

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
						new Todo("", "", true, 1))
				.exchange()
				.expectStatus()
				.isBadRequest();
	}
}
