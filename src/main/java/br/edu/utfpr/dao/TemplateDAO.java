package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe abstrata template para DAO
 *
 * @param <T> Recebe DTO como parâmetro a ser substituido em tempo de execução
 */
public abstract class TemplateDAO<T> {

    /**
     *  Constante string de conexão ao banco apache derby
     */
    private static final String DEFAULT_URL = "jdbc:derby:memory:database";
    /**
     *
     * @return string de consulta SQL especifica de inserir
     */
    public abstract String getSQLInsert();

    /**
     *
     * @return string de consulta SQL especificia de alteração
     */
    public abstract String getSQLAlterar();

    /**
     *
     * @return string de consulta SQL especifica de listagem
     */
    public abstract String getSQLMostrarTodos();

    /**
     *
     * @return string de consulta SQL especifica de exclusão
     */
    public abstract String getSQLExcluir();

}
