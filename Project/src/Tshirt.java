import java.io.Serializable;

/**
 * Classe que representa artigos do tipo Tshirt.
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Tshirt extends Vestuario implements Serializable
{
    private int padrao;

    /**
     * Variavel de instacia estatica para representar o padrão Liso
     */
    public static final int LISA = 0;

    /**
     * Variavel de instacia estatica para representar o padrão Riscas
     */
    public static final int RISCAS = 1;

    /**
     * Variavel de instacia estatica para representar o padrão Palmeiras
     */
    public static final int PALMEIRAS = 2;


    ///////////////
    //Contrutores//
    ///////////////

    public Tshirt()
    {
        super();
        this.padrao = LISA;
    }

    public Tshirt(String id, String vendedor, String descricao, String marca, double precoBase, int nrDonos, double avaliacao, Transportadora transportadora, int estadoVenda, int tamanho, int padrao)
    {
        super(id, vendedor, descricao, marca, precoBase, nrDonos, avaliacao, transportadora, estadoVenda, tamanho);
        this.padrao = padrao;
    }

    public Tshirt(Tshirt tshirt)
    {
        super(tshirt);
        this.padrao = tshirt.getPadrao();
    }

    /////////////////////
    //Getters e Setters//
    /////////////////////

    public int getPadrao() {
        return padrao;
    }

    public void setPadrao(int padrao) {
        this.padrao = padrao;
    }

    @Override
    public double getCorrecaoPreco() {
        if (!this.verificaNovo() && this.getPadrao() != Tshirt.LISA)
        {
            return Math.round((this.getPrecoBase() * (-0.5)) * 100.0) / 100.0;
        }
        return 0;
    }

    public double getPrecoFinal(int date)
    {
        return this.getPrecoBase() + this.getCorrecaoPreco();
    }

    /**
     * Metodo que devolve uma string com o padrão da tshirt
     * @return Uma string
     */
    public String padraoToString()
    {
        if (this.getPadrao() == LISA)
        {
            return "Lisa";
        }
        if (this.getPadrao() == RISCAS)
        {
            return "Riscas";
        }
        return "Palmeiras";
    }

    /**
     * Metodo que devolve uma string com o tipo do artigo
     * @return Uma string "[Tshirt] "
     */
    public String showArtigoLinha() {
        return ("[Tshirt] ");
    }


    /**
     * Metodo que verifica a igualdade entre objectos
     * @param o Um objeto
     * @return true se forem iguais, false caso contrario
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
        Tshirt tshirt = (Tshirt) o;
        return super.equals(tshirt) &&
                this.getPadrao() == tshirt.getPadrao();
    }

    /**
     * Devolve uma string com o valor dos atributos de uma tshirt
     * @return String ccom o valor dos atributos de uma tshirt
     */
    public String toString()
    {
        return "[Tshirt]" + " | " +
                super.toString() +
                "Padrão: " + this.padraoToString() + "\n";
    }

    /**
     * Cria uma copia de um objecto Tshirt
     * @return Um Objecto Tshirt
     */
    @Override
    public Tshirt clone() {
        return new Tshirt(this);
    }
}
