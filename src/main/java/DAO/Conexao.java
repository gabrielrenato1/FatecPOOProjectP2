package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection conexao;

    public boolean conectar(){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "@senha123");

        }catch (SQLException err){

            System.err.println(err.getMessage());
            return false;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return true;

    }

    public boolean desconectar(){

        try{

            this.conexao.close();

        }catch (SQLException err){

            System.err.println(err.getMessage());
            return false;

        }

        return true;

    }

    public Connection getConexao(){
        return this.conexao;
    }



}
