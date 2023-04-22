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

    public static final int LISA = 0;
    public static final int RISCAS = 1;
    public static final int PALMEIRAS = 2;

    public static final int S = 0;
    public static final int M = 1;
    public static final int L = 2;
    public static final int XL = 3;

    public Tshirt()
    {
        super();
        this.tamanho = S;
        this.padrao = LISA;
    }

    public Tshirt(int id, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int tamanho, int padrao)
    {
        super(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora);
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
    public Tshirt clone() {
        return new Tshirt(this);
    }
}
