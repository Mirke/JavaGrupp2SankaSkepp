package com.grupp2.sankaskepp.CreateAndSetBoats;

import com.grupp2.sankaskepp.Bastian_Tobias_Anna.GameBoard;
import com.grupp2.sankaskepp.Remaining.ComputerLogic;

import java.util.ArrayList;

public class ControlOfInput {
    ArrayList<Integer> skipCheck = new ArrayList<>(0);
    ArrayList<String> answer = new ArrayList<>(0);
    ComputerLogic computerLogic = new ComputerLogic();
    int boats = 10;

    private GameBoard youBoard, enemyBoard;
    private Boat myBoat;
    private Boat serverBoat;

    public ControlOfInput(GameBoard youBoard, GameBoard enemyBoard, Boat myBoat, Boat serverBoat) {
        this.youBoard = youBoard;
        this.enemyBoard = enemyBoard;
        this.myBoat = myBoat;
        this.serverBoat = serverBoat;
    }

    public ControlOfInput() {

    }

    public String controlOtherPlayerString(String choice) {

        String hit = "";

        /*Metod för att initiera all kod ska i teorin skulle befinna sig i main. Vi kan antingen starta den koden i main
        /* Eller så skickar vi in informationen som fås genom socket till den här metoden som i sin tur startar main
        /* ifall att första Char är "i" likt exemplet nedan. Detta kan även lösas på andra sätt.
         */

            answer.add(Character.toString(choice.charAt(0)));
            checkAnswerFromOtherPlayer();


            //Tar ut värdena från andra spelarens textsträng och omvandlar dem till en egen textsträng som sedan kan kontrolleras
            String y = Character.toString(choice.charAt(7));
            String x = Character.toString(choice.charAt(8));
            String playerChoice = y + x;

            for (int i = 0; i < myBoat.getBoats().length; i++) {
                for (int j = 0; j < myBoat.getBoats()[i].getPosition().size(); j++) {

                    if (myBoat.getBoats()[i].getPosition().get(j).equals(playerChoice) &&
                            myBoat.getBoats()[i].getPosition().size() == 1) {

                        hit = hit.concat(computerLogic.sForSink());

                        // Tobias { *********
                        //metodTillUserInterfaceFörOmvandlingOchÄndring(String playerChoice); Här ska rutan bli röd på position "playerChoice" i userInterface
                        youBoard.parceStringShotCoordinates(true, playerChoice);
                        // *********** } Tobias

                        myBoat.getBoats()[i].getPosition().remove(j);
                        hit = "s";
                        j = myBoat.getBoats()[i].getPosition().size() - 1;
                        i = myBoat.getBoats().length - 1;

                    } else if (myBoat.getBoats()[i].getPosition().get(j).equals(playerChoice)) {
                        hit = "h";
                        //För att ta bort värdet i arraylisten

                        hit = hit.concat(computerLogic.hForHit());

                        myBoat.getBoats()[i].getPosition().remove(j);

                        /*Metoden nedan kan vara exakt likadan kod som när man lägger till båtarna men att man
                         *ändrar färgen på den rutan för att kunna se någon skillnad på skärmen*/

                        //metodTillUserInterfaceFörOmvandlingOchÄndring(String playerChoice); Här ska rutan bli röd på position "playerChoice" i userInterface
                        // Tobias { *********
                        //metodTillUserInterfaceFörOmvandlingOchÄndring(String playerChoice); Här ska rutan bli röd på position "playerChoice" i userInterface
                        youBoard.parceStringShotCoordinates(true, playerChoice);
                        // *********** } Tobias

                        //för att avsluta loopen när vi redan har ett svar
                        j = myBoat.getBoats()[i].getPosition().size() - 1;
                        i = myBoat.getBoats().length - 1;


                    }
                }
            }
        if (hit.equals("")) {
            hit = "m";

            hit = hit.concat(computerLogic.mForMiss());

            //Här ska rutan bli blå på position "playerChoice" i userInterface
            // Tobias { *********
            //metodTillUserInterfaceFörOmvandlingOchÄndring(String playerChoice); Här ska rutan bli röd på position "playerChoice" i userInterface
            youBoard.parceStringShotCoordinates(false, playerChoice);
            // *********** } Tobias
        }
        if (Character.toString(choice.charAt(0)).equals("i")) {
            hit = hit.concat(computerLogic.iForStartOfRound());
        }

        for (int i = 0; i < myBoat.getBoats().length; i++) {
                if (myBoat.getBoats()[i].getPosition().size() == 0 && !skipCheck.contains(i)) {
                    boats -= 1;
                    skipCheck.add(i);

                    if (boats == 0) {
                        hit = "game over";
                    /*Eventuellt en metod som avslutar spelet när man har förlorat. Metoden kan behöva kallas på
                    /* Lite senare så att man hinner skicka iväg informationen innan man avslutar spelet
                    */

                    }
                }
            }


        return hit;
    }

    ArrayList<String> sentPosition = new ArrayList<>();

    public void sentString(String sentString) {
        sentPosition.add(Character.toString(sentString.charAt(7)).concat(Character.toString(sentString.charAt(8))));

    }

    int sentArrayControl = 0;
    int answerArrayControl = 0;

    public void checkAnswerFromOtherPlayer() {
            if (answer.get(answerArrayControl).equals("h") || answer.get(answerArrayControl).equals("s")) {
                //metod till tobias kod, färg blir röd för den andra spelarens plan genom metod(sentPosition.get(sentArrayControl)
                // vet inte om gameBoard här måste vara under sentArrayControl???
                enemyBoard.parceStringShotCoordinates(true, sentPosition.get(sentArrayControl));
                answerArrayControl++;
                sentArrayControl++;
            } else if (answer.get(answerArrayControl).equals("m")) {
                //metod till tobias kod, färg blir blå för den andra spelarens plan genom metod(sentPosition.get(sentArrayControl)
                enemyBoard.parceStringShotCoordinates(false, sentPosition.get(sentArrayControl));
                answerArrayControl++;
                sentArrayControl++;
            }
            else{
                System.out.println("inget");
                answerArrayControl++;
            }
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }
}
