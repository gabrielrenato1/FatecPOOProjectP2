package View;

import DTO.Abrigo;
import DTO.Animal;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnimalCriar extends JDialog {
    private JPanel contentPane;
    private JLabel lblNome;
    private JLabel lblTemperamento;
    private JLabel lblRaca;
    private JLabel lblSaude;
    private JLabel lblNecessidades;
    private JLabel lblIdade;
    private JLabel lblDescricao;
    private JTextField textFieldNome;
    private JTextField textFieldDescricao;
    private JTextField textFieldRaca;
    private JTextField textFieldIdade;
    private JTextField textFieldTemperamento;
    private JTextField textFieldHistSaude;
    private JTextField textFieldNessEspeciais;
    private JButton buttonSalvar;
    private JButton buttonVoltar;
    private JLabel lblStatus;

    public AnimalCriar(Abrigo abrigo) {

        setContentPane(contentPane);
        ImageIcon img = new ImageIcon("src/main/icons/paw-print.png");
        setIconImage(img.getImage());
        setModal(true);
        setSize(600,700);
        setLocationRelativeTo(null);

        buttonVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                super.mouseClicked(e);
            }
        });
        buttonSalvar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                criarAnimal(abrigo);
                super.mouseClicked(e);
            }
        });

        setVisible(true);

    }

    private void criarAnimal(Abrigo abrigo){

        Animal animalDTO = new Animal();

        animalDTO.setNome(textFieldNome.getText());
        animalDTO.setDescricao(textFieldDescricao.getText());
        animalDTO.setRaca(textFieldRaca.getText());
        animalDTO.setIdade(Integer.parseInt(textFieldIdade.getText()));
        animalDTO.setTemperamento(textFieldTemperamento.getText());
        animalDTO.setHistoricoSaude(textFieldHistSaude.getText());
        animalDTO.setNecessidadesEspeciais(textFieldNessEspeciais.getText());
        animalDTO.setAbrigo(abrigo.getCodigo());

        if(animalDTO.criar()){
            lblStatus.setText("Pet Criado com sucesso!");
            textFieldNome.setText("");
            textFieldDescricao.setText("");
            textFieldRaca.setText("");
            textFieldIdade.setText("");
            textFieldTemperamento.setText("");
            textFieldHistSaude.setText("");
            textFieldNessEspeciais.setText("");
        }else{
            lblStatus.setText("Erro ao criar o Pet");
        }

        dispose();

    }

}
