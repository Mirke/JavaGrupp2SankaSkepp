package com.grupp2.sankaskepp.Bastian_Tobias_Anna;


import com.grupp2.sankaskepp.CreateAndSetBoats.Boat;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;

public class TestStart {
    public static void main(String[] args) {
        String hej = "h shot 6c";

        Boat boat = new Boat();
        PlaceBoats placeBoats = new PlaceBoats();
        boat.createBoats();


        placeBoats.placeBoats(boat);


        for(int i = 1; i < 11; i++){
            System.out.println();
            for (int j = 1; j < 11; j++){
                System.out.print("\t" + placeBoats.getField()[i][j]);

            }
        }


        for(int i = 0; i < boat.getBoats().length; i ++){
            System.out.println(boat.getBoats()[i].getPosition());

        }



        //hej
        placeBoats.initializeGridArray();

        for(int i = 1; i < 11; i ++){
            System.out.println();
            for (int j = 1; j < 11; j++){
                boolean test = true;
                for(int a = 0; a < boat.getBoats().length; a++){
                    for( int b = 0; b < boat.getBoats()[a].getSize(); b++ ){
                        if(placeBoats.getField()[i][j].equals(boat.getBoats()[a].getPosition().get(b))){
                            System.out.print("\t!!");
                            test = false;

                        }
                    }
                }
                if(test) {
                    System.out.print("\t" + placeBoats.getField()[i][j]);
                }
            }
        }



    }

}
