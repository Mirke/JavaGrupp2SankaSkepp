package com.grupp2.sankaskepp.CreateAndSetBoats;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.GameBoard;
import com.grupp2.sankaskepp.Remaining.ComputerLogic;

import java.util.ArrayList;
/**
 * Author: Bastian
 */

public class ControlOfInput {
    /*-----------------------------------------------------------------------------------------------------------------
     * Variabler
     ------------------------------------------------------------------------------------------------------------------*/
    ArrayList<Integer> skipCheck = new ArrayList<>(0);
    ArrayList<String> answer = new ArrayList<>(0);
    ComputerLogic computerLogic = new ComputerLogic();
    int boats = 10;
    private GameBoard youBoard, enemyBoard;
    private Fleet myFleet;
    private Fleet yourFleet;
    private String hit = "";
    private String choice = "";
    ArrayList<String> sentPosition = new ArrayList<>();
    int sentArrayControl = 0;
    int answerArrayControl = 0;

    /*-----------------------------------------------------------------------------------------------------------------
     * Konstruktorer
     ------------------------------------------------------------------------------------------------------------------*/
    /*Tobias*/
    public ControlOfInput(GameBoard youBoard, GameBoard enemyBoard, Fleet myFleet, Fleet yourFleet) {
        this.youBoard = youBoard;
        this.enemyBoard = enemyBoard;
        this.myFleet = myFleet;
        this.yourFleet = yourFleet;
    }
    /*Tobias*/
    public ControlOfInput() {

    }

    /*-----------------------------------------------------------------------------------------------------------------
     * Metoder
     ------------------------------------------------------------------------------------------------------------------*/
    public String controlOtherPlayerString(String choice) {

        this.choice = choice;
        hit = "";

        //Tar ut värdena från andra spelarens textsträng och omvandlar dem till en egen textsträng som sedan kan kontrolleras
        String y = Character.toString(choice.charAt(7));
        String x = Character.toString(choice.charAt(8));
        String playerChoice = y.concat(x);

        for (int i = 0; i < myFleet.getBoats().length; i++) {
            for (int j = 0; j < myFleet.getBoats()[i].getPosition().size(); j++) {
                if (myFleet.getBoats()[i].getPosition().get(j).equals(playerChoice) &&
                        myFleet.getBoats()[i].getPosition().size() == 1) {

                    // Tobias { *********
                    //Färgar rutan på egen spelplan
                    youBoard.boatIsHit(playerChoice);
                    // *********** } Tobias

                    //Tar bort värdet ur båtens arraylista och tilldelar returnerande textsträng ett värde
                    myFleet.getBoats()[i].getPosition().remove(j);
                    hit = "s";

                    //Avslutar loopen när vi redan har ett svar
                    j = myFleet.getBoats()[i].getPosition().size() - 1;
                    i = myFleet.getBoats().length - 1;

                } else if (myFleet.getBoats()[i].getPosition().get(j).equals(playerChoice)) {

                    // Tobias { *********
                    //Färgar rutan på egen spelplan
                    youBoard.boatIsHit(playerChoice);
                    // *********** } Tobias


                    //Tar bort värdet ur båtens arraylista och tilldelar returnerande textsträng ett värde
                    myFleet.getBoats()[i].getPosition().remove(j);
                    hit = "h";


                    //Avslutar loopen när vi redan har ett svar
                    j = myFleet.getBoats()[i].getPosition().size() - 1;
                    i = myFleet.getBoats().length - 1;

                }
            }
        }
        if (hit.equals("")) {
            // Tobias { *********
            //Färgar rutan på egen spelplan
            youBoard.boatIsMiss(playerChoice);
            // *********** } Tobias

            //Returnerande textsträng tilldelas ett värde
            hit = "m";
        }



        /*For-loop som kollar om en båts längd är lika med 0. Är den det och den inte gått igenom loopen tidigare så blir integern för
         *antalet båtar en mindre. Blir integern 0 så blir textsträngen game over
         */
        for (int i = 0; i < myFleet.getBoats().length; i++) {
            if (myFleet.getBoats()[i].getPosition().size() == 0 && !skipCheck.contains(i)) {
                boats -= 1;
                skipCheck.add(i);

                if (boats == 0) {
                    hit = "game over";
                }
            }
        }

        //Är det inte game over så kollar den vad som ska skickas
        if (!hit.contains("game over")) {
            answer.add(Character.toString(choice.charAt(0)));
            checkAnswerFromOtherPlayer();
        }

        //Returnerande textsträng lagras och skickas iväg
        sentString(hit);
        return hit;
    }

    public void sentString(String sentString) {
        sentPosition.add(Character.toString(sentString.charAt(7)).concat(Character.toString(sentString.charAt(8))));

    }


    public String checkAnswerFromOtherPlayer() {
        if (answer.get(answerArrayControl).equals("s")) {
            //Färgar rutan på den andra spelplanen med den senast skickade textsträngen
            enemyBoard.boatIsHit( sentPosition.get(sentArrayControl));

            //Kallar på metod för att veta vad man ska svara med
            hit = hit.concat(computerLogic.sForSink(this));

            //Kontrollvariablerna räknas upp
            answerArrayControl++;
            sentArrayControl++;
        } else if (answer.get(answerArrayControl).equals("h")) {

            if (!Character.toString(choice.charAt(0)).equals("i")) {
                hit = hit.concat(computerLogic.hForHit(this));
            }
            //Färgar rutan på den andra spelplanen med den senast skickade textsträngen
            enemyBoard.boatIsHit( sentPosition.get(sentArrayControl));

            //Kontrollvariablerna räknas upp
            answerArrayControl++;
            sentArrayControl++;

        } else if (answer.get(answerArrayControl).equals("m")) {

            if (!Character.toString(choice.charAt(0)).equals("i")) {
                hit = hit.concat(computerLogic.mForMiss(this));
            }
            //Färgar rutan på den andra spelplanen med den senast skickade textsträngen
            enemyBoard.boatIsMiss(sentPosition.get(sentArrayControl));

            //Kontrollvariablerna räknas upp
            answerArrayControl++;
            sentArrayControl++;
        } else {
            //En egen metod om man tar emot i
            if (Character.toString(choice.charAt(0)).equals("i")) {
                hit = hit.concat(computerLogic.ifRecievingi());
            }
            //Kontrollvariabel räknas upp
            answerArrayControl++;
        }
        return hit;
    }

    /*-----------------------------------------------------------------------------------------------------------------
     * Getters och Setters
     ------------------------------------------------------------------------------------------------------------------*/
    public String startRound() {
        String text = computerLogic.iForStartOfRound();
        return text;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public ArrayList<String> getSentPosition() {
        return sentPosition;
    }
}
