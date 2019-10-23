package com.webder.flashcards.app;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

import javax.swing.*;


import com.webder.flashcards.app.FlashCardbuilder.NewMenuItemListener;
import com.webder.flashcards.app.FlashCardbuilder.saveMenuItemListener;


public class FlashCardPlayer {

	private JTextArea display;
	private JTextArea answer;
	private ArrayList<FlashCard> cardlist;
	private Iterator cardIterator;
	private FlashCard currentCard;
	private int currentCardIndex;
	private JFrame frame;
	private JButton showButton;
	
	public FlashCardPlayer() {
		//Build UI
		
		frame = new JFrame("FlashCard Player");
		JPanel myPanel = new JPanel();
		Font myFont = new Font("Helvetica Neue", Font.BOLD, 21);
		
		display = new JTextArea(10,20);
		display.setFont(myFont);
		
		JScrollPane jScrollPane = new JScrollPane(display);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		showButton = new JButton("Show Answer");
		showButton.addActionListener(new ShowCardListener());
		
		//Menu Bar
		JMenuBar menuBar = new JMenuBar();
		JMenu jMenu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.addActionListener(new openMenuItemListener());
						
		jMenu.add(openMenuItem);
		menuBar.add(jMenu);
		
		myPanel.add(jScrollPane);
		myPanel.add(showButton);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, myPanel);
		frame.setSize(640, 500);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new FlashCardPlayer();
				}
			});
	}
	
	public class ShowCardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}

	}
	
	public class openMenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser file = new JFileChooser();
			file.showOpenDialog(frame);
			loadFile(file.getSelectedFile());
		}

	}

	public void loadFile(File selectedFile) {
		// TODO Auto-generated method stub
		
	}



}
