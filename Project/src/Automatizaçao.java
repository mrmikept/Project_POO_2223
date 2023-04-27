import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automatizaçao {

    private String path;
    private List<String> excecoes;

    public Automatizaçao(){

        this.path = "";
        this.excecoes = new ArrayList<>();
    }

    public Automatizaçao(String path){

        this.path = path;
        this.excecoes = new ArrayList<>();
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public List<String> getExcecoes(){
        return excecoes;
    }

    public void setExcecoes(List<String> excecoes){
        this.excecoes = excecoes;
    }

    public void carregaFicheiro(Sistema sistema) throws IOException{
        BufferedReader comeco = new BufferedReader(new FileReader(this.path));

        while(comeco.ready()){
            String linha = comeco.readLine();
            if(!linha.contains("--")){
                String[] aux = linha.split(":");

                if(linha.contains("Adiciona Utilizador:")){
                    try {
                        util(aux, sistema);
                    }
                    catch(UtilizadorException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if(linha.contains("Adiciona Venda:")){
                    try{
                        artVenda(aux, sistema);
                    }
                    catch(ArtigoException| TransportadoraException| UtilizadorException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if(linha.contains("Adiciona Compra:")){
                    try{
                        artCompra(aux, sistema);
                    }
                    catch(ArtigoException| UtilizadorException| EncomendaException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if(linha.contains("Adiciona Transportadora:")){
                    try{
                        transp(aux, sistema);
                    }
                    catch(TransportadoraException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if(linha.contains("Adiciona Encomenda:")){
                    try {
                        encom(aux, sistema);
                    }
                    catch (EncomendaException| ArtigoException| TransportadoraException| UtilizadorException a){
                        this.excecoes.add(a.getMessage());
                    }
                }
            }
        }
        comeco.close();
    }

    public void util(String[] aux, Sistema sistema) throws UtilizadorException{
        String[] camposUtil = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposUtil[0]);
        String email = camposUtil[1];
        String palavraPasse = camposUtil[2];
        String nome = camposUtil[3];
        String morada = camposUtil[4];
        int nrFiscal = Integer.valueOf(camposUtil[5]);
        sistema.adicionaUtilizador(email, palavraPasse, nome, morada, nrFiscal);
    }
    

    public void artVenda(String[] aux, Sistema sistema) throws ArtigoException, TransportadoraException, UtilizadorException{
        String[] camposArt = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposArt[0]);
        String email = camposArt[1];
        int id = Integer.valueOf(camposArt[3]);
        String descricao = camposArt[4];
        String marca = camposArt[5];
        double precoBase = Double.parseDouble(camposArt[6]);
        double correcaoPreco = Double.parseDouble(camposArt[7]);
        EstadoArtigo estado = new EstadoArtigo(Integer.valueOf(camposArt[8]), Double.parseDouble(camposArt[9]), Integer.valueOf(camposArt[10]));
        String nomeTransportadora = camposArt[11];
        Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
        int estadoVenda = Integer.valueOf(camposArt[12]);

        if(camposArt[1].contains("Mala")){
            double dimensao = Double.parseDouble(camposArt[13]);
            String material = camposArt[14];
            LocalDate anoLancamento = LocalDate.parse(camposArt[15]);
            int tipo = Integer.valueOf(camposArt[16]);
            sistema.adicionaMalaVenda(id, email, descricao, marca, precoBase, correcaoPreco, estado, transportadora, estadoVenda, dimensao, material, anoLancamento, tipo);
        }
        else if(camposArt[1].contains("Sapatilha")){
            int tamanho = Integer.valueOf(camposArt[13]);
            int tipoCordao = Integer.valueOf(camposArt[14]);
            String cor = camposArt[15];
            LocalDate dataLancamento = LocalDate.parse(camposArt[16]);
            int tipo = Integer.valueOf(camposArt[17]);
            sistema.adicionaSapatilhaVenda(id, email, descricao, marca, precoBase, correcaoPreco, estado, transportadora, estadoVenda, tamanho, tipoCordao, cor, dataLancamento, tipo);
        }
        else if(camposArt[1].contains("Tshirt")){
            int tamanho = Integer.valueOf(camposArt[13]);
            int padrao = Integer.valueOf(camposArt[14]);
            sistema.adicionaTshirtVenda(id, email, descricao, marca, precoBase, correcaoPreco, estado, transportadora, estadoVenda, tamanho, padrao);
        }
    }

    public void artCompra(String[] aux, Sistema sistema) throws ArtigoException, UtilizadorException, EncomendaException{
        String[] camposArtComp = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposArtComp[0]);
        String email = camposArtComp[1];
        Utilizador utilizador = sistema.procuraUtilizador(email);
        int idArtigo = Integer.valueOf(camposArtComp[2]);
        Artigo artigo = sistema.procuraArtigoVenda(idArtigo);
        utilizador.adicionaArtigoPedido(artigo);

    }

    public void transp(String[] aux, Sistema sistema) throws TransportadoraException{
        String[] camposTransp = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposTransp[0]);
        String nome = camposTransp[1];
        double margemLucro = Double.parseDouble(camposTransp[2]);
        int tipo = Integer.valueOf(camposTransp[3]);
        sistema.adicionaTransportadora(nome, margemLucro, tipo);
    }

    public void encom(String[] aux, Sistema sistema) throws EncomendaException, ArtigoException, TransportadoraException, UtilizadorException{
        String[] camposEncom = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposEncom[0]);
        String email = camposEncom[1];
        String nomeTransportadora = camposEncom[2];
        Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
        Encomenda encomenda = new Encomenda();
        encomenda.setTransportadora(transportadora);
        int x = 3;
        while (camposEncom[x] != null){
            int idArtigo = Integer.valueOf(camposEncom[x]);
            Artigo artigo = sistema.procuraArtigoVenda(idArtigo);
            sistema.adicionaArtigoPedido(artigo, email);
            x++;
        }
    }
}
