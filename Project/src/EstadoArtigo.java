import java.io.Serializable;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class EstadoArtigo implements Serializable
{
    private int tipoEstado;
    private double avaliacao;
    private int nrDonos;

    public static final int NOVO = 0;
    public static final int USADO = 1;

    //TODO DEFINE AVALIACAO POR OMISSÃO 5 ESTRELAS

    public EstadoArtigo()
    {
        this.tipoEstado = NOVO;
        this.avaliacao = 0.0;
        this.nrDonos = 0;
    }

    public EstadoArtigo(int tipoEstado, double avaliacao, int nrDonos)
    {
        this.tipoEstado = tipoEstado;
        this.avaliacao = avaliacao;
        this.nrDonos = nrDonos;
    }

    public EstadoArtigo(EstadoArtigo estado)
    {
        this.tipoEstado = estado.getTipoEstado();
        this.avaliacao = estado.getAvaliacao();
        this.nrDonos = estado.getNrDonos();
    }

    public void setTipoEstado(int tipoEstado){
        this.tipoEstado = tipoEstado;
    }
    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setNrDonos(int nrDonos) {
        this.nrDonos = nrDonos;
    }

    public int getTipoEstado()
    {
        return tipoEstado;
    }
    public double getAvaliacao() {
        return avaliacao;
    }

    public int getNrDonos() {
        return nrDonos;
    }

    public EstadoArtigo clone()
    {
        return new EstadoArtigo(this);
    }
}
