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
    private int id;
    private Utilizador vendedor;
    private String descricao;
    private String marca;
    private double precoBase;
    private double correcaoPreco;
    private EstadoArtigo estado;
    private Transportadora transportadora;
    private int tipo;

    public Artigo()
    {
        this.id = 0;
        this.vendedor = new Utilizador();
        this.descricao = "";
        this.marca = "";
        this.precoBase = 0.0;
        this.correcaoPreco = 0.0;
        this.estado = new EstadoArtigo();
        this.transportadora = new Transportadora();
        this.tipo = 0;
    }

    public Artigo(int id, Utilizador utilizador, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int tipo)
    {
        this.id = id;
        this.vendedor = utilizador;
        this.descricao = descricao;
        this.marca = marca;
        this.precoBase = precoBase;
        this.correcaoPreco = correcaoPreco;
        this.estado = estado.clone();
        this.transportadora = transportadora.clone();
        this.tipo = tipo;
    }

    public Artigo(Artigo artigo)
    {
        this.id = artigo.getId();
        this.vendedor = artigo.getVendedor();
        this.descricao = artigo.getDescricao();
        this.marca = artigo.getMarca();
        this.precoBase = artigo.getPrecoBase();
        this.correcaoPreco = artigo.getCorrecaoPreco();
        this.estado = artigo.getEstado();
        this.transportadora = artigo.getTransportadora();
        this.tipo = artigo.getTipo();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVendedor(Utilizador vendedor)
    {
        this.vendedor = vendedor;
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

    public void setCorrecaoPreco(double correcaoPreco) {
        this.correcaoPreco = correcaoPreco;
    }

    public void setEstado(EstadoArtigo estado)
    {
        this.estado = estado;
    }

    public void setTransportadora(Transportadora transportadora)
    {
        this.transportadora = transportadora;
    }

    public int getId() {
        return id;
    }

    public Utilizador getVendedor()
    {
        return this.vendedor;
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

    public EstadoArtigo getEstado() {
        return this.estado.clone();
    }

    public Transportadora getTransportadora() {
        return this.transportadora.clone();
    }

    public int getTipo(){ return tipo; }

    public void setTipo(int tipo){
        this.tipo = tipo;
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
                this.getMarca().equals(artigo.getMarca()) &&
                this.getPrecoBase() == artigo.getPrecoBase() &&
                this.getCorrecaoPreco() == artigo.getCorrecaoPreco() &&
                this.getTransportadora().equals(artigo.getTransportadora()) &&
                this.getEstado().equals(artigo.getEstado()) &&
                this.getDescricao().equals(artigo.getDescricao()));
    }

    public abstract Artigo clone();

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("Identificador: " + this.getId() + "\n");
        string.append("Descrição: " + this.getDescricao() + "\n");
        string.append("Marca: " + this.getMarca() + "\n");
        string.append("Preço Base: " + this.getPrecoBase() + "\n");
        string.append("Correção Preço: " + this.getCorrecaoPreco() + "\n");
        string.append("Estado: " + this.getEstado().toString() + "\n");
        string.append("Transportadora: " + this.getTransportadora().toString());

        return string.toString();
    }


}
