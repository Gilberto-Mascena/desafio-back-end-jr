package br.com.mascenadev.crud.exception;

/**
 * Exceção lançada quando uma tarefa (Todo) específica não é encontrada.
 * Esta é uma exceção de tempo de execução (unchecked exception) para evitar a propagação
 * excessiva da declaração de exceções nas assinaturas dos métodos.
 *
 * @author Gilberto Dev
 * @since 1.0.0
 */
public class TodoNaoEncontradoException extends RuntimeException {

    /**
     * Construtor padrão que cria uma exceção com uma mensagem genérica.
     */
    public TodoNaoEncontradoException() {
        super("Todo não encontrado");
    }

    /**
     * Construtor que cria uma exceção com uma mensagem específica, incluindo o ID da tarefa.
     *
     * @param id O ID da tarefa que não foi encontrada.
     */
    public TodoNaoEncontradoException(Long id) {
        super("Todo com o ID " + id + " não foi encontrado");
    }
}
