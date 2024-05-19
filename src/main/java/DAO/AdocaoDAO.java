package DAO;

import DTO.Adocao;
import Interfaces.OperacaoBD;

import java.util.List;

public class AdocaoDAO implements OperacaoBD {

    private Conexao conexao = new Conexao();

    public boolean create(){
        return true;
    }

    public Adocao detalhe(int codigo){
        return null;
    }

    public List<Adocao> listar(){
        return null;
    }

    public boolean atualizar(int codigo){
        return true;
    }

    public boolean deletar(int codigo){
        return true;
    }

}
