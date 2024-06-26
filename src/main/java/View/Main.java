package View;

import DTO.Adocao;
import DTO.Adotante;
import DTO.Animal;
import DTO.User;

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
    private JTextField txtComplemento;
    private JTextField txtBairro;
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
    private Animal animalDTO = new Animal();
    private List<Animal> listaAnimal = null;
    private Animal selectedAnimal = null;
    private int codigoAdotante;
    private Adotante adotanteDTO = new Adotante();
    private Adotante adotante = null;
    private Adocao adocaoDTO = new Adocao();
    private List<Adocao> listaAdocoes = null;
    private Adocao selectedAdocao = null;

    public Main(User usuario) {
        setContentPane(contentPane);
        setModal(true);
        System.out.println(usuario.getCodigo());
        if(Objects.equals(usuario.getTipo(), "adotante")){
            codigoAdotante = usuario.getCodigo();
            adotante = adotanteDTO.detalhe(this.codigoAdotante);
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
                    new AnimalDetalhe(selectedAnimal, adotante);
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

        setSize(1200,700);
        setLocationRelativeTo(null);
        setVisible(true);
        System.exit(0);

    }

    private void setAnimais(){

        listaAnimal = animalDTO.listar();

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

        listaAdocoes = adocaoDTO.listarPorAdotantes(codigoAdotante);

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

        this.textName.setText(adotante.getNome());
        this.txtEmail.setText(adotante.getEmail());
        this.txtCep.setText(String.valueOf(adotante.getCep()));
        this.txtEndereco.setText(adotante.getEndereco());
        this.txtNumero.setText(String.valueOf(adotante.getNumero()));
//        this.txtComplemento.setText(adotante.getComplemento());
//        this.txtBairro.setText(adotante.getBairro());
        this.txtCidade.setText(adotante.getCidade());
        this.txtEstado.setText(adotante.getEstado());

    }

    private void atualizarUser(Adotante adotante){

        this.adotante.setNome(textName.getText());
        this.adotante.setEmail(txtEmail.getText());
        this.adotante.setCep(txtCep.getText());
        this.adotante.setEndereco(txtEndereco.getText());
        this.adotante.setNumero(Integer.parseInt(txtNumero.getText()));
//        adotante.setComplemento(txtComplemento.getText());
//        adotante.setBairro(txtBairro.getText());
        this.adotante.setCidade(txtCidade.getText());
        this.adotante.setEstado(txtEstado.getText());

        if(this.adotante.atualizar(codigoAdotante)){
            lblStatus.setText("Usuário Atualizado com sucesso!");
        }else{
            lblStatus.setText("Erro ao atualizar o usuário!");
        }

    }

}
