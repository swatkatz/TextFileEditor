package controller;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.actionlistener.OpenActionListener;
import view.TextEditorFrame;
import view.jmenuitem.BaseItem;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class Controller {
    private final TextEditorFrame textEditorFrame = new TextEditorFrame();
    private final JFileChooser fileExplorer = new JFileChooser();

    public Controller() {
        //You could create it's own controller that you can call in this big controller.
        //something like JMenuItemController menuItemController = new JMenuItemController();
        //menuItemController.control();
        String filePath = "C:/Users/Dhruv/Desktop/Java Course Materials/"; //default file path name - yet to be given by professor!
        fileExplorer.setCurrentDirectory(new File(filePath)); // 'Citation: From the problem statement (example)'
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt"); // Filter to accept only file with .txt extension
        fileExplorer.setFileFilter(filter);

        BaseItem openItem = textEditorFrame
                .getTextEditorMenuBar()
                .getFileMenu()
                .getOpenItem();

        textEditorFrame
                .getTextEditorMenuBar()
                .getFileMenu().addItemActionListener(openItem, new OpenActionListener(fileExplorer, textEditorFrame));
    }
}
