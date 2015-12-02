package view;

import javax.swing.JScrollPane;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class TextEditorScrollPane extends JScrollPane {

    public TextEditorScrollPane() {
        super(new TextEditorTextArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

}
