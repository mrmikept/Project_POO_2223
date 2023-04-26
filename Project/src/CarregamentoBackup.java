import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarregamentoBackup {

    private String path;
    private List<String> excecoes;

    public CarregamentoBackup(){

        this.path = "";
        this.excecoes = new ArrayList<>();
    }

    public CarregamentoBackup(String path){

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

                if(linha.contains("Venda:")){
                    try{
                        artVenda(aux, sistema);
                    }
                    catch(ArtigoException a){
                        this.excecoes.add(a.getMessage());
                    }
                }

                if(linha.contains("Compra:")){
                    try{
                        artCompra(aux, sistema);
                    }
                    catch(ArtigoException a){
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
                    catch (EncomendaException a){
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
    

    public void artVenda(String[] aux, Sistema sistema) throws ArtigoException{
        String[] camposArt = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposArt[0]);
        int id = Integer.valueOf(camposArt[2]);
        String descricao = camposArt[3];
        String marca = camposArt[4];
        double precoBase = Double.parseDouble(camposArt[5]);
        double correcaoPreco = Double.parseDouble(camposArt[6]);
        EstadoArtigo estado = new EstadoArtigo(Integer.valueOf(camposArt[7]), Double.parseDouble(camposArt[8]), Integer.valueOf(camposArt[9]));
        Transportadora transportadora = new Transportadora(camposArt[10], Double.parseDouble(camposArt[11]), Integer.valueOf(camposArt[12]));

        if(camposArt[1].contains("Mala")){
            double dimensao = Double.parseDouble(camposArt[13]);
            String material = camposArt[14];
            LocalDate anoLancamento = LocalDate.parse(camposArt[15]);
            int tipo = Integer.valueOf(camposArt[16]);
            sistema.adicionaMalaVenda(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora, dimensao, material, anoLancamento, tipo);
        }
        else if(camposArt[1].contains("Sapatilha")){
            int tamanho = Integer.valueOf(camposArt[13]);
            int tipoCordao = Integer.valueOf(camposArt[14]);
            String cor = camposArt[15];
            LocalDate dataLancamento = LocalDate.parse(camposArt[16]);
            int tipo = Integer.valueOf(camposArt[17]);
            sistema.adicionaSapatilhaVenda(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora, tamanho, tipoCordao, cor, dataLancamento, tipo);
        }
        else if(camposArt[1].contains("Tshirt")){
            int tamanho = Integer.valueOf(camposArt[13]);
            int padrao = Integer.valueOf(camposArt[14]);
            sistema.adicionaTshirtVenda(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora, tamanho, padrao);
        }

    }

    public void artCompra(String[] aux, Sistema sistema) throws ArtigoException{
        String[] camposArtComp = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposArtComp[0]);
        int idUtilizador = Integer.valueOf(camposArtComp[1]);
        int idArtigo = Integer.valueOf(camposArtComp[2]);
        Artigo artigo = sistema.procuraArtigoVenda(idArtigo);
        sistema.adicionaArtigoCompra(artigo);
    }

    public void transp(String[] aux, Sistema sistema) throws TransportadoraException{
        String[] camposTransp = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposTransp[0]);
        String nome = camposTransp[1];
        double margemLucro = Double.parseDouble(camposTransp[2]);
        int tipo = Integer.valueOf(camposTransp[3]);
        sistema.adicionaTransportadora(nome, margemLucro, tipo);
    }

    public void encom(String[] aux, Sistema sistema) throws EncomendaException{
        String[] camposEncom = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposEncom[0]);
        String email = camposEncom[1];
        int dimensao = Integer.valueOf(camposEncom[2]);
        int estado = Integer.valueOf(camposEncom[3]);
        ArrayList<Artigo> listaArtigos = new ArrayList<>();



    }




}
