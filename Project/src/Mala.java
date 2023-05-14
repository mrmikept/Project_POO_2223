import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que extende a classe Acessorio e que contem os parametros de uma mala
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

    ///////////////
    //Contrutores//
    ///////////////

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

    /////////////////////
    //Getters e Setters//
    ////////////////////

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

    /**
     * funçao que calcula a valorizaçao de uma mala premium com base na data
     * @param date
     * @return double valor da valorizacao premium
     */
    public double getValorizacaoPremium(int date)
    {
        return Math.round(((date - this.getAnoLancamento()) * 0.2) * 100) /100;
    }

    /**
     * funçao que calcula a correçao de preço de uma mala normal
     * @return double valor da correçao de preço
     */
    public double getCorrecaoPreco()
    {
        if (!this.verificaNovo() && this.getTipo() == Atributos.NORMAL)
        {
            return Math.round(((this.getPrecoBase() / this.getDimensao()) * -1) * 100) / 100;
        }
        return 0;
    }

    /**
     * funçao que calcula a preço final de uma mala
     * @param data
     * @return double preço final da mala
     */
    public double getPrecoFinal(int data)
    {
        if (this.getTipo() == Atributos.PREMIUM)
        {
            return this.getPrecoBase() + this.getValorizacaoPremium(data);
        }
        else return this.getPrecoBase() + this.getCorrecaoPreco();
    }

    /**
     * funçao que verifica se uma mala é igual ao objeto fornecido
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
        Mala mala = (Mala) o;
        return (super.equals(mala) &&
                this.getTipo() == mala.getTipo() &&
                this.getMaterial().equals(mala.getMaterial()) &&
                this.getDimensao() == mala.getDimensao()) &&
                this.getAnoLancamento() == mala.getAnoLancamento();
    }

    /**
     * funçao que converte o tipo de uma mala para String
     * @return String do tipo
     */
    private String tipoToString()
    {
        if (this.getTipo() == Atributos.PREMIUM)
        {
            return "Premium";
        }
        return "Normal";
    }

    /**
     * funçao que devolve uma String [Mala]
     * @return String
     */
    public String showArtigoLinha() {
        return ("[Mala]");
    }

    /**
     * funçao que converte para String todos os parametro de uma mala
     * @return String mala
     */
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

    /**
     * funçao que faz uma cópia do objeto
     * @return Mala com a cópia do objeto
     */
    public Mala clone()
    {
        return new Mala(this);
    }
}
