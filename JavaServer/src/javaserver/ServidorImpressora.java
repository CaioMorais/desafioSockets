package javaserver;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServidorImpressora {

    private Queue<String> queue = new ConcurrentLinkedQueue<>();
    public static ServidorImpressora INSTANCE = null;
    Cliente cli;
    private Thread thread = new Thread(){
        @Override
        public void run() {
            ArrayList<Impressora> impressoras = new ArrayList<Impressora>() {{
                add(new Impressora("Impressora 1"));
                add(new Impressora("Impressora 2"));
                add(new Impressora("Impressora 3"));
                add(new Impressora("Impressora 4"));
                add(new Impressora("Impressora 5"));
            }};
            
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100);
                    String mensagem = queue.poll();
                    if (mensagem != null) {
                        Impressora ultImpressora = impressoras.get(0);
                        
                        for (Impressora impressora : impressoras) {
                            if (ultImpressora.tamanhoQueue()> impressora.tamanhoQueue()) {
                                ultImpressora = impressora;
                            }
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    };

    public static ServidorImpressora getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServidorImpressora();
            INSTANCE.iniciarImpressao();
            
        }
        return INSTANCE;
    }

    private void iniciarImpressao(){
        thread.start();
    }
}
