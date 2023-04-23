import java.io.Serializable;

/**
 * Descrição classe
 *
 * @author Lucas Oliveira A98695
 * @author Mike Pinto A89292
 * @author Rafael Gomes A96208
 */

public class Utilizador implements Serializable {
    private int id;
    private String email;
    private String palavraPasse;
    private String nome;
    private String morada;
    private int nrFiscal;
    //TODO: Historico de venda, compras e artigos à venda.


    public Utilizador() {

        this.id = 0;
        this.email = "";
        this.palavraPasse = "";
        this.nome = "";
        this.morada = "";
        this.nrFiscal = 0;

    }

    public Utilizador(int id, String email, String palavraPasse, String nome, String morada, int nrFiscal) {

        this.id = id;
        this.email = email;
        this.palavraPasse = palavraPasse;
        this.nome = nome;
        this.morada = morada;
        this.nrFiscal = nrFiscal;

    }

    public Utilizador(Utilizador utilizador) {

        this.id = utilizador.getId();
        this.email = utilizador.getEmail();
        this.palavraPasse = utilizador.getPalavraPasse();
        this.nome = utilizador.getNome();
        this.morada = utilizador.getMorada();
        this.nrFiscal = utilizador.getNrFiscal();

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPalavraPasse()
    {
        return palavraPasse;
    }

    public void setPalavraPasse(String palavraPasse)
    {
        this.palavraPasse = palavraPasse;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return this.morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNrFiscal() {
        return this.nrFiscal;
    }

    public void setNrFiscal(int nrFiscal) {
        this.nrFiscal = nrFiscal;
    }


    public Utilizador clone() {
        return new Utilizador(this);
    }

    public String toString()
    {
        return "[Utilizador]" + "\n" +
                "Numero: " + this.getId() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Nome: " + this.getNome() + "\n" +
                "Morada: " + this.getMorada() + "\n" +
                "Numero Fiscal: " + this.getNrFiscal() + "\n";
        //TODO Historico de compras e vendas Maybe???
    }
}