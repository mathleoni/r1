package Model;

import DAO.EventoDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Evento {

    String titulo;
    Integer codigo;
    LocalDate dataEvento, dataSorteio;
    Double valorMinimo;
    Participante criador;
    List<Participante> participantes;
    Integer totalParticipantes;
    Boolean sorteado;

    public Evento(String titulo, Integer codigo, LocalDate data, LocalDate sorteio, Participante criador, List<Participante> participantes, boolean sorteado) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.dataEvento = data;
        this.dataSorteio = sorteio;
        this.criador = criador;
        this.participantes = participantes;
        totalParticipantes = participantes.size();
        this.sorteado = sorteado;
    }

    public Evento(Integer codigo, String titulo, Double valorMinimo, String data, String sorteio, Participante criador, List<Participante> participantes,boolean sorteado) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.titulo = titulo;
        this.codigo = codigo;
        this.valorMinimo = valorMinimo;
        this.dataEvento = LocalDate.parse(data, dt);
        this.dataSorteio = LocalDate.parse(sorteio, dt);
        this.criador = criador;
        this.participantes = participantes;
        totalParticipantes = participantes.size();
        this.sorteado = sorteado;
    }

    public Evento(String titulo, Double valorMinimo, String data, String sorteio, Participante criador) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.titulo = titulo;
        this.valorMinimo = valorMinimo;
        this.dataEvento = LocalDate.parse(data, dt);
        this.dataSorteio = LocalDate.parse(sorteio, dt);
        this.criador = criador;
    }

    public Evento(String titulo, Double valorMinimo, String data, String sorteio, Participante criador, DateTimeFormatter dt) {
        this.titulo = titulo;
        this.valorMinimo = valorMinimo;
        this.dataEvento = LocalDate.parse(data, dt);
        this.dataSorteio = LocalDate.parse(sorteio, dt);
        this.criador = criador;
    }

    public Evento(Integer codigo, String titulo, Double valorMinimo, String data, String sorteio, Participante criador, DateTimeFormatter dt, List<Participante> participantes, boolean sorteado) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.valorMinimo = valorMinimo;
        this.dataEvento = LocalDate.parse(data, dt);
        this.dataSorteio = LocalDate.parse(sorteio, dt);
        this.criador = criador;
        this.participantes = participantes;
        totalParticipantes = participantes.size();
        this.sorteado = sorteado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalParticipantes() {
        return totalParticipantes;
    }

    public void setTotalParticipantes(Integer totalParticipantes) {
        this.totalParticipantes = totalParticipantes;
    }
    
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDataEvento() {
        return dataEvento.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDataSorteio() {
        return dataSorteio.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    public void setDataSorteio(LocalDate dataSorteio) {
        this.dataSorteio = dataSorteio;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public Participante getCriador() {
        return criador;
    }

    public void setCriador(Participante criador) {
        this.criador = criador;
    }
    

    public void sorteia() throws SQLException {
        if (participantes != null) {
            List<Participante> amigos = participantes;
            Collections.shuffle(amigos, new Random());
            int auxiliar = 1;
            EventoDAO dao = EventoDAO.getInstance();
            for (int i = 0; i < amigos.size(); i++) {
                if (auxiliar<amigos.size()) {
                    dao.adicionarAmigo(this.codigo, amigos.get(i).getCodigo(), amigos.get(auxiliar).getCodigo());
                    auxiliar++;
                } else {
                    dao.adicionarAmigo(this.codigo, amigos.get(i).getCodigo(), amigos.get(0).getCodigo());
                }
            }
            dao.setSorteado(this.codigo);
        }
    }

    public Boolean getSorteado() {
        return sorteado;
    }

    public void setSorteado(Boolean sorteado) {
        this.sorteado = sorteado;
    }
}
