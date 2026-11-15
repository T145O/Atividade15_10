package br.com.NexusCloud.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CloudManager {
    private Map<Cliente, Set<Servidor>> bancoDeDados;

    public CloudManager() {
        this.bancoDeDados = new HashMap<>();
    }

    public void registrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    public void provisionarServidor(Cliente c, Servidor s) {
        bancoDeDados.computeIfAbsent(c, k -> new HashSet<>()).add(s);
    }

    public double calcularFaturaAtiva(Cliente c) {
        Set<Servidor> servidores = bancoDeDados.get(c);
        if (servidores == null) {
            return 0.0; // Retorna 0 caso não haja servidores ou o cliente não exista
        }
        return servidores.stream()
                .filter(Servidor::getStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora).sum();
    }
}
