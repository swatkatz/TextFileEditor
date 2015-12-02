package view;

import java.awt.Font;
import javax.swing.JTextArea;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class TextEditorTextArea extends JTextArea {
    private final static int ROWS = 20;
    private final static int COLS = 60;

    public TextEditorTextArea() {
        super(ROWS, COLS);
        setEditable(true);
        setWrapStyleWord(true);
        setLineWrap(true);
        setFont(new Font("Serif", Font.PLAIN, 20));
    }
}
