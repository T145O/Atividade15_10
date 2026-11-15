package br.com.NexusCloud.main;

import br.com.NexusCloud.model.Cliente;
import br.com.NexusCloud.model.CloudManager;
import br.com.NexusCloud.model.Container;
import br.com.NexusCloud.model.Servidor;

public class MainCloud {
    public static void main(String[] args) throws Exception {
        CloudManager claudio = new CloudManager();
        Cliente Bruce = new Cliente("111.222.333-44", "Wayne Interprises");
        Servidor aeternos = new Servidor("192.168.0.1", 5.50, 16);
        claudio.registrarCliente(Bruce);
        claudio.provisionarServidor(Bruce, aeternos);
        Container primeiro = new Container("1", "seila", 8);
        Container segundo  = new Container("2", "seila2", 10);
        //aeternos.alocarContainer(segundo);
        aeternos.ligar();
        aeternos.alocarContainer(primeiro);
        try {
            aeternos.alocarContainer(segundo);
        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println(claudio.calcularFaturaAtiva(Bruce));
    }
}
