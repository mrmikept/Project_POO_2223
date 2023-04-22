import com.sun.jdi.PrimitiveValue;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public abstract class Artigo
{
    private int id;
    private String descricao;
    private String marca;
    private double precoBase;
    private double correcaoPreco;
    private EstadoArtigo estado;
    private Transportadora transportadora;

    public Artigo()
    {
        this.id = 0;
        this.descricao = "";
        this.marca = "";
        this.precoBase = 0.0;
        this.correcaoPreco = 0.0;
        this.estado = new EstadoArtigo();
        //TODO Construtor da transportadora
    }

    public Artigo(int id, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora)
    {
        this.id = id;
        this.descricao = descricao;
        this.marca = marca;
        this.precoBase = precoBase;
        this.correcaoPreco = correcaoPreco;
        this.estado = estado.clone();
        this.transportadora = transportadora;
    }

    public Artigo(Artigo artigo)
    {
        this.id = artigo.getId();
        this.descricao = artigo.getDescricao();
        this.marca = artigo.getMarca();
        this.precoBase = artigo.getPrecoBase();
        this.correcaoPreco = artigo.getCorrecaoPreco();
        this.estado = artigo.getEstado();
        this.transportadora = artigo.getTransportadora();
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public double getCorrecaoPreco() {
        return correcaoPreco;
    }


    public EstadoArtigo getEstado() {
        return this.estado.clone();
    }

    public Transportadora getTransportadora() {
        return this.transportadora.clone();
    }

    public abstract Artigo clone();
}
