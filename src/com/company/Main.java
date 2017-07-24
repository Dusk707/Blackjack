package com.company;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public int Card_Picker(int[] dealt_cards) {
        Random rand = new Random();

        int card = rand.nextInt(52 ) + 1;
        for (int i = 0; i < Array.getLength(dealt_cards); i++) {
            if (dealt_cards[i] == 0) {
                dealt_cards[i] = card;
                i = Array.getLength(dealt_cards);
            }
            else if (card == dealt_cards[i]){
                card = rand.nextInt(52 ) + 1;
                i = 0;
            }
        }

        return card;
    }

    public void Number_to_Card(int card_number, char[] card){
        int
                suit = card_number/13,
                value = card_number%13 + 1;

        if (suit == 0)
            card[0] = 'H';
        else if (suit == 1)
            card[0] = 'S';
        else if (suit == 2)
            card[0] = 'C';
        else
            card[0] = 'D';

        if (value < 10){
            card[1] = '0';
            card[2] = (char) ('0' + value);
        }
        else {
            card[1] = '1';
            card[2] = (char) ('0' + value%10);
        }

    }

    public int Score(int card){
        int score = card%13 + 1;

        if (score > 10)
            score = 10;
        else if (score == 1)
            score = 11;

        return score;
    }

    public Main() {
        int
                self_card_1,
                self_card_2,
                self_score,
                dealer_card_1,
                dealer_card_2,
                dealer_score;
        char
                user_response = 'Y';

        int[] dealt_cards;
        dealt_cards = new int[10];

        char[]
                self_card_str_1,
                self_card_str_2,
                dealer_card_str_1,
                dealer_card_str_2;
        self_card_str_1 = new char[3];
        self_card_str_2 = new char[3];
        dealer_card_str_1 = new char[3];
        dealer_card_str_2 = new char[3];


        self_card_1 = Card_Picker(dealt_cards);
        dealer_card_1 = Card_Picker(dealt_cards);
        self_card_2 = Card_Picker(dealt_cards);
        dealer_card_2 = Card_Picker(dealt_cards);

        Number_to_Card(self_card_1, self_card_str_1);
        Number_to_Card(self_card_2, self_card_str_2);
        Number_to_Card(dealer_card_1, dealer_card_str_1);
        Number_to_Card(dealer_card_2, dealer_card_str_2);

        self_score = Score(self_card_1) + Score(self_card_2);
        dealer_score = Score(dealer_card_1) + Score(dealer_card_2);

        System.out.printf("Your cards are %s %s%n", new String(self_card_str_1), new String(self_card_str_2));
        System.out.printf("Your score is %d and the dealer's face up card is %s%n",
                          self_score,
                          new String(dealer_card_str_1));
        Scanner sc = new Scanner(System.in);
        while (user_response == 'Y' && self_score <= 21){
            System.out.printf("Do you want another card? (Y or N)%n");
            user_response = sc.next().charAt(0);
            if (user_response == 'Y'){
                int new_card = Card_Picker(dealt_cards);
                char[] new_card_str;
                new_card_str = new char[3];
                Number_to_Card(new_card, new_card_str);
                self_score += Score(new_card);
                System.out.printf("Your new card is %s and your score is now %d%n",
                                  new String(new_card_str),
                                  self_score);
            }
        }
        if (self_score > 21) {
            System.out.printf("You've exceeded 21. You lose!");
        }
        else {
            System.out.printf("Dealer cards are %s %s and his score is %d%n",
                              new String(dealer_card_str_1),
                              new String(dealer_card_str_2),
                              dealer_score);

            while (dealer_score < 17){
                int new_card = Card_Picker(dealt_cards);
                char[] new_card_str;
                new_card_str = new char[3];
                Number_to_Card(new_card, new_card_str);
                dealer_score += Score(new_card);
                System.out.printf("The dealer's new card is %s and his score is now %d%n",
                                  new String(new_card_str),
                                  dealer_score);
            }

            if (dealer_score > 21)
                System.out.printf("The dealer exceeds 21, you win!");
            else if (self_score == dealer_score)
                System.out.printf("The game is a tie!");
            else if (self_score > dealer_score)
                System.out.printf("You win!");
            else
                System.out.printf("You lose!");
        }
    }
    public static void main(String args[]) {
        new Main();
    }
}
