package View;

import DTO.Abrigo;
import DTO.Adotante;
import DTO.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonLogin;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;
    private JTabbedPane tabbedPane1;
    private JPanel tabPerfil;
    private JLabel lblNome;
    private JTextField textName;
    private JTextField txtEmail;
    private JTextField txtCep;
    private JTextField txtEndereco;
    private JTextField txtNumero;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JCheckBox checkAdotante;
    private JCheckBox checkAbrigo;
    private JLabel lblStatus;
    private JButton buttonCadastrar;
    private JTextField txtTelefone;
    private JPasswordField passwordFieldCadastro;

    private boolean isAdotante = false;

    public Login() {

        setContentPane(contentPane);

        ImageIcon img = new ImageIcon("src/main/icons/paw-print.png");
        setIconImage(img.getImage());

        buttonLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                autenticacao();
                super.mouseClicked(e);
            }
        });

        checkAdotante.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isAdotante = true;
                checkAbrigo.setSelected(false);
                super.mouseClicked(e);
            }
        });

        checkAbrigo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isAdotante = false;
                checkAdotante.setSelected(false);
                super.mouseClicked(e);
            }
        });

        buttonCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                criarUser();
                super.mouseClicked(e);
            }
        });
    }

    private void criarUser(){

        if(isAdotante){

            Adotante adotante = new Adotante();

            adotante.setNome(textName.getText());
            adotante.setEmail(txtEmail.getText());
            adotante.setCep(txtCep.getText());
            adotante.setEndereco(txtEndereco.getText());
            adotante.setNumero(Integer.parseInt(txtNumero.getText()));
//        adotante.setComplemento(txtComplemento.getText());
//        adotante.setBairro(txtBairro.getText());
            adotante.setCidade(txtCidade.getText());
            adotante.setEstado(txtEstado.getText());
            adotante.setSenha(passwordFieldCadastro.getText());
            adotante.setTelefone(txtTelefone.getText());

            if(adotante.criar()){
                lblStatus.setText("Cadastro feito com sucesso!");
            }else{
                lblStatus.setText("Erro ao realizar cadastro");
            }

        }else{

            Abrigo abrigo = new Abrigo();

            abrigo.setNome(textName.getText());
            abrigo.setEmail(txtEmail.getText());
            abrigo.setCep(txtCep.getText());
            abrigo.setEndereco(txtEndereco.getText());
            abrigo.setNumero(Integer.parseInt(txtNumero.getText()));
//        adotante.setComplemento(txtComplemento.getText());
//        adotante.setBairro(txtBairro.getText());
            abrigo.setCidade(txtCidade.getText());
            abrigo.setEstado(txtEstado.getText());
            abrigo.setSenha(passwordFieldCadastro.getText());
            abrigo.setTelefone(txtTelefone.getText());

            if(abrigo.criar()){
                lblStatus.setText("Cadastro feito com sucesso!");
            }else{
                lblStatus.setText("Erro ao realizar cadastro");
            }

        }

    }

    private void autenticacao(){

        User userDTO = new User();
        User usuario = userDTO.autenticar(textFieldEmail.getText(), passwordField.getText());

        if(usuario != null){
            new Main(usuario);
            dispose();

        }

    }

    public static void main(String[] args) {
        Login dialog = new Login();
        dialog.setModal(true);
        dialog.setSize(450,700);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
