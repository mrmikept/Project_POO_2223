import java.io.Serializable;

public abstract class Vestuario extends Artigo implements Serializable
{
    private int tamanho;

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

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

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

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append(super.toString());
        string.append("Tamanho: " + this.tamanhoToString() + " | ");
        return string.toString();
    }

    public abstract Vestuario clone();
}
