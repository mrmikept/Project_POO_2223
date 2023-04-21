import java.time.LocalDate;
import java.util.ArrayList;

public class Encomenda
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
}
