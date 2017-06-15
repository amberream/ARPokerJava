package com.amberream.poker;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PokerGameFrame extends JFrame{
		
	static final String HOLD = "HOLD";

	private static final float FONT_SIZE = 20f;

	public static final String SPACE = " ";

	private JLabel [] jlCard;
	private JLabel [] jlHold;
	
	private JLabel jlCash;
	
	private JButton jbBet1;
	private JButton jbBet5;
	private JButton jbBet10;
	private JButton jbBet20;
	private JButton jbBet50;
	private JButton jbBet100;
	
	private JButton jbDraw;
	
	public PokerGameFrame()
	{
		// set up the UI
		JPanel mainPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/Rules.png"))), c);
		
		c.gridx = 1;
		c.gridwidth = 3;
		mainPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/ARPoker.png"))), c);

		c.gridx = 4;
		c.gridwidth = 1;
		mainPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/diamond.png"))), c);
		
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		for (int i = 0; i < PokerGame.NUM_CARDS; i++)
		{
			c.gridx = i;
			mainPanel.add(getJlCard(i), c);
		}
		
		// panel that contains all the "hold" indicators
		c.gridy = 2;
		for (int i = 0; i < PokerGame.NUM_CARDS; i++)
		{
			c.gridx = i;
			mainPanel.add(getJlHold(i), c);
		}
		
		JPanel cashPanel = new JPanel();
		JLabel cashLabel = createLabel("Cash: $");
		cashLabel.setFont(cashLabel.getFont().deriveFont(50));
		cashPanel.add(cashLabel);
		cashPanel.add(getJlCash());
		
		c.insets = new Insets(5, 5, 15, 5);
		c.gridx = 0;
		c.gridy = 3;
		mainPanel.add(cashPanel, c);
			
		// panel that contains all the buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(createLabel("Bet:"));
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(getJbBet1());
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(getJbBet5());
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(getJbBet10());
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(getJbBet20());
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(getJbBet50());
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(getJbBet100());
		
		// set up the frame
		c.gridx = 1;
		c.gridwidth = 3;
		mainPanel.add(buttonPanel, c);
		
		c.gridx = 4;
		c.gridwidth = 1;
		mainPanel.add(getJbDraw(), c);
		
		setTitle("AR Poker");
		setContentPane(mainPanel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JLabel getJlCard(int position) {
		if (jlCard == null)
		{
			jlCard = new JLabel [PokerGame.NUM_CARDS];
			for (int i = 0; i < PokerGame.NUM_CARDS; i++)
			{
				jlCard[i] = new JLabel(new ImageIcon(getClass().getResource("/images/back.png")));
			}
		}
		return jlCard[position];
	}
	
	public JLabel getJlHold(int position)
	{
		if (jlHold == null)
		{
			jlHold = new JLabel [5];
			for (int i = 0; i < PokerGame.NUM_CARDS; i++)
			{
				jlHold[i] = createLabel(HOLD);
				jlHold[i].setForeground(Color.red);
			}
		}
		return jlHold[position];
	}

	public JLabel getJlCash() {
		if (jlCash == null)
		{
			jlCash = createLabel("");
			jlCash.setFont(jlCash.getFont().deriveFont(FONT_SIZE));
		}
		return jlCash;
	}

	public JButton getJbBet1() {
		if (jbBet1 == null)
		{
			jbBet1 = createButton("$1");
		}
		return jbBet1;
	}

	public JButton getJbBet5() {
		if (jbBet5 == null)
		{
			jbBet5 = createButton("$5");
		}
		return jbBet5;
	}

	public JButton getJbBet10() {
		if (jbBet10 == null)
		{
			jbBet10 = createButton("$10");
		}
		return jbBet10;
	}

	public JButton getJbBet20() {
		if (jbBet20 == null)
		{
			jbBet20 = createButton("$20");
		}
		return jbBet20;
	}

	public JButton getJbBet50() {
		if (jbBet50 == null)
		{
			jbBet50 = createButton("$50");
		}
		return jbBet50;
	}

	public JButton getJbBet100() {
		if (jbBet100 == null)
		{
			jbBet100 = createButton("$100");
		}
		return jbBet100;
	}

	public JButton getJbDraw() {
		if (jbDraw == null)
		{
			jbDraw = createButton("Draw");
		}
		return jbDraw;
	}
	
	// create a new button with a large font
	private JButton createButton(String text)
	{
		JButton button = new JButton(text);
		button.setFont(button.getFont().deriveFont(FONT_SIZE));
		return button;
	}
	
	// create a new label with a large font
	private JLabel createLabel(String text)
	{
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(FONT_SIZE));
		return label;
	}

}
