package br.edu.utfpr.dao;

import java.sql.*;

import br.edu.utfpr.dto.PaisDTO;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;

@Log
public class PaisDAO extends TemplateDAO<PaisDTO>{

    // Responsável por criar a tabela País no banco
    public PaisDAO() {
        try ( Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE pais ("
                    + "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_pais_pk PRIMARY KEY,"
                    + "nome varchar(255),"
                    + "sigla varchar(3),"
                    + "codigoTelefone int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String InserirSQL() {
        return "INSERT INTO pais (nome, sigla, codigoTelefone) VALUES (?, ?, ?)";
    }

    @Override
    public String AlterarSQL() {
        return "UPDATE pais SET nome=?, sigla=?, codigoTelefone=? WHERE id=?";
    }

    @Override
    public String ListarSQL() {
        return "SELECT * FROM pais";
    }

    @Override
    public String ExcluirSQL() {
        return "DELETE FROM pais WHERE id=?";
    }

    @Override
    public void inflateStatementInserir(PreparedStatement statement, PaisDTO entity) {
        try{

            statement.setString(1, entity.getNome());
            statement.setString(2, entity.getSigla());
            statement.setInt(3, entity.getCodigoTelefone());

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void inflateStatementAlterar(PreparedStatement statement, PaisDTO entity) {
        try {

            statement.setString(1, entity.getNome());
            statement.setString(2, entity.getSigla());
            statement.setInt(3, entity.getCodigoTelefone());
            statement.setInt(4, entity.getId());

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void inflateStatementExcluir(PreparedStatement statement, int id) {
        try {

            statement.setInt(1, id);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public PaisDTO inflateEntity(ResultSet result) {
        try {
            return PaisDTO.builder()
                    .codigoTelefone(result.getInt("codigoTelefone"))
                    .id(result.getInt("id"))
                    .nome(result.getString("nome"))
                    .sigla(result.getString("sigla"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
