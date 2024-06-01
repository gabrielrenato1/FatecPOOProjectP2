package DTO;

import DAO.AdotanteDAO;

//TODO: Adicionar campos Bairro e complemento

public class Adotante extends User{

    private String preferencia;

    public Adotante(){}

    public Adotante(int codigo, int nome, String email, int telefone, String estado, String cidade, String endereco, String numero, String cep) {
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

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public boolean criar(){

        AdotanteDAO dao = new AdotanteDAO();
        return dao.create(this);

    }

    public Adotante detalhe(int codigo){

        AdotanteDAO dao = new AdotanteDAO();
        return dao.detalhe(codigo);

    }

    public boolean atualizar(int codigo){

        AdotanteDAO dao = new AdotanteDAO();
        return dao.atualizar(codigo, this);

    }

    public boolean deletar(int codigo){

        AdotanteDAO dao = new AdotanteDAO();
        return dao.deletar(codigo);

    }

}
