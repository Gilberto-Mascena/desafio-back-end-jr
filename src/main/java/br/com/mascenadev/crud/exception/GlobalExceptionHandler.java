package br.com.mascenadev.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * **Tratador Global de Exceções para a API.**
 * <p>
 * Esta classe é responsável por centralizar o tratamento de exceções lançadas
 * pelos controladores e serviços da aplicação. Ela intercepta exceções específicas
 * e retorna respostas HTTP padronizadas com detalhes de erro formatados
 * pelo {@link ErroResponse}.
 * </p>
 * <p>
 * Utiliza a anotação {@code @ControllerAdvice} para aplicar o tratamento de exceções
 * globalmente em todos os controladores da aplicação.
 * </p>
 *
 * @author Gilberto Dev
 * @see org.springframework.web.bind.annotation.ControllerAdvice
 * @see org.springframework.web.bind.annotation.ExceptionHandler
 * @see br.com.mascenadev.crud.exception.ErroResponse
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções do tipo {@link MethodArgumentNotValidException}, que ocorrem
     * quando a validação de argumentos de método (ex: DTOs anotados com {@code @Valid}) falha.
     * <p>
     * Retorna uma resposta HTTP 400 Bad Request, contendo uma lista de erros de validação
     * para cada campo problemático.
     * </p>
     *
     * @param ex A exceção {@link MethodArgumentNotValidException} capturada.
     * @return Uma {@link ResponseEntity} contendo um {@link ErroResponse} com os detalhes dos erros de validação.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação nos campos enviados",
                fieldErrors
        );
        return ResponseEntity.badRequest().body(erroResponse);
    }

    /**
     * Trata exceções do tipo {@link TodoNaoEncontradoException}, lançadas quando
     * uma tarefa (Todo) solicitada não é encontrada no sistema.
     * <p>
     * Retorna uma resposta HTTP 404 Not Found, indicando que o recurso não foi localizado.
     * </p>
     *
     * @param ex A exceção {@link TodoNaoEncontradoException} capturada.
     * @return Uma {@link ResponseEntity} contendo um {@link ErroResponse} com a mensagem de "Todo não encontrado".
     */
    @ExceptionHandler(TodoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleTodoNaoEncontradoException(TodoNaoEncontradoException ex) {
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Todo não encontrado",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
    }

    /**
     * Trata todas as outras exceções genéricas do tipo {@link Exception}.
     * <p>
     * Atua como um "catch-all" para exceções não especificamente tratadas,
     * garantindo que nenhuma exceção interna seja exposta diretamente ao cliente.
     * Retorna uma resposta HTTP 500 Internal Server Error.
     * </p>
     *
     * @param ex A exceção {@link Exception} genérica capturada.
     * @return Uma {@link ResponseEntity} contendo um {@link ErroResponse} com uma mensagem de erro genérica.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(Exception ex) {
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde." // Mensagem genérica para o cliente
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
    }
}
