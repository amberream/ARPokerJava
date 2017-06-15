package com.amberream.poker;

/**
 * This class represents a playing card.
 * @author Amber Ream
 *
 */
public class Card {
	
	public static final int CLUB = 0;
	public static final int HEART = 1;
	public static final int SPADE = 2;
	public static final int DIAMOND = 3;
		
	private int mSuit;
	private int mValue;
	
	public Card(int suit, int value)
	{
		setSuit(suit);
		setValue(value);
	}

	public int getSuit() {
		return mSuit;
	}

	public void setSuit(int mSuit) {
		this.mSuit = mSuit;
	}

	public int getValue() {
		return mValue;
	}

	public void setValue(int mValue) {
		this.mValue = mValue;
	}
	
}
