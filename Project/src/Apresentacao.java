import javax.swing.plaf.PanelUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;

public class Apresentacao
{
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String CYAN_BOLD = "\033[1;36m";   // Cyan Bold

    public static final String YELLOW = "\033[0;33m";
    public static final String CYAN = "\033[0;36m";
    public static final int ECRA=55;
    public static String LINE =
            "##############################";

    public static void clear(){
        System.out.print("\033\143");
        System.out.flush();
    }

    public static void printClear(int number) {
        for (int i = 0; i < number; ++i) System.out.println();
    }

    public void line(int numeroDeLinhas){
        for (int i=0;i<numeroDeLinhas;i++) System.out.println(LINE);
    }
    public void printEspacos(int x) {
        for (int i = 0; i< x; i++) System.out.print(" ");
    }

    public void cyan(){
        System.out.println("\033[0;36m");
    }
    public void cyanBold(){
        System.out.println("\033[1;36m");
    }
    public void yellow(){
        System.out.println("\033[0;33m");
    }

    /**
     * Texto a vermelho
     */
    public void red(){
        System.out.println("\033[0;31m");
    }

    /**
     * Volta a colocar a cor "normal"
     */
    public void resetColor(){
        System.out.println("\033[0m");
    }

    public void printOpcao(int i,String s){
        System.out.println("                                                                                       "+CYAN_BOLD+i+")"+RESET+"  "+s);
    }

    public void printMenu(String[] opcoes, int x, String nome){
        clear();

        if (x == 0) {
            printVintage();
        }

        if (x == 1) {
            printVintage();
            System.out.println("                                                                                           " + CYAN_BOLD +"Bem-vindo " + RESET + nome + "!!");
        }

        if (x==2) printConfiguracoes();

        if (x == 3) printEncomendas();

        if (x == 4) printEstatisticas();

        if (x == 5) printFaturas();

        int i = 1;
        printClear(3);
        for (String s : opcoes) {
            printOpcao(i, s);
            i++;
        }
        printClear(5);
        cyan();
        line(1);
        red();
        System.out.print("Opção pretendida:");
        resetColor();
    }

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

    public void printProcuraTrans() {
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
    }

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
    }

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
    }

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
    }

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

    public void printEncomendasVendedor(){
        clear();
        System.out.println();
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣤⣤⣤⣤⣤⣤⣤⣿⡇⠀⢀⡀⠀⢸⣿⣤⡄⠀⠀⠀⢠⣤⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠉⠉⠉⠉⠉⠉⠉⣿⡇⢠⣿⣿⡄⢸⣿⠉⠁⠀⠀⠀⠈⠉⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⣿⣷⣿⠏⠹⣿⣾⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀⠘⣿⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                        ⣿⡿⢿⣿⣀⣀⣀⣀⣀⣀⣀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⣀⣀⣀⣀⣀⣀⣀⣿⡿⢿⣿");
        System.out.println("                                                                                        ⣿⡇⢸⣿⠛⠛⠛⠛⠛⠛⠻⣿⣾⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣷⣿⠟⠛⠛⠛⠛⠛⠛⣿⡇⢸⣿");
        System.out.println("                                                                                        ⣿⡇⢸⣿⠀⠀⠀⠀⣶⣦⣀⠀⠉⠛⢿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⡿⠛⠉⠀⣀⣴⣶⠀⠀⠀⠀⣿⡇⢸⣿");
        System.out.println("                                                                                        ⣿⡇⢸⣿⠀⠀⠀⠀⠀⠙⠻⢿⣶⣄⡀⣨⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣅⢀⣠⣶⡿⠟⠋⠀⠀⠀⠀⠀⣿⡇⢸⣿");
        System.out.println("                                                                                        ⣿⣇⣸⣿⠿⣷⣦⣄⡀⠀⠀⠀⠉⠛⠿⠿⠿⠿⠿⠿⠿⠿⠿⣿⣿⣿⣿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠛⠉⠀⠀⠀⢀⣠⣴⣾⠿⣿⣇⣸⣿");
        System.out.println("                                                                                        ⡟⠻⠛⠛⠀⠀⠉⠛⢿⣷⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣼⡿⢿⣧⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣶⡿⠛⠉⠀⠀⠛⠛⠛⠛");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠉⠀⠀⠉⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                                                         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
    }

    public void printVintageDinheiro(){
        clear();
        System.out.println();
        System.out.println("                                                          ⣤⣤⣶⣶⣿⣿⣿⣿⣿⣿⣶⣶⣤⣤                                                                                 ⣤⣤⣶⣶⣿⣿⣿⣿⣿⣿⣶⣶⣤⣤           ");
        System.out.println("                                                   ⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⡿⠿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀                                                                    ⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⡿⠿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀");
        System.out.println("                                                   ⠀⣠⣾⣿⣿⣿⠿⠛⠉⠀⠀⣶⣶⣶⡆⠀⠀⠉⠛⠿⣿⣿⣿⣷⣄⠀⠀⠀           ############################################             ⠀⣠⣾⣿⣿⣿⠿⠛⠉⠀⠀⣶⣶⣶⡆⠀⠀⠉⠛⠿⣿⣿⣿⣷⣄⠀⠀⠀");
        System.out.println("                                                  ⡆⣴⣿⣿⣿⠟⠁⠀⠀⢀⣠⣤⣿⣿⣿⣧⣤⣀⠀⠀⠀⠈⠻⣿⣿⣿⣦⠀⠀        ##################################################         ⡆⣴⣿⣿⣿⠟⠁⠀⠀⢀⣠⣤⣿⣿⣿⣧⣤⣀⠀⠀⠀⠈⠻⣿⣿⣿⣦⠀⠀");
        System.out.println("                                                  ⣽⣿⣿⣿⠃⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠘⣿⣿⣿⣧⠀     ########################################################      ⣽⣿⣿⣿⠃⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠘⣿⣿⣿⣧⠀");
        System.out.println("                                                 ⣿⣿⣿⣿⠃⠀⠀⠀⢸⣿⣿⡟⠉⣿⣿⣿⡇⠙⣿⣿⣿⡄⠀⠀⠀⠘⣿⣿⣿⡇      ██╗░░░██╗██╗███╗░░██╗████████╗░█████╗░░██████╗░███████╗     ⣿⣿⣿⣿⠃⠀⠀⠀⢸⣿⣿⡟⠉⣿⣿⣿⡇⠙⣿⣿⣿⡄⠀⠀⠀⠘⣿⣿⣿⡇");
        System.out.println("                                                 ⣿⣿⣿⡏⠀⠀⠀⠀⢸⣿⣿⣿⣦⣿⣿⣿⣇⣀⡀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿      ██║░░░██║██║████╗░██║╚══██╔══╝██╔══██╗██╔════╝░██╔════╝     ⣿⣿⣿⡏⠀⠀⠀⠀⢸⣿⣿⣿⣦⣿⣿⣿⣇⣀⡀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿");
        System.out.println("                                                 ⣿⣿⣿⡇⠀⠀⠀⠀⠀⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⠀⠀⠀⠀⠀⢸⣿⣿⣿      ╚██╗░██╔╝██║██╔██╗██║░░░██║░░░███████║██║░░██╗░█████╗░░     ⣿⣿⣿⡇⠀⠀⠀⠀⠀⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⠀⠀⠀⠀⠀⢸⣿⣿⣿");
        System.out.println("                                                 ⣿⣿⣿⣇⠀⠀⠀⠀⣀⣀⣀⡀⠀⣿⣿⣿⡟⠛⢿⣿⣿⡇⠀⠀⠀⠀⣸⣿⣿⣿      ░╚████╔╝░██║██║╚████║░░░██║░░░██╔══██║██║░░╚██╗██╔══╝░░     ⣿⣿⣿⣇⠀⠀⠀⠀⣀⣀⣀⡀⠀⣿⣿⣿⡟⠛⢿⣿⣿⡇⠀⠀⠀⠀⣸⣿⣿⣿");
        System.out.println("                                                 ⣿⣿⣿⣿⡄⠀⠀⠀⢹⣿⣿⣷⣄⣿⣿⣿⣇⣀⣾⣿⣿⠇⠀⠀⠀⢠⣿⣿⣿⡇      ░░╚██╔╝░░██║██║░╚███║░░░██║░░░██║░░██║╚██████╔╝███████╗     ⣿⣿⣿⣿⡄⠀⠀⠀⢹⣿⣿⣷⣄⣿⣿⣿⣇⣀⣾⣿⣿⠇⠀⠀⠀⢠⣿⣿⣿⡇");
        System.out.println("                                                  ⣻⣿⣿⣿⡄⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⢠⣾⣿⣿⡟⠀      ░░░╚═╝░░░╚═╝╚═╝░░╚══╝░░░╚═╝░░░╚═╝░░╚═╝░╚═════╝░╚══════╝      ⣻⣿⣿⣿⡄⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⢠⣾⣿⣿⡟⠀");
        System.out.println("                                                   ⠻⣿⣿⣿⣦⡀⠀⠀⠀⠉⠙⣿⣿⣿⡟⠋⠉⠀⠀⠀⢀⣴⣿⣿⣿⡟⠀⠀     ########################################################       ⠻⣿⣿⣿⣦⡀⠀⠀⠀⠉⠙⣿⣿⣿⡟⠋⠉⠀⠀⠀⢀⣴⣿⣿⣿⡟⠀⠀");
        System.out.println("                                                   ⠀⠙⢿⣿⣿⣿⣶⣤⣀⠀⠀⠛⠛⠛⠃⠀⠀⣀⣠⣶⣿⣿⣿⡿⠋⠀⠀⠀        ##################################################          ⠀⠙⢿⣿⣿⣿⣶⣤⣀⠀⠀⠛⠛⠛⠃⠀⠀⣀⣠⣶⣿⣿⣿⡿⠋⠀⠀⠀");
        System.out.println("                                                   ⠀⠀⠀⠙⠿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣶⣾⣿⣿⣿⣿⣿⠿⠋⠀⠀⠀⠀⠀           ############################################             ⠀⠀⠀⠙⠿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣶⣾⣿⣿⣿⣿⣿⠿⠋⠀⠀⠀⠀⠀");
        System.out.println("                                                   ⠀⠀⠀⠀⠀⠀⠉⠛⠿⠿⣿⣿⣿⣿⣿⣿⠿⠿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀                                                                    ⠀⠀⠀⠀⠀⠀⠉⠛⠿⠿⣿⣿⣿⣿⣿⣿⠿⠿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀");
    }

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

    public void printMenuGuardar()
    {
        this.printGuardar();
        cyan();
        System.out.println("                                                               Indique o caminho para a pasta onde pertende guardar o estado (../\"ficheiro\"):");
        resetColor();
        System.out.print("                                                               ");
    }

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

    public void printMensagemSimOuNao(int x) {
        System.out.println();
        for (int i = 0; i < x; i++)
        {
            System.out.print(" ");
        }
        System.out.print("1-SIM");
        System.out.println();
        for (int i = 0; i < x; i++)
        {
            System.out.print(" ");
        }
        System.out.print("0-NAO");
        System.out.println();
        System.out.println();
    }

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

    public void printEnter(String mensagem)
    {
        printMensagemCentrada(mensagem,0);
        resetColor();
        System.out.println();
        System.out.println("                                                                                          Pressione enter para continuar...");
        System.out.println();
        System.out.print("                                                                                                         ");
    }

    public void printMenuCarregarEstado()
    {
        printLoad();
        cyan();
        System.out.println("                                                               Indique o caminho para a pasta de onde pertende carregar o estado (../\"ficheiro\"):");
        resetColor();
        System.out.print("                                                               ");
    }

    public void printMenuAutomatizacao()
    {
        printBackup();
        cyan();
        System.out.println("                                                               Indique o caminho para a pasta de onde pertende carregar de Automatização (../\"ficheiro\"):");
        resetColor();
        System.out.print("                                                               ");
    }
}


