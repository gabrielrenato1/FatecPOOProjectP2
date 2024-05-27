package DTO;

import DAO.AbrigoDAO;
import DAO.AnimalDAO;

import java.util.List;

public class Abrigo extends User{

    public Abrigo(int codigo, int nome, String email, int telefone, String estado, String cidade, String endereco, String numero, String cep) {
        setCodigo(codigo);
        setNome(String.valueOf(nome));
        setEmail(email);
        setTelefone(String.valueOf(telefone));
        setEstado(estado);
        setCidade(cidade);
        setEndereco(endereco);
        setNumero(Integer.parseInt(numero));
        setCep(Integer.parseInt(cep));
    }

    public boolean criar(){

        AbrigoDAO dao = new AbrigoDAO();
        return dao.create(this);

    }

    public Abrigo detalhe(int codigo){

        AbrigoDAO dao = new AbrigoDAO();
        return dao.detalhe(codigo);

    }

    public boolean atualizar(int codigo){

        AbrigoDAO dao = new AbrigoDAO();
        return dao.atualizar(codigo, this);

    }

    public boolean deletar(int codigo){

        AbrigoDAO dao = new AbrigoDAO();
        return dao.deletar(codigo);

    }

}
