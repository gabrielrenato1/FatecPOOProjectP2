package View;

import DTO.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonLogin;
    private JButton buttonCadastro;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;

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

        setModal(true);
        setSize(300,450);
        setLocationRelativeTo(null);
        setVisible(true);

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
        new Login();
    }
}
