public abstract class Calcado extends Artigo
{
    private int tamanho;

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

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
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
        Calcado calcado = (Calcado) o;
        return (super.equals(calcado) &&
                this.getTamanho() == calcado.getTamanho());
    }

    public abstract Calcado clone();
}
