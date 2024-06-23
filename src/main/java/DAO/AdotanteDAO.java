package DAO;

import DTO.Adotante;
import DTO.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdotanteDAO{

    private Conexao conexao = new Conexao();

    public boolean create(Adotante object){

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "INSERT INTO adotantes(nome, email, telefone, estado, cidade, endereco, numero, cep) " +
                        "VALUES (?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setString(1, object.getNome());
                stmt.setString(2, object.getEmail());
                stmt.setString(3, object.getTelefone());
                stmt.setString(4, object.getEstado());
                stmt.setString(5, object.getCidade());
                stmt.setString(6, object.getEndereco());
                stmt.setInt(7, object.getNumero());
                stmt.setString(8, object.getCep());

                success = stmt.execute();

            }

        }catch(SQLException err){
            System.err.println(err.getMessage());
            success = false;
        }catch(Exception e){
            System.err.println(e.getMessage());
            success = false;
        }finally{
            conexao.desconectar();
        }

        return success;

    }

    public Adotante detalhe(int codigo){

        Adotante adotante = null;

        try{

            if(conexao.conectar()){

                String sql = "SELECT * FROM adotantes WHERE codigo = ? LIMIT 1";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
                stmt.setInt(1, codigo);

                ResultSet result = stmt.executeQuery();
                result.next();

                adotante = new Adotante(
                        result.getInt("codigo"),
                        result.getString("nome"),
                        result.getString("email"),
                        result.getString("telefone"),
                        result.getString("estado"),
                        result.getString("cidade"),
                        result.getString("endereco"),
                        result.getString("numero"),
                        result.getString("cep")
                );

            }

        }catch(SQLException err){
            System.err.println(err.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            conexao.desconectar();
        }

        return adotante;

    }

    public boolean atualizar(int codigo, Adotante object){

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "UPDATE adotantes SET nome = ?, email = ?, telefone = ?, estado = ?, cidade = ?,"+
                        "  endereco = ?, numero = ?, cep = ? WHERE codigo = ?";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setString(1, object.getNome());
                stmt.setString(2, object.getEmail());
                stmt.setString(3, object.getTelefone());
                stmt.setString(4, object.getEstado());
                stmt.setString(5, object.getCidade());
                stmt.setString(6, object.getEndereco());
                stmt.setInt(7, object.getNumero());
                stmt.setString(8, object.getCep());
                stmt.setInt(9, codigo);

                success = stmt.executeUpdate() > 0;

            }

        }catch(SQLException err){
            System.err.println(err.getMessage());
            success = false;
        }catch(Exception e){
            System.err.println(e.getMessage());
            success = false;
        }finally{
            conexao.desconectar();
        }

        return success;

    }

    public boolean deletar(int codigo){

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "DELETE FROM adotantes WHERE codigo = ?";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setInt(1, codigo);

                success = stmt.execute();

            }

        }catch(SQLException err){
            System.err.println(err.getMessage());
            success = false;
        }catch(Exception e){
            System.err.println(e.getMessage());
            success = false;
        }finally{
            conexao.desconectar();
        }

        return success;

    }

}
