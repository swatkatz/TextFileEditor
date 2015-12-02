package view.jmenu;

import java.awt.event.ActionListener;
import javax.swing.JMenu;

import view.jmenuitem.BaseItem;
import view.jmenuitem.CloseItem;
import view.jmenuitem.ExitItem;
import view.jmenuitem.OpenItem;
import view.jmenuitem.SaveAsItem;
import view.jmenuitem.SaveItem;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class FileMenu extends JMenu {
    private static final String NAME = "File";
    private OpenItem openItem;
    private CloseItem closeItem;
    private SaveAsItem saveAsItem;
    private SaveItem saveItem;
    private ExitItem exitItem;

    public FileMenu() {
        super(NAME);
        openItem = new OpenItem();
        closeItem = new CloseItem();
        saveAsItem = new SaveAsItem();
        saveItem = new SaveItem();
        exitItem = new ExitItem();
        this.add(openItem);
        this.addSeparator();
        this.add(closeItem);
        this.addSeparator();
        this.add(saveAsItem);
        this.addSeparator();
        this.add(saveItem);
        this.addSeparator();
        this.add(exitItem);
        this.addSeparator();
    }

    public void addItemActionListener(BaseItem item, ActionListener listener) {
        item.addActionListener(listener);
    }

    public OpenItem getOpenItem() {
        return openItem;
    }

    public CloseItem getCloseItem() {
        return closeItem;
    }

    public SaveAsItem getSaveAsItem() {
        return saveAsItem;
    }

    public SaveItem getSaveItem() {
        return saveItem;
    }

    public ExitItem getExitItem() {
        return exitItem;
    }
}
