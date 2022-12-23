package org.misaeldev.automato;


import org.misaeldev.matriz.Matriz;
import org.misaeldev.matriz.Estado;
import org.misaeldev.matriz.Transicao;
import org.misaeldev.utils.Pilha;

import java.io.File;
import java.util.*;

public class Automato {
    public static final List<String> alfabeto = new ArrayList<>(Arrays.asList("a","b","?"));

    public boolean inInstanciado = false;
    private Matriz matrizTrasiacao;
    private Pilha pilha;
    private String  sentenca;
    public Automato(File arquivoParamentros){
        this.matrizTrasiacao = new Matriz(arquivoParamentros);
        this.pilha = new Pilha();
        this.inInstanciado=true;
    }

    public boolean verificaSentenca(String sentenca){
        this.sentenca = sentenca;
        Estado estadoAtual = this.matrizTrasiacao.getEstadoInicial();
        int index =0;
        while (index < this.sentenca.length() && estadoAtual!=null){
            String letra = Character.toString(this.sentenca.charAt(index));
            if(letra.equals("?") && !this.pilha.inVazia()){
                return false;
            }
            Transicao transicao = estadoAtual.getTransicoes().get(letra);
            if(!this.pilha.pop(transicao.getItensPop())){
                return false;
            }
            this.pilha.push(transicao.getItensPush());
            estadoAtual = this.matrizTrasiacao.getEstado(transicao.getProxEstado());
            index++;
        }
        if(estadoAtual==null){
            System.out.println("Sem estado atual");
            return false;
        }
        estadoAtual.exibir();
    return estadoAtual.inEstadoFinal() && this.pilha.inVazia();
    }
    public void exibir(){
        this.matrizTrasiacao.exibir();
    }


}
