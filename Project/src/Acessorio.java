import java.io.Serializable;

/**
 * Classe abstata Acessorio, representa artigo do tipo acessorio
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public abstract class Acessorio extends Artigo implements Serializable
{
    private String material;

    ///////////////
    //Contrutores//
    ///////////////

    public Acessorio()
    {
        super();
        this.material = "";
    }

    public Acessorio(String id, String vendedor, String descricao, String marca, double precoBase, int nrDonos, double avaliacao, Transportadora transportadora, int estadoVenda, String material)
    {
        super(id,vendedor,descricao,marca,precoBase,nrDonos,avaliacao,transportadora,estadoVenda);
        this.material = material;
    }

    public Acessorio(Acessorio acessorio)
    {
        super(acessorio);
        this.material = acessorio.getMaterial();
    }

    /////////////////////
    //Getters e Setters//
    /////////////////////

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
        Acessorio acessorio = (Acessorio) o;
        return super.equals(acessorio) &&
                this.getMaterial().equals(acessorio.getMaterial());
    }

    /**
     * Devolve uma string com o valor dos atributos de um acessorio
     * @return String com o valor dos atributos de um acessorio
     */
    public String totring()
    {
        return "\nTamaho: " + this.getMaterial();
    }

    /**
     * Cria uma copia de um objecto
     * @return Um Objecto Acessorio
     */
    public abstract Acessorio clone();
}
