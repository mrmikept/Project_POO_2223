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
        if (this.getEstado().getTipoEstado() == EstadoArtigo.USADO && this.getTipo() == NORMAL)
        {
            return (this.getPrecoBase() / this.getDimensao()) * -1;
        }
        //TODO Acrescimo tipo Premium
        return 0;
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() == o.getClass())
        {
            return false;
        }
        Mala mala = (Mala) o;
        return (super.equals(mala) &&
                this.getTipo() == mala.getTipo() &&
                this.getMaterial().equals(mala.getMaterial()) &&
                this.getDimensao() == mala.getDimensao()) &&
                this.getAnoLancamento().equals(mala.getAnoLancamento());
    }

    private String tipoToString()
    {
        if (this.getTipo() == Sapatilha.PREMIUM)
        {
            return "Premium";
        }
        return "Normal";
    }

    public String toString()
    {
        StringBuffer string = new StringBuffer();
        string.append("[Artigo Mala]");
        string.append(super.toString());
        string.append("Dimensão: " + this.getDimensao() + "\n");
        string.append("Material: " + this.getMaterial() + "\n");
        string.append("Data Lançamento: " + this.getAnoLancamento().toString() + "\n");
        string.append("Tipo: " + this.tipoToString());
        return string.toString();
    }

    public Mala clone()
    {
        return new Mala(this);
    }
}
