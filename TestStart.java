import CreateAndSetBoats.Boat;
import CreateAndSetBoats.CreateAndSetBoats;

public class TestStart {
    public static void main(String[] args) {

        Boat boat = new Boat();
        CreateAndSetBoats createAndSetBoats = new CreateAndSetBoats();
        boat.createBoats();
        createAndSetBoats.initializeGridArray();
        for(int i = 1; i < 11; i++){
            System.out.println();
            for (int j = 1; j < 11; j++){
                System.out.print("\t" + createAndSetBoats.getField()[i][j]);

            }
        }

        createAndSetBoats.newTest(boat.getBoats());
        for(int i = 1; i < 11; i++){
            System.out.println();
            for (int j = 1; j < 11; j++){
                System.out.print("\t" + createAndSetBoats.getField()[i][j]);

            }
        }
        for(int i = 0; i < 60; i = i + 2) {
            System.out.print("\n" + createAndSetBoats.getTemporaryControl().charAt(i) + createAndSetBoats.getTemporaryControl().charAt(i + 1));
        }

    }
}
