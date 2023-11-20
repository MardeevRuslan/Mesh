package mesh.desktop;

import mesh.mesh.Mesh;
import mesh.svg.CreateSvG;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Desktop {

    private JFrame frame;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextArea textField5;
    private JPanel panel;
    private Button logicButton;


    private void createFields() {
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextArea();
        textField5.setLineWrap(true);
    }

    private void createLogicButton() {
        logicButton = new Button("Рассчитать сетку");
        logicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List input = new List();
                input.add(textField1.getText());
                input.add(textField2.getText());
                input.add(textField3.getText());
                input.add(textField4.getText());
                Mesh mesh = new Mesh(input);
                String result = mesh.calculationMesh();
                try {
                    CreateSvG createSvG = new CreateSvG(mesh);
                    createSvG.create();
                } catch (TransformerException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                textField5.setText(result);
            }
        });
    }



    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(new JLabel("Введите длину стола, мм:"));
        panel.add(textField1);
        panel.add(new JLabel("Введите ширину стола, мм:"));
        panel.add(textField2);
        panel.add(new JLabel("Введите минимальный шаг по длине, мм:"));
        panel.add(textField3);
        panel.add(new JLabel("Введите минимальный шаг по ширине, мм:"));
        panel.add(textField4);
        panel.add(new JLabel("Результат"));
        panel.add(textField5);
        panel.add(logicButton);
        frame.getContentPane().add(panel);
    }

    public void create() {
        frame = new JFrame("Расчёт сетки");
        createFields();
        createLogicButton();
        createPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 800);
        frame.setVisible(true);
    }
}