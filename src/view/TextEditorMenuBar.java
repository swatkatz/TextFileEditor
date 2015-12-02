package view;

import javax.swing.JMenuBar;

import view.jmenu.FileMenu;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class TextEditorMenuBar extends JMenuBar {
    private FileMenu fileMenu;

    public TextEditorMenuBar() {
        initialize();
    }

    private void initialize() {
        fileMenu = new FileMenu();
        this.add(fileMenu);
    }

    public FileMenu getFileMenu() {
        return fileMenu;
    }
}

