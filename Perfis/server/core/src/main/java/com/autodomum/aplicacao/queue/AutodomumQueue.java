package com.autodomum.aplicacao.queue;

import com.autodomum.comandos.Comando;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author sabrina on 13/06/16.
 */
@Component
@Scope("singleton")
public class AutodomumQueue {

    private Queue<Comando> filaDeComandos;

    @PostConstruct
    public void init() {
        this.filaDeComandos = new ConcurrentLinkedQueue<>();
    }

    public boolean send(Comando comando) {
        return filaDeComandos.offer(comando);
    }

    public Comando receive() {
        return filaDeComandos.poll();
    }

    public Collection<Comando> receiveAll() {
        Collection<Comando> comandos = new ArrayList<>();

        Comando lido = filaDeComandos.poll();
        while(lido != null) {
            comandos.add(lido);
            lido = filaDeComandos.poll();
        }

        return comandos;
    }
}
