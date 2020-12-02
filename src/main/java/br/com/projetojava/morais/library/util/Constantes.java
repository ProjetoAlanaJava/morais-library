package br.com.projetojava.morais.library.util;

public class Constantes {

    public static final String METODOS_PERMITIDOS = "GET, POST, PUT, DELETE";

    public static final String HEADERS_PERMITIDOS = "Origin, Accept, X-Requestd-Wtih, Content-Type, Authorization, access_token";

    public static final String MAX_AGE = "3600";

    public static final String REQUEST_DEFAULT = "Bem-vindo, essa é a MoraisLibrary-API! api de gestão de sistema de bibliotecário." +
                    "<br>Todos os path's expostos e os verbos HTTP que podem ser acessados podem ser encontrados em:  https://github.com/ProjetoAlanaJava/morais-library ";

    public static final String CREATE_USER = "Usuário criado com sucesso!";

    public static final String USER_BAD_REQUEST = "Os dados enviados na request não são válidos!";

    public static final String VAZIO = "";

    public static final Long TEMPO_DEVOLUCAO_ALUNO = 2L;

    public static final Long TEMPO_DEVOLUCAO_EXTERNO = 1L;

    public static final Long TEMPO_DEVOLUCAO_PROFESSOR = 3L;

    public static final String USUARIO_NAO_ACHADO = "Usuário com o ID informado não achado!";

    public static final String FUNCIONARIO_NAO_ACHADO = "Funcinário com o ID informado não achado!";

    public static final String RESERVA_LIVRO_EXISE = "Esse livro já está reservado por outro usuário!";

    public static final String EMPRESTIMO_NAO_ENCONTRADO = "Esse emprestimo não foi encontrado!";

    public static final String MULTA_NAO_ENCONTRADA = "Essa multa não foi encontrada!";

    public static final String MULTA_OK = "Paga";

    public static final String MULTA_PENDENTE = "Pendente";

    public static final String FICHA_STATUS_SOLICITACAO = "solicitada";

    public static final String FICHA_STATUS_GERADA = "gerada";

}
