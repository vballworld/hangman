package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //count user attempts & create boolean for end of game; guessedLetters will track char inputs from user
        int userAttempts = 0;
        boolean userWins;
        String guessedLetters = "";

        //computer chooses word from list & stores it into "chosenWord"
        String listOfWords[] = {"chrome", "amazon", "netflix", "sonic", "dairy", "innovate"};
        Random rand = new Random();
        int randomNumber = rand.nextInt(6) + 0;
        String chosenWord = listOfWords[randomNumber];

        //create answer key -- "answerArray" -- which contains the chosenWord
        char answerArray[] = new char[chosenWord.length()];
        for (int k = 0; k < chosenWord.length(); k++) {
            answerArray[k] = chosenWord.charAt(k);
        }

        //"userArray" will track game's progress -- user wins if userArray matches contents of "answerArray"
        char userArray[] = new char[chosenWord.length()];

        //print the problem & create scanner to read char input from user
        System.out.println("Welcome to Hangman!");
        for (int i = 0; i < chosenWord.length(); i++) {
            System.out.print("___ ");
        }
        System.out.println("");
        System.out.println("");
        Scanner scanner = new Scanner(System.in);

        //repeat loop until user gets the answer or runs out of attempts
        do {
            System.out.println("Guess a letter: ");
            char userInput = Character.toLowerCase(scanner.next().charAt(0)); //convert inputs to lowercase

            //print warning if user enters a duplicate letter
            if (guessedLetters.contains(userInput + "")) {
                System.out.println("You guessed that already...");
                continue;
            }
            guessedLetters += userInput; //add userInput into list of guessed letters

            //print warning for incorrect guesses
            if (!chosenWord.contains(userInput + "")) {
                System.out.println("Wrong! " + "'" + userInput + "'" + " is not there. Try again.");
                userAttempts++; //add to userAttempts for each wrong answer
            }

            //update userArray to reveal correctly guessed letter(s)
            for (int i=0; i < chosenWord.length(); i++) {
                if (chosenWord.charAt(i) == userInput) {
                    userArray[i] = userInput;
                }
            }

            //print updated userArray
            for (int j=0; j < chosenWord.length(); j++) {
                if (userArray[j] > 0) {
                    System.out.print(userArray[j] + "   ");
                }
                else {
                    System.out.print("___ ");
                }
            }
            System.out.println("");

            //print win message if userArray == answerArray
            userWins = Arrays.equals(answerArray, userArray);
            if (userWins) {
                System.out.println("Congratulations! You win!");
                break;
            }

            //notify user of remaining attempts
            System.out.println("You have " + (9 - userAttempts) + " attempts left.");

            //print loss message if user attempts run out & show answer
            if (userAttempts == 9) {
                System.out.println("Sorry, you lost. The word was:");
                System.out.println(chosenWord);
            }


        } while (userAttempts <= 8);
    }
}
