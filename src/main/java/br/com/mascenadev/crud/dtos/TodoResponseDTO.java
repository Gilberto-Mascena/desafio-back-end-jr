package br.com.mascenadev.crud.dtos;

import br.com.mascenadev.crud.domain.Todo;

import java.util.Objects;

/**
 * DTO (Data Transfer Object) para representar os dados de resposta
 * de uma Tarefa (Todo) para o cliente da API.
 * Contém apenas os campos que devem ser expostos.
 *
 * @author Gilberto Dev
 * @see br.com.mascenadev.crud.domain.Todo
 * @since 1.0.0
 */
public class TodoResponseDTO {

    /**
     * O identificador único da tarefa.
     */
    private final Long id;

    /**
     * O título da tarefa.
     */
    private final String titulo;

    /**
     * A descrição detalhada da tarefa.
     */
    private final String descricao;

    /**
     * Indica se a tarefa foi realizada (true) ou não (false).
     */
    private final Boolean realizado;

    /**
     * O nível de prioridade da tarefa.
     */
    private final Integer prioridade;

    /**
     * Construtor para criar um {@code TodoResponseDTO} a partir de uma entidade {@code Todo}.
     * Copia os dados relevantes da entidade para o DTO.
     *
     * @param todo A entidade {@link Todo} da qual os dados serão copiados.
     */
    public TodoResponseDTO(Todo todo) {
        this.id = todo.getId();
        this.titulo = todo.getTitulo();
        this.descricao = todo.getDescricao();
        this.realizado = todo.getRealizado();
        this.prioridade = todo.getPrioridade();
    }

    /**
     * Obtém o ID da tarefa.
     *
     * @return O ID da tarefa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtém o título da tarefa.
     *
     * @return O título da tarefa.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtém a descrição da tarefa.
     *
     * @return A descrição da tarefa.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obtém o status de realização da tarefa.
     *
     * @return {@code true} se a tarefa foi realizada, {@code false} caso contrário.
     */
    public Boolean getRealizado() {
        return realizado;
    }

    /**
     * Obtém a prioridade da tarefa.
     *
     * @return A prioridade da tarefa.
     */
    public Integer getPrioridade() {
        return prioridade;
    }

    /**
     * Compara este {@code TodoResponseDTO} com outro objeto.
     * A comparação é baseada no ID para garantir a unicidade.
     *
     * @param o O objeto a ser comparado.
     * @return {@code true} se os objetos forem iguais, {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoResponseDTO that = (TodoResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Gera um código hash para este {@code TodoResponseDTO} com base no seu ID.
     *
     * @return O código hash do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Retorna uma representação em string deste {@code TodoResponseDTO}.
     *
     * @return Uma string contendo os dados do DTO.
     */
    @Override
    public String toString() {
        return "TodoResponseDTO{" +
               "id=" + id +
               ", titulo='" + titulo + '\'' +
               ", descricao='" + descricao + '\'' +
               ", realizado=" + realizado +
               ", prioridade=" + prioridade +
               '}';
    }
}
