import java.io.IOException;
import java.time.LocalDate;
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
                    x = runIN();
                    x = 0;
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

    private int runIN() throws UtilizadorException, TransportadoraException, ArtigoException {
        int x = 0;
        String email, pass, nome, morada, nomeTrans;
        int nif;
        int tipo = 0;
        double lucro;
        String[] s = {"Iniciar sessao - Utlizador", "Procurar Transportadora", "Registar - Utilizador", "Registar - Transportadora", "Retroceder"};
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

                    System.out.println(apresentacao.YELLOW + "                                                                     Introduza a transportadora que pretende procurar:" + apresentacao.RESET);
                    System.out.println("                                                                                         ");
                    ler = new Scanner(System.in);
                    nomeTrans = ler.nextLine();
                    Transportadora transportadora = sistema.procuraTransportadora(nomeTrans);
                    apresentacao.printProcuraTrans();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                       Nome:" + transportadora.getNome());
                    System.out.println(apresentacao.CYAN_BOLD + "                                Margem Lucro: " + transportadora.getMargemLucro() +
                            " Tipo: " + transportadora.getTipo() + " Imposto: " + transportadora.getImposto() + " Taxas: " + transportadora.getTxEncPq()+ "(Pequena), " + transportadora.getTxEncMd()+" (Média), " + transportadora.getTxEncGd()+" (Grande)");
                    System.out.println();
                    System.out.println();
                    ler = new Scanner(System.in);
                    x = ler.nextInt();
                    break;

                case 3: //Registar utilizador
                    apresentacao.printReg();

                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira o email:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    email = ler.nextLine();

                    System.out.println();

                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira a password:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    pass = ler.nextLine();

                    System.out.println();

                    //TODO: verificar se ja nao existe um email no sistema
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
                    //TODO: Duvida! Verificar se ja existe um numero fiiscal
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

                    if (x==1) x = 3;
                    else x = 0;

                    break;

                case 4:
                    apresentacao.printRegTrans();
                    apresentacao.cyan();
                    System.out.println("                                                                                      Insira o nome da transportadora:");
                    apresentacao.resetColor();
                    System.out.print("                                                                                      ");
                    ler = new Scanner(System.in);
                    nomeTrans = ler.nextLine();

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

                    sistema.adicionaTransportadora(nomeTrans, lucro, tipo);

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

                    if (x==1) x = 4;
                    else x = 0;

                    break;
            }
        } while (x != 5);

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
        //Transportadora ctt = new Transportadora("ctt",0.3, Atributos.PREMIUM,0.23,1.75,2.45,3.15);
        //Transportadora tcc = new Transportadora("tcc",0.3,Atributos.NORMAL,0.23,1.75,2.45,3.15);
        //Tshirt tshirt = new Tshirt(0, utilizador,"tshirt","something",20,0,new EstadoArtigo(),ctt, Atributos.VENDA , Atributos.L,Atributos.M);
        //Tshirt tshirt1 = new Tshirt(1, utilizador,"tshirt1","something1",10,0,new EstadoArtigo(),ctt, Atributos.VENDA, Atributos.L,Atributos.M);
        //Tshirt tshirt2 = new Tshirt(2, utilizador,"tshirt2","something2",10,0,new EstadoArtigo(),tcc, Atributos.VENDA, Atributos.L,Atributos.M);
        //Sapatilha sapatilha = new Sapatilha(3, utilizador,"sapatilha", "NIKE", 30, 0, new EstadoArtigo(), ctt, Atributos.VENDA , 43, 0,"Branca", LocalDate.now(), 0);

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
                    apresentacao.clear();
                    apresentacao.printComprar();
                    sistema.getListaArtigosVenda().forEach((k,v)->System.out.println(v.showArtigo()));
                    //TODO: Alterar para a funcao do sistema
                    ler = new Scanner(System.in);
                    x = ler.nextInt();



                case 3: // MENU VENDAS

                case 4: // MENU FATURAS

                case 5: // MENU ENCOMENDA

                case 6:

            }
        } while (x != 6);

        return 0;
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
