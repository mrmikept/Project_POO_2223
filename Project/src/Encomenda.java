import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Encomenda {
    private int id;
    private Utilizador vendedor;
    private Utilizador comprador;
    private List<Artigo> listaArtigos;
    private Transportadora transportadora;
    private int dimensao;
    private double precoFinal;
    private int estado;
    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;


    public Encomenda() {
        this.id = 0;
        this.vendedor = new Utilizador();
        this.comprador = new Utilizador();
        this.listaArtigos = new ArrayList<>();
        this.transportadora = new Transportadora();
        this.dimensao = Atributos.PEQUENO;
        this.precoFinal = 0;
        this.estado = Atributos.PENDENTE;
        this.dataCriacao = LocalDate.now();
        this.dataAtualizacao = LocalDate.now();
    }

    public Encomenda(int id, Utilizador vendedor, Utilizador comprador, List<Artigo> listaArtigos, Transportadora transportadora, int dimensao, double precoFinal, int estado, LocalDate dataCriacao, LocalDate dataAtualizacao) {
        this.id = id;
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.listaArtigos = listaArtigos;
        this.transportadora = transportadora.clone();
        this.dimensao = dimensao;
        this.precoFinal = precoFinal;
        this.estado = estado;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Encomenda(Encomenda encomenda) {
        this.id = encomenda.getId();
        this.vendedor = encomenda.getVendedor();
        this.comprador = encomenda.getComprador();
        this.listaArtigos = encomenda.getListaArtigos();
        this.transportadora = encomenda.getTransportadora();
        this.dimensao = encomenda.getDimensao();
        this.precoFinal = encomenda.getPrecoFinal();
        this.estado = encomenda.getEstado();
        this.dataCriacao = encomenda.getDataCriacao();
        this.dataAtualizacao = encomenda.getDataAtualizacao();
    }

    public Encomenda(int id, LocalDate dataCriacao, Utilizador comprador, Utilizador vendedor) {
        this.id = id;
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.listaArtigos = new ArrayList<>();
        this.transportadora = new Transportadora();
        this.dimensao = Atributos.PEQUENO;
        this.precoFinal = 0;
        this.estado = Atributos.PENDENTE;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataCriacao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilizador getVendedor()
    {
        return this.vendedor;
    }

    public void setVendedor(Utilizador vendedor)
    {
        this.vendedor = vendedor;
    }

    public Utilizador getComprador()
    {
        return this.comprador;
    }

    public void setComprador(Utilizador comprador)
    {
        this.comprador = comprador;
    }

    public List<Artigo> getListaArtigos() {
        return listaArtigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public void setListaArtigos(List<Artigo> listaArtigos) {
        this.listaArtigos = listaArtigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public Transportadora getTransportadora()
    {
        return this.transportadora.clone();
    }

    public void setTransportadora(Transportadora transportadora)
    {
        this.transportadora = transportadora.clone();
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
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
        return this.dataCriacao;
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

    public void adicionaArtigo(Artigo artigo) throws EncomendaException {
        if (!this.listaArtigos.contains(artigo)) {
            if (this.listaArtigos.isEmpty())
            {
                this.setVendedor(artigo.getVendedor());
                this.setTransportadora(artigo.getTransportadora());
            }
            this.listaArtigos.add(artigo);
            this.alteraPreco();
            this.alteraDimensão(listaArtigos.size());
        } else throw new EncomendaException("Artigo já existente na encomenda!");
    }

    public void removeArtigo(Artigo artigo) throws EncomendaException {
        if (this.listaArtigos.contains(artigo)) {
            this.listaArtigos.remove(artigo);
            this.alteraDimensão(listaArtigos.size());
            this.alteraPreco();
        } else throw new EncomendaException("Artigo não existe na encomenda!");
    }

    public double calculaValorArtigos()
    {
        return this.listaArtigos.stream().mapToDouble(Artigo::getPrecoBase).sum() + this.listaArtigos.stream().mapToDouble(Artigo::getCorrecaoPreco).sum();
    }

    public double calculaTaxaArtigos()
    {
        double valorArtigosUsados = (this.listaArtigos.stream().filter(artigo -> artigo.getEstado().getTipoEstado() == Atributos.USADO).count()) * 0.25;
        double valorArtigosNovos = (this.listaArtigos.stream().filter(artigo -> artigo.getEstado().getTipoEstado() == Atributos.NOVO).count()) * 0.5;
        return valorArtigosNovos + valorArtigosUsados;
    }

    public double calculaValorExpedicao()
    {
        return this.transportadora.calculaValorExpedicao(this.listaArtigos.size());
    }
    public void alteraPreco()
    {
        this.setPrecoFinal(this.calculaValorArtigos() + this.calculaTaxaArtigos() + this.calculaValorExpedicao());
    }

    public void alteraDimensão(int tamanho) {
        if (tamanho <= 1) {
            this.setDimensao(Atributos.PEQUENO);
        }
        if (tamanho >= 2 && tamanho <= 5) {
            this.setDimensao(Atributos.MEDIO);
        }
        if (tamanho >= 6) {
            this.setDimensao(Atributos.GRANDE);
        }
    }


    public void alteraEstadoExpedido(LocalDate dataAtualizacao)
    {
        this.estado = Atributos.EXPEDIDA;
        for (Artigo artigo: listaArtigos) {
            artigo.setEstadoVenda(Atributos.VENDIDO);
        }
        this.setDataAtualizacao(dataAtualizacao);
    }

    public LocalDate getDataPrevistaEntrega()
    {
        return this.getDataAtualizacao().plusDays(this.getTransportadora().getTempoExpedicao());
    }

    public void alteraEstadoFinalizado(LocalDate dataAtualizacao)
    {
        LocalDate dataPrevistaEntrega = this.getDataPrevistaEntrega();
        if (dataPrevistaEntrega.isBefore(dataAtualizacao))
        {
            this.estado = Atributos.FINALIZADA;
            this.setDataAtualizacao(dataPrevistaEntrega);
        }
    }

    public void alteraEstadoDevolvida(LocalDate dataAtualizacao)
    {
        this.setEstado(Atributos.DEVOLVIDA);
        this.setDataAtualizacao(dataAtualizacao);
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
        Encomenda encomenda = (Encomenda) o;
        return (this.getListaArtigos().equals(encomenda.getListaArtigos()) &&
                this.getTransportadora().equals(encomenda.getTransportadora()) &&
                this.getPrecoFinal() == encomenda.getPrecoFinal() &&
                this.getDimensao() == encomenda.getDimensao());
    }
    private String dimensaoToString() {
        if (this.getDimensao() == Atributos.PEQUENO) {
            return "Pequena";
        }
        if (this.getDimensao() == Atributos.MEDIO) {
            return "Media";
        }
        return "Grande";
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

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Identificador Encomenda: " + this.getId());
        string.append(this.listaArtigos.toString());
        string.append("Dimensão: " + this.dimensaoToString() + "\n");
        string.append("Preço: " + this.precoFinal + "\n");
        string.append("Estado encomenda: " + this.estadoToString() + "\n");
        string.append("Data Criação Encomenda: " + this.getDataCriacao() + "\n");
        string.append("Data Atualização Estado: " + this.getDataAtualizacao());
        return string.toString();
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }
}
