package DAO;

import DTO.Animal;
import Interfaces.OperacaoBD;

import java.util.List;

public class AnimalDAO implements OperacaoBD {

    private Conexao conexao = new Conexao();

    public boolean create(){
        return true;
    }

    public Animal detalhe(int codigo){
        return null;
    }

    public List<Animal> listar(){
        return null;
    }

    public boolean atualizar(int codigo){
        return true;
    }

    public boolean deletar(int codigo){
        return true;
    }

}
