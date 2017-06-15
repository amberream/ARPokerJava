package com.amberream.poker.test;

import com.amberream.poker.Card;
import com.amberream.poker.Hand;

/**
 * This class tests the poker hand scoring.
 */
public class PokerTest {
	public static void main(String [] args)
	{
		PokerTest test = new PokerTest();
		test.testFlush();
		test.testFullHouse();
		test.testStrait();
		test.testMultiples();
	}
	
	public void testFlush()
	{
		// royal flush
		Hand hand = new Hand();
		hand.add(new Card(Card.CLUB, 10));
		hand.add(new Card(Card.CLUB, 11));
		hand.add(new Card(Card.CLUB, 12));
		hand.add(new Card(Card.CLUB, 13));
		hand.add(new Card(Card.CLUB, 14));
		System.out.println("royal flush " + (hand.score() == Hand.ROYAL_FLUSH));
		
		// strait flush
		hand = new Hand();
		hand.add(new Card(Card.HEART, 5));
		hand.add(new Card(Card.HEART, 6));
		hand.add(new Card(Card.HEART, 7));
		hand.add(new Card(Card.HEART, 8));
		hand.add(new Card(Card.HEART, 9));
		System.out.println("strait flush " + (hand.score() == Hand.STRAIT_FLUSH));
		
		// regular flush
		hand = new Hand();
		hand.add(new Card(Card.SPADE, 3));
		hand.add(new Card(Card.SPADE, 7));
		hand.add(new Card(Card.SPADE, 4));
		hand.add(new Card(Card.SPADE, 9));
		hand.add(new Card(Card.SPADE, 13));
		System.out.println("flush " + (hand.score() == Hand.FLUSH));
	}
	
	public void testStrait()
	{
		Hand hand = new Hand();
		hand.add(new Card(Card.CLUB, 5));
		hand.add(new Card(Card.HEART, 4));
		hand.add(new Card(Card.DIAMOND, 6));
		hand.add(new Card(Card.DIAMOND, 7));
		hand.add(new Card(Card.CLUB, 3));
		System.out.println("strait " + (hand.score() == Hand.STRAIT));
	}
	
	public void testFullHouse()
	{
		Hand hand = new Hand();
		hand.add(new Card(Card.CLUB, 5));
		hand.add(new Card(Card.HEART, 5));
		hand.add(new Card(Card.HEART, 7));
		hand.add(new Card(Card.DIAMOND, 7));
		hand.add(new Card(Card.SPADE, 7));
		System.out.println("full house" + (hand.score() == Hand.FULL_HOUSE));
		
		hand = new Hand();
		hand.add(new Card(Card.CLUB, 5));
		hand.add(new Card(Card.HEART, 5));
		hand.add(new Card(Card.DIAMOND, 5));
		hand.add(new Card(Card.DIAMOND, 7));
		hand.add(new Card(Card.SPADE, 7));
		System.out.println("full house " + (hand.score() == Hand.FULL_HOUSE));
	}
	
	public void testMultiples()
	{

		Hand hand = new Hand();
		hand.add(new Card(Card.CLUB, 5));
		hand.add(new Card(Card.HEART, 5));
		hand.add(new Card(Card.HEART, 5));
		hand.add(new Card(Card.DIAMOND, 5));
		hand.add(new Card(Card.SPADE, 8));
		System.out.println("four of a kind " + (hand.score() == Hand.FOUR_KIND));
				
		hand = new Hand();
		hand.add(new Card(Card.CLUB, 5));
		hand.add(new Card(Card.HEART, 5));
		hand.add(new Card(Card.HEART, 5));
		hand.add(new Card(Card.DIAMOND, 7));
		hand.add(new Card(Card.SPADE, 8));
		System.out.println("three of a kind " + (hand.score() == Hand.THREE_KIND));

		hand = new Hand();
		hand.add(new Card(Card.CLUB, 2));
		hand.add(new Card(Card.HEART, 2));
		hand.add(new Card(Card.HEART, 7));
		hand.add(new Card(Card.DIAMOND, 7));
		hand.add(new Card(Card.SPADE, 9));
		System.out.println("two pair " + (hand.score() == Hand.TWO_PAIR));

		hand = new Hand();
		hand.add(new Card(Card.CLUB, 2));
		hand.add(new Card(Card.HEART, 2));
		hand.add(new Card(Card.HEART, 5));
		hand.add(new Card(Card.DIAMOND, 7));
		hand.add(new Card(Card.SPADE, 7));
		System.out.println("two pair " + (hand.score() == Hand.TWO_PAIR));
		
		hand = new Hand();
		hand.add(new Card(Card.CLUB, 6));
		hand.add(new Card(Card.HEART, 2));
		hand.add(new Card(Card.HEART, 2));
		hand.add(new Card(Card.DIAMOND, 7));
		hand.add(new Card(Card.SPADE, 7));
		System.out.println("two pair " + (hand.score() == Hand.TWO_PAIR));
		
		hand = new Hand();
		hand.add(new Card(Card.CLUB, 12));
		hand.add(new Card(Card.HEART, 12));
		hand.add(new Card(Card.HEART, 7));
		hand.add(new Card(Card.DIAMOND, 8));
		hand.add(new Card(Card.SPADE, 9));
		System.out.println("one pair " + (hand.score() == Hand.ONE_PAIR));
		
		hand = new Hand();
		hand.add(new Card(Card.CLUB, 5));
		hand.add(new Card(Card.HEART, 5));
		hand.add(new Card(Card.HEART, 6));
		hand.add(new Card(Card.DIAMOND, 7));
		hand.add(new Card(Card.SPADE, 8));
		System.out.println("nothing " + (hand.score() == Hand.NOTHING));
	}
}
