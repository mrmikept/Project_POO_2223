import javax.swing.plaf.PanelUI;
import java.security.PublicKey;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Tshirt extends Artigo
{
    private int tamanho;
    private int padrao;
    private double correcaoPreco;

    public static final int LISA = 0;
    public static final int RISCAS = 1;
    public static final int PALMEIRAS = 2;


    public Tshirt()
    {
        super();
        this.tamanho = Atributos.S;
        this.padrao = LISA;
    }

    public Tshirt(int id, Utilizador utilizador, String descricao, String marca, double precoBase, EstadoArtigo estado, Transportadora transportadora, int estadoVenda, int tamanho, int padrao)
    {
        super(id, utilizador, descricao, marca, precoBase, estado, transportadora, estadoVenda);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public Tshirt(Tshirt tshirt)
    {
        super(tshirt);
        this.tamanho = tshirt.getTamanho();
        this.padrao = tshirt.getPadrao();
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getPadrao() {
        return padrao;
    }

    public void setPadrao(int padrao) {
        this.padrao = padrao;
    }

    @Override
    public double getCorrecaoPreco() {
        if (this.getEstado().getTipoEstado() == Atributos.USADO && this.getPadrao() != Tshirt.LISA)
        {
            return (this.getPrecoBase() * (-0.5));
        }
        return 0;
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
        Tshirt tshirt = (Tshirt) o;
        return (super.equals(tshirt) &&
                this.getTamanho() == tshirt.getTamanho() &&
                this.getPadrao() == tshirt.getPadrao() &&
                this.getCorrecaoPreco() == tshirt.getCorrecaoPreco());
    }

    private String tamanhoToString()
    {
        if (this.getTamanho() == Atributos.S)
        {
            return "S";
        }
        if (this.getTamanho() == Atributos.M)
        {
            return "M";
        }
        if (this.getTamanho() == Atributos.L)
        {
            return "L";
        }
        return "XL";
    }

    private String estadoToString(){
        if (this.getEstado().getTipoEstado() == Atributos.NOVO){
            return "NOVO";
        }
            return "USADO"; // (" + Apresentacao.YELLOW +"Aval: "+ Apresentacao.RESET + this.getEstado().getAvaliacao() + " | "+ Apresentacao.YELLOW +"Nr. Donos: "+ Apresentacao.RESET + this.getEstado().getNrDonos() + ")";
    }

    private String padraoToString()
    {
        if (this.getPadrao() == LISA)
        {
            return "Lisa";
        }
        if (this.getPadrao() == RISCAS)
        {
            return "Riscas";
        }
        return "Palmeiras";
    }

    public String showArtigo() {
        String tipo = "PREMIUM";
        //StringBuilder string = new StringBuilder();
        int x = this.getTransportadora().getTipo();
        if (x == 0) tipo = "NORMAL";
        return (Apresentacao.CYAN + "                                                                                   ⢀⣀⣠⣀⡀⠀⠀⠀⠀⢀⣀⣄⣀⡀            "  + Apresentacao.RED + "[Artigo Tshirt]" + Apresentacao.RESET + "\n" +
                Apresentacao.CYAN + "                                                                           ⡀⣀⣀⣤⠴⠖⠛⠋⠉⠸⣏⠙⠛⠛⠛⠛⠋⣹⠇⠉⠙⠛⠲⠦⣤⣀⣀       "  + Apresentacao.YELLOW + "Identificador: " + Apresentacao.RESET + this.getId() + "\n" +
                Apresentacao.CYAN + "                                                                           ⣿⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠳⢦⣤⣤⡴⠞⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⣿      "  + Apresentacao.YELLOW + "Descrição: " + Apresentacao.RESET + this.getDescricao() + "\n" +
                Apresentacao.CYAN + "                                                                           ⣻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟      "  + Apresentacao.YELLOW + "Marca: " + Apresentacao.RESET + this.getMarca() + "\n" +
                Apresentacao.CYAN + "                                                                           ⢿⣇⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣸⡇      "  + Apresentacao.YELLOW + "Preço Base: " + Apresentacao.RESET + this.getPrecoBase() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠉⠉⠉⠉⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠉⠉⠉⠉       " + Apresentacao.YELLOW + "Correção Preço: " + Apresentacao.RESET + this.getCorrecaoPreco() + "\n" +
                Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Estado: " + Apresentacao.RESET + this.estadoToString() + "\n" +
                Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Transportadora: " + Apresentacao.RESET + this.getTransportadora().getNome() + "\n" +
                Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Margem Lucro: " + Apresentacao.RESET + this.getTransportadora().getMargemLucro() + "\n" +
                Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Tipo de Transportadora: " + Apresentacao.RESET + tipo + "\n" +
                Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Tamanho: " + Apresentacao.RESET + this.tamanhoToString() + "\n" +
                Apresentacao.CYAN + "                                                                                ⠘⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠃           " + Apresentacao.YELLOW + "Padrão: " + Apresentacao.RESET + this.padraoToString() + "\n\n");
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Tshirt]" + " | ");
        string.append(super.toString());
        string.append("Tamanho: " + this.tamanhoToString() + " | ");
        string.append("Padrão: " + this.padraoToString() + "\n");
        return string.toString();
    }

    @Override
    public Tshirt clone() {
        return new Tshirt(this);
    }
}
