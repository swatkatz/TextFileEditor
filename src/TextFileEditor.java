/*
 This file: TextFileEditor.java
 Programmer author: Dhruv Kumar (dkumar1@andrew.cmu.edu)
 Course/Section: 95 712
 Assignment: HW5 - Object-Oriented Programming in Java
 Description: Swing application to develop a simple File text editor and include many features;
 			  If the user chooses ‘Word Count’ option, the total number of words is displayed in the status bar at the bottom;
 			  If the user chooses ‘Word Unique’ option, the total number of unique words is displayed in the status bar;
 			  If the user chooses ‘Word Search’ option, the menu opens further to show a sub-menu with only one option ‘Search New…’.
 			  The user enters inputs the search term in dialog box and all its occurrences are highlighted in the editor with total number of occurrences showing up in the status bar. The text is positioned at the first occurrence of the search term
 			  If the term is not found then the status bar says so, and the text is left where it was before;
 			  The final feature in this version is maintaining a history of last 5 search-strings, if they were found in the text.;
 			  As more terms are searched and found, a list of last 5 terms builds up; history is maintained only till the time the document is open. It is lost after closing the document;
 			  Clicking on any of the last 5 search-terms should trigger the search and highlight its occurrences same as before;		  	  
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;

public class TextFileEditor {
	JFrame jFrameWindow = new JFrame("Text Editor");
	JPanel panel = new JPanel();
	JTextArea jta = new JTextArea(20, 60);
	JScrollPane jsp = new JScrollPane();
	JLabel label = new JLabel();
	JFileChooser fileExplorer = new JFileChooser();
	JMenuBar menuBar = new JMenuBar();
	JMenuItem openItem;
	JMenuItem closeItem;
	JMenuItem exitItem;
	JMenuItem saveItem;
	JMenuItem saveAsItem;
	JMenuItem aboutItem;
	JMenu wordsMenu;
	JMenu searchMenu;
	JMenu oldBookMarkMenu;
	JMenuItem countItem;
	JMenuItem uniqItem;
	JMenuItem searchMenuItem;
	JMenuItem searchMenuItem1;
	JMenuItem oldBookMarkItem1;

	JMenuItem newBookMarkItem;
	JMenuItem deleteBookMarkItem;


	LinkedList<String> addSearchItems = new LinkedList<String>();
	LinkedList<String> addSearchItemsSorted = new LinkedList<String>();
	LinkedList<Integer> bookMarkPosition = new LinkedList<Integer>();
	Highlighter hl = jta.getHighlighter();

	JMenu bookMarkMenu;
	int result;
	String getSearchItems;
	File file = null; // flag for handling file

	public void run() throws IOException {

		jFrameWindow.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		openItem = new JMenuItem("Open...");
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		saveAsItem = new JMenuItem("Save as...");
		fileMenu.add(saveAsItem);
		fileMenu.addSeparator();
		closeItem = new JMenuItem("Close");
		fileMenu.add(closeItem);
		exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		JMenu toolsMenu = new JMenu("Tools");
		menuBar.add(toolsMenu);
		wordsMenu = new JMenu("Words");
		toolsMenu.add(wordsMenu);
		countItem = new JMenuItem("Count");
		wordsMenu.add(countItem);
		uniqItem = new JMenuItem("Unique");
		wordsMenu.add(uniqItem);
		searchMenu = new JMenu("Search");
		wordsMenu.add(searchMenu);
		searchMenuItem = new JMenuItem("Search New...");
		searchMenu.add(searchMenuItem);
		searchMenuItem1 = new JMenuItem("");

		oldBookMarkItem1 = new JMenuItem();
		bookMarkMenu = new JMenu("BookMark");
		toolsMenu.add(bookMarkMenu);
		newBookMarkItem = new JMenuItem("New...");
		bookMarkMenu.add(newBookMarkItem);
		oldBookMarkMenu = new JMenu("Old");
		bookMarkMenu.add(oldBookMarkMenu);
		deleteBookMarkItem = new JMenuItem("Delete....");
		bookMarkMenu.add(deleteBookMarkItem);


		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);

		jta.setEditable(true);
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);
		jta.setFont(new Font("Serif", Font.PLAIN, 20));

		JScrollPane jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // creating the JOptionPane and taking the file name as input. "Citation: concept from Lecture 8.1, slide 11"
		jFrameWindow.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 30));
		jFrameWindow.setSize(800, 500);
		jFrameWindow.add(jsp, BorderLayout.CENTER);
		jFrameWindow.setVisible(true);
		label.setPreferredSize(new Dimension(400, 20));
		jFrameWindow.add(label, BorderLayout.PAGE_END);
		openItem.addActionListener(new Open()); // making use of Innerclasss to register with the listener
//		saveItem.addActionListener(new Save());
//		saveAsItem.addActionListener(new SaveAs());
		closeItem.addActionListener(new Close());
		exitItem.addActionListener(new Exit());
//		aboutItem.addActionListener(new About());
//		countItem.addActionListener(new Count());
//		uniqItem.addActionListener(new UniqueCount());
//		searchMenuItem.addActionListener(new SearchItem());
//		searchMenuItem1.addActionListener(new SearchAgain());
		newBookMarkItem.addActionListener(new NewTagName());
		oldBookMarkItem1.addActionListener(new StoreBookMark());
		deleteBookMarkItem.addActionListener(new Delete());

		jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String filePath = "C:/Users/Dhruv/Desktop/Java Course Materials/"; //default file path name - yet to be given by professor!
		fileExplorer.setCurrentDirectory(new File(filePath)); // 'Citation: From the problem statement (example)'
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt"); // Filter to accept only file with .txt extension
		fileExplorer.setFileFilter(filter);
	}

	public static void main(String[] args) throws IOException {
		TextFileEditor textFileEditor = new TextFileEditor();
		textFileEditor.run();
	}

	class Open implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			hl.removeAllHighlights();
			String getFileName;
			String getFilePath;
			if (e.getSource().equals(openItem)) { // action when user selects open menuitem
				fileExplorer.setDialogTitle("Choose a File to open");
				result = fileExplorer.showOpenDialog(fileExplorer); // opening the open dialogue window 
				if (result == JFileChooser.APPROVE_OPTION) { // when user chooses open option
					file = fileExplorer.getSelectedFile();
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
						searchMenu.removeAll();
						addSearchItems.clear();
						searchMenu.add(searchMenuItem);

						oldBookMarkMenu.removeAll();
						oldBookMarkItem1.removeAll();
						addSearchItems.clear();
						addSearchItemsSorted.clear();
						bookMarkPosition.clear();
					} catch (IOException e1) {
						label.setText("System cannot find " + getFileName);
					}
				}
			}
		}
	}

	class Close implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(closeItem)) { // action when user selects close menu item
				jta.setText(""); // sets text area and label as empty
				label.setText("");
				searchMenu.removeAll(); // removing all the previous search history
				searchMenu.add(searchMenuItem);

				oldBookMarkMenu.removeAll();
				oldBookMarkItem1.removeAll();
				addSearchItems.clear();
				addSearchItemsSorted.clear();
				bookMarkPosition.clear();
				file = null;
			}
		}
	}

	class Exit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) { // action when user selects exit menu item
			if (e.getSource().equals(exitItem)) {
				jFrameWindow.dispose(); // closes the application
			}
		}
	}

	class NewTagName implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(newBookMarkItem)) {
				hl.removeAllHighlights();
				String getSearchPhrase = JOptionPane.showInputDialog(null, "Enter tag name", "Input", JOptionPane.PLAIN_MESSAGE); //getting the search string from the user
				if (getSearchPhrase == null) { // condition when cancel is pressed
					JOptionPane.showMessageDialog(null, "You pressed cancel", "Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if (getSearchPhrase.length() == 0) { // checking if user has entered a string
					JOptionPane.showMessageDialog(null, "Please enter a valid Tag name", "Empty String name", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					addSearchItems.add(getSearchPhrase);
					addSearchItemsSorted.add(getSearchPhrase);
					bookMarkPosition.add(jta.getCaretPosition());
					oldBookMarkMenu.removeAll(); // Remove all menu items from this menu.
					Collections.sort(addSearchItemsSorted, new Comparator<String>() {

						@Override
						public int compare(String o1, String o2) {
							return Collator.getInstance().compare(o1, o2);
						}
					});

					for(int l = 0; l < addSearchItemsSorted.size(); l++) { // dynamically populating the the menu items with the elements present in linkedlist						
						getSearchItems = addSearchItemsSorted.get(l);
						oldBookMarkItem1 = new JMenuItem(getSearchItems);
						oldBookMarkItem1.addActionListener(new StoreBookMark()); // registering the new menu item with the listener
						oldBookMarkMenu.add(oldBookMarkItem1);
					}

				}

			}

		}

	}
	class StoreBookMark implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			hl.removeAllHighlights(); // removing the highlighted phrase
			String getSearchPhrase = ""; 
			JMenuItem jItem = (JMenuItem)e.getSource();
			getSearchPhrase = jItem.getText();
			int countOccurences = 0;
			int getTagPosition = 0;
			try{	
				getTagPosition = bookMarkPosition.get(addSearchItems.indexOf(getSearchPhrase));
				jta.setCaretPosition(getTagPosition);
			}
			catch(IllegalArgumentException iae) {
				label.setText(getSearchPhrase + " has " + countOccurences + " occurences");
			}

			try {
				hl.addHighlight(getTagPosition, getTagPosition + 1, DefaultHighlighter.DefaultPainter);
			} catch (BadLocationException e1) {
				JOptionPane.showMessageDialog(null, "Please enter a valid String name", "Empty or Bad String name ", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	class Delete implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(deleteBookMarkItem)) {
				hl.removeAllHighlights();
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Delete");
				if (addSearchItemsSorted.size() != 0) {
					String input = (String) JOptionPane.showInputDialog(null, "Select bookmark to delete", "Delete Bookmarks", JOptionPane.QUESTION_MESSAGE, null, 
							addSearchItemsSorted.toArray(), addSearchItemsSorted.get(0)
							);
					if (input == null) { // condition when cancel is pressed
						UIManager.put("OptionPane.okButtonText", "OK");
						JOptionPane.showMessageDialog(null, "You pressed cancel", "Input", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//System.out.println("input : " + input );
					int x = addSearchItems.indexOf(input);
					bookMarkPosition.remove(x);
					addSearchItemsSorted.remove(input);
					addSearchItems.remove(input);
					oldBookMarkItem1.removeAll();
					oldBookMarkMenu.removeAll();
					for (int l = 0; l < addSearchItemsSorted.size(); l++) {
						getSearchItems = addSearchItemsSorted.get(l);
						oldBookMarkItem1 = new JMenuItem(getSearchItems);
						oldBookMarkItem1.addActionListener(new StoreBookMark());
						oldBookMarkMenu.add(oldBookMarkItem1);
					}
				}
				else {
					UIManager.put("OptionPane.okButtonText", "OK");
					JOptionPane.showMessageDialog(null, "No bookmark tags to delete!", "Error message", JOptionPane.ERROR_MESSAGE);
				}

			}
		}

	}


}
