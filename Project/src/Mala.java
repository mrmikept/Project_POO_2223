import java.io.Serializable;
import java.time.LocalDate;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Mala extends Acessorio implements Premium, Serializable
{
    private double dimensao;
    private int anoLancamento;
    private int tipo;

    public Mala()
    {
        super();
        this.dimensao = 0.0;
        this.anoLancamento = 0;
        this.tipo = Atributos.NORMAL;
    }

    public Mala(String id, String vendedor, String descricao, String marca, double precoBase, int nrDonos, double avaliacao, Transportadora transportadora, int estadoVenda, double dimensao, String material, int anoLancamento, int tipo)
    {
        super(id, vendedor, descricao, marca, precoBase, nrDonos, avaliacao, transportadora, estadoVenda, material);
        this.dimensao = dimensao;
        this.anoLancamento = anoLancamento;
        this.tipo = tipo;
    }

    public Mala(Mala mala)
    {
        super(mala);
        this.dimensao = mala.getDimensao();
        this.anoLancamento = mala.getAnoLancamento();
        this.tipo = mala.getTipo();
    }

    public double getDimensao() {
        return dimensao;
    }

    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getValorizacaoPremium(int date)
    {
        return Math.round(((date - this.getAnoLancamento()) * 0.2) * 100) /100;
    }

    public double getCorrecaoPreco()
    {
        if (!this.verificaNovo() && this.getTipo() == Atributos.NORMAL)
        {
            return (this.getPrecoBase() / this.getDimensao()) * -1;
        }
        return 0;
    }

    public double getPrecoFinal(int data)
    {
        if (this.getTipo() == Atributos.PREMIUM)
        {
            return this.getPrecoBase() + this.getValorizacaoPremium(data);
        }
        else return this.getPrecoBase() + this.getCorrecaoPreco();
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
        Mala mala = (Mala) o;
        return (super.equals(mala) &&
                this.getTipo() == mala.getTipo() &&
                this.getMaterial().equals(mala.getMaterial()) &&
                this.getDimensao() == mala.getDimensao()) &&
                this.getAnoLancamento() == mala.getAnoLancamento();
    }

    private String tipoToString()
    {
        if (this.getTipo() == Atributos.PREMIUM)
        {
            return "Premium";
        }
        return "Normal";
    }


    public String showArtigoLinha() {
        return ("[Mala]");
    }

    public String toString()
    {
        StringBuffer string = new StringBuffer();
        string.append("[Mala]");
        string.append(super.toString());
        string.append("Dimensão: " + this.getDimensao() + " | ");
        string.append("Material: " + this.getMaterial() + " | ");
        string.append("Data Lançamento: " + this.getAnoLancamento() + " | ");
        string.append("Tipo: " + this.tipoToString());
        return string.toString();
    }

    public Mala clone()
    {
        return new Mala(this);
    }
}
