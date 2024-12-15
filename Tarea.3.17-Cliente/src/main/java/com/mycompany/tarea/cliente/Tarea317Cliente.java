package com.mycompany.tarea.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Tarea317Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        
        int menor = 1;
        int mayor = 100;
        
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Random rand = new Random();
            
            while (true) {
                int numero = (menor + mayor) / 2;
                System.out.println("Proponiendo el numero: " + numero);
                output.println(numero);  

                String response = input.readLine();  
                System.out.println("Servidor: " + response);

                if ("Correcto!".equals(response)) {
                    System.out.println("Felicidades! Has adivinado el numero.");
                    break;
                } 
                else if ("Es mayor.".equals(response)) {
                    menor = numero + 1;  
                } 
                else if ("Es menor.".equals(response)) {
                    mayor = numero - 1;  
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}