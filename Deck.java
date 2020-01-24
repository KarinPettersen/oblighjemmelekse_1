package com.karin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Deck {

     public final ArrayList <Card> cards = new ArrayList<>();
     private ArrayList<Card> shuffleCards;

    /**
     *Initializes all the methods in the class
     * */
    public Deck()
        {
            createDeck();
            //printDeck();
            Collection<Card> randomCard = assign(6);
            //System.out.println(randomCard);
            printResults(shuffleCards);
            printSpades();
            collectHearts();
            printHearts();
            printTurnIntoColor();
            printValue();
            printQueenSpade();
            printPokerFlush();
        }


     /**
      * The method creates the deck.
      * */
    public void createDeck()
        {
            for (int index = 1; index <= 13; index ++) {
                cards.add(new Card('C', index));
                cards.add(new Card('D', index));
                cards.add(new Card('H', index));
                cards.add(new Card('S', index));
            }

        }
    /**
     * The method prints the deck.
     */

    public void printDeck(){
       for(Card card: this.cards) {

           card.getDetails();
       }
       System.out.println();
       System.out.println("Amount of cards: " + this.cards.size());

    }

    /**
     * The method shuffles the deck and picks the deciede amount of cards (6).
     * @param n, the amount of cards you have.(6)
     * @return shuffleCards, is the list of the amount of cards you have.
     */

    public Collection<Card> assign (int n) {
        Collections.shuffle(cards);
       shuffleCards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            shuffleCards.add(cards.get(i));
        }

        return shuffleCards;

    }

    /**
     * The methods prints your cards.
     * @param cards
     */
    public void printResults(ArrayList<Card> cards)
    {
        System.out.println("Your cards:");
        cards.stream().forEach(s -> s.getDetails());
    }

    /**
     * The method filters out the spades on your hand.
     */
    public void printSpades(){
        ArrayList<Card> spades = new ArrayList<>();
        shuffleCards.stream().filter((card-> card.getSuit() == 'S')).forEach(spades::add);
        System.out.println("Spades:");
        for (Card tempSpades: spades){
            tempSpades.getDetails();
        }

    }

    /**
     * Filters the hearts on your hand and collects all hearts in a list.
     * @return hearts
     */
    public ArrayList<Card> collectHearts(){
        ArrayList<Card> hearts = new ArrayList<>();
        shuffleCards.stream().filter((card-> card.getSuit() == 'H')).forEach(hearts::add);
        return hearts;
    }

    /**
     * Prints all the hearts you have on your hand.
     */
    public void printHearts(){
        System.out.println("Hearts:");
        List<Card> collectedCards = collectHearts();
        for (Card tempHearts: collectedCards){
            tempHearts.getDetails();
        }
    }

    /**
     *
     * @param cards
     * @return the colors of the cards that's selected
     */
    public static List<String> turnIntoColor(ArrayList<Card> cards) {

        return cards.stream().map(card ->

                (card.getSuit() == 'H' || card.getSuit() == 'D') ? "RED" : "BLACK"

        ).collect(Collectors.toList());

    }

    /**
     * The method prints the only the color of your cards. (Black or red)
     */
    public void printTurnIntoColor(){
        System.out.println(turnIntoColor(shuffleCards));
    }

    /**
     *
     * @param cards
     * @return the sum of all the cards faces added.
     */
    public int getValueOfCards(ArrayList<Card> cards){
        return shuffleCards.stream().reduce(0, (subtotal, card) -> subtotal + card.getFace(), Integer::sum);
    }

    /**
     * The method prints the value of your cards.
     */
    public void printValue(){
        System.out.print("Value of your cards: ");
        System.out.println(getValueOfCards(shuffleCards));
    }

    /**
     *
     * @return checks if you have the queen spade.
     */
    public boolean queenSpade(){
        return shuffleCards.stream().anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
    }

    /**
     * The method prints a message if you have the queen of spade or not.
     */
    public void printQueenSpade(){
        if(queenSpade() == true){
            System.out.println("The queen of spades is on your hand.");
        }
        else {
            System.out.println("You don't have the queen of spades.");
        }
    }

    /**
     * The method checks if you have a flush (6) or not.
     * @return pokerFlushExist
     */
    public boolean pokerFlush(){
        ArrayList<Card> red = new ArrayList<>();
        ArrayList<Card> black = new ArrayList<>();
        boolean pokerFlushExist;
        shuffleCards.stream().filter((card-> card.getSuit() == 'S' || card.getSuit() == 'C')).forEach(black::add);
        shuffleCards.stream().filter((card-> card.getSuit() == 'H' || card.getSuit() == 'D')).forEach(red::add);
        if(red.size() >= 6 || black.size() >=6){
            pokerFlushExist = true;
        }
        else{
            pokerFlushExist = false;
        }
        return pokerFlushExist;
    }

    /**
     * The method prints out a message if you have flush or not.
     */
    public void printPokerFlush(){
        if(pokerFlush() == true){
            System.out.println("You have a flush.");
        }
        else{
            System.out.println("You don't have a flush.");
        }
    }
}

