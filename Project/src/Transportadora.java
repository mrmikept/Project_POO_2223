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
    private int tempoExpedicao;
    private double valorFaturado;
    private TaxasImpostos taxasImpostos;


    public Transportadora()
    {
        this.nome = "";
        this.margemLucro = 0.0;
        this.tipo = Atributos.NORMAL;
        this.tempoExpedicao = 0;
        this.valorFaturado = 0;
        this.taxasImpostos = new TaxasImpostos();
    }

    public Transportadora(String nome, double lucro, int tipo, int tempoExpedicao, double valorFaturado, TaxasImpostos taxasImpostos)
    {
        this.nome = nome;
        this.margemLucro = lucro;
        this.tipo = tipo;
        this.tempoExpedicao = tempoExpedicao;
        this.valorFaturado = valorFaturado;
        this.taxasImpostos = taxasImpostos;
    }

    public Transportadora(Transportadora transportadora)
    {
        this.nome = transportadora.getNome();
        this.margemLucro = transportadora.getMargemLucro();
        this.tipo = transportadora.getTipo();
        this.tempoExpedicao = transportadora.getTempoExpedicao();
        this.valorFaturado = transportadora.getValorFaturado();
        this.taxasImpostos = transportadora.getTaxasImpostos();
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

    public int getTempoExpedicao() {
        return tempoExpedicao;
    }

    public void setTempoExpedicao(int tempoExpedicao) {
        this.tempoExpedicao = tempoExpedicao;
    }

    public double getValorFaturado()
    {
        return this.valorFaturado;
    }

    public void setValorFaturado(double valorFaturado)
    {
        this.valorFaturado = valorFaturado;
    }

    public TaxasImpostos getTaxasImpostos() {
        return taxasImpostos;
    }

    public void setTaxasImpostos(TaxasImpostos taxasImpostos) {
        this.taxasImpostos = taxasImpostos;
    }

    public double getTxExpedicao(int tamanhoEnc)
    {
        if (tamanhoEnc < 2)
        {
            return this.getTaxasImpostos().getTaxaEncPequena();
        }
        if (tamanhoEnc > 2 && tamanhoEnc < 6)
        {
            return this.getTaxasImpostos().getTaxaEncMedia();
        }
        return this.getTaxasImpostos().getTaxaEncGrande();
    }

    public double calculaValorExpedicao(int tamanhoEnc)
    {
       return (double) Math.round((this.getTxExpedicao(tamanhoEnc) + (this.getTxExpedicao(tamanhoEnc) * this.getMargemLucro() * (1 + ((double)this.getTaxasImpostos().getImposto() / 100)))) * 100) / 100;
    }

    public void adicionaValorGanho(double valor)
    {
        this.valorFaturado += valor;
    }

    private String tipotoString()
    {
        if (this.getTipo() == Atributos.NORMAL)
        {
            return "Normal";
        }
        return "Premium";
    }

    public String showTransportadora(int i)
    {
        return "                                                                               " + i +") " + Apresentacao.CYAN_BOLD + "Nome: " + Apresentacao.RESET + this.getNome() + Apresentacao.CYAN_BOLD + ", Margem de Lucro: " + Apresentacao.RESET + this.getMargemLucro() + Apresentacao.CYAN_BOLD + ", Tipo: " + Apresentacao.RESET + this.tipotoString() + "\n";
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append(Apresentacao.CYAN_BOLD +"                                                                                                    [Transportadora]\n" + Apresentacao.RESET);
        string.append(Apresentacao.CYAN_BOLD +"                                                                                                    Nome: " + Apresentacao.RESET + this.getNome() + "\n");
        string.append(Apresentacao.CYAN_BOLD +"                                                                                                    Tipo: " + Apresentacao.RESET + this.getTipo() + "\n");
        string.append(Apresentacao.CYAN_BOLD +"                                                                                                    Margem Lucro: " + Apresentacao.RESET + this.getMargemLucro() + "\n");
        string.append(Apresentacao.CYAN_BOLD +"                                                                                                    Tempo de expedição: " + Apresentacao.RESET + this.getTempoExpedicao() + Apresentacao.CYAN_BOLD + " dias\n");
        string.append(Apresentacao.CYAN_BOLD +"                                                                                                    Valor Faturado: " + Apresentacao.RESET + this.getValorFaturado() + "\n");
        return string.toString();
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
                this.getMargemLucro() == transportadora.getMargemLucro()) &&
                this.getTempoExpedicao() == transportadora.getTempoExpedicao() &&
                this.getValorFaturado() == transportadora.getValorFaturado() &&
                this.getTaxasImpostos().equals(transportadora.getTaxasImpostos());
    }

    public Transportadora clone()
    {
        return new Transportadora(this);
    }
}
