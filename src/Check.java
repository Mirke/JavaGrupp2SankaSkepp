package src;

public class Check {
    //En textsträng med den andra spelarens val skickas in
    public static boolean controlHit(String choice){
        boolean hit = false;

        //Tar ut värdena för andra spelarens skott och omvandlar dem till en egen textsträng
        String y = Character.toString(choice.charAt(7));
        String x = Character.toString(choice.charAt(8));
        String playerChoice = y + x;

        //Bara som test för funktionen, detta ska egentligen vara en getter från en textsträng med alla båtens positioner
        String boatPositions = "6c7c8c9c1a2a3a";


        if(boatPositions.contains(playerChoice)){
            hit = true;
            //En metod för att markera rutan som träffad alternativt använda boolean-värdet som returneras för att göra det

        }

        //Alternativ till lösningar
        /*
        char y = choice.charAt(7);
        char x = choice.charAt(8);

        if(y == boatPositions.getY() && x == boatPositions.getX()){
            hit = true;
        /*
        String y1 = Character.toString(y);
        String x1 = Character.toString(x);

        if(y1.equals(boatPositions.getY) && x1.equals(boatPositions.getX)){
            hit = true;
        }
        */


        return hit;
    }
}
