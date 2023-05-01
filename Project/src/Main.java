import javax.swing.text.Style;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Sistema sistema;
    private Apresentacao apresentacao;

    public static void main(String[] args) throws IOException, UtilizadorException, TransportadoraException, ClassNotFoundException, ArtigoException {
        new Main().run();
    }

    private Main() {
        this.sistema = new Sistema();
        this.apresentacao = new Apresentacao();
    }

    private void run() throws UtilizadorException, TransportadoraException, IOException, ClassNotFoundException, ArtigoException {
        int x = 0;
        String c;
        String [] s = {"Entrar no programa", "Guardar estado", "Carregar estado anterior", "Carregar ficheiro para o sistema", "Estatísticas", "Sair"};
        Scanner ler = new Scanner(System.in);
        String input, input_backup;

        do {
            switch (x) {
                case 0:
                    apresentacao.printMenu(s,x, "");
                    x = ler.nextInt();
                    break;

                case 1:
                    try {
                        //sistema.adicionaUtilizador("r","r","r","r",1);
                        //x = runUtilizador("r");
                        x = runIN();
                        x = 0;
                    }
                    catch (UtilizadorException a){
                        System.out.println(a.getMessage());
                    } catch (SistemaException e) {
                        throw new RuntimeException(e);
                    }


                    break;

                case 2:
                    apresentacao.printGuardar();
                    apresentacao.cyan();
                    System.out.println("                                                               Indique o caminho para a pasta onde pertende guardar o estado (../\"ficheiro\"):");
                    apresentacao.resetColor();
                    System.out.print("                                                               ");
                    ler = new Scanner(System.in);
                    input = ler.nextLine();
                    CarregamentoFicheiro.escreveFicheiro(this.sistema, input);

                    apresentacao.clear();
                    apresentacao.printGuardar();
                    apresentacao.yellow();
                    System.out.println("                                                                                            ESTADO GRAVADO COM SUCESSO!!");
                    apresentacao.resetColor();
                    System.out.println();
                    System.out.println("                                                                                          Pressione enter para continuar...");
                    System.out.println();
                    System.out.print("                                                                                                         ");
                    ler = new Scanner(System.in);
                    ler.nextLine();
                    x = 0;
                    break;

                case 3:
                    apresentacao.printLoad();
                    apresentacao.cyan();
                    System.out.println("                                                               Indique o caminho para a pasta de onde pertende carregar o estado (../\"ficheiro\"):");
                    apresentacao.resetColor();
                    System.out.print("                                                               ");
                    ler = new Scanner(System.in);
                    input = ler.nextLine();
                    this.sistema = CarregamentoFicheiro.lerFicheiro(input);

                    apresentacao.clear();
                    apresentacao.printLoad();
                    apresentacao.yellow();
                    System.out.println("                                                                                         ESTADO CARREGADO COM SUCESSO!!");
                    apresentacao.resetColor();
                    System.out.println();
                    System.out.println("                                                                                        Pressione enter para continuar...");
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    ler.nextLine();
                    x = 0;
                    break;

                case 4 :
                    apresentacao.printBackup();
                    apresentacao.cyan();
                    System.out.println("                                                               Indique o caminho para a pasta de onde pertende carregar o backup (../\"ficheiro\"):");
                    apresentacao.resetColor();
                    System.out.print("                                                               ");
                    ler = new Scanner(System.in);
                    input_backup = ler.nextLine();
                    Automatizaçao backup = new Automatizaçao(input_backup);
                    backup.carregaFicheiro(this.sistema);
                    if (!backup.getExcecoes().isEmpty() ){
                        apresentacao.printErros(backup.getExcecoes());
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                    }
                    apresentacao.clear();
                    apresentacao.printBackup();
                    apresentacao.yellow();
                    System.out.println("                                                                                        BACKUP CARREGADO COM SUCESSO!!");
                    apresentacao.resetColor();
                    System.out.println();
                    System.out.println("                                                                                       Pressione enter para continuar...");
                    System.out.println();
                    System.out.print("                                                                                                      ");
                    ler = new Scanner(System.in);
                    ler.nextLine();
                    x = 0;
                    break;
            }
        } while (x != 6);
        apresentacao.clear();
    }

    private int runIN() throws UtilizadorException, TransportadoraException, ArtigoException, SistemaException {
        int x = 0;
        String email, pass, nome, morada, nomeTrans, c;
        int nif;
        int tipo = 0;
        double lucro;
        String[] s = {"Iniciar sessao - Utlizador", "Procurar Transportadora", "Registar - Utilizador", "Registar - Transportadora", "Configuracoes", "Retroceder"};
        Scanner ler = new Scanner(System.in);

        do {
            switch (x) {
                case 0:
                    apresentacao.printMenu(s,x, "");
                    x = ler.nextInt();
                    break;
                case 1: //Iniciar sessao
                    apresentacao.printLogin();
                    apresentacao.cyan();
                    System.out.println("                                                                                       Insira o seu email:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                       ");
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    if(sistema.verificaUtilizador(email)) {
                        apresentacao.cyan();
                        System.out.println("                                                                                       Insira a sua password:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                       ");
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();
                        if(sistema.verificaPassword(email, pass))
                        {
                            x = runUtilizador(email);
                            break;
                        }
                        else
                        {
                            while (x == 1) {
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.red();
                                System.out.println("                                                                               PASSWORD INCORRETA!! DESEJA CONTINUAR A TENTAR?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");
                                x = ler.nextInt();

                                if (x == 0) break;

                                apresentacao.clear();
                                apresentacao.printLogin();
                                apresentacao.cyan();
                                System.out.println("                                                                                       Insira o seu email:");
                                apresentacao.resetColor();
                                System.out.print("                                                                                       " + email);
                                System.out.println();
                                apresentacao.cyan();
                                System.out.println("                                                                                       Insira a sua password:");
                                apresentacao.resetColor();
                                System.out.print("                                                                                       ");

                                ler = new Scanner(System.in);
                                pass = ler.nextLine();
                                if(sistema.verificaPassword(email, pass))
                                {
                                    x = runUtilizador(email);
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        while (x == 1) {
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            apresentacao.red();
                            System.out.println("                                                                         EMAIL INCORRETO OU NAO EXISTE!! DESEJA CONTINUAR A TENTAR?");
                            apresentacao.resetColor();
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NAO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            x = ler.nextInt();

                            if (x == 0) break;

                            apresentacao.clear();
                            apresentacao.printLogin();
                            apresentacao.cyan();
                            System.out.println("                                                                                       Insira o seu email:");
                            apresentacao.resetColor();
                            System.out.print("                                                                                       ");

                            ler = new Scanner(System.in);
                            email = ler.nextLine();

                            if(sistema.verificaUtilizador(email)) {
                                System.out.println();
                                apresentacao.cyan();
                                System.out.println("                                                                                       Insira a sua password:");

                                apresentacao.resetColor();
                                System.out.print("                                                                                       ");
                                ler = new Scanner(System.in);
                                pass = ler.nextLine();
                                if(sistema.verificaPassword(email, pass))
                                {
                                    x = runUtilizador(email);
                                    break;
                                }
                                else
                                {
                                    while (x == 1) {
                                        System.out.println();
                                        System.out.println();
                                        System.out.println();
                                        apresentacao.red();
                                        System.out.println("                                                                               PASSWORD INCORRETA!! DESEJA CONTINUAR A TENTAR?");
                                        apresentacao.resetColor();
                                        System.out.println();
                                        System.out.println("                                                                                                    1 - SIM");
                                        System.out.println("                                                                                                    0 - NAO");
                                        System.out.println();
                                        System.out.print("                                                                                                      ");
                                        x = ler.nextInt();

                                        if (x == 0) break;

                                        apresentacao.clear();
                                        apresentacao.printLogin();
                                        apresentacao.cyan();
                                        System.out.println("                                                                                       Insira o seu email:");
                                        apresentacao.resetColor();
                                        System.out.print("                                                                                       " + email);
                                        System.out.println();
                                        apresentacao.cyan();
                                        System.out.println("                                                                                       Insira a sua password:");

                                        apresentacao.resetColor();
                                        System.out.print("                                                                                       ");

                                        ler = new Scanner(System.in);
                                        pass = ler.nextLine();
                                        if(sistema.verificaPassword(email, pass))
                                        {
                                            x = runUtilizador(email);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 2:
                    apresentacao.printProcuraTrans();

                    System.out.println(apresentacao.YELLOW + "                                                                             Introduza a transportadora que pretende procurar:" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                             ");
                    ler = new Scanner(System.in);
                    nomeTrans = ler.nextLine();
                    Transportadora transportadora = sistema.procuraTransportadora(nomeTrans);
                    apresentacao.printProcuraTrans();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                                  Nome: " + apresentacao.RESET + transportadora.getNome());
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                      Margem Lucro: " + apresentacao.RESET + transportadora.getMargemLucro() + apresentacao.CYAN_BOLD +
                            " | Tipo: " + apresentacao.RESET + transportadora.getTipo() +  apresentacao.CYAN_BOLD + " | Imposto: " + apresentacao.RESET + transportadora.getImposto() + apresentacao.CYAN_BOLD + " | Taxas: " + apresentacao.RESET + transportadora.getTxEncPq() + apresentacao.CYAN_BOLD + " (Pequena), " + apresentacao.RESET + transportadora.getTxEncMd() + apresentacao.CYAN_BOLD+ " (Média), " + apresentacao.RESET + transportadora.getTxEncGd() + apresentacao.CYAN_BOLD + " (Grande)" + apresentacao.RESET);
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                                Encomendas:" + apresentacao.RESET);
                    System.out.println();
                    System.out.println(apresentacao.YELLOW + "                                                                                      Pressione enter para continuar..." + Apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                     ");
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 3: //Registar utilizador
                    apresentacao.printReg();

                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira o email:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    if(!sistema.verificaUtilizador(email)) {
                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira a password:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();

                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira o seu primeiro e ultimo nome:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        nome = ler.nextLine();

                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira a sua morada:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");

                        ler = new Scanner(System.in);
                        morada = ler.nextLine();

                        System.out.println();


                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira o seu numero fiscal:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        nif = ler.nextInt();
                        sistema.adicionaUtilizador(email, pass, nome, morada, nif);

                        apresentacao.printReg();
                        apresentacao.yellow();
                        System.out.println("                                                                        UTILIZADOR REGISTADO COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?");
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println("                                                                                                    1 - SIM");
                        System.out.println("                                                                                                    0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                      ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) x = 3;
                        else x = 0;
                    }
                    else
                    {
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                                 ESTE EMAIL JÁ EXISTE!! DESEJA TENTAR DE NOVO?");
                        System.out.println();
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println("                                                                                                   1 - SIM");
                        System.out.println("                                                                                                   0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                      ");
                        ler = new Scanner(System.in);
                        x = ler.nextInt();
                        if (x == 1) x = 3;
                        else x = 0;
                    }

                    break;

                case 4:
                    apresentacao.printRegTrans();
                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira o nome da transportadora:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    nomeTrans = ler.nextLine();
                    if(!sistema.verificaTransportadora(nomeTrans)) {

                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira a margem de lucro que pretende obter:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        lucro = ler.nextDouble();

                        System.out.println();

                        apresentacao.cyan();
                        System.out.println("                                                                                      Insira 1-\"Normal\" ou 2-\"Premium\":");
                        apresentacao.resetColor();
                        System.out.print("                                                                                      ");
                        ler = new Scanner(System.in);
                        tipo = ler.nextInt();

                        sistema.adicionaTransportadora(nomeTrans, lucro, tipo,2);

                        apresentacao.printRegTrans();
                        apresentacao.yellow();
                        System.out.println("                                                                       TRANSPORTADORA REGISTADA COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?");
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println("                                                                                                    1 - SIM");
                        System.out.println("                                                                                                    0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                      ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) x = 4;
                        else x = 0;
                    }
                    else
                    {
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                             ESTA TRANSPORTADORA JÁ EXISTE!! DESEJA TENTAR DE NOVO?");
                        System.out.println();
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println("                                                                                                  1 - SIM");
                        System.out.println("                                                                                                  0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        x = ler.nextInt();
                        if (x == 1) x = 4;
                        else x = 0;
                    }
                    break;

                case 5: // MENU CONFIGURACOES
                    x = runConfig();
                    break;
            }
        } while (x != 6);

        return x;
    }

    private int runUtilizador(String email) throws UtilizadorException, ArtigoException //MENU UTILIZADOR
    {
        String[] s = {"Ver perfil", "Comprar", "Vendas", "Faturas", "Encomenda", "Retroceder"};
        int x = 0;
        String c;
        Utilizador utilizador = sistema.procuraUtilizador(email);
        String nome = utilizador.getNome();
        Scanner ler = new Scanner(System.in);
        Transportadora ctt = new Transportadora("ctt",0.3, Atributos.PREMIUM,2,0.23,1.75,2.45,3.15);
        Transportadora tcc = new Transportadora("tcc",0.3,Atributos.NORMAL,2,0.23,1.75,2.45,3.15);
        sistema.adicionaTshirtVenda(1, "m","tshirt","something",20,0,new EstadoArtigo(),ctt, Atributos.VENDA , Atributos.L,Atributos.M);
        sistema.adicionaTshirtVenda(2, "m","tshirt1","something1",10,0,new EstadoArtigo(),ctt, Atributos.VENDA, Atributos.L,Atributos.M);
        sistema.adicionaTshirtVenda(3, "m","tshirt2","something2",10,0,new EstadoArtigo(),tcc, Atributos.VENDA, Atributos.L,Atributos.M);
        sistema.adicionaSapatilhaVenda(4, "m","sapatilha", "NIKE", 30, 0, new EstadoArtigo(), ctt, Atributos.VENDA , 43, 0,"Branca", LocalDate.now(), 0);

        //Todo:

        //sistema.adicionaSapatilhaVenda(3,"teste","teste",30,0,new EstadoArtigo(),ctt,46,Sapatilha.CORDAO,"teste", LocalDate.now(),0);
        //sistema.adicionaMalaVenda(5,"teste", "teste", 20,0, new EstadoArtigo(), ctt, 1,"teste", LocalDate.now(), 0);
        //sistema.adicionaArtigoVenda(tshirt);
        //sistema.adicionaArtigoVenda(tshirt1);
        //sistema.adicionaArtigoVenda(tshirt2);
        //sistema.adicionaArtigoVenda(sapatilha);

        do {
            switch (x)
            {
                case 0:
                    apresentacao.printMenu(s,1,nome);
                    x = ler.nextInt();
                    break;

                case 1:
                    apresentacao.printLogin();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                        ID: " + Apresentacao.RESET + utilizador.getId());
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                        Nome: " + Apresentacao.RESET + utilizador.getNome());
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                        Email: " + Apresentacao.RESET + utilizador.getEmail());
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                        Morada: " + Apresentacao.RESET + utilizador.getMorada());
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                        Nr. Fiscal: " + Apresentacao.RESET + utilizador.getNrFiscal());
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println(apresentacao.YELLOW + "                                                                                        Pressione enter para continuar..." + Apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 2: // MENU COMPRAR
                    apresentacao.printComprar();

                    paginateMenu(sistema.getArtigosVenda(email),2);


                    x = 0;
                    break;

                case 3: // MENU VENDAS
                    x = runVendas(email);
                    x = 0;

                case 4: // MENU FATURAS

                case 5: // MENU ENCOMENDA
                    x = runEncomendas(email);


                case 6:

            }
        } while (x != 6);

        return 0;
    }
    public int runVendas(String email) throws UtilizadorException {
        String[] s = {"Minha lista de vendas", "Adicionar artigos a minha lista de vendas", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        int x = 0;

        do {
            switch (x) {
                case 0:
                    apresentacao.printVendas();
                    apresentacao.printMenu(s,0, "");
                    x = ler.nextInt();
                    break;

                case 1: //MINHA LISTA DE ARTIGOS A VENDA
                    apresentacao.ptintMinhaLista();
                    //TODO: Mostrar todos os artigos a venda pelo o utilizador
                    //TODO: Retirar algum artigo à minha lista de vendas (pelo o ID)

                    paginateMenu(sistema.getArtigosVendaUtilizador(email),2);

                    ler = new Scanner(System.in);
                    x = ler.nextInt();
                    x = 0;
                    break;

                case 2: //ADICIONAR ARTIGOS A MINHA LISTA DE VENDAS
                    apresentacao.printAdicionaArtigoVenda();

                    //TODO: Adicionar algum artigo à minha lista de vendas
                    ler = new Scanner(System.in);
                    x = ler.nextInt();
                    x = 0;
                    break;

            }
        } while (x != 3);

        return x;
    }

    public int runConfig() throws SistemaException {
        int x = 0;
        String [] s = {"Avancar no tempo", "Alterar taxas e impostos", "Alterar tempo de devolucao", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        int opcao, dia, mes, ano;
        String c;

        do {
            switch (x) {
                case 0:
                    apresentacao.printMenu(s, 2, "");
                    x = ler.nextInt();
                    break;
                case 1: // MENU AVANCAR NO TEMPO
                    apresentacao.printSaltaTempo();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                  Selecione o metodo para avancar no tempo:");
                    System.out.println();
                    System.out.println(apresentacao.YELLOW + "                                                                                       1 - Avancar para uma data");
                    System.out.println(                      "                                                                                       2 - Adicionar dias à data atual" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                      ");
                    ler = new Scanner(System.in);
                    opcao = ler.nextInt();
                    LocalDate localDate;

                    if (opcao == 1) { // PARA UMA DATA
                        apresentacao.printSaltaTempo();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                            Introduza o dia (DD)" + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        dia = ler.nextInt();

                        System.out.println();

                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                            Introduza o mes (MM)" + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        mes = ler.nextInt();

                        System.out.println();

                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                           Introduza o ano (AAAA)" + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        ano = ler.nextInt();

                        sistema.saltaTempo(ano, mes, dia);
                        localDate = this.sistema.getDataAtual();

                        apresentacao.printSaltaTempo();
                        System.out.println(apresentacao.YELLOW + "                                                                                    SALTO NO TEMPO REALIZADO COM SUCESSO!!");
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                      Data atual do sistema: " + apresentacao.RESET + localDate);
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println("                                                                                        Pressione enter para sair...");
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                        x = 0;
                        break;
                    }

                    if (opcao == 2) { // ADICIONAR DIAS A DATA ATUAL
                        apresentacao.printSaltaTempo();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                            Introduza o numero de dias que pretende avancar (DD)" + apresentacao.RESET);
                        System.out.println();
                        System.out.print("                                                                                                      ");
                        ler = new Scanner(System.in);
                        dia = ler.nextInt();

                        sistema.saltaTempo(dia);
                        localDate = this.sistema.getDataAtual();

                        apresentacao.printSaltaTempo();
                        System.out.println(apresentacao.YELLOW + "                                                                                    SALTO NO TEMPO REALIZADO COM SUCESSO!!");
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                      Data atual do sistema: " + apresentacao.RESET + localDate);
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println("                                                                                        Pressione enter para sair...");
                        System.out.println();
                        System.out.print("                                                                                                     ");
                        ler = new Scanner(System.in);
                        c = ler.nextLine();
                        x = 0;
                        break;
                    }

                    x = 0;
                    break;

                case 2: // MENU ALTERAR IMPOSTO E TAXAS
                    apresentacao.printTax();
                    int imposto;
                    double taxa;

                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                           Defina a taxa de imposto" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    imposto = ler.nextInt();
                    sistema.setImposto(imposto);

                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                     Defina a taxa de uma encomanda pequena" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.setTaxaEncPequena(taxa);

                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                     Defina a taxa de uma encomanda media" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.setTaxaEncMedia(taxa);

                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                     Defina a taxa de uma encomanda grande" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    taxa = ler.nextDouble();
                    sistema.setTaxaEncGrande(taxa);

                    apresentacao.printTax();

                    System.out.println(apresentacao.YELLOW + "                                                                                       ALTERACAO REALIZADA COM SUCESSO!!" + apresentacao.RESET);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("                                                                                         Pressione enter para sair...");
                    System.out.println();
                    System.out.print("                                                                                                      ");
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

                case 3:
                    apresentacao.printReturnTime();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                            Defina o limite de dias para a devolucao de uma encomenda" + apresentacao.RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    x = ler.nextInt();

                    sistema.setTempoDevolucao(x);

                    apresentacao.printReturnTime();
                    System.out.println(apresentacao.YELLOW + "                                                                                       ALTERACAO REALIZADA COM SUCESSO!!" + apresentacao.RESET);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("                                                                                          Pressione enter para sair...");
                    System.out.println();
                    System.out.print("                                                                                                       ");
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    x = 0;
                    break;

            }
        } while (x != 4);

        return 0;
    }

    public int runEncomendas(String email) {
        int x = 0;
        String [] s = {"Pendentes", "Expedidas", "Finalizadas", "Devolvidas"};
        Scanner ler = new Scanner(System.in);
        Transportadora ctt = new Transportadora("ctt",0.3, Atributos.PREMIUM,2,0.23,1.75,2.45,3.15);
        Transportadora tcc = new Transportadora("tcc",0.3,Atributos.NORMAL,2,0.23,1.75,2.45,3.15);
        Tshirt tshirt = new (5, "m","tshirt","something",20,0,new EstadoArtigo(),ctt, Atributos.VENDA , Atributos.L,Atributos.M);
        Tshirt tshirt1 = new (6, "m","tshirt1","something1",10,0,new EstadoArtigo(),ctt, Atributos.VENDA, Atributos.L,Atributos.M);
        Tshirt tshirt2 = new(3, "m","tshirt2","something2",10,0,new EstadoArtigo(),tcc, Atributos.VENDA, Atributos.L,Atributos.M);
        Sapatilha sapatilha = (4, "m","sapatilha", "NIKE", 30, 0, new EstadoArtigo(), ctt, Atributos.VENDA , 43, 0,"Branca", LocalDate.now(), 0);




        do {
            switch (x) {
                case 0: // MENU ENCOMENDAS
                    apresentacao.printMenu(s,3,"");
                    x = ler.nextInt();
                    break;

                case 1: // MENU PENDENTES
                    apresentacao.printPendentes();
                    System.out.println(apresentacao.RED + "[ARTIGOS]" + apresentacao.RESET);



            }

        } while (x != 5);

        apresentacao.printEncomendas();






        return 0;
    }

    public void paginateMenu(Map<Integer, Artigo> lista, int pageSize) {
        Artigo[] menuItems = lista.values().toArray(new Artigo[0]);
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;

        do {
            apresentacao.printComprar();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Artigo artigo = menuItems[i];
                System.out.println(artigo.showArtigo());
            }

            if (numPages > 1) {
                System.out.println();
                System.out.println();
                System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
                System.out.println();
                System.out.println(apresentacao.CYAN_BOLD + "                                                                      Pressione" + apresentacao.RESET + " '+' " +
                        apresentacao.CYAN_BOLD + "para avancar," + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder e" + apresentacao.RESET +
                        " 's' " + apresentacao.CYAN_BOLD + "para sair" + apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                       ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("s")){
                    break;
                }
            }
        } while (true);
    }



}




//MENU - UTILIZADOR
// Comprar -> lista de todos os produtos a venda no mercado
// Vendas -> lista do que ele esta a vender + a possibilidade de adcionar artigos para venda
// Faturas -> do que ele ja vendeu/ganhou juntamente com o produto que ele vendeu)
// Sair

// MENU - COMPRAR
// Lista de produtos à venda

// MENU - LISTA DE PRODUTOS À VENDA
// Encomendar
// Filtros (por exemplo, por transportadora)

// MENU - FILTROS
// Encomendar

// MENU - VENDAS
// Minha lista (lista de produtos a ser vendida pelo utilizador)
// Adcionar novo artigo à lista

// MENU - ADICONAR NOVO ARTIGO Á LISTA
// Tipo de artigo

// MENU - FATURAS
// Lista de artigos comprados e o seu valor gasto
// Lista de artigos vendidos e o seu valor obtido

// MENU - LISTA DE ARTIGOS COMPRADOS E O SEU VALOR GASTO

// MENU - LISTA DE ARTIGOS VENDIDOS E O SEU VALOR OBTIDO
