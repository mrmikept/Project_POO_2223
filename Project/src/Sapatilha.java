import java.io.Serializable;
import java.time.LocalDate;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Sapatilha extends Calcado implements Premium, Serializable {

    private int tipoCordao;
    private String cor;
    private int dataLancamento;
    private int tipo;

    public Sapatilha() {
        super();
        this.tipoCordao = Atributos.CORDAO;
        this.cor = "";
        this.dataLancamento = 0;
    }

    public Sapatilha(String id, String vendedor, String descricao, String marca, double precoBase, int nrDonos, double avaliacao, Transportadora transportadora, int estadoVenda, int tamanho, int tipoCordao, String cor, int dataLancamento, int tipo) {
        super(id, vendedor, descricao, marca, precoBase, nrDonos, avaliacao, transportadora, estadoVenda, tamanho);
        this.tipoCordao = tipoCordao;
        this.cor = cor;
        this.dataLancamento = dataLancamento;
        this.tipo = tipo;
    }

    public Sapatilha(Sapatilha sapatilha) {
        super(sapatilha);
        this.tipoCordao = sapatilha.getTipoCordao();
        this.cor = sapatilha.getCor();
        this.dataLancamento = sapatilha.getDataLancamento();
        this.tipo = sapatilha.getTipo();

    }

    public int getTipoCordao() {
        return tipoCordao;
    }

    public void setTipoCordao(int tipoCordao) {
        this.tipoCordao = tipoCordao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(int dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getValorizacaoPremium(int date)
    {
        return Math.round((0.6 * (date - this.getDataLancamento()) * this.getPrecoBase())* 100) / 100;
    }

    public double getCorrecaoPreco()
    {
            if (this.verificaNovo() && this.getTamanho() > 45)
            {
                return this.getPrecoBase() * (-0.2);
            }
            if (!this.verificaNovo())
            {
                return  -this.getPrecoBase() * (double) Math.round((((1 - (this.getAvaliacao() / 5.0)) * 0.6) + ((Math.pow((2),(-this.getNrDonos())) * (-1) + 1) * 0.4)) * 100.0) / 100.0;
            }
            return 0;
    }

    public double getPrecoFinal(int data)
    {
        if (this.getTipo() == Atributos.PREMIUM)
        {
            return this.getPrecoBase() + this.getValorizacaoPremium(data);
        }
        else
        {
            return this.getPrecoBase() + this.getCorrecaoPreco();
        }
    }

    private String tipoCordaoToString()
    {
        if (this.getTipoCordao() == Atributos.CORDAO)
        {
            return "Cordão";
        }
        return "Atilho";
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || o.getClass() != this.getClass())
        {
            return false;
        }
        Sapatilha sapatilha = (Sapatilha) o;
        return (super.equals(sapatilha) &&
                this.getTamanho() == sapatilha.getTamanho() &&
                this.getTipoCordao() == sapatilha.getTipoCordao() &&
                this.getCor().equals(sapatilha.getCor()) &&
                this.getDataLancamento() == sapatilha.getDataLancamento() &&
                this.getTipo() == sapatilha.getTipo());
    }

    public String showArtigoLinha() {
        return ("[Sapatilha] ");
    }

    private String tipoToString()
    {
        if (this.getTipo() == Atributos.PREMIUM)
        {
            return "Premium";
        }
        return "Normal";
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Sapatilha]" + " | ");
        string.append(super.toString());
        string.append("Tamanho: " + this.getTamanho() + " | ");
        string.append("Tipo Cordão: " + this.tipoCordaoToString() + " | ");
        string.append("Cor: " + this.getCor() + " | ");
        string.append("Data Lançamento: " + this.getDataLancamento() + " | ");
        string.append("Tipo: " + this.tipoToString() + "\n");
        return string.toString();
    }

    public Sapatilha clone() {
        return new Sapatilha(this);
    }
}