import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Encomenda implements Serializable
{
    private ArrayList<Artigo> listaArtigos;
    private int dimensao;
    private double precoFinal;
    private int estado;
    private LocalDate data;

    //Pequeno: Encomenda com apenas 1 artigo.
    public static final int PEQUENO = 1;
    //Medio: Encomenda com 2 a 5 artigos.
    public static final int MEDIO = 2;
    //Grande: Encomenda com mais de 5.
    public static final int GRANDE = 3;

    public static final int PENDENTE = 0;
    public static final int FINALIZADA = 1;
    public static final int EXPEDIDA = 2;

    public Encomenda()
    {
        this.listaArtigos = new ArrayList<Artigo>();
        this.dimensao = PEQUENO;
        this.precoFinal = 0.0; //TODO Por formula para calcular valor final;
        this.estado = PENDENTE;
        this.data = LocalDate.now();
    }

    public Encomenda(ArrayList<Artigo> listaArtigos, int dimensao, double precoFinal, int estado, LocalDate data)
    {
        //TODO clone do arraylist!
        this.dimensao = dimensao;
        this.precoFinal = precoFinal;
        this.estado = estado;
        this.data = data;
    }

    public Encomenda(Encomenda encomenda)
    {
        //TODO clone do arraylist!
        this.dimensao = encomenda.getDimensao();
        this.precoFinal = encomenda.getPrecoFinal();
        this.estado = encomenda.getEstado();
        this.data = encomenda.getData();
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Encomenda clone()
    {
        return new Encomenda(this);
    }
}
