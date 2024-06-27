package View;

import DTO.Adocao;
import DTO.Adotante;
import DTO.Animal;
import DTO.Mensagem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class AdocaoDetalhe extends JDialog {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JLabel lblNomeValue;
    private JLabel lblTemperamentoValue;
    private JLabel lblRacaValue;
    private JLabel lblAbrigoValue;
    private JLabel lblSaudeValue;
    private JLabel lblNecessidadesValue;
    private JLabel lblIdadeValue;
    private JLabel lblDescricaoValue;
    private JLabel lblAbrigoNome;
    private JLabel lblAbrigoTelefone;
    private JLabel lblAbrigoEmail;
    private JLabel lblAbrigoDocumento;
    private JLabel lblAbrigoCep;
    private JLabel lblAbrigoEndereco;
    private JLabel lblAbrigoNumero;
    private JLabel lblAbrigoCidade;
    private JLabel lblAbrigoEstado;
    private JButton enviarButton;
    private JTextField textMessage;
    private JTextArea textAreaMessages;
    private JButton buttonVoltar;
    private JLabel lblNome;
    private JLabel lblTemperamento;
    private JLabel lblRaca;
    private JLabel lblSaude;
    private JLabel lblNecessidades;
    private JLabel lblIdade;
    private JLabel lblDescricao;
    private JLabel lblAbrigoComplemento;
    private JLabel lblAbrigoBairro;

    public AdocaoDetalhe(Adocao adocao) {
        setContentPane(contentPane);
        setModal(true);
        ImageIcon img = new ImageIcon("src/main/icons/paw-print.png");
        setIconImage(img.getImage());

        setAnimalData(adocao.getAnimal());
        setMensagens(adocao.getAnimal(), adocao.getAdotante());

        setSize(600,700);
        setLocationRelativeTo(null);

        buttonVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                super.mouseClicked(e);
            }
        });
        enviarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendMessage(adocao.getAnimal(), adocao.getAdotante());
                super.mouseClicked(e);
            }
        });

        setVisible(true);


    }

    private void setAnimalData(Animal animal){

        lblNomeValue.setText(animal.getNome());
        lblRacaValue.setText(animal.getRaca());
        lblIdadeValue.setText(String.valueOf(animal.getIdade()));
        lblTemperamentoValue.setText(animal.getTemperamento());
        lblSaudeValue.setText(animal.getHistoricoSaude());
        lblNecessidadesValue.setText(animal.getNecessidadesEspeciais());
        lblDescricaoValue.setText(animal.getDescricao());

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

    }

    private void setMensagens(Animal animal, Adotante adotante){

        textAreaMessages.setText(null);
        textAreaMessages.setDisabledTextColor(Color.BLACK);
        Mensagem mensagemDTO = new Mensagem();

        List<Mensagem> mensagens = null;

        if(adotante != null){
            mensagens = mensagemDTO.listarMensagensAdotante(animal, adotante);
        }else{
            mensagens = mensagemDTO.listarMensagensAbrigo(animal);
        }

        for (Mensagem value : mensagens) {

            String sender = Objects.equals(value.getRemetente(), "abrigo") ? value.getAbrigo().getNome() : value.getAdotante().getNome();

            textAreaMessages.append(sender+": " + value.getConteudo() + "\n" + value.getData() + " " + value.getHora() + "\n\n");
        }

    }

    private void sendMessage(Animal animal, Adotante adotante){

        if(!Objects.equals(textMessage.getText(), "")){

            Mensagem messageDTO = new Mensagem();
            messageDTO.enviarMensagem(animal, adotante, textMessage.getText());
            setMensagens(animal, adotante);

            textMessage.setText("");

        }

    }


}
