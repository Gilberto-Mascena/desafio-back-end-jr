package br.com.mascenadev.crud.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

/**
 * **Entidade JPA para representar uma Tarefa (Todo).**
 * <p>
 * Mapeia a classe {@code Todo} para a tabela "todos" no banco de dados.
 * Inclui validações de Bean Validation para garantir a integridade dos dados
 * antes da persistência.
 * </p>
 *
 * @author Gilberto Dev
 * @see jakarta.persistence.Entity
 * @see jakarta.validation.constraints
 * @since 1.0.0
 */
@Entity
@Table(name = "todos")
public class Todo {

    /**
     * Identificador único da tarefa.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título da tarefa.
     * Deve ser preenchido e ter entre 2 e 100 caracteres.
     */
    @NotBlank(message = "Título é obrigatório")
    @Size(min = 2, max = 100, message = "Título deve ter entre 2 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;

    /**
     * Descrição detalhada da tarefa.
     * Deve ser preenchida e ter entre 2 e 255 caracteres.
     */
    @NotBlank(message = "Descrição é obrigatório")
    @Size(min = 2, max = 255, message = "Descrição deve ter entre 2 e 255 caracteres")
    @Column(nullable = false, length = 255)
    private String descricao;

    /**
     * Indica se a tarefa foi realizada (true) ou não (false).
     * Não pode ser nulo.
     */
    @NotNull(message = "Realizado é obrigatório")
    @Column(nullable = false)
    private Boolean realizado;

    /**
     * Nível de prioridade da tarefa.
     * Deve ser preenchido, não pode ser negativo e tem um valor máximo de 5.
     */
    @NotNull(message = "Prioridade é obrigatório")
    @Min(value = 0, message = "Prioridade não pode ser negativa")
    @Max(value = 5, message = "Prioridade máxima é 5")
    @Column(nullable = false)
    private Integer prioridade;

    /**
     * Construtor padrão exigido pelo JPA.
     * Inicializa {@code realizado} como {@code false} por padrão para novas tarefas.
     */
    public Todo() {
        this.realizado = false;
    }

    /**
     * Construtor para criar uma nova instância de {@code Todo}.
     *
     * @param titulo     O título da tarefa.
     * @param descricao  A descrição detalhada da tarefa.
     * @param realizado  O status de realização da tarefa (true se realizada, false caso contrário).
     * @param prioridade O nível de prioridade da tarefa.
     */
    public Todo(String titulo, String descricao, Boolean realizado, Integer prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.realizado = realizado;
        this.prioridade = prioridade;
    }

    /**
     * Obtém o ID da tarefa.
     *
     * @return o ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da tarefa.
     *
     * @param id o novo ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o titúlo da tarefa.
     *
     * @return o titúlo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o titúlo da tarefa.
     *
     * @param titulo o novo titúlo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém a descrição da tarefa.
     *
     * @return a descrição
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da tarefa.
     *
     * @param descricao a nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o estado da tarefa
     *
     * @return o estado
     */
    public Boolean getRealizado() {
        return realizado;
    }

    /**
     * Define o estado.
     *
     * @param realizado o no estado
     */
    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    /**
     * Obtém a prioridade da tarefa.
     *
     * @return a prioridade
     */
    public Integer getPrioridade() {
        return prioridade;
    }

    /**
     * Define a prioridade da tarefa.
     *
     * @param prioridade a nova prioridade
     */
    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    /**
     * Compara dois objetos Todo com base no ID.
     *
     * @param o objeto a ser comparado
     * @return true se os objetos tiverem o mesmo ID
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    /**
     * Gera um valor de hash para o objeto Todo com base no seu ID.
     * Fundamental para o correto funcionamento em coleções como {@code HashMap} e {@code HashSet}.
     *
     * @return O código hash do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Retorna uma representação em string do objeto Todo.
     *
     * @return string contendo os dados do Todo
     */
    @Override
    public String toString() {
        return "Todo [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", realizado=" + realizado
               + ", prioridade=" + prioridade + "]";
    }
}
