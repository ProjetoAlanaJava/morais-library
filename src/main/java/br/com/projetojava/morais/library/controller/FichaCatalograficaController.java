package br.com.projetojava.morais.library.controller;

import br.com.projetojava.morais.library.model.FichaCatalografica;
import br.com.projetojava.morais.library.model.books.Autor;
import br.com.projetojava.morais.library.service.FichaCatalograficaService;
import br.com.projetojava.morais.library.util.Constantes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = {"/ficha-catalografica"})
public class FichaCatalograficaController {

    FichaCatalograficaService service;

    public FichaCatalograficaController(FichaCatalograficaService fichaCatalograficaService) {
        service = fichaCatalograficaService;
    }

    /**
     * Metodo para ser usudo no funcionario para achar todas as fichas catalograficas
        @param
        @return Todas as fichas catalograficas no sistema
     */
    @GetMapping
    public List<FichaCatalografica> acharTodas() {
        return service.findAll();
    }

    /**
     *   Exemplo de Request /titulo
     *   Json com o nome do titulo:
     *   {
     *      "titulo" :  "Desenvolvimento ambiental do pantanal Brasileiro"
     *   }
     *
     *
     * @param ficha titulo da ficha apenas para procurar pelo titulo
     * @return
     * 200 - Se achar a ficha
     * 402 - Se não achar nenhuma ficha com o titulo
     * 404 - Se o titulo não for válido
     */
    @GetMapping(path = "/titulo")
    public ResponseEntity<?> acharPorTitulo(@RequestBody FichaCatalografica ficha){

        if(Objects.nonNull(ficha)) {

            String titulo = ficha.getTitulo();

            return service.findByTitle(titulo).map(fichaTitulo -> {

                if(fichaTitulo.getStatus().equals(Constantes.FICHA_STATUS_SOLICITACAO)) {
                    return ResponseEntity.badRequest().body("Você não possui nenhuma ficha gerada!");
                }

                return ResponseEntity.ok().body(fichaTitulo);
                }).orElse(ResponseEntity.notFound().build());

        }

        return ResponseEntity.badRequest().build();
    }

    /**
     *         Exemplo de Request /autor
     *         Json com o nome do autor:
     *         {
     *              "name" : "Gabriel Moreira de Oliveira"
     *         }

     * @param autor enviar o nome do autor para visualizar as fichas dele!!!
     * @return
     * 200 - Se achar a ficha
     * 402 - Se não achar nenhuma ficha para o autor
     * 404 - Se o autor não for válido
     */

    @GetMapping(path = "/autor")
    public ResponseEntity<?> acharPorAutor(@RequestBody Autor autor) {

        if(Objects.nonNull(autor)) {

            String nomeAutor = autor.getName();

            return service.findByAuthor(nomeAutor)
                    .map(fichaAutor -> {

                        if(fichaAutor.getStatus().equals(Constantes.FICHA_STATUS_SOLICITACAO)) {
                            return ResponseEntity.badRequest().body("Você não possui nenhuma ficha gerada!");
                        }

                        return ResponseEntity.ok().body(fichaAutor);
                    }).orElse(ResponseEntity.notFound().build());

        }

        return ResponseEntity.badRequest().build();
    }

    /**
     *
     * @param ficha enviar uma ficha catalografica de acordo com o modelo que se encontra em model/FichaCatalografica
     *              Aqui ela é solicitada, e quando for gerada muda o status
     * @return 200 - Se a ficha for solicirtada
     * 404 - Se a ficha não for válida
     */
    @PostMapping(path = {"/solicitar-ficha"})
    public ResponseEntity<String> solicitar(@RequestBody FichaCatalografica ficha) {

        if(Objects.nonNull(ficha)) {

            ficha.setStatus(Constantes.FICHA_STATUS_SOLICITACAO);
            service.save(ficha);
            return ResponseEntity.ok().body("Ficha solicitada, aguardando um funcionário para gerá-la!");
        }

        return ResponseEntity.badRequest().build();
    }

    /**
     *
     * @param id da ficha a ser gerada que já se encontran o sistema, voltar a ficha para o usuario, para que ele possa visualizar
     * @return
     * 200 - Se conseguir gerar a ficha
     * 402 - Se não achar nenhum registro
     * 404 - Se o id não for válido
     */

    @PutMapping(path = {"/gerar-ficha/{id}"})
    public ResponseEntity<?> gerarFicha(@PathVariable long id) {

        if(id > 0) {
            return service.findById(id)
                    .map(ficha -> {
                        ficha.setStatus(Constantes.FICHA_STATUS_GERADA);
                        service.save(ficha);
                        return ResponseEntity.ok().body("Ficha Catalográfica gerada com sucesso!");
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {

        if(id > 0) {
            return service.findById(id)
                    .map(ficha -> {
                        service.deleteById(id);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }
}


