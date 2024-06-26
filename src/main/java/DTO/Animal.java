package DTO;

import DAO.AnimalDAO;

import java.util.List;

public class Animal {

    private int codigo;
    private String nome;
    private int idade;
    private String raca;
    private String temperamento;
    private String historico_saude;
    private String necessidades_especiais;
    private String descricao;
    private String foto;
    private Abrigo abrigo;

    public Animal(){}
    public Animal(int codigo, String nome, String raca, int idade){
        setCodigo(codigo);
        setNome(nome);
        setRaca(raca);
        setIdade(idade);
    }

    public Animal(int codigo, int codigoAbrigo, String nome, int idade, String raca, String temperamento, String historicoSaude, String necessidadesEspeciais, String descricao, String foto) {
        setCodigo(codigo);
        setAbrigo(codigoAbrigo);
        setNome(nome);
        setIdade(idade);
        setRaca(raca);
        setTemperamento(temperamento);
        setHistoricoSaude(historicoSaude);
        setNecessidadesEspeciais(necessidadesEspeciais);
        setDescricao(descricao);
        setFoto(foto);
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public String getHistoricoSaude() {
        return historico_saude;
    }

    public void setHistoricoSaude(String historico_saude) {
        this.historico_saude = historico_saude;
    }

    public String getNecessidadesEspeciais() {
        return necessidades_especiais;
    }

    public void setNecessidadesEspeciais(String necessidades_especiais) {
        this.necessidades_especiais = necessidades_especiais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(int codigoAbrigo) {

        Abrigo abrigoDTO = new Abrigo();
        this.abrigo = abrigoDTO.detalhe(codigoAbrigo);

    }

    public boolean criar(){

        AnimalDAO dao = new AnimalDAO();
        return dao.create(this);

    }

    public List<Animal> listar(){

        AnimalDAO dao = new AnimalDAO();
        return dao.listar();

    }

    public Animal detalhe(int codigo){

        AnimalDAO dao = new AnimalDAO();
        return dao.detalhe(codigo);

    }

    public boolean atualizar(int codigo){

        AnimalDAO dao = new AnimalDAO();
        return dao.atualizar(codigo, this);

    }

    public boolean deletar(int codigo){

        AnimalDAO dao = new AnimalDAO();
        return dao.deletar(codigo);

    }


}
