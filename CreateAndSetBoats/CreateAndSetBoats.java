package CreateAndSetBoats;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateAndSetBoats {

        private String[][] field = new String[12][12];
        String temporaryControl = "";

        //AreaArray areaArray = new AreaArray();
        Random random = new Random();
        int y;
        int x;
        boolean horisontal;

        //Kom ihåg att ta boats[i].setPosition


        public void newTest(Boat []boats) {
            for (int a = 0; a < boats.length;) {

                y = random.nextInt(10) + 1;
                x = random.nextInt(10) + 1;
                horisontal = random.nextBoolean();
                List boatPlaces = new ArrayList<>();

                //Minus 1 på i för att få med kordinaten som ligger innan båtens början och +1 för ett efter slutet
                try {
                    for (int i = 0; i < boats[a].getSize();) {
                        if(horisontal){
                            if (!field[y][x + i].equals("!!") && (x + i) < 10/*&& !field[y-1][x + i].equals("0") && !field[y+1][x+i].equals("0")*/) {

                                System.out.println("inne i kontrolloopen");
                                if (i == boats[a].getSize() -1 ) {
                                    System.out.println("inne i utplaneringsloopen för båt " + a);
                                    //try {
                                    for (int j = 0; j < boats[a].getSize(); j++) {
                                        //Temporary control
                                        temporaryControl = temporaryControl.concat(field[y][x +j]);
                                        System.out.println("Lägger till båten");
                                        //boatPlaces.add(field[y][x + j]);
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
                                        //Lägg till de borttagna området runtom här



                                    }
                                    // }catch(ArrayIndexOutOfBoundsException e2){
                                    //   continue;
                                    //}
                                    //boats[a].setPosition(boatPlaces);
                                    a++;
                                    System.out.println("Nästa båt");

                                }
                                i++;
                            } else {
                                y = random.nextInt(10) + 1;
                                x = random.nextInt(10) + 1;
                                i = 0;
                            }


                        }
                        if (!horisontal) {
                            if (!field[y + i][x].equals("!!") && (y + i) < 10/*&& !field[y-1][x + i].equals("0") && !field[y+1][x+i].equals("0")*/) {

                                System.out.println("inne i kontrolloopen");
                                if (i == boats[a].getSize() -1) {
                                    System.out.println("inne i utplaveringsloopen för båt " + a);
                                    //try {
                                    for (int j = 0; j < boats[a].getSize(); j++) {
                                        System.out.println("Lägger till båten vertikalt");
                                        temporaryControl = temporaryControl.concat(field[y + j][x]);
                                        //boatPlaces.add(field[y + j][x]);

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
                                        //Lägg till de borttagna området runtom här

                                    }
                                    // }catch(ArrayIndexOutOfBoundsException e2){
                                    //   continue;
                                    //}
                                    //boats[a].setPosition(boatPlaces);
                                    System.out.println("Nästa båt");
                                    a++;
                                }
                                i++;
                            } else {
                                y = random.nextInt(10) + 1;
                                x = random.nextInt(10) + 1;
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
                case 11:
                    letter = "!";
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

        //For loop med dess loopvärden som x och y för båten och omgivningen
        //Göra en try catch för allt inuti (för out of bounds)
        //If(ingen av de värdena equals(0) så gör det som står nedan
        //Efter det sätta båtens positioner till 0 och lägga in de värdena i båtens positionsobjekt
    }
}
