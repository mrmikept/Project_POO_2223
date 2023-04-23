import java.time.LocalDate;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Mala extends Artigo
{
    private double dimensao;
    private String material;
    private LocalDate anoLancamento;
    private int tipo;

    public static final int NORMAL = 0;
    public static final int PREMIUM = 1;

    public Mala()
    {
        super();
        this.dimensao = 0.0;
        this.material = "";
        this.anoLancamento = LocalDate.now();
        this.tipo = NORMAL;
    }

    public Mala(int id, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, double dimensao, String material, LocalDate anoLancamento, int tipo)
    {
        super(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora);
        this.dimensao = dimensao;
        this.material = material;
        this.anoLancamento = anoLancamento;
        this.tipo = tipo;
    }

    public Mala(Mala mala)
    {
        super(mala);
        this.dimensao = mala.getDimensao();
        this.material = mala.getMaterial();
        this.anoLancamento = mala.getAnoLancamento();
        this.tipo = mala.getTipo();
    }

    public double getDimensao() {
        return dimensao;
    }

    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getCorrecaoPreco()
    {
        if (this.getEstado().getTipoEstado() == EstadoArtigo.USADO)
        {
            return (this.getPrecoBase() / this.getDimensao()) * -1;
        }
        return 0;
    }

    public Mala clone()
    {
        return new Mala(this);
    }
}
