public class EstadoNovo extends EstadoArtigo
{
    public EstadoNovo()
    {
        super(Atributos.NOVO);
    }

    public EstadoNovo(int estado)
    {
        super(estado);
    }

    public EstadoNovo(EstadoNovo estadoNovo)
    {
        super(estadoNovo);
    }

    public EstadoNovo clone()
    {
        return new EstadoNovo(this);
    }

    public String toString()
    {
        return super.toString();
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
        EstadoNovo estadoNovo = (EstadoNovo) o;
        return super.equals(estadoNovo);
    }

}
