package br.com.projetojava.morais.library.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GetData {

    public String getCurrentData() {
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
        return formatarData.format(localDate);
    }

    public String getCurrentHour() {
        DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("hh:mm:ss");
        LocalTime localTime = LocalTime.now();
        return formatarHora.format(localTime);
    }

    public String getDevolucaoData(Long tempoEmprestimo) {
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now().plusWeeks(tempoEmprestimo);
        return formatarData.format(localDate);
    }

    public String getReservaData(){
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now().plusWeeks(1L);
        return formatarData.format(localDate);
    }

    public String novaDataDevolucao(String dataAtualDevolucao,Long tempoEmprestimo) {
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataDevolucao = LocalDate.from(formatarData.parse(dataAtualDevolucao));
        LocalDate novaDevolucao = dataDevolucao.plusWeeks(tempoEmprestimo);
        return formatarData.format(novaDevolucao);
    }
}
