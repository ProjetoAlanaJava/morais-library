package br.com.projetojava.morais.library.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constantes {

    public static final List<String> METODOS_PERMITIDOS = Arrays.asList("GET", "POST", "PUT", "DELETE");

    public static final List<String> HEADERS_PERMITIDOS = Arrays.asList("origin", "authorization", "content-type", "accept", "x-requestd-with", "access_token");

    public static final List<String> HEADERS_EXPOSTOS = Arrays.asList("access_token");

    public static final Long MAX_AGE = 3600L;

    public static final String REQUEST_DEFAULT = "Bem-vindo, essa é a MoraisLibrary-API! api de gestão de sistema de bibliotecário." +
                    "<br>Todos os path's expostos e os verbos HTTP que podem ser acessados podem ser encontrados em:  https://github.com/ProjetoAlanaJava/morais-library ";

}
