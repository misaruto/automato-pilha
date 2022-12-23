package org.misaeldev.matriz;

import org.misaeldev.automato.Automato;

import java.util.*;

public class Estado {
    private boolean inEstadoInicial;
    private boolean inEstadoFinal;
    private String nmEstado;
    private Map<String,Transicao> transicoes = new HashMap<>();

    /**
     * Metodo para criação vindo a partir de um arquivo.
     * MODELO DO ARQUIVO:
     *  1,a-b,a;2,,a-b\n
     *  O simbolo ; separa as colunas.
     *  O simbolo , separa as opções da coluna, que são (respectivamente): para onde o item vai, quais valores empilha e quais valores desempilha.
     *  O simbolo - separa os itens que devem ser empilhado.
     *  OBSERVAÇÃO.: Espaço vazio será desconsiderado.
     * */
    public Estado(String row){
        row = row.replace(" ","");
        String cols[] = row.split(";");
        String[] parametros = cols[0].split(",");
        this.nmEstado = parametros[0];
        if(parametros.length ==2){
            this.inEstadoInicial = parametros[1].equals("i");
            this.inEstadoFinal = parametros[1].equals("f");
        }
        else if(parametros.length==3){
            this.inEstadoInicial = parametros[2].equals("i");
            this.inEstadoFinal = parametros[2].equals("f");
        }else {
            this.inEstadoInicial = false;
            this.inEstadoFinal = false;
        }
        int indexLetra = 0;
        String letra = "";
        for(String col: Arrays.copyOfRange(cols,1,cols.length)){
            //remove espaços vazios
            col = col.replace(" ","");

            String [] item = col.split(",");

            letra = Automato.alfabeto.get(indexLetra);

            indexLetra++;
            if(item.length==1 && !item[0].isEmpty()){
                this.transicoes.put(letra,new Transicao(item[0],new String[0],new String[0]));
            }
            else if (item.length == 2){
                this.transicoes.put(letra,new Transicao(item[0],item[1].split("-"),new String[0]));
            } else if (item.length==3) {
                if(item[1].isEmpty()) {
                    this.transicoes.put(letra,new Transicao(item[0], new String[0], item[2].split("-")));
                }
                else{
                    this.transicoes.put(letra,new Transicao(item[0], item[1].split("-"), item[2].split("-")));
                }
            }

        }
    }

    public Map<String, Transicao> getTransicoes() {
        return transicoes;
    }

    public boolean inEstadoInicial() {
        return this.inEstadoInicial;
    }

    public boolean inEstadoFinal() {
        return this.inEstadoFinal;
    }

    public String getFinalIncial(){

        if(this.inEstadoInicial && !this.inEstadoFinal){
            return "inicial";
        }
        else if(this.inEstadoFinal && !this.inEstadoInicial){
            return "final";
        }
        else{
            return "inicial e final";
        }

    }
    public String getNmEstado(){
        return this.nmEstado;
    }


    public void exibir(){
        System.out.printf("%s",this.getNmEstado());
        Automato.alfabeto.forEach((letra->{
            Transicao transicao = this.transicoes.get(letra);
            if(transicao==null){
                System.out.printf("%24s","Sem transicao");
            }else {
                System.out.printf("%10s %s %s", transicao.getProxEstado(), transicao.getItensPop().toString(), transicao.getItensPush().toString());
            }
        }));
        System.out.println();
    }
}
