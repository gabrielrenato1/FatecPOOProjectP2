package View;

import DTO.Adotante;

import javax.swing.*;
import java.awt.event.*;

public class AdotanteDetalhe extends JDialog {
    private JPanel contentPane;
    private JButton buttonUpdate;
    private JButton buttonCancel;
    private JLabel lblNome;
    private JTextField textName;
    private JTextField txtEmail;
    private JTextField txtCep;
    private JTextField txtEndereco;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JLabel lblStatus;
    private int codigoAdotante = 2;
    private Adotante adotanteDTO = new Adotante();

    public AdotanteDetalhe() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonUpdate);

        Adotante adotante = adotanteDTO.detalhe(this.codigoAdotante);

        this.textName.setText(adotante.getNome());
        this.txtEmail.setText(adotante.getEmail());
        this.txtCep.setText(String.valueOf(adotante.getCep()));
        this.txtEndereco.setText(adotante.getEndereco());
        this.txtNumero.setText(String.valueOf(adotante.getNumero()));
//        this.txtComplemento.setText(adotante.getComplemento());
//        this.txtBairro.setText(adotante.getBairro());
        this.txtCidade.setText(adotante.getCidade());
        this.txtEstado.setText(adotante.getEstado());

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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                onUpdate(adotante);

            }
        });
    }

    private void onUpdate(Adotante adotante){

        adotante.setNome(textName.getText());
        adotante.setEmail(txtEmail.getText());
        adotante.setCep(Integer.parseInt(txtCep.getText()));
        adotante.setEndereco(txtEndereco.getText());
        adotante.setNumero(Integer.parseInt(txtNumero.getText()));
//        adotante.setComplemento(txtComplemento.getText());
//        adotante.setBairro(txtBairro.getText());
        adotante.setCidade(txtCidade.getText());
        adotante.setEstado(txtEstado.getText());

        if(adotante.atualizar(codigoAdotante)){
            lblStatus.setText("Usuário Atualizado com sucesso!");
        }else{
            lblStatus.setText("Erro ao atualizar o usuário!");
        }

    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        AdotanteDetalhe dialog = new AdotanteDetalhe();
        dialog.setSize(600,700);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
