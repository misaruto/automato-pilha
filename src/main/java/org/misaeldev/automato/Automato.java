package org.misaeldev.automato;


import org.misaeldev.exceptions.AutomatoException;
import org.misaeldev.utils.Pilha;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Automato {
    public List<String> alfabeto;

    public boolean inInstanciado = false;
    private Pilha pilha;
    private String  sentenca;

    private Estado estadoInicial;
    private Map<String, Estado> estados = new HashMap<String, Estado>();

    public Automato(File arquivoParamentros){
        this.carregarMatrizDeArquivo(arquivoParamentros);
        this.pilha = new Pilha();
        this.inInstanciado=true;
    }

    public boolean verificaSentenca(String sentenca){
        this.sentenca = sentenca;
        this.pilha = new Pilha();
        System.out.println(this.sentenca);
        Estado estadoAtual = this.estadoInicial;
        int index =0;
        while (index < this.sentenca.length() && estadoAtual!=null){
            String letra = Character.toString(this.sentenca.charAt(index));
            System.out.println(letra);
            if(letra.equals("?") && !this.pilha.inVazia()){
                return false;
            }
            Transicao transicao = estadoAtual.getTransicoes().get(letra);

            if(transicao == null || !this.pilha.pop(transicao.getItensPop())){
                return false;
            }
            this.pilha.push(transicao.getItensPush());
            estadoAtual = this.estados.get(transicao.getProxEstado());
            index++;
        }
        if(estadoAtual==null){
            System.out.println("Sem estado atual");
            return false;
        }
        estadoAtual.exibir(this.alfabeto);
    return estadoAtual.inEstadoFinal() && this.pilha.inVazia();
    }


    private void carregarMatrizDeArquivo(File file){
        try{
            Scanner arquivo = new Scanner(file);
            arquivo.useDelimiter("\n");
            String alfabeto = arquivo.next();

            this.alfabeto = new ArrayList<>(Arrays.asList(alfabeto.split(";")));
            this.processaArquivoMatrizPilha(arquivo);

        }catch (FileNotFoundException ex) {
            throw new AutomatoException("importar automato","arquivo não encontrado");
        }
    }


    private void processaArquivoMatrizPilha(Scanner arquivo){
        while (arquivo.hasNext()) {
            Estado r = new Estado(arquivo.next(), this.alfabeto);
            if (this.estadoInicial != null && r.inEstadoInicial()) {
                throw new AutomatoException("importar automato", "contém dois estados iniciais");
            } else {
                if (r.inEstadoInicial()) {
                    this.estadoInicial = r;
                }
            }
            this.estados.put(r.getNmEstado(), r);
        }
    }
    public void exibir(){
        System.out.println("Alfabeto: "+this.alfabeto.toString());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%s %10s %14s %16s ","Estado", "A" ,"B", "?");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();
        this.estados.forEach((key,value)->{
            value.exibir(this.alfabeto);
        });
        System.out.println("-----------------------------------------------------------------------------");
    }

    public Estado getEstadoInicial(){
        return  this.estadoInicial;
    }

    public Estado getEstado(String nmEstado){
        return this.estados.get(nmEstado);
    }


}
