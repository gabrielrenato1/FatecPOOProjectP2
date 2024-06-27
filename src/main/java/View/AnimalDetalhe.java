package View;

import DTO.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;

public class AnimalDetalhe extends JDialog {
    private JPanel contentPaneDetalheAnimal;
    private JButton buttonSolicitar;
    private JLabel lblCodigo;
    private JLabel lblNomeValue;
    private JLabel lblAbrigoValue;
    private JLabel lblRacaValue;
    private JLabel lblIdadeValue;
    private JLabel lblTemperamentoValue;
    private JLabel lblSaudeValue;
    private JLabel lblNecessidadesValue;
    private JLabel lblDescricaoValue;
    private JTabbedPane tabbedPane1;
    private JLabel lblAbrigoNome;
    private JLabel lblAbrigoTelefone;
    private JLabel lblAbrigoEmail;
    private JLabel lblAbrigoDocumento;
    private JLabel lblAbrigoCep;
    private JLabel lblAbrigoEndereco;
    private JLabel lblAbrigoNumero;
    private JLabel lblAbrigoCidade;
    private JLabel lblAbrigoEstado;
    private JButton buttonVoltar;
    private JLabel lblNome;
    private JLabel lblTemperamento;
    private JLabel lblRaca;
    private JLabel lblSaude;
    private JLabel lblNecessidades;
    private JLabel lblIdade;
    private JLabel lblDescricao;
    private JButton enviarButton;
    private JTextField textMessage;
    private JTextArea textAreaMessages;
    private JButton buttonEditarPet;
    private Animal animalDTO = new Animal();

    private Adocao adocaoDTO = new Adocao();

    private boolean solicitacaoExiste = false;

    public AnimalDetalhe(Animal animal, Adotante adotante, Abrigo abrigo) {

        setContentPane(contentPaneDetalheAnimal);

        if(adotante != null) {
            buttonEditarPet.setVisible(false);
        }else{
            buttonSolicitar.setVisible(false);
        }

        ImageIcon img = new ImageIcon("src/main/icons/paw-print.png");
        setIconImage(img.getImage());

        setMensagens(animal, adotante);
        setAnimalData(animal);

        this.solicitacaoExiste = adocaoDTO.solicitacaoExiste(animal, adotante);

        if(this.solicitacaoExiste){

            buttonSolicitar.setText("Adoção já solicitada");
            buttonSolicitar.setEnabled(false);

        }

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        buttonSolicitar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                solicitarAdocao(animal, adotante);

                super.mouseClicked(e);

            }

        });

        buttonVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onCancel();
                super.mouseClicked(e);
            }
        });

        enviarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendMessage(animal, adotante);
                super.mouseClicked(e);
            }
        });

        buttonEditarPet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contentPaneDetalheAnimal.setFocusable(false);
                new AnimalAtualizar(animal);
                super.mouseClicked(e);
            }
        });

        setModal(true);
        setSize(600,700);
        setLocationRelativeTo(null);
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

    private void solicitarAdocao(Animal animal, Adotante adotante){

        if(!this.solicitacaoExiste){

            boolean success = adocaoDTO.solicitarAdocao(animal, adotante);

            if(success){
                this.solicitacaoExiste = true;
                buttonSolicitar.setText("Adoção solicitada");
                buttonSolicitar.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Solicitação de adoção enviada");
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao solicitar adoção");
            }

        }

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

    private void onCancel() {
        this.solicitacaoExiste = false;
        buttonSolicitar.setEnabled(true);
        buttonSolicitar.setText("Adoção solicitada");
        this.dispose();
    }

}
