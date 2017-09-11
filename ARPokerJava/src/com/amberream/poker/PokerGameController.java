package com.amberream.poker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * The controller for the poker game.  Handles the game state and all interactions with the UI.
 * @author Amber Ream
 *
 */
public class PokerGameController implements MouseListener, ActionListener{
	
	private static final int INIT_CASH;
	private static final HashMap<Integer, String> SCORE_MAP;
	private PokerGameFrame mGameFrame;
	private int mBet;
	private Deck mDeck;
	private Hand mHand;
	private int mCash;
	
	static {
		INIT_CASH = 100;
		SCORE_MAP = buildScoreMap();
	}
	
	public PokerGameController()
	{
		mGameFrame = new PokerGameFrame();
		
		// set up MouseListeners
		for (int i = 0; i < PokerGame.NUM_CARDS; i++)
		{
			mGameFrame.getJlCard(i).addMouseListener(this);
		}
		
		// set up ActionListeners
		mGameFrame.getJbBet1().addActionListener(this);
		mGameFrame.getJbBet5().addActionListener(this);
		mGameFrame.getJbBet10().addActionListener(this);
		mGameFrame.getJbBet20().addActionListener(this);
		mGameFrame.getJbBet50().addActionListener(this);
		mGameFrame.getJbBet100().addActionListener(this);
		mGameFrame.getJbDraw().addActionListener(this);

		mGameFrame.getJbDraw().setEnabled(false);
		
		resetHolds();
		
		mCash = INIT_CASH;
		updateCashUI();
	}

	private static HashMap<Integer, String> buildScoreMap() {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(Hand.FLUSH, "Flush");
		map.put(Hand.FOUR_KIND, "Four of a Kind");
		map.put(Hand.FULL_HOUSE, "Full House");
		map.put(Hand.ONE_PAIR, "One Pair");
		map.put(Hand.ROYAL_FLUSH, "Royal Flush");
		map.put(Hand.STRAIT, "Strait");
		map.put(Hand.STRAIT_FLUSH, "Strait Flush");
		map.put(Hand.THREE_KIND, "Three of a Kind");
		map.put(Hand.TWO_PAIR, "Two Pair");
		map.put(Hand.NOTHING, "");
		return map;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mGameFrame.getJbBet1())
		{
			bet(1);
		}
		else if (e.getSource() == mGameFrame.getJbBet5())
		{
			bet(5);
		}
		else if (e.getSource() == mGameFrame.getJbBet10())
		{
			bet(10);
		}
		else if (e.getSource() == mGameFrame.getJbBet20())
		{
			bet(20);
		}
		else if (e.getSource() == mGameFrame.getJbBet50())
		{
			bet(50);
		}
		else if (e.getSource() == mGameFrame.getJbBet100())
		{
			bet(100);
		}
		else if (e.getSource() == mGameFrame.getJbDraw())
		{
			draw();
		}
	}

	private void bet(int bet) {
		mBet = bet;
		mCash -= bet;
		clearDisplay();
		updateCashUI();
		resetHolds();
		deal();
		toggleButtons();
	}
	
	private void clearDisplay() {
		mGameFrame.getJlResult().setText("");
	}

	private void toggleButtons() {
		mGameFrame.getJbDraw().setEnabled(!mGameFrame.getJbDraw().isEnabled());
		boolean enabled = !mGameFrame.getJbDraw().isEnabled();
		
		mGameFrame.getJbBet1().setEnabled(enabled && mCash >= 1);
		mGameFrame.getJbBet5().setEnabled(enabled && mCash >= 5);
		mGameFrame.getJbBet10().setEnabled(enabled && mCash >= 10);
		mGameFrame.getJbBet20().setEnabled(enabled && mCash >= 20);
		mGameFrame.getJbBet50().setEnabled(enabled && mCash >= 50);
		mGameFrame.getJbBet100().setEnabled(enabled && mCash >= 100);
	}

	private void updateCashUI() {

		mGameFrame.getJlCash().setText("" + mCash);
	}

	// deal a new hand and update the cards in the ui
	private void deal()
	{
		mDeck = new Deck();
		mHand = mDeck.deal();
		
		updateCardUI();
	}

	private void updateCardUI() {
		for (int i = 0; i < PokerGame.NUM_CARDS; i++)
		{
			mGameFrame.getJlCard(i).setIcon(new ImageIcon(getClass().getResource(
					"/images/" + mHand.getCard(i).getValue() + "_" + mHand.getCard(i).getSuit() + ".png")));
		}
	}

	// draw new cards to replace cards that are not held and update the cards in the ui
	private void draw() {
		for (int i = 0; i < PokerGame.NUM_CARDS; i++)
		{
			if (!mGameFrame.getJlHold(i).getText().equals(PokerGameFrame.HOLD))
			{
				mHand.remove(i);
				mHand.insert(mDeck.dealCard(), i);
			}
		}
		updateCardUI();
		resetHolds();
		int score = mHand.score();
		mCash += (mBet * score);

		// display the result
		mGameFrame.getJlResult().setText(SCORE_MAP.get(score));
		
		if (mCash <= 0)
		{
			int res = JOptionPane.showConfirmDialog(mGameFrame, "Game over. Play again?", "Play Again", JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION)
			{
				mCash = 100;
			}
		}
		
		updateCashUI();
		toggleButtons();
	}

	// hide the "hold" labels
	private void resetHolds() {
		for (int i = 0; i < PokerGame.NUM_CARDS; i++)
		{
			mGameFrame.getJlHold(i).setText(PokerGameFrame.SPACE);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// when a card is clicked toggle the hold label
		// but only allow this after betting (not after drawing cards)
		if (!mGameFrame.getJbDraw().isEnabled())
		{
			return;
		}
		for (int i = 0; i < PokerGame.NUM_CARDS; i++)
		{
			if (e.getSource() == mGameFrame.getJlCard(i))
			{
				toggleHold(i);
				break;
			}
		}
	}
	
	private void toggleHold(int position)
	{
		mGameFrame.getJlHold(position).setText(mGameFrame.getJlHold(position).getText().equals(PokerGameFrame.SPACE) ? PokerGameFrame.HOLD : PokerGameFrame.SPACE);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
