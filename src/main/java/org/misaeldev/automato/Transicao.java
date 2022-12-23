package org.misaeldev.automato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transicao {
    private  String proxEstado;
    private List<String> itensPush = new ArrayList<String>();
    private  List<String>  itensPop = new ArrayList<String>();
    public Transicao(String next, String[] itensPop, String[] itensPush){

        this.proxEstado = next;
        this.itensPush.addAll(Arrays.asList(itensPush));
        this.itensPop.addAll(Arrays.asList(itensPop));
    }

    public String getProxEstado() {
        return this.proxEstado;
    }

    public List<String> getItensPush() {
        return this.itensPush;
    }

    public List<String> getItensPop() {
        return this.itensPop;
    }

    public void exibir(){
        System.out.printf("%10s %20s %20s",this.proxEstado,this.itensPop.toString(),this.itensPush.toString());
    }

}
