public class TaxasImpostos
{
    private int imposto;
    private double taxaEncPequena;
    private double taxaEncMedia;
    private double taxaEncGrande;

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

    public TaxasImpostos clone()
    {
        return new TaxasImpostos(this);
    }
}
