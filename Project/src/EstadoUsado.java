public class EstadoUsado extends EstadoArtigo
{
    private double avaliacao;
    private int nrDonos;

    public EstadoUsado()
    {
        super(Atributos.USADO);
        this.avaliacao = 0;
        this.nrDonos = 0;
    }

    public EstadoUsado(int estado, double avaliacao, int nrDonos)
    {
        super(estado);
        this.avaliacao = avaliacao;
        this.nrDonos = nrDonos;
    }

    public EstadoUsado(EstadoUsado estadoUsado)
    {
        super(estadoUsado);
        this.nrDonos = estadoUsado.getNrDonos();
        this.avaliacao = estadoUsado.getAvaliacao();
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getNrDonos() {
        return nrDonos;
    }

    public void setNrDonos(int nrDonos) {
        this.nrDonos = nrDonos;
    }

    public String toString()
    {
        return super.toString() +
                "Avaliação: " + this.getAvaliacao() +
                "Numero de Donos: " + this.getNrDonos();
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
        EstadoUsado estadoUsado = (EstadoUsado) o;
        return (super.equals(estadoUsado) &&
                this.getNrDonos() == estadoUsado.getNrDonos() &&
                this.getAvaliacao() == estadoUsado.getAvaliacao());
    }

    public EstadoUsado clone()
    {
        return new EstadoUsado(this);
    }

}
