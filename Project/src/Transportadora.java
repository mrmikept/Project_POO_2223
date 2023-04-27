import java.io.Serializable;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Transportadora implements Serializable {
    private String nome;
    private double margemLucro;
    private int tipo;
    private double imposto;
    private double txEncPq;
    private double txEncMd;
    private double txEncGd;


    public Transportadora()
    {
        this.nome = "";
        this.margemLucro = 0.0;
        this.tipo = Atributos.NORMAL;
        this.imposto = 0;
        this.txEncPq = 0;
        this.txEncMd = 0;
        this.txEncGd = 0;
    }

    public Transportadora(String nome, double lucro, int tipo, double imposto, double txEncPq, double txEncMd, double txEncGd)
    {
        this.nome = nome;
        this.margemLucro = lucro;
        this.tipo = tipo;
        this.imposto = imposto;
        this.txEncPq = txEncPq;
        this.txEncMd = txEncMd;
        this.txEncGd = txEncGd;
    }

    public Transportadora(Transportadora transportadora)
    {
        this.nome = transportadora.getNome();
        this.margemLucro = transportadora.getMargemLucro();
        this.tipo = transportadora.getTipo();
        this.imposto = transportadora.getImposto();
        this.txEncPq = transportadora.getTxEncPq();
        this.txEncMd = transportadora.getTxEncMd();
        this.txEncGd = transportadora.getTxEncGd();
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(int imposto) {
        this.imposto = imposto;
    }

    public double getTxEncPq() {
        return txEncPq;
    }

    public void setTxEncPq(double txEncPq) {
        this.txEncPq = txEncPq;
    }

    public double getTxEncMd() {
        return txEncMd;
    }

    public void setTxEncMd(double txEncMd) {
        this.txEncMd = txEncMd;
    }

    public double getTxEncGd() {
        return txEncGd;
    }

    public void setTxEncGd(double txEncGd) {
        this.txEncGd = txEncGd;
    }

    public double getTxExpedicao(int tamanhoEnc)
    {
        if (tamanhoEnc < 2)
        {
            return this.getTxEncPq();
        }
        if (tamanhoEnc > 2 && tamanhoEnc < 6)
        {
            return this.getTxEncMd();
        }
        return this.getTxEncGd();
    }

    public double calculaValorExpedicao(int tamanhoEnc)
    {
       return this.getTxExpedicao(tamanhoEnc) + (this.getTxExpedicao(tamanhoEnc) * this.getMargemLucro() * (1 + this.getImposto()));
    }

    private String tipotoString()
    {
        if (this.getTipo() == Atributos.NORMAL)
        {
            return "Normal";
        }
        return "Premium";
    }

    public String toString()
    {
        return this.getNome() + ", Margem de Lucro: " + this.getMargemLucro() + ", Tipo de transportadora: " + this.tipotoString() + "\n";
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
        Transportadora transportadora = (Transportadora) o;
        return (this.getNome().equals(transportadora.getNome()) &&
                this.getTipo() == transportadora.getTipo() &&
                this.getMargemLucro() == transportadora.getMargemLucro());
    }

    public Transportadora clone()
    {
        return new Transportadora(this);
    }
}
