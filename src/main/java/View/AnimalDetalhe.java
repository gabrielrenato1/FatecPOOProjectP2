package View;

import DTO.Animal;

import javax.swing.*;
import java.awt.event.*;

public class AnimalDetalhe extends JDialog {
    private JPanel contentPaneDetalheAnimal;
    private JButton buttonCancel;
    private JLabel lblCodigo;
    private JLabel lblNome;
    private JLabel lblTemperamento;
    private JLabel lblRaca;
    private JLabel lblAbrigo;
    private JLabel lblSaude;
    private JLabel lblNecessidades;
    private JLabel lblIdade;
    private JLabel lblNomeValue;
    private JLabel lblAbrigoValue;
    private JLabel lblRacaValue;
    private JLabel lblIdadeValue;
    private JLabel lblTemperamentoValue;
    private JLabel lblSaudeValue;
    private JLabel lblNecessidadesValue;
    private JLabel lblDescricao;
    private JLabel lblDescricaoValue;
    private Animal animalDTO = new Animal();

    public AnimalDetalhe(int codigo) {
        setContentPane(contentPaneDetalheAnimal);
        setModal(true);

        Animal animal = animalDTO.detalhe(codigo);

        lblCodigo.setText("#"+String.valueOf(animal.getCodigo()));
        lblNomeValue.setText(animal.getNome());
        lblAbrigoValue.setText("Abrigo");
        lblRacaValue.setText(animal.getRaca());
        lblIdadeValue.setText(String.valueOf(animal.getIdade()));
        lblTemperamentoValue.setText(animal.getTemperamento());
        lblSaudeValue.setText(animal.getHistoricoSaude());
        lblNecessidadesValue.setText(animal.getNecessidadesEspeciais());
        lblDescricaoValue.setText(animal.getDescricao());
        System.out.println(animal.getNome());

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPaneDetalheAnimal.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setSize(600,700);
        setLocationRelativeTo(null);
        setVisible(true);
        System.exit(0);

    }

    private void onCancel() {
        this.dispose();
    }

}
