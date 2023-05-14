import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private Sistema sistema;
    private final Apresentacao apresentacao;

    public static void main(String[] args) throws UtilizadorException, TransportadoraException, ClassNotFoundException, ArtigoException, EncomendaException, SistemaException {
        new Main().run();
    }

    public Main() {
        this.sistema = new Sistema();
        this.apresentacao = new Apresentacao();
    }

    

    public boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean notDouble(String input) {
        try {
            // Attempt to parse the input string as a double
            Double.parseDouble(input);
            return false;
        } catch (NumberFormatException e) {
            // The input string is not a valid double
            return true;
        }
    }

    private int stringToInt(String str) {
        return Integer.parseInt(str);
    }

    public double stringToDouble(String input) throws NumberFormatException {
        return Double.parseDouble(input);
    }

    public boolean naoContemLetras(String input) {
        // Regular expression to match one or more alphabetical characters
        String regex = "[a-zA-Z ]+";

        // Check if the input matches the regular expression
        return !input.matches(regex);
    }

    private void run() throws UtilizadorException, TransportadoraException, ClassNotFoundException, ArtigoException, EncomendaException, SistemaException {
        int x = 0;
        String c;
        String[] s = {"Entrar no programa", "Guardar estado", "Carregar estado anterior", "Carregar ficheiro de Automatização", "Sair"};
        Scanner ler = new Scanner(System.in);
        String eq;
        String input, input_backup;

        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printMenu(s, x, 87, "", sistema.getDataAtual());
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (eq.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (eq.equals("3")) {
                            x = 3;
                            break;
                        }
                        if (eq.equals("4")) {
                            x = 4;
                            break;
                        }
                        if (eq.equals("5")) {
                            x = 5;
                            break;
                        }
                    } while (true);
                case 1:
                    try {
                        sistema.atualizaSistema();
                        x = runPrograma();
                    } catch (UtilizadorException a) {
                        System.out.println(a.getMessage());
                    } catch (SistemaException | EncomendaException e) {
                        throw new RuntimeException(e);
                    }
                case 2:
                    apresentacao.printMenuGuardar();
                    ler = new Scanner(System.in);
                    input = ler.nextLine();

                    if (input.isEmpty()){
                        do {
                            apresentacao.printMenuGuardar();
                            apresentacao.printMensagemCentrada("ERRO! DEVE INSERIR O NOME DO FICHEIRO",2);
                            apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 93, 2);
                            apresentacao.printMensagemSimOuNao(101);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;}
                            if (c.equals("0")) { x = 0; break;}
                        } while (true);
                        break;
                    }
                    try {
                        CarregamentoFicheiro.escreveFicheiro(this.sistema, input);
                        apresentacao.printGuardar();
                        apresentacao.printEnter("   ESTADO GUARDADO COM SUCESSO!!");
                        ler.nextLine();
                        x = 0;
                    } catch (IOException e) {
                        apresentacao.printGuardar();
                        apresentacao.printEnter("   ERRO AO GUARDAR O FICHEIRO!!");
                        ler.nextLine();
                    } catch (CarregamentoFicheiroException a) {
                        apresentacao.printMensagem(a.getMessage(), 87, 2);
                        apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                        apresentacao.printMensagemSimOuNao(101);

                        c = ler.nextLine();

                        if (!isInt(c)) {
                            c = apresentacao.printInputIncorreto(4);
                        }

                        x = stringToInt(c);

                        if (x == 1) {
                            x = 2;
                        }
                    }
                case 3:
                    apresentacao.printMenuCarregarEstado();
                    ler = new Scanner(System.in);
                    input = ler.nextLine();
                    if (input.isEmpty()) {
                        do {
                            apresentacao.printMenuCarregarEstado();
                            apresentacao.printMensagemCentrada("   ERRO! DEVE INSERIR O NOME DO FICHEIRO QUE DESEJA CARREGAR!", 2);
                            apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 94, 2);
                            apresentacao.printMensagemSimOuNao(102);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    try {
                        this.sistema = CarregamentoFicheiro.lerFicheiro(input);
                        apresentacao.printLoad();
                        apresentacao.printEnter("ESTADO CARREGADO COM SUCESSO!!");
                        ler.nextLine();
                        x = 0;
                    } catch (IOException e) {
                        apresentacao.printErroFicheiro();
                        apresentacao.printEnterSair(90);
                        ler.nextLine();
                        x = 0;
                    } catch (CarregamentoFicheiroException e) {
                        do {
                            apresentacao.printMenuCarregarEstado();
                            apresentacao.printMensagem(e.getMessage(), 95, 2);
                            apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 94, 2);
                            apresentacao.printMensagemSimOuNao(102);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                    }
                case 4:
                    apresentacao.printMenuAutomatizacao();
                    ler = new Scanner(System.in);
                    input_backup = ler.nextLine();
                    if (input_backup.isEmpty()){
                        do {
                            apresentacao.printMenuAutomatizacao();
                            apresentacao.printMensagemCentrada("ERRO! DEVE INSERIR O NOME DO FICHEIRO QUE PRETENDE CARREGAR",2);
                            apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 92, 2);
                            apresentacao.printMensagemSimOuNao(102);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;}
                            if (c.equals("0")) {x = 0;break;}
                        } while (true);
                        break;
                    }
                    try {
                        Automatizacao backup = new Automatizacao(input_backup);
                        backup.carregaFicheiro(this.sistema);
                        if (!backup.getExcecoes().isEmpty()) {
                            apresentacao.printErrosAutomatizcao(backup.getExcecoes());
                            ler.nextLine();
                            x = 0;
                            break;
                        }
                        apresentacao.printBackup();
                        apresentacao.printEnter("AUTOMATIZAÇÃO EXECUTADA COM SUCESSO!!");
                        ler.nextLine();
                        x = 0;
                    } catch (IOException e) {
                        apresentacao.printErroFicheiro();
                        apresentacao.printEnterSair(90);
                        ler.nextLine();
                        x = 0;
                    }
                    catch (AutomatizacaoException a){
                        do {
                            apresentacao.printMenuAutomatizacao();
                            apresentacao.printMensagem(a.getMessage(),95,2);
                            apresentacao.printMensagemCentrada("  DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(102);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;}
                            if (c.equals("0")) {x = 0;break;}
                        } while (true);
                    }
            }
        } while (x != 5);
        Apresentacao.clear();
    }

    private int runPrograma() throws UtilizadorException, TransportadoraException, ArtigoException, SistemaException, EncomendaException {
        int x = 0, teste, nNif;
        String email, pass, nome, morada, nomeTrans, c, nif, tipo, lucro, tempoExpedicao;
        String[] s = {"Iniciar sessao - Utlizador", "Iniciar sessao - Transportadora", "Registar - Utilizador", "Registar - Transportadora", "Estatisticas", "Configuracoes", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        String eq;

        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printMenu(s, x, 87, "", sistema.getDataAtual());
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (eq.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (eq.equals("3")) {
                            x = 3;
                            break;
                        }
                        if (eq.equals("4")) {
                            x = 4;
                            break;
                        }
                        if (eq.equals("5")) {
                            x = 5;
                            break;
                        }
                        if (eq.equals("6")) {
                            x = 6;
                            break;
                        }
                        if (eq.equals("7")) {
                            x = 7;
                            break;
                        }
                    } while (true);
                case 1: //Iniciar sessao
                    apresentacao.printMenuLogin();
                    apresentacao.printEspacos(88);
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    try {
                        sistema.verificaUtilizador(email);
                    } catch (UtilizadorException a) {
                        apresentacao.printMensagemCentrada(a.getMessage(), 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(0, 1, 0);
                        break;
                    }
                    teste = 0;
                    while (teste == 0) {
                        Apresentacao.clear();
                        apresentacao.printMenuLogin();
                        apresentacao.printMensagem(email, 88, 0);
                        apresentacao.printMensagem("Insira a sua password:", 88, 1);
                        apresentacao.printEspacos(88);
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();
                        try {
                            sistema.verificaPasswordUtilizador(email, pass);
                            x = runUtilizador(email);
                            break;
                        } catch (UtilizadorException a) {
                            apresentacao.printMensagemCentrada(a.getMessage(), 2);
                            apresentacao.printClear(1);
                            x = valorSimouNao(0, 1, 0);
                        }
                        if (x == 0) {
                            teste = 1;
                        }
                    }
                case 2:
                    apresentacao.printTrans();
                    apresentacao.printMensagem("Insira o email:", 86, 1);
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    try {
                        sistema.verificaEmailTransportadora(email);
                    } catch (TransportadoraException a) {
                        apresentacao.printMensagemCentrada(a.getMessage(), 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(1, 2, 0);
                        break;
                    }
                    teste = 0;
                    while (teste == 0) {
                        apresentacao.printTrans();
                        apresentacao.printMensagem("Insira o email:", 86, 1);
                        apresentacao.printMensagem(email, 86, 0);
                        apresentacao.printMensagem("Insira a password:", 86, 1);
                        apresentacao.printEspacos(86);
                        ler = new Scanner(System.in);
                        pass = ler.nextLine();
                        try {
                            sistema.verificaPasswordTransportadora(email, pass);
                            x = runTransportadora(email);
                            break;
                        } catch (TransportadoraException a) {
                            apresentacao.printMensagemCentrada(a.getMessage(), 2);
                            apresentacao.printClear(1);
                            x = valorSimouNao(1, 1, 0);
                        }
                        if (x == 0) {
                            teste = 1;
                        }
                    }
                case 3: //Registar utilizador
                    apresentacao.printReg();
                    apresentacao.printMensagem("Insira o email:", 86, 1);
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    if (email.isEmpty()) {
                        apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UM EMAIL!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(2, 3, 0);
                        break;
                    }
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira a password:", 86, 1);
                    apresentacao.printEspacos(86);
                    pass = ler.nextLine();
                    if (pass.isEmpty()) {
                        apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UMA PASSWORD!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(2, 3, 0);
                        break;
                    }
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira o seu primeiro e ultimo nome:", 86, 1);
                    apresentacao.printEspacos(86);
                    nome = ler.nextLine();
                    if (naoContemLetras(nome)) {
                        apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADAS LETRAS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(2, 3, 0);
                        break;
                    }
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira a sua morada:", 86, 1);
                    apresentacao.printEspacos(86);
                    morada = ler.nextLine();
                    if (morada.isEmpty()) {
                        apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UMA MORADA!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(2, 3, 0);
                        break;
                    }
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira o seu numero fiscal:", 86, 1);
                    apresentacao.printEspacos(86);
                    nif = ler.nextLine();
                    if (!isInt(nif)) {
                        apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(2, 3, 0);
                        break;
                    }
                    nNif = stringToInt(nif);
                    try {
                        sistema.adicionaUtilizador(email, pass, nome, morada, nNif);
                        do {
                            apresentacao.printReg();
                            apresentacao.printMensagem("UTILIZADOR REGISTADO COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?", 72, 3);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(100);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                    } catch (UtilizadorException a) {

                        apresentacao.printClear(1);
                        apresentacao.printMensagemCentrada(a.getMessage(), 2);
                        x = valorSimouNao(2, 3, 0);
                    }
                case 4:
                    apresentacao.printRegTrans();
                    apresentacao.printMensagem("Insira o email:", 86, 1);
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    email = ler.nextLine();
                    if (email.isEmpty()) {
                        apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UM EMAIL!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(3, 4, 0);
                        break;
                    }
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira a password:", 86, 1);
                    apresentacao.printEspacos(86);
                    pass = ler.nextLine();
                    if (pass.isEmpty()) {
                        apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UMA PASSWORD!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(3, 4, 0);
                        break;
                    }
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira o nome da transportadora:", 86, 1);
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    nomeTrans = ler.nextLine();
                    if (naoContemLetras(nomeTrans)) {
                        apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADAS LETRAS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(3, 4, 0);
                        break;
                    }
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira a sua morada:", 86, 1);
                    apresentacao.printEspacos(86);
                    morada = ler.nextLine();
                    if (morada.isEmpty()) {
                        apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UMA MORADA!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(3, 4, 0);
                        break;
                    }
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira o seu numero fiscal:", 86, 1);
                    apresentacao.printEspacos(86);
                    nif = ler.nextLine();
                    if (!isInt(nif)) {
                        apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(3, 4, 0);
                        break;
                    }
                    nNif = stringToInt(nif);
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira a margem de lucro que pretende obter:", 86, 1);
                    apresentacao.printEspacos(86);
                    lucro = ler.nextLine();
                    if (notDouble(lucro)) {
                        apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(3, 4, 0);
                        break;
                    }
                    double nLucro = stringToDouble(lucro);
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Insira 0-\"Normal\" ou 1-\"Premium\":", 86, 1);
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    tipo = ler.nextLine();
                    if (!tipo.equals("0") && !tipo.equals("1")) {
                        apresentacao.printMensagemCentrada("ERR0! SO PODE INSERIR 0 0U 1!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(3, 4, 0);
                        break;
                    }
                    int nTipo = stringToInt(tipo);
                    apresentacao.printMensagem("Insira o tempo de expedição de uma encomenda:", 86, 1);
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    tempoExpedicao = ler.nextLine();
                    if (!isInt(tempoExpedicao)) {
                        apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(3, 4, 0);
                        break;
                    }
                    int nTempoExpedicao = stringToInt(tempoExpedicao);
                    try {
                        sistema.adicionaTransportadora(email, pass, nomeTrans, morada, nNif, nLucro, nTipo, nTempoExpedicao);

                        do {
                            apresentacao.printRegTrans();
                            apresentacao.printMensagemCentrada("TRANSPORTADORA REGISTADA COM SUCESSO!! DESEJA CONTINUAR A REGISTAR?", 3);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemSimOuNao(100);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                    } catch (TransportadoraException a) {
                        apresentacao.printClear(3);
                        apresentacao.printMensagemCentrada(a.getMessage(), 2);
                        x = valorSimouNao(3, 4, 0);
                    }
                case 5: // MENU ESTATISTICAS
                        x = runEstatisticas();
                case 6: // MENU CONFIGURACOES
                        x = runConfig();
            }

            }while (x != 7);
        return 0;
        }

    public int runUtilizador(String email) throws UtilizadorException, ArtigoException, TransportadoraException, EncomendaException //MENU UTILIZADOR
    {
        String[] s = {"Ver perfil", "Comprar", "Vendas", "Encomendas", "Faturas", "Retroceder"};
        int x = 0;
        String eq;
        Utilizador utilizador = sistema.procuraUtilizador(email);
        String nome = utilizador.getNome();
        Scanner ler = new Scanner(System.in);
        apresentacao.printMenu(s, 1, 87, nome, sistema.getDataAtual());

        do {
            switch (x) {
                case 0:
                    do {

                        apresentacao.printMenu(s, 1, 87, nome, sistema.getDataAtual());
                        eq = ler.nextLine().toLowerCase();

                        if (eq.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (eq.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (eq.equals("3")) {
                            x = 3;
                            break;
                        }
                        if (eq.equals("4")) {
                            x = 4;
                            break;
                        }
                        if (eq.equals("5")) {
                            x = 5;
                            break;
                        }
                        if (eq.equals("6")) {
                            x = 6;
                            break;
                        }
                    } while (true);
                case 1:
                    apresentacao.printLogin();
                    apresentacao.printPerfil(utilizador);
                    ler.nextLine();
                    x = 0;
                case 2: // MENU COMPRAR
                        x = runMenuComprar(email);
                case 3: // MENU VENDAS
                        x = runVendas(email);
                case 4: // MENU ENCOMENDAS
                        x = runEncomendas(email);
                case 5: // MENU FATURAS
                        x = runFaturas(email);
            }
        } while (x != 6);

        return 0;
    }

    public void paginacaoEncTransportadora(List<String> encomendas)
    {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        int quantidade = 10;
        do {
            apresentacao.printEncomendas();
            int numPaginas = (int) Math.ceil((double) encomendas.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, encomendas.size());
            apresentacao.paginateMenu(encomendas, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            apresentacao.printEspacos(74);
            System.out.println(Apresentacao.CYAN_BOLD + "Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();
            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("s")) {
                break;
            }
        } while (true);
    }

    public int runTransportadora(String email) throws TransportadoraException {
        Transportadora transportadora = sistema.procuraTransportadoraEmail(email);
        int x = 0;
        String [] s = {"Dados da transportadora", "Encomendas" ,"Alterar margem de lucro", "Alterar tempo de expedição", "Retroceder"};
        String eq, c;
        Scanner ler = new Scanner(System.in);
        double lucro;

        do{
            switch (x) {
                case 0:
                    do {
                        apresentacao.printMenu(s, 1, 87, transportadora.getNome(), sistema.getDataAtual());
                        eq = ler.nextLine();
                        if (eq.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (eq.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (eq.equals("3")) {
                            x = 3;
                            break;
                        }
                        if (eq.equals("4")) {
                            x = 4;
                            break;
                        }
                        if (eq.equals("5")) {
                            x = 5;
                            break;
                        }
                    } while (true);
                case 1:
                    apresentacao.printDadosTransportadora(transportadora);
                    ler.nextLine();
                    x = 0;

                case 2:
                    List<Encomenda> lista = sistema.listaEncomendaTransportadoras(email);
                    if (lista.isEmpty()) {
                        apresentacao.printEncomendas();
                        apresentacao.printMensagemCentrada("Esta transportadora não possui encomendas associadas!", 2);
                        apresentacao.printEnter("");
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    List<String> strings = new ArrayList<>();
                    for (Encomenda encomenda : lista) {
                        strings.add(apresentacao.showEncomendaEstatisticas(encomenda));
                    }
                    paginacaoEncTransportadora(strings);
                    x = 0;
                case 3:
                    transportadora = sistema.procuraTransportadoraEmail(email);
                    apresentacao.printTrans();
                    apresentacao.printClear(1);
                    apresentacao.printEspacos(93);
                    System.out.print(Apresentacao.CYAN_BOLD + "Lucro atual: " + Apresentacao.RESET + transportadora.getMargemLucro());
                    apresentacao.printClear(2);
                    apresentacao.printMensagem("Insira a nova margem de lucro da transportadora:", 77, 1);
                    apresentacao.printEspacos(77);
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    if (notDouble(c)) {
                        apresentacao.printMensagemCentrada("ERR0! SÓ PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(1, 3, 0);
                        break;
                    }
                    lucro = stringToDouble(c);
                    if (lucro <= 0) {
                        apresentacao.printMensagemCentrada("ERR0! SÓ PODEM SER VALORES POSITIVOS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(1, 3, 0);
                        break;
                    }
                    sistema.alteraMargemLucroTransportadora(email, lucro);
                    apresentacao.printTrans();
                    apresentacao.printMensagemCentrada("MARGEM DE LUCRO ALTERADA COM SUCESSO", 3);
                    apresentacao.printClear(1);
                    apresentacao.printEspacos(90);
                    System.out.println(Apresentacao.CYAN_BOLD + "Margem de lucro atual: " + Apresentacao.RESET + lucro);
                    apresentacao.printEnterSair(90);
                    ler.nextLine();
                    x = 0;
                case 4:
                    transportadora = sistema.procuraTransportadoraEmail(email);
                    apresentacao.printTrans();
                    apresentacao.printClear(1);
                    apresentacao.printEspacos(89);
                    System.out.print(Apresentacao.CYAN_BOLD + "Tempo de expedição atual: " + Apresentacao.RESET + transportadora.getTempoExpedicao());
                    apresentacao.printClear(2);
                    apresentacao.printMensagem("Insira o novo tempo de expedição da transportadora:", 77, 1);
                    apresentacao.printEspacos(77);
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    if (!isInt(c)) {
                        apresentacao.printMensagemCentrada("ERR0! SÓ PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(1, 4, 0);
                        break;
                    }
                    x = stringToInt(c);
                    int n = x;
                    if (x <= 0) {
                        apresentacao.printMensagemCentrada("ERR0! SÓ PODEM SER VALORES POSITIVOS!!", 2);
                        apresentacao.printClear(1);
                        x = valorSimouNao(1, 4, 0);
                        break;
                    }
                    sistema.alteraTempoExpedicaoTransportadora(email, x);
                    apresentacao.printTrans();
                    apresentacao.printMensagemCentrada("TEMPO DE EXPEDICÃO ALTERADO COM SUCESSO", 3);
                    apresentacao.printClear(1);
                    apresentacao.printEspacos(90);
                    System.out.println(Apresentacao.CYAN_BOLD + "Tempo de expedição atual: " + Apresentacao.RESET + n);
                    apresentacao.printEnterSair(90);
                    ler.nextLine();
                    x = 0;
            }
        } while(x != 5);

        return 0;
    }

    public int runMenuComprar(String email) {
        Scanner ler = new Scanner(System.in);
        String opcao;
        int paginaAtual = 1;
        do {
            List<Artigo> artigos = sistema.getArtigosVenda(email).values().stream().collect(Collectors.toList());
            if (artigos.isEmpty()) {
                apresentacao.printComprar();
                apresentacao.printMensagem("Não existem artigos para comprar!", 88, 2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : artigos) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
                apresentacao.printComprar();
                apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
                apresentacao.printClear(2);
                System.out.println(Apresentacao.CYAN_BOLD + "                                             Pressione" + Apresentacao.RESET + " '+' " +
                        Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'c' " + Apresentacao.CYAN_BOLD +
                        "para adicionar artigo a uma encomenda e" + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD + "para sair" + Apresentacao.RESET);
                apresentacao.printEspacos(103);
                ler = new Scanner(System.in);
                opcao = ler.nextLine();
                if (opcao.equals("+") && paginaAtual < numPaginas) {
                    paginaAtual++;
                } else if (opcao.equals("-") && paginaAtual > 1) {
                    paginaAtual--;
                } else if (opcao.equals("c")) {
                    apresentacao.printClear(1);
                    apresentacao.printMensagemCentrada("INTRODUZA O ID DO ARTIGO QUE DESEJA ADICIONAR A UMA ENCOMENDA", 1);
                    apresentacao.printClear(2);
                    apresentacao.printEspacos(103);
                    String id = ler.nextLine().toUpperCase();
                    Apresentacao.clear();
                    apresentacao.printComprar();
                    try {
                        apresentacao.showArtigo(sistema.procuraArtigoVenda(id.toUpperCase()), sistema.getDataAtual().getYear());
                        apresentacao.printMensagemCentrada("DESEJA ADICIONAR ESTE ARTIGO A UMA ENCOMENDA?", 1);
                        apresentacao.printMensagemSimOuNao(101);
                    } catch (ArtigoException e)
                    {
                        apresentacao.printMensagemCentrada(e.getMessage(),2);
                        apresentacao.printEnter("");
                        ler.nextLine();
                        continue;
                    }
                    if (ler.nextLine().equals("1")) {
                        try {
                            sistema.adicionaArtigoEncomenda(id, email);
                            apresentacao.printClear(1);

                            apresentacao.printMensagemCentrada("ARTIGO ADICIONADO COM SUCESSO!", 3);
                            apresentacao.printEnter("");
                            ler.nextLine();
                        } catch (EncomendaException | UtilizadorException | ArtigoException a) {
                            apresentacao.printMensagemCentrada(a.getMessage(),2);
                            apresentacao.printEnterSair(90);
                            ler.nextLine();
                        }
                    }
                } else if (opcao.equals("s"))
                {
                    break;
                }
        } while (true);
        
        return 0;
    }

    public int runFaturas(String email) throws UtilizadorException, EncomendaException {
        String [] s = {"Faturas de compras", "Faturas de vendas", "Retroceder"};
        apresentacao.printMenu(s,5, 87,"", sistema.getDataAtual());
        int x = 0;
        Scanner ler = new Scanner(System.in);
        String eq;

        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printMenu(s, 5, 87, "", sistema.getDataAtual());
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (eq.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (eq.equals("3")) {
                            x = 3;
                            break;
                        }
                    } while (true);
                case 1:
                    runMenuFaturas(email, Atributos.VENDIDO);
                    x = 0;
                case 2:
                    runMenuFaturas(email, Atributos.VENDA);
                    x = 0;
            }
        } while (x != 3);

        return 0;
    }

    public void runMenuFaturas(String email, int tipoFatura) throws UtilizadorException, EncomendaException {
        Scanner ler = new Scanner(System.in);
        String opcao;
        int paginaAtual = 1;
        int quantidade = 5;
        List<Fatura> listaFaturas = sistema.procuraUtilizador(email).getListaFaturas().stream().filter(fatura -> fatura.getTipo() == tipoFatura).collect(Collectors.toList());
        do {
            if (listaFaturas.isEmpty())
            {
                apresentacao.printFatura();
                apresentacao.printMensagemCentrada("Não existem faturas relativa a" + (tipoFatura == 0 ? "Vendas" : "Compras"),3);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Fatura fatura : listaFaturas)
            {
                strings.add(apresentacao.showFatura(fatura));
            }
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            if (tipoFatura == Atributos.VENDA) apresentacao.printVendas();
            else apresentacao.printCompras();

            apresentacao.printMensagem("[Faturas de " + (tipoFatura == 0 ? "Vendas]" : "Compras]"),0,3);
            apresentacao.paginateMenu(strings,quantidade,paginaAtual,numPaginas,inicio,fim);
            System.out.println(Apresentacao.CYAN_BOLD + "                                                 Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'v' " + Apresentacao.CYAN_BOLD + "para ver os artigos de uma encomenda," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();
            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("v")) {
                apresentacao.printMensagemCentrada("INTRODUZA O ID DA ENCOMENDA QUE DESEJA VER OS ARTIGOS",1);
                apresentacao.printClear(1);
                apresentacao.printEspacos(102);
                int id = ler.nextInt();
                if (sistema.verificaEncomenda(email,id))
                {
                    runVerArtigosEncomenda(id,email,tipoFatura);
                }
                else
                {
                    apresentacao.printMensagem("ENCOMENDA NÃO ENCONTRADA",94,2);
                    apresentacao.printEnter("");
                    ler = new Scanner(System.in);
                    ler.nextLine();
                }
            } else if (opcao.equals("s")) {
                break;
            }
        }while (true);
    }

    public int runVendas(String email) throws UtilizadorException, TransportadoraException {
        String[] s = {"Minha lista de vendas", "Adicionar artigos a minha lista de vendas", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        int x = 0;
        String eq;

        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printMenu(s, 0, 87, "", sistema.getDataAtual());
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (eq.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (eq.equals("3")) {
                            x = 3;
                            break;
                        }
                    } while (true);

                case 1: //MINHA LISTA DE ARTIGOS A VENDA
                    runMenuUtilizadorArtigosVenda(email);
                    x = 0;
                case 2: //ADICIONAR ARTIGOS A MINHA LISTA DE VENDAS
                    x = runArtigosVender(email);

            }
        } while (x != 3);

        return 0;
    }

    public void runMenuUtilizadorArtigosVenda(String email) throws UtilizadorException, TransportadoraException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        int quantidade = 2;
        do {
            List<Artigo> lista = sistema.getArtigosVendaUtilizador(email).values().stream().collect(Collectors.toList());
            if (lista.isEmpty()) {
                apresentacao.printMinhaLista();
                apresentacao.printMensagem("NÃO POSSUI NENHUM ARTIGO À VENDA!!", 87, 1);
                apresentacao.printClear(1);
                apresentacao.printMensagem("DESEJA ADICONAR ALGUM ARTIGO À SUA LISTA DE VENDAS?", 79, 3);
                apresentacao.printClear(2);
                apresentacao.printMensagemSimOuNao(100);
                opcao = ler.nextLine();
                if (!opcao.equals("1") && !opcao.equals("0")){
                    apresentacao.printMensagemCentrada("INPUT INVÁLIDO!!",2);
                    apresentacao.printEnterSair(90);
                    ler.nextLine();
                    break;
                }
                if (opcao.equals("1")) {
                    runArtigosVender(email);
                }
                break;

            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : lista) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            do {
                int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
                apresentacao.printMinhaLista();
                apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
                apresentacao.printClear(2);
                System.out.println(Apresentacao.CYAN_BOLD + "                                                           Pressione" + Apresentacao.RESET + " '+' " +
                        Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'r' " + Apresentacao.CYAN_BOLD +
                        "para remover artigo e" + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD + "para sair" + Apresentacao.RESET);
                apresentacao.printEspacos(103);
                opcao = ler.nextLine();
                if (opcao.equals("+") && paginaAtual < numPaginas) {
                    paginaAtual++;
                } else if (opcao.equals("-") && paginaAtual > 1) {
                    paginaAtual--;
                } else if (opcao.equals("r"))
                {
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA O ID DO ARTIGO QUE DESEJA REMOVER",83,1);
                    apresentacao.printClear(1);
                    apresentacao.printEspacos(104);
                    String id = ler.nextLine().toUpperCase();
                    if (sistema.verificaArtigoUtilizador(email,id))
                    {
                        Apresentacao.clear();
                        apresentacao.printMinhaLista();
                        try {
                            apresentacao.showArtigo(sistema.procuraArtigoVenda(id),sistema.getDataAtual().getYear());
                            apresentacao.printClear(2);
                            apresentacao.printMensagem("DESEJA REMOVER ESTE ARTIGO?", 91,1);
                            apresentacao.printMensagemSimOuNao(101);
                            opcao = ler.nextLine();
                        } catch (ArtigoException e)
                        {
                            apresentacao.printMensagemCentrada(e.getMessage(),2);
                            apresentacao.printEnter("");
                            ler.nextLine();
                        }
                        if (opcao.equals("1"))
                        {
                            try {
                                sistema.removeArtigo(id);
                                apresentacao.printClear(1);
                                apresentacao.printMensagem("ARTIGO REMOVIDO COM SUCESSO!",90,3);
                                apresentacao.printEnter("");
                                ler.nextLine();
                                break;
                            } catch (UtilizadorException | ArtigoException a) {
                                apresentacao.printMensagemCentrada(a.getMessage(),2);
                                apresentacao.printEnterSair(90);
                                ler.nextLine();
                            }

                        }
                    }
                } else if (opcao.equals("s")) break;
            } while (true);
            break;
        } while (true);
    }

    public String runEscolhaTransportadora(int tipoTransportadora) {
        String opcao;
        Scanner ler = new Scanner(System.in);
        List<Transportadora> transportadoras = sistema.getListaTransportadoras().values().stream().filter(transportadora -> transportadora.getTipo() == tipoTransportadora).collect(Collectors.toList());
        List<String> strings = new ArrayList<>();
        int i = 1;
        for (Transportadora transportadora : transportadoras) {
            strings.add(apresentacao.showTransportadora(i, transportadora));
            i++;
        }
        int quantidade = 1;
        int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
        int paginaAtual = 1;
        do {
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printProcuraTrans();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(2);
            System.out.println(Apresentacao.CYAN_BOLD + "                                                                               Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar e" + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder ");
            apresentacao.printMensagemCentrada("Digite o nome da Transportadora para a selecionar!", 3);
            apresentacao.printClear(1);
            apresentacao.printEspacos(103);
            opcao = ler.nextLine().toUpperCase();
            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            }
            else try {
                if (sistema.verificaNomeTransportadora(opcao))
                {
                    if (sistema.procuraTransportadoraNome(opcao).getTipo() == tipoTransportadora) {
                        break;
                    }
                }
                } catch (TransportadoraException e)
                {
                    apresentacao.printMensagemCentrada("Comando Invalido ou " + e.getMessage(), 2);
                    apresentacao.printEnter("");
                    ler.nextLine();
                }
        }while (true);
        return opcao;
    }

    public int runArtigosVender(String email) throws TransportadoraException, UtilizadorException {
        Scanner ler = new Scanner(System.in);
        int opcao, nrDonos = 0, tamanho, padrao, tipo, tipoCordao, dataLancamento;
        double precoBase = 0, avaliacao = 0, dimensao;
        String id, descricao = "", marca = "", material, cor, nomeTransportadora, c;
        int artigo; //1 = Tshirt ; 2 = Mala ; 3 = Sapatilha
        int x;
        do {
            do {
                apresentacao.printRunArtigosVendaCase0();
                c = ler.nextLine();
                if (isInt(c)) {
                    if (stringToInt(c) <= 4 && stringToInt(c) > 0)
                    {
                        artigo = stringToInt(c);
                        break;
                    }
                }
            } while (true);
            if (artigo == 4) break;

            do { //Ver idArtigo, Descricao, marca, precoBase, estadoArtigo, nrDonos, Avaliacao.

                apresentacao.printAdicionaArtigoVenda();
                apresentacao.printMensagem("INTRODUZA O ID DO ARTIGO (CÓDIGO DE BARRAS)", 84, 1);
                ler = new Scanner(System.in);
                apresentacao.printEspacos(84);
                id = ler.nextLine();

                if (id.isEmpty()){
                    apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UM ID!!",2);
                    apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 87, 2);
                    apresentacao.printMensagemSimOuNao(87);
                    ler = new Scanner(System.in);

                    c = ler.nextLine();

                    if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1){
                        c = apresentacao.printInputIncorreto(0);
                    }
                    x = stringToInt(c);

                    if (x == 0){
                        artigo = 4;
                        break;
                    } else if (x == 1) {
                        continue;
                    }
                }

                apresentacao.printClear(1);
                apresentacao.printMensagem("INTRODUZA UMA DESCRIÇÃO", 84, 1);
                apresentacao.printEspacos(84);
                descricao = ler.nextLine();

                if (descricao.isEmpty()){
                    apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UMA DESCRIÇÃO!!",2);
                    apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 87, 2);
                    apresentacao.printMensagemSimOuNao(87);
                    ler = new Scanner(System.in);

                    c = ler.nextLine();

                    if (!isInt(c)|| stringToInt(c) < 0 || stringToInt(c) > 1){
                        c = apresentacao.printInputIncorreto(0);
                    }
                    x = stringToInt(c);

                    if (x == 0){
                        artigo = 4;
                        break;
                    } else if (x == 1) {
                        continue;
                    }
                }

                apresentacao.printClear(1);
                apresentacao.printMensagem("INTRODUZA A MARCA", 84, 1);
                apresentacao.printEspacos(84);

                marca = ler.nextLine();

                if (marca.isEmpty()){
                    apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UMA MARCA!!",2);
                    apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 87, 2);
                    apresentacao.printMensagemSimOuNao(87);
                    ler = new Scanner(System.in);

                    c = ler.nextLine();

                    if (!isInt(c)|| stringToInt(c) < 0 || stringToInt(c) > 1){
                        c = apresentacao.printInputIncorreto(0);
                    }
                    x = stringToInt(c);

                    if (x == 0){
                        artigo = 4;
                        break;
                    } else if (x == 1) {
                        continue;
                    }
                }

                apresentacao.printClear(1);
                apresentacao.printMensagem("INTRODUZA O PREÇO BASE", 84, 1);
                apresentacao.printEspacos(84);

                c = ler.nextLine();

                if (notDouble(c)) {
                    apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS!!", 2);
                    apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                    apresentacao.printMensagemSimOuNao(87);
                    ler = new Scanner(System.in);

                    c = ler.nextLine();

                    if (!isInt(c)|| stringToInt(c) < 0 || stringToInt(c) > 1){
                        c = apresentacao.printInputIncorreto(0);
                    }
                    x = stringToInt(c);

                    if (x == 0) {
                        artigo = 4;
                        break;
                    } else if (x == 1) {
                        continue;
                    }
                }

                precoBase = stringToDouble(c);

                apresentacao.printEstadoArtigo();
                c = ler.nextLine();

                if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                    apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS OS DIGITOS 0 E 1s!!", 2);
                    apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                    apresentacao.printMensagemSimOuNao(87);
                    ler = new Scanner(System.in);

                    c = ler.nextLine();

                    if (!isInt(c)|| stringToInt(c) < 0 || stringToInt(c) > 1){
                        c = apresentacao.printInputIncorreto(0);
                    }

                    x = stringToInt(c);

                    if (x == 0) {
                        artigo = 4;
                        break;
                    } else if (x == 1) {
                        continue;
                    }
                }

                opcao = stringToInt(c);

                if (opcao == 0) {
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA A SUA AVALIAÇÃO", 84, 1);
                    apresentacao.printEspacos(84);

                    c = ler.nextLine();

                    if (notDouble(c)) {
                        apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                        apresentacao.printMensagemSimOuNao(87);
                        ler = new Scanner(System.in);

                        c = ler.nextLine();

                        if (!isInt(c)|| stringToInt(c) < 0 || stringToInt(c) > 1){
                            c = apresentacao.printInputIncorreto(0);
                        }

                        x = stringToInt(c);

                        if (x == 0) {
                            break;
                        } else if (x == 1) {
                            continue;
                        }
                    }

                    avaliacao = stringToDouble(c);

                    apresentacao.printClear(1);
                    apresentacao.printMensagem("INTRODUZA O NÚMERO DE DONOS QUE JÁ TEVE", 84, 1);
                    apresentacao.printEspacos(84);

                    c = ler.nextLine();

                    if (!isInt(c)) {
                        apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                        apresentacao.printMensagemSimOuNao(87);
                        ler = new Scanner(System.in);

                        c = ler.nextLine();

                        if (!isInt(c)|| stringToInt(c) < 0 || stringToInt(c) > 1){
                            c = apresentacao.printInputIncorreto(0);
                        }

                        x = stringToInt(c);

                        if (x == 0) {
                            artigo = 4;
                            break;
                        } else if (x == 1) {
                            continue;
                        }
                    }
                    nrDonos = stringToInt(c);
                    break;
                }
                break;
            }   while (true);
            if (artigo == 4) break;

            do { //Ver cada parametro especifico, escolher transportadora e adicionar Artigo
                switch (artigo) {
                    case 1:
                        apresentacao.printAdicionaArtigoVenda();
                        apresentacao.printClear(1);
                        apresentacao.printTamanhosTshirt();
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 3) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS DE 0 A 3!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        tamanho = stringToInt(c);
                        apresentacao.printClear(1);
                        apresentacao.printPadroesTshirt();
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 2) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS DE 0 A 2!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        padrao = stringToInt(c);
                        nomeTransportadora = runEscolhaTransportadora(0);
                        Tshirt tshirt = new Tshirt(id, email, descricao, marca, precoBase, nrDonos, avaliacao, sistema.procuraTransportadoraNome(nomeTransportadora), 0, tamanho, padrao);
                        Apresentacao.clear();
                        apresentacao.printClear(5);
                        apresentacao.showArtigo(tshirt, sistema.getDataAtual().getYear());
                        apresentacao.printClear(3);
                        apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?", 81, 1);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(99);
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS OU DIGITOS 0 OU 1!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                            break;
                        }
                        x = stringToInt(c);
                        if (x == 1) {
                            try {
                                sistema.adicionaTshirtVenda(id, email, descricao, marca, precoBase, avaliacao, nrDonos, nomeTransportadora, tamanho, padrao);
                                apresentacao.printMensagemCentrada("ARTIGO ADICIONADO COM SUCESSO!!", 3);
                                apresentacao.printEnter("");
                                ler.nextLine();
                            } catch (ArtigoException e) {
                                apresentacao.printAdicionaArtigoVenda();
                                apresentacao.printMensagemCentrada(e.getMessage(), 2);
                                apresentacao.printEnter("");
                                ler.nextLine();
                            }
                        }
                    case 2:
                        apresentacao.printAdicionaArtigoVenda();
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA A SUA DIMENSÃO", 84, 1);
                        apresentacao.printEspacos(84);
                        c = ler.nextLine();
                        if (notDouble(c)) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        dimensao = stringToDouble(c);
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA O MATERIAL", 84, 1);
                        apresentacao.printEspacos(84);
                        material = ler.nextLine();
                        if (material.isEmpty()) {
                            apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UM MATERIAL!!", 2);
                            apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 87, 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }
                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA O SEU ANO DE LANÇAMENTO (EX: 2015)", 84, 1);
                        apresentacao.printEspacos(84);
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 1900 || stringToInt(c) >= sistema.getDataAtual().getYear()) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS ENTRE 1900 E O ANO ATUAL!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        dataLancamento = stringToInt(c);
                        apresentacao.printClear(1);
                        apresentacao.printArtigoPremiumOuNormal();
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS OS DIGITOS 0 OU 1!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        tipo = stringToInt(c);
                        nomeTransportadora = runEscolhaTransportadora(tipo);
                        Mala mala = new Mala(id, email, descricao, marca, precoBase, nrDonos, avaliacao, sistema.procuraTransportadoraNome(nomeTransportadora), 0, dimensao, material, dataLancamento, tipo);
                        Apresentacao.clear();
                        apresentacao.showArtigo(mala, sistema.getDataAtual().getYear());
                        apresentacao.printClear(3);
                        apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?", 81, 1);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(99);
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS OS DIGITOS 0 OU 1!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                            break;
                        }
                        x = stringToInt(c);
                        if (x == 1) {
                            try {
                                sistema.adicionaMalaVenda(id, email, descricao, marca, precoBase, avaliacao, nrDonos, nomeTransportadora, dimensao, material, dataLancamento, tipo);
                                apresentacao.printMensagemCentrada("ARTIGO ADICIONADO COM SUCESSO!!", 3);
                                apresentacao.printEnter("");
                                ler.nextLine();
                            } catch (ArtigoException e) {
                                apresentacao.printMensagemCentrada(e.getMessage(), 2);
                                apresentacao.printEnter("");
                                ler.nextLine();
                            }
                        }
                    case 3:
                        apresentacao.printAdicionaArtigoVenda();
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA O SEU TAMANHO (Nº DE CALÇADO )", 84, 1);
                        apresentacao.printEspacos(84);
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 1 && stringToInt(c) > 60) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS ENTRE 1 E 60!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }
                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        tamanho = stringToInt(c);
                        apresentacao.printClear(1);
                        apresentacao.printTipoCordao();
                        c = ler.nextLine();
                        if (!isInt(c)) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS OS DIGITOS 0 OU 1!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }
                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        tipoCordao = stringToInt(c);
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA A SUA COR", 84, 1);
                        apresentacao.printEspacos(84);
                        cor = ler.nextLine();
                        if (cor.isEmpty()) {
                            apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UMA COR!!", 2);
                            apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 87, 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }
                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        apresentacao.printClear(1);
                        apresentacao.printMensagem("INTRODUZA O SEU ANO DE LANÇAMENTO (EX: 2015)", 84, 1);
                        apresentacao.printEspacos(84);
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 1900 || stringToInt(c) >= sistema.getDataAtual().getYear()) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS ENTRE 1900 E O ANO ATUAL!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        dataLancamento = stringToInt(c);
                        apresentacao.printClear(1);
                        apresentacao.printArtigoPremiumOuNormal();
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS OS DIGITOS 0 OU 1!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                        }
                        tipo = stringToInt(c);
                        nomeTransportadora = runEscolhaTransportadora(tipo);
                        Sapatilha sapatilha = new Sapatilha(id, email, descricao, marca, precoBase, nrDonos, avaliacao, sistema.procuraTransportadoraNome(nomeTransportadora), 0, tamanho, tipoCordao, c, dataLancamento, tipo);
                        Apresentacao.clear();
                        apresentacao.showArtigo(sapatilha, sistema.getDataAtual().getYear());
                        apresentacao.printClear(3);
                        apresentacao.printMensagem("TEM A CERTEZA QUE DESEJA ADICIONAR ESTE ARTIGO?", 81, 1);
                        apresentacao.printClear(1);
                        apresentacao.printMensagemSimOuNao(99);
                        c = ler.nextLine();
                        if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                            apresentacao.printMensagemCentrada("ERR0! SO PODEM SER UTILIZADOS DIGITOS 0 OU 1!!", 2);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                            apresentacao.printMensagemSimOuNao(87);
                            ler = new Scanner(System.in);

                            c = ler.nextLine();

                            if (!isInt(c) || stringToInt(c) < 0 || stringToInt(c) > 1) {
                                c = apresentacao.printInputIncorreto(0);
                            }

                            x = stringToInt(c);

                            if (x == 0) {
                                break;
                            } else if (x == 1) {
                                continue;
                            }
                            break;
                        }
                        x = stringToInt(c);
                        if (x == 1) {
                            try {
                                sistema.adicionaSapatilhaVenda(id, email, descricao, marca, precoBase, avaliacao, nrDonos, nomeTransportadora, tamanho, tipoCordao, c, dataLancamento, tipo);
                                apresentacao.printMensagemCentrada("ARTIGO ADICIONADO COM SUCESSO!!", 3);
                                apresentacao.printEnter("");
                                ler.nextLine();
                            } catch (ArtigoException e) {
                                apresentacao.printMensagemCentrada(e.getMessage(), 2);
                                apresentacao.printEnter("");
                                ler.nextLine();
                            }
                        }
                }
                break;
            } while (true);
            break;
            } while (true);
        return 0;
    }

    public int runMenuImpostos()
    {
        int imposto, flag = 0;
        double nTaxa;
        String escolha, c, taxa;
        String [] opcoes = {"Alterar taxa de Imposto", "Alterar taxa encomendas pequenas","Alterar taxa encomendas médias","Alterar taxa encomendas grandes","Retroceder"};
        Scanner ler = new Scanner(System.in);

        do {
            switch (flag) {
                case 0:
                    do {
                        apresentacao.printTax();
                        apresentacao.printEspacos(60);
                        System.out.println(Apresentacao.CYAN_BOLD + "Taxa de imposto: " + Apresentacao.RESET + sistema.getTaxas().getImposto() +
                                Apresentacao.CYAN_BOLD + " | Taxa enc. Peq.: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncPequena() +
                                Apresentacao.CYAN_BOLD + " | Taxa enc. Med.: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncMedia() +
                                Apresentacao.CYAN_BOLD + " | Taxa enc. Grd.: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncGrande());
                        apresentacao.printMenu(opcoes, 100, 87, "", sistema.getDataAtual());
                        escolha = ler.nextLine().toLowerCase();
                        if (escolha.equals("1")) {
                            flag = 1;
                            break;
                        }
                        if (escolha.equals("2")) {
                            flag = 2;
                            break;
                        }
                        if (escolha.equals("3")) {
                            flag = 3;
                            break;
                        }
                        if (escolha.equals("4")) {
                            flag = 4;
                            break;
                        }
                        if (escolha.equals("5")) {
                            flag = 5;
                            break;
                        }
                        if (escolha.equals("6")) {
                            flag = 6;
                            break;
                        }
                    } while (true);
                case 1: //Alterar imposto
                    apresentacao.printTax();
                    apresentacao.printEspacos(86);
                    System.out.println(Apresentacao.CYAN_BOLD + "Taxa de imposto atual: " + Apresentacao.RESET + sistema.getTaxas().getImposto());
                    System.out.println();
                    apresentacao.printMensagem("Defina a taxa de imposto", 86, 1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    escolha = ler.nextLine();
                    if (!isInt(escolha)) {
                        do {
                            apresentacao.printTax();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                flag = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    imposto = stringToInt(escolha);
                    if (imposto <= 0) {
                        do {
                            apresentacao.printTax();
                            apresentacao.printMensagemErro(3);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                flag = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    sistema.getTaxas().setImposto(imposto);
                    flag = 6;
                case 2:
                    apresentacao.printTax();
                    apresentacao.printEspacos(86);
                    System.out.println(Apresentacao.CYAN_BOLD + "Taxa atual: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncPequena());
                    System.out.println();
                    apresentacao.printMensagem("Defina a Taxa de uma Encomenda Pequena", 86, 1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    taxa = ler.nextLine();
                    if (notDouble(taxa)) {
                        apresentacao.printMensagemCentrada("ERR0! SÓ PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printClear(1);
                        flag = valorSimouNao(4, 2, 0);
                        break;
                    }
                    nTaxa = stringToDouble(taxa);
                    if (nTaxa <= 0) {
                        apresentacao.printMensagemCentrada("ERR0! APENAS VALORES POSITIVOS!!", 2);
                        apresentacao.printClear(1);
                        flag = valorSimouNao(4, 2, 0);
                        break;
                    }
                    sistema.getTaxas().setTaxaEncPequena(nTaxa);
                    flag = 6;
                case 3:
                    apresentacao.printTax();
                    apresentacao.printEspacos(86);
                    System.out.println(Apresentacao.CYAN_BOLD + "Taxa atual: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncMedia());
                    System.out.println();
                    apresentacao.printMensagem("Defina a Taxa de uma Encomenda Média", 86, 1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    taxa = ler.nextLine();
                    if (notDouble(taxa)) {
                        apresentacao.printMensagemCentrada("ERR0! SÓ PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printClear(1);
                        flag = valorSimouNao(4, 3, 0);
                        break;
                    }
                    nTaxa = stringToDouble(taxa);
                    if (nTaxa <= 0) {
                        apresentacao.printMensagemCentrada("ERR0! APENAS VALORES POSITIVOS!!", 2);
                        apresentacao.printClear(1);
                        flag = valorSimouNao(4, 3, 0);
                        break;
                    }
                    sistema.getTaxas().setTaxaEncMedia(nTaxa);
                    flag = 6;
                case 4:
                    apresentacao.printTax();
                    apresentacao.printEspacos(86);
                    System.out.println(Apresentacao.CYAN_BOLD + "Taxa atual: " + Apresentacao.RESET + sistema.getTaxas().getTaxaEncGrande());
                    System.out.println();
                    apresentacao.printMensagem("Defina a Taxa de uma Encomenda Grande", 86, 1);
                    System.out.println();
                    apresentacao.printEspacos(86);
                    ler = new Scanner(System.in);
                    taxa = ler.nextLine();
                    if (notDouble(taxa)) {
                        apresentacao.printMensagemCentrada("ERR0! SÓ PODEM SER UTILIZADOS DIGITOS!!", 2);
                        apresentacao.printClear(1);
                        flag = valorSimouNao(4, 4, 0);
                        break;
                    }
                    nTaxa = stringToDouble(taxa);
                    if (nTaxa <= 0) {
                        apresentacao.printMensagemCentrada("ERR0! APENAS VALORES POSITIVOS!!", 2);
                        apresentacao.printClear(1);
                        flag = valorSimouNao(4, 4, 0);
                        break;
                    }
                    sistema.getTaxas().setTaxaEncGrande(nTaxa);
                    flag = 6;

                case 6:
                    apresentacao.printTax();
                    apresentacao.printMensagem("ALTERACAO REALIZADA COM SUCESSO!!", 87, 3);
                    apresentacao.printEnterSair(90);
                    ler.nextLine();
                    flag = 0;
            }
        } while (flag != 5);
        return 0;
    }

    public int runConfig() {
        int x = 0;
        String [] s = {"Avancar no tempo", "Alterar taxas e impostos", "Alterar tempo de devolucao", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        String c, eq;

        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printMenu(s, 2, 87, "", sistema.getDataAtual());
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (eq.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (eq.equals("3")) {
                            x = 3;
                            break;
                        }
                        if (eq.equals("4")) {
                            x = 4;
                            break;
                        }
                    } while (true);
                case 1: // MENU AVANCAR NO TEMPO
                        x = runAvancaTempo();
                case 2: // MENU ALTERAR IMPOSTO E TAXAS
                        x = runMenuImpostos();
                case 3:
                    apresentacao.printReturnTime();
                    apresentacao.printEspacos(82);
                    System.out.println(Apresentacao.CYAN_BOLD + "Limite de dias para devolver uma encomenda: " + Apresentacao.RESET + sistema.getTempoDevolucao());
                    apresentacao.printClear(1);
                    apresentacao.printMensagem("Defina o limite de dias para a devolucao de uma encomenda", 77, 1);
                    System.out.println();
                    apresentacao.printEspacos(77);
                    ler = new Scanner(System.in);
                    c = ler.nextLine();
                    if (!isInt(c)) {
                        do {
                            apresentacao.printReturnTime();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    int rt = stringToInt(c);
                    if (rt <= 0) {
                        do {
                            apresentacao.printReturnTime();
                            apresentacao.printMensagemErro(3);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    sistema.setTempoDevolucao(rt);
                    apresentacao.printReturnTime();
                    apresentacao.printMensagem("ALTERACAO REALIZADA COM SUCESSO!!", 87, 3);
                    apresentacao.printClear(1);
                    apresentacao.printEspacos(82);
                    System.out.print(Apresentacao.CYAN_BOLD + "Limite de dias para devolver uma encomenda: " + Apresentacao.RESET + rt);
                    apresentacao.printEnterSair(90);
                    ler.nextLine();
                    x = 0;
            }
        } while (x != 4);
        return 0;
    }

    public int runAvancaTempo() {
        int x = 0, nDia, nMes, nAno;
        String[] s ={"Avançar para uma data", "Adicionar dias à data atual", "Retroceder"};
        String c, dia, mes, ano;
        Scanner ler;
        LocalDate localDate;

        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printSaltaTempo();
                        apresentacao.printOpcoes("Selecione o metodo para avançar no tempo:", s, 87);
                        apresentacao.printEspacos(103);
                        ler = new Scanner(System.in);
                        c = ler.nextLine();

                        if (c.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (c.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (c.equals("3")) {
                            x = 3;
                            break;
                        }

                    } while (true);
                case 1:
                    apresentacao.printSaltaTempo();
                    apresentacao.printMensagem("Introduza o dia (DD)", 87, 1);
                    System.out.println();
                    apresentacao.printEspacos(87);
                    ler = new Scanner(System.in);
                    dia = ler.nextLine();
                    if (!isInt(dia)) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    nDia = stringToInt(dia);
                    if (nDia < 1 || nDia > 31) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemCentrada("DIA INVALIDO!!", 2);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR DE NOVO?", 0);
                            apresentacao.printMensagemSimOuNao(101);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    System.out.println();
                    apresentacao.printMensagem("Introduza o mês (MM)", 87, 1);
                    System.out.println();
                    apresentacao.printEspacos(87);
                    ler = new Scanner(System.in);
                    mes = ler.nextLine();
                    if (!isInt(mes)) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    nMes = stringToInt(mes);
                    if (nMes < 1 || nMes > 12) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemCentrada("MES INVALIDO!!", 2);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR DE NOVO?", 0);
                            apresentacao.printMensagemSimOuNao(101);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    System.out.println();
                    apresentacao.printMensagem("Introduza o ano (AAAA)", 87, 1);
                    System.out.println();
                    apresentacao.printEspacos(87);
                    ler = new Scanner(System.in);
                    ano = ler.nextLine();
                    if (!isInt(ano)) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    nAno = stringToInt(ano);
                    try {
                        sistema.saltaTempo(nAno, nMes, nDia);
                        localDate = this.sistema.getDataAtual();
                        System.out.println();
                        apresentacao.printEnterSair(90);

                        apresentacao.printSaltaTempo();
                        apresentacao.printMensagem("SALTO NO TEMPO REALIZADO COM SUCESSO!!", 84, 3);
                        System.out.println();
                        apresentacao.printMensagemLocaldate("Data atual do sistema: ", 86, 1, localDate);
                        apresentacao.printEnterSair(90);
                        ler.nextLine();
                        x = 0;
                    } catch (SistemaException e) {
                        apresentacao.printClear(3);
                        apresentacao.printMensagemCentrada(e.getMessage(), 2);
                        apresentacao.printEnter("");
                        ler.nextLine();
                        x = 0;
                    }
                case 2:
                    apresentacao.printSaltaTempo();
                    apresentacao.printMensagem("Introduza o numero de dias que pretende avançar (DD)", 78, 1);
                    System.out.println();
                    apresentacao.printEspacos(78);
                    ler = new Scanner(System.in);
                    dia = ler.nextLine();
                    if (!isInt(dia)) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemErro(1);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    nDia = stringToInt(dia);
                    if (nDia < 0) {
                        do {
                            apresentacao.printSaltaTempo();
                            apresentacao.printMensagemCentrada("IMPOSSIVEL VOLTAR ATRAS NO TEMPO!!", 2);
                            apresentacao.printClear(1);
                            apresentacao.printMensagemCentrada("DESEJA TENTAR DE NOVO?", 0);
                            apresentacao.printMensagemSimOuNao(101);
                            ler = new Scanner(System.in);
                            c = ler.nextLine();
                            if (c.equals("1")) {
                                break;
                            }
                            if (c.equals("0")) {
                                x = 0;
                                break;
                            }
                        } while (true);
                        break;
                    }
                    sistema.saltaTempo(nDia);
                    localDate = this.sistema.getDataAtual();
                    apresentacao.printSaltaTempo();
                    apresentacao.printMensagem("SALTO NO TEMPO REALIZADO COM SUCESSO!!", 84, 3);
                    System.out.println();
                    apresentacao.printMensagemLocaldate("Data atual do sistema: ", 86, 1, localDate);
                    apresentacao.printEnterSair(90);
                    ler.nextLine();
                    x = 0;
            }
        } while (x!=3);

        return 0;
    }

    public int runEncomendas(String email) throws ArtigoException, UtilizadorException, EncomendaException, TransportadoraException {
        int x = 0;
        String [] s = {"Pendentes", "Expedidas", "Finalizadas", "Devolvidas", "Retroceder"};
        Scanner ler = new Scanner(System.in);
        String eq;

        do {
            switch (x) {
                case 0: // MENU ENCOMENDAS

                    do {
                        apresentacao.printMenu(s, 3, 87, "", sistema.getDataAtual());
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (eq.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (eq.equals("3")) {
                            x = 3;
                            break;
                        }
                        if (eq.equals("4")) {
                            x = 4;
                            break;
                        }
                        if (eq.equals("5")) {
                            x = 5;
                            break;
                        }
                    } while (true);
                case 1: // MENU PENDENTES
                    runListarEncomendas(email, Atributos.PENDENTE);
                    x = 0;
                case 2:  //MENU EXPEDIDAS
                    runListarEncomendas(email, Atributos.EXPEDIDA);
                    x = 0;
                case 3: //MENU FINALIZADAS
                    runListarEncomendas(email, Atributos.FINALIZADA);
                    x = 0;
                case 4:
                    runListarEncomendas(email, Atributos.DEVOLVIDA);
                    x = 0;
            }
        } while (x != 5);

        return 0;
    }

    public void runEncomendaPendente(String email, int id) throws EncomendaException, UtilizadorException, TransportadoraException, ArtigoException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<String> strings = new ArrayList<>();
            try {
                List<Artigo> artigos = sistema.procuraEncomendaComprador(id, email).getListaArtigos();
                if (artigos.isEmpty()) {
                    apresentacao.printBox();
                    apresentacao.printMensagem("Esta encomenda não possui artigos!", 87, 2);
                    apresentacao.printEnter("");
                    ler.nextLine();
                    break;
                }
                for (Artigo artigo : artigos) {
                    strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
                }
            } catch (EncomendaException e)
            {
                return;
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printPendentes();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "                    Vendedor: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).getVendedor() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor dos artigos: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).calculaValorArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxas Artigos: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).calculaTaxaArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxa Expedição: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).calculaValorExpedicao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Preço Total: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).getPrecoFinal() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data de Criação: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).getDataCriacao().toString());
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "                             Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'c' " + Apresentacao.CYAN_BOLD +
                    "para confirmar encomenda," + Apresentacao.RESET + " 'r' " + Apresentacao.CYAN_BOLD + "remover um artigo da encomenda," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();

            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("c")) {
                try {
                    sistema.confirmaEncomenda(id, email);
                    apresentacao.printMensagem("ENCOMENDA CONFIRMADA COM SUCESSO!!", 88, 3);
                    apresentacao.printEnter("");
                    ler.nextLine();
                    break;
                } catch (EncomendaException e)
                {
                    apresentacao.printMensagemCentrada(e.getMessage(),2);
                    apresentacao.printEnter("");
                    ler.nextLine();
                    break;
                }
            } else if (opcao.equals("r")) {
                apresentacao.printMensagem("INTRODUZA O ID DO ARTIGO QUE DESEJA REMOVER", 82, 2);
                apresentacao.printEspacos(103);
                String idArtigo = ler.nextLine().toUpperCase();
                try {
                    sistema.removeArtigoEncomenda(idArtigo, email);
                    apresentacao.printClear(2);
                    apresentacao.printMensagem("ARTIGO REMOVIDO COM SUCESSO!!", 89, 3);
                    apresentacao.printEnter("");
                    ler.nextLine();
                } catch (ArtigoException e)
                {
                    apresentacao.printMensagemCentrada(e.getMessage(),2);
                    apresentacao.printEnter("");
                    ler.nextLine();
                }
            } else if (opcao.equals("s")) {
                break;
            }
        } while (true);
    }

    public void runEncomendaExpedida(String email, int id) throws EncomendaException, UtilizadorException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<Artigo> artigos = sistema.procuraEncomendaComprador(id, email).getListaArtigos();
            if (artigos.isEmpty()) {
                apresentacao.printBox(); //90
                apresentacao.printMensagem("Esta encomenda não possui artigos!", 87, 2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : artigos) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printExpedidas();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "                    Vendedor: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).getVendedor() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor dos artigos: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).calculaValorArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxas Artigos: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).calculaTaxaArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxa Expedição: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).calculaValorExpedicao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Preço Total: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).getPrecoFinal() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data Prevista de Entrega: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).getDataPrevistaEntrega());
            apresentacao.printClear(1);
            apresentacao.printEspacos(74);
            System.out.println(Apresentacao.CYAN_BOLD + "Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();

            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            }  else if (opcao.equals("s")) {
                break;
            }
        } while (true);
    }

    public void runEncomendaFinalizada(String email, int id) throws EncomendaException, UtilizadorException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<Artigo> artigos = sistema.procuraEncomendaComprador(id, email).getListaArtigos();
            if (artigos.isEmpty()) {
                apresentacao.printBox(); //90
                apresentacao.printMensagem("Esta encomenda não possui artigos!", 87, 2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : artigos) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printFinalizadas();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "   Vendedor: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).getVendedor() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor dos artigos: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).calculaValorArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxas Artigos: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).calculaTaxaArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxa Expedição: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).calculaValorExpedicao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Preço Total: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).getPrecoFinal() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data de Entrega: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).getDataAtualizacao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data limite de Devolução: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).getDataAtualizacao().plusDays(sistema.getTempoDevolucao()) + Apresentacao.YELLOW);
            apresentacao.printClear(1);
            System.out.println(Apresentacao.CYAN_BOLD + "                                                        Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'd' " + Apresentacao.CYAN_BOLD + "para devolver a encomenda" + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();

            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("d")) {
                try {
                    sistema.devolveEncomenda(email, id);
                    apresentacao.printMensagem("ENCOMENDA DEVOLVIDA COM SUCESSO!!", 88, 3);
                    apresentacao.printEnter("");
                    ler.nextLine();
                    break;
                }
                catch (EncomendaException e) {
                    apresentacao.printMensagem(e.getMessage(),85,2);
                    apresentacao.printEnter("");
                    ler.nextLine();
                }
            } else if (opcao.equals("s")) {
                break;
            }
        } while (true);
    }

    public void runEncomendaDevolvidas(String email, int id) throws EncomendaException, UtilizadorException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<Artigo> artigos = sistema.procuraEncomendaComprador(id, email).getListaArtigos();
            if (artigos.isEmpty()) {
                apresentacao.printBox(); //90
                apresentacao.printMensagem("Esta encomenda não possui artigos!", 87, 2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Artigo artigo : artigos) {
                strings.add(apresentacao.showArtigoString(artigo, sistema.getDataAtual().getYear()));
            }
            int quantidade = 2;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printDevolvidas();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            apresentacao.printEspacos(15);
            System.out.println(Apresentacao.CYAN_BOLD + "Vendedor: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).getVendedor() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor dos artigos: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).calculaValorArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxas Artigos: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).calculaTaxaArtigos() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Valor Taxa Expedição: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).calculaValorExpedicao() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Preço Total: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id,email).getPrecoFinal() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data de Devolução: " + Apresentacao.RESET + sistema.procuraEncomendaComprador(id, email).getDataAtualizacao().toString());
            apresentacao.printEspacos(74);
            System.out.println(Apresentacao.CYAN_BOLD + "Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();
            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("s")) {
                break;
            }
        } while (true);
    }

    public void runListarEncomendas(String email, int estado) throws UtilizadorException, EncomendaException, ArtigoException, TransportadoraException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        do {
            List<Encomenda> encomendas = sistema.procuraUtilizador(email).getListaEncomendas(estado).stream().collect(Collectors.toList());
            if (encomendas.isEmpty())
            {
                apresentacao.printClear(2);
                apresentacao.printBox();
                apresentacao.printClear(3);
                apresentacao.printMensagem("NÃO EXISTEM ENCOMENDAS " + apresentacao.estadoEncomendaString(estado), 88, 3);
                apresentacao.printClear(2);
                apresentacao.printEnter("");
                ler.nextLine();
                break;
            }
            List<String> strings = new ArrayList<>();
            for (Encomenda encomenda: encomendas)
            {
                strings.add(apresentacao.showEncomenda(encomenda,sistema.getTempoDevolucao()));
            }
            int quantidade = 5;
            int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());

                apresentacao.printEncomendas();
                System.out.println(Apresentacao.RED + "[ENCOMENDAS " + apresentacao.estadoEncomendaString(estado) + "]\n" + Apresentacao.RESET);
                apresentacao.paginateMenu(strings,quantidade,paginaAtual,numPaginas,inicio,fim);
                apresentacao.printClear(1);
                System.out.println(Apresentacao.CYAN_BOLD + "                                                        Pressione" + Apresentacao.RESET + " '+' " +
                        Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 'v' " + Apresentacao.CYAN_BOLD +
                        "para ver/editar uma encomenda,"  + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                        "para sair" + Apresentacao.RESET);
                apresentacao.printClear(1);
                apresentacao.printEspacos(105);
                opcao = ler.nextLine().toLowerCase();
                if (opcao.equals("+") && paginaAtual < numPaginas) {
                    paginaAtual++;
                } else if (opcao.equals("-") && paginaAtual > 1) {
                    paginaAtual--;
                } else if (opcao.equals("v"))
                {
                    apresentacao.printMensagem("INTRODUZA O ID DA ENCOMENDA QUE DESEJA VER/EDITAR",81, 3);
                    apresentacao.printClear(1);
                    apresentacao.printEspacos(105);
                    int idEncomenda = ler.nextInt();
                    if (sistema.verificaEstadoEncomenda(email,idEncomenda,estado))
                    {
                        if (estado == Atributos.PENDENTE) runEncomendaPendente(email, idEncomenda);
                        if (estado == Atributos.EXPEDIDA) runEncomendaExpedida(email, idEncomenda);
                        if (estado == Atributos.FINALIZADA) runEncomendaFinalizada(email, idEncomenda);
                        if (estado == Atributos.DEVOLVIDA) runEncomendaDevolvidas(email, idEncomenda);
                    }
                    else
                    {
                        apresentacao.printMensagem("ENCOMENDA NÃO ENCONTRADA",94,2);
                        apresentacao.printEnter("");
                        ler = new Scanner(System.in);
                        ler.nextLine();
                    }
                } else if (opcao.equals("s"))
                {
                    break;
                }
        }while (true);
    }

    public void runVerArtigosEncomenda(int id, String email, int tipoEncomenda) throws EncomendaException, UtilizadorException {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        List<String> strings = new ArrayList<>();
        Encomenda encomenda;
        if (tipoEncomenda == Atributos.VENDA)
        {
            encomenda = sistema.procuraEncomendaVendedor(id,email);
        }
        else
        {
            encomenda = sistema.procuraEncomendaComprador(id,email);
        }
        List<Artigo> artigos = encomenda.getListaArtigos();
        for (Artigo artigo : artigos)
        {
            strings.add(apresentacao.showArtigoString(artigo,encomenda.getDataCriacao().getYear()));
        }
        int quantidade = 2;
        int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
        do {
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printEncomendas();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            apresentacao.printEspacos(74);
            System.out.println(Apresentacao.CYAN_BOLD + "Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();
            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("s")) {
                break;
            }
        } while (true);
    }

    public void runMenuVendedorMaisFaturou()
    {
        int x = 0;
        String opcao;
        Scanner ler = new Scanner(System.in);
        LocalDate data1, data2;
        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printVendedorDinheiro();
                        String[] o = {"DE SEMPRE", "PERÍODO DE TEMPO", "RETROCEDER"};
                        apresentacao.printOpcoes("INDIQUE A OPÇÃO QUE PRETENDE", o, 95);
                        apresentacao.printEspacos(101);
                        ler = new Scanner(System.in);

                        opcao = ler.nextLine().toLowerCase();
                        if (opcao.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (opcao.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (opcao.equals("3")) {
                            x = 3;
                            break;
                        }
                    } while (true);
                case 1: //Desde Sempre
                    apresentacao.printVendedorDinheiro();
                    System.out.println();
                    try {
                        Utilizador utilizador = sistema.vendedorMaisFaturouSempre();
                        System.out.print(utilizador.toString());
                        apresentacao.printFaturacao("Faturou: ", 90, 1, utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum());
                        apresentacao.printEnterSair(90);
                        ler.nextLine();
                        x = 0;
                    } catch (SistemaException a) {
                        apresentacao.printMensagem(a.getMessage(), 86, 2);
                        apresentacao.printEnterSair(90);
                        ler.nextLine();
                        x = 0;
                    }
                case 2:
                    apresentacao.printVendedorDinheiro();
                    System.out.println();
                    ler = new Scanner(System.in);
                    apresentacao.printMensagem("INSIRA A DATA INICIAL (AAAA-MM-DD)", 87, 1);
                    apresentacao.printEspacos(87);
                    String d1 = ler.nextLine();
                    try {
                        data1 = stringParaData(d1);

                    } catch (DateTimeException a) {
                        apresentacao.printMensagem(a.getMessage(), 97, 2);
                        apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 91, 2);
                        apresentacao.printMensagemSimOuNao(99);
                        ler = new Scanner(System.in);

                        opcao = ler.nextLine();

                        if (!isInt(opcao)) {
                            opcao = apresentacao.printInputIncorreto(7);
                        }

                        x = stringToInt(opcao);

                        if (x == 1) {
                            x = 2;
                            break;
                        }
                        break;
                    }
                    System.out.println();
                    apresentacao.printMensagem("INSIRA A DATA FINAL (AAAA-MM-DD)", 87, 1);
                    apresentacao.printEspacos(87);
                    String d2 = ler.nextLine();
                    try {
                        data2 = stringParaData(d2);
                    } catch (DateTimeException a) {
                        apresentacao.printMensagem(a.getMessage(), 97, 2);
                        apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 91, 2);
                        apresentacao.printMensagemSimOuNao(99);
                        ler = new Scanner(System.in);

                        opcao = ler.nextLine();

                        if (!isInt(opcao)) {
                            opcao = apresentacao.printInputIncorreto(7);
                        }

                        x = stringToInt(opcao);

                        if (x == 1) {
                            x = 2;
                            break;
                        }
                        break;
                    }
                    try {
                        Utilizador utilizador = sistema.vendedorMaisFaturouEntreDatas(data1, data2);
                        apresentacao.printVendedorDinheiro();
                        System.out.println();
                        System.out.print(utilizador.toString());
                        apresentacao.printFaturacao("Faturou: ", 99, 1, utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).mapToDouble(Fatura::getValorTotal).sum());
                        apresentacao.printEnterSair(90);
                        ler.nextLine();
                        x = 0;
                    } catch (SistemaException e) {
                        apresentacao.printMensagem(e.getMessage(), 78, 2);
                        apresentacao.printEnterSair(90);
                        ler.nextLine();
                        x = 0;
                    }
                }
        }while (x != 3);
    }

    public void runPaginacaoMaioresUtilizadores(List<String> strings)
    {
        String opcao;
        Scanner ler = new Scanner(System.in);
        int paginaAtual = 1;
        int quantidade = 10;
        int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
        do {
            int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
            apresentacao.printCompradorVendedor();
            apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
            apresentacao.printClear(1);
            apresentacao.printEspacos(74);
            System.out.println(Apresentacao.CYAN_BOLD + "Pressione" + Apresentacao.RESET + " '+' " +
                    Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                    "para sair" + Apresentacao.RESET);
            apresentacao.printClear(1);
            apresentacao.printEspacos(102);
            opcao = ler.nextLine().toLowerCase();
            if (opcao.equals("+") && paginaAtual < numPaginas) {
                paginaAtual++;
            } else if (opcao.equals("-") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("s")) {
                break;
            }
        } while (true);
    }

    public void runMaiorCompradorVendedor()
    {
        int x = 0;
        Scanner ler = new Scanner(System.in);
        String opcao, d1, d2;
        LocalDate data1, data2;
        List<Utilizador> utilizadores;
        List<String> strings;
        do {
            switch (x) {
                case 0:
                    do {
                        apresentacao.printCompradorVendedor();
                        String[] ops = {"COMPRADOR", "VENDEDOR", "RETROCEDER"};
                        apresentacao.printOpcoes("INDIQUE A SUA ESCOLHA", ops, 95);
                        apresentacao.printEspacos(101);
                        opcao = ler.nextLine().toLowerCase();
                        if (opcao.equals("1")) {
                            x = 1;
                            break;
                        }
                        if (opcao.equals("2")) {
                            x = 2;
                            break;
                        }
                        if (opcao.equals("3")) {
                            x = 3;
                            break;
                        }
                    } while (true);
                case 1: // Comprador
                    apresentacao.printCompradorVendedor();
                    apresentacao.printMensagemCentrada("INSIRA A DATA INICIAL (AAAA-MM-DD)", 1);
                    apresentacao.printEspacos(86);
                    d1 = ler.nextLine();
                    try {
                        data1 = stringParaData(d1);

                    } catch (DateTimeException a) {
                        apresentacao.printMensagem(a.getMessage(), 97, 2);
                        apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 91, 2);
                        apresentacao.printMensagemSimOuNao(99);
                        ler = new Scanner(System.in);

                        opcao = ler.nextLine();

                        if (!isInt(opcao)) {
                            opcao = apresentacao.printInputIncorreto(8);
                        }

                        x = stringToInt(opcao);

                        if (x == 1) {
                            break;
                        }
                        break;

                    }
                    apresentacao.printMensagemCentrada("INSIRA A DATA FINAL (AAAA-MM-DD)", 1);
                    apresentacao.printEspacos(86);
                    d2 = ler.nextLine();
                    try {
                        data2 = stringParaData(d2);
                    } catch (DateTimeException a) {
                        apresentacao.printMensagemCentrada(a.getMessage(), 2);
                        apresentacao.printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
                        apresentacao.printMensagemSimOuNao(99);
                        ler = new Scanner(System.in);

                        opcao = ler.nextLine();

                        if (!isInt(opcao)) {
                            opcao = apresentacao.printInputIncorreto(8);
                        }

                        x = stringToInt(opcao);

                        if (x == 1) {
                            break;
                        }
                        break;
                    }
                    utilizadores = sistema.maioresUtilizadoresEntreDatas(data1, data2, Atributos.VENDIDO);
                    if (utilizadores.isEmpty()) {
                        apresentacao.printCompradorVendedor();
                        apresentacao.printMensagemCentrada("Nenhum Utilizador encontrado entre as datas inseridas!!", 2);
                        apresentacao.printEnter("");
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    strings = new ArrayList<>();
                    int i = 1;
                    for (Utilizador utilizador : utilizadores) {
                        strings.add(i + ") " + apresentacao.utilizadorLinha(utilizador));
                        i++;
                    }
                    runPaginacaoMaioresUtilizadores(strings);
                    x = 0;
                case 2:
                    apresentacao.printCompradorVendedor();
                    apresentacao.printMensagemCentrada("INSIRA A DATA INICIAL (AAAA-MM-DD)", 1);
                    apresentacao.printEspacos(86);
                    d1 = ler.nextLine();
                    try {
                        data1 = stringParaData(d1);

                    } catch (DateTimeException a) {
                        apresentacao.printMensagem(a.getMessage(), 97, 2);
                        apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 91, 2);
                        apresentacao.printMensagemSimOuNao(99);
                        ler = new Scanner(System.in);

                        opcao = ler.nextLine();

                        if (!isInt(opcao)) {
                            opcao = apresentacao.printInputIncorreto(8);
                        }

                        x = stringToInt(opcao);

                        if (x == 1) {
                            break;
                        }
                        break;

                    }
                    apresentacao.printMensagemCentrada("INSIRA A DATA FINAL (AAAA-MM-DD)", 1);
                    apresentacao.printEspacos(86);
                    d2 = ler.nextLine();
                    try {
                        data2 = stringParaData(d2);
                    } catch (DateTimeException a) {
                        apresentacao.printMensagem(a.getMessage(), 97, 2);
                        apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 91, 2);
                        apresentacao.printMensagemSimOuNao(99);
                        ler = new Scanner(System.in);

                        opcao = ler.nextLine();

                        if (!isInt(opcao)) {
                            opcao = apresentacao.printInputIncorreto(8);
                        }

                        x = stringToInt(opcao);

                        if (x == 1) {
                            break;
                        }
                        break;
                    }
                    utilizadores = sistema.maioresUtilizadoresEntreDatas(data1, data2, Atributos.VENDA);
                    if (utilizadores.isEmpty()) {
                        apresentacao.printCompradorVendedor();
                        apresentacao.printMensagemCentrada("Nenhum Utilizador encontrado entre as datas inseridas!!", 2);
                        apresentacao.printEnter("");
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    strings = new ArrayList<>();
                    for (Utilizador utilizador : utilizadores) {
                        strings.add(apresentacao.utilizadorLinha(utilizador));
                    }
                    runPaginacaoMaioresUtilizadores(strings);
                    x = 0;
            }
        }while (x != 3);
    }

    public int runEstatisticas() {
        int x = 0;
        String [] s = {"Vendedor que mais facturou desde sempre ou num período de tempo", "Transportadora com maior facturação", "Encomendas de um vendedor", "Maiores Vendedores/Compradores" +
                " em um período de tempo", "Ganhos Vintage", "Retroceder"};
        String email;
        Scanner ler = new Scanner(System.in);
        String eq;

        do {
            switch (x){
                case 0:

                    do {
                        apresentacao.printMenu(s, 4, 87, "", sistema.getDataAtual());
                        eq = ler.nextLine().toLowerCase();
                        if (eq.equals("1")) { x = 1; break;}
                        if (eq.equals("2")) { x = 2; break;}
                        if (eq.equals("3")) { x = 3; break;}
                        if (eq.equals("4")) { x = 4; break;}
                        if (eq.equals("5")) { x = 5; break;}
                        if (eq.equals("6")) { x = 6; break;}
                    } while (true);
                    break;

                case 1: // Vendedor que mais Faturou
                    runMenuVendedorMaisFaturou();
                    x = 0;
                    break;

                case 2: // Transportadora maior volume facturacao;
                    apresentacao.printTransportadoraDinheiro();
                    System.out.println();
                    try {
                        Transportadora transportadora = sistema.transportadoraMaiorFaturacao();
                        System.out.println();
                        System.out.println(transportadora.toString());
                        apresentacao.printEnterSair(94);
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    catch (SistemaException a){
                        apresentacao.printMensagem(a.getMessage(),86,2);
                        apresentacao.printEnterSair(94);
                        ler.nextLine();
                        x = 0;
                        break;
                    }

                case 3: //Listar encomendas emitidas por um vendedor
                    apresentacao.printEncomendasVendedor();
                    System.out.println();
                    apresentacao.printMensagem("INTRODUZA O EMAIL DO VENDEDOR",88,1);
                    apresentacao.printEspacos(88);
                    email = ler.nextLine();

                    if (email.isEmpty()){
                        apresentacao.printMensagemCentrada("ERR0! DEVE INSERIR UM EMAIL!!",2);
                        apresentacao.printMensagem("DESEJA TENTAR NOVAMENTE?", 87, 2);
                        apresentacao.printMensagemSimOuNao(87);
                        ler = new Scanner(System.in);
                        x = ler.nextInt();

                        if (x == 0){
                            break;
                        } else if (x == 1) {
                            x = 3;
                            break;
                        }
                    }

                    List<Encomenda> listaEncomendas = sistema.listaEncomendasVendedor(email);
                    if (listaEncomendas.isEmpty()){
                        apresentacao.printMensagem("NÃO EXISTEM ENCOMENDAS PARA ESTE UTILIZADOR!",82,2);
                        apresentacao.printEnterSair(90);
                        ler.nextLine();
                        x = 0;
                        break;
                    }
                    List<String> strings = new ArrayList<>();
                    for (Encomenda encomenda : listaEncomendas)
                    {
                        strings.add(apresentacao.showEncomendaEstatisticas(encomenda));
                    }
                    int paginaAtual = 1;
                    int quantidade = 6;
                    int numPaginas = (int) Math.ceil((double) strings.size() / quantidade);
                    String opcao;
                    do {
                        int inicio = (paginaAtual - 1) * quantidade, fim = Math.min(inicio + quantidade, strings.size());
                        apresentacao.printEncomendasVendedor();
                        apresentacao.paginateMenu(strings, quantidade, paginaAtual, numPaginas, inicio, fim);
                        apresentacao.printClear(1);
                        apresentacao.printEspacos(74);
                        System.out.println(Apresentacao.CYAN_BOLD + "Pressione" + Apresentacao.RESET + " '+' " +
                                Apresentacao.CYAN_BOLD + "para avancar," + Apresentacao.RESET + " '-' " + Apresentacao.CYAN_BOLD + "para a retroceder," + Apresentacao.RESET + " 's' " + Apresentacao.CYAN_BOLD +
                                "para sair" + Apresentacao.RESET);
                        apresentacao.printClear(1);
                        apresentacao.printEspacos(102);
                        opcao = ler.nextLine().toLowerCase();
                        if (opcao.equals("+") && paginaAtual < numPaginas) {
                            paginaAtual++;
                        } else if (opcao.equals("-") && paginaAtual > 1) {
                            paginaAtual--;
                        } else if (opcao.equals("s")) {
                            break;
                        }
                    } while (true);
                case 4: //Ordenacao maiores compradores/vendedores
                    runMaiorCompradorVendedor();
                    x = 0;
                    break;

                case 5: //Valor ganho Vintage
                    apresentacao.printVintageDinheiro();
                    apresentacao.printClear(3);
                    apresentacao.printGanhos("$GANHOS$ : ",96,1, sistema.ganhoVintage());
                    apresentacao.printEnterSair(90);
                    ler.nextLine();
                    x = 0;
                    break;
            }
        }while (x != 6);

        return 0;
    }

    public int valorSimouNao(int p, int y, int z) {
        String c;
        Scanner ler;
        int x, u = p; p = -1;

        do {
            if (p == 0) apresentacao.printLogin();
            if (p == 1) apresentacao.printTrans();
            if (p == 2) apresentacao.printReg();
            if (p == 3) apresentacao.printRegTrans();
            if (p == 4) apresentacao.printTax();
            p = u;
            apresentacao.printMensagemCentrada("DESEJA CONTINUAR A TENTAR?", 2);
            apresentacao.printMensagemSimOuNao(101);
            ler = new Scanner(System.in);
            c = ler.nextLine();
            if (c.equals("1")) { x = y; break;}
            if (c.equals("0")) { x = z; break;}
        } while (true);

        return x;
    }

    public static boolean dataValida(final String data){

        boolean valido = false;

        try {
            LocalDate.parse(data, DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT));
            valido = true;
        }
        catch (DateTimeParseException ignored) {
        }
        return valido;
    }

    public LocalDate stringParaData(String dma){

        if (dataValida(dma)){
            String[] dataf = dma.split("-");
            int ano = Integer.parseInt(dataf[0]);
            int mes = Integer.parseInt(dataf[1]);
            int dia = Integer.parseInt(dataf[2]);
            return LocalDate.of(ano, mes, dia);
        }
        else throw new DateTimeException("DATA INVÁLIDA");
    }
}
