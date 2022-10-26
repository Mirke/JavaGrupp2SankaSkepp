package CreateAndSetBoats;
import java.util.ArrayList;
import java.util.Random;


public class Boat {



    //Alternativ 1: Skapa en klass för position
    private ArrayList<String> position = new ArrayList();
    //private String[] position;


    //Alternativ 2: Lagra positionen som en textsträng
    //private String position;
    private int size;
    private Boat[] boats;
    Random random = new Random();


    //Konstruktorer för båtarna
    public Boat(){

    }
    public Boat(int size){
        this.size = size;
    }


    //Skapande av alla båtar
    public void createBoats(){
        Boat boat1 = new Boat(5);
        Boat boat2 = new Boat(4);
        Boat boat3 = new Boat(4);
        Boat boat4 = new Boat(3);
        Boat boat5 = new Boat(3);
        Boat boat6 = new Boat(3);
        Boat boat7 = new Boat(2);
        Boat boat8 = new Boat(2);
        Boat boat9 = new Boat(2);
        Boat boat10 = new Boat(2);

        //Gör att man kan få båtarnas storlek och position genom en loop och en getter istället för väldigt många rader kod
        boats = new Boat[]{boat1, boat2, boat3, boat4, boat5, boat6, boat7, boat8, boat9, boat10};

    }

    //En metod för att placera ut båtarna, behövs byggas på

    //Kalla på fler metoder för att avsluta denna del

        /*Ifall att man gör ett objekt för position
        setPosition();
        Ifall att man lagrar positionen som en sträng i Boat-Klassen
        setPosition1();*/




    //Getters och Setters
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public ArrayList<String> getPosition() {
        return position;
    }

    public void setPosition(ArrayList <String> position) {
        this.position = position;
    }

    public Boat[] getBoats() {
        return boats;
    }
    }


