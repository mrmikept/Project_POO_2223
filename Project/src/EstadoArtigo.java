import java.io.Serializable;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public abstract class EstadoArtigo implements Serializable
{
    private int tipoEstado;

    public EstadoArtigo()
    {
        this.tipoEstado = Atributos.NOVO;
    }

    public EstadoArtigo(int tipoEstado)
    {
        this.tipoEstado = tipoEstado;
    }

    public EstadoArtigo(EstadoArtigo estado)
    {
        this.tipoEstado = estado.getTipoEstado();
    }

    public void setTipoEstado(int tipoEstado){
        this.tipoEstado = tipoEstado;
    }

    public int getTipoEstado()
    {
        return tipoEstado;
    }


    public String toString()
    {
        if (this.tipoEstado == Atributos.NOVO)
        {
            return "NOVO\n";
        }
        else
        {
            return "USADO\n";
        }
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
        EstadoArtigo estadoArtigo = (EstadoArtigo) o;
        return (this.getTipoEstado() == estadoArtigo.getTipoEstado());
    }

    public abstract EstadoArtigo clone();
}
