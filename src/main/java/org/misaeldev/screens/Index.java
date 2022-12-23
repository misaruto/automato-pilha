package org.misaeldev.screens;

import org.misaeldev.automato.Automato;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Index extends JFrame{
    private JPanel root;
    private JLabel title;
    private JPanel body;
    private JPanel menu;
    private JButton importarAutomatoBtn;
    private JTextField sentencaInput;
    private JButton executarSentenca;
    private JLabel exibirResultado;
    private JTextPane comoMontarSeuAutomatoTextPane;

    private Automato automato;

    public Index() {
        this.setContentPane(root);
        this.setSize(960,720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        exibirResultado.setForeground(Color.RED);
        exibirResultado.setText("Importe o automato primeiro");

        this.setVisible(true);

        importarAutomatoBtn.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setFileFilter(new FileNameExtensionFilter("Apenas textos","txt"));
            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                automato = new Automato(selectedFile);
                automato.exibir();
                sentencaInput.setEnabled(true);
                sentencaInput.setEditable(true);
                executarSentenca.setEnabled(true);
                exibirResultado.setText("");
            }
        }
        });

        executarSentenca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String sentenca = sentencaInput.getText();
                boolean resultado = automato.verificaSentenca(sentenca);
                if(resultado){
                    exibirResultado.setForeground(Color.GREEN);
                    exibirResultado.setText("Palavra válida");
                }else{
                    exibirResultado.setForeground(Color.RED);
                    exibirResultado.setText("Palavra inválida");
                }
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
