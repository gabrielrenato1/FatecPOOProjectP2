package DTO;

import DAO.AdocaoDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adocao {

    private int codigo;
    private Animal animal;
    private Abrigo abrigo;
    private Adotante adotante;
    private String etapa;
    private String data;
    private String estado;
    private String cidade;
    private String endereco;
    private int numero;
    private String cep;

    public Adocao(){}

    public Adocao(int codigo, int codigoAnimal, int codigoAbrigo, int codigoAdotante, String etapa, String data, String estado, String cidade, String endereco, int numero, String cep) {
        this.setCodigo(codigo);
        this.setAnimal(codigoAnimal);
        this.setAbrigo(codigoAbrigo);
        this.setAdotante(codigoAdotante);
        this.setEtapa(etapa);
        this.setData(data);
        this.setEstado(estado);
        this.setCidade(cidade);
        this.setEndereco(endereco);
        this.setNumero(numero);
        this.setCep(cep);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(int codigoAnimal) {

        Animal animalDTO = new Animal();
        this.animal = animalDTO.detalhe(codigoAnimal);

    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(int codigoAbrigo) {

        Abrigo abrigoDTO = new Abrigo();
        this.abrigo = abrigoDTO.detalhe(codigoAbrigo);

    }

    public Adotante getAdotante() {
        return adotante;
    }

    public void setAdotante(int codigoAdotante) {

        Adotante adotanteDTO = new Adotante();
        this.adotante = adotanteDTO.detalhe(codigoAdotante);

    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getData() {

        try {

            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            date = dateFormater.parse(data);
            dateFormater.applyPattern("dd/MM/yyyy");

            return dateFormater.format(date);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void setData(String data) {
        this.data = data;
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

    public boolean solicitarAdocao(Animal animal, Adotante adotante){

        AdocaoDAO adocaoDAO = new AdocaoDAO();
        return adocaoDAO.criar(animal, adotante);

    }

    public boolean solicitacaoExiste(Animal animal, Adotante adotante){

        AdocaoDAO adocaoDAO = new AdocaoDAO();
        return adocaoDAO.solicitacaoExiste(animal, adotante);

    }

    public List<Adocao> listarPorAdotantes(int codigo) {

        AdocaoDAO adocaoDAO = new AdocaoDAO();
        return adocaoDAO.listarAdocoesAdotante(codigo);

    }

}
