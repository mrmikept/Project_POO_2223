import java.time.LocalDate;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Sapatilha extends Artigo {
    private int tamanho;
    private int tipoCordao;
    private String cor;
    private LocalDate data;
    private int tipo;

    public static final int CORDAO = 0;
    public static final int ATILHO = 1;
    public static final int NORMAL = 0;
    public static final int PREMIUM = 1;

    public Sapatilha()
    {
        super();
        this.tamanho = 0;
        this.tipoCordao = CORDAO;
        this.cor = "";
        this.data = LocalDate.now();
        this.tipo = NORMAL;
    }

    public Sapatilha(int id, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int tamanho, int tipoCordao, String cor, LocalDate data, int tipo)
    {
        super(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora);
        this.tamanho = tamanho;
        this.tipoCordao = tipoCordao;
        this.cor = cor;
        this.data = data;
        this.tipo = tipo;
    }

    public Sapatilha(Sapatilha sapatilha)
    {
        super(sapatilha);
        this.tamanho = sapatilha.getTamanho();
        this.tipoCordao = sapatilha.getTipoCordao();
        this.cor = sapatilha.getCor();
        this.data = sapatilha.getData();
        this.tipo = sapatilha.getTipo();

    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getTipoCordao() {
        return tipoCordao;
    }

    public void setTipoCordao(int tipoCordao) {
        this.tipoCordao = tipoCordao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getCorrecaoPreco()
    {
        if (this.getEstado().getTipoEstado() == EstadoArtigo.USADO && this.getEstado().getNrDonos() <= 10)
        {
            return (1 - ((((double)this.getEstado().getNrDonos() / 11) * (1- ((double)this.getEstado().getAvaliacao() / 6)))));
        } else if (this.getEstado().getTipoEstado() == EstadoArtigo.USADO && this.getEstado().getNrDonos() > 10) {
            return (1 - (((10.0 / 11) * (1- ((double)this.getEstado().getAvaliacao() / 6)))));
        } else if (this.getEstado().getTipoEstado() == EstadoArtigo.NOVO && this.getTamanho() > 45) {
            return 0.3;
        }
        return 0;
    }

    public Sapatilha clone()
    {
        return new Sapatilha(this);
    }
}