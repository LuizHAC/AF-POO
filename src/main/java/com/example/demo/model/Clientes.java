package com.example.demo.model;

public class Reserva{
    
    private int codigo;
    private String nome;
    private String endereço;
    private String CPF;

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEndereço(){
        return endereço;
    }

    public void setEndereço(String endereço){
        this.endereço = endereço;
    }
    
    public String getCPF(){
        return CPF;
    }

    public void setCPF(String CPF){
        this.CPF = CPF;
    }

}