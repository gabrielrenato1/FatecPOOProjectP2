package DAO;

import DTO.Adocao;
import DTO.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdocaoDAO{

    private Conexao conexao = new Conexao();

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
