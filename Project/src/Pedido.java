import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Pedido implements Serializable {
    private Map<String, Encomenda> listaEncomendas;
    private double precoFinal;
    private int estado;

    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;

    public Pedido()
    {
        this.listaEncomendas = new HashMap<>();
        this.precoFinal = 0;
        this.estado = Atributos.PENDENTE;
        this.dataCriacao = LocalDate.now();
        this.dataAtualizacao = LocalDate.now();
    }

    public Pedido(Map<String, Encomenda> listaEncomendas, double precoFinal, int estado, LocalDate dataCriacao)
    {
        this.listaEncomendas = listaEncomendas;
        this.precoFinal = precoFinal;
        this.estado = estado;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataCriacao;
    }

    public Pedido(Pedido pedido)
    {
        this.listaEncomendas = pedido.getListaEncomendas();
        this.precoFinal = pedido.getPrecoFinal();
        this.estado = pedido.getEstado();
        this.dataCriacao = pedido.getDataCriacao();
        this.dataAtualizacao = pedido.getDataAtualizacao();
    }

    public Map<String, Encomenda> getListaEncomendas() {
        return listaEncomendas.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaEncomendas(Map<String, Encomenda> listaEncomendas) {
        this.listaEncomendas = listaEncomendas.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void adicionaEncomenda(Encomenda encomenda) throws EncomendaException {
        if (!this.listaEncomendas.containsKey(encomenda.getTransportadora()))
        {
            this.listaEncomendas.put(encomenda.getTransportadora().getNome(),encomenda.clone());
            this.alteraPrecoTotal();
        }
        else
        {
            if (!this.listaEncomendas.get(encomenda.getTransportadora()).equals(encomenda))
            {
                this.listaEncomendas.put(encomenda.getTransportadora().getNome(),encomenda.clone());
                this.alteraPrecoTotal();
            }
            else throw new EncomendaException("Encomenda já existente no Pedido!");
        }

    }

    public void removeEncomenda(Encomenda encomenda) throws EncomendaException {
        if (this.listaEncomendas.containsKey(encomenda.getTransportadora().getNome()))
        {
            this.listaEncomendas.remove(encomenda.getTransportadora().getNome(),encomenda);
            this.alteraPrecoTotal();
        }
        else throw new EncomendaException("Encomenda não existente no Pedido!");
    }

    public void adicionaArtigoPedido(Artigo artigo) throws EncomendaException {
        if (!this.listaEncomendas.containsKey(artigo.getTransportadora().getNome()))
        {
            Encomenda encomenda = new Encomenda();
            encomenda.setTransportadora(artigo.getTransportadora());
            encomenda.adicionaArtigo(artigo);
            this.listaEncomendas.put(artigo.getTransportadora().getNome(),encomenda);
            this.alteraPrecoTotal();
        }
        else
        {
            Encomenda encomenda = this.listaEncomendas.get(artigo.getTransportadora().getNome());
            encomenda.adicionaArtigo(artigo);
            this.listaEncomendas.put(artigo.getTransportadora().getNome(),encomenda);
            this.alteraPrecoTotal();
        }
    }

    public void removeArtigoPedido(Artigo artigo) throws EncomendaException {
        if (this.listaEncomendas.containsKey(artigo.getTransportadora().getNome()))
        {
            this.listaEncomendas.get(artigo.getTransportadora().getNome()).removeArtigo(artigo);
            this.listaEncomendas.remove(artigo.getTransportadora().getNome());
            this.alteraPrecoTotal();
        }
        else throw new EncomendaException("Artigo não existente na encomenda!");
    }

    public void alteraPrecoTotal()
    {
        this.precoFinal = this.listaEncomendas.values().stream().mapToDouble(encomenda -> encomenda.getPrecoTotalArtigos()).sum();
    }

    public void alteraEstadoExpedido(LocalDate dataAtualizacao)
    {
        this.estado = Atributos.EXPEDIDA;
        this.setDataAtualizacao(dataAtualizacao);
    }

    public void alteraEstadoFinalizado(LocalDate dataAtualizacao)
    {
        this.estado = Atributos.FINALIZADA;
        this.setDataAtualizacao(dataAtualizacao);
    }

    public String estadoToString()
    {
        if (this.getEstado() == Atributos.PENDENTE)
        {
            return "PENDENTE";
        }
        if (this.getEstado() == Atributos.EXPEDIDA)
        {
            return "EXPEDIDA";
        }
        return "FINALIZADA";
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }
        Pedido pedido = (Pedido) o;
        return this.getListaEncomendas().equals(pedido.getListaEncomendas()) &&
                this.getEstado() == pedido.getEstado() &&
                this.getPrecoFinal() == pedido.getPrecoFinal() &&
                this.getDataAtualizacao().equals(pedido.getDataAtualizacao()) &&
                this.getDataCriacao().equals(pedido.getDataCriacao());
    }


    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Pedido]\n");
        string.append(this.listaEncomendas.toString() + "\n");
        string.append("Preço Total: " + this.getPrecoFinal() + "\n");
        string.append("Estado: " + this.estadoToString() + "\n");
        return string.toString();
    }

    public Pedido clone()
    {
        return new Pedido(this);
    }

}