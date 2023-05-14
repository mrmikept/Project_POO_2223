import java.io.Serializable;

/**
 * Classe abstata Calcado, representa um artigo do tipo calçado
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */

public abstract class Calcado extends Artigo implements Serializable
{
    private int tamanho;


    ///////////////
    //Contrutores//
    ///////////////

    public Calcado()
    {
        super();
        this.tamanho = 0;
    }

    public Calcado(String id, String vendedor, String descricao, String marca, double precoBase, int nrDonos, double avaliacao, Transportadora transportadora, int estadoVenda, int tamanho)
    {
        super(id, vendedor, descricao, marca, precoBase, nrDonos, avaliacao, transportadora, estadoVenda);
        this.tamanho = tamanho;
    }

    public Calcado(Calcado calcado)
    {
        super(calcado);
        this.tamanho = calcado.getTamanho();
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
        Calcado calcado = (Calcado) o;
        return (super.equals(calcado) &&
                this.getTamanho() == calcado.getTamanho());
    }

    /**
     * Metodo toString calçado
     * @return String com o conteudo do sistema
     */
    public String totring()
    {
        return "\nTamaho: " + this.getTamanho();
    }

    /**
     * Cria uma copia de um objecto
     * @return Um Objecto Calcado
     */
    public abstract Calcado clone();
}
