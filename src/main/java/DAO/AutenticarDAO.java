package DAO;

import DTO.Abrigo;
import DTO.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticarDAO {

    private Conexao conexao = new Conexao();

    public User emailExiste(String email){

        User user = null;

        try{

            if(conexao.conectar()){

                String sql = "SELECT codigo, email, senha, 'adotante' as tipo FROM adotantes WHERE email = ? " +
                        "UNION " +
                        "SELECT codigo, email, senha, 'abrigo' as tipo FROM abrigos WHERE email = ?";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, email);

                ResultSet result = stmt.executeQuery();

                result.next();

                user = new User(
                        result.getInt("codigo"),
                        result.getString("email"),
                        result.getString("senha"),
                        result.getString("tipo")
                );


            }

        }catch(SQLException err){
            System.err.println(err.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            conexao.desconectar();
        }

        return user;

    }

}
