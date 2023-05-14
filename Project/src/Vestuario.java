import java.io.Serializable;

/**
 * Classe abstata Vestuario, representa artigo do tipo vestuario
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public abstract class Vestuario extends Artigo implements Serializable
{
    private int tamanho;

    ///////////////
    //Contrutores//
    ///////////////

    public Vestuario()
    {
        super();
        this.tamanho = 0;
    }

    public Vestuario(String id, String vendedor, String descricao, String marca, double precoBase, int nrDonos, double avaliacao, Transportadora transportadora, int estadoVenda, int tamanho)
    {
        super(id,vendedor,descricao,marca,precoBase,nrDonos,avaliacao,transportadora,estadoVenda);
        this.tamanho = tamanho;
    }

    public Vestuario(Vestuario vestuario)
    {
        super(vestuario);
        this.tamanho = vestuario.getTamanho();
    }

    /////////////////////
    //Getters e Setters//
    /////////////////////

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Devolve uma String com o tamanho
     * @return Uma String com o tamanho.
     */
    public String tamanhoToString()
    {
        if (this.getTamanho() == Atributos.S)
        {
            return "S";
        }
        if (this.getTamanho() == Atributos.M)
        {
            return "M";
        }
        if (this.getTamanho() == Atributos.L)
        {
            return "L";
        }
        return "XL";
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
        Vestuario vestuario = (Vestuario) o;
        return super.equals(vestuario) &&
                this.getTamanho() == vestuario.getTamanho();
    }

    /**
     * Devolve uma string com o valor dos atributos de um vestuario
     * @return String ccom o valor dos atributos de um vestuario
     */
    public String toString()
    {
        return super.toString() +
                "Tamanho: " + this.tamanhoToString() + " | ";
    }

    /**
     * Cria uma copia de um objecto vestuario
     * @return Um Objecto vestuario
     */
    public abstract Vestuario clone();
}
