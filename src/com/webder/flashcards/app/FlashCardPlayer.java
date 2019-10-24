package com.webder.flashcards.app;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.*;

import javax.swing.*;


import com.webder.flashcards.app.FlashCardbuilder.NewMenuItemListener;
import com.webder.flashcards.app.FlashCardbuilder.saveMenuItemListener;


public class FlashCardPlayer {

	private JTextArea display;
	
	private ArrayList<FlashCard> cardlist;
	private Iterator cardIterator;
	private FlashCard currentCard;
	private JFrame frame;
	private JButton showAnwser;
	private boolean isShowAnswer; 

	public FlashCardPlayer() {
		//Build UI
		
		frame = new JFrame("FlashCard Player");
		JPanel myPanel = new JPanel();
		Font myFont = new Font("Helvetica Neue", Font.BOLD, 21);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		display = new JTextArea(10,15);
		display.setFont(myFont);
		
		JScrollPane jScrollPane = new JScrollPane(display);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		showAnwser = new JButton("Show Answer");
		
		//Menu Bar
		JMenuBar menuBar = new JMenuBar();
		JMenu jMenu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.addActionListener(new openMenuItemListener());
						
		jMenu.add(openMenuItem);
		menuBar.add(jMenu);
		
		myPanel.add(jScrollPane);
		myPanel.add(showAnwser);
		showAnwser.addActionListener(new NextCardListener());

		
		frame.setJMenuBar(menuBar);
		frame.setSize(640, 500);
		frame.getContentPane().add(BorderLayout.CENTER, myPanel);
		
		frame.setVisible(true);
	}
	
// Main Function	
	public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new FlashCardPlayer();
				}
			});
	}
	
	// Action Listener for the button
	public class NextCardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(isShowAnswer) {
				display.setText(currentCard.getAnswer());
				showAnwser.setText("next card");
				isShowAnswer=false;
			}else {
				if(cardIterator.hasNext()) {
					showNextCard();
				}else { //No more cards
					display.setText("that was the last card.");
					showAnwser.setEnabled(false);
				}
			}
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

		cardlist = new ArrayList<FlashCard>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(selectedFile)); //File Reader returns a Reader
			String line = null;
			
			while((line=reader.readLine())!= null) {
				makeCard(line);
			
			}
			reader.close();
			
		} catch (Exception e) {
			System.out.println("problems reading the file");
			e.getStackTrace();
		}
		
		cardIterator = cardlist.iterator();
		showNextCard();
	}


	private void showNextCard() {
		currentCard = (FlashCard) cardIterator.next();
		
		display.setText(currentCard.getQuestion());
		showAnwser.setText("Show Anwser");
		isShowAnswer=true;
	}


	public void makeCard(String lineToParse) {
		//String[] result = lineToParse.split("/");
		StringTokenizer result = new StringTokenizer(lineToParse, "/");
		if (result.hasMoreTokens()) {
			
			FlashCard card = new FlashCard(result.nextToken(), result.nextToken());
			cardlist.add(card);
			System.out.println("Made a Card" + card.getQuestion());
			
			
		}
		
	}



}
