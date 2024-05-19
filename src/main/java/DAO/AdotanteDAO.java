package DAO;

import DTO.Adotante;
import Interfaces.OperacaoBD;

import java.util.List;

public class AdotanteDAO implements OperacaoBD {

    private Conexao conexao = new Conexao();

    public boolean create(){
        return true;
    }

    public Adotante detalhe(int codigo){
        return null;
    }

    public List<Adotante> listar(){
        return null;
    }

    public boolean atualizar(int codigo){
        return true;
    }

    public boolean deletar(int codigo){
        return true;
    }

}
