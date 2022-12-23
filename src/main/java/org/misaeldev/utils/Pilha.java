package org.misaeldev.utils;

import java.util.ArrayList;
import java.util.List;

public class Pilha {
    private List<String> pilha = new ArrayList<String>();
    public int length;

    public Pilha(){
        this.length = 0;
    }
    public boolean inVazia(){
        return this.length ==0;
    }
    public void push(List<String> itens){
        itens.forEach((item)->{
            this.pilha.add(item);
            this.length++;
        });
    }
    public boolean pop(List<String> itens){
        List<String> removed = new ArrayList<>();
        itens.forEach((item)->{
            int itemIndex = this.pilha.lastIndexOf(item);
            this.pilha.remove(itemIndex);
            removed.add(item);
            this.length--;
        });

        return removed.size() == itens.size();
    }
}
