package DTO;

import DAO.AdotanteDAO;
import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;

//TODO: Adicionar campos Bairro e complemento

public class Adotante extends User{

    private String preferencia;

    public Adotante(){}

    public Adotante(int codigo, String nome, String email, String telefone, String estado, String cidade, String endereco, String numero, String cep) {
        setCodigo(codigo);
        setNome(nome);
        setEmail(email);
        setTelefone(String.valueOf(telefone));
        setEstado(estado);
        setCidade(cidade);
        setEndereco(endereco);
        setNumero(Integer.parseInt(numero));
        setCep(cep);
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public boolean criar(){

        try{

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(this.getSenha().getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            this.setSenha(myHash);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

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

}
