package com.grupp2.sankaskepp.players;
import com.grupp2.sankaskepp.protokoll.ProtocolSankaSkepp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


/**
 * Author: Wei
 */
public class Client implements Runnable {
    //properties

    private PrintWriter writer;

    private BufferedReader reader;

    private boolean firstGuess;




    // Constructor
    public Client() {
    }

    //Methods
    public void start() throws IOException {
        try {
            //clients socket connected to server
            Socket clientSocket = new Socket("localhost",1619);

            //Create a input stream
            InputStream inputStream = clientSocket.getInputStream();


            //Create a reader
            reader = new BufferedReader( new InputStreamReader(inputStream));

            //Create a output stream
            OutputStream outputStream = clientSocket.getOutputStream();

            //Create a printwriter
            writer = new PrintWriter(outputStream,true);


        } catch (IOException ioException) {
            System.out.println(ioException);
        }
        /*
         * Mikael kod: START
         */
        ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
        /*
         * Mikael kod: END
         */

        Random rand = new Random();
        //String coordinate = String.valueOf(rand.nextInt(10) + "." + rand.nextInt(10)); // Commenting out Mikael
        writer.println(protocolSankaSkepp.beginGame(rand.nextInt(10),rand.nextInt(10)));



        boolean sendMessage = true;
        while(sendMessage) {
            if (reader.ready()) {
                String messageFromServer= reader.readLine();
                //System.out.println("Player 1 says " + messageFromServer); //Mikael commenting out
                System.out.println("Client receiving: " + messageFromServer);

                //TODO: hit or miss depending on shot from server, need method to check result

                //coordinate = String.valueOf(rand.nextInt(10) + "." + rand.nextInt(10));// going to edit Mikael
                //String outputText = "m shot " + coordinate; // going to edit Mikael
                String outputText = protocolSankaSkepp.sendRandomProtocolMethod(rand.nextInt(10),rand.nextInt(10)); //Mikaels kod
                //System.out.println(outputText); // commenting and changing code Mikael
                System.out.println("Client sending: " + outputText);
                System.out.println();




                //TODO: add delay function
                //System.out.println("Client sending: ");
                writer.println(outputText);
                //sendMessage = false;
            }



        }

    }


    @Override
    public void run() {
        try {
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
