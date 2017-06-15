package com.amberream.poker;

import java.util.Vector;

/**
 * This class represents a deck of playing cards.
 * 
 * @author Amber Ream
 *
 */
public class Deck {

	private Vector<Card> cards;
	
	public Deck()
	{
		cards = new Vector<Card>(52);
		for (int suit = 0; suit < 4; suit++)
		{
			for (int value = 2; value < 15; value++)
			{
				cards.add(new Card(suit, value));
			}
		}
	}
	
	/**
	 * Deals a hand of five cards.
	 * @return a hand of 5 cards.
	 */
	public Hand deal()
	{
		Hand hand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			hand.add(dealCard());
		}
		return hand;
	}
	
	/**
	 * Deal a single card from the deck.
	 * @return a single card from the deck.
	 */
	public Card dealCard()
	{
		return cards.remove((int) (Math.random() * (cards.size() -1)));
	}
}
