import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private List<Artigo> listaVendas;
    private List<Artigo> listaVendidos;
    private List<Encomenda>listaCompras;
    //TODO: Historico de venda, compras e artigos à venda.


    public Utilizador() {

        this.id = 0;
        this.email = "";
        this.palavraPasse = "";
        this.nome = "";
        this.morada = "";
        this.nrFiscal = 0;
        this.listaVendas = new ArrayList<>();
        this.listaVendidos = new ArrayList<>();
        this.listaCompras = new ArrayList<>();

    }

    public Utilizador(int id, String email, String palavraPasse, String nome, String morada, int nrFiscal) {

        this.id = id;
        this.email = email;
        this.palavraPasse = palavraPasse;
        this.nome = nome;
        this.morada = morada;
        this.nrFiscal = nrFiscal;
        this.listaVendas = new ArrayList<>();
        this.listaVendidos = new ArrayList<>();
        this.listaCompras = new ArrayList<>();

    }

    public Utilizador(Utilizador utilizador) {

        this.id = utilizador.getId();
        this.email = utilizador.getEmail();
        this.palavraPasse = utilizador.getPalavraPasse();
        this.nome = utilizador.getNome();
        this.morada = utilizador.getMorada();
        this.nrFiscal = utilizador.getNrFiscal();
        this.listaVendas = utilizador.getListaVendas();
        this.listaVendidos = utilizador.getListaVendidos();
        this.listaCompras = utilizador.getListaCompras();

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

    public List<Artigo> getListaVendas(){
        return listaVendas.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public void setListaVendas(List<Artigo> listaVendas){
        this.listaVendas = listaVendas.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public List<Artigo> getListaVendidos(){
        return listaVendidos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public void setListaVendidos(List<Artigo> listaVendidos){
        this.listaVendidos = listaVendidos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public List<Encomenda> getListaCompras(){
        return listaCompras.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setListaCompras(List<Encomenda> listaCompras){
        this.listaCompras = listaCompras.stream().map(Encomenda::clone).collect(Collectors.toList());
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
        Utilizador utilizador = (Utilizador) o;
        return (this.getId() == utilizador.getId() &&
                this.getEmail().equals(utilizador.getEmail()) &&
                this.getPalavraPasse().equals(utilizador.getPalavraPasse()) &&
                this.getNome().equals(utilizador.getNome()) &&
                this.getMorada().equals(utilizador.getMorada()) &&
                this.getNrFiscal() == utilizador.getNrFiscal() &&
                this.getListaVendas().equals(utilizador.getListaVendas()) &&
                this.getListaCompras().equals(utilizador.getListaCompras()) &&
                this.getListaVendidos().equals(utilizador.getListaVendidos()));
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