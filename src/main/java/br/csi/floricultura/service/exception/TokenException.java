package br.csi.floricultura.service.exception;

public class TokenException extends RuntimeException{
    public TokenException(String mensage){
        super(mensage);
    }
}
