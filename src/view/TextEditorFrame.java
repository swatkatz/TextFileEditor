package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class TextEditorFrame extends JFrame {
    private static final String NAME = "Text Editor";
    private TextEditorScrollPane textEditorScrollPane;
    private TextEditorMenuBar textEditorMenuBar;
    private JLabel label = new JLabel();

    public TextEditorFrame() {
        super(NAME);
        initilaze();
        setFrameSpecs();
    }

    private void initilaze() {
        textEditorScrollPane = new TextEditorScrollPane();
        textEditorMenuBar = new TextEditorMenuBar();
        label.setPreferredSize(new Dimension(400, 20));
        this.add(textEditorScrollPane, BorderLayout.CENTER);
        this.setJMenuBar(textEditorMenuBar);
        this.add(label, BorderLayout.PAGE_END);
    }

    private void setFrameSpecs() {
        this.setLayout(new BorderLayout());
        this.setSize(800, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public TextEditorScrollPane getTextEditorScrollPane() {
        return textEditorScrollPane;
    }

    public TextEditorMenuBar getTextEditorMenuBar() {
        return textEditorMenuBar;
    }

    public JLabel getLabel() {
        return label;
    }
}
