package com.webder.flashcards.app;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class FlashCardbuilder {
	
	private JTextArea question;
	private JTextArea anwser;
	private ArrayList<FlashCard> cardlist;
	private JFrame jFrame;
	
	
	public FlashCardbuilder() {
		
		cardlist = new ArrayList<FlashCard>();
				
		jFrame = new JFrame("Flash Card Builder");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//Creating Panel
		JPanel myPanel = new JPanel();
		
		// CReating Font
		Font myFont = new Font("Helvetica Neue", Font.BOLD, 21);
		
		question = new JTextArea(6,20); //6 rows and 20 colluns
		question.setLineWrap(true); //pass to 2nd line if its too long
		question.setWrapStyleWord(true);
		question.setFont(myFont);
		
		//JScrollPane - Question
		JScrollPane jScrollPaneQuestion = new JScrollPane(question);
		jScrollPaneQuestion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPaneQuestion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		anwser = new JTextArea(6,20); //6 rows and 20 colluns
		anwser.setLineWrap(true); //pass to 2nd line if its too long
		anwser.setWrapStyleWord(true);
		anwser.setFont(myFont);
		
		//JScrollPane - answer
		JScrollPane jScrollPaneAnwser = new JScrollPane(anwser);
		jScrollPaneAnwser.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPaneAnwser.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//JButton
		JButton nextButton = new JButton("Next Card");
		nextButton.addActionListener(new NextCardListener());
		//JLabel
		JLabel qjLabel = new JLabel("Question");
		JLabel ajLabel = new JLabel("Anwser");
		
		
		//Menu Bar
		JMenuBar menuBar = new JMenuBar();
		JMenu jMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
				
		newMenuItem.addActionListener(new NewMenuItemListener());
		saveMenuItem.addActionListener(new saveMenuItemListener());

				
		jMenu.add(newMenuItem);
		jMenu.add(saveMenuItem);
				
		menuBar.add(jMenu);
				
		//Add things to Pane
		myPanel.add(qjLabel);
		myPanel.add(jScrollPaneQuestion);
		myPanel.add(ajLabel);
		myPanel.add(jScrollPaneAnwser);
		myPanel.add(nextButton);
		jFrame.setJMenuBar(menuBar);
		
		//Getting the data from the panel to the frame
		jFrame.getContentPane().add(BorderLayout.CENTER,myPanel);
		jFrame.setSize(450, 600);
		jFrame.setVisible(true);

	}
	
	
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new FlashCardbuilder();
			}
		});
	}
	
	//Buttons Logic
	//Next Button
	public class NextCardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Create a Flashcard
			FlashCard card = new FlashCard(question.getText(), anwser.getText());
			cardlist.add(card);
			clearCard();
		}

	}
	//Save Button
	public class saveMenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			FlashCard card = new FlashCard(question.getText(), anwser.getText());
			cardlist.add(card);
			
			//Create a File dialog with file chooser
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(jFrame);
			saveFile(fileSave.getSelectedFile());
		}
	
	}
	//New Button
	public class NewMenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	private void clearCard() {
		question.setText("");
		anwser.setText("");
		question.requestFocus(); //put the cursor back to the question jarea
	}


	private void saveFile(File selectedFile) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile)); 
			
			Iterator<FlashCard> cardIterator = cardlist.iterator();
			while(cardIterator.hasNext()) {
				FlashCard card = (FlashCard)cardIterator.next(); //garantia para que o qje sai do iterador é um cartao
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
				
			}
			writer.close();
			
		} catch (Exception e) {
			System.out.println("System couldnt write to file");
		}
	}


}
