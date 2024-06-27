package DTO;

import DAO.AbrigoDAO;
import DAO.AnimalDAO;

import java.util.List;

public class Abrigo extends User{

    public Abrigo(int codigo, String nome, String documento, String email, String telefone, String estado, String cidade, String endereco, int numero, String cep) {
        setCodigo(codigo);
        setNome(nome);
        setDocumento(documento);
        setEmail(email);
        setTelefone(telefone);
        setEstado(estado);
        setCidade(cidade);
        setEndereco(endereco);
        setNumero(numero);
        setCep(cep);
    }

    public Abrigo() {}

    public boolean criar(){

        AbrigoDAO dao = new AbrigoDAO();
        return dao.create(this);

    }

    public Abrigo detalhe(int codigo){

        AbrigoDAO dao = new AbrigoDAO();
        return dao.detalhe(codigo);

    }

    public boolean atualizar(){

        AbrigoDAO dao = new AbrigoDAO();
        return dao.atualizar(this);

    }

}
