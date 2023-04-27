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

    public Tshirt(int id, Utilizador utilizador, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int estadoVenda, int tamanho, int padrao)
    {
        super(id, utilizador, descricao, marca, precoBase, correcaoPreco, estado, transportadora, estadoVenda);
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

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Artigo Tshirt]" + "\n");
        string.append(super.toString());
        string.append("Tamanho: " + this.tamanhoToString() + "\n");
        string.append("Padrão: " + this.padraoToString() + "\n");
        return string.toString();
    }

    @Override
    public Tshirt clone() {
        return new Tshirt(this);
    }
}
