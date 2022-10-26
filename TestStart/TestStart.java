package TestStart;

import CreateAndSetBoats.Boat;
import CreateAndSetBoats.PlaceBoats;

public class TestStart {
    public static void main(String[] args) {
        String hej = "h shot 6c";

        Boat boat = new Boat();
        PlaceBoats placeBoats = new PlaceBoats();
        boat.createBoats();
        placeBoats.initializeGridArray();
        for(int i = 1; i < 11; i++){
            System.out.println();
            for (int j = 1; j < 11; j++){
                System.out.print("\t" + placeBoats.getField()[i][j]);

            }
        }

        placeBoats.newTest(boat.getBoats());
        for(int i = 1; i < 11; i++){
            System.out.println();
            for (int j = 1; j < 11; j++){
                System.out.print("\t" + placeBoats.getField()[i][j]);

            }
        }
        for(int i = 0; i < 60; i = i + 2) {
            System.out.print("\n" + placeBoats.getTemporaryControl().charAt(i) + placeBoats.getTemporaryControl().charAt(i + 1));
        }

        for(int i = 0; i < boat.getBoats().length; i ++){
            System.out.println(boat.getBoats()[i].getPosition());

        }

    }

}
