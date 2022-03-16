package br.com.horys.alex.product;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
