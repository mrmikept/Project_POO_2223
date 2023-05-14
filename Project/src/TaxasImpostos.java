import java.io.Serializable;

/**
 * Classe que contem todos os parametros de taxas e impostos
 */
public class TaxasImpostos implements Serializable
{
    private int imposto;
    private double taxaEncPequena;
    private double taxaEncMedia;
    private double taxaEncGrande;

    ///////////////
    //Contrutores//
    ///////////////

    public TaxasImpostos()
    {
        this.imposto = 23;
        this.taxaEncPequena = 2.55;
        this.taxaEncMedia = 3.25;
        this.taxaEncGrande = 4.15;
    }

    public TaxasImpostos(int imposto, double taxaEncPequena, double taxaEncMedia, double taxaEncGrande)
    {
        this.imposto = imposto;
        this.taxaEncPequena = taxaEncPequena;
        this.taxaEncMedia = taxaEncMedia;
        this.taxaEncGrande = taxaEncGrande;
    }

    public TaxasImpostos(TaxasImpostos taxasImpostos)
    {
        this.imposto = taxasImpostos.getImposto();
        this.taxaEncPequena = taxasImpostos.getTaxaEncPequena();
        this.taxaEncMedia = taxasImpostos.getTaxaEncMedia();
        this.taxaEncGrande = taxasImpostos.getTaxaEncGrande();
    }

    /////////////////////
    //Getters e Setters//
    ////////////////////

    public int getImposto() {
        return imposto;
    }

    public void setImposto(int imposto) {
        this.imposto = imposto;
    }

    public double getTaxaEncPequena() {
        return taxaEncPequena;
    }

    public void setTaxaEncPequena(double taxaEncPequena) {
        this.taxaEncPequena = taxaEncPequena;
    }

    public double getTaxaEncMedia() {
        return taxaEncMedia;
    }

    public void setTaxaEncMedia(double taxaEncMedia) {
        this.taxaEncMedia = taxaEncMedia;
    }

    public double getTaxaEncGrande() {
        return taxaEncGrande;
    }

    public void setTaxaEncGrande(double taxaEncGrande) {
        this.taxaEncGrande = taxaEncGrande;
    }

    /**
     * funçao que converte as taxas e impostos para String
     * @return String TaxasImpostos
     */
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[Taxas]\n");
        string.append("Taxa de imposto: " + this.getImposto());
        string.append("\nTaxa sobre encomendas pequenas: " + this.getTaxaEncPequena());
        string.append("\nTaxa sobre encomendas medias: " + this.getTaxaEncMedia());
        string.append("\nTaxa sobre encomendas grandes: " + this.getTaxaEncGrande());
        return string.toString();
    }

    /**
     * funçao que verifica se as taxas e impostos são iguais ao objeto fornecido
     * @param o
     * @return True se for igual, caso contrario false
     */
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
        TaxasImpostos taxasImpostos = (TaxasImpostos) o;
        return (this.getImposto() == taxasImpostos.getImposto() &&
                this.getTaxaEncPequena() == taxasImpostos.getTaxaEncPequena() &&
                this.getTaxaEncMedia() == taxasImpostos.getTaxaEncMedia() &&
                this.getTaxaEncGrande() == taxasImpostos.getTaxaEncGrande());
    }

    /**
     * funçao que uma cópia do objeto
     * @return TaxasImpostos com a cópia do objeto
     */
    public TaxasImpostos clone()
    {
        return new TaxasImpostos(this);
    }
}
