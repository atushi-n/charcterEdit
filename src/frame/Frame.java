package frame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Frame extends JFrame implements DocumentListener {

    JTextArea inputArea = new JTextArea();
    JTextArea outArea = new JTextArea();
    JTextField topStr = new JTextField();
    JTextField lastStr = new JTextField();

    JPanel middlePanel = new JPanel();

    public Frame(String title) {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension winSize = new Dimension(scrSize.width / 2, scrSize.height / 2);
        this.setSize(winSize);
        this.setLocationRelativeTo(null);
        this.setFrameLayout();

        outArea.setEditable(false);
        inputArea.getDocument().addDocumentListener(this);
        topStr.getDocument().addDocumentListener(this);
        lastStr.getDocument().addDocumentListener(this);


    }

    private void setFrameLayout() {
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 1));
        container.add(inputArea);
        container.add(middlePanel);
        container.add(outArea);

        middlePanel.setLayout(new GridLayout(2, 2));
        middlePanel.add(new JLabel("先頭に追加する文字"));
        middlePanel.add(topStr);
        middlePanel.add(new JLabel("末尾に追加する文字"));
        middlePanel.add(lastStr);

    }

    private void edit() {

        String[] planeText = inputArea.getText().split("\n"); //入力されたテキストを行で区切った配列として取得

        StringBuilder processedText = new StringBuilder();//メモリ消費を考慮しStringBuilderを使用

        String top = topStr.getText();
        String last = lastStr.getText();

        if (!inputArea.getText().equals("")) {
            for (String str : planeText) {
                processedText.append(top); //append内で連結するあほがいた（自分） https://stackoverflow.com/questions/43108059/warning-in-stringbuffer-and-stringbuilder
                processedText.append(str);
                processedText.append(last);
                processedText.append("\n");

            }
        }
        outArea.setText(processedText.toString());

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        edit();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        edit();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        edit();
    }


}
