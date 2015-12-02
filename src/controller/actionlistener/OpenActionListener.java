package controller.actionlistener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.Highlighter;

import view.TextEditorFrame;
import view.jmenuitem.BaseItem;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class OpenActionListener implements ActionListener {
    private Highlighter hl;
    private BaseItem openItem;
    private JFileChooser fileExplorer;
    private JTextArea jta;
    private JLabel label;


    public OpenActionListener(JFileChooser fileExplorer, TextEditorFrame textEditorFrame) {
        this.openItem = textEditorFrame.getTextEditorMenuBar().getFileMenu().getOpenItem();
        this.fileExplorer = fileExplorer;
        this.jta = ((JTextArea) textEditorFrame.getTextEditorScrollPane().getViewport().getView());
        this.hl = jta.getHighlighter();
        this.label = textEditorFrame.getLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        hl.removeAllHighlights();
        String getFileName;
        String getFilePath;
        if (e.getSource().equals(openItem)) { // action when user selects open menuitem
            fileExplorer.setDialogTitle("Choose a File to open");
            int result = fileExplorer.showOpenDialog(fileExplorer); // opening the open dialogue window
            if (result == JFileChooser.APPROVE_OPTION) { // when user chooses open option
                File file = fileExplorer.getSelectedFile();
                getFilePath = fileExplorer.getSelectedFile().getPath(); //getting the complete path of the file
                getFileName = fileExplorer.getSelectedFile().getName(); // getting the name of the file
                FileReader fr;
                try {
                    fr = new FileReader(getFilePath);
                    jta.setFont(new Font("Serif", Font.PLAIN, 20));
                    jta.setBackground(Color.WHITE);
                    jta.setBorder(null);
                    jta.read(fr, null); // reading the file chosen by user
                    label.setText("You have opened: " + getFileName + " file");

                    //You can add these in later..
                    /*searchMenu.removeAll();
                    addSearchItems.clear();
                    searchMenu.add(searchMenuItem);

                    oldBookMarkMenu.removeAll();
                    oldBookMarkItem1.removeAll();
                    addSearchItems.clear();
                    addSearchItemsSorted.clear();
                    bookMarkPosition.clear();*/
                } catch (IOException e1) {
                    label.setText("System cannot find " + getFileName);
                }
            }
        }

    }
}
