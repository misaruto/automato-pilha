package org.misaeldev.matriz;


import org.misaeldev.automato.Automato;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Matriz{
    private int nrLinhas;
    private int nrColunas;

    private Estado estadoInicial;
    private List<Estado> matrizPilha;
    private Map<String, Estado> estados = new HashMap<String, Estado>();
    public Matriz(File arquivoParametros){
        this.carregarMatrizDeArquivo(arquivoParametros);
    }
    private void carregarMatrizDeArquivo(File file){
        try{
            Scanner arquivo = new Scanner(file);
            arquivo.useDelimiter("\n");
            this.matrizPilha = new ArrayList<Estado>();
            this.processaArquivoMatrizPilha(arquivo);

        }catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private void processaArquivoMatrizPilha(Scanner arquivo){
        while (arquivo.hasNext()){
            Estado r = new Estado(arquivo.next());
            if(r.inEstadoInicial()){
                this.estadoInicial = r;
            }
            this.estados.put(r.getNmEstado(),r);
            this.matrizPilha.add(r);
        }
    }
    public void exibir(){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%s %10s %14s %16s ","Estado", "A" ,"B", "?");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();
        this.matrizPilha.forEach(Estado::exibir);
        System.out.println("-----------------------------------------------------------------------------");
    }

    public Estado getEstadoInicial(){
        return  this.estadoInicial;
    }

    public Estado getEstado(String nmEstado){
        return this.estados.get(nmEstado);
    }
}
