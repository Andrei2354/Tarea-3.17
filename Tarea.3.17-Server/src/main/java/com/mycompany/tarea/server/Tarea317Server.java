package com.mycompany.tarea.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Tarea317Server {

    public static void main(String[] args) {
        // Puerto del servidor
        int port = 1234;
        Random random = new Random();
        int numeroSecreto = random.nextInt(100) + 1;  // Número secreto entre 1 y 100

        System.out.println("Numero secreto generado: " + numeroSecreto);

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port + ".");

            while (true) {
                // Aceptar una conexión de un cliente
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());

                // Leer y responder al cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);

                while (true) {
                    int numeroIntento = Integer.parseInt(input.readLine());

                    if (numeroIntento < numeroSecreto) {
                        output.println("Es mayor.");
                    } else if (numeroIntento > numeroSecreto) {
                        output.println("Es menor.");
                    } else {
                        output.println("Correcto!");
                        System.out.println("El cliente ha adivinado el numero: " + numeroSecreto);
                        break; 
                    }
                }
                client.close(); 
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}