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
    private Map<Integer, Artigo> listaArtigos;
    private List<Encomenda> listaEncomendas;


    public Utilizador() {

        this.id = 0;
        this.email = "";
        this.palavraPasse = "";
        this.nome = "";
        this.morada = "";
        this.nrFiscal = 0;
        this.listaArtigos = new HashMap<>();
        this.listaEncomendas = new ArrayList<>();

    }

    public Utilizador(int id, String email, String palavraPasse, String nome, String morada, int nrFiscal, HashMap<Integer, Artigo> listaArtigos, ArrayList<Encomenda> listaEncomendas) {

        this.id = id;
        this.email = email;
        this.palavraPasse = palavraPasse;
        this.nome = nome;
        this.morada = morada;
        this.nrFiscal = nrFiscal;
        this.listaArtigos = listaArtigos;
        this.listaEncomendas = listaEncomendas;

    }

    public Utilizador(Utilizador utilizador) {

        this.id = utilizador.getId();
        this.email = utilizador.getEmail();
        this.palavraPasse = utilizador.getPalavraPasse();
        this.nome = utilizador.getNome();
        this.morada = utilizador.getMorada();
        this.nrFiscal = utilizador.getNrFiscal();
        this.listaArtigos = utilizador.getListaArtigos();
        this.listaEncomendas = utilizador.getListaEncomendas();

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

    public Map<Integer, Artigo> getListaArtigos(){
        return listaArtigos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaArtigos(Map<Integer, Artigo> listaArtigos){
        this.listaArtigos = listaArtigos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }


    public List<Encomenda> getListaEncomendas(){
        return this.listaEncomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setListaEncomendas(List<Encomenda> listaEncomendas)
    {
        this.listaEncomendas = listaEncomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void adicionaArtigo(Artigo artigo) throws UtilizadorException {
        if (!this.listaArtigos.containsKey(artigo.getId()))
        {
            this.listaArtigos.put(artigo.getId(),artigo);
        }
        else throw new UtilizadorException("O utilizador já tem este artigo à venda!");
    }

    public void removeArtigo(Artigo artigo) throws UtilizadorException {
        if (this.listaArtigos.containsKey(artigo.getId()))
        {
            this.listaArtigos.remove(artigo.getId(),artigo);
        }
        else throw new UtilizadorException("Este artigo não existe!");
    }

    public void adicionaEncomenda(Encomenda encomenda)
    {
        this.listaEncomendas.add(encomenda);
    }

    public void adicionaArtigoEncomenda(Artigo artigo) throws EncomendaException {
        //TODO ???????????????
        if (!this.listaEncomendas.isEmpty())
        {
            if (!this.listaEncomendas.stream().filter(enc -> enc.getTransportadora().equals(artigo.getTransportadora()) && enc.getEstado() == Atributos.PENDENTE).toList().isEmpty())
            {
                //Encomenda encomenda = this.listaEncomendas.stream().filter(enc -> enc.getTransportadora().equals(artigo.getTransportadora()) && enc.getEstado() == Atributos.PENDENTE).toList().get(0);
                //encomenda.adicionaArtigo(artigo);
                System.out.println("LOOOOOOL");
            }
            else
            {
                Encomenda novaEncomenda = new Encomenda();
                novaEncomenda.adicionaArtigo(artigo);
                this.listaEncomendas.add(novaEncomenda);
                System.out.println("LOOOOOOL2");
            }
        }
        else
        {
            Encomenda novaEncomenda = new Encomenda();
            novaEncomenda.adicionaArtigo(artigo);
            this.listaEncomendas.add(novaEncomenda);
        }

    }

    public void removeArtigoEncomenda(Artigo artigo) throws EncomendaException {
        //TODO ???????????????????
        Encomenda encomenda = this.listaEncomendas.stream().filter(enc -> enc.getTransportadora().equals(artigo.getTransportadora()) && enc.getEstado() == Atributos.PENDENTE).toList().get(0);
        if (encomenda != null)
        {
            encomenda.removeArtigo(artigo);
        }
        else throw new EncomendaException("Este artigo não está atribuido a nenhuma encomenda!");
    }


    public List<Encomenda> getEncomendaPendente(String nomeTransportadora)
    {
        return this.listaEncomendas.stream().filter(encomenda -> encomenda.getTransportadora().getNome().equals(nomeTransportadora) && encomenda.getEstado() == Atributos.PENDENTE).toList();
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
                this.getListaArtigos().equals(utilizador.getListaArtigos()) &&
                this.getListaEncomendas().equals(utilizador.getListaEncomendas()));
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