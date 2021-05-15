/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Caio
 */
package javaserver;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Impressora{

    private String nomeImpressora;
    private Queue<String> queue = new ConcurrentLinkedQueue<>();
    Cliente cli;
    public Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                    String message = queue.poll();
                    if (message != null) {
                        System.out.println(nomeImpressora + ": " + message);
                    }
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
        }
    };

    public int tamanhoQueue(){
        return queue.size();
    }

    public Impressora(String nome)
    {
        thread.start();
        nomeImpressora = nome;
    }
}
