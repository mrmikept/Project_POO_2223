import java.io.Serializable;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public abstract class Artigo implements Serializable
{
    private String id;
    private String emailVendedor;
    private String descricao;
    private String marca;
    private double precoBase;
    private Transportadora transportadora;
    private int nrDonos;
    private double avaliacao;
    private int estadoVenda;

    public Artigo()
    {
        this.id = "";
        this.emailVendedor = "";
        this.descricao = "";
        this.marca = "";
        this.precoBase = 0.0;
        this.nrDonos = 0;
        this.avaliacao = 0;
        this.transportadora = new Transportadora();
        this.estadoVenda = Atributos.VENDA;
    }

    public Artigo(String id, String emailVendedor, String descricao, String marca, double precoBase, int nrDonos, double avaliacao, Transportadora transportadora, int estadoVenda)
    {
        this.id = id;
        this.emailVendedor = emailVendedor;
        this.descricao = descricao;
        this.marca = marca;
        this.precoBase = precoBase;
        this.nrDonos = nrDonos;
        this.avaliacao = avaliacao;
        this.transportadora = transportadora;
        this.estadoVenda = estadoVenda;
    }

    public Artigo(Artigo artigo)
    {
        this.id = artigo.getId();
        this.emailVendedor = artigo.getEmailVendedor();
        this.descricao = artigo.getDescricao();
        this.marca = artigo.getMarca();
        this.precoBase = artigo.getPrecoBase();
        this.nrDonos = artigo.getNrDonos();
        this.avaliacao = artigo.getAvaliacao();
        this.transportadora = artigo.getTransportadora();
        this.estadoVenda = artigo.getEstadoVenda();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmailVendedor(String emailVendedor)
    {
        this.emailVendedor = emailVendedor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public void setNrDonos(int nrDonos)
    {
        this.nrDonos = nrDonos;
    }

    public void setAvaliacao(double avaliacao)
    {
        this.avaliacao = avaliacao;
    }

    public void setTransportadora(Transportadora transportadora)
    {
        this.transportadora = transportadora;
    }

    public String getId() {
        return id;
    }

    public String getEmailVendedor()
    {
        return this.emailVendedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public abstract double getCorrecaoPreco();

    public abstract double getPrecoFinal(int data);

    public int getNrDonos()
    {
        return this.nrDonos;
    }

    public double getAvaliacao()
    {
        return this.avaliacao;
    }

    public Transportadora getTransportadora() {
        return this.transportadora;
    }

    public int getEstadoVenda(){ return estadoVenda; }

    public void setEstadoVenda(int estadoVenda){
        this.estadoVenda = estadoVenda;
    }

    public boolean verificaNovo()
    {
        if (this.getNrDonos() > 0)
        {
            return false;
        }
        else return true;
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
        Artigo artigo = (Artigo) o;
        return (this.getId() == artigo.getId() &&
                this.getEmailVendedor().equals(artigo.getEmailVendedor()) &&
                this.getMarca().equals(artigo.getMarca()) &&
                this.getPrecoBase() == artigo.getPrecoBase() &&
                this.getTransportadora().equals(artigo.getTransportadora()) &&
                this.getNrDonos() == artigo.getNrDonos() &&
                this.getAvaliacao() == artigo.getAvaliacao() &&
                this.getDescricao().equals(artigo.getDescricao()));
    }

    public abstract Artigo clone();

    public abstract String showArtigoLinha();

    public String estadoToString()
    {
        if (this.verificaNovo()){
            return "NOVO";
        }
        return "USADO (" + Apresentacao.YELLOW +"Aval: "+ Apresentacao.RESET + this.getAvaliacao() + " | "+ Apresentacao.YELLOW +"Nr. Donos: "+ Apresentacao.RESET + this.getNrDonos() + ")";
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();

        string.append("Identificador: " + this.getId() + " | ");
        string.append("Descrição: " + this.getDescricao() + " | ");
        string.append("Marca: " + this.getMarca() + " | ");
        string.append("Preço Base: " + this.getPrecoBase() + " | ");
        string.append("Correção Preço: " + this.getCorrecaoPreco() + " | ");
        string.append("Estado: " + this.estadoToString() + " | ");
        string.append("Transportadora: " + this.getTransportadora().toString());
        string.append("Estado Venda: " + this.getEstadoVenda() + " | ");
        return string.toString();
    }


}
