package com.grupp2.sankaskepp.CreateAndSetBoats;

import java.util.ArrayList;

public class ControlOfInput {
    ArrayList<Integer> skipCheck = new ArrayList<Integer>(0);
    int boats = 10;

    public ControlOfInput(){

    }


    public String controlIfHit(String choice, Boat boat){
        String hit = "";

        /*Metod för att initiera all kod ska i teorin skulle befinna sig i main. Vi kan antingen starta den koden i main
        /* Eller så skickar vi in informationen som fås genom socket till den här metoden som i sin tur startar main
        /* ifall att första Char är "i" likt exemplet nedan. Detta kan även lösas på andra sätt.
         */



        if(Character.toString(choice.charAt(0)).equals("i")){
            //Metod där starten av programmet sker
        }
        if(choice.equals("game over")){
            //Metod för att avsluta spelet efter att man har vunnit
            //System.out.println("Avslutar spelet");
        }

        //Tar ut värdena från andra spelarens textsträng och omvandlar dem till en egen textsträng som sedan kan kontrolleras
        String y = Character.toString(choice.charAt(7));
        String x = Character.toString(choice.charAt(8));
        String playerChoice = y + x;

        for(int i = 0; i < boat.getBoats().length; i++){
            for(int j = 0; j < boat.getBoats()[i].getPosition().size(); j++){

                if(boat.getBoats()[i].getPosition().get(j).equals(playerChoice) &&
                        boat.getBoats()[i].getPosition().size() == 1 && !skipCheck.contains(i)) {

                    //metodTillUserInterfaceFörOmvandlingOchÄndring(String playerChoice);

                    boat.getBoats()[i].getPosition().remove(j);
                    hit = "s";
                    j = boat.getBoats()[i].getPosition().size() - 1;
                    i = boat.getBoats().length - 1;

                } else if(boat.getBoats()[i].getPosition().get(j).equals(playerChoice) && !skipCheck.contains(i)){
                    hit = "h";
                    //För att ta bort värdet i arraylisten
                    boat.getBoats()[i].getPosition().remove(j);

                    //Metoden nedan kan vara exakt likadan kod som när man lägger till båtarna men att man
                    //ändrar färgen på den rutan för att kunna se någon skillnad på skärmen

                    //metodTillUserInterfaceFörOmvandlingOchÄndring(String playerChoice);

                    //för att avsluta loopen när vi redan har ett svar
                    j = boat.getBoats()[i].getPosition().size() - 1;
                    i = boat.getBoats().length - 1;


                }
            }
        }



        for(int i = 0; i < boat.getBoats().length; i++){
            if(boat.getBoats()[i].getPosition().size() == 0 && !skipCheck.contains(i)){
                boats -= 1;
                skipCheck.add(i);

                if(boats == 0){
                    hit = "game over";
                    /*Eventuellt en metod som avslutar spelet när man har förlorat. Metoden kan behöva kallas på
                    /* Lite senare så att man hinner skicka iväg informationen innan man avslutar spelet
                    */

                }
            }
        }
        if(hit.equals("")){
            hit = "m";
        }
        return hit;
    }
}
