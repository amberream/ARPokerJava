package com.amberream.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class represents a hand of 5 playing cards.
 * 
 * @author Amber Ream
 *
 */
public class Hand {

	public static final int ROYAL_FLUSH = 250;
	public static final int STRAIT_FLUSH = 50;
	public static final int FOUR_KIND = 25;
	public static final int FULL_HOUSE = 9;
	public static final int FLUSH = 6;
	public static final int STRAIT = 4;
	public static final int THREE_KIND = 3;
	public static final int TWO_PAIR = 2;
	public static final int ONE_PAIR = 1;
	public static final int NOTHING = 0;
	
	private List<Card> mCards;
	
	public Hand()
	{
		mCards = new ArrayList<Card>();
	}
	
	/**
	 * Add a new Card to the hand.
	 * @param card The card to add to the hand.
	 */
	public void add(Card card)
	{
		mCards.add(card);
	}
	
	/**
	 * Remove the card in the given position from the hand.
	 * @param position The position of the card to remove from the hand.
	 */
	public void remove(int position)
	{
		mCards.remove(position);
	}
	
	/**
	 * Insert a card into the hand at the given position.
	 * @param card The card to insert.
	 * @param position The position to insert the card.
	 */
	public void insert(Card card, int position)
	{
		mCards.add(position, card);
	}
	
	/**
	 * Get the card at the given position.
	 * @param position The position of the card.
	 * @return the card at the given position.
	 */
	public Card getCard(int position)
	{
		return mCards.get(position);
	}
	
	/**
	 * Get a list of all cards in the hand.
	 * @return a list of all cards in the hand.
	 */
	public List<Card> getCards()
	{
		return mCards;
	}
	
	/**
	 * Sort the cards by their value.
	 */
	private void sort()
	{
		Collections.sort(mCards, new Comparator<Card>(){

			@Override
			public int compare(Card o1, Card o2) {
				if (o1.getValue() < o2.getValue())
				{
					return -1;
				}
				else if (o1.getValue() > o2.getValue())
				{
					return 1;
				}
				else
				{
					return 0;
				}
			}
			
		});
	}
	
	/**
	 * Scores the hand.  The returned value is a number representing the poker hand.
	 * One of Hand.FLUSH, Hand.FOUR_KIND, Hand.FULL_HOUSE, Hand.NOTHING, Hand.ONE_PAIR,
	 * Hand.ROYAL_FLUSH, Hand.STRAIT, Hand.STRAIT_FLUSH, Hand.THREE_KIND, Hand.TWO_PAIR.
	 * @return The poker hand score.
	 */
	public int score()
	{
		sort();
		boolean strait = isStrait();
		boolean flush = isFlush();
		
		if (strait && flush)
		{
			if (mCards.get(0).getValue() == 10)
			{
				return ROYAL_FLUSH;
			}
			else
			{
				return STRAIT_FLUSH;
			}
		}
		if (flush)
		{
			return FLUSH;
		}
		if (strait)
		{
			return STRAIT;
		}
		
		int count = 1;
		int i;
		// look for multiples
		for (i = 0; i < 4; i++)
		{
			if (mCards.get(i).getValue() == mCards.get(i + 1).getValue())
			{
				count++;
			}
			else if (count > 1)
			{
				break;
			}
		}
		
		if (count == 4)
		{
			return FOUR_KIND;
		}
		else if (count == 1)
		{
			return NOTHING;
		}
		
		int count2 = 1;
		// count is 2 or 3, we might have a full house or two pair
		for (int j = i + 1; j < 4; j++)
		{
			if (mCards.get(j).getValue() == mCards.get(j + 1).getValue())
			{
				count2++;
			}
			else if(count2 > 1)
			{
				break;
			}
		}
		
		if (count2 > 1)
		{
			if (count == 3 || count2 == 3)
			{
				return FULL_HOUSE;
			}
			else
			{
				return TWO_PAIR;
			}
		}
		
		if (count == 3 || count2 == 3)
		{
			return THREE_KIND;
		}
		
		// a single pair only counts if its jacks or better
		if (mCards.get(i).getValue() > 10)
		{
			return ONE_PAIR;
		}
		else
		{
			return NOTHING;
		}
		
	}

	private boolean isFlush() {
		return mCards.get(0).getSuit() == mCards.get(1).getSuit() &&
				mCards.get(1).getSuit() == mCards.get(2).getSuit() &&
				mCards.get(2).getSuit() == mCards.get(3).getSuit() &&
				mCards.get(3).getSuit() == mCards.get(4).getSuit();
	}

	private boolean isStrait() {
		return mCards.get(0).getValue() + 1 == mCards.get(1).getValue() &&
				mCards.get(1).getValue() + 1 == mCards.get(2).getValue() &&
				mCards.get(2).getValue() + 1 == mCards.get(3).getValue() &&
				mCards.get(3).getValue() + 1 == mCards.get(4).getValue();
	}
}
