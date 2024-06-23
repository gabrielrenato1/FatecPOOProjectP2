package View;

import DTO.Adocao;
import DTO.Adotante;
import DTO.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
    private Animal animalDTO = new Animal();
    private List<Animal> listaAnimal = animalDTO.listar();
    private Animal selectedAnimal = null;
    private int codigoAdotante = 2;
    private Adotante adotanteDTO = new Adotante();
    Adotante adotante = adotanteDTO.detalhe(this.codigoAdotante);

    private Adocao adocaoDTO = new Adocao();
    private List<Adocao> listaAdocoes = adocaoDTO.listarPorAdotantes(this.codigoAdotante);


    public Main() {
        setContentPane(contentPane);
        setModal(true);

        this.setAnimais();
        this.setAdocoes();
        this.setUser();

        buttonUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            atualizarUser(adotante);
            }
        });

        JtbAnimais.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try{
                    selectedAnimal = listaAnimal.get(JtbAnimais.getSelectedRow()-1);
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
                    AnimalDetalhe detalheDialog = new AnimalDetalhe(selectedAnimal);
                }

                super.mouseClicked(e);
            }
        });

    }

    private void setAnimais(){

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("codigo");
        model.addColumn("nome");
        model.addColumn("raca");
        model.addColumn("idade");

        model.addRow(new Object[] {"Código", "Nome", "Raça", "Idade"});

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

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("codigo");
        model.addColumn("nome");
        model.addColumn("raca");
        model.addColumn("idade");

        model.addRow(new Object[] {"Código", "Nome Pet", "Abrigo", "Status", "Data"});

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

    public static void main(String[] args) {
        Main dialog = new Main();
        dialog.setSize(1200,700);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }

}
