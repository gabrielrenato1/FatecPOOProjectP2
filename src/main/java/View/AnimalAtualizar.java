package View;

import DTO.Abrigo;
import DTO.Animal;

import javax.swing.*;
import java.awt.event.ContainerAdapter;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnimalAtualizar extends JDialog {
    private JPanel contentPane;
    private JButton buttonVoltar;
    private JButton buttonSalvar;
    private JLabel lblStatus;
    private JTextField textFieldNome;
    private JLabel lblNome;
    private JTextField textFieldRaca;
    private JLabel lblRaca;
    private JTextField textFieldHistSaude;
    private JLabel lblSaude;
    private JTextField textFieldNessEspeciais;
    private JLabel lblNecessidades;
    private JTextField textFieldIdade;
    private JLabel lblIdade;
    private JTextField textFieldDescricao;
    private JLabel lblDescricao;
    private JTextField textFieldTemperamento;
    private JLabel lblTemperamento;

    public AnimalAtualizar(Animal animal) {

        setContentPane(contentPane);

        ImageIcon img = new ImageIcon("src/main/icons/paw-print.png");
        setIconImage(img.getImage());
        setModal(true);
        setSize(600,700);
        setLocationRelativeTo(null);

        textFieldNome.setText(animal.getNome());
        textFieldDescricao.setText(animal.getDescricao());
        textFieldRaca.setText(animal.getRaca());
        textFieldIdade.setText(String.valueOf(animal.getIdade()));
        textFieldTemperamento.setText(animal.getTemperamento());
        textFieldHistSaude.setText(animal.getHistoricoSaude());
        textFieldNessEspeciais.setText(animal.getNecessidadesEspeciais());

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
                atualizarAnimal(animal);
                super.mouseClicked(e);
            }
        });

        setVisible(true);

    }

    private void atualizarAnimal(Animal animal){

        Animal animalDTO = new Animal();

        animalDTO.setNome(textFieldNome.getText());
        animalDTO.setDescricao(textFieldDescricao.getText());
        animalDTO.setRaca(textFieldRaca.getText());
        animalDTO.setIdade(Integer.parseInt(textFieldIdade.getText()));
        animalDTO.setTemperamento(textFieldTemperamento.getText());
        animalDTO.setHistoricoSaude(textFieldHistSaude.getText());
        animalDTO.setNecessidadesEspeciais(textFieldNessEspeciais.getText());

        if(animalDTO.atualizar(animal.getCodigo())){
            lblStatus.setText("Pet Criado com sucesso!");
        }else{
            lblStatus.setText("Erro ao criar o Pet");
        }

        dispose();

    }

}
