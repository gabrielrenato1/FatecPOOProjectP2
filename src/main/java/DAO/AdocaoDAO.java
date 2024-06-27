package DAO;

import DTO.Adocao;
import DTO.Adotante;
import DTO.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdocaoDAO{

    private Conexao conexao = new Conexao();

    public boolean criar(Animal animal, Adotante adotante) {

        boolean success = false;

        try{

            if(conexao.conectar()){

                String sql = "INSERT INTO adocoes(codigo_animal, codigo_abrigo, codigo_adotante, codigo_etapa, data, estado, cidade, endereco, numero, cep) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setInt(1, animal.getCodigo());
                stmt.setInt(2, animal.getAbrigo().getCodigo());
                stmt.setInt(3, adotante.getCodigo());
                stmt.setInt(4, 1);
                stmt.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                stmt.setString(6, animal.getAbrigo().getEstado());
                stmt.setString(7, animal.getAbrigo().getCidade());
                stmt.setString(8, animal.getAbrigo().getEndereco());
                stmt.setInt(9, animal.getAbrigo().getNumero());
                stmt.setString(10, animal.getAbrigo().getCep());

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

    public List<Adocao> listarAdocoesAdotante(int codigo){

        List<Adocao> lista = new ArrayList<Adocao>();

        try{

            if(conexao.conectar()){

                String sql = "SELECT *, etapas_adocao.descricao as etapa FROM adocoes LEFT JOIN etapas_adocao ON codigo_etapa = etapas_adocao.codigo WHERE codigo_adotante = ? ORDER BY adocoes.codigo desc";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setInt(1, codigo);

                ResultSet result = stmt.executeQuery();

                while(result.next()){

                    Adocao adocao = new Adocao(
                        result.getInt("codigo"),
                        result.getInt("codigo_animal"),
                        result.getInt("codigo_abrigo"),
                        result.getInt("codigo_adotante"),
                        result.getString("etapa"),
                        result.getString("data"),
                        result.getString("estado"),
                        result.getString("cidade"),
                        result.getString("endereco"),
                        result.getInt("numero"),
                        result.getString("cep")
                    );

                    lista.add(adocao);

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

    public boolean solicitacaoExiste(Animal animal, Adotante adotante) {

        boolean exists = false;

        try{

            if(conexao.conectar()){

                String sql = "SELECT count(codigo) as count FROM sistema_adocao_animais.adocoes where codigo_animal = ? AND codigo_adotante = ?";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setInt(1, animal.getCodigo());
                stmt.setInt(2, adotante.getCodigo());

                ResultSet result = stmt.executeQuery();

                result.next();

                exists = result.getInt("count") > 0;

            }

        }catch(SQLException err){
            System.err.println(err.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            conexao.desconectar();
        }

        return exists;

    }

    public List<Adocao> listarAdocoesAbrigo(int codigo) {

        List<Adocao> lista = new ArrayList<Adocao>();

        try{

            if(conexao.conectar()){

                String sql = "SELECT *, etapas_adocao.descricao as etapa FROM adocoes LEFT JOIN etapas_adocao ON codigo_etapa = etapas_adocao.codigo WHERE codigo_abrigo = ? ORDER BY adocoes.codigo desc";

                PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);

                stmt.setInt(1, codigo);

                ResultSet result = stmt.executeQuery();

                while(result.next()){

                    Adocao adocao = new Adocao(
                            result.getInt("codigo"),
                            result.getInt("codigo_animal"),
                            result.getInt("codigo_abrigo"),
                            result.getInt("codigo_adotante"),
                            result.getString("etapa"),
                            result.getString("data"),
                            result.getString("estado"),
                            result.getString("cidade"),
                            result.getString("endereco"),
                            result.getInt("numero"),
                            result.getString("cep")
                    );

                    lista.add(adocao);

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
