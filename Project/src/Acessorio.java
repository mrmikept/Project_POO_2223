public abstract class Acessorio extends Artigo
{
    private String material;

    public Acessorio()
    {
        super();
        this.material = "";
    }

    public Acessorio(String id, Utilizador utilizador, String descricao, String marca, double precoBase, int nrDonos, double avaliacao, Transportadora transportadora, int estadoVenda, String material)
    {
        super(id,utilizador,descricao,marca,precoBase,nrDonos,avaliacao,transportadora,estadoVenda);
        this.material = material;
    }

    public Acessorio(Acessorio acessorio)
    {
        super(acessorio);
        this.material = acessorio.getMaterial();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
        Acessorio acessorio = (Acessorio) o;
        return super.equals(acessorio) &&
                this.getMaterial().equals(acessorio.getMaterial());
    }

    public abstract Acessorio clone();
}
