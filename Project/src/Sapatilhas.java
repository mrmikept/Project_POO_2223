import java.time.LocalDate;

public class Sapatilhas extends Artigo {
    private int tamanho;
    private int tipoCordao;
    private String cor;
    private LocalDate data;
    private int tipo;

    public static final int CORDAO = 0;
    public static final int ATILHO = 1;
    public static final int NORMAL = 0;
    public static final int PREMIUM = 1;
}