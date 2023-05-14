import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que contem os parametros de uma encomenda
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Encomenda implements Serializable {
    private int id;
    private String emailVendedor;
    private String emailComprador;
    private List<Artigo> listaArtigos;
    private Transportadora transportadora;
    private int dimensao;
    private double precoFinal;
    private int estado;
    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;


    ///////////////
    //Contrutores//
    ///////////////

    public Encomenda() {
        this.id = 0;
        this.emailVendedor = "";
        this.emailComprador = "";
        this.listaArtigos = new ArrayList<>();
        this.transportadora = new Transportadora();
        this.dimensao = Atributos.PEQUENO;
        this.precoFinal = 0;
        this.estado = Atributos.PENDENTE;
        this.dataCriacao = LocalDate.now();
        this.dataAtualizacao = LocalDate.now();
    }

    public Encomenda(int id, String emailComprador, String emailVendedor, List<Artigo> listaArtigos, Transportadora transportadora, int dimensao, double precoFinal, int estado, LocalDate dataCriacao, LocalDate dataAtualizacao) {
        this.id = id;
        this.emailVendedor = emailVendedor;
        this.emailComprador = emailComprador;
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
        this.emailVendedor = encomenda.getVendedor();
        this.emailComprador = encomenda.getComprador();
        this.listaArtigos = encomenda.getListaArtigos();
        this.transportadora = encomenda.getTransportadora();
        this.dimensao = encomenda.getDimensao();
        this.precoFinal = encomenda.getPrecoFinal();
        this.estado = encomenda.getEstado();
        this.dataCriacao = encomenda.getDataCriacao();
        this.dataAtualizacao = encomenda.getDataAtualizacao();
    }

    public Encomenda(int id, LocalDate dataCriacao, String emailComprador, String emailVendedor) {
        this.id = id;
        this.emailVendedor = emailVendedor;
        this.emailComprador = emailComprador;
        this.listaArtigos = new ArrayList<>();
        this.transportadora = new Transportadora();
        this.dimensao = Atributos.PEQUENO;
        this.precoFinal = 0;
        this.estado = Atributos.PENDENTE;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataCriacao;
    }

    /////////////////////
    //Getters e Setters//
    ////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendedor()
    {
        return this.emailVendedor;
    }

    public void setVendedor(String vendedor)
    {
        this.emailVendedor = vendedor;
    }

    public String getComprador()
    {
        return this.emailComprador;
    }

    public void setComprador(String comprador)
    {
        this.emailComprador = comprador;
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
        this.transportadora = transportadora;
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

    /**
     * funçao que adiciona um artigo a encomenda
     * @param artigo
     * @throws EncomendaException
     */
    public void adicionaArtigo(Artigo artigo) throws EncomendaException {
        if (!this.listaArtigos.contains(artigo)) {
            if (this.listaArtigos.isEmpty())
            {
                this.setVendedor(artigo.getEmailVendedor());
            }
            this.listaArtigos.add(artigo);
            this.setTransportadora(artigo.getTransportadora().clone());
            this.alteraPreco();
            this.alteraDimensão(listaArtigos.size());
        } else throw new EncomendaException("Artigo já existente na encomenda!");
    }

    /**
     * funçao que remove um artigo a encomenda
     * @param artigo
     * @throws EncomendaException
     */
    public void removeArtigo(Artigo artigo) throws EncomendaException {
        if (this.listaArtigos.contains(artigo)) {
            this.listaArtigos.remove(artigo);
            this.alteraDimensão(listaArtigos.size());
            this.alteraPreco();
        } else throw new EncomendaException("Artigo não existe na encomenda!");
    }

    /**
     * funçao que calcula o valor dos artigos na encomenda
     * @return double valor final de todos os artigos
     */
    public double calculaValorArtigos()
    {
        return this.listaArtigos.stream().mapToDouble(Artigo::getPrecoBase).sum() + this.listaArtigos.stream().mapToDouble(Artigo::getCorrecaoPreco).sum();
    }

    /**
     * funçao que calcula o valor das taxas dos artigos na encomenda
     * @return double valor final das taxas
     */
    public double calculaTaxaArtigos()
    {
        double valorArtigosUsados = (this.listaArtigos.stream().filter(artigo -> !artigo.verificaNovo()).count()) * 0.25;
        double valorArtigosNovos = (this.listaArtigos.stream().filter(artigo -> artigo.verificaNovo()).count()) * 0.5;
        return valorArtigosNovos + valorArtigosUsados;
    }

    /**
     * funçao que calcula o valor de expediçao de uma encomenda
     * @return double valor final da expedicao
     */
    public double calculaValorExpedicao()
    {
        return this.transportadora.calculaValorExpedicao(this.listaArtigos.size());
    }

    /**
     * funçao que altera o preço final de uma encomenda
     */
    public void alteraPreco()
    {
        this.setPrecoFinal(this.calculaValorArtigos() + this.calculaTaxaArtigos() + this.calculaValorExpedicao());
    }

    /**
     * funçao que altera a dimensao de uma encomenda
     * @param tamanho
     */
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


    /**
     * funçao que altera o estado de uma encomenda para expedida
     * @param dataAtualizacao
     */
    public void alteraEstadoExpedido(LocalDate dataAtualizacao)
    {
        this.estado = Atributos.EXPEDIDA;
        this.setDataAtualizacao(dataAtualizacao);
    }

    /**
     * funçao que calcula a data prevista de entrega de uma encomenda
     * @return LocalDate data prevista da entrega
     */
    public LocalDate getDataPrevistaEntrega()
    {
        return this.getDataAtualizacao().plusDays(this.getTransportadora().getTempoExpedicao());
    }

    /**
     * funçao que calcula a data máxima de devoluçao de uma encomenda
     * @param tempoDevolucao
     * @return LocalDate data máxima de devoluçao
     * @throws EncomendaException
     */
    public LocalDate getDataDevolucao(int tempoDevolucao) throws EncomendaException {
        if (this.getEstado() == Atributos.FINALIZADA){
            return this.getDataAtualizacao().plusDays(tempoDevolucao);
        }
        else throw new EncomendaException("Esta encomenda não se encontra finalizada");
    }

    /**
     * funçao que altera o estado da encomenda para finalizada
     * @param dataAtualizacao
     */
    public void alteraEstadoFinalizado(LocalDate dataAtualizacao)
    {
        LocalDate dataPrevistaEntrega = this.getDataPrevistaEntrega();
        if (dataPrevistaEntrega.equals(dataAtualizacao) || dataPrevistaEntrega.isBefore(dataAtualizacao))
        {
            this.estado = Atributos.FINALIZADA;
            this.setDataAtualizacao(dataPrevistaEntrega);
        }
    }

    /**
     * funçao que altera o estado da encomenda para devolvida
     * @param dataAtualizacao
     */
    public void alteraEstadoDevolvida(LocalDate dataAtualizacao)
    {
        this.setEstado(Atributos.DEVOLVIDA);
        this.setDataAtualizacao(dataAtualizacao);
    }

    /**
     * funçao que verifica se uma encomenda é igual ao objeto fornecido
     * @param o
     * @return True se for igual, caso contrario false
     */
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

    /**
     * funçao que converte uma dimensao para String
     * @return String dimensao
     */
    private String dimensaoToString() {
        if (this.getDimensao() == Atributos.PEQUENO) {
            return "Pequena";
        }
        if (this.getDimensao() == Atributos.MEDIO) {
            return "Media";
        }
        return "Grande";
    }

    /**
     * funçao que converte o estado para String
     * @return String estado
     */
    private String estadoToString()
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

    /**
     * funçao que converte os parametros de uma encomenda para String
     * @return String de uma encomenda
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Identificador Encomenda: " + this.getId() + "\n");
        string.append("Vendedor: " + this.getVendedor() + "\n");
        string.append("Comprador: " + this.getComprador() + "\n");
        string.append(this.listaArtigos.toString());
        string.append("Dimensão: " + this.dimensaoToString() + "\n");
        string.append("Preco: " + this.precoFinal + "\n");
        string.append("Estado encomenda: " + this.estadoToString() + "\n");
        string.append("Data Criação Encomenda: " + this.getDataCriacao() + "\n");
        string.append("Data Atualização Estado: " + this.getDataAtualizacao());
        return string.toString();
    }

    /**
     * funçao que faz uma cópia do objeto
     * @return Encomenda com a cópia do objeto
     */
    public Encomenda clone() {
        return new Encomenda(this);
    }
}
