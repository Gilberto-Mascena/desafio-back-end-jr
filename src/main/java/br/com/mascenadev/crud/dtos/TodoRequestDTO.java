package br.com.mascenadev.crud.dtos;

import br.com.mascenadev.crud.domain.Todo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.BeanUtils;

/**
 * DTO (Data Transfer Object) para representar os dados de requisição
 * ao criar ou atualizar uma Tarefa (Todo).
 * Contém os campos que o cliente envia para a API e suas validações.
 *
 * @author Gilberto Dev
 * @see br.com.mascenadev.crud.domain.Todo
 * @since 1.0.0
 */
public class TodoRequestDTO {

    /**
     * O título da tarefa.
     * Deve ser preenchido e ter entre 2 e 100 caracteres.
     */
    @NotBlank(message = "Título é obrigatório")
    @Size(min = 2, max = 100, message = "Título deve ter entre 2 e 100 caracteres")
    private String titulo;

    /**
     * A descrição detalhada da tarefa.
     * Deve ser preenchida e ter entre 2 e 255 caracteres.
     */
    @NotBlank(message = "Descrição é obrigatório")
    @Size(min = 2, max = 255, message = "Descrição deve ter entre 2 e 255 caracteres")
    private String descricao;

    /**
     * Indica se a tarefa foi realizada (true) ou não (false).
     * Não pode ser nulo.
     */
    @NotNull(message = "Realizado é obrigatório")
    private Boolean realizado;

    /**
     * O nível de prioridade da tarefa.
     * Deve ser preenchido, não pode ser negativo e tem um valor máximo de 5.
     */
    @NotNull(message = "Prioridade é obrigatório")
    @Min(value = 0, message = "Prioridade não pode ser negativa")
    @Max(value = 5, message = "Prioridade máxima é 5")
    private Integer prioridade;

    /**
     * Construtor padrão.
     * Inicializa o campo 'realizado' como false por padrão.
     */
    public TodoRequestDTO() {
        this.realizado = false;
    }

    /**
     * Construtor para criar uma nova instância de TodoRequestDTO.
     *
     * @param titulo     O título da tarefa.
     * @param descricao  A descrição detalhada da tarefa.
     * @param realizado  O status de realização da tarefa (true se realizada, false caso contrário).
     * @param prioridade O nível de prioridade da tarefa.
     */
    public TodoRequestDTO(String titulo, String descricao, Boolean realizado, Integer prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.realizado = realizado;
        this.prioridade = prioridade;
    }

    /**
     * Obtém o título da tarefa.
     *
     * @return o título da tarefa
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título da tarefa.
     *
     * @param titulo o novo título da tarefa
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém a descrição da tarefa.
     *
     * @return a descrição da tarefa
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da tarefa.
     *
     * @param descricao a nova descrição da tarefa
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o status de realização da tarefa.
     *
     * @return o status de realização da tarefa
     */
    public Boolean getRealizado() {
        return realizado;
    }

    /**
     * Define o status de realização da tarefa.
     *
     * @param realizado o novo status de realização da tarefa
     */
    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    /**
     * Obtém a prioridade da tarefa.
     *
     * @return a prioridade da tarefa
     */
    public Integer getPrioridade() {
        return prioridade;
    }

    /**
     * Define a prioridade da tarefa.
     *
     * @param prioridade a nova prioridade da tarefa
     */
    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    /**
     * Converte o DTO para uma entidade {@link Todo}.
     * O ID não é copiado, pois este DTO é para requisições de criação/atualização.
     *
     * @return Uma nova instância da entidade {@link Todo} com os dados do DTO.
     */
    public Todo toEntity() {
        Todo todo = new Todo();
        BeanUtils.copyProperties(this, todo);
        return todo;
    }

    /**
     * Retorna uma representação em string do objeto Todo.
     * Útil para logging e depuração.
     *
     * @return Uma string contendo os dados da tarefa (título, descrição, realizado, prioridade).
     */
    @Override
    public String toString() {
        return "TodoRequestDTO{" +
               "titulo='" + titulo + '\'' +
               ", descricao='" + descricao + '\'' +
               ", realizado=" + realizado +
               ", prioidade=" + prioridade +
               '}';
    }
}
