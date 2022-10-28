package CreateAndSetBoats;
import java.util.ArrayList;
import java.util.Random;

public class PlaceBoats {
    private String[][] field = new String[12][12];
    String temporaryControl = "";

    Random random = new Random();

    int y;
    int x;
    boolean horisontal;


    public void newTest(Boat []boats) {


        for (int a = 0; a < boats.length;) {


            y = random.nextInt(10) + 1;
            x = random.nextInt(10) + 1;
            horisontal = random.nextBoolean();


            //Minus 1 på i för att få med kordinaten som ligger innan båtens början och +1 för ett efter slutet
            try {
                for (int i = 0; i < boats[a].getSize();) {

                    if(horisontal){
                        if (!field[y][x + i].equals("!!") && (x + i) < 11/*&& !field[y-1][x + i].equals("0") && !field[y+1][x+i].equals("0")*/) {

                            System.out.println("inne i kontrolloopen horisontell");
                            if (i == boats[a].getSize() -1 ) {
                                System.out.println("inne i utplaneringsloopen för båt " + a);
                                //try {
                                for (int j = 0; j < boats[a].getSize(); j++) {

                                    //Temporary control
                                    temporaryControl = temporaryControl.concat(field[y][x +j]);
                                    System.out.println("Lägger till båten");

                                    //Lägger till koordinaten som ett objekt i båten
                                    boats[a].getPosition().add(field[y][x + j]);

                                    field[y][x + j] = "!!";
                                    field[y - 1][x + j] = "!!";
                                    field[y + 1][x + j] = "!!";

                                    if (j == 0) {
                                        field[y][x + j] = "!!";
                                        field[y - 1][x - 1] = "!!";
                                        field[y + 1][x - 1] = "!!";
                                    }
                                    if(j == boats[a].getSize() - 1){
                                        field[y][x + j + 1] = "!!";
                                        field[y - 1][x + j + 1] = "!!";
                                        field[y + 1][x + j + 1] = "!!";
                                    }



                                }


                                System.out.println("Nästa båt");
                                a++;
                                i++;

                            }
                            i++;
                        } else {
                            y = random.nextInt(10) + 1;
                            x = random.nextInt(10) + 1;
                            horisontal = random.nextBoolean();
                            i = 0;
                        }


                    }
                    if (!horisontal) {
                        if (!field[y + i][x].equals("!!") && (y + i) < 11/*&& !field[y-1][x + i].equals("0") && !field[y+1][x+i].equals("0")*/) {

                            System.out.println("inne i kontrolloopen vertikal");
                            if (i == boats[a].getSize() -1) {
                                System.out.println("inne i utplaveringsloopen för båt " + a);
                                //try {
                                for (int j = 0; j < boats[a].getSize(); j++) {
                                    System.out.println("Lägger till båten vertikalt");

                                    temporaryControl = temporaryControl.concat(field[y + j][x]);

                                    //Lägger till koordinaten som ett objekt i båten
                                    boats[a].getPosition().add(field[y + j][x]);

                                    field[y + j][x] = "!!";
                                    field[y + j + 1][x - 1] = "!!";
                                    field[y + j + 1][x + 1] = "!!";
                                    if (j == 0) {
                                        field[y - 1][x] = "!!";
                                        field[y - 1][x - 1] = "!!";
                                        field[y - 1][x + 1] = "!!";
                                    }

                                    if(j == boats[a].getSize() - 1){
                                        field[y + j + 1][x] = "!!";
                                        field[y + j + 1][x - 1] = "!!";
                                        field[y  + j + 1][x + 1] = "!!";
                                    }


                                }

                                System.out.println("Nästa båt");
                                a++;
                                i++;
                            }
                            i++;
                        } else {
                            y = random.nextInt(10) + 1;
                            x = random.nextInt(10) + 1;
                            horisontal = random.nextBoolean();
                            i = 0;
                        }
                    }

                }

            }catch (ArrayIndexOutOfBoundsException e) {

            }
        }
    }
    public void initializeGridArray(){
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
                if(i == 0){
                    field[i][j] = "!".concat(getXField(j));
                }
                else {
                    field[i][j] = Integer.toString(i - 1).concat(getXField(j));
                }
            }
        }
    }


    public String getXField(int j){
        String letter = "";
        switch(j){
            case 0:
            case 11:
                letter = "!";
                break;
            case 1:
                letter = "a";
                break;
            case 2:
                letter = "b";
                break;
            case 3:
                letter = "c";
                break;
            case 4:
                letter = "d";
                break;
            case 5:
                letter = "e";
                break;
            case 6:
                letter = "f";
                break;
            case 7:
                letter = "g";
                break;
            case 8:
                letter = "h";
                break;
            case 9:
                letter = "i";
                break;
            case 10:
                letter = "j";
                break;

        }
        return letter;
    }

    public String[][] getField() {
        return field;
    }

    public void setField(String[][] field) {
        this.field = field;
    }

    public String getTemporaryControl() {
        return temporaryControl;
    }


}

