package br.com.NexusCloud.exception;

public class ServidorOfflineException extends RuntimeException{
    public ServidorOfflineException(String mensagem){
        super(mensagem);
    }
}
