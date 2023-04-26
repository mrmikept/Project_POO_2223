import jdk.jshell.execution.Util;

import java.io.Serializable;
import java.security.PublicKey;
import java.time.LocalDate;
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
public class Sistema implements Serializable
{
    private Map<String,Utilizador> listaUtilizadores;
    private Map<String, Transportadora> listaTransportadoras;
    private Map<Integer, Artigo> listaArtigosVenda;
    private Map<Integer, Artigo> listaArtigosComprados;
    private Map<String, Pedido> listaEncomendas;
    private LocalDate dataSistema;

    private int imposto;
    private double taxaEncPequena;
    private double taxaEncMedia;
    private double taxaEncGrande;

    private static final int TAXAIMPOSTO_OMISSAO = 23;
    private static final double TAXAENC_PQ_OMISSAO = 2.55;
    private static final double TAXAENC_MD_OMISSAO = 3.25;
    private static final double TAXAENC_GD_OMISSAO = 4.15;

    public Sistema()
    {
        this.listaUtilizadores = new HashMap<>();
        this.listaTransportadoras = new HashMap<>();
        this.listaArtigosVenda = new HashMap<>();
        this.listaArtigosComprados = new HashMap<>();
        this.listaEncomendas = new HashMap<>();
        this.dataSistema = LocalDate.now();
        this.imposto = TAXAIMPOSTO_OMISSAO;
        this.taxaEncPequena = TAXAENC_PQ_OMISSAO;
        this.taxaEncMedia = TAXAENC_MD_OMISSAO;
        this.taxaEncGrande = TAXAENC_GD_OMISSAO;
    }

    public Sistema(Map<String, Utilizador> listaUtilizadores, Map<String,Transportadora> listaTransportadoras, Map<Integer,Artigo> listaArtigosVenda, Map<Integer,Artigo> listaArtigosComprados, Map<String, Pedido> listaEncomendas, LocalDate dataSistema, int imposto, double taxaEncPequena, double taxaEncMedia, double taxaEncGrande)
    {
        this.listaUtilizadores = listaUtilizadores;
        this.listaTransportadoras = listaTransportadoras;
        this.listaArtigosVenda = listaArtigosVenda;
        this.listaArtigosComprados = listaArtigosComprados;
        this.listaEncomendas = listaEncomendas;
        this.dataSistema = dataSistema;
        this.imposto = imposto;
        this.taxaEncPequena = taxaEncPequena;
        this.taxaEncMedia = taxaEncMedia;
        this.taxaEncGrande = taxaEncGrande;
    }

    public Sistema(Sistema sistema)
    {
        this.listaUtilizadores = sistema.getListaUtilizadores();
        this.listaTransportadoras = sistema.getListaTransportadoras();
        this.listaArtigosVenda = sistema.getListaArtigosVenda();
        this.listaArtigosComprados = sistema.getListaArtigosComprados();
        this.listaEncomendas = sistema.getListaEncomendas();
        this.dataSistema = sistema.getDataSistema();
    }

    public Map<String, Utilizador> getListaUtilizadores() {
        return listaUtilizadores.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaUtilizadores(Map<String, Utilizador> listaUtilizadores) {
        this.listaUtilizadores = listaUtilizadores.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));;
    }

    public Map<String, Transportadora> getListaTransportadoras() {
        return listaTransportadoras.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaTransportadoras(Map<String, Transportadora> listaTransportadoras) {
        this.listaTransportadoras = listaTransportadoras.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));;
    }

    public Map<Integer, Artigo> getListaArtigosVenda() {
        return listaArtigosVenda.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e-> e.getValue().clone()));
    }

    public void setListaArtigosVenda(Map<Integer, Artigo> listaArtigosVenda) {
        this.listaArtigosVenda = listaArtigosVenda.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e-> e.getValue().clone()));
    }

    public Map<Integer, Artigo> getListaArtigosComprados() {
        return listaArtigosComprados.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaArtigosComprados(Map<Integer, Artigo> listaArtigosComprados) {
        this.listaArtigosComprados = listaArtigosComprados.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Map<String, Pedido> getListaEncomendas() {
        return listaEncomendas.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaEncomendas(Map<String, Pedido> listaEncomendas) {
        this.listaEncomendas = listaEncomendas.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public LocalDate getDataSistema()
    {
        return this.dataSistema;
    }

    public void setDataSistema(LocalDate data)
    {
        this.dataSistema = data;
    }

    public void setDataSistema(int dias)
    {
        this.dataSistema.plusDays(dias);
        //TODO Atualizar sistema e gerar faturas!
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

    /* TODO:
    *   Adiciona utilizador por objeto
    *   Adiciona utilizador por parametros
    *   Adiciona transportadora por objeto
    *   Adiciona transportadora por parametros
    *   Adiciona Artigo Venda por objeto
    *   Adiciona Artigo Venda por parametros
    *   Adiciona Artigo Compra por objeto*/


    /**
     * Adiciona um utilizador à lista de utilizadores do Sistema.
     *
     * @param utilizador Um utilizdor
     * @throws UtilizadorException Caso o utilizdor já exista
     */
    public void adicionaUtilizador(Utilizador utilizador) throws UtilizadorException
    {
        if (!this.listaUtilizadores.containsKey(utilizador.getEmail()))
        {
            Pedido pedido = utilizador.getListaCompras();
            int id = this.listaUtilizadores.size() + 1;
            utilizador.setId(id);
            this.listaEncomendas.put(utilizador.getEmail(),pedido);
            this.listaUtilizadores.put(utilizador.getEmail(),utilizador.clone());
        }
        else{
            throw new UtilizadorException("O utilizador com email: "+ utilizador.getEmail() + " já existe!");
        }
    }

    /**
     * Adiciona um utilizador à lista de utilizadores do Sistema
     *
     * @param email Email do utilizador
     * @param palavraPasse Palavra-passe do utilizador
     * @param nome Nome do utilizador
     * @param morada Morada Fiscal do utilizador
     * @param nrFiscal Numero de contribuinte do utilizador
     * @throws UtilizadorException Caso o utilizador já exista
     */
    public void adicionaUtilizador(String email, String palavraPasse, String nome, String morada, int nrFiscal) throws UtilizadorException
    {
        if (!this.listaUtilizadores.containsKey(email))
        {
            int id = this.listaUtilizadores.size() + 1;
            Pedido pedido = new Pedido();
            Utilizador utilizador = new Utilizador(id, email, palavraPasse, nome, morada, nrFiscal, new HashMap<>(), new HashMap<>(), new Pedido());
            this.listaUtilizadores.put(email,utilizador);
            this.listaEncomendas.put(email,utilizador.getListaCompras());
        }
        else
        {
            throw new UtilizadorException("O utilizador com email: "+ email + " já existe!");
        }
    }

    /**
     * Adiciona uma transportadora à lista de transportadoras do Sistema
     *
     * @param transportadora Uma transportadora
     * @throws TransportadoraException Caso a Transportadora já exista
     */
    public void adicionaTransportadora(Transportadora transportadora) throws TransportadoraException
    {
        if(!this.listaTransportadoras.containsKey(transportadora.getNome()))
        {
            this.listaTransportadoras.put(transportadora.getNome(),transportadora.clone());
        }
        else
        {
            throw new TransportadoraException("A Transportadora " + transportadora.getNome() + " já existe!");
        }
    }

    /**
     * Adiciona uma transportadora à lista de transportadoras do Sistema
     *
     * @param nome Nome da transportadora
     * @param lucro Margem de lucro da Transportadora
     * @param tipo Tipo de transportadora
     * @throws TransportadoraException Caso a Transportadora já exista
     */
    public void adicionaTransportadora(String nome, double lucro, int tipo) throws TransportadoraException
    {
        if (!this.listaTransportadoras.containsKey(nome))
        {
            Transportadora transportadora = new Transportadora(nome, lucro, tipo, this.getImposto(), this.getTaxaEncPequena(), this.getTaxaEncMedia(),this.getTaxaEncGrande());
            this.listaTransportadoras.put(nome,transportadora);
        }
        else
        {
            throw new TransportadoraException("A Transportadora " + nome + " já existe!");
        }
    }

    public void adicionaArtigoVenda(Artigo artigo) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigosVenda.containsKey(artigo.getId()))
        {
            Utilizador utilizador = this.procuraUtilizador(artigo.getVendedor().getEmail());
            this.listaArtigosVenda.put(artigo.getId(), artigo.clone());
            utilizador.adicionaArtigoVenda(this.listaArtigosVenda.get(artigo.getId()));
        }
        else throw new ArtigoException("Artigo já há venda!");
    }

    public void adicionaArtigoComprado(Artigo artigo) throws UtilizadorException {
        if (this.listaArtigosVenda.containsKey(artigo.getId()))
        {
            this.procuraUtilizador(artigo.getVendedor().getEmail()).adicionaArtigoVendidos(artigo);
            this.listaArtigosVenda.remove(artigo.getId(),artigo);
            this.listaArtigosComprados.put(artigo.getId(),artigo);
        }
    }

    /**
     * Adiciona o artigo Tshirt à lista de artigos
     *
     * @param tshirt
     * @throws ArtigoException
     */
    public void adicionaTshirtVenda(Tshirt tshirt) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigosVenda.containsKey(tshirt.getId()))
        {
            Utilizador utilizador =  this.procuraUtilizador(tshirt.getVendedor().getEmail());
            this.listaArtigosVenda.put(tshirt.getId(),tshirt.clone());
            utilizador.adicionaArtigoVenda(this.listaArtigosVenda.get(tshirt.getId()));
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaTshirtVenda(int id, String email, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int tamanho, int padrao) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigosVenda.containsKey(id))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            Tshirt tshirt = new Tshirt(id, utilizador, descricao, marca, precoBase, correcaoPreco, estado, transportadora, tamanho, padrao);
            this.listaArtigosVenda.put(id,tshirt);
            utilizador.adicionaArtigoVenda(this.listaArtigosVenda.get(id));
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaTshirtComprados(Tshirt tshirt) throws ArtigoException, UtilizadorException {
        if(!this.listaArtigosComprados.containsKey(tshirt.getId())){
            Utilizador utilizador = this.procuraUtilizador(tshirt.getVendedor().getEmail());
            this.listaArtigosComprados.put(tshirt.getId(), tshirt.clone());
            this.listaArtigosVenda.remove(tshirt.getId());
            utilizador.adicionaArtigoVendidos(this.listaArtigosComprados.get(tshirt.getId()));
        }
        else{
            throw new ArtigoException("Este Artigo já está comprado");
        }
    }

    public void adicionaSapatilhaVenda(Sapatilha sapatilha) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigosVenda.containsKey(sapatilha.getId()))
        {
            Utilizador utilizador = this.procuraUtilizador(sapatilha.getVendedor().getEmail());
            this.listaArtigosVenda.put(sapatilha.getId(),sapatilha.clone());
            utilizador.adicionaArtigoVenda(this.listaArtigosVenda.get(sapatilha.getId()));
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaSapatilhaVenda(int id, String email, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int tamanho, int tipoCordao, String cor, LocalDate data, int tipo) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigosVenda.containsKey(id))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            Sapatilha sapatilha = new Sapatilha(id, utilizador, descricao, marca, precoBase, correcaoPreco, estado, transportadora, tamanho, tipoCordao, cor, data, tipo);
            this.listaArtigosVenda.put(id,sapatilha);
            utilizador.adicionaArtigoVenda(sapatilha);
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }
    public void adicionaSapatilhaCompra(Sapatilha sapatilha) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigosComprados.containsKey(sapatilha.getId())){
            Utilizador utilizador = this.procuraUtilizador(sapatilha.getVendedor().getEmail());
            this.listaArtigosComprados.put(sapatilha.getId(), sapatilha.clone());
            this.listaArtigosVenda.remove(sapatilha.getId(),sapatilha);
            utilizador.adicionaArtigoVendidos(this.listaArtigosComprados.get(sapatilha.getId()));
        }
        else{
            throw new ArtigoException("Este artigo já foi comprado");
        }
    }

    public void adicionaMalaVenda(Mala mala) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigosVenda.containsKey(mala.getId()))
        {
            Utilizador utilizador = this.procuraUtilizador(mala.getVendedor().getEmail());
            this.listaArtigosVenda.put(mala.getId(),mala.clone());
            utilizador.adicionaArtigoVenda(this.listaArtigosVenda.get(mala.getId()));
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaMalaVenda(int id, String email, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, double dimensao, String material, LocalDate anoLancamento, int tipo) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigosVenda.containsKey(id))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            Mala mala = new Mala(id, utilizador, descricao,marca,precoBase, correcaoPreco, estado, transportadora, dimensao, material, anoLancamento, tipo);
            this.listaArtigosVenda.put(id,mala);
            utilizador.adicionaArtigoVenda(this.listaArtigosVenda.get(id));
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }
    public void adicionaMalaCompra(Mala mala) throws ArtigoException, UtilizadorException {
        if(!this.listaArtigosComprados.containsKey(mala.getId())){
            Utilizador utilizador = this.procuraUtilizador(mala.getVendedor().getEmail());
            this.listaArtigosComprados.put(mala.getId(), mala.clone());
            this.listaArtigosVenda.remove(mala.getId(),mala);
            utilizador.adicionaArtigoVendidos(mala);
        }
        else{
            throw new ArtigoException("Este artigo já está comprado");
        }
    }


    public void adicionaArtigoPedido(Artigo artigo, String email) throws UtilizadorException, EncomendaException, ArtigoException {
        if (this.listaArtigosVenda.containsKey(artigo.getId()))
        {
            Utilizador comprador = this.procuraUtilizador(email);
            comprador.adicionaArtigoPedido(artigo);
        }
        else throw new ArtigoException("Artigo não encontrado à venda!");
    }

    public void removeArtigoPedido(Artigo artigo, String email) throws UtilizadorException, EncomendaException, ArtigoException {
        if (this.listaArtigosVenda.containsKey(artigo.getId()))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            utilizador.removeArtigoPedido(artigo);
        } else throw new ArtigoException("Artigo não encontrado à venda!");
    }
    
    public Utilizador procuraUtilizador(String email) throws UtilizadorException
    {
        if (listaUtilizadores.containsKey(email))
        {
            return listaUtilizadores.get(email);
        }
        else{
            throw new UtilizadorException("O utilizador com o email:" + email + "não encontrado");
        }
    }

    public boolean verificaUtilizador(String email) throws UtilizadorException
    {
        return (listaUtilizadores.containsKey(email));
    }

    public boolean verificaPassword(String email, String pass) throws UtilizadorException
    {
        Utilizador utilizador = procuraUtilizador(email);
        return (pass.compareTo(utilizador.getPalavraPasse()) == 0);
    }

    public Artigo procuraArtigoVenda(int id) throws ArtigoException
    {
        if (listaArtigosVenda.containsKey(id))
        {
            return listaArtigosVenda.get(id);
        }
        else{
            throw new ArtigoException("O artigo com o id" + id + "não existe");
        }
    }

    public Pedido procuraEncomenda(String email) throws EncomendaException {
        if (listaEncomendas.containsKey(email))
        {
            return listaEncomendas.get(email);
        }
        else
        {
            throw new EncomendaException("O Utilizador com email, " + email + " não tem encomendas!");
        }
    }

    public Sistema clone()
    {
        return new Sistema(this);
    }
}
