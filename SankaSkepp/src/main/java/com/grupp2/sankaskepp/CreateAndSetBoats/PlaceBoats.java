package com.grupp2.sankaskepp.CreateAndSetBoats;
import java.util.Random;


public class PlaceBoats {
    private String[][] field = new String[12][12];
    String temporaryControl = "";

    Random random = new Random();

    int y;
    int x;
    boolean horisontal;
    boolean works = false;

    int controlLoop = 0;

    public void placeBoats(Boat boat){
        boolean works2 = false;
        while(!works2) {
            works2 = setTheBoats(boat.getBoats());

            if(!works2) {
                for (int i = 0; i < boat.getBoats().length - 1; i++) {
                    boat.getBoats()[i].getPosition().clear();
                    controlLoop = 0;
                }
            }
        }
    }

    public boolean setTheBoats(Boat []boats) {

        initializeGridArray();
        for (int a = 0; a < boats.length;) {

            y = random.nextInt(10) + 1;
            x = random.nextInt(10) + 1;
            horisontal = random.nextBoolean();


            try {
                for (int i = 0; i < boats[a].getSize();) {

                    if(horisontal){
                        if (!field[y][x + i].equals("!!") && (x + i) < 11) {

                            controlLoop++;
                            if(controlLoop == 300){
                                a = 10;
                                i = 10;
                            }

                            if (i == (boats[a].getSize() -1)) {

                                for (int j = 0; j < boats[a].getSize(); j++) {

                                    //Lägger till koordinaten som ett objekt i båten
                                    boats[a].getPosition().add(field[y][x + j]);

                                    field[y][x + j] = "!!";
                                    field[y - 1][x + j] = "!!";
                                    field[y + 1][x + j] = "!!";

                                    if (j == 0) {
                                        field[y][x - 1] = "!!";
                                        field[y - 1][x - 1] = "!!";
                                        field[y + 1][x - 1] = "!!";
                                    }
                                    if(j == boats[a].getSize() - 1){
                                        field[y][x + j + 1] = "!!";
                                        field[y - 1][x + j + 1] = "!!";
                                        field[y + 1][x + j + 1] = "!!";
                                    }



                                }
                                a++;

                            }if(i >= boats[a].getSize() && a < 9){
                                i = 0;
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
                        if (!field[y + i][x].equals("!!") && (y + i) < 11) {

                            controlLoop++;
                            if(controlLoop == 300){
                                a = 10;
                                i = 10;
                            }

                            if (i == (boats[a].getSize() -1)) {

                                for (int j = 0; j < boats[a].getSize(); j++) {

                                    //Lägger till koordinaten som ett objekt i båten
                                    boats[a].getPosition().add(field[y + j][x]);

                                    field[y + j][x] = "!!";
                                    field[y + j][x - 1] = "!!";
                                    field[y + j][x + 1] = "!!";

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
                                a++;

                            }
                            if(i >= boats[a].getSize() && a < 9){
                                i = 0;
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

            }catch (ArrayIndexOutOfBoundsException  e) {

            }
        }

        if(controlLoop < 300){
            works = true;
        }
        return works;
    }
    public void initializeGridArray(){
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){

                if(i == 0 || i == 11 || j == 0 || j == 11){
                    field[j][i] = "!!";
                }else {
                    field[j][i] = Integer.toString(i - 1).concat(getYField(j));
                }

            }
        }
    }


    public String getYField(int j){
        String letter = "";
        switch(j){
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

