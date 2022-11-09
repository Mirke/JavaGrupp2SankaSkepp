package com.grupp2.sankaskepp.players;

import com.grupp2.sankaskepp.protokoll.ProtocolSankaSkepp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


/**
 * Author: Wei
 */
public class Client {
    //properties

    private PrintWriter writer;

    private BufferedReader reader;

    private boolean firstGuess;


    // Constructor
    public Client() {
    }

    //Methods
    public void start() throws IOException, InterruptedException {
        try {
            //clients socket connected to server
            Socket clientSocket = new Socket("localhost", 1619);

            //Create a input stream
            InputStream inputStream = clientSocket.getInputStream();


            //Create a reader
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Create a output stream
            OutputStream outputStream = clientSocket.getOutputStream();

            //Create a printwriter
            writer = new PrintWriter(outputStream, true);


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
        writer.println(protocolSankaSkepp.beginGame(rand.nextInt(10), rand.nextInt(10)));


        boolean sendMessage = true;
        while (sendMessage) {
            if (reader.ready()) {
                String messageFromServer = reader.readLine();
                //System.out.println("Player 1 says " + messageFromServer); //Mikael commenting out
                System.out.println("Client receiving: " + messageFromServer);

                //TODO: hit or miss depending on shot from server, need method to check result

                //coordinate = String.valueOf(rand.nextInt(10) + "." + rand.nextInt(10));// going to edit Mikael
                //String outputText = "m shot " + coordinate; // going to edit Mikael
                String outputText = protocolSankaSkepp.sendRandomProtocolMethod(rand.nextInt(10), rand.nextInt(10)); //Mikaels kod
                //System.out.println(outputText); // commenting and changing code Mikael
                System.out.println("Client sending: " + outputText);
                System.out.println();


                //TODO: add delay function
                //KL random time delay 2-5 s, don't forget "throws InterruptedException" see above

                int t = (int) (Math.random() * 5 + 1);
                if (t == 1) {
                    t++;
                }
                Thread.sleep(t * 1000);

                //System.out.println("Client sending: ");


                writer.println(outputText);
                //sendMessage = false;
            }


        }

    }


}

