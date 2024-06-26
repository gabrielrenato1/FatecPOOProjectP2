package DTO;

import DAO.MensagenDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Mensagem {

    private int codigo;
    private Adotante adotante;
    private Abrigo abrigo;
    private Animal animal;
    private String remetente;
    private String conteudo;
    private String data;
    private String hora;

    public Mensagem(int codigo, int abrigo, int adotante,int animal, String remetente, String conteudo, String data, String hora) {
        this.setCodigo(codigo);
        this.setAdotante(adotante);
        this.setAbrigo(abrigo);
        this.setAnimal(animal);
        this.setRemetente(remetente);
        this.setConteudo(conteudo);
        this.setData(data);
        this.setHora(hora);
    }

    public Mensagem() {}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Adotante getAdotante() {
        return adotante;
    }

    public void setAdotante(int codigoAdotante) {

        Adotante adotanteDTO = new Adotante();
        this.adotante = adotanteDTO.detalhe(codigoAdotante);

    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(int codigoAbrigo) {

        Abrigo abrigoDTO = new Abrigo();
        this.abrigo = abrigoDTO.detalhe(codigoAbrigo);

    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(int codigoAnimal) {

        Animal animalDTO = new Animal();
        this.animal = animalDTO.detalhe(codigoAnimal);

    }

    public List<Mensagem> listarMensagens(Animal animal, Adotante adotante){

        MensagenDAO mensagemDAO = new MensagenDAO();
        return mensagemDAO.listar(adotante.getCodigo(), animal.getCodigo());

    }

    public void enviarMensagem(Animal animal, Adotante adotante, String text) {

        MensagenDAO mensagemDAO = new MensagenDAO();
        mensagemDAO.create(animal, adotante, text);

    }
}
