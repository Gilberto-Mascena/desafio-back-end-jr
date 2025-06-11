package br.com.mascenadev.crud.exception;

import java.time.LocalDateTime;

/**
 * Record que representa uma resposta padronizada para erros da API.
 * Fornece detalhes como timestamp, status HTTP, tipo de erro e detalhes específicos.
 *
 * @param timestamp O momento em que o erro ocorreu.
 * @param status    O código de status HTTP do erro (ex: 404, 500).
 * @param error     Uma mensagem geral descrevendo o tipo de erro.
 * @param details   Um objeto contendo detalhes adicionais sobre o erro (ex: mensagens de validação).
 */
public record ErroResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        Object details
) {
}
