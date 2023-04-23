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
            }
        }
        comeco.close();
    }

    public void util(String[] aux, Sistema sistema) throws UtilizadorException{
        String[] camposUtil = aux[1].split(",");
        String email = camposUtil[0];
        String palavraPasse = camposUtil[1];
        String nome = camposUtil[2];
        String morada = camposUtil[3];
        int nrFiscal = Integer.valueOf(camposUtil[4]);
        sistema.adicionaUtilizador(email, palavraPasse, nome, morada, nrFiscal);
    }
    

    public void artVenda(String[] aux, Sistema sistema) throws ArtigoException{
        String[] camposArt = aux[1].split(",");
        int id = Integer.valueOf(camposArt[1]);
        String descricao = camposArt[2];
        String marca = camposArt[3];
        double precoBase = Double.parseDouble(camposArt[4]);
        double correcaoPreco = Double.parseDouble(camposArt[5]);
        EstadoArtigo estado = new EstadoArtigo(Integer.valueOf(camposArt[6]), Double.parseDouble(camposArt[7]), Integer.valueOf(camposArt[8]));
        Transportadora transportadora = new Transportadora(camposArt[9], Double.parseDouble(camposArt[10]), Integer.valueOf(camposArt[11]));

        if(camposArt[0].contains("Mala")){
            double dimensao = Double.parseDouble(camposArt[12]);
            String material = camposArt[13];
            LocalDate anoLancamento = LocalDate.parse(camposArt[14]);
            int tipo = Integer.valueOf(camposArt[15]);
            sistema.adicionaMalaVenda(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora, dimensao, material, anoLancamento, tipo);
        }
        else if(camposArt[0].contains("Sapatilha")){
            int tamanho = Integer.valueOf(camposArt[12]);
            int tipoCordao = Integer.valueOf(camposArt[13]);
            String cor = camposArt[14];
            LocalDate data = LocalDate.parse(camposArt[15]);
            int tipo = Integer.valueOf(camposArt[16]);
            sistema.adicionaSapatilhaVenda(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora, tamanho, tipoCordao, cor, data, tipo);
        }
        else if(camposArt[0].contains("Tshirt")){
            int tamanho = Integer.valueOf(camposArt[12]);
            int padrao = Integer.valueOf(camposArt[13]);
            sistema.adicionaTshirtVenda(id, descricao, marca, precoBase, correcaoPreco, estado, transportadora, tamanho, padrao);
        }

    }

    public void artCompra(String[] aux, Sistema sistema) throws ArtigoException{
        String[] camposArtComp = aux[1].split(",");
        LocalDate data = LocalDate.parse(camposArtComp[0]);
        int idUtilizador = Integer.valueOf(camposArtComp[1]);
        int idArtigo = Integer.valueOf(camposArtComp[2]);
    }

    public void transp(String[] aux, Sistema sistema) throws TransportadoraException{
        String[] camposTransp = aux[1].split(",");
        String nome = camposTransp[0];
        double margemLucro = Double.parseDouble(camposTransp[1]);
        int tipo = Integer.valueOf(camposTransp[2]);
        sistema.adicionaTransportadora(nome, margemLucro, tipo);
    }




}
