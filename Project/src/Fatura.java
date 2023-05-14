import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que contem os parametros de uma fatura
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */
public class Fatura implements Serializable
{
    private int idEncomenda;
    private Utilizador utilizador;
    private int tipo;
    private double valorArtigos;
    private double valorTaxas;
    private double valorExpedicao;
    private double valorTotal;
    private LocalDate dataFaturacao;

    ///////////////
    //Contrutores//
    ///////////////

    public Fatura()
    {
        this.idEncomenda = 0;
        this.tipo = Atributos.VENDA;
        this.utilizador = new Utilizador();
        this.valorArtigos = 0;
        this.valorExpedicao = 0;
        this.valorTaxas = 0;
        this.valorTotal = 0;
        this.dataFaturacao = LocalDate.now();
    }

    public Fatura(int idEncomenda, Utilizador utilizador, int tipo, double valorArtigos, double valorTaxas, double valorExpedicao, LocalDate dataFaturacao)
    {
        this.idEncomenda = idEncomenda;
        this.utilizador = utilizador.clone();
        this.tipo = tipo;
        this.valorArtigos = valorArtigos;
        this.valorTaxas = valorTaxas;
        this.valorExpedicao = valorExpedicao;
        this.valorTotal = (double) Math.round((valorArtigos + valorTaxas + valorExpedicao) * 100) / 100;
        this.dataFaturacao = dataFaturacao;
    }

    public Fatura(Fatura fatura)
    {
        this.idEncomenda = fatura.getIdEncomenda();
        this.utilizador = fatura.getUtilizador();
        this.tipo = fatura.getTipo();
        this.valorArtigos = fatura.getValorArtigos();
        this.valorTaxas = fatura.getValorTaxas();
        this.valorExpedicao = fatura.getValorExpedicao();
        this.valorTotal = fatura.getValorTotal();
        this.dataFaturacao = fatura.getDataFaturacao();
    }

    /////////////////////
    //Getters e Setters//
    ////////////////////

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }

    public Utilizador getUtilizador()
    {
        return this.utilizador.clone();
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador.clone();
    }

    public int getTipo()
    {
        return this.tipo;
    }

    public void setTipo(int tipo)
    {
        this.tipo = tipo;
    }

    public double getValorArtigos() {
        return valorArtigos;
    }

    public void setValorArtigos(double valorArtigos) {
        this.valorArtigos = valorArtigos;
    }

    public double getValorTaxas() {
        return valorTaxas;
    }

    public void setValorTaxas(double valorTaxas) {
        this.valorTaxas = valorTaxas;
    }

    public double getValorExpedicao() {
        return valorExpedicao;
    }

    public void setValorExpedicao(double valorExpedicao) {
        this.valorExpedicao = valorExpedicao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataFaturacao() {
        return dataFaturacao;
    }

    public void setDataFaturacao(LocalDate dataFaturacao) {
        this.dataFaturacao = dataFaturacao;
    }

    /**
     * funçao que converte todos os parametros de uma fatura para String
     * @return String de uma fatura
     */
    public String toString()
    {
        return "[Fatura]\n" +
                "Fatura referente à encomenda: " + this.getIdEncomenda() + "\n" +
                "Tipo: " + this.getTipo() + "\n" +
                "Valor dos Artigos: " + this.getValorArtigos() + "\n" +
                "Valor Taxas sobre Artigos Novos e Usados: " + this.getValorTaxas() + "\n" +
                "Valor Taxa de expedição: " + this.getValorExpedicao() + "\n" +
                "Valor total: " + this.getValorTotal() + "\n" +
                "Data de Faturacao: " + this.getDataFaturacao() + "\n";
    }

    /**
     * funçao que verifca se uma fatura é igual ao objeto fornecido
     * @param o Um objeto
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
        Fatura fatura = (Fatura) o;
        return (this.getIdEncomenda() == fatura.getIdEncomenda() &&
                this.getUtilizador().equals(fatura.getUtilizador()) &&
                this.getTipo() == fatura.getTipo() &&
                this.getValorArtigos() == fatura.getValorArtigos() &&
                this.getValorTaxas() == fatura.getValorTaxas() &&
                this.getValorExpedicao() == fatura.getValorTaxas() &&
                this.getValorTotal() == fatura.getValorTotal() &&
                this.getDataFaturacao().equals(fatura.getDataFaturacao()));
    }

    /**
     * funçao que faz uma cópia do objeto
     * @return Fatura com a cópia do objeto
     */
    public Fatura clone()
    {
        return new Fatura(this);
    }
}
