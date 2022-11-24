package com.grupp2.sankaskepp.Bastian_Tobias_Anna;


import com.grupp2.sankaskepp.CreateAndSetBoats.Fleet;
import com.grupp2.sankaskepp.CreateAndSetBoats.PlaceBoats;

public class TestStart {
    public static void main(String[] args) {
        String hej = "h shot 6c";

        Fleet fleet = new Fleet();
        PlaceBoats placeBoats = new PlaceBoats();



        placeBoats.placeBoats(fleet);


        for(int i = 1; i < 11; i++){
            System.out.println();
            for (int j = 1; j < 11; j++){
                System.out.print("\t" + placeBoats.getField()[i][j]);

            }
        }


        for(int i = 0; i < fleet.getBoats().length; i ++){
            System.out.println(fleet.getBoats()[i].getPosition());

        }



        //hej
        placeBoats.initializeGridArray();

        for(int i = 1; i < 11; i ++){
            System.out.println();
            for (int j = 1; j < 11; j++){
                boolean test = true;
                for(int a = 0; a < fleet.getBoats().length; a++){
                    for( int b = 0; b < fleet.getBoats()[a].getSize(); b++ ){
                        if(placeBoats.getField()[i][j].equals(fleet.getBoats()[a].getPosition().get(b))){
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
