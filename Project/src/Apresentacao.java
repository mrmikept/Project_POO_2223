import java.time.LocalDate;
import java.util.*;

public class Apresentacao
{
    /**
     * Variante de instância estática que define o valor da cor original do terminal
     */
    public static final String RESET = "\033[0m";

    /**
     * Variante de instância estática que define o valor da cor vermelho do terminal
     */
    public static final String RED = "\033[0;31m";

    /**
     * Variante de instância estática que define o valor da cor azul negrito do terminal
     */
    public static final String CYAN_BOLD = "\033[1;36m";

    /**
     * Variante de instância estática que define o valor da cor amarelo do terminal
     */
    public static final String YELLOW = "\033[0;33m";

    /**
     * Variante de instância estática que define o valor da cor azul do terminal
     */
    public static final String CYAN = "\033[0;36m";

    /**
     * Variante de instância estática que define uma linha
     */
    public static String LINE =
            "##############################";

    /**
     * Função que apaga tudo do terminal
     */
    public static void clear(){
        System.out.print("\033\143");
        System.out.flush();
    }

    /**
     * Função que imprime um número de linhas vazias
     * @param number Número de limhas
     */
    public void printClear(int number) {
        for (int i = 0; i < number; ++i) System.out.println();
    }
    /**
     * Função que imprime um número de linhas da instância estática LINE
     * @param numeroDeLinhas Número de limhas
     */
    public void line(int numeroDeLinhas){
        for (int i=0;i<numeroDeLinhas;i++) System.out.println(LINE);
    }

    /**
     * Função que imprime um número de espaços numa linha
     * @param x Número de espaços
     */
    public void printEspacos(int x) {
        for (int i = 0; i< x; i++) System.out.print(" ");
    }

    /**
     * Função que coloca o texto a azul
     */
    public void cyan(){
        System.out.println("\033[0;36m");
    }

    /**
     * Função que coloca o texto a azul negrito
     */
    public void cyanBold(){
        System.out.println("\033[1;36m");
    }

    /**
     * Função que coloca o texto a amarelo
     */
    public void yellow(){
        System.out.println("\033[0;33m");
    }

    /**
     * Função que coloca o texto a vermelho
     */
    public void red(){
        System.out.println("\033[0;31m");
    }

    /**
     * Função que coloca o texto à cor original do terminal
     */
    public void resetColor(){
        System.out.println("\033[0m");
    }

    /**
     * Função que imprime uma opção
     * @param i Número da opção
     * @param s Opção
     * @param x Número de espaços
     */
    public void printOpcao(int i,String s, int x){
        printEspacos(x);
        System.out.print(CYAN_BOLD+i+")"+RESET+"  "+s + "\n");
    }

    /**
     * Função que imprime a data atual do sistema
     * @param data Data atual do sistema
     */
    public void printDataAtual(LocalDate data){
        System.out.println( CYAN_BOLD + "                                                                                          Data Atual: " + RESET + data);
    }

    /**
     * Função que imprime um menu
     * @param opcoes Número de opções
     * @param x Identificador para imprimir uma determinada imagem
     * @param e Número de espaços
     * @param nome Nome de um utilizador ou transportadora
     * @param data Data atual do sistema
     */
    public void printMenu(String[] opcoes, int x, int e, String nome, LocalDate data){
        if (x != 100)
        {
            clear();
        }

        if (x == 0) {
            printVintage();
            printDataAtual(data);
        }

        if (x == 1) {
            printVintage();
            printDataAtual(data);
            System.out.println();
            System.out.println("                                                                                            " + CYAN_BOLD +"Bem-vindo " + RESET + nome + "!!");
        }

        if (x==2) printConfiguracoes();

        if (x == 3) printEncomendas();

        if (x == 4) printEstatisticas();

        if (x == 5) printFaturas();

        int i = 1;
        printClear(3);
        for (String s : opcoes) {
            printOpcao(i, s, e);
            i++;
        }
        printClear(5);
        cyan();
        line(1);
        red();
        System.out.print("Opção pretendida:");
        resetColor();
    }

    /**
     * Função que imprime a imagem da Vintage
     */
    public void printVintage(){
        cyan();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                    ⠀⠀⠀⠀⠀⠀⢀⣀⣠⣀⡀⠀⠀⠀⠀⢀⣀⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀                                ############################################                                                                     ");
        System.out.println("                   ⡀⣀⣀⣤⠴⠖⠛⠋⠉⠸⣏⠙⠛⠛⠛⠛⠋⣹⠇⠉⠙⠛⠲⠦⣤⣀⣀⠀⠀                             ##################################################                           ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⠟⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ");
        System.out.println("                   ⣿⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠳⢦⣤⣤⡴⠞⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⣿⠀                          ########################################################                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣴⣾⠿⢦⣴⠏⠀⠀⣴⢿⡄⠀⠀⠀⠀⠀   ");
        System.out.println("                   ⣻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠀                           ██╗░░░██╗██╗███╗░░██╗████████╗░█████╗░░██████╗░███████╗                        ⠀⠀⠀⢀⣀⣀⣤⣴⡶⢻⣏⠻⡦⠙⠇⠀⠉⠻⠶⠞⠫⡼⣧⠀⠀⠀⠀⠀   ");
        System.out.println("                   ⢿⣇⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣸⡇⠀                           ██║░░░██║██║████╗░██║╚══██╔══╝██╔══██╗██╔════╝░██╔════╝                       ⣴⠞⠛⠛⠋⠉⠉⠀⠀⠋⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⣦⡀⠀⠀⠀⠀⠀   ");
        System.out.println("                    ⠉⠉⠉⠉⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠉⠉⠉⠉⠀⠀                           ╚██╗░██╔╝██║██╔██╗██║░░░██║░░░███████║██║░░██╗░█████╗░░                       ⣿⣷⡶⠶⣤⣤⣤⣤⣤⠶⠶⠶⠛⠛⠛⠛⢛⣠⣴⣾⣏⣀⡾⠁⠀⢀⣶⡄⠀   ");
        System.out.println("                    ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                           ░╚████╔╝░██║██║╚████║░░░██║░░░██╔══██║██║░░╚██╗██╔══╝░░                        ⠉⠙⠛⠳⠶⠶⠶⣤⠀⠀⢀⣀⣤⣴⡾⢻⣍⠻⣦⠈⠙⢷⣤⣴⠟⣩⣷⠀   ");
        System.out.println("                    ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                           ░░╚██╔╝░░██║██║░╚███║░░░██║░░░██║░░██║╚██████╔╝███████╗                        ⠀⠀⠀⢀⣴⠶⠶⠶⠚⠛⠋⠉⠻⠂⠙⠀⠉⠀⠀⠀⠀⠀⠀⣠⡞⠉⣿⠀   ");
        System.out.println("                    ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                           ░░░╚═╝░░░╚═╝╚═╝░░╚══╝░░░╚═╝░░░╚═╝░░╚═╝░╚═════╝░╚══════╝                        ⠀⠀⠀⣿⣧⣤⣤⣀⣀⣀⣀⣀⣀⣤⣤⣤⠶⠶⠶⠶⠶⠶⠶⠿⠶⢶⣏⠀   ");
        System.out.println("                    ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                          ########################################################                        ⠀⠀⠀⠉⠛⠳⠶⢯⣭⣭⣍⣉⣉⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⡿⠀   ");
        System.out.println("                    ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                             ##################################################                           ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠀⠀   ");
        System.out.println("                    ⠀⠀⠀⠀⠘⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠃⠀⠀⠀⠀⠀⠀                                ############################################                                                                     ");

        printClear(2);
        resetColor();
    }

    /**
     * Função que imprime a imagem do login utilizador
     */
    public void printLogin() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                                  ⠀ ⠀⠀⠀        ⣀⣤⣶⣶⣿⣿⣿⣿⣿⣿⣶⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⣠⣴⣿⡿⠟⠛⠉⠁⠀⠀⠀⠀⠈⠉⠛⠻⢿⣿⣦⣄⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⣠⣾⣿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣷⣄⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⣴⣿⡟⠁⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣧⠀⠀");
        System.out.println("                                                                                       ⠀⣼⣿⠏⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠹⣿⣧⠀");
        System.out.println("                                                                                       ⢸⣿⡟⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⢻⣿⡇");
        System.out.println("                                                                                       ⣿⣿⠃⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠘⣿⣿");
        System.out.println("                                                                                       ⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿");
        System.out.println("                                                                                       ⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⢿⣿⣿⡿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿");
        System.out.println("                                                                                       ⢸⣿⣧⠀⠀⠀⠀⠀⠀⠀⢀⣄⡀⠀⠀⠀⠀⢀⣠⡀⠀⠀⠀⠀⠀⠀⠀⣸⣿⡇");
        System.out.println("                                                                                       ⠀⢻⣿⣆⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⠀⣰⣿⡟⠀");
        System.out.println("                                                                                       ⠀⠀⢻⣿⣧⡀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⢀⣼⣿⠟⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠙⢿⣿⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣴⣿⡿⠋⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠋⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠿⠿⣿⣿⣿⣿⣿⣿⠿⠿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem de registar um utilizador
     */
    public void printReg() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                                             ⣠⣶⣿⣿⣿⣷⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⡿⢃⣡⣴⣾⣶⣶⣶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⠏⢠⣾⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⢈⣻⣿⣿⡿⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⣀⣤⣶⣿⣿⣿⣿⣟⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣇⡀⠻⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⠻⠛⢁⣠⣨⣿⣿⣿⣿⣿⣄⣄⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⣾⣿⣿⣿⣿⣿⣿⠿⠃⠀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣤⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠿⠿⠿⠿⣿⣿⠟⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠉⣁⡀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⢴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢸⣿⣿⠀⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢠⣤⣤⣬⣿⣿⣴⣤⣤");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠘⠛⠛⠻⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠇⠘⠛⠛⢛⣿⣿⠛⠛⠛");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢘⣿⣿⠀⠀⠀");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem da transportadora
     */
    public void printTrans() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                    ⠀⠀⠀⠀⠀ ⠀⠀⠀⠀       ⠀⣾⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⢻⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⢴⣶⣶⣶⣿⣶⣶⣶⣶⣶⣶⡦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                            ⠀⠀⠀⢀⣠⣀⣀⣀⣀⣸⣇⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡿⠛⠛⠛⠛⠛⠷⣄⠀⠀⠀⠀⠀");
        System.out.println("                                                                            ⠀⠀⠀⠈⠛⠛⠛⠛⠛⣿⠛⠛⠛⠛⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⣠⣤⣤⣤⣤⡀⠉⠷⣄⠀⠀⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠿⠿⠿⢿⡿⠿⠿⠿⠿⠿⠿⠿⠿⠗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠉⣿⣿⣿⣿⣿⣿⣦⡀⠉⢷⣄⠀");
        System.out.println("                                                                            ⠀⠠⣴⣶⣶⣶⣶⣶⣾⣷⣶⣶⣶⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⠀⠈⠛⠛⠛⠛⠛⠛⠛⠂⠀⣿⠄");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⣿⠆⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⢀⣴⠿⠛⠛⠿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⣠⣶⠿⠿⣶⣄⠀⠀⢸⡇⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⣸⣧⣤⣤⣤⣤⣤⣤⣾⡏⠀⠀⠀⠀⣹⣧⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣿⣤⣤⣼⡟⠁⠀⠀⢘⣿⣤⣤⡾⠃⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣷⣄⣀⣀⣴⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣦⣤⣤⣾⠏⠀⠀⠀⠀ ");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠀⠀⠀⠀    ");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem de registar uma transportadora
     */
    public void printRegTrans() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                    ⠀⠀⠀⠀⠀ ⠀⠀⠀⠀       ⠀⣾⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⢻⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⢴⣶⣶⣶⣿⣶⣶⣶⣶⣶⣶⡦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                            ⠀⠀⠀⢀⣠⣀⣀⣀⣀⣸⣇⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡿⠛⠛⠛⠛⠛⠷⣄⠀⠀⠀⠀⠀");
        System.out.println("                                                                            ⠀⠀⠀⠈⠛⠛⠛⠛⠛⣿⠛⠛⠛⠛⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⣠⣤⣤⣤⣤⡀⠉⠷⣄⠀⠀⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠿⠿⠿⢿⡿⠿⠿⠿⠿⠿⠿⠿⠿⠗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠉⣿⣿⣿⣿⣿⣿⣦⡀⠉⢷⣄⠀");
        System.out.println("                                                                            ⠀⠠⣴⣶⣶⣶⣶⣶⣾⣷⣶⣶⣶⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⠀⠈⠛⠛⠛⠛⠛⠛⠛⠂⠀⣿⠄");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⣿⠆⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⢀⣴⠿⠛⠛⠿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⣠⣶⠿⠿⣶⣄⠀⠀⢸⡇⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⣸⣧⣤⣤⣤⣤⣤⣤⣾⡏⠀⠀⠀⠀⣹⣧⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣿⣤⣤⣼⡟⠁⠀⠀⢘⣿⣤⣤⡾⠃⠀");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣷⣄⣀⣀⣴⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣦⣤⣤⣾⠏⠀⠀⠀⠀⠀⢸⣿⣿");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠀⠀⠀⠀⢠⣤⣤⣿⣿⣿⣤⣤⣤");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                              ⠘⠛⠛⢛⣿⣿⠛⠛⠛");
        System.out.println("                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                 ⢸⣿⣿⠀  ");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem de uma encomenda
     */
    public void printBox() {
        clear();
        System.out.println();
        System.out.println("                                                                                                    ⣤⣤⡶⠿⠿⢶⣤⣤             ");
        System.out.println("                                                                                           ⠀⠀⠀⠀⠀⣀⣤⡶⠿⢯⣅⡀⠀⠀⠀⠈⠙⠻⢶⣤⣀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                           ⠀⣀⣤⠶⠛⠻⢧⣄⡀⠀⠈⠙⠳⢶⣤⣀⠀⠀⠀⠈⠉⠛⠶⣤⣀⠀⠀⠀");
        System.out.println("                                                                                          ⣶⣿⣉⠀⠀⠀⠀⠀⠈⠙⠳⢶⣄⣀⠀⠈⠙⠻⢶⣤⣀⠀⠀⠀⠀⣉⣿⣶⠀");
        System.out.println("                                                                                          ⣿⠀⠉⠛⠶⣤⣀⠀⠀⠀⠀⠀⠈⠙⠻⢶⣤⣀⠀⠈⠉⣻⣶⠶⠛⠉⠀⣿⠀");
        System.out.println("                                                                                          ⣿⠀⠀⠀⠀⠀⠉⠛⠶⣦⣄⠀⠀⠀⠀⠀⢈⡙⠛⣶⠛⠉⣿⠀⠀⠀⠀⣿⠀");
        System.out.println("                                                                                          ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠶⣦⣴⠾⠛⠁⠀⣿⠀⠀⣿⠀⠀⠀⠀⣿⠀");
        System.out.println("                                                                                          ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⣿⣀⣤⡿⠀⠀⠀⠀⣿⠀");
        System.out.println("                                                                                          ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠛⠉⠀⠀⠀⠀⠀⠀⣿⠀");
        System.out.println("                                                                                          ⣿⠀⠛⠶⣤⣄⡀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀");
        System.out.println("                                                                                          ⣿⠀⠛⠶⡄⠉⠁⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀");
        System.out.println("                                                                                          ⠿⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⠿⠀");
        System.out.println("                                                                                           ⠀⠉⠛⠶⣤⣀⡀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⢀⣀⣤⠶⠛⠉⠀⠀⠀");
        System.out.println("                                                                                           ⠀⠀⠀⠀⠀⠉⠛⠷⣦⣄⡀⠀⢸⡇⠀⢀⣠⣴⠾⠛⠉⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                           ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠷⣾⣷⠾⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem de guardar o estado
     */
    public void printGuardar() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                                         ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣬       ");
        System.out.println("                                                                                         ⣿⣿⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡟⢿⣷⣄⠀⠀⠀⠀");
        System.out.println("                                                                                         ⣿⣿⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⣾⣷⠀⢸⣿⡇⠀⠙⢿⣿⣦⡀⠀");
        System.out.println("                                                                                         ⣿⣿⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠈⠻⣿⣦");
        System.out.println("                                                                                         ⣿⣿⠀⠀⠀⠀⠀⠸⣿⣷⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣾⣿⠇⠀⠀⠀⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⠀⠀⢰⣿⡿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⢿⣿⡆⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⠀⠀⢸⣿⡇⠀⠰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠆⠀⢸⣿⡇⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⠀⠀⢸⣿⡇⠀⠠⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⠄⠀⢸⣿⡇⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⠀⠀⠸⣿⣷⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣾⣿⠇⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠁⠀⠀⠀⣿⣿");
        System.out.println("                                                                                         ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem de carregar estado
     */
    public void printLoad() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                                               ⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                            ⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣖⠀⠀⠀⠀⠀ ⠀⠀ ⠀⠀ ⠀⠀⠀⠀");
        System.out.println("                                                                                            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣷⣾⣶⣶⣶⣶⣶⣷⣶⣶⣧⣄⠈");
        System.out.println("                                                                                            ⣿⣿⡟⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⢻⣿⣷");
        System.out.println("                                                                                            ⣿⣿⡅⠀⠀ ⠀⠀⠀⠀⠀⠀⣤⣤⣤⣴⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣿⣿");
        System.out.println("                                                                                            ⣿⣿⠆⠀⠀⠀⠀⠀ ⠀⠂⠀⣿⣿⣿⣿⠀⠀⠐⠀⢀⠀⠐⠀⠀⠀⣿⣿");
        System.out.println("                                                                                            ⣿⣿⡅⠀⠀⠀⠁⠀⠀⢀⣀⣄⣿⣿⣿⣿⣀⣀⡀⠀⠀⠀⠀⠀⠈⠈⣿⣿");
        System.out.println("                                                                                            ⣿⣿⠆⠀⠀⢀⠀⠀⠈⠀⠝⣿⣿⣿⣿⣿⡿⠋⠀⠃⠀⠀⠁⠀⠀⠐⣿⣿");
        System.out.println("                                                                                            ⣿⣿⡃⠀⠀⡀⠀⢀⣠⣀⠀⠀⠝⢿⡿⢋⠀⠀⣄⣄⡀⠀⢀⠀⢀⢀⣿⣿");
        System.out.println("                                                                                            ⣿⣿⡅⠀⠀⠀⠀⢺⣿⣯⡀⣀⡀⢁⣀⡀⣀⢀⣽⣿⡇⠀⠀⠀⠀⠀⣿⣿");
        System.out.println("                                                                                            ⣿⣿⡂⠀⠀⠀⠀⡀⣙⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠀⠀⠀⠀⠀⠐⣿⣿");
        System.out.println("                                                                                            ⣿⣿⡅⠀⠀⡐⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⢢⣿⣿");
        System.out.println("                                                                                            ⠘⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃");
        System.out.println("                                                                                            ⠀⠀⠀⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠁   ");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem "COMPRAR"
     */
    public void printComprar() {
        clear();
        cyan();
        System.out.println("                                                                                #############################################        ");
        System.out.println("                                                                             ###################################################     ");
        System.out.println("                                                                          #########################################################  ");
        System.out.println("                                                                         ░█████╗░░█████╗░███╗░░░███╗██████╗░██████╗░░█████╗░██████╗░ ");
        System.out.println("                                                                         ██╔══██╗██╔══██╗████╗░████║██╔══██╗██╔══██╗██╔══██╗██╔══██╗ ");
        System.out.println("                   ##################################################### ██║░░╚═╝██║░░██║██╔████╔██║██████╔╝██████╔╝███████║██████╔╝ ##################################################### ");
        System.out.println("                                                                         ██║░░██╗██║░░██║██║╚██╔╝██║██╔═══╝░██╔══██╗██╔══██║██╔══██╗ ");
        System.out.println("                                                                         ╚█████╔╝╚█████╔╝██║░╚═╝░██║██║░░░░░██║░░██║██║░░██║██║░░██║ ");
        System.out.println("                                                                         ░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚═╝ ");
        System.out.println("                                                                          #########################################################  ");
        System.out.println("                                                                             ###################################################     ");
        System.out.println("                                                                                #############################################        ");
        System.out.println();
        System.out.println();
        resetColor();
    }

    /**
     * Função que imprime a imagem "COMPRAS"
     */
    public void printCompras() {
        clear();
        cyan();
        System.out.println("                                                                                #############################################        ");
        System.out.println("                                                                             ###################################################     ");
        System.out.println("                                                                          #########################################################  ");
        System.out.println("                                                                         ░█████╗░░█████╗░███╗░░░███╗██████╗░██████╗░░█████╗░░██████╗ ");
        System.out.println("                                                                         ██╔══██╗██╔══██╗████╗░████║██╔══██╗██╔══██╗██╔══██╗██╔════╝ ");
        System.out.println("                   ##################################################### ██║░░╚═╝██║░░██║██╔████╔██║██████╔╝██████╔╝███████║╚█████╗░ ##################################################### ");
        System.out.println("                                                                         ██║░░██╗██║░░██║██║╚██╔╝██║██╔═══╝░██╔══██╗██╔══██║░╚═══██╗ ");
        System.out.println("                                                                         ╚█████╔╝╚█████╔╝██║░╚═╝░██║██║░░░░░██║░░██║██║░░██║██████╔╝ ");
        System.out.println("                                                                         ░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░ ");
        System.out.println("                                                                          #########################################################  ");
        System.out.println("                                                                             ###################################################     ");
        System.out.println("                                                                                #############################################        ");
        System.out.println();
        System.out.println();
        resetColor();
    }

    /**
     * Função que imprime a imagem da automatização
     */
    public void printBackup() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                                 ⠀⠀⠀⠀ ⠀⠀⢀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣼⣿⠻⠛⢛⠛⡛⣛⡛⣛⡛⣛⠛⣛⡛⣛⣛⣻⣛⡛⠛⣻⣛⡛⠛⠛⠛⢻⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠌⣀⠀⠀⠀⠁⡁⠁⡁⠉⠀⠁⠉⠈⠅⠀⣙⣷⣲⠋⣽⣖⡠⠁⢹⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⢘⢿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠏⠈⠘⣿⣿⠧⠻⣿⣿⠇⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⡀⠀⠀⠂⠀⢂⠀⢂⢀⢂⡀⢀⠀⢀⣀⣀⡀⠁⣀⡀⠀⠀⠀⠀⢰⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⢻⣿⣶⣿⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣾⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢀⣿⡧⠀⠀⠀⠀⠀⠐⡀⠒⠐⠐⠂⠒⠒⠐⠒⠂⠀⠀⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣼⣿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⢿⢿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠠⠆⠒⠀⠒⠂⠂⠒⠐⠂⠒⠀⠂⠀⠂⠂⠀⠀⠀⠂⠀⠀⠀⠀⠀⢺⣿⡅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠹⣷⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⠇⠀⠀⠀⠀⠀⠀⠀⢀⢀⣀⣸⣿⡇⠀⠀⠀⣾⡆⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠘⠐⠒⠒⠒⠒⠒⠒⠒⠒⠂⠂⠂⠀⠀⠀⠀⣠⣴⣾⡿⠿⠟⠻⠿⠿⢿⣶⣤⣹⣿⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⢿⣿⣴⣶⣶⣤⣴⣤⣤⣤⣴⣤⣴⣤⣤⣤⣤⣤⣤⣤⣾⡿⠋⠁⠀⠀⠀⠀⠀⢀⣤⣬⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⠊⠉⠉⠉⣿⡏⠉⠙⠋⠛⠋⠛⠋⠉⠉⠉⠉⠉⣽⣿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠈⠋⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣴⣶⣶⣶⣿⣷⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣾⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠠⠀⠀⠄⠀⠄⠠⠄⠂⠄⠠⠀⠄⢀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⢱⣦⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⡼⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠙⠉⠉⡉⠉⡉⠉⡉⡉⠉⠉⠉⠀⢹⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⣿⣿⣀⣀⣀⣀⣀⣀⣀⣀⣐⣀⣀⣀⣀⣀⣀⣀⣻⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣄⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⠘⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⢿⣷⣄⡀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⡿⠃⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠿⣷⣶⣶⣴⣶⣶⣾⠿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem "VENDAS"
     */
    public void printVendas() {
        clear();
        cyan();
        System.out.println("                                                                                   ######################################            ");
        System.out.println("                                                                                ############################################         ");
        System.out.println("                                                                             ##################################################      ");
        System.out.println("                                                                             ██╗░░░██╗███████╗███╗░░██╗██████╗░░█████╗░░██████╗      ");
        System.out.println("                                                                             ██║░░░██║██╔════╝████╗░██║██╔══██╗██╔══██╗██╔════╝      ");
        System.out.println("                       ##################################################### ╚██╗░██╔╝█████╗░░██╔██╗██║██║░░██║███████║╚█████╗░ #####################################################");
        System.out.println("                                                                             ░╚████╔╝░██╔══╝░░██║╚████║██║░░██║██╔══██║░╚═══██╗      ");
        System.out.println("                                                                             ░░╚██╔╝░░███████╗██║░╚███║██████╔╝██║░░██║██████╔╝      ");
        System.out.println("                                                                             ░░░╚═╝░░░╚══════╝╚═╝░░╚══╝╚═════╝░╚═╝░░╚═╝╚═════╝░      ");
        System.out.println("                                                                             ##################################################      ");
        System.out.println("                                                                                ############################################         ");
        System.out.println("                                                                                   ######################################            ");
        resetColor();
    }

    /**
     * Função que imprime a imagem da minha lista de artigos à venda
     */
    public void printMinhaLista() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                                              ⣼⡟⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⢻⣷⣄⠀        ⠀");
        System.out.println("                                                                                              ⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠙⢷⣄⠀⠀⠀⠀⠀ ⠀");
        System.out.println("                                                                                              ⣿⡇⠀⢠⣄⣠⡾⠂⠀⣠⣤⣤⡄⠘⠷⠶⠶⠿⣷⠀⠀⠀⠀ ⠀");
        System.out.println("                                                                                              ⣿⡇⠀⠀⠙⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿       ");
        System.out.println("                                                                                              ⣿⡇⠀⢀⡀⣀⡶⠂⠀⣀⣀⣀⣀⣀⣀⣀⡀⠀⣿⠀⠀⠀⠀ ⠀");
        System.out.println("                                                                                              ⣿⡇⠀⠈⠛⠋⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠀⠀⣿⠀⠀⠀⠀⠀ ");
        System.out.println("                                                                                              ⣿⡇⠀⢀⡀⢀⣴⠄⠀⣀⣀⣀⣀⣀⣀⣀⠀⠀⣿⠀⠀⠀⠀⠀ ");
        System.out.println("                                                                                              ⣿⡇⠀⠈⠻⠟⠁⠀⠀⠉⠉⠉⠉⠉⠉⠉⠁⠀⣿⠀⠀⠀⠀⠀ ");
        System.out.println("                                                                                              ⣿⡇⠀⠀⠀⢀⣤⠄⠀⠀⠀⠀⠀⢠⣤⣤⣤⣤⣼⠀⠀⠀⠀⠀ ");
        System.out.println("                                                                                              ⣿⡇⠀⠈⠳⠟⠁⠀⠀⠙⠛⢿⡄⢸⣇⣀⣀⣀⣿⣛⣛⣛⣿⡀ ");
        System.out.println("                                                                                              ⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣧⠈⠉⠉⠉⠉⠉⠉⠉⠉⢹⡏ ");
        System.out.println("                                                                                              ⠹⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠀⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀⣾⠁ ");
        System.out.println("                                                                                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣧⣀⣀⣀⣀⣀⣀⣀⣸⡏⠀ ");
        System.out.println("                                                                                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⣠⣿⣭⣭⣭⣭⣭⣭⣭⣥⣀⠀");
        System.out.println("                                                                                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠸⣯⣽⠃⠀⠀⠀⠀⠀⠸⣧⣽⠂");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem de adicionar artigos à minha lista de vendas
     */
    public void printAdicionaArtigoVenda() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⣴⡿⠛⠛⠛⠛⠛⠛⡛⠛⠛⠛⣿ ");
        System.out.println("                                                                                                 ⠀⠀⠀⠀⠀⠀⠀⠀⢀⢠⣾⢋⣤⣤⡀⠀⠀⠀ ⣴⡿⠶⠀⠀⣿ ");
        System.out.println("                                                                                                 ⠀⠀ ⠀⠀⢀⡴⠟⠛⠛⠻⣟⠛⢧⣼⠇⠀⠀⠀⣌⣟⣷⠀⠀⣿ ");
        System.out.println("                                                                                                 ⠀ ⠀⠀⣾⠃ ⠀⠀⠀⠀⠙⢷⣄⠀⠀⠀⠀⠀⠉⠛⠁⠀⠀⣿ ");
        System.out.println("                                                                                                  ⠀⠀⠀⠹⣧⡀⠀⠀⠀⠀⠀⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛  ");
        System.out.println("                                                                                                 ⠀⠀⠀⠀⠀⠈⢛⡛⣛⣛⣛⢛⢛⣛⣟⡟⠛⠛⠛⢷⣄⠀⠀⠀  ");
        System.out.println("                                                                                                 ⠀⢸⡟⠛⠛⠛⠛⣿⠛⠛⣿⠛⠛⠛⠛⢻⡇⠀⠀⢀⣿⠀⠀⠀  ");
        System.out.println("                                                                                                 ⠀⢸⡇⠀⠀⠀⠀⣿⣤⣤⣿⠀⠀⠀⠀⢸⡷⠶⠶⠛⠁⠀⠀⠀  ");
        System.out.println("                                                                                                 ⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀  ");
        System.out.println("                                                                                                 ⠀⢸⣇⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀  ⠀");
        System.out.println("                                                                                         ⠶⠶⠶⠶⠶⠶⣶⠟⠋⠉⠉⠙⠛⠷⠶⠶⠶⠶⠶⢦⡀⢸⡇⢀⣠⣴⠞⠛⠻⣦  ");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠠⣿⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⣸⣧⣼⠷⠛⠉⠀⠀⠀⣠⡿  ");
        System.out.println("                                                                                         ⠀⠀⣀⣤⣄⠀⣿⠀⠀⠀⠀⠀⠀⠘⠛⠛⠛⠛⠛⠛⠉⠀⠀⠀⠀⣠⣴⠟⠋⠀  ");
        System.out.println("                                                                                         ⠀⠀⠻⣧⠿⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡶⠛⠉⠀⠀⠀⠀  ");
        System.out.println("                                                                                         ⣤⣤⣤⣤⣤⣤⣿⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣴⠾⠛⠁⠀⠀⠀⠀⠀⠀  ⠀");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem "AVISOS"
     */
    public void printAviso() {
        clear();
        cyan();
        System.out.println("                                                                                      ######################################            ");
        System.out.println("                                                                                   ############################################         ");
        System.out.println("                                                                                ##################################################      ");
        System.out.println("                                                                                   ░█████╗░██╗░░░██╗██╗░██████╗░█████╗░░██████╗         ");
        System.out.println("                                                                                   ██╔══██╗██║░░░██║██║██╔════╝██╔══██╗██╔════╝         ");
        System.out.println("                             ##################################################### ███████║╚██╗░██╔╝██║╚█████╗░██║░░██║╚█████╗░ #####################################################");
        System.out.println("                                                                                   ██╔══██║░╚████╔╝░██║░╚═══██╗██║░░██║░╚═══██╗         ");
        System.out.println("                                                                                   ██║░░██║░░╚██╔╝░░██║██████╔╝╚█████╔╝██████╔╝         ");
        System.out.println("                                                                                   ╚═╝░░╚═╝░░░╚═╝░░░╚═╝╚═════╝░░╚════╝░╚═════╝░         ");
        System.out.println("                                                                                ##################################################      ");
        System.out.println("                                                                                   ############################################         ");
        System.out.println("                                                                                      ######################################            ");
    }
    /**
     * Função que imprime erro na automatização
     */
    public void printErrosAutomatizcao(List<String> erro){
        printAviso();
        cyan();
        System.out.println();
        System.out.println();
        System.out.println(RED+"                                                                                        Avisos do carregamento do ficheiro");
        resetColor();
        System.out.println();
        System.out.println();
        int i = 1;
        for (String s : erro){
            System.out.println("                                                                          "+ CYAN_BOLD+i+")"+"    " + YELLOW +s);
            i++;
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(RESET +"                                                                                       Pressione enter para continuar...");
        System.out.println();
        System.out.print("                                                                                                      ");
    }

    /**
     * Função que imprime a imagem dos artigos à venda
     */
    public void printArtigosVenda() {
        cyan();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                                                                                      ⡤⠷⣿⣯⣽⣿⠶⢤                                                                                        ");
        System.out.println("                           ⠀                                      ⠀⠀⠀⠀⠀⠀                            ⡴⢋⡴⠋⠉⠀⠀⠉⠙⢦⡙⢦⠀⠀⠀⠀⠀⠀                                                                              ");
        System.out.println("                            ⠀⠀⠀⠀⠀⠀⢀⣀⣠⣀⡀⠀⠀⠀⠀⢀⣀⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀     ⠀⠀⠀⠀                                  ⣸⢡⡏⠀⠀⠀⠀⠀⠀⠀⠀⢹⡌⣇⠀⠀⠀⠀⠀                                ⠀⠀⠀⠀⠀⠀                                      ");
        System.out.println("                           ⡀⣀⣀⣤⠴⠖⠛⠋⠉⠸⣏⠙⠛⠛⠛⠛⠋⣹⠇⠉⠙⠛⠲⠦⣤⣀⣀⠀⠀                                      ⠀⠀⠀⠀⠀⣿⢸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⣿⠀⠀⠀⠀⠀                                                                              ");
        System.out.println("                           ⣿⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠳⢦⣤⣤⡴⠞⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⣿⠀                                      ⠀⠀⠀⠀⠀⣿⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿⠀⠀⠀⠀⠀                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⣠⡴⠟⣷           ");
        System.out.println("                           ⣻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠀                                      ⡤⠤⠤⠤⠤⣿⢸⠤⠤⠤⠤⠤⠤⠤⠤⠤⠤⡇⣿⠤⠤⠤⠤⢤                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣴⣾⠿⢦⣴⠏⠀⠀⣴⢿⡄⠀⠀⠀⠀⠀   ");
        System.out.println("                           ⢿⣇⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣸⡇⠀                                      ⡏⠉⠉⠉⠉⣿⢾⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡷⣿⠉⠉⠉⠉⢹                                        ⠀⠀⠀⢀⣀⣀⣤⣴⡶⢻⣏⠻⡦⠙⠇⠀⠉⠻⠶⠞⠫⡼⣧⠀⠀⠀⠀⠀   ");
        System.out.println("                            ⠉⠉⠉⠉⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠉⠉⠉⠉⠀⠀                                      ⡇⠀⠀⠀⠀⣿⢸⠀⠀⢠⣤⣤⣤⣤⡄⠀⠀⡇⣿⠀⠀⠀⠀⢸                                       ⣴⠞⠛⠛⠋⠉⠉⠀⠀⠋⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⣦⡀⠀⠀⠀⠀⠀   ");
        System.out.println("                            ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                                      ⡇⠀⠀⠀⠀⠛⠛⠀⠀⢸⡯⠅⠈⢽⡇⠀⠀⠛⠛⠀⠀⠀⠀⢸                                       ⣿⣷⡶⠶⣤⣤⣤⣤⣤⠶⠶⠶⠛⠛⠛⠛⢛⣠⣴⣾⣏⣀⡾⠁⠀⢀⣶⡄⠀   ");
        System.out.println("                            ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                                      ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⣿                                        ⠉⠙⠛⠳⠶⠶⠶⣤⠀⠀⢀⣀⣤⣴⡾⢻⣍⠻⣦⠈⠙⢷⣤⣴⠟⣩⣷⠀   ");
        System.out.println("                            ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                                      ⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇                                        ⠀⠀⠀⢀⣴⠶⠶⠶⠚⠛⠋⠉⠻⠂⠙⠀⠉⠀⠀⠀⠀⠀⠀⣠⡞⠉⣿⠀   ");
        System.out.println("                            ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                                      ⠸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠇                                        ⠀⠀⠀⣿⣧⣤⣤⣀⣀⣀⣀⣀⣀⣤⣤⣤⠶⠶⠶⠶⠶⠶⠶⠿⠶⢶⣏⠀   ");
        System.out.println("                            ⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀                                       ⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼                                          ⠀⠀⠀⠉⠛⠳⠶⢯⣭⣭⣍⣉⣉⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⡿⠀  ");
        System.out.println("                            ⠀⠀⠀⠀⠘⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠃⠀⠀⠀⠀⠀                                        ⠸⣄⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⠇                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠀⠀  ");
        resetColor();
        System.out.println("                                        (1)                                                              (2)                                                                 (3)");
    }

    /**
     * Função que imprime a imagem da tshirt
     */
    public void printTshirt(){
        clear();
        System.out.println();
        System.out.println("                                                                                                    ⢀⣀⣠⣀⡀⠀⠀⠀⠀⢀⣀⣄⣀⡀             ");
        System.out.println("                                                                                             ⡀⣀⣀⣤⠴⠖⠛⠋⠉⠸⣏⠙⠛⠛⠛⠛⠋⣹⠇⠉⠙⠛⠲⠦⣤⣀⣀      ");
        System.out.println("                                                                                             ⣿⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠳⢦⣤⣤⡴⠞⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⣿     ");
        System.out.println("                                                                                             ⣻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟     ");
        System.out.println("                                                                                             ⢿⣇⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣸⡇     ");
        System.out.println("                                                                                              ⠉⠉⠉⠉⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠉⠉⠉⠉      ");
        System.out.println("                                                                                                  ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           ");
        System.out.println("                                                                                                  ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           ");
        System.out.println("                                                                                                  ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           ");
        System.out.println("                                                                                                  ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           ");
        System.out.println("                                                                                                  ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           ");
        System.out.println("                                                                                                  ⠘⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠃           ");
        System.out.println();
    }

    /**
     * Função que imprime a imagem da mala
     */
    public void printMala(){
        clear();
        System.out.println();
        System.out.println(Apresentacao.CYAN + "                                                                                                      ⡤⠷⣿⣯⣽⣿⠶⢤           ");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⠀⠀⠀⠀⠀⠀⡴⢋⡴⠋⠉⠀⠀⠉⠙⢦⡙⢦⠀⠀⠀⠀⠀⠀⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⠀⠀⠀⠀⠀⣸⢡⡏⠀⠀⠀⠀⠀⠀⠀⠀⢹⡌⣇⠀⠀⠀⠀⠀⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⠀⠀⠀⠀⠀⣿⢸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⣿⠀⠀⠀⠀⠀⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⠀⠀⠀⠀⠀⣿⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿⠀⠀⠀⠀⠀⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⡤⠤⠤⠤⠤⣿⢸⠤⠤⠤⠤⠤⠤⠤⠤⠤⠤⡇⣿⠤⠤⠤⠤⢤⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⡏⠉⠉⠉⠉⣿⢾⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡷⣿⠉⠉⠉⠉⢹⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⡇⠀⠀⠀⠀⣿⢸⠀⠀⢠⣤⣤⣤⣤⡄⠀⠀⡇⣿⠀⠀⠀⠀⢸⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⡇⠀⠀⠀⠀⠛⠛⠀⠀⢸⡯⠅⠈⢽⡇⠀⠀⠛⠛⠀⠀⠀⠀⢸⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⠸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠇⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⠀");
        System.out.println(Apresentacao.CYAN + "                                                                                             ⠀⠀⠸⣄⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⠇⠀⠀");
        System.out.println();
    }

    /**
     * Função que imprime a imagem da sapatilha
     */
    public void printSapatilhas(){
        clear();
        System.out.println();
        System.out.println("                                                                                           ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⠟⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀    ");
        System.out.println("                                                                                           ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣴⣾⠿⢦⣴⠏⠀⠀⣴⢿⡄⠀⠀⠀⠀ ⠀    ");
        System.out.println("                                                                                           ⠀⠀⠀⢀⣀⣀⣤⣴⡶⢻⣏⠻⡦⠙⠇⠀⠉⠻⠶⠞⠫⡼⣧⠀⠀⠀⠀ ⠀    ");
        System.out.println("                                                                                          ⣴⠞⠛⠛⠋⠉⠉⠀⠀⠋⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⣦⡀⠀⠀⠀⠀ ⠀    ");
        System.out.println("                                                                                          ⣿⣷⡶⠶⣤⣤⣤⣤⣤⠶⠶⠶⠛⠛⠛⠛⢛⣠⣴⣾⣏⣀⡾⠁⠀⢀⣶⡄ ⠀    ");
        System.out.println("                                                                                           ⠉⠙⠛⠳⠶⠶⠶⣤⠀⠀⢀⣀⣤⣴⡾⢻⣍⠻⣦⠈⠙⢷⣤⣴⠟⣩⣷ ⠀    ");
        System.out.println("                                                                                           ⠀⠀⠀⢀⣴⠶⠶⠶⠚⠛⠋⠉⠻⠂⠙⠀⠉⠀⠀⠀⠀⠀⠀⣠⡞⠉⣿ ⠀    ");
        System.out.println("                                                                                           ⠀⠀⠀⣿⣧⣤⣤⣀⣀⣀⣀⣀⣀⣤⣤⣤⠶⠶⠶⠶⠶⠶⠶⠿⠶⢶⣏ ⠀    ");
        System.out.println("                                                                                           ⠀⠀⠀⠉⠛⠳⠶⢯⣭⣭⣍⣉⣉⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⡿ ⠀    ");
        System.out.println();
    }

    /**
     * Função que imprime a imagem "CONFIGURACOES"
     */
    public void printConfiguracoes() {
        clear();
        cyan();
        System.out.print("                                                                #############################################################################\n");
        System.out.print("                                                          #########################################################################################\n");
        System.out.print("                                                    #####################################################################################################\n");
        System.out.print("                                                    ░█████╗░░█████╗░███╗░░██╗███████╗██╗░██████╗░██╗░░░██╗██████╗░░█████╗░░█████╗░░█████╗░███████╗░██████╗\n");
        System.out.print("                                                    ██╔══██╗██╔══██╗████╗░██║██╔════╝██║██╔════╝░██║░░░██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔════╝\n");
        System.out.print("          ######################################### ██║░░╚═╝██║░░██║██╔██╗██║█████╗░░██║██║░░██╗░██║░░░██║██████╔╝███████║██║░░╚═╝██║░░██║█████╗░░╚█████╗░ #########################################\n");
        System.out.print("                                                    ██║░░██╗██║░░██║██║╚████║██╔══╝░░██║██║░░╚██╗██║░░░██║██╔══██╗██╔══██║██║░░██╗██║░░██║██╔══╝░░░╚═══██╗\n");
        System.out.print("                                                    ╚█████╔╝╚█████╔╝██║░╚███║██║░░░░░██║╚██████╔╝╚██████╔╝██║░░██║██║░░██║╚█████╔╝╚█████╔╝███████╗██████╔╝\n");
        System.out.print("                                                    ░╚════╝░░╚════╝░╚═╝░░╚══╝╚═╝░░░░░╚═╝░╚═════╝░░╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝░╚════╝░░╚════╝░╚══════╝╚═════╝░\n");
        System.out.print("                                                    #####################################################################################################\n");
        System.out.print("                                                          #########################################################################################\n");
        System.out.print("                                                                #############################################################################\n");
        System.out.println();
        System.out.println();
        resetColor();
    }

    /**
     * Função que imprime a imagem salta no tempo
     */
    public void printSaltaTempo() {
        clear();
        System.out.println();
        System.out.println();
        System.out.print("                                                                                      ⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇           \n");
        System.out.print("                                                                                      ⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠈⠛⠛⢻⡟⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⢻⣿⠛⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⢸⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⢿⣇⠀⠀⣀⣤⣤⣶⣶⣶⣶⣤⣤⣀⣀⠀⠀⣀⣠⣴⣶⠀⢰⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⠈⢿⣦⡀⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⣰⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⠀⠀⠙⢿⣦⡈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⣠⣾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣦⡀⠙⢿⣿⣿⣿⣿⣿⠟⢁⣴⡿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣦⡀⠹⣿⣿⡟⠁⣴⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⡧⠀⠛⠟⠁⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠃⠀⢠⣤⠀⠈⢿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠋⠀⠀⠀⢀⡀⠀⠀⠀⠙⠻⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⠀⠀⣠⣾⠿⠋⠀⠀⠀⠀⠀⠘⠋⠀⠀⠀⠀⠀⠈⠻⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⠀⣴⡿⠁⠀⠀⢀⣠⣴⣶⣿⣿⣿⣿⣶⣦⣄⡀⠀⠀⠈⢻⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⠀⣼⡟⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⢹⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⢰⣿⠁⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⢸⡿⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠀⠀⠀⢸⡇⠀⠀⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠇⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⢀⣤⣤⣼⣧⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣼⣿⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                                      ⠸⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem do imposto
     */
    public void printTax() {
        clear();
        System.out.println();
        System.out.println();
        System.out.print("                                                                                    ⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n");
        System.out.print("                                                                                    ⢸⣿⡏⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⣴⣶⣶⣶⣶⣶⣶⣶⣶⣶⠀⠀⢀⣶⣦⠀⠀⠀⠀⢠⣶⣦⡀⠀⠀⣰⣶⡆⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠈⠉⠉⠉⣿⣿⠉⠉⠉⠁⠀⠀⣾⣿⣿⣇⠀⠀⠀⠀⠹⣿⣷⣄⣾⣿⠟⠁⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣸⣿⡏⢿⣿⡄⠀⠀⠀⠀⠈⣿⣿⣿⡋⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⡀⠀⠀⢀⣾⣿⠿⣿⣷⡄⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⣿⣿⠋⠉⠉⠹⣿⡧⠀⢰⣿⡿⠋⠀⠈⢿⣿⡆⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠈⠀⠀⠀⠈⠀⠀⠀⠀⠀⠈⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⢠⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣦⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣴⣶⣾⣿⣷⣤⣤⣀⡈⠙⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠉⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⠀⠀⣠⣴⣿⣿⠿⠛⠛⠋⠉⠉⠛⠛⠿⢿⣿⣷⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠀⠀⣠⣾⣿⠟⠉⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠈⠛⢿⣿⣯⡉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⢀⣾⣿⠟⠁⠀⠀⠀⠀⢀⣀⣸⣿⣇⣀⣀⠀⠀⠀⠀⠀⠙⣿⣿⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⣾⣿⠋⠀⠀⠀⠀⠀⣾⣿⠿⢿⣿⡿⠿⣿⣿⡄⠀⠀⠀⠀⠈⢿⣿⡏⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                       ⣿⣿⡏⠀⠀⠀⠀⠀⢸⣿⣿⠀⢸⣿⡇⠀⠘⠿⠇⠀⠀⠀⠀⠀⠘⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⢠⣶⣶⣶⣶⣶⣶⣶⣶⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                       ⣿⣿⠃⠀⠀⠀⠀⠀⠈⣿⣿⣄⣸⣿⣧⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⢻⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠉⠉⠉⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                       ⣿⣿⠀⠀⠀⠀⠀⠀⠀⠈⠛⠿⢿⣿⡿⠿⢿⣿⡄⠀⠀⠀⠀⠀⠀⣸⣿⡇⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                       ⣿⣿⡇⠀⠀⠀⠀⠀⠀⣠⡀⠀⢸⣿⡇⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⣿⣷⡀⠀⠀⠀⠀⠈⣿⣿⣤⣼⣿⣧⣤⣼⣿⡇⠀⠀⠀⠀⠀⣼⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿\n");
        System.out.print("                                                                        ⠘⣿⣷⡄⠀⠀⠀⠀⠈⠛⠻⢿⣿⡿⠿⠛⠋⠀⠀⠀⠀⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n");
        System.out.print("                                                                        ⠀⠈⢿⣿⣦⣀⠀⠀⠀⠀⠀⠈⠛⠃⠀⠀⠀⠀⠀⢀⣴⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                         ⠀⠀⠙⠻⣿⣷⣦⣄⣀⡀⠀⠀⠀⠀⣀⣠⣤⣾⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.print("                                                                         ⠀⠀⠀⠀⠀⠉⠛⠿⢿⣿⣿⣿⣿⣿⡿⠿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem de alterar o tempo de devolução de uma encomenda
     */
    public void printReturnTime() {
        clear();
        System.out.println();
        System.out.println();
        System.out.print("                                                                                      ⠀⠀⠀⠀⠀  ⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣶⣾⣿⠿⠿⠿⠛⠛⠛⠿⠿⠿⣿⣷⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣶⣿⠿⠛⠉⣀⣠⣤⣴⣶⣶⣶⣶⣶⣦⣤⣄⣈⠉⠛⠿⣿⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⠟⢉⣠⣴⣾⡿⠿⠛⠋⠉⠉⢉⣉⡉⠉⠉⠛⠛⠿⣿⣷⣦⣄⠙⠻⣿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⢠⣾⡿⠋⢠⣴⣿⡿⠛⠁⠀⠀⠀⠀⠀⠀⠸⠿⠇⠀⠀⠀⠀⠀⠀⠈⠛⢿⣷⣦⡈⠙⢿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⣴⣿⠟⢀⣴⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠈⠻⣿⣦⡈⠻⣿⣆⠀⠀⠀⠀⠀⠀⠀   \n");
        System.out.print("                                                                                ⠀⠀⢀⣼⣿⠃⣠⣿⡿⠁⠀⢾⡷⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⢾⡷⠀⠘⢿⣷⡄⠙⣿⣧⠀⠀⠀⠀⠀⠀   \n");
        System.out.print("                                                                                ⠀⠀⣾⣿⠃⣰⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⡄⠘⣿⣧⠀⠀⠀⠀⠀   \n");
        System.out.print("                                                                                ⠀⣸⣿⠇⢠⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⡄⠸⣿⡆⠀⠀⠀⠀   \n");
        System.out.print("                                                                                ⠀⣿⡟⠀⣾⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣷⠀⢿⣿⠀⠀⠀⠀   \n");
        System.out.print("                                                                                ⢸⣿⡇⢠⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⡀⢸⣿⡄⠀⠀⠀   \n");
        System.out.print("                                                                                ⢸⣿⡏⢸⣿⡗⢰⣶⡆⠀⠀⠀⣶⣶⣶⣶⣶⣶⣶⣶⣿⣟⠛⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣶⠂⢸⣿⡇⢸⣿⡇⠀⠀⠀   \n");
        System.out.print("                                                                                ⢸⣿⡇⢸⣿⡇⠈⠉⠁⠀⠀⠀⠉⠉⠉⠉⠉⠉⠉⠉⠙⢿⣿⡿⠋⠀⠀⣤⣤⣤⣤⣤⣤⣤⣤⣤⣬⣭⣤⣼⣿⣧⣼⣿⡇⠀⠀⠀   \n");
        System.out.print("                                                                                ⠈⣿⣇⠀⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⢀⣾⡿⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠻⣿⣄⠀⠀   \n");
        System.out.print("                                                                                ⠀⢿⣿⡀⢹⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣟⣁⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣙⣿⣦⠀   \n");
        System.out.print("                                                                                ⠀⠘⣿⣧⠀⢿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡿⠿⠿⠿⠿⠿⠿⢿⣿⡿⠿⠿⣿⣿⠿⠿⠿⠿⠿⠿⠿⣿⡇   \n");
        System.out.print("                                                                                ⠀⠀⠘⣿⣧⠈⢻⣿⣄⠀⠀⣠⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣇⣀⣀⣀⣀⣀⣀⣸⣿⡇⠀⠀⣿⣿⣀⣀⣀⣀⣀⣀⣐⣿⡇   \n");
        System.out.print("                                                                                ⠀⠀⠀⠘⣿⣷⡀⠹⣿⣧⡀⠙⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡿⠿⠿⠿⠿⠿⠿⢿⣿⡇⠀⠀⣿⣿⠿⠿⠿⠿⠿⠿⠿⣿⡇   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠈⠻⣿⣦⡈⠻⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⣿⡇⠀⠀⠀⠀⠀⠀⢸⣿⣷⣿⣷⣿⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⠀⠙⢿⣿⣦⡈⠙⠿⣿⣶⣤⣀⡀⠀⠀⠀⠘⠛⠃⣿⡇⠀⠀⠀⠀⠀⠀⠘⠛⠋⠀⠈⠛⠛⠀⠀⠀⠀⠀⠀⠀⣿⡇   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⣷⣤⣀⠉⠛⠿⠿⣿⣷⣶⣶⣶⣶⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠿⣿⣶⣦⣤⣄⣀⣀⣉⣉⣉⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠛⠛⠿⠿⠿⠿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣾⣿⠃   \n");
        System.out.print("                                                                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠀⠀   \n");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Função que imprime a imagem "ENCOMENDAS"
     */
    public void printEncomendas() {
        clear();
        cyan();
        System.out.println("                                                                     #################################################################");
        System.out.println("                                                                ###########################################################################");
        System.out.println("                                                           #####################################################################################");
        System.out.println("                                                           ███████╗███╗░░██╗░█████╗░░█████╗░███╗░░░███╗███████╗███╗░░██╗██████╗░░█████╗░░██████╗");
        System.out.println("                                                           ██╔════╝████╗░██║██╔══██╗██╔══██╗████╗░████║██╔════╝████╗░██║██╔══██╗██╔══██╗██╔════╝");
        System.out.println("             ############################################# █████╗░░██╔██╗██║██║░░╚═╝██║░░██║██╔████╔██║█████╗░░██╔██╗██║██║░░██║███████║╚█████╗░ #############################################");
        System.out.println("                                                           ██╔══╝░░██║╚████║██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░██║╚████║██║░░██║██╔══██║░╚═══██╗");
        System.out.println("                                                           ███████╗██║░╚███║╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗██║░╚███║██████╔╝██║░░██║██████╔╝");
        System.out.println("                                                           ╚══════╝╚═╝░░╚══╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚══╝╚═════╝░╚═╝░░╚═╝╚═════╝░");
        System.out.println("                                                           #####################################################################################");
        System.out.println("                                                                ###########################################################################");
        System.out.println("                                                                     #################################################################");
        System.out.println();
        System.out.println();
        resetColor();
    }

    /**
     * Função que imprime a imagem "PENDENTES"
     */
    public void printPendentes() {
        clear();
        cyan();
        System.out.println("                                                                          #######################################################");
        System.out.println("                                                                     #################################################################");
        System.out.println("                                                                ###########################################################################");
        System.out.println("                                                                ██████╗░███████╗███╗░░██╗██████╗░███████╗███╗░░██╗████████╗███████╗░██████╗");
        System.out.println("                                                                ██╔══██╗██╔════╝████╗░██║██╔══██╗██╔════╝████╗░██║╚══██╔══╝██╔════╝██╔════╝");
        System.out.println("                  ############################################# ██████╔╝█████╗░░██╔██╗██║██║░░██║█████╗░░██╔██╗██║░░░██║░░░█████╗░░╚█████╗░ #############################################");
        System.out.println("                                                                ██╔═══╝░██╔══╝░░██║╚████║██║░░██║██╔══╝░░██║╚████║░░░██║░░░██╔══╝░░░╚═══██╗");
        System.out.println("                                                                ██║░░░░░███████╗██║░╚███║██████╔╝███████╗██║░╚███║░░░██║░░░███████╗██████╔╝");
        System.out.println("                                                                ╚═╝░░░░░╚══════╝╚═╝░░╚══╝╚═════╝░╚══════╝╚═╝░░╚══╝░░░╚═╝░░░╚══════╝╚═════╝░");
        System.out.println("                                                                ###########################################################################");
        System.out.println("                                                                     #################################################################");
        System.out.println("                                                                          #######################################################");
        System.out.println();
        System.out.println();
        resetColor();
    }

    /**
     * Função que imprime a imagem "EXPEDIDAS"
     */
    public void printExpedidas() {
        clear();
        cyan();
        System.out.println("                                                                              ###############################################");
        System.out.println("                                                                         #########################################################");
        System.out.println("                                                                    ###################################################################");
        System.out.println("                                                                    ███████╗██╗░░██╗██████╗░███████╗██████╗░██╗██████╗░░█████╗░░██████╗");
        System.out.println("                                                                    ██╔════╝╚██╗██╔╝██╔══██╗██╔════╝██╔══██╗██║██╔══██╗██╔══██╗██╔════╝");
        System.out.println("                      ############################################# █████╗░░░╚███╔╝░██████╔╝█████╗░░██║░░██║██║██║░░██║███████║╚█████╗░ #############################################");
        System.out.println("                                                                    ██╔══╝░░░██╔██╗░██╔═══╝░██╔══╝░░██║░░██║██║██║░░██║██╔══██║░╚═══██╗");
        System.out.println("                                                                    ███████╗██╔╝╚██╗██║░░░░░███████╗██████╔╝██║██████╔╝██║░░██║██████╔╝");
        System.out.println("                                                                    ╚══════╝╚═╝░░╚═╝╚═╝░░░░░╚══════╝╚═════╝░╚═╝╚═════╝░╚═╝░░╚═╝╚═════╝░");
        System.out.println("                                                                    ###################################################################");
        System.out.println("                                                                         #########################################################");
        System.out.println("                                                                              ###############################################");
        System.out.println();
        System.out.println();
        resetColor();
    }

    /**
     * Função que imprime a imagem "FINALIZADAS"
     */
    public void printFinalizadas() {
        clear();
        cyan();
        System.out.println("                                                                         ###########################################################");
        System.out.println("                                                                    #####################################################################");
        System.out.println("                                                               ###############################################################################");
        System.out.println("                                                               ███████╗██╗███╗░░██╗░█████╗░██╗░░░░░██╗███████╗░█████╗░██████╗░░█████╗░░██████╗");
        System.out.println("                                                               ██╔════╝██║████╗░██║██╔══██╗██║░░░░░██║╚════██║██╔══██╗██╔══██╗██╔══██╗██╔════╝");
        System.out.println("                 ############################################# █████╗░░██║██╔██╗██║███████║██║░░░░░██║░░███╔═╝███████║██║░░██║███████║╚█████╗░ #############################################");
        System.out.println("                                                               ██╔══╝░░██║██║╚████║██╔══██║██║░░░░░██║██╔══╝░░██╔══██║██║░░██║██╔══██║░╚═══██╗");
        System.out.println("                                                               ██║░░░░░██║██║░╚███║██║░░██║███████╗██║███████╗██║░░██║██████╔╝██║░░██║██████╔╝");
        System.out.println("                                                               ╚═╝░░░░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝╚══════╝╚═╝╚══════╝╚═╝░░╚═╝╚═════╝░╚═╝░░╚═╝╚═════╝░");
        System.out.println("                                                               ###############################################################################");
        System.out.println("                                                                    #####################################################################");
        System.out.println("                                                                         ###########################################################");
        System.out.println();
        System.out.println();
        resetColor();
    }

    /**
     * Função que imprime a imagem "DEVOLVIDAS"
     */
    public void printDevolvidas() {
        clear();
        cyan();
        System.out.println("                                                                         #########################################################");
        System.out.println("                                                                    ###################################################################");
        System.out.println("                                                               #############################################################################");
        System.out.println("                                                               ██████╗░███████╗██╗░░░██╗░█████╗░██╗░░░░░██╗░░░██╗██╗██████╗░░█████╗░░██████╗");
        System.out.println("                                                               ██╔══██╗██╔════╝██║░░░██║██╔══██╗██║░░░░░██║░░░██║██║██╔══██╗██╔══██╗██╔════╝");
        System.out.println("                 ############################################# ██║░░██║█████╗░░╚██╗░██╔╝██║░░██║██║░░░░░╚██╗░██╔╝██║██║░░██║███████║╚█████╗░ #############################################");
        System.out.println("                                                               ██║░░██║██╔══╝░░░╚████╔╝░██║░░██║██║░░░░░░╚████╔╝░██║██║░░██║██╔══██║░╚═══██╗");
        System.out.println("                                                               ██████╔╝███████╗░░╚██╔╝░░╚█████╔╝███████╗░░╚██╔╝░░██║██████╔╝██║░░██║██████╔╝");
        System.out.println("                                                               ╚═════╝░╚══════╝░░░╚═╝░░░░╚════╝░╚══════╝░░░╚═╝░░░╚═╝╚═════╝░╚═╝░░╚═╝╚═════╝░");
        System.out.println("                                                               #############################################################################");
        System.out.println("                                                                    ###################################################################");
        System.out.println("                                                                         #########################################################");
        System.out.println();
        System.out.println();
        resetColor();
    }

    /**
     * Função que imprime a imagem "ESTATISTICAS"
     */
    public void printEstatisticas(){
        clear();
        cyan();
        System.out.println("                                                                         ###################################################################");
        System.out.println("                                                                   #############################################################################");
        System.out.println("                                                               ########################################################################################");
        System.out.println("                                                               ███████╗░██████╗████████╗░█████╗░████████╗██╗░██████╗████████╗██╗░█████╗░░█████╗░░██████╗");
        System.out.println("                                                               ██╔════╝██╔════╝╚══██╔══╝██╔══██╗╚══██╔══╝██║██╔════╝╚══██╔══╝██║██╔══██╗██╔══██╗██╔════╝");
        System.out.println("                 ############################################# █████╗░░╚█████╗░░░░██║░░░███████║░░░██║░░░██║╚█████╗░░░░██║░░░██║██║░░╚═╝███████║╚█████╗░ #############################################");
        System.out.println("                                                               ██╔══╝░░░╚═══██╗░░░██║░░░██╔══██║░░░██║░░░██║░╚═══██╗░░░██║░░░██║██║░░██╗██╔══██║░╚═══██╗");
        System.out.println("                                                               ███████╗██████╔╝░░░██║░░░██║░░██║░░░██║░░░██║██████╔╝░░░██║░░░██║╚█████╔╝██║░░██║██████╔╝");
        System.out.println("                                                               ╚══════╝╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░░░╚═╝░░░╚═╝╚═════╝░░░░╚═╝░░░╚═╝░╚════╝░╚═╝░░╚═╝╚═════╝░");
        System.out.println("                                                               ########################################################################################");
        System.out.println("                                                                    #############################################################################");
        System.out.println("                                                                         ###################################################################");

    }

    /**
     * Função que imprime a imagem "FATURAS"
     */
    public void printFaturas() {
        clear();
        cyan();
        System.out.println("                                                                                   ######################################");
        System.out.println("                                                                              ################################################");
        System.out.println("                                                                         ##########################################################");
        System.out.println("                                                                         ███████╗░█████╗░████████╗██╗░░░██╗██████╗░░█████╗░░██████╗");
        System.out.println("                                                                         ██╔════╝██╔══██╗╚══██╔══╝██║░░░██║██╔══██╗██╔══██╗██╔════╝");
        System.out.println("                           ############################################# █████╗░░███████║░░░██║░░░██║░░░██║██████╔╝███████║╚█████╗░ #############################################");
        System.out.println("                                                                         ██╔══╝░░██╔══██║░░░██║░░░██║░░░██║██╔══██╗██╔══██║░╚═══██╗");
        System.out.println("                                                                         ██║░░░░░██║░░██║░░░██║░░░╚██████╔╝██║░░██║██║░░██║██████╔╝");
        System.out.println("                                                                         ╚═╝░░░░░╚═╝░░╚═╝░░░╚═╝░░░░╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░");
        System.out.println("                                                                         ##########################################################");
        System.out.println("                                                                              ################################################");
        System.out.println("                                                                                   ######################################");
        System.out.println();
        System.out.println();
        resetColor();
    }

    /**
     * Função que imprime a imagem do vendedor que mais faturou
     */
    public void printVendedorDinheiro(){
        clear();
        System.out.println();
        System.out.println("                                                                                             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈");
        System.out.println("                                                                                             ⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                             ⠀⠀⠀⢀⣴⣿⣿⡿⠟⠋⠉⠀⠀⠉⠉⠛⢿⣿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⢀⣾⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⣼⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠸⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⢀⣠⣾⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⣈⣻⣿⣿⣿⣿⣶⣶⣶⣶⣾⣿⣿⣿⣿⣁⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⣠⣴⣿⣿⣿⣿⡿⠿⠿⠿⠿⠿⠿⠿⠿⠿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⢠⣾⣿⣿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠻⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⣰⣿⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣬⣿⣿⣿⣿⣿⣿⣿⣶⣦⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⢠⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⣿⣿⡿⠿⠛⠛⠛⠛⠻⠿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⣼⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⡿⠛⠁⠀⠀⠀⣤⣤⡄⠀⠀⠀⠉⠻⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⠟⠀⠀⠀⠀⢀⣤⣿⣿⣧⣄⡀⠀⠀⠀⠈⢻⣿⣿⣆⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⣿⣿⣿⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣴⣿⣿⡏⠀⠀⠀⠀⢠⣿⣿⠟⠛⠿⣿⣿⠀⠀⠀⠀⠀⢻⣿⣿⡆⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠸⣿⣿⣤⣤⣄⣀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣧⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠙⠻⠿⠿⣿⣿⣷⡀⠀⠀⠀⠀⢀⣿⣿⡿⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⡆⠀⠀⠀⠀⢠⣤⣤⣀⣀⣀⣿⣿⠇⠀⠀⠀⠀⣸⣿⣿⡇⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⡄⠀⠀⠀⠀⠻⢿⣿⣿⣿⡿⠋⠀⠀⠀⠀⣠⣿⣿⡿⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣦⡀⠀⠀⠀⠀⢿⣿⡇⠀⠀⠀⠀⢀⣴⣿⣿⡟⠁⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣶⣤⣀⣀⡀⠀⢀⣀⣠⣤⣾⣿⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠛⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println();
    }

    /**
     * Função que imprime a imagem da transportadora que mais faturou
     */
    public void printTransportadoraDinheiro(){
        clear();
        System.out.println();
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣴⣶⣶⣆⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣿⡿⠟⠛⢉⡉⠙⠛⢿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⠟⠉⠀⣀⣴⣿⣷⣄⡀⠀⠈⢻⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⡏⠀⠀⣾⣿⡿⠛⠛⢿⣿⣦⠀⠀⢿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠙⢿⣿⣶⣶⣤⣛⠁⠀⠀⠘⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⡀⣿⣿⡄⠀⠀⣶⣷⡈⠉⠙⣿⣿⣧⠀⠀⣸⣿⡿⢀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⢸⣿⡿⠿⠇⠹⣿⣷⡀⠀⠙⠿⣿⣿⣿⣿⠿⠃⠀⢠⣿⣿⠃⠻⠿⣿⣿⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠙⢿⣿⣦⣀⠀⠈⠻⠟⠀⠀⣀⣴⣿⡿⠃⠀⠀⠀⣿⣿⣄⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⠙⠻⣿⣿⣶⣶⣶⣾⣿⣿⠿⠋⠀⠀⠀⠀⠀⣿⣿⡿⠿⠿⠿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⢰⣶⣶⣶⣾⣾⣿⣷⣶⣶⣶⣶⡆⠀⠀⠀⠈⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠘⣿⣿⡀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠉⠉⠉⠉⢹⣿⡏⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⢹⣿⣧⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⢠⣤⣤⣤⣤⣤⣤⣾⣿⣧⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⢻⣿⣧⣀⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠈⠛⠛⠋⠉⠉⠙⢻⣿⡟⠉⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣷⣦⣀⠀");
        System.out.println("                                                                                  ⠀⠀⢀⣀⣀⣀⣀⣸⣿⣇⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢻⣿⡇");
        System.out.println("                                                                                  ⠀⠀⠘⠿⠿⠿⢿⣿⣿⡿⠿⠿⠿⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⢸⣿⡇");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⢀⣴⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⣿⡿⢿⣿⣶⡀⠀⠀⢸⣿⡇");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⢸⣿⣇⣀⣠⣾⣿⠋⠀⠀⠀⠙⣿⣿⣄⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣾⣿⠋⠁⠀⠀⠈⣿⣿⣄⣀⣸⣿⡇");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠘⠿⠛⠛⢻⣿⣿⠀⠀⠀⠀⠀⣼⣿⡟⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠻⣿⣿⠀⠀⠀⠀⠀⣸⣿⡟⠛⠛⠿⠃");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣷⣤⣀⣠⣴⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣵⣤⣀⣠⣴⣿⡿⠁⠀⠀⠀⠀");
        System.out.println("                                                                                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⠿⠿⠛⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⠿⠿⠟⠋⠀⠀⠀⠀⠀⠀");
    }

    /**
     * Função que imprime a imagem das encomendas de um vendedor
     */
    public void printEncomendasVendedor(){
        clear();
        System.out.println();
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣤⣤⣤⣤⣤⣤⣤⣿⡇⠀⢀⡀⠀⢸⣿⣤⡄⠀⠀⠀⢠⣤⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠉⠉⠉⠉⠉⠉⠉⣿⡇⢠⣿⣿⡄⢸⣿⠉⠁⠀⠀⠀⠈⠉⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⣿⣷⣿⠏⠹⣿⣾⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀⠘⣿⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                              ⣿⡿⢿⣿⣀⣀⣀⣀⣀⣀⣀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⣀⣀⣀⣀⣀⣀⣀⣿⡿⢿⣿");
        System.out.println("                                                                              ⣿⡇⢸⣿⠛⠛⠛⠛⠛⠛⠻⣿⣾⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣷⣿⠟⠛⠛⠛⠛⠛⠛⣿⡇⢸⣿");
        System.out.println("                                                                              ⣿⡇⢸⣿⠀⠀⠀⠀⣶⣦⣀⠀⠉⠛⢿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⡿⠛⠉⠀⣀⣴⣶⠀⠀⠀⠀⣿⡇⢸⣿");
        System.out.println("                                                                              ⣿⡇⢸⣿⠀⠀⠀⠀⠀⠙⠻⢿⣶⣄⡀⣨⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣅⢀⣠⣶⡿⠟⠋⠀⠀⠀⠀⠀⣿⡇⢸⣿");
        System.out.println("                                                                              ⣿⣇⣸⣿⠿⣷⣦⣄⡀⠀⠀⠀⠉⠛⠿⠿⠿⠿⠿⠿⠿⠿⠿⣿⣿⣿⣿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠛⠉⠀⠀⠀⢀⣠⣴⣾⠿⣿⣇⣸⣿");
        System.out.println("                                                                              ⡟⠻⠛⠛⠀⠀⠉⠛⢿⣷⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣼⡿⢿⣧⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣶⡿⠛⠉⠀⠀⠛⠛⠛⠛");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠉⠀⠀⠉⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                               ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
    }

    /**
     * Função que imprime a imagem dos ganhos da Vintage
     */
    public void printVintageDinheiro(){
        clear();
        System.out.println();
        System.out.println("                                                    ⣤⣤⣶⣶⣿⣿⣿⣿⣿⣿⣶⣶⣤⣤                                                                                 ⣤⣤⣶⣶⣿⣿⣿⣿⣿⣿⣶⣶⣤⣤           ");
        System.out.println("                                             ⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⡿⠿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀                                                                    ⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⡿⠿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀");
        System.out.println("                                             ⠀⣠⣾⣿⣿⣿⠿⠛⠉⠀⠀⣶⣶⣶⡆⠀⠀⠉⠛⠿⣿⣿⣿⣷⣄⠀⠀⠀           ############################################             ⠀⣠⣾⣿⣿⣿⠿⠛⠉⠀⠀⣶⣶⣶⡆⠀⠀⠉⠛⠿⣿⣿⣿⣷⣄⠀⠀⠀");
        System.out.println("                                            ⡆⣴⣿⣿⣿⠟⠁⠀⠀⢀⣠⣤⣿⣿⣿⣧⣤⣀⠀⠀⠀⠈⠻⣿⣿⣿⣦⠀⠀        ##################################################         ⡆⣴⣿⣿⣿⠟⠁⠀⠀⢀⣠⣤⣿⣿⣿⣧⣤⣀⠀⠀⠀⠈⠻⣿⣿⣿⣦⠀⠀");
        System.out.println("                                            ⣽⣿⣿⣿⠃⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠘⣿⣿⣿⣧⠀     ########################################################      ⣽⣿⣿⣿⠃⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠘⣿⣿⣿⣧⠀");
        System.out.println("                                           ⣿⣿⣿⣿⠃⠀⠀⠀⢸⣿⣿⡟⠉⣿⣿⣿⡇⠙⣿⣿⣿⡄⠀⠀⠀⠘⣿⣿⣿⡇      ██╗░░░██╗██╗███╗░░██╗████████╗░█████╗░░██████╗░███████╗     ⣿⣿⣿⣿⠃⠀⠀⠀⢸⣿⣿⡟⠉⣿⣿⣿⡇⠙⣿⣿⣿⡄⠀⠀⠀⠘⣿⣿⣿⡇");
        System.out.println("                                           ⣿⣿⣿⡏⠀⠀⠀⠀⢸⣿⣿⣿⣦⣿⣿⣿⣇⣀⡀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿      ██║░░░██║██║████╗░██║╚══██╔══╝██╔══██╗██╔════╝░██╔════╝     ⣿⣿⣿⡏⠀⠀⠀⠀⢸⣿⣿⣿⣦⣿⣿⣿⣇⣀⡀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿");
        System.out.println("                                           ⣿⣿⣿⡇⠀⠀⠀⠀⠀⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⠀⠀⠀⠀⠀⢸⣿⣿⣿      ╚██╗░██╔╝██║██╔██╗██║░░░██║░░░███████║██║░░██╗░█████╗░░     ⣿⣿⣿⡇⠀⠀⠀⠀⠀⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⠀⠀⠀⠀⠀⢸⣿⣿⣿");
        System.out.println("                                           ⣿⣿⣿⣇⠀⠀⠀⠀⣀⣀⣀⡀⠀⣿⣿⣿⡟⠛⢿⣿⣿⡇⠀⠀⠀⠀⣸⣿⣿⣿      ░╚████╔╝░██║██║╚████║░░░██║░░░██╔══██║██║░░╚██╗██╔══╝░░     ⣿⣿⣿⣇⠀⠀⠀⠀⣀⣀⣀⡀⠀⣿⣿⣿⡟⠛⢿⣿⣿⡇⠀⠀⠀⠀⣸⣿⣿⣿");
        System.out.println("                                           ⣿⣿⣿⣿⡄⠀⠀⠀⢹⣿⣿⣷⣄⣿⣿⣿⣇⣀⣾⣿⣿⠇⠀⠀⠀⢠⣿⣿⣿⡇      ░░╚██╔╝░░██║██║░╚███║░░░██║░░░██║░░██║╚██████╔╝███████╗     ⣿⣿⣿⣿⡄⠀⠀⠀⢹⣿⣿⣷⣄⣿⣿⣿⣇⣀⣾⣿⣿⠇⠀⠀⠀⢠⣿⣿⣿⡇");
        System.out.println("                                            ⣻⣿⣿⣿⡄⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⢠⣾⣿⣿⡟⠀      ░░░╚═╝░░░╚═╝╚═╝░░╚══╝░░░╚═╝░░░╚═╝░░╚═╝░╚═════╝░╚══════╝      ⣻⣿⣿⣿⡄⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⢠⣾⣿⣿⡟⠀");
        System.out.println("                                             ⠻⣿⣿⣿⣦⡀⠀⠀⠀⠉⠙⣿⣿⣿⡟⠋⠉⠀⠀⠀⢀⣴⣿⣿⣿⡟⠀⠀     ########################################################       ⠻⣿⣿⣿⣦⡀⠀⠀⠀⠉⠙⣿⣿⣿⡟⠋⠉⠀⠀⠀⢀⣴⣿⣿⣿⡟⠀⠀");
        System.out.println("                                             ⠀⠙⢿⣿⣿⣿⣶⣤⣀⠀⠀⠛⠛⠛⠃⠀⠀⣀⣠⣶⣿⣿⣿⡿⠋⠀⠀⠀        ##################################################          ⠀⠙⢿⣿⣿⣿⣶⣤⣀⠀⠀⠛⠛⠛⠃⠀⠀⣀⣠⣶⣿⣿⣿⡿⠋⠀⠀⠀");
        System.out.println("                                             ⠀⠀⠀⠙⠿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣶⣾⣿⣿⣿⣿⣿⠿⠋⠀⠀⠀⠀⠀           ############################################             ⠀⠀⠀⠙⠿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣶⣾⣿⣿⣿⣿⣿⠿⠋⠀⠀⠀⠀⠀");
        System.out.println("                                             ⠀⠀⠀⠀⠀⠀⠉⠛⠿⠿⣿⣿⣿⣿⣿⣿⠿⠿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀                                                                    ⠀⠀⠀⠀⠀⠀⠉⠛⠿⠿⣿⣿⣿⣿⣿⣿⠿⠿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀");
    }

    /**
     * Função que imprime a imagem dos maiores vendedores/compradores num período de tempo
     */
    public void printCompradorVendedor(){
        clear();
        System.out.println();
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠀⠬⠤⠤⠤⢤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⠀⠀⢀⡬⣲⢒⠲⢤⡀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⠀⠀⠀⠀⠀⣠⠞⢉⢉⡕⠉⣠⠿⣄⠉⢊⢦⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⡠⠊⠀⠀⠀⠀⠀⠀⠸⠿⠿⠛⠋⠀⡖⠉⠉⣾⠀⠀⡾⣆⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀ ⠀⣀⡴⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣄⡠⡄⠽⢶⣊⡽⠀⣰⣿⠙⠀");
        System.out.println("                                                                                          ⠀⠀⠀ ⠀⢀⣠⣾⣏⠁⠳⣄⠀⠀⠀⠀⠀⠀⠀⢀⣠⠖⠉⢹⣗⢮⡳⠤⣯⣤⣾⠟⠁ ⠀");
        System.out.println("                                                                                          ⠀⠀⠀⢀⣴⣿⡋⢻⣿⣷⡀⠈⢳⡄⢀⣀⣀⠤⠖⠋⠀⠀⠀⠀⠻⠬⠽⠉⠉⡅⠀⠀⠀ ⠀");
        System.out.println("                                                                                          ⢀⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣦⣀⡽⠉⠀ ⠀⠀⠀ ⠀⠀⠀  ⠀⠀⠀ ⠀⠀⠀  ⠀");
        System.out.println("                                                                                          ⠈⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠗⠀⠀⠀ ⠀⠀⠀      ⠀⠀⠀⠀ ⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠙⠻⣿⣿⣿⣿⡿⠋⠁⠀⠀⠀⠀ ⠀⠀⠀          ⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀ ⠀⠈⠻⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣯⢿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀ ⠀⠀⠀ ⠀⠀⠀  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣯⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣿⣿⣟⡳⣄⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⡟⣧⢟⣷⣌⢳⡀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                          ⠀⠀⠀ ⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⡟⢿⣿⣏⣻⣫⡟⠿⡆⠹⡄⠀⠀ ⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⢰⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣴⠀⣼⡎⣉⣻⣿⡧⣽⢼⡇⠀⠈⢠⢿⠀⣀⣀⡀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⠛⣿⣿⠈⢹⠋⠉⠉⠉⠁⠈⠈⢹⣄⡠⠶⠋⠉⠀⢀⡇");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢸⠀⠀⠀⠶⠗⠲⠰⠚⠉⠀⠀⣀⡴⠚⠁⠀");
        System.out.println("                                                                                          ⠀⠀⠀ ⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢸⠀⠀⠀⠀⠀⠀⢀⣀⣠⠔⡋⠁⠀⠀ ⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠤⠼⠤⠔⠒⠒⠋⠉⡅⠀⠀⠀ ⠀⠀⠀ ⠀");
        System.out.println("                                                                                          ⠀⠀⠀⠀⠀⠘⠉⠉⠉⠉⠉⠉⡉⠉⠉⠉⡝⠀⠀⠀ ⠀⠀⠀  ⠀⠀⠀ ⠀⠀⠀⠀⠀ ");
        System.out.println();
    }

    /**
     * Função que imprime a imagem de uma fatura
     */
    public void printFatura() {
        clear();
        System.out.println();
        System.out.println();
        System.out.println("                                                                                             ⠀   ⣾⡿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⢿⣿⢿⡆");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⣿");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⢠⣤⣦⣄⠀⠀⠀⠀⠀⠀⠀⢸⡿⠿⠿");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠈⢉⣉⣉⣀⣀⣀⣀⣀⠀⠀⢸⡇⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣈⣿⠀⠀⠀⠈⠛⠛⠛⠛⠛⠻⠛⠀⠀⣻⡇⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠈⠛⠛⠛⠛⠛⠛⠛⠛⠃⠀⠀⣿⣧⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠠⠿⠿⠿⠿⠿⠿⠿⠿⠿⠂⠀⣿⡇⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡟⠀⠰⣶⣶⣶⣶⣶⣶⣶⣶⠈⠀⠀⣿⡇⠀⠀");
        System.out.println("                                                                                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⣤⣤⣤⣤⣤⣤⣤⣤⣤⡄⠀⣀⣿⠁⠀⠀");
        System.out.println("                                                                                       ⠀⠀⣀⣤⣤⣤⣤⣤⣤⣿⣥⣤⣤⣤⣤⣤⣤⣤⣤⡀⠀⠀⠀⢹⡿⠀⠀⠀");
        System.out.println("                                                                                       ⠀⣴⣿⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⣿⡏⢹⣿⠀⠀⠀⣿⡇⠀⠀⠀");
        System.out.println("                                                                                       ⠀⢺⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠘⣿⣀⢀⣼⡿⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠀⣸⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠟⠛⠛⠛⢛⠋⠀⠀⠀⠀⠀");
        System.out.println("                                                                                       ⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println();
    }

    /**
     * Função que imprime o início de um login
     */
    public void printMenuLogin()
    {
        printLogin();
        printMensagem("Insira o seu email:", 88, 1);
    }

    /**
     * Função que imprime erro ao carregar/guardar um ficheiro
     */
    public void printErroFicheiro(){
        printMensagem("ERRO AO CARREGAR/GUARDAR O FICHEIRO",86,2);
    }

    /**
     * Função que imprime o início de guardar o estado
     */
    public void printMenuGuardar()
    {
        printGuardar();
        cyan();
        System.out.println("                                                                                 Indique o nome do ficheiro que pretende guardar:");
        resetColor();
        System.out.print("                                                                                 ");
    }

    /**
     * Função que imprime uma mensagem centrada com uma determinada cor
     * @param mensagem Mensagem
     * @param cor Cor
     */
    public void printMensagemCentrada(String mensagem, int cor)
    {
        if (cor == 1) cyanBold();
        if (cor == 2) red();
        if (cor == 3) yellow();
        if (cor == 4) cyan();

        int x = 105 - ((mensagem.length())/2);
        for (int i = 0; i < x; i++)
        {
            System.out.print(" ");
        }
        System.out.print(mensagem);
        resetColor();
    }
    /**
     * Função que imprime "SIM" ou "NÃO"
     * @param x Número de espaços
     */
    public void printMensagemSimOuNao(int x) {
        System.out.println();
        printEspacos(x);
        System.out.print("1 - SIM");
        System.out.println();
        printEspacos(x);
        System.out.print("0 - NÃO");
        printClear(2);
        printEspacos(x+3);
    }

    /**
     * Função que imprime "SIM" ou "NÃO"
     * @param mensagem Número de espaços
     * @param x Número de espaços
     * @param cor Número de espaços
     */
    public void printMensagem(String mensagem, int x, int cor) {
        if (cor == 1) cyanBold();
        if (cor == 2) red();
        if (cor == 3) yellow();
        if (cor == 4) cyan();

        for (int i = 0; i < x; i++)
        {
            System.out.print(" ");
        }
        System.out.print(mensagem);
        resetColor();
    }

    /**
     * Função que imprime uma determinada mensagem de erro
     * @param x Identificador da mensagem
     */
    public void printMensagemErro(int x) {
        if (x == 1) {
            printMensagemCentrada("ERRO! APENAS PODE UTILIZAR DIGITOS",2);
            System.out.println();
            printMensagemCentrada("DESEJA TENTAR DE NOVO?",0);
            printMensagemSimOuNao(102);
        }
        if (x == 2) printMensagemCentrada("ERRO! APENAS PODE UTILIZAR CARACTERES",2);
        if (x == 3) {
            printMensagemCentrada("ERRO! APENAS VALORES POSITIVOS!!",2);
            System.out.println();
            printMensagemCentrada("DESEJA TENTAR DE NOVO?",0);
            printMensagemSimOuNao(102);
        }
    }

    /**
     * Função que imprime uma determinada mensagem com um localdate
     * @param mensagem Mensagem
     * @param x Número de espaços
     * @param cor Cor da mensagem
     * @param localdate Data
     */
    public void printMensagemLocaldate(String mensagem, int x, int cor, LocalDate localdate){
        if (cor == 1) cyanBold();
        if (cor == 2) red();
        if (cor == 3) yellow();
        if (cor == 4) cyan();

        for (int i = 0; i < x; i++)
        {
            System.out.print(" ");
        }
        System.out.print(mensagem + RESET + localdate);
    }

    /**
     * Função que imprime uma determinada mensagem com um valor de uma faturação
     * @param mensagem Mensagem
     * @param x Número de espaços
     * @param cor Cor da mensagem
     * @param faturacao Valor da faturação
     */
    public void printFaturacao(String mensagem, int x, int cor, double faturacao){
        if (cor == 1) cyanBold();
        if (cor == 2) red();
        if (cor == 3) yellow();
        if (cor == 4) cyan();

        for (int i = 0; i < x; i++)
        {
            System.out.print(" ");
        }
        System.out.print(mensagem + RESET + faturacao);
    }

    /**
     * Função que imprime uma determinada mensagem com um valor ganho
     * @param mensagem Mensagem
     * @param x Número de espaços
     * @param cor Cor da mensagem
     * @param ganho Valor da ganho
     */
    public void printGanhos(String mensagem, int x, int cor, double ganho){
        if (cor == 1) cyanBold();
        if (cor == 2) red();
        if (cor == 3) yellow();
        if (cor == 4) cyan();

        for (int i = 0; i < x; i++)
        {
            System.out.print(" ");
        }
        System.out.print(mensagem + RESET + ganho);
    }

    /**
     * Função que imprime uma mensagem centrada com enter para sair
     * @param mensagem Mensagem
     */
    public void printEnter(String mensagem)
    {
        resetColor();
        printMensagem(mensagem,88,3);
        System.out.println();
        System.out.println("                                                                                          Pressione enter para continuar...");
        System.out.println();
        System.out.print("                                                                                                         ");
    }
    /**
     * Função que imprime enter para sair
     * @param x Número de espaços
     */
    public void printEnterSair(int x){
        System.out.println();
        System.out.println();
        printMensagem("Pressione enter para sair...",x,0);
        System.out.println();
        System.out.print("                                                                                                        ");
    }

    /**
     * Função que imprime mensagem de input incorreto
     * @param p Identificador da imagem
     */
    public String printInputIncorreto(int p){


        String c;
        do {
            if (p == 0)
            {
                printAdicionaArtigoVenda();
            }
            if (p == 1){
                printTshirt();
            }
            if (p == 2){
                printMala();
            }
            if (p == 3){
                printSapatilhas();
            }
            if (p == 4){
                printMenuGuardar();
            }
            if (p == 5){
                printMenuCarregarEstado();
            }
            if (p == 6){
                printMenuAutomatizacao();
            }
            if (p == 7){
                printVendedorDinheiro();
            }
            if (p == 8){
                printCompradorVendedor();
            }
            printMensagemCentrada("INPUT INCORRETO!!", 2);
            printMensagemCentrada("DESEJA TENTAR NOVAMENTE?", 2);
            printMensagemSimOuNao(101);
            Scanner ler = new Scanner(System.in);
            c = ler.nextLine();
        }while (!c.equals("1") && !c.equals("0"));
        return c;
    }

    /**
     * Função que imprime várias opções
     * @param mensagem Mensagem
     * @param opcoes Opcões
     * @param e Número de espaços
     */
    public void printOpcoes(String mensagem, String[] opcoes, int e){
        printMensagem(mensagem,92,1);
        int i = 1;
        printClear(2);
        for (String s : opcoes) {
            printOpcao(i, s, e);
            i++;
        }
        printClear(1);

    }

    /**
     * Função que imprime o início do carregar estado
     */
    public void printMenuCarregarEstado()
    {
        printLoad();
        cyan();
        System.out.println("                                                                              Indique o nome do ficheiro estado que pretende carregar:");
        resetColor();
        System.out.print("                                                                              ");
    }

    /**
     * Função que imprime o início da automatização
     */
    public void printMenuAutomatizacao()
    {
        printBackup();
        cyan();
        System.out.println("                                                                           Indique o nome do ficheiro que pretende carregar a automatizacao:");
        resetColor();
        System.out.print("                                                                           ");
    }


    /**
     * Função que imprime o o perfil de um utilizador
     * @param utilizador Utilizador
     */
    public void printPerfil(Utilizador utilizador)
    {
        System.out.println(CYAN_BOLD + "                                                                                        ID: " + Apresentacao.RESET + utilizador.getId());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                        Nome: " + Apresentacao.RESET + utilizador.getNome());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                        Email: " + Apresentacao.RESET + utilizador.getEmail());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                        Morada: " + Apresentacao.RESET + utilizador.getMorada());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                        Nr. Fiscal: " + Apresentacao.RESET + utilizador.getNrFiscal());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(YELLOW + "                                                                                        Pressione enter para continuar..." + Apresentacao.RESET);
        System.out.println();
        System.out.print("                                                                                                       ");
    }

    /**
     * Função que imprime procurar uma transportadora
     */
    public void printProcuraTrans() {
        printTrans();
        printMensagem("Introduza a transportadora que pretende procurar:",77,3);
        printClear(1);
    }

    /**
     * Função que imprime todos os dados de uma transportadora
     * @param transportadora Transportadora
     */
    public void printDadosTransportadora(Transportadora transportadora) {
        String tipo;
        if (transportadora.getTipo() == 0) tipo = "NORMAL";
        else tipo = "PREMIUM";
        printTrans();
        System.out.println(CYAN_BOLD + "                                                                                      ID: " + RESET + transportadora.getId());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                      Nome: " + RESET + transportadora.getNome());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                      Email: " + RESET + transportadora.getEmail());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                      Morada: " + RESET + transportadora.getMorada());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                      Nr. Fiscal: " + RESET + transportadora.getNrFiscal());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                      Tipo: " + RESET + tipo);
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                      Margem de Lucro: " + RESET + transportadora.getMargemLucro());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                      Tempo de Expedição: " + RESET + transportadora.getTempoExpedicao());
        printClear(2);
        System.out.println(YELLOW + "                                                                                      Pressione enter para continuar..." + RESET);
        System.out.println();
        System.out.print("                                                                                                     ");
    }

    /**
     * Função que imprime procurar uma transportadora
     */
    public void printRunArtigosVendaCase0() {
        printAdicionaArtigoVenda();
        printArtigosVenda();
        printClear(4);
        printMensagemCentrada("ESCOLHA O ARTIGO QUE DESEJA VENDER OU PRESSIONE 4 PARA SAIR!",1);
        printClear(1);
        printEspacos(105);
    }

    /**
     * Função que imprime os tamanhos de uma tshirt
     */
    public void printTamanhosTshirt() {
        printMensagem("INDIQUE O TAMANHO",84,1);
        System.out.println(CYAN_BOLD + "                                                                                    3 - " + RESET + "XL");
        System.out.println(CYAN_BOLD + "                                                                                    2 - " + RESET + "L");
        System.out.println(CYAN_BOLD + "                                                                                    1 - " + RESET + "M");
        System.out.println(CYAN_BOLD + "                                                                                    0 - " + RESET + "S");
        printEspacos(84);
    }

    /**
     * Função que imprime os padrões de uma tshirt
     */
    public void printPadroesTshirt() {
        printMensagem("INDIQUE O PADRAO",84,1);
        System.out.println(CYAN_BOLD + "                                                                                    2 - " + RESET + "PALMEIRAS");
        System.out.println(CYAN_BOLD + "                                                                                    1 - " + RESET + "RISCAS");
        System.out.println(CYAN_BOLD + "                                                                                    0 - " + RESET + "LISA");
        printEspacos(84);
    }

    /**
     * Função que imprime o estado de um artigo
     */
    public void printEstadoArtigo() {
        printMensagem("INDIQUE O SEU ESTADO:",84,1);
        System.out.println(CYAN_BOLD + "                                                                                    1 - " + RESET + "NOVO");
        System.out.println(CYAN_BOLD + "                                                                                    0 - " + RESET + "USADO");
        printEspacos(84);
    }

    /**
     * Função que imprime o tipo de um artigo
     */
    public void printArtigoPremiumOuNormal() {
        printMensagem("INDIQUE O SEU TIPO",84,1);
        System.out.println(CYAN_BOLD + "                                                                                    1 - " + RESET + "PREMIUM");
        System.out.println(CYAN_BOLD + "                                                                                    0 - " + RESET + "NORMAL");
        System.out.println();
        printEspacos(88);
    }

    /**
     * Função que imprime o tipo de um cordao
     */
    public void printTipoCordao() {
        printMensagem("INDIQUE O TIPO DE CORDÃO", 84,1);
        System.out.println(CYAN_BOLD + "                                                                                    1 - " + RESET + "ATILHO");
        System.out.println(CYAN_BOLD + "                                                                                    0 - " + RESET + "CORDAO");
        System.out.println();
        printEspacos(88);
    }

    /**
     * Função que imprime a paginação
     * @param lista Listas de strings
     * @param quantidade Quantidade por página
     * @param paginaAtual Página atual
     * @param numPaginas Número de páginas
     * @param inicio Primeira página
     * @param fim ùltima página
     */
    public void paginateMenu(List<String> lista, int quantidade, int paginaAtual, int numPaginas, int inicio, int fim)
    {
            for (int i = inicio; i < fim; i++)
            {
                System.out.println(lista.get(i));
            }
            System.out.println("                                                                                                  Pag." + paginaAtual + " de " + numPaginas);
    }

    /**
     * Função que converte para uma string a paginação de uma transportadora
     * @param indice Posição da transportadora na lista de strings
     * @param transportadora Transportadora
     */
    public String showTransportadora(int indice, Transportadora transportadora)
    {
        return "                                                                               " + indice +") " + Apresentacao.CYAN_BOLD + "Nome: " + Apresentacao.RESET + transportadora.getNome() + Apresentacao.CYAN_BOLD + ", Margem de Lucro: " + Apresentacao.RESET + transportadora.getMargemLucro() + Apresentacao.CYAN_BOLD + ", Tipo: " + Apresentacao.RESET + (transportadora.getTipo() == 0 ? "Normal" : "Premium") + "\n";
    }

    /**
     * Função que converte para uma string uma tshirt e os seus dados
     * @param tshirt Tshirt
     * @param date Data atual do sistema
     */
    private String showTshirt(Tshirt tshirt, int date)
    {
        String tipo = "PREMIUM";
        int x = tshirt.getTransportadora().getTipo();
        if (x == 0) tipo = "NORMAL";
        return  (Apresentacao.CYAN + "                                                                                   ⢀⣀⣠⣀⡀⠀⠀⠀⠀⢀⣀⣄⣀⡀            "  + Apresentacao.RED + "[Artigo Tshirt]" + Apresentacao.RESET + "\n" +
                            Apresentacao.CYAN + "                                                                           ⡀⣀⣀⣤⠴⠖⠛⠋⠉⠸⣏⠙⠛⠛⠛⠛⠋⣹⠇⠉⠙⠛⠲⠦⣤⣀⣀       "  + Apresentacao.YELLOW + "Identificador: " + Apresentacao.RESET + tshirt.getId() + "\n" +
                            Apresentacao.CYAN + "                                                                           ⣿⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠳⢦⣤⣤⡴⠞⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⣿      "  + Apresentacao.YELLOW + "Descrição: " + Apresentacao.RESET + tshirt.getDescricao() + "\n" +
                            Apresentacao.CYAN + "                                                                           ⣻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟      "  + Apresentacao.YELLOW + "Marca: " + Apresentacao.RESET + tshirt.getMarca() + "\n" +
                            Apresentacao.CYAN + "                                                                           ⢿⣇⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣸⡇      "  + Apresentacao.YELLOW + "Preço Base: " + Apresentacao.RESET + tshirt.getPrecoBase() + "\n" +
                            Apresentacao.CYAN + "                                                                            ⠉⠉⠉⠉⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠉⠉⠉⠉       " + Apresentacao.YELLOW + "Correção Preço: " + Apresentacao.RESET + tshirt.getCorrecaoPreco() + "\n" +
                            Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Preço Final: " + Apresentacao.RESET + tshirt.getPrecoFinal(date) + "\n" +
                            Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Estado: " + Apresentacao.RESET + tshirt.estadoToString() + "\n" +
                            Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Transportadora: " + Apresentacao.RESET + tshirt.getTransportadora().getNome() + "\n" +
                            Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Tipo de Transportadora: " + Apresentacao.RESET + tipo + "\n" +
                            Apresentacao.CYAN + "                                                                                ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇           " + Apresentacao.YELLOW + "Tamanho: " + Apresentacao.RESET + tshirt.tamanhoToString() + "\n" +
                            Apresentacao.CYAN + "                                                                                ⠘⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠃           " + Apresentacao.YELLOW + "Padrão: " + Apresentacao.RESET + tshirt.padraoToString() + "\n\n");
    }

    /**
     * Função que converte para uma string uma mala e os seus dados
     * @param mala Mala
     * @param date Data atual do sistema
     */
    private String showMala(Mala mala, int date) {
        String tipo = "PREMIUM";
        int x = mala.getTransportadora().getTipo();
        if (x == 0) tipo = "NORMAL";
        return  (Apresentacao.CYAN + "                                                                                     ⡤⠷⣿⣯⣽⣿⠶⢤           "   + Apresentacao.RED +    "     [Artigo Mala]\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠀⠀⠀⠀⡴⢋⡴⠋⠉⠀⠀⠉⠙⢦⡙⢦⠀⠀⠀⠀⠀⠀⠀"   + Apresentacao.YELLOW + "       Identificador: " + Apresentacao.RESET + mala.getId() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠀⠀⠀⣸⢡⡏⠀⠀⠀⠀⠀⠀⠀⠀⢹⡌⣇⠀⠀⠀⠀⠀⠀"   + Apresentacao.YELLOW + "       Descrição: " + Apresentacao.RESET + mala.getDescricao() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠀⠀⠀⣿⢸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⣿⠀⠀⠀⠀⠀⠀"   + Apresentacao.YELLOW + "       Marca: " + Apresentacao.RESET + mala.getMarca() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠀⠀⠀⣿⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿⠀⠀⠀⠀⠀⠀"   + Apresentacao.YELLOW + "       Preço Base: " + Apresentacao.RESET + mala.getPrecoBase() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⡤⠤⠤⠤⠤⣿⢸⠤⠤⠤⠤⠤⠤⠤⠤⠤⠤⡇⣿⠤⠤⠤⠤⢤⠀"   + Apresentacao.YELLOW + "       Correção Preço: " + Apresentacao.RESET + (mala.getTipo() == 0 ? mala.getCorrecaoPreco() : mala.getValorizacaoPremium(date)) + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⡏⠉⠉⠉⠉⣿⢾⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡷⣿⠉⠉⠉⠉⢹⠀"   + Apresentacao.YELLOW + "       Preço Final: " + Apresentacao.RESET + mala.getPrecoFinal(date) + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⡇⠀⠀⠀⠀⣿⢸⠀⠀⢠⣤⣤⣤⣤⡄⠀⠀⡇⣿⠀⠀⠀⠀⢸⠀"   + Apresentacao.YELLOW + "       Estado: " + Apresentacao.RESET + mala.estadoToString() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⡇⠀⠀⠀⠀⠛⠛⠀⠀⢸⡯⠅⠈⢽⡇⠀⠀⠛⠛⠀⠀⠀⠀⢸⠀"   + Apresentacao.YELLOW + "       Transportadora: " + Apresentacao.RESET + mala.getTransportadora().getNome() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀"   + Apresentacao.YELLOW + "       Tipo de Transportadora: " + Apresentacao.RESET + tipo + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀"   + Apresentacao.YELLOW + "       Dimensão: " + Apresentacao.RESET + mala.getDimensao() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠇⠀"   + Apresentacao.YELLOW + "       Material: " + Apresentacao.RESET + mala.getMaterial() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⠀"   + Apresentacao.YELLOW + "       Data Lançamento: " + Apresentacao.RESET + mala.getAnoLancamento() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠸⣄⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⠇⠀⠀"   + Apresentacao.YELLOW + "       Tipo: " + Apresentacao.RESET + (mala.getTipo() == 0 ? "Normal" : "Premium") + "\n\n");
    }

    /**
     * Função que converte para uma string uma sapatilha e os seus dados
     * @param sapatilha Sapatilha
     * @param date Data atual do sistema
     */
    private String showSapatilha(Sapatilha sapatilha, int date) {
        String tipo = "PREMIUM";
        int x = sapatilha.getTransportadora().getTipo();
        if (x == 0) tipo = "NORMAL";
        return (Apresentacao.RED +    "                                                                                                             [Artigo Sapatilha]\n" +
                Apresentacao.YELLOW + "                                                                                                             Identificador: " + Apresentacao.RESET + sapatilha.getId() + "\n" +
                Apresentacao.YELLOW + "                                                                                                             Descrição: " + Apresentacao.RESET + sapatilha.getDescricao() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⠟⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀    "   + Apresentacao.YELLOW + "Marca: " + Apresentacao.RESET + sapatilha.getMarca() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣴⣾⠿⢦⣴⠏⠀⠀⣴⢿⡄⠀⠀⠀⠀ ⠀    "   + Apresentacao.YELLOW + "Preço Base: " + Apresentacao.RESET + sapatilha.getPrecoBase() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⢀⣀⣀⣤⣴⡶⢻⣏⠻⡦⠙⠇⠀⠉⠻⠶⠞⠫⡼⣧⠀⠀⠀⠀ ⠀    "   + Apresentacao.YELLOW + "Correção Preço: " + Apresentacao.RESET + (sapatilha.getTipo() == 0 ? sapatilha.getCorrecaoPreco() : sapatilha.getValorizacaoPremium(date)) + "\n" +
                Apresentacao.CYAN + "                                                                           ⣴⠞⠛⠛⠋⠉⠉⠀⠀⠋⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⣦⡀⠀⠀⠀⠀ ⠀    "   + Apresentacao.YELLOW + "Preço Final:: " + Apresentacao.RESET + sapatilha.getPrecoFinal(date) + "\n" +
                Apresentacao.CYAN + "                                                                           ⣿⣷⡶⠶⣤⣤⣤⣤⣤⠶⠶⠶⠛⠛⠛⠛⢛⣠⣴⣾⣏⣀⡾⠁⠀⢀⣶⡄ ⠀    "   + Apresentacao.YELLOW + "Estado: " + Apresentacao.RESET + sapatilha.estadoToString() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠉⠙⠛⠳⠶⠶⠶⣤⠀⠀⢀⣀⣤⣴⡾⢻⣍⠻⣦⠈⠙⢷⣤⣴⠟⣩⣷ ⠀    "   + Apresentacao.YELLOW + "Transportadora: " + Apresentacao.RESET + sapatilha.getTransportadora().getNome() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⢀⣴⠶⠶⠶⠚⠛⠋⠉⠻⠂⠙⠀⠉⠀⠀⠀⠀⠀⠀⣠⡞⠉⣿ ⠀    "   + Apresentacao.YELLOW + "Tipo de Transportadora: " + Apresentacao.RESET + tipo + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⣿⣧⣤⣤⣀⣀⣀⣀⣀⣀⣤⣤⣤⠶⠶⠶⠶⠶⠶⠶⠿⠶⢶⣏ ⠀    "   + Apresentacao.YELLOW + "Tamanho: " + Apresentacao.RESET + sapatilha.getTamanho() + "\n" +
                Apresentacao.CYAN + "                                                                            ⠀⠀⠀⠉⠛⠳⠶⢯⣭⣭⣍⣉⣉⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⡿ ⠀    "   + Apresentacao.YELLOW + "Tipo Cordão: " + Apresentacao.RESET +  (sapatilha.getTipoCordao() == 0 ? "Cordão" : "Atacadores") +  "\n" +
                Apresentacao.YELLOW + "                                                                                                             Cor: " + Apresentacao.RESET + sapatilha.getCor() + "\n" +
                Apresentacao.YELLOW + "                                                                                                             Data Lançamento: " + Apresentacao.RESET + sapatilha.getDataLancamento() + "\n" +
                Apresentacao.YELLOW + "                                                                                                             Tipo: " + Apresentacao.RESET + (sapatilha.getTipo() == 0 ? "Normal" : "Premium") + "\n\n");
    }

    /**
     * Função que converte para uma string um artigo
     * @param artigo Artigo
     * @param date Data atual do sistema
     */
    public String showArtigoString(Artigo artigo, int date)
    {
        if (artigo.getClass().getSimpleName().equals("Tshirt"))
        {
            return (showTshirt((Tshirt) artigo,date));
        }
        if (artigo.getClass().getSimpleName().equals("Mala")) {
            return (showMala((Mala) artigo, date));
        }
        if (artigo.getClass().getSimpleName().equals("Sapatilha")) {
            return (showSapatilha((Sapatilha) artigo, date));
        }
        else return "";
    }

    /**
     * Função que imprime um artigo
     * @param artigo Artigo
     * @param date Data atual do sistema
     */
    public void showArtigo(Artigo artigo, int date)
    {
        if (artigo.getClass().getSimpleName().equals("Tshirt"))
        {
            System.out.println(showTshirt((Tshirt) artigo,date));
        }
        if (artigo.getClass().getSimpleName().equals("Mala")) {
            System.out.println(showMala((Mala) artigo, date));
        }
        if (artigo.getClass().getSimpleName().equals("Sapatilha")) {
            System.out.println(showSapatilha((Sapatilha) artigo, date));
        }
    }

    /**
     * Função que imprime o estado de uma encomenda
     * @param estado Estado
     */
    public String estadoEncomendaString(int estado)
    {
        if (estado == Atributos.PENDENTE)
        {
            return "PENDENTES";
        }
        if (estado == Atributos.EXPEDIDA)
        {
            return "EXPEDIDAS";
        }
        if (estado == Atributos.FINALIZADA)
        {
            return "FINALIZADAS";
        }
        if (estado == Atributos.DEVOLVIDA)
        {
            return "DEVOLVIDAS";
        }
        return "";
    }

    /**
     * Função que converte para uma string uma fatura
     * @param fatura Fatura
     */
    public String showFatura(Fatura fatura) {
        StringBuilder string = new StringBuilder();
        if (fatura.getTipo() == Atributos.VENDA)
        {
            string.append(Apresentacao.CYAN_BOLD + "Fatura referente à Venda da encomenda: " + Apresentacao.RESET + fatura.getIdEncomenda() + Apresentacao.YELLOW + " | ");
            string.append(Apresentacao.CYAN_BOLD + "Valor dos Artigos: " + Apresentacao.RESET + fatura.getValorArtigos() + Apresentacao.YELLOW + " | ");

        }
        else
        {
            string.append(Apresentacao.CYAN_BOLD + "Fatura referente à Compra da encomenda: " + Apresentacao.RESET + fatura.getIdEncomenda() + Apresentacao.YELLOW + " | ");
            string.append(Apresentacao.CYAN_BOLD + "Valor dos Artigos: " + Apresentacao.RESET + fatura.getValorArtigos() + Apresentacao.YELLOW + " | ");
            string.append(Apresentacao.CYAN_BOLD + "Valor Taxas sobre Artigos Novos e Usados: " + Apresentacao.RESET + fatura.getValorTaxas() + Apresentacao.YELLOW + " | ");
            string.append(Apresentacao.CYAN_BOLD + "Valor Taxa de expedição: " + Apresentacao.RESET + fatura.getValorExpedicao() + Apresentacao.YELLOW + " | ");
        }
        string.append(Apresentacao.CYAN_BOLD + "Valor total: " + Apresentacao.RESET + fatura.getValorTotal() + Apresentacao.YELLOW + " | ");
        string.append(Apresentacao.CYAN_BOLD + "Data de Faturacao: " + Apresentacao.RESET + fatura.getDataFaturacao() + "\n");
        return string.toString();
    }

    /**
     * Função que converte para uma string as encomendas do menu estatísticas
     * @param encomenda Encomenda
     */
    public String showEncomendaEstatisticas(Encomenda encomenda)
    {
        StringBuilder string = new StringBuilder();
        string.append(Apresentacao.CYAN_BOLD + "ID Encomenda: " + Apresentacao.RESET + encomenda.getId() + Apresentacao.YELLOW + " | " +
                Apresentacao.CYAN_BOLD + "Artigos: " + Apresentacao.RESET);

        for(Artigo artigo: encomenda.getListaArtigos())
        {
            string.append(artigo.showArtigoLinha());
        }
        string.append(Apresentacao.YELLOW + "| " + Apresentacao.CYAN_BOLD + "Transportadora: " + Apresentacao.RESET + encomenda.getTransportadora().getNome() + Apresentacao.YELLOW + " | "
                + Apresentacao.CYAN_BOLD + "Vendedor: " + Apresentacao.RESET + encomenda.getVendedor() + Apresentacao.YELLOW + " | "
                + Apresentacao.CYAN_BOLD + "Comprador: " + Apresentacao.RESET + encomenda.getComprador() + Apresentacao.YELLOW + " | "
                + Apresentacao.CYAN_BOLD + "Preço: " + Apresentacao.RESET + encomenda.getPrecoFinal());
        return string.toString();
    }

    /**
     * Função que converte para uma string os dados de um utilizador
     * @param utilizador Utilizador
     */
    public String utilizadorLinha(Utilizador utilizador)
    {
        StringBuilder string = new StringBuilder();
        string.append(Apresentacao.CYAN_BOLD + "ID Utilizador: " + Apresentacao.RESET + utilizador.getId() + Apresentacao.YELLOW + " | ");
        string.append(Apresentacao.CYAN_BOLD + "Email Utilizador: " + Apresentacao.RESET + utilizador.getEmail() + Apresentacao.YELLOW + " | ");
        string.append(Apresentacao.CYAN_BOLD + "Nome: " + Apresentacao.RESET + utilizador.getNome() + Apresentacao.YELLOW + " | ");
        string.append(Apresentacao.CYAN_BOLD + "Morada: " + Apresentacao.RESET + utilizador.getMorada() + Apresentacao.YELLOW + " | ");
        string.append(Apresentacao.CYAN_BOLD + "Identificação Fiscal: " + Apresentacao.RESET + utilizador.getNrFiscal());
        return string.toString();
    }

    /**
     * Função que converte para uma string os dados de uma encomenda
     * @param encomenda Encomenda
     * @param tempoDevolucao Tempo de devolução de uma encomenda
     *
     */
    public String showEncomenda(Encomenda encomenda, int tempoDevolucao) throws EncomendaException {
        StringBuilder string = new StringBuilder();
        string.append(Apresentacao.CYAN_BOLD + "ID Encomenda: " + Apresentacao.RESET + encomenda.getId() + Apresentacao.YELLOW + " | " +
                Apresentacao.CYAN_BOLD + "Artigos: " + Apresentacao.RESET);

        for(Artigo artigo: encomenda.getListaArtigos())
        {
            string.append(artigo.showArtigoLinha());
        }
        string.append(Apresentacao.YELLOW + "| " + Apresentacao.CYAN_BOLD + "Transportadora: " + Apresentacao.RESET + encomenda.getTransportadora().getNome() + Apresentacao.YELLOW + " | "
                + Apresentacao.CYAN_BOLD + "Vendedor: " + Apresentacao.RESET + encomenda.getVendedor() + Apresentacao.YELLOW + " | "
                + Apresentacao.CYAN_BOLD + "Preço: " + Apresentacao.RESET + encomenda.getPrecoFinal());
        if (encomenda.getEstado() == Atributos.EXPEDIDA){
            string.append(Apresentacao.YELLOW + " | " + Apresentacao.CYAN_BOLD + "Data Prevista: " + Apresentacao.RESET + encomenda.getDataPrevistaEntrega() + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data da Atualizaçao de estado: " + Apresentacao.RESET + encomenda.getDataAtualizacao() + "\n");
        }
        else if (encomenda.getEstado() == Atributos.FINALIZADA){
            string.append(Apresentacao.YELLOW + " | " + Apresentacao.CYAN_BOLD + "Data Máxima para devolver: " + Apresentacao.RESET + encomenda.getDataDevolucao(tempoDevolucao) + Apresentacao.YELLOW + " | " +
                    Apresentacao.CYAN_BOLD + "Data da Atualizaçao de estado: " + Apresentacao.RESET + encomenda.getDataAtualizacao() + "\n");
        } else if (encomenda.getEstado() == Atributos.DEVOLVIDA) {
            string.append(Apresentacao.YELLOW + " | " + Apresentacao.CYAN_BOLD + "Data da Devolucao: " + Apresentacao.RESET + encomenda.getDataAtualizacao() + "\n");
        } else {
            string.append("\n");
        }

        return string.toString();
    }
}