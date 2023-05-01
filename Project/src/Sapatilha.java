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

    public Sapatilha(int id, Utilizador utilizador, String descricao, String marca, double precoBase, EstadoArtigo estado, Transportadora transportadora, int estadoVenda, int tamanho, int tipoCordao, String cor, LocalDate dataLancamento, int tipo) {
        super(id, utilizador, descricao, marca, precoBase, estado, transportadora, estadoVenda);
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

    private String estadoToString(){
        if (this.getEstado().getTipoEstado() == Atributos.NOVO){
            return "NOVO";
        }
        return "USADO (" + Apresentacao.YELLOW +"Aval: "+ Apresentacao.RESET + this.getEstado().getAvaliacao() + " | "+ Apresentacao.YELLOW +"Nr. Donos: "+ Apresentacao.RESET + this.getEstado().getNrDonos() + ")";
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

    public String showArtigo() {
        String tipo = "PREMIUM";
        int x = this.getTransportadora().getTipo();
        if (x == 0) tipo = "NORMAL";
        return (Apresentacao.RED +    "                                                                                                             [Artigo Sapatilha]\n" +
                Apresentacao.YELLOW + "                                                                                                             Identificador: " + Apresentacao.RESET + this.getId() + "\n" +
                Apresentacao.YELLOW + "                                                                                                             Descrição: " + Apresentacao.RESET + this.getDescricao() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⠟⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀    "   + Apresentacao.YELLOW + "Marca: " + Apresentacao.RESET + this.getMarca() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣴⣾⠿⢦⣴⠏⠀⠀⣴⢿⡄⠀⠀⠀⠀ ⠀    "   + Apresentacao.YELLOW + "Preço Base: " + Apresentacao.RESET + this.getPrecoBase() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⢀⣀⣀⣤⣴⡶⢻⣏⠻⡦⠙⠇⠀⠉⠻⠶⠞⠫⡼⣧⠀⠀⠀⠀ ⠀    "   + Apresentacao.YELLOW + "Correção Preço: " + Apresentacao.RESET + this.getCorrecaoPreco() + "\n" +
                Apresentacao.CYAN + "                                                                           ⣴⠞⠛⠛⠋⠉⠉⠀⠀⠋⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⣦⡀⠀⠀⠀⠀ ⠀    "   + Apresentacao.YELLOW + "Estado: " + Apresentacao.RESET + this.estadoToString() + "\n" +
                Apresentacao.CYAN + "                                                                           ⣿⣷⡶⠶⣤⣤⣤⣤⣤⠶⠶⠶⠛⠛⠛⠛⢛⣠⣴⣾⣏⣀⡾⠁⠀⢀⣶⡄ ⠀    "   + Apresentacao.YELLOW + "Transportadora: " + Apresentacao.RESET + this.getTransportadora().getNome() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠉⠙⠛⠳⠶⠶⠶⣤⠀⠀⢀⣀⣤⣴⡾⢻⣍⠻⣦⠈⠙⢷⣤⣴⠟⣩⣷ ⠀    "   + Apresentacao.YELLOW + "Margem Lucro: " + Apresentacao.RESET + this.getTransportadora().getMargemLucro() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⢀⣴⠶⠶⠶⠚⠛⠋⠉⠻⠂⠙⠀⠉⠀⠀⠀⠀⠀⠀⣠⡞⠉⣿ ⠀    "   + Apresentacao.YELLOW + "Tipo de Transportadora: " + Apresentacao.RESET + tipo + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⣿⣧⣤⣤⣀⣀⣀⣀⣀⣀⣤⣤⣤⠶⠶⠶⠶⠶⠶⠶⠿⠶⢶⣏ ⠀    "   + Apresentacao.YELLOW + "Tamanho: " + Apresentacao.RESET + this.getTamanho() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠉⠛⠳⠶⢯⣭⣭⣍⣉⣉⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⡿ ⠀    "   + Apresentacao.YELLOW + "Tipo Cordão: " + Apresentacao.RESET + this.tipoCordaoToString() +  "\n" +
                Apresentacao.YELLOW + "                                                                                                             Cor: " + Apresentacao.RESET + this.getCor() + "\n" +
                Apresentacao.YELLOW + "                                                                                                             Data Lançamento: " + Apresentacao.RESET + this.getDataLancamento().toString() + "\n" +
                Apresentacao.YELLOW + "                                                                                                             Tipo: " + Apresentacao.RESET + this.tipoToString() + "\n\n");
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