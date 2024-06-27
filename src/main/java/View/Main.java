package View;

import DTO.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;


public class Main extends JDialog {

    private JTabbedPane tabbedPane1;
    private JPanel contentPane;
    private JTable JtbAnimais;
    private JButton verPetButton;
    private JTextField textName;
    private JLabel lblNome;
    private JTextField txtEmail;
    private JTextField txtCep;
    private JTextField txtEndereco;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JButton buttonUpdate;
    private JLabel lblStatus;
    private JTextField txtNumero;
    private JTable JtbAdocoes;
    private JButton buttonVerAdocao;
    private JPanel tabPets;
    private JPanel tabAdocoes;
    private JPanel tabPerfil;
    private JTextField txtTelefone;
    private JButton buttonCriarPet;
    private Animal animalDTO = new Animal();
    private Adocao adocaoDTO = new Adocao();
    private Abrigo abrigoDTO = new Abrigo();
    private List<Animal> listaAnimal = null;
    private List<Adocao> listaAdocoes = null;
    private Animal selectedAnimal = null;
    private Adocao selectedAdocao = null;
    private Adotante adotanteDTO = new Adotante();
    private Adotante adotante = null;
    private Abrigo abrigo = null;

    public Main(User usuario) {
        setContentPane(contentPane);
        setModal(true);

        if(Objects.equals(usuario.getTipo(), "adotante")) {
            buttonCriarPet.setVisible(false);
            adotante = adotanteDTO.detalhe(usuario.getCodigo());
        }else{
            abrigo = abrigoDTO.detalhe(usuario.getCodigo());
        }

        setAnimais();

        ImageIcon img = new ImageIcon("src/main/icons/paw-print.png");
        setIconImage(img.getImage());

        buttonUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            atualizarUser(adotante);
            }
        });

        JtbAnimais.setDefaultEditor(Object.class, null);
        JtbAdocoes.setDefaultEditor(Object.class, null);

        JtbAnimais.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try{
                    selectedAnimal = listaAnimal.get(JtbAnimais.getSelectedRow());
                }catch(Exception err){
                    selectedAnimal = null;
                }

                super.mouseClicked(e);
            }
        });

        verPetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(selectedAnimal == null){
                    JOptionPane.showMessageDialog(null, "Selecione um Pet, por favor");
                }else{
                    new AnimalDetalhe(selectedAnimal, adotante, abrigo);
                }

                super.mouseClicked(e);
            }
        });

        JtbAdocoes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try{
                    selectedAdocao = listaAdocoes.get(JtbAdocoes.getSelectedRow());
                }catch(Exception err){
                    selectedAdocao = null;
                }

                super.mouseClicked(e);
            }
        });
        buttonVerAdocao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AdocaoDetalhe(selectedAdocao);
                super.mouseClicked(e);
            }
        });
        tabPets.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                setAnimais();
                super.componentShown(e);
            }
        });
        tabAdocoes.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                setAdocoes();
                super.componentShown(e);
            }
        });
        tabPerfil.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                setUser();
                super.componentShown(e);
            }
        });
        JtbAnimais.addComponentListener(new ComponentAdapter() {
        });
        JtbAnimais.addKeyListener(new KeyAdapter() {
        });

        buttonCriarPet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AnimalCriar(abrigo);
                super.mouseClicked(e);
            }

        });

        setSize(1200,700);
        setLocationRelativeTo(null);
        setVisible(true);
        System.exit(0);

    }

    private void setAnimais(){

        if(adotante != null){
            listaAnimal = animalDTO.listar();
        }else{
            listaAnimal = animalDTO.listarPorAbrigo(abrigo.getCodigo());
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("Nome");
        model.addColumn("Raca");
        model.addColumn("Idade");

        for (Animal value : listaAnimal) {
            model.addRow(new Object[]{
                    "#" + value.getCodigo(),
                    value.getNome(),
                    value.getRaca(),
                    value.getIdade(),
            });
        }

        JtbAnimais.setRowHeight(30);
        JtbAnimais.setModel(model);

    }

    private void setAdocoes(){

        if(adotante != null){
            listaAdocoes = adocaoDTO.listarPorAdotantes(adotante.getCodigo());
        }else{
            listaAdocoes =  adocaoDTO.listarPorAbrigo(abrigo.getCodigo());
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Nome Pet");
        model.addColumn("Abrigo");
        model.addColumn("Status");
        model.addColumn("Data");

        for (Adocao value : listaAdocoes) {
            model.addRow(new Object[]{
                    "#" + value.getCodigo(),
                    value.getAnimal().getNome(),
                    value.getAbrigo().getNome(),
                    value.getEtapa(),
                    value.getData()

            });
        }

        JtbAdocoes.setRowHeight(30);
        JtbAdocoes.setModel(model);

    }

    private void setUser(){

        if(adotante != null){
            this.textName.setText(adotante.getNome());
            this.txtEmail.setText(adotante.getEmail());
            this.txtCep.setText(String.valueOf(adotante.getCep()));
            this.txtEndereco.setText(adotante.getEndereco());
            this.txtNumero.setText(String.valueOf(adotante.getNumero()));
//        this.txtComplemento.setText(adotante.getComplemento());
//        this.txtBairro.setText(adotante.getBairro());
            this.txtCidade.setText(adotante.getCidade());
            this.txtEstado.setText(adotante.getEstado());
            this.txtTelefone.setText(adotante.getTelefone());
        }else{
            this.textName.setText(abrigo.getNome());
            this.txtEmail.setText(abrigo.getEmail());
            this.txtCep.setText(String.valueOf(abrigo.getCep()));
            this.txtEndereco.setText(abrigo.getEndereco());
            this.txtNumero.setText(String.valueOf(abrigo.getNumero()));
//        this.txtComplemento.setText(abrigo.getComplemento());
//        this.txtBairro.setText(abrigo.getBairro());
            this.txtCidade.setText(abrigo.getCidade());
            this.txtEstado.setText(abrigo.getEstado());
            this.txtTelefone.setText(abrigo.getTelefone());
        }

    }

    private void atualizarUser(Adotante adotante){

        if(adotante != null){

            this.adotante.setNome(textName.getText());
            this.adotante.setEmail(txtEmail.getText());
            this.adotante.setCep(txtCep.getText());
            this.adotante.setEndereco(txtEndereco.getText());
            this.adotante.setNumero(Integer.parseInt(txtNumero.getText()));
//        adotante.setComplemento(txtComplemento.getText());
//        adotante.setBairro(txtBairro.getText());
            this.adotante.setCidade(txtCidade.getText());
            this.adotante.setEstado(txtEstado.getText());
            this.adotante.setEstado(txtTelefone.getText());

            if(this.adotante.atualizar(this.adotante.getCodigo())){
                lblStatus.setText("Usuário Atualizado com sucesso!");
            }else{
                lblStatus.setText("Erro ao atualizar o usuário!");
            }

        }else{

            this.abrigo.setNome(textName.getText());
            this.abrigo.setEmail(txtEmail.getText());
            this.abrigo.setCep(txtCep.getText());
            this.abrigo.setEndereco(txtEndereco.getText());
            this.abrigo.setNumero(Integer.parseInt(txtNumero.getText()));
//        abrigo.setComplemento(txtComplemento.getText());
//        abrigo.setBairro(txtBairro.getText());
            this.abrigo.setCidade(txtCidade.getText());
            this.abrigo.setEstado(txtEstado.getText());
            this.abrigo.setEstado(txtTelefone.getText());

            if(this.abrigo.atualizar()){
                lblStatus.setText("Usuário Atualizado com sucesso!");
            }else{
                lblStatus.setText("Erro ao atualizar o usuário!");
            }

        }

    }

}
