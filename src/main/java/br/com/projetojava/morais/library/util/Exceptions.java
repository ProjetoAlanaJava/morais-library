package br.com.projetojava.morais.library.util;

public class Exceptions extends RuntimeException {

    private static final long serialVersionUID = -12309812371223L;

    public Exceptions() {
    }

    public Exceptions(String message) {
        super(message);
    }

    public Exceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public static void checkAndThrow(boolean expression, String mensagemErro) throws Exceptions{

        if(expression) {
            throw new Exceptions(mensagemErro);
        }

    }

}
