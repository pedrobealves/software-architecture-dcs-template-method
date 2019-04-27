package br.edu.utfpr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata template para DAO
 *
 * @param <T> Recebe DTO como parâmetro a ser substituido em tempo de execução
 */
public abstract class TemplateDAO<T> {

    /**
     * Constante string de conexão ao banco apache derby
     */
    private static final String DEFAULT_URL = "jdbc:derby:memory:database";

    /**
     * @return string de consulta SQL especifica de inserir
     */
    public abstract String InserirSQL();

    /**
     * @return string de consulta SQL especificia de alteração
     */
    public abstract String AlterarSQL();

    /**
     * @return string de consulta SQL especifica de listagem
     */
    public abstract String ListarSQL();

    /**
     * @return string de consulta SQL especifica de exclusão
     */
    public abstract String ExcluirSQL();

    /**
     *
     * @param statement
     * @param entity
     */
    public abstract void inflateStatementInserir(PreparedStatement statement, T entity);

    /**
     *
     * @param statement
     * @param entity
     */
    public abstract void inflateStatementAlterar(PreparedStatement statement, T entity);

    /**
     *
     * @param statement
     * @param id
     */
    public abstract void inflateStatementExcluir(PreparedStatement statement, int id);

    /**
     *
     * @param rs
     * @return
     */
    public abstract T inflateEntity(ResultSet rs);



    /**
     * @param entity
     * @return
     */
    public boolean inserir(T entity) {
        try ( Connection conn = DriverManager.getConnection(DEFAULT_URL)) {

            PreparedStatement statement = conn.prepareStatement(InserirSQL());

            inflateStatementInserir(statement,entity);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;    }

    /**
     * @return
     */
    public List<T> listarTodos() {

        List<T> resultado = new ArrayList<>();

        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database")) {

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(ListarSQL());

            while (result.next()) {
                resultado.add(inflateEntity(result));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;    }

    /**
     * @param id
     * @return
     */
    public boolean excluir(int id) {
        try ( Connection conn = DriverManager.getConnection(DEFAULT_URL)) {

            PreparedStatement statement = conn.prepareStatement(ExcluirSQL());

            inflateStatementExcluir(statement,id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;    }

    /**
     *
     * @param entity
     * @return
     */
    public boolean alterar(T entity) {
        try ( Connection conn = DriverManager.getConnection(DEFAULT_URL)) {

            PreparedStatement statement = conn.prepareStatement(AlterarSQL());

            inflateStatementAlterar(statement,entity);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0)
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;    }

    }
