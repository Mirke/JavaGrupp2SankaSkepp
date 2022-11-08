package com.grupp2.sankaskepp.players;

import com.grupp2.sankaskepp.protokoll.ProtocolSankaSkepp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
/**
 * Author: Wei
 */
public class Server {public static void main(String[] args) throws InterruptedException {
    //properties

    private PrintWriter writer;

    private BufferedReader reader;

    private boolean gameIsRunning;

    public Server() {
    }

    //Methods
    public void start () throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(1619);
            System.out.println("Server is ready, waiting for client.");

            //accept client´s socket
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client has joined the game.");

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

        boolean sendMessage = true;
        Random rand = new Random();

        while (sendMessage) {
            if (reader.ready()) {
                String messageFromClient = reader.readLine();
                //System.out.println("Player 2 says " + messageFromClient);
                System.out.println("Server receiving: " + messageFromClient);


                //TODO: hit or miss depending on shot from client, need method to check result
                /*
                 * Mikael kod: START
                 */
                ProtocolSankaSkepp protocolSankaSkepp = new ProtocolSankaSkepp();
                /*
                 * Mikael kod: END
                 */
                String coordinate = String.valueOf(rand.nextInt(10) + "." + rand.nextInt(10));
                //String outputText = "m shot " + coordinate; //commenting out to merge code Mikael
                String outputText = protocolSankaSkepp.sendRandomProtocolMethod(rand.nextInt(10), rand.nextInt(10)); //Mikaels kod
                System.out.println("Server sending: " + outputText);
                System.out.println();
                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Error trying to pause for two seconds with message " + e);
                }*/

                //delay function

                //System.out.print("Server sending: ");

                //KL random time delay 2-5 s, don't forget "throws InterruptedException" see above

                int t = (int) (Math.random() * 5 + 1);
                if (t == 1) {
                    t++;
                }
                Thread.sleep(t * 1000);

                writer.println(outputText);


                //sendMessage = false;


            }
        }

    }


}
}



