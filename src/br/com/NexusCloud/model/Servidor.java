package br.com.NexusCloud.model;

import java.util.ArrayList;
import java.util.List;

import br.com.NexusCloud.exception.CapacidadeExcedidaException;
import br.com.NexusCloud.exception.ServidorOfflineException;

public class Servidor {
    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;
    private List<Container> containers = new ArrayList<>();

    public Servidor(String ip, double precoPorHora, double ramTotal) {
        this.ip = ip;
        this.precoPorHora = precoPorHora;
        this.ramTotal = ramTotal;
    }

    public boolean getStatusLigado(){
        return statusLigado;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public void ligar(){
        statusLigado = true;
    }

    public void desligar(){
        statusLigado = false;
    }

    public void alocarContainer(Container c) throws Exception{
        if(statusLigado == false){
            throw new ServidorOfflineException("Erro: Não é possível alocar containers em um servidor desligado.");
        }else{
            if(c.getRamNecessaria()< calcularRamEmUso()){
                throw new CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor [IP].");
            }else{
                containers.add(c);
            }
        }
        
    }

    public double calcularRamEmUso(){
        return 
        containers.stream()
            .mapToDouble(Container::getRamNecessaria)
            .sum();
            
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Servidor other = (Servidor) obj;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        return true;
    }

    
}
