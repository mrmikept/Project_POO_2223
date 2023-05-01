import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

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
        String[] s = {"Entrar no programa", "Guardar estado", "Carregar estado anterior", "Carregar ficheiro para o sistema", "Estatísticas", "Sair"};
        Scanner ler = new Scanner(System.in);
        String input, input_backup;

        do {
            switch (x) {
                case 0:
                    apresentacao.printMenu(s, x, "");
                    x = ler.nextInt();
                    break;

                case 1:
                    try {
                        //sistema.adicionaUtilizador("r","r","r","r",1);
                        //x = runUtilizador("r");
                        x = runIN();
                        x = 0;
                    } catch (UtilizadorException a) {
                        System.out.println(a.getMessage());
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

                case 4:
                    apresentacao.printBackup();
                    apresentacao.cyan();
                    System.out.println("                                                               Indique o caminho para a pasta de onde pertende carregar o backup (../\"ficheiro\"):");
                    apresentacao.resetColor();
                    System.out.print("                                                               ");
                    ler = new Scanner(System.in);
                    input_backup = ler.nextLine();
                    Automatizacao backup = new Automatizacao(input_backup);
                    backup.carregaFicheiro(this.sistema);
                    if (!backup.getExcecoes().isEmpty()) {
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

    private int runIN() throws UtilizadorException, TransportadoraException, ArtigoException {
        int x = 0;
        String email, pass, nome, morada, nomeTrans, c;
        int nif;
        int tipo = 0;
        double lucro;
        String[] s = {"Iniciar sessao - Utlizador", "Procurar Transportadora", "Registar - Utilizador", "Registar - Transportadora", "Retroceder"};
        Scanner ler = new Scanner(System.in);

        do {
            switch (x) {
                case 0:
                    apresentacao.printMenu(s, x, "");
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
                    if (sistema.verificaUtilizador(email)) {
                        apresentacao.cyan();
                        System.out.println("                                                                                       Insira a sua password:");
                        apresentacao.resetColor();
                        System.out.print("                                                                                       ");
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();
                        if (sistema.verificaPassword(email, pass)) {
                            x = runUtilizador(email);
                            break;
                        } else {
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
                                if (sistema.verificaPassword(email, pass)) {
                                    x = runUtilizador(email);
                                    break;
                                }
                            }
                        }
                    } else {
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

                            if (sistema.verificaUtilizador(email)) {
                                System.out.println();
                                apresentacao.cyan();
                                System.out.println("                                                                                       Insira a sua password:");

                                apresentacao.resetColor();
                                System.out.print("                                                                                       ");
                                ler = new Scanner(System.in);
                                pass = ler.nextLine();
                                if (sistema.verificaPassword(email, pass)) {
                                    x = runUtilizador(email);
                                    break;
                                } else {
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
                                        if (sistema.verificaPassword(email, pass)) {
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
                            " | Tipo: " + apresentacao.RESET + transportadora.getTipo() + apresentacao.CYAN_BOLD + " | Imposto: " + apresentacao.RESET + transportadora.getImposto() + apresentacao.CYAN_BOLD + " | Taxas: " + apresentacao.RESET + transportadora.getTxEncPq() + apresentacao.CYAN_BOLD + " (Pequena), " + apresentacao.RESET + transportadora.getTxEncMd() + apresentacao.CYAN_BOLD + " (Média), " + apresentacao.RESET + transportadora.getTxEncGd() + apresentacao.CYAN_BOLD + " (Grande)" + apresentacao.RESET);
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
                    if (!sistema.verificaUtilizador(email)) {
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
                    } else {
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
                    if (!sistema.verificaTransportadora(nomeTrans)) {

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

                        if (x == 1) x = 4;
                        else x = 0;
                    } else {
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
            }
        } while (x != 5);

        return x;
    }

    private int runUtilizador(String email) throws UtilizadorException, ArtigoException, TransportadoraException //MENU UTILIZADOR
    {
        String[] s = {"Ver perfil", "Comprar", "Vendas", "Faturas", "Encomenda", "Retroceder"};
        int x = 0;
        String c;
        Utilizador utilizador = sistema.procuraUtilizador(email);
        String nome = utilizador.getNome();
        Scanner ler = new Scanner(System.in);
        Transportadora ctt = new Transportadora("ctt", 0.3, Atributos.PREMIUM, 0.23, 1.75, 2.45, 3.15);
        Transportadora tcc = new Transportadora("tcc", 0.3, Atributos.NORMAL, 0.23, 1.75, 2.45, 3.15);
        sistema.adicionaTransportadora("ctt", 25.4, Atributos.PREMIUM);
        sistema.adicionaTransportadora("tcc", 0.3, Atributos.NORMAL);
        sistema.adicionaTshirtVenda(1, "m", "tshirt", "something", 20,  new EstadoArtigo(), ctt, Atributos.VENDA, Atributos.L, Atributos.M);
        sistema.adicionaTshirtVenda(2, "m", "tshirt1", "something1", 10,  new EstadoArtigo(), ctt, Atributos.VENDA, Atributos.L, Atributos.M);
        sistema.adicionaTshirtVenda(3, "m", "tshirt2", "something2", 10,  new EstadoArtigo(Atributos.USADO, 4.3, 4), tcc, Atributos.VENDA, Atributos.L, Atributos.M);
        sistema.adicionaSapatilhaVenda(4, "m", "sapatilha", "NIKE", 30,  new EstadoArtigo(), ctt, Atributos.VENDA, 43, 0, "Branca", LocalDate.now(), 0);

        //sistema.adicionaSapatilhaVenda(3,"teste","teste",30,0,new EstadoArtigo(),ctt,46,Sapatilha.CORDAO,"teste", LocalDate.now(),0);
        //sistema.adicionaMalaVenda(5,"teste", "teste", 20,0, new EstadoArtigo(), ctt, 1,"teste", LocalDate.now(), 0);
        //sistema.adicionaArtigoVenda(tshirt);
        //sistema.adicionaArtigoVenda(tshirt1);
        //sistema.adicionaArtigoVenda(tshirt2);
        //sistema.adicionaArtigoVenda(sapatilha);

        do {
            switch (x) {
                case 0:
                    apresentacao.printMenu(s, 1, nome);
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

                    paginateMenuCompras(sistema.getArtigosVenda(email), 2, email);


                    x = 0;
                    break;

                case 3: // MENU VENDAS
                    x = runVendas(email);
                    x = 0;

                case 4: // MENU FATURAS

                case 5: // MENU ENCOMENDA

                case 6:

            }
        } while (x != 6);

        return 0;
    }

    public int runVendas(String email) throws UtilizadorException, ArtigoException, TransportadoraException {
        int id, opcao, nrDonos, tamanho, padrao, tipo, tipoCordao;
        double precoBase, avaliacao, dimensao;
        String descricao, marca, material, data, cor;
        String[] s = {"Minha lista de vendas", "Adicionar artigos a minha lista de vendas", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        int x = 0;

        do {
            switch (x) {
                case 0:
                    apresentacao.printVendas();
                    apresentacao.printMenu(s, 0, "");
                    x = ler.nextInt();
                    break;

                case 1: //MINHA LISTA DE ARTIGOS A VENDA
                    apresentacao.printMinhaLista();
                    //TODO: Retirar algum artigo à minha lista de vendas (pelo o ID)

                    if (sistema.getArtigosVendaUtilizador(email).isEmpty()) {
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                       NÃO POSSUI NENHUM ARTIGO À VENDA!!");
                        System.out.println();
                        System.out.println("                                                                               DESEJA ADICONAR ALGUM ARTIGO À SUA LISTA DE VENDAS?");
                        apresentacao.resetColor();
                        System.out.println();
                        System.out.println();
                        System.out.println("                                                                                                    1 - SIM");
                        System.out.println("                                                                                                    0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                       ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) runArtigosVender(email);
                        else if (x == 0) runVendas(email);
                    } else {
                        paginateMenuVendas(sistema.getArtigosVendaUtilizador(email), 2, email);
                        x =0;
                        break;

                    }

                case 2: //ADICIONAR ARTIGOS A MINHA LISTA DE VENDAS
                    x = runArtigosVender(email);
                    break;
            }

        } while (x != 3);

        return 0;
    }

    public int runArtigosVender(String email) throws ArtigoException, UtilizadorException, TransportadoraException {

        int id, opcao, nrDonos, tamanho, padrao, tipo, tipoCordao;
        double precoBase, avaliacao, dimensao;
        String descricao, marca, material, data, cor;
        int x = 0;

        Scanner ler = new Scanner(System.in);

        do {
            switch (x) {

                case 0:
                    apresentacao.printAdicionaArtigoVenda();
                    apresentacao.printArtigosVenda();
                    System.out.println("                                        (1)                                                              (2)                                                                 (3)");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                            ESCOLHA O ARTIGO QUE DESEJA VENDER OU PRESSIONE 0 PARA SAIR!");
                    apresentacao.resetColor();
                    System.out.println();
                    System.out.print("                                                                                                         ");
                    x = ler.nextInt();
                    break;

                case 1:
                    apresentacao.printTshirt();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O ID DA T-SHIRT (CÓDIGO DE BARRAS)");
                    apresentacao.resetColor();
                    System.out.print("                                                                                    ");
                    ler = new Scanner(System.in);
                    id = ler.nextInt();
                    if (!sistema.verificaArtigoVenda(id)) {

                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA UMA DESCRIÇÃO");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        descricao = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A MARCA");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        marca = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O PREÇO BASE");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        precoBase = ler.nextDouble();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O TAMANHO");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    3 - " + apresentacao.RESET + "XL");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    2 - " + apresentacao.RESET + "L");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "M");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "S");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        tamanho = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O PADRAO");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    2 - " + apresentacao.RESET + "PALMEIRAS");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "RISCAS");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "LISA");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        padrao = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU ESTADO:");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "NOVO");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "USADO");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        opcao = ler.nextInt();

                        if (opcao == 1) {
                            EstadoArtigo estadoNovo = new EstadoArtigo();
                            apresentacao.printTshirt();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Tshirt tshirt = new Tshirt(id, utilizador, descricao, marca, precoBase, estadoNovo, transportadora, Atributos.VENDA, tamanho, padrao);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(tshirt.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaTshirtVenda(id, email, descricao, marca, precoBase, estadoNovo, transportadora, Atributos.VENDA, tamanho, padrao);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;

                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        } else {
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA AVALIAÇÃO");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            avaliacao = ler.nextDouble();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            nrDonos = ler.nextInt();

                            EstadoArtigo estadoUsado = new EstadoArtigo(Atributos.USADO, avaliacao, nrDonos);
                            apresentacao.printTshirt();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Tshirt tshirt = new Tshirt(id, utilizador, descricao, marca, precoBase, estadoUsado, transportadora, Atributos.VENDA, tamanho, padrao);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(tshirt.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaTshirtVenda(id, email, descricao, marca, precoBase, estadoUsado, transportadora, Atributos.VENDA, tamanho, padrao);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        }
                    } else {
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                                              ESTE ARTIGO JÁ ESTÁ A VENDA!" + apresentacao.RESET);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                                DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                        System.out.println();
                        System.out.println("                                                                                                        1 - SIM");
                        System.out.println("                                                                                                        0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                          ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) {
                            x = 1;
                            break;
                        } else if (x == 0) {x = 4; break;}

                    }

                case 2:
                    apresentacao.printMala();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O ID DA MALA (CÓDIGO DE BARRAS)");
                    apresentacao.resetColor();
                    System.out.print("                                                                                    ");
                    ler = new Scanner(System.in);
                    id = ler.nextInt();
                    if (!sistema.verificaArtigoVenda(id)) {

                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA UMA DESCRIÇÃO");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        descricao = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A MARCA");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        marca = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O PREÇO BASE");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        precoBase = ler.nextDouble();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA DIMENSÃO");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        dimensao = ler.nextDouble();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O MATERIAL");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        material = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA DATA DE LANÇAMENTO (EX: ANO-MÊS-DIA)");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        data = ler.nextLine();
                        LocalDate dataLancamento = stringParaData(data);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU TIPO");
                        apresentacao.resetColor();
                        System.out.println("                                                                                    1 - PREMIUM");
                        System.out.println("                                                                                    0 - NORMAL");
                        System.out.println();
                        System.out.print("                                                                                        ");
                        ler = new Scanner(System.in);
                        tipo = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU ESTADO:");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "NOVO");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "USADO");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        opcao = ler.nextInt();
                        if (opcao == 1) {
                            EstadoArtigo estadoNovo = new EstadoArtigo();
                            apresentacao.printMala();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Mala mala = new Mala(id, utilizador, descricao, marca, precoBase, estadoNovo, transportadora, Atributos.VENDA, dimensao, material, dataLancamento, tipo);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(mala.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaMalaVenda(id, email, descricao, marca, precoBase, estadoNovo, transportadora, Atributos.VENDA, dimensao, material, dataLancamento, tipo);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        } else {
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA AVALIAÇÃO");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            avaliacao = ler.nextDouble();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            nrDonos = ler.nextInt();

                            EstadoArtigo estadoUsado = new EstadoArtigo(Atributos.USADO, avaliacao, nrDonos);
                            apresentacao.printMala();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Mala mala = new Mala(id, utilizador, descricao, marca, precoBase, estadoUsado, transportadora, Atributos.VENDA, dimensao, material, dataLancamento, tipo);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(mala.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaMalaVenda(id, email, descricao, marca, precoBase, estadoUsado, transportadora, Atributos.VENDA, dimensao, material, dataLancamento, tipo);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        }
                    } else {
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                                              ESTE ARTIGO JÁ ESTÁ A VENDA!" + apresentacao.RESET);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                                DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                        System.out.println();
                        System.out.println("                                                                                                        1 - SIM");
                        System.out.println("                                                                                                        0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                          ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) {
                            x = 2;
                        } else if (x == 0) {x = 4; break;}

                    }
                case 3:
                    apresentacao.printSapatilhas();
                    System.out.println();
                    System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O ID DA SAPATILHA (CÓDIGO DE BARRAS)");
                    apresentacao.resetColor();
                    System.out.print("                                                                                    ");
                    ler = new Scanner(System.in);
                    id = ler.nextInt();
                    if (!sistema.verificaArtigoVenda(id)) {

                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA UMA DESCRIÇÃO");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        descricao = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A MARCA");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        marca = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O PREÇO BASE");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        precoBase = ler.nextDouble();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O SEU TAMANHO (Nº DE CALÇADO)");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        tamanho = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O TIPO DE CORDÃO");
                        apresentacao.resetColor();
                        System.out.println("                                                                                    1 - ATILHO");
                        System.out.println("                                                                                    0 - CORDÃO");
                        System.out.println();
                        System.out.print("                                                                                       ");
                        tipoCordao = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA COR");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        cor = ler.nextLine();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA DATA DE LANÇAMENTO (EX: ANO-MÊS-DIA)");
                        apresentacao.resetColor();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        data = ler.nextLine();
                        LocalDate dataLancamento = stringParaData(data);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU TIPO");
                        apresentacao.resetColor();
                        System.out.println("                                                                                    1 - PREMIUM");
                        System.out.println("                                                                                    0 - NORMAL");
                        System.out.println();
                        System.out.print("                                                                                       ");
                        ler = new Scanner(System.in);
                        tipo = ler.nextInt();
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INDIQUE O SEU ESTADO:");
                        apresentacao.resetColor();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    1 - " + apresentacao.RESET + "NOVO");
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                    0 - " + apresentacao.RESET + "USADO");
                        System.out.println();
                        System.out.print("                                                                                    ");
                        ler = new Scanner(System.in);
                        opcao = ler.nextInt();
                        if (opcao == 1) {
                            EstadoArtigo estadoNovo = new EstadoArtigo();
                            apresentacao.printSapatilhas();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Sapatilha sapatilha = new Sapatilha(id, utilizador, descricao, marca, precoBase, estadoNovo, transportadora, Atributos.VENDA, tamanho, tipoCordao, cor, dataLancamento, tipo);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(sapatilha.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaSapatilhaVenda(id, email, descricao, marca, precoBase, estadoNovo, transportadora, Atributos.VENDA, tamanho, tipoCordao, cor, dataLancamento, tipo);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        } else {
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA A SUA AVALIAÇÃO");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            avaliacao = ler.nextDouble();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                    INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE");
                            apresentacao.resetColor();
                            System.out.print("                                                                                    ");
                            ler = new Scanner(System.in);
                            nrDonos = ler.nextInt();

                            EstadoArtigo estadoUsado = new EstadoArtigo(Atributos.USADO, avaliacao, nrDonos);
                            apresentacao.printSapatilhas();
                            System.out.println();
                            System.out.println();
                            String nomeTransportadora = paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                            Transportadora transportadora = sistema.procuraTransportadora(nomeTransportadora);
                            Utilizador utilizador = sistema.procuraUtilizador(email);
                            Sapatilha sapatilha = new Sapatilha(id, utilizador, descricao, marca, precoBase, estadoUsado, transportadora, Atributos.VENDA, tamanho, tipoCordao, cor, dataLancamento, tipo);
                            apresentacao.clear();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(sapatilha.showArtigo());
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(apresentacao.CYAN_BOLD + "                                                                                 TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?" + apresentacao.RESET);
                            System.out.println();
                            System.out.println("                                                                                                   1 - SIM");
                            System.out.println("                                                                                                   0 - NÃO");
                            System.out.println();
                            System.out.print("                                                                                                      ");

                            ler = new Scanner(System.in);
                            x = ler.nextInt();

                            if (x == 1) {
                                sistema.adicionaSapatilhaVenda(id, email, descricao, marca, precoBase, estadoUsado, transportadora, Atributos.VENDA, tamanho, tipoCordao, cor, dataLancamento, tipo);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                apresentacao.yellow();
                                System.out.println("                                                                            ARTIGO ADICIONADO COM SUCESSO!! DESEJA ADICIONAR OUTRO?");
                                apresentacao.resetColor();
                                System.out.println();
                                System.out.println("                                                                                                    1 - SIM");
                                System.out.println("                                                                                                    0 - NAO");
                                System.out.println();
                                System.out.print("                                                                                                      ");

                                ler = new Scanner(System.in);
                                x = ler.nextInt();

                                if (x == 1) {
                                    x = 0;
                                    break;
                                } else if (x == 0) {x = 4; break;}

                            } else if (x == 0) {x = 4; break;}

                        }
                    } else {
                        System.out.println();
                        System.out.println();
                        System.out.println(apresentacao.RED + "                                                                                              ESTE ARTIGO JÁ ESTÁ A VENDA!" + apresentacao.RESET);
                        System.out.println();
                        System.out.println(apresentacao.CYAN_BOLD + "                                                                                                DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                        System.out.println();
                        System.out.println("                                                                                                        1 - SIM");
                        System.out.println("                                                                                                        0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                          ");

                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 1) {
                            x = 3;
                        } else if (x == 0) {x = 4; break;}

                    }
            }
        } while (x != 4);

        return 0;
    }

    public void paginateMenuVendas(Map<Integer, Artigo> lista, int pageSize, String email) throws ArtigoException, UtilizadorException, TransportadoraException {
        Artigo[] menuItems = lista.values().toArray(new Artigo[0]);
        String c;
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;

        do {
            apresentacao.printVendas();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Artigo artigo = menuItems[i];
                System.out.println(artigo.showArtigo());
            }

            if (numPages == 1){
                System.out.println();
                System.out.println();
                System.out.println(apresentacao.YELLOW + "                                                                                        Pressione enter para continuar..." + Apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                       ");
                Scanner ler = new Scanner(System.in);
                c = ler.nextLine();
                runVendas(email);
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
                } else if (input.equals("s")) {
                    break;
                }
            }
        } while (true);
    }

    public void paginateMenuCompras(Map<Integer, Artigo> lista, int pageSize, String email) throws ArtigoException, UtilizadorException, TransportadoraException {
        Artigo[] menuItems = lista.values().toArray(new Artigo[0]);
        String c;
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

            if (numPages == 1){
                System.out.println();
                System.out.println();
                System.out.println(apresentacao.YELLOW + "                                                                                        Pressione enter para continuar..." + Apresentacao.RESET);
                System.out.println();
                System.out.print("                                                                                                       ");
                Scanner ler = new Scanner(System.in);
                c = ler.nextLine();
                runVendas(email);
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
                } else if (input.equals("s")) {
                    break;
                }
            }
        } while (true);
    }

    public String paginateTransportadora(Map<String, Transportadora> lista, int pageSize, String email) throws TransportadoraException, UtilizadorException, ArtigoException {
        Transportadora[] menuItems = lista.values().toArray(new Transportadora[0]);
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;
        do {
            apresentacao.printProcuraTrans();
            System.out.println();
            System.out.println();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Transportadora transportadora = menuItems[i];
                System.out.println();
                System.out.println(transportadora.showTransportadora(key));
            }

            if (numPages > 1) {
                System.out.println();
                System.out.println();
                System.out.println("                                                                                                 Pag." + currentPage + " de " + numPages);
                System.out.println();
                System.out.println(apresentacao.CYAN_BOLD + "                                                                               Pressione" + apresentacao.RESET + " '+' " +
                        apresentacao.CYAN_BOLD + "para avancar e" + apresentacao.RESET + " '-' " + apresentacao.CYAN_BOLD + "para a retroceder ");
                System.out.println("                                                                              Para selecionar a transportadora, digite o nome dela");
                apresentacao.resetColor();
                System.out.println();
                System.out.print("                                                                                                       ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (!sistema.verificaTransportadora(input)) {
                    System.out.println();
                    System.out.println(apresentacao.RED + "                                                        A TRANSPORTADORA COM O NOME "+ apresentacao.RESET + input + apresentacao.RED + " NÃO EXISTE" + apresentacao.RESET);
                    System.out.println(apresentacao.CYAN_BOLD +"                                                                            DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                    System.out.println();
                    System.out.println("                                                                                                            1 - SIM");
                    System.out.println("                                                                                                            0 - NAO");
                    System.out.println();
                    System.out.print("                                                                                                              ");

                    Scanner ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 1) paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                    else runVendas(email);

                }
                else
                {
                    return input;
                }
            } else if (numPages == 1) {
                System.out.println();
                System.out.println();
                System.out.println("                                                                                 Para selecionar a transportadora, digite o nome dela");
                System.out.println();
                System.out.print("                                                                                                        ");

                Scanner scanner = new Scanner(System.in);
                String nomeTransportadora = scanner.nextLine();
                if (!sistema.verificaTransportadora(nomeTransportadora)){

                    System.out.println();
                    System.out.println(apresentacao.RED + "                                                        A TRANSPORTADORA COM O NOME "+ apresentacao.RESET + nomeTransportadora + apresentacao.RED + " NÃO EXISTE" + apresentacao.RESET);
                    System.out.println(apresentacao.CYAN_BOLD +"                                                                            DESEJA TENTAR NOVAMENTE?" + apresentacao.RESET);
                    System.out.println();
                    System.out.println("                                                                                                            1 - SIM");
                    System.out.println("                                                                                                            0 - NAO");
                    System.out.println();
                    System.out.print("                                                                                                              ");

                    Scanner ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 1) paginateTransportadora(sistema.getListaTransportadoras(), 1, email);
                    else runVendas(email);

                }
                else
                {
                    return nomeTransportadora;
                }
            }
        } while (true);
    }


    public LocalDate stringParaData(String dma){
        String[] dataf = dma.split("-");
        int ano = Integer.valueOf(dataf[0]);
        int mes = Integer.valueOf(dataf[1]);
        int dia = Integer.valueOf(dataf[2]);
        return LocalDate.of(ano,mes,dia);
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
