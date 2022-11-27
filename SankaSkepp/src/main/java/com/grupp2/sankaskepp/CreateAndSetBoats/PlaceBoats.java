package com.grupp2.sankaskepp.CreateAndSetBoats;
import java.util.Random;

/**
 * Author: Bastian
 */
public class PlaceBoats {
    /*-----------------------------------------------------------------------------------------------------------------
     * Konstruktorer
     ------------------------------------------------------------------------------------------------------------------*/
    private String[][] field = new String[12][12];
    String temporaryControl = "";
    Random random = new Random();
    int y;
    int x;
    boolean horisontal;
    boolean works = false;
    int controlLoop = 0;

    /*-----------------------------------------------------------------------------------------------------------------
     * Metoder
     ------------------------------------------------------------------------------------------------------------------*/

    public void placeBoats(Fleet fleet){
        //Kontrollerar om det fungerar, om inte så börjar den om
        boolean works2 = false;
        while(!works2) {
            works2 = setTheBoats(fleet.getBoats());

            if(!works2) {
                for (int i = 0; i < fleet.getBoats().length - 1; i++) {
                    fleet.getBoats()[i].getPosition().clear();
                    controlLoop = 0;
                }
            }
        }
    }

    public boolean setTheBoats(Boat []boats) {
        //Skapar spelplan
        initializeGridArray();
        for (int a = 0; a < boats.length;) {

            //Tar slumpmässiga värden för x,y och horisontellt/vertikalt led
            y = random.nextInt(10) + 1;
            x = random.nextInt(10) + 1;
            horisontal = random.nextBoolean();


            try {
                for (int i = 0; i < boats[a].getSize();) {

                    //Försöker lägga ut båten i horisontellt led så längre den inte stöter på två utropstecken
                    if(horisontal){
                        if (!field[y][x + i].equals("!!") && (x + i) < 11) {

                            //För att kolla att det inte blir en evighetsloop
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
                            //Fungerar det inte så tar den nya värden utan att öka variabeln a och ställer om i till 0
                            y = random.nextInt(10) + 1;
                            x = random.nextInt(10) + 1;
                            horisontal = random.nextBoolean();
                            i = 0;
                        }


                    }
                    //Försöker lägga ut båten i vertikalt led så längre den inte stöter på två utropstecken
                    if (!horisontal) {
                        if (!field[y + i][x].equals("!!") && (y + i) < 11) {

                            //För att kolla att det inte blir en evighetsloop
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
                            //Fungerar det inte så tar den nya värden utan att öka variabeln a och ställer om i till 0
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

    //Skapar en spelplan med en ruta för mycket i varje led. Rutorna utanför spelplanen markeras med två utropstecken
    public void initializeGridArray(){
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){

                if(i == 0 || i == 11 || j == 0 || j == 11){
                    field[j][i] = "!!";
                }else {
                    //Omvandlar integern till en textsträng och lägger ihop den med rätt bokstav från getYField
                    field[j][i] = Integer.toString(i - 1).concat(getYField(j));
                }

            }
        }
    }

    //Tar en integer och omvandlar den till motsvarande bokstav och returnerar den
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


    /*-----------------------------------------------------------------------------------------------------------------
     * Getters och setters
     ------------------------------------------------------------------------------------------------------------------*/

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

