package DAO;

import DTO.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnimalDAO{

    private Conexao conexao = new Conexao();

    public boolean create(Animal object){

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "INSERT INTO animais(nome, idade, raca, temperamento, historico_saude, necessidades_especiais, descricao, foto) " +
                        "VALUES (?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setString(1, object.getNome());
                stmt.setInt(2, object.getIdade());
                stmt.setString(3, object.getRaca());
                stmt.setString(4, object.getTemperamento());
                stmt.setString(5, object.getHistoricoSaude());
                stmt.setString(6, object.getNecessidadesEspeciais());
                stmt.setString(7, object.getDescricao());
                stmt.setString(8, object.getFoto());

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

    public Animal detalhe(int codigo){

        Animal animal = null;

        try{

            if(conexao.conectar()){

                String sql = "SELECT * FROM animais WHERE codigo = ?";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
                stmt.setInt(1, codigo);

                ResultSet result = stmt.executeQuery();

                while(result.next()){

                    animal = new Animal(
                            result.getInt("codigo"),
                            result.getInt("codigo_abrigo"),
                            result.getString("nome"),
                            result.getInt("idade"),
                            result.getString("raca"),
                            result.getString("temperamento"),
                            result.getString("historico_saude"),
                            result.getString("necessidades_especiais"),
                            result.getString("descricao"),
                            result.getString("foto")
                    );

                }

            }

        }catch(SQLException err){
            System.err.println(err.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            conexao.desconectar();
        }

        return animal;

    }

    public List<Animal> listar(){

        List<Animal> lista = new ArrayList<Animal>();

        try{

            if(conexao.conectar()){

                String sql = "SELECT * FROM animais";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                ResultSet result = stmt.executeQuery();

                while(result.next()){

                    Animal animal = new Animal(
                            result.getInt("codigo"), result.getString("nome"),
                            result.getString("raca"), result.getInt("idade")
                    );

                    lista.add(animal);

                }

            }

        }catch(SQLException err){
            System.err.println(err.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            conexao.desconectar();
        }

        return lista;

    }

    public boolean atualizar(int codigo, Animal object){

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "UPDATE animais SET nome = ?, idade = ?, raca = ?, temperamento = ?, historico_saude = ?,"+
                        " necessidades_especiais = ?, descricao = ?, foto = ? WHERE codigo = ?";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setString(1, object.getNome());
                stmt.setInt(2, object.getIdade());
                stmt.setString(3, object.getRaca());
                stmt.setString(4, object.getTemperamento());
                stmt.setString(5, object.getHistoricoSaude());
                stmt.setString(6, object.getNecessidadesEspeciais());
                stmt.setString(7, object.getDescricao());
                stmt.setString(8, object.getFoto());
                stmt.setInt(9, codigo);

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

    public boolean deletar(int codigo){

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "DELETE FROM animais WHERE codigo = ?";

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
