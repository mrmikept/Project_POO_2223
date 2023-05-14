import javax.swing.plaf.PanelUI;
import java.security.PublicKey;
import java.time.LocalDate;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Tshirt extends Vestuario
{
    private int padrao;

    public static final int LISA = 0;
    public static final int RISCAS = 1;
    public static final int PALMEIRAS = 2;


    public Tshirt()
    {
        super();
        this.padrao = LISA;
    }

    public Tshirt(String id, String vendedor, String descricao, String marca, double precoBase, int nrDonos, double avaliacao, Transportadora transportadora, int estadoVenda, int tamanho, int padrao)
    {
        super(id, vendedor, descricao, marca, precoBase, nrDonos, avaliacao, transportadora, estadoVenda, tamanho);
        this.padrao = padrao;
    }

    public Tshirt(Tshirt tshirt)
    {
        super(tshirt);
        this.padrao = tshirt.getPadrao();
    }

    public int getPadrao() {
        return padrao;
    }

    public void setPadrao(int padrao) {
        this.padrao = padrao;
    }

    @Override
    public double getCorrecaoPreco() {
        if (!this.verificaNovo() && this.getPadrao() != Tshirt.LISA)
        {
            return (this.getPrecoBase() * (-0.5));
        }
        return 0;
    }

    public double getPrecoFinal(int date)
    {
        return this.getPrecoBase() + this.getCorrecaoPreco();
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
        return super.equals(tshirt) &&
                this.getPadrao() == tshirt.getPadrao();
    }


    public String padraoToString()
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

    public String showArtigoLinha() {
        return ("[Tshirt] ");
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Tshirt]" + " | ");
        string.append(super.toString());
        string.append("Padrão: " + this.padraoToString() + "\n");
        return string.toString();
    }

    @Override
    public Tshirt clone() {
        return new Tshirt(this);
    }
}
