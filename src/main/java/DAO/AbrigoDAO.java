package DAO;

import DTO.Abrigo;
import DTO.Adotante;
import DTO.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbrigoDAO{

    private Conexao conexao = new Conexao();

    public boolean create(Abrigo object){

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "INSERT INTO abrigos(nome, email, telefone, estado, cidade, endereco, numero, cep) " +
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

    public Abrigo detalhe(int codigo){

        Abrigo abrigo = null;

        try{

            if(conexao.conectar()){

                String sql = "SELECT * FROM abrigos WHERE codigo = ? LIMIT 1";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
                stmt.setInt(1, codigo);

                ResultSet result = stmt.executeQuery();

                result.next();

                abrigo = new Abrigo(
                        result.getInt("codigo"),
                        result.getString("nome"),
                        result.getString("documento"),
                        result.getString("email"),
                        result.getString("telefone"),
                        result.getString("estado"),
                        result.getString("cidade"),
                        result.getString("endereco"),
                        result.getInt("numero"),
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

        return abrigo;

    }

    public boolean atualizar(Abrigo object){

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "UPDATE abrigos SET nome = ?, email = ?, telefone = ?, estado = ?, cidade = ?,"+
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
                stmt.setInt(9, object.getCodigo());

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

}
