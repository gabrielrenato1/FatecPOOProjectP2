package DTO;

import DAO.AutenticarDAO;
import Interfaces.Authenticate;
import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Authenticate {

    private int codigo;
    private String nome;
    private String email;
    private String documento;
    private String telefone;
    private String estado;
    private String cidade;
    private String endereco;
    private int numero;
    private String cep;
    private String senha;
    private String tipo;

    public User(){}

    public User(int codigo, String email, String senha, String tipo) {
        this.setCodigo(codigo);
        this.setEmail(email);
        this.setSenha(senha);
        this.setTipo(tipo);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public User autenticar(String email, String senha){

        try {

            AutenticarDAO autenticacao = new AutenticarDAO();
            User user = autenticacao.emailExiste(email);

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            if(myHash.equals(user.getSenha().toUpperCase())){
                return user;
            }else{
                return null;
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


}
