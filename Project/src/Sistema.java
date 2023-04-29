import com.sun.source.tree.ReturnTree;

import java.io.Serializable;
import java.security.PublicKey;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDate;
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
public class Sistema implements Serializable
{
    private Map<String,Utilizador> listaUtilizadores;
    private Map<String, Transportadora> listaTransportadoras;
    private Map<Integer, Artigo> listaArtigos;
    private List<Encomenda> listaEncomendas;
    private LocalDate dataCriacao;
    private LocalDate dataAtual;

    private int imposto;
    private double taxaEncPequena;
    private double taxaEncMedia;
    private double taxaEncGrande;
    private int tempoDevolucao;

    private static final int TAXAIMPOSTO_OMISSAO = 23;
    private static final double TAXAENC_PQ_OMISSAO = 2.55;
    private static final double TAXAENC_MD_OMISSAO = 3.25;
    private static final double TAXAENC_GD_OMISSAO = 4.15;
    private static final int TEMPODEVOLUCAO_OMISSAO = 2;

    public Sistema()
    {
        this.listaUtilizadores = new HashMap<>();
        this.listaTransportadoras = new HashMap<>();
        this.listaArtigos = new HashMap<>();
        this.listaEncomendas = new ArrayList<>();
        this.dataCriacao = LocalDate.now();
        this.dataAtual = LocalDate.now();
        this.imposto = TAXAIMPOSTO_OMISSAO;
        this.taxaEncPequena = TAXAENC_PQ_OMISSAO;
        this.taxaEncMedia = TAXAENC_MD_OMISSAO;
        this.taxaEncGrande = TAXAENC_GD_OMISSAO;
        this.tempoDevolucao = TEMPODEVOLUCAO_OMISSAO;
    }

    public Sistema(Map<String, Utilizador> listaUtilizadores, Map<String,Transportadora> listaTransportadoras, Map<Integer,Artigo> listaArtigos, List<Encomenda> listaEncomendas, LocalDate dataCriacao, LocalDate dataAtual, int imposto, double taxaEncPequena, double taxaEncMedia, double taxaEncGrande, int tempoDevolucao)
    {
        this.listaUtilizadores = listaUtilizadores;
        this.listaTransportadoras = listaTransportadoras;
        this.listaArtigos = listaArtigos;
        this.listaEncomendas = listaEncomendas;
        this.dataCriacao = dataCriacao;
        this.dataAtual = dataAtual;
        this.imposto = imposto;
        this.taxaEncPequena = taxaEncPequena;
        this.taxaEncMedia = taxaEncMedia;
        this.taxaEncGrande = taxaEncGrande;
        this.tempoDevolucao = tempoDevolucao;
    }

    public Sistema(Sistema sistema)
    {
        this.listaUtilizadores = sistema.getListaUtilizadores();
        this.listaTransportadoras = sistema.getListaTransportadoras();
        this.listaArtigos = sistema.getListaArtigos();
        this.listaEncomendas = sistema.getListaEncomendas();
        this.dataCriacao = sistema.getDataCriacao();
        this.dataAtual = sistema.getDataAtual();
        this.tempoDevolucao = sistema.getTempoDevolucao();
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

    public Map<Integer, Artigo> getListaArtigos()
    {
        return this.listaArtigos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setListaArtigos(Map<Integer,Artigo> listaArtigos)
    {
        this.listaArtigos = listaArtigos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public List<Encomenda> getListaEncomendas() {
        return listaEncomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setListaEncomendas(List<Encomenda> listaEncomendas) {
        this.listaEncomendas = listaEncomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public LocalDate getDataCriacao()
    {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate data)
    {
        this.dataCriacao = data;
    }

    public LocalDate getDataAtual()
    {
        return this.dataAtual;
    }

    public void setDataAtual(LocalDate data)
    {
        this.dataAtual = data;
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

    public int getTempoDevolucao() {
        return tempoDevolucao;
    }

    public void setTempoDevolucao(int tempoDevolucao) {
        this.tempoDevolucao = tempoDevolucao;
    }

    public Map<Integer, Artigo> getArtigosVenda(String email) throws UtilizadorException {
        Utilizador utilizador = this.procuraUtilizador(email);
        return this.listaArtigos = listaArtigos.entrySet().stream().filter(encomenda -> encomenda.getValue().getVendedor().getId() != utilizador.getId()).collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
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
            int id = this.listaUtilizadores.size() + 1;
            utilizador.setId(id);
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
            Utilizador utilizador = new Utilizador(id, email, palavraPasse, nome, morada, nrFiscal, new HashMap<>(), new ArrayList<>());
            this.listaUtilizadores.put(email,utilizador);
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
    public void adicionaTransportadora(String nome, double lucro, int tipo, int tempExpedicao) throws TransportadoraException
    {
        if (!this.listaTransportadoras.containsKey(nome))
        {
            Transportadora transportadora = new Transportadora(nome, lucro, tipo, tempExpedicao, this.getImposto(), this.getTaxaEncPequena(), this.getTaxaEncMedia(),this.getTaxaEncGrande());
            this.listaTransportadoras.put(nome,transportadora);
        }
        else
        {
            throw new TransportadoraException("A Transportadora " + nome + " já existe!");
        }
    }

    public void adicionaArtigo(Artigo artigo) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigos.containsKey(artigo.getId()))
        {
            Utilizador utilizador = this.procuraUtilizador(artigo.getVendedor().getEmail());
            this.listaArtigos.put(artigo.getId(), artigo.clone());
            utilizador.adicionaArtigo(this.listaArtigos.get(artigo.getId()));
        }
        else throw new ArtigoException("Artigo já há venda!");
    }

    public void removeArtigo(Artigo artigo) throws UtilizadorException {
        if (this.listaArtigos.containsKey(artigo.getId()))
        {
            Utilizador utilizador = this.procuraUtilizador(artigo.getVendedor().getEmail());
            this.listaArtigos.remove(artigo.getId(), artigo);
            utilizador.removeArtigo(artigo);
        }
    }

    public void removeArtigo(int id) throws UtilizadorException, ArtigoException {
        if (this.listaArtigos.containsKey(id))
        {
            Artigo artigo = this.procuraArtigo(id);
            Utilizador utilizador = this.procuraUtilizador(artigo.getVendedor().getEmail());
            this.listaArtigos.remove(artigo.getId(), artigo);
            utilizador.removeArtigo(artigo);
        }
    }

    public void adicionaTshirtVenda(int id, String email, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int estadoVenda, int tamanho, int padrao) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigos.containsKey(id))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            Tshirt tshirt = new Tshirt(id, utilizador, descricao, marca, precoBase, correcaoPreco, estado, transportadora, estadoVenda, tamanho, padrao);
            this.listaArtigos.put(id,tshirt);
            utilizador.adicionaArtigo(this.listaArtigos.get(id));
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaSapatilhaVenda(int id, String email, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int estadoVenda, int tamanho, int tipoCordao, String cor, LocalDate data, int tipo) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigos.containsKey(id))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            Sapatilha sapatilha = new Sapatilha(id, utilizador, descricao, marca, precoBase, correcaoPreco, estado, transportadora, estadoVenda, tamanho, tipoCordao, cor, data, tipo);
            this.listaArtigos.put(id,sapatilha);
            utilizador.adicionaArtigo(sapatilha);
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaMalaVenda(int id, String email, String descricao, String marca, double precoBase, double correcaoPreco, EstadoArtigo estado, Transportadora transportadora, int estadoVenda, double dimensao, String material, LocalDate anoLancamento, int tipo) throws ArtigoException, UtilizadorException {
        if (!this.listaArtigos.containsKey(id))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            Mala mala = new Mala(id, utilizador, descricao,marca,precoBase, correcaoPreco, estado, transportadora, estadoVenda, dimensao, material, anoLancamento, tipo);
            this.listaArtigos.put(id,mala);
            utilizador.adicionaArtigo(this.listaArtigos.get(id));
        }
        else{
            throw new ArtigoException("Este Artigo já está à venda");
        }
    }

    public void adicionaArtigoComprado(int id) throws ArtigoException {
        if (this.listaArtigos.containsKey(id))
        {
            Artigo artigo = this.listaArtigos.get(id);
            if (artigo.getEstadoVenda() == Atributos.VENDA)
            {
                artigo.setEstadoVenda(Atributos.VENDIDO);
            }
            else throw new ArtigoException("O artigo já está comprado!");
        }
        else throw new ArtigoException("O artigo não existe!");
    }

    public void adicionaArtigoComprado(Artigo artigo) throws ArtigoException {

        if (this.listaArtigos.containsKey(artigo.getId()))
        {
            Artigo a = this.listaArtigos.get(artigo.getId());
            if (a.getEstadoVenda() == Atributos.VENDA)
            {
                a.setEstadoVenda(Atributos.VENDIDO);
            }
            else throw new ArtigoException("O artigo já está comprado!");
        }
        else throw new ArtigoException("O artigo não existe!");
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

    public Transportadora procuraTransportadora(String nome) throws TransportadoraException{
        if (listaTransportadoras.containsKey(nome))
        {
            return listaTransportadoras.get(nome);
        }
        else
        {
            throw new TransportadoraException("A Transportadora com o nome, " + nome + "não existe!");
        }
    }

    public Artigo procuraArtigo(int id) throws ArtigoException
    {
        if (listaArtigos.containsKey(id))
        {
            return listaArtigos.get(id);
        }
        else{
            throw new ArtigoException("O artigo com o id" + id + "não existe");
        }
    }

    public Encomenda procuraEncomenda(Encomenda encomenda) throws ArtigoException, EncomendaException {
        if (this.listaEncomendas.contains(encomenda))
        {
            return this.listaEncomendas.stream().filter(enc -> enc.equals(encomenda)).collect(Collectors.toList()).get(0);
        } else throw new EncomendaException("Esta encomenda não existe!");
    }

    public void adicionaEncomenda(Encomenda encomenda, String email) throws UtilizadorException, EncomendaException {
        if (this.listaUtilizadores.containsKey(email))
        {
            if (!this.listaEncomendas.contains(encomenda))
            {
                this.listaEncomendas.add(encomenda);
            }
            else throw new EncomendaException("Encomenda já existente!");
        }
        else throw new UtilizadorException("Utilizador não encontrado!");
    }

    public void removeEncomenda(Encomenda encomenda) throws SistemaException {
        if (this.listaEncomendas.contains(encomenda))
        {
            this.listaEncomendas.remove(encomenda);
        } else throw new SistemaException("Esta encomenda não existe no sistema!");
    }

    public void removeEncomenda(int idEncomenda, String email)
    {
        if (this.listaUtilizadores.containsKey(email))
        {
            Encomenda encomenda = this.listaEncomendas.get(idEncomenda-1);
        }
    }

    public void adicionaArtigoEncomenda(Artigo artigo, String email) throws EncomendaException, UtilizadorException, ArtigoException {
        if (this.listaUtilizadores.containsKey(email))
        {
            Utilizador comprador = this.procuraUtilizador(email);
            List<Encomenda> listaEncomenda = comprador.getEncomendaPendente(artigo.getTransportadora().getNome());
            if (!listaEncomenda.isEmpty())
            {
                Encomenda encomenda = listaEncomenda.get(0);
                encomenda.adicionaArtigo(this.procuraArtigo(artigo.getId()));
            }
            else
            {
                Encomenda encomenda = new Encomenda(this.listaEncomendas.size() + 1, this.getDataAtual());
                encomenda.adicionaArtigo(this.procuraArtigo(artigo.getId()));
                comprador.adicionaEncomenda(encomenda);
                this.adicionaEncomenda(encomenda,comprador.getEmail());
            }
        }
        else throw new UtilizadorException("Utilizador não existente!");
    }

    public void adicionaArtigoEncomenda(int idArtigo, String email) throws EncomendaException, UtilizadorException, ArtigoException {
        if (this.listaUtilizadores.containsKey(email))
        {
            Utilizador comprador = this.procuraUtilizador(email);
            Artigo artigo = this.procuraArtigo(idArtigo);
            List<Encomenda> listaEncomenda = comprador.getEncomendaPendente(artigo.getTransportadora().getNome());
            if (!listaEncomenda.isEmpty())
            {
                Encomenda encomenda = listaEncomenda.get(0);
                encomenda.adicionaArtigo(this.procuraArtigo(idArtigo));
            }
            else
            {
                Encomenda encomenda = new Encomenda(this.listaEncomendas.size() + 1, this.getDataAtual());
                encomenda.adicionaArtigo(this.procuraArtigo(idArtigo));
                comprador.adicionaEncomenda(encomenda);
                this.adicionaEncomenda(encomenda,comprador.getEmail());
            }
        }
        else throw new UtilizadorException("Utilizador não existente!");
    }

    public void removeArtigoEncomenda(Artigo artigo, String email) throws EncomendaException, UtilizadorException, ArtigoException {
        if (this.listaUtilizadores.containsKey(email))
        {
            Utilizador comprador = this.procuraUtilizador(email);
            List<Encomenda> listaEncomenda = comprador.getEncomendaPendente(artigo.getTransportadora().getNome());
            if (!listaEncomenda.isEmpty())
            {
                Encomenda encomenda = listaEncomenda.get(0);
                encomenda.removeArtigo(this.procuraArtigo(artigo.getId()));
            }
            else throw new EncomendaException("Encomenda não existe!");
        }
        else throw new UtilizadorException("Utilizador não existente!");
    }

    public void removeArtigoEncomenda(int idArtigo, String email) throws EncomendaException, UtilizadorException, ArtigoException {
        if (this.listaUtilizadores.containsKey(email))
        {
            Utilizador comprador = this.procuraUtilizador(email);
            Artigo artigo = this.procuraArtigo(idArtigo);
            List<Encomenda> listaEncomenda = comprador.getEncomendaPendente(artigo.getTransportadora().getNome());
            if (!listaEncomenda.isEmpty())
            {
                Encomenda encomenda = listaEncomenda.get(0);
                encomenda.removeArtigo(this.procuraArtigo(idArtigo));
            }
            else throw new EncomendaException("Encomenda não existe!");
        }
        else throw new UtilizadorException("Utilizador não existente!");
    }

    public void confirmaSelecaoEncomenda(String email, String nomeTransportadora) throws UtilizadorException, EncomendaException {
        if (this.listaUtilizadores.containsKey(email))
        {
            Utilizador utilizador = this.procuraUtilizador(email);
            List<Encomenda> lista = utilizador.getEncomendaPendente(nomeTransportadora);
            if(!lista.isEmpty())
            {
                Encomenda encomenda = lista.get(0);
                encomenda.alteraEstadoExpedido(this.getDataAtual());
            } else throw new EncomendaException("A encomenda não existe!");
        } else throw new UtilizadorException("O utilizador não existe!");
    }

    public void devolveEncomenda(String email, int idEncomenda) throws UtilizadorException, EncomendaException, SistemaException {
        if (this.listaUtilizadores.containsKey(email))
        {
            List<Encomenda> encomendas = this.listaEncomendas.stream().filter(encomenda -> encomenda.getId() == idEncomenda).collect(Collectors.toList());
            if (!encomendas.isEmpty())
            {
                Encomenda encomenda = encomendas.get(0);
                if (this.getDataAtual().compareTo(encomenda.getDataAtualizacao()) <= this.getTempoDevolucao())
                {
                    Utilizador utilizador = this.procuraUtilizador(email);
                    utilizador.removeEncomenda(encomenda);
                    this.removeEncomenda(encomenda);
                    encomenda.devolveEncomenda();
                }
                else throw new EncomendaException("Esta encomenda não pode ser devolvida!");
            } else throw new EncomendaException("Esta encomenda não existe!");
        }
    }

    public void atualizaData()
    {
        LocalDate dataAtualReal = LocalDate.now();
        int diferenca = dataAtualReal.compareTo(this.getDataCriacao());
        this.setDataAtual(this.getDataAtual().plusDays(diferenca));
    }

    public void saltaTempo(int dias)
    {
        this.setDataAtual(this.getDataAtual().plusDays(dias));
        this.atualizaSistema();
    }

    public void saltaTempo(int ano, int mes, int dia) throws SistemaException {
        LocalDate dataInput = LocalDate.of(ano,mes,dia);
        int diferenca = dataInput.compareTo(this.getDataAtual());
        if (diferenca >= 0)
        {
            this.setDataAtual(dataInput);
            this.atualizaSistema();
        } else throw new SistemaException("Data inserida não pode ser anterior à data atual!");
    }

    public void atualizaEncomendas()
    {
        for (Encomenda encomenda : listaEncomendas) {
            encomenda.alteraEstadoFinalizado(this.getDataAtual());

        }
    }

    public void atualizaSistema()
    { //TODO Função que atualiza o sistema: Entregar encomendas, diminuit stock, e emissão de fatura para cada comprador/vendedor
        this.atualizaEncomendas();
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

    public Sistema clone()
    {
        return new Sistema(this);
    }
}
