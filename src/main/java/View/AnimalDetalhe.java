package View;

import DTO.Animal;

import javax.swing.*;
import java.awt.event.*;

public class AnimalDetalhe extends JDialog {
    private JPanel contentPaneDetalheAnimal;
    private JButton buttonAdotar;
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
    private JTabbedPane tabbedPane1;
    private JLabel lblAbrigoNome;
    private JLabel lblAbrigoTelefone;
    private JLabel lblAbrigoEmail;
    private JLabel lblAbrigoDocumento;
    private JLabel lblAbrigoCep;
    private JLabel lblAbrigoEndereco;
    private JLabel lblAbrigoNumero;
    private JLabel lblAbrigoComplemento;
    private JLabel lblAbrigoBairro;
    private JLabel lblAbrigoCidade;
    private JLabel lblAbrigoEstado;
    private Animal animalDTO = new Animal();

    public AnimalDetalhe(Animal animal) {
        setContentPane(contentPaneDetalheAnimal);
        setModal(true);

        lblNomeValue.setText(animal.getNome());
        lblRacaValue.setText(animal.getRaca());
        lblIdadeValue.setText(String.valueOf(animal.getIdade()));
        lblTemperamentoValue.setText(animal.getTemperamento());
        lblSaudeValue.setText(animal.getHistoricoSaude());
        lblNecessidadesValue.setText(animal.getNecessidadesEspeciais());
        lblDescricaoValue.setText(animal.getDescricao());
        lblAbrigoValue.setText(animal.getAbrigo().getNome());

        lblAbrigoNome.setText(animal.getAbrigo().getNome());
        lblAbrigoTelefone.setText(animal.getAbrigo().getTelefone());
        lblAbrigoEmail.setText(animal.getAbrigo().getEmail());
        lblAbrigoDocumento.setText(animal.getAbrigo().getDocumento());
        lblAbrigoCep.setText(animal.getAbrigo().getCep());
        lblAbrigoEndereco.setText(animal.getAbrigo().getEndereco());
        lblAbrigoNumero.setText(String.valueOf(animal.getAbrigo().getNumero()));
//        lblAbrigoComplemento.setText(animal.getAbrigo().getComplemento());
//        lblAbrigoBairro.setText(animal.getAbrigo().getBairro());
        lblAbrigoCidade.setText(animal.getAbrigo().getCidade());
        lblAbrigoEstado.setText(animal.getAbrigo().getEstado());

        buttonAdotar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPaneDetalheAnimal.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setSize(600,700);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void onCancel() {
        this.dispose();
    }

}
