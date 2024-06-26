package DAO;

import DTO.Adotante;
import DTO.Animal;
import DTO.Mensagem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MensagenDAO {

    private Conexao conexao = new Conexao();

    public boolean create(Animal animal, Adotante adotante, String mensagem){

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "INSERT INTO mensagens(codigo_adotante, codigo_abrigo, codigo_animal, remetente, conteudo, data, hora) " +
                        "VALUES (?,?,?,?,?,?,?)";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setInt(1, adotante.getCodigo());
                stmt.setInt(2, animal.getAbrigo().getCodigo());
                stmt.setInt(3, animal.getCodigo());
                stmt.setString(4, "adotante");
                stmt.setString(5, mensagem);
                stmt.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                stmt.setString(7, new SimpleDateFormat("HH:mm").format(new Date()));

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

    public List<Mensagem> listar(int codigoAdotante, int codigoAnimal){

        List<Mensagem> lista = new ArrayList<Mensagem>();

        try{

            if(conexao.conectar()){

                String sql = "SELECT * FROM mensagens WHERE codigo_adotante = ? AND codigo_animal = ? ORDER BY codigo ASC";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
                stmt.setInt(1, codigoAdotante);
                stmt.setInt(2, codigoAnimal);

                ResultSet result = stmt.executeQuery();

                while(result.next()){

                    Mensagem mensagem = new Mensagem(
                            result.getInt("codigo"),
                            result.getInt("codigo_abrigo"),
                            result.getInt("codigo_adotante"),
                            result.getInt("codigo_animal"),
                            result.getString("remetente"),
                            result.getString("conteudo"),
                            result.getString("data"),
                            result.getString("hora")
                    );

                    lista.add(mensagem);

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

}
