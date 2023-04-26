import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
    private Map<Integer, Artigo> listaVendas;
    private Map<Integer, Artigo> listaVendidos;
    private Pedido listaCompras;
    //TODO: Historico de venda, compras e artigos à venda.


    public Utilizador() {

        this.id = 0;
        this.email = "";
        this.palavraPasse = "";
        this.nome = "";
        this.morada = "";
        this.nrFiscal = 0;
        this.listaVendas = new HashMap<>();
        this.listaVendidos = new HashMap<>();
        this.listaCompras = new Pedido();

    }

    public Utilizador(int id, String email, String palavraPasse, String nome, String morada, int nrFiscal, HashMap<Integer, Artigo> listaVendas, HashMap<Integer, Artigo> listaVendidos, Pedido pedido) {

        this.id = id;
        this.email = email;
        this.palavraPasse = palavraPasse;
        this.nome = nome;
        this.morada = morada;
        this.nrFiscal = nrFiscal;
        this.listaVendas = listaVendas;
        this.listaVendidos = listaVendidos;
        this.listaCompras = pedido;

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

    public Map<Integer, Artigo> getListaVendas(){
        return listaVendas.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaVendas(Map<Integer, Artigo> listaVendas){
        this.listaVendas = listaVendas.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Map<Integer, Artigo> getListaVendidos(){
        return listaVendidos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaVendidos(Map<Integer, Artigo> listaVendidos){
        this.listaVendidos = listaVendidos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Pedido getListaCompras(){
        return listaCompras;
    }

    public void setListaCompras(Pedido listaCompras){
        this.listaCompras = listaCompras;
    }

    public void adicionaArtigoVenda(Artigo artigo) throws UtilizadorException {
        if (!this.listaVendas.containsKey(artigo.getId()))
        {
            this.listaVendas.put(artigo.getId(),artigo.clone());
        }
        else throw new UtilizadorException("O utilizador já tem este artigo à venda!");
    }

    public void adicionaArtigoVendidos(Artigo artigo) throws UtilizadorException {
        if (this.listaVendas.containsKey(artigo.getId()))
        {
            this.listaVendas.remove(artigo.getId(),artigo);
            if (!this.listaVendidos.containsKey(artigo.getId()))
            {
                this.listaVendidos.put(artigo.getId(), artigo.clone());
            }
            else throw new UtilizadorException("O utilizador já possui este artigo na lista de vendidos!");
        }
        else throw new UtilizadorException("Este artigo não está a ser vendido pelo utilizador");
    }

    public void adicionaArtigoPedido(Artigo artigo) throws EncomendaException {
        this.listaCompras.adicionaArtigoPedido(artigo);
    }

    public void removeArtigoPedido(Artigo artigo) throws EncomendaException {
        this.listaCompras.removeArtigoPedido(artigo);
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