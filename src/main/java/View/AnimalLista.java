package View;

import DTO.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class AnimalLista extends JDialog {
    private JPanel contentPaneListaAnimal;
    private JButton buttonDetalhe;
    private JTable JtbAnimais;
    private Animal animalDTO = new Animal();
    private List<Animal> lista = animalDTO.listar();
    private Animal selectedAnimal = null;

    public AnimalLista() {
        setContentPane(contentPaneListaAnimal);
        setModal(true);
        getRootPane().setDefaultButton(buttonDetalhe);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("codigo");
        model.addColumn("nome");
        model.addColumn("raca");
        model.addColumn("idade");

        model.addRow(new Object[] {"Código", "Nome", "Raça", "Idade"});

        for (Animal value : lista) {
            model.addRow(new Object[]{
                    "#" + value.getCodigo(),
                    value.getNome(),
                    value.getRaca(),
                    value.getIdade(),
            });
        }

        JtbAnimais.setRowHeight(30);
        JtbAnimais.setModel(model);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPaneListaAnimal.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        JtbAnimais.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try{
                    selectedAnimal = lista.get(JtbAnimais.getSelectedRow()-1);
                }catch(Exception err){
                    selectedAnimal = null;
                }

                super.mouseClicked(e);
            }
        });
        buttonDetalhe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(selectedAnimal == null){
                    JOptionPane.showMessageDialog(null, "Selecione um Pet, por favor");
                }else{
                    AnimalDetalhe detalheDialog = new AnimalDetalhe(selectedAnimal.getCodigo());
                }

                super.mouseClicked(e);
            }
        });
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        AnimalLista dialog = new AnimalLista();
        dialog.setSize(600,700);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
