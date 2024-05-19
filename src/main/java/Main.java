import DAO.Conexao;
import DTO.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {


        Animal animal = new Animal();

        Animal detalhe = animal.detalhe();

        System.out.println(detalhe);

//        try{
//
//            Conexao conexao = new Conexao();
//
//            conexao.conectar();
//
//            String sql = "SELECT nome FROM leitor LIMIT 1";
//
//            PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
//
//            ResultSet resultado = stmt.executeQuery();
//
//            resultado.next();
//
//            System.out.println(resultado.getString("nome"));
//
//        }catch (SQLException err){
//            System.out.println("erro " + err.getMessage());
//        }


    }
}