import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que extende a classe Calcado e que contem os parametros de uma sapatilha
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

    ///////////////
    //Contrutores//
    ///////////////

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

    /////////////////////
    //Getters e Setters//
    ////////////////////

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


    /**
     * funçao que calcula a valorizacao de uma sapatilha premium com base na data
     * @param date
     * @return double valor da valorizacao premium
     */
    public double getValorizacaoPremium(int date)
    {
        return Math.round((0.6 * (date - this.getDataLancamento()) * this.getPrecoBase())* 100) / 100;
    }

    /**
     * funçao que calcula a correçao de preço de uma sapatilha normal
     * @return double valor da correçao de preço
     */
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

    /**
     * funçao que calcula o preço final de uma sapatilha
     * @param data
     * @return double preço final de uma sapatilha
     */
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

    /**
     * funçao que converte o tipo de cordao para String
     * @return String tipoCordao
     */
    private String tipoCordaoToString()
    {
        if (this.getTipoCordao() == Atributos.CORDAO)
        {
            return "Cordão";
        }
        return "Atilho";
    }

    /**
     * funçao que verifica se uma sapatilha é igual ao objeto fornecido
     * @param o
     * @return True se for igual, caso contrario false
     */
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

    /**
     * funçao que devolve uma String [Sapatilha]
     * @return String
     */
    public String showArtigoLinha() {
        return ("[Sapatilha] ");
    }

    /**
     * funçao que converte o tipo de uma sapatilha para String
     * @return String tipo
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
     * funçao que converte para String todos os parametros de uma sapatilha
     * @return String sapatilha
     */
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

    /**
     * funçao que faz uma cópia do objeto
     * @return Sapatilha com a cópia do objeto
     */
    public Sapatilha clone() {
        return new Sapatilha(this);
    }
}