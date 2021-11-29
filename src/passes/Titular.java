/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passes;

import java.time.LocalDate;

/**
 *
 * @author Ronaldo Guilherme
 */
public class Titular {
    private DocumentoID docID;
    private String nome;
    private String morada;
    private String email;
    private int telefone;
    private LocalDate dataNasc;
    
    Titular(String tipo,String cod,String nome,int telef,LocalDate dataNasc){
        docID = new DocumentoID(tipo,cod);
        this.nome = nome;
        this.telefone = telef;
        this.dataNasc = dataNasc;
    }
    
    Titular(String tipo,String cod,String nome,String morada,String email,int telef,LocalDate dataNasc){
        docID = new DocumentoID(tipo,cod);
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telef;
        this.dataNasc = dataNasc;
    }

    

    public void setDocID(DocumentoID docID) {
        this.docID = docID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    
    
    public DocumentoID getDocID() {
        return docID;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public int getTelefone() {
        return telefone;
    }
    
    
    
    

}
