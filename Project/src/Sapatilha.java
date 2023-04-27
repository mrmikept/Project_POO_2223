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
    private LocalDate dataLancamento;
    private int tipo;

    private static final Integer CORDAO = 0;
    private static final Integer ATILHO = 1;


    public Sapatilha() {
        super();
        this.tamanho = 0;
        this.tipoCordao = CORDAO;
        this.cor = "";
        this.dataLancamento = LocalDate.now();
        this.tipo = Atributos.NORMAL;
    }

    public Sapatilha(int id, Utilizador utilizador, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int estadoVenda, int tamanho, int tipoCordao, String cor, LocalDate dataLancamento, int tipo) {
        super(id, utilizador, descricao, marca, precoBase, correcaoPreco, estado, transportadora, estadoVenda);
        this.tamanho = tamanho;
        this.tipoCordao = tipoCordao;
        this.cor = cor;
        this.dataLancamento = dataLancamento;
        this.tipo = tipo;
    }

    public Sapatilha(Sapatilha sapatilha) {
        super(sapatilha);
        this.tamanho = sapatilha.getTamanho();
        this.tipoCordao = sapatilha.getTipoCordao();
        this.cor = sapatilha.getCor();
        this.dataLancamento = sapatilha.getDataLancamento();
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getCorrecaoPreco() {
        if (this.getEstado().getTipoEstado() == Atributos.USADO)
        {
            return  -this.getPrecoBase() * (double) Math.round((((1 - (this.getEstado().getAvaliacao() / 5.0)) * 0.6) + ((Math.pow((2),(-this.getEstado().getNrDonos())) * (-1) + 1) * 0.4)) * 100.0) / 100.0;
        } else if (this.getEstado().getTipoEstado() == Atributos.NOVO && this.getTamanho() > 45) {
            return this.getPrecoBase() * (-0.2);
        }
        return 0;
    }

    private String tipoCordaoToString()
    {
        if (this.getTipoCordao() == Sapatilha.CORDAO)
        {
            return "Cordão";
        }
        return "Atilho";
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || o.getClass() != this.getClass())
        {
            return false;
        }
        Sapatilha sapatilha = (Sapatilha) o;
        return (super.equals(sapatilha) &&
                this.getTamanho() == sapatilha.getTamanho() &&
                this.getTipoCordao() == sapatilha.getTipoCordao() &&
                this.getCor().equals(sapatilha.getCor()) &&
                this.getDataLancamento().equals(sapatilha.getDataLancamento()) &&
                this.getTipo() == sapatilha.getTipo());
    }

    private String tipoToString()
    {
        if (this.getTipo() == Atributos.PREMIUM)
        {
            return "Premium";
        }
        return "Normal";
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Artigo Sapatilha]" + "\n");
        string.append(super.toString());
        string.append("Tamanho: " + this.getTamanho() + "\n");
        string.append("Tipo Cordão: " + this.tipoCordaoToString() + "\n");
        string.append("Cor: " + this.getCor() + "\n");
        string.append("Data Lançamento: " + this.getDataLancamento().toString() + "\n");
        string.append("Tipo: " + this.tipoToString());
        return string.toString();
    }

    public Sapatilha clone() {
        return new Sapatilha(this);
    }
}