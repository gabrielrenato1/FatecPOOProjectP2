package DAO;

import DTO.Abrigo;
import Interfaces.OperacaoBD;

import java.util.List;

public class AbrigoDAO implements OperacaoBD {

    private Conexao conexao = new Conexao();

    public boolean create(){
        return true;
    }

    public Abrigo detalhe(int codigo){
        return null;
    }

    public List<Abrigo> listar(){
        return null;
    }

    public boolean atualizar(int codigo){
        return true;
    }

    public boolean deletar(int codigo){
        return true;
    }

}
