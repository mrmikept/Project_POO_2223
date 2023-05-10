import javax.swing.plaf.PanelUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        if (x != 100)
        {
            clear();
        }

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
        System.out.println("                                        (1)                                                              (2)                                                                 (3)");
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
        System.out.println();
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
        System.out.println();
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
        System.out.println();
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

    public void printMenuLogin()
    {
        printLogin();
        printMensagem("Insira o seu email:", 88, 1);
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
        printEspacos(x);
        System.out.print("1 - SIM");
        System.out.println();
        printEspacos(x);
        System.out.print("0 - NAO");
        printClear(2);
        printEspacos(x+3);
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

    public void printEnter(String mensagem)
    {
        resetColor();
        System.out.println();
        System.out.println("                                                                                          Pressione enter para continuar...");
        System.out.println();
        System.out.print("                                                                                                         ");
    }

    public void printEnterSair(){
        System.out.println();
        System.out.println();
        System.out.println("                                                                                          Pressione enter para sair...");
        System.out.println();
        System.out.print("                                                                                                     ");
    }

    public void printOpcoes(String mensagem, String[] opcoes){
        printMensagem(mensagem,86,1);
        int i = 1;
        printClear(2);
        for (String s : opcoes) {
            printOpcao(i, s);
            i++;
        }
        printClear(1);

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

    public void printProcuraTrans() {
        printTrans();
        printMensagem("Introduza a transportadora que pretende procurar:",77,3);
        printClear(1);
        printEspacos(77);
    }

    public void printDadosTransportadora(Transportadora transportadora) {
        printTrans();
        System.out.println(CYAN_BOLD + "                                                                                                  Nome: " + RESET + transportadora.getNome());
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                      Margem Lucro: " + RESET + transportadora.getMargemLucro() + CYAN_BOLD +

                " | Tipo: " + RESET + transportadora.getTipo() +  CYAN_BOLD + " | Imposto: " + RESET + transportadora.getTaxasImpostos().getImposto() + CYAN_BOLD + " | Taxas: " + RESET + transportadora.getTaxasImpostos().getTaxaEncPequena() + CYAN_BOLD + " (Pequena)"+ RESET + ", " +  transportadora.getTaxasImpostos().getTaxaEncMedia() + CYAN_BOLD+ " (Média)"+ RESET + ", " +  transportadora.getTaxasImpostos().getTaxaEncGrande() + CYAN_BOLD + "(Grande)" + RESET);
        System.out.println();
        System.out.println(CYAN_BOLD + "                                                                                                Encomendas:" + RESET);
        System.out.println();
        System.out.println(YELLOW + "                                                                                      Pressione enter para continuar..." + Apresentacao.RESET);
        System.out.println();
        System.out.print("                                                                                                     ");
    }

    public void printRunArtigosVendaCase0() {
        printAdicionaArtigoVenda();
        printArtigosVenda();
        printClear(4);
        printMensagem("ESCOLHA O ARTIGO QUE DESEJA VENDER OU PRESSIONE 4 PARA SAIR!",76,1);
        printClear(1);
        printEspacos(105);
    }

    public void printTamanhosTshirt() {
        printMensagem("INDIQUE O TAMANHO",84,1);
        System.out.println(CYAN_BOLD + "                                                                                    3 - " + RESET + "XL");
        System.out.println(CYAN_BOLD + "                                                                                    2 - " + RESET + "L");
        System.out.println(CYAN_BOLD + "                                                                                    1 - " + RESET + "M");
        System.out.println(CYAN_BOLD + "                                                                                    0 - " + RESET + "S");
        printEspacos(84);
    }

    public void printPadroesTshirt() {
        printMensagem("INDIQUE O PADRAO",84,1);
        System.out.println(CYAN_BOLD + "                                                                                    2 - " + RESET + "PALMEIRAS");
        System.out.println(CYAN_BOLD + "                                                                                    1 - " + RESET + "RISCAS");
        System.out.println(CYAN_BOLD + "                                                                                    0 - " + RESET + "LISA");
        printEspacos(84);
    }

    public void printEstadoArtigo() {
        printMensagem("INDIQUE O SEU ESTADO:",84,1);
        System.out.println(CYAN_BOLD + "                                                                                    1 - " + RESET + "NOVO");
        System.out.println(CYAN_BOLD + "                                                                                    0 - " + RESET + "USADO");
        printEspacos(84);
    }
    
    public void printArtigoPremiumOuNormal() {
        printMensagem("INDIQUE O SEU TIPO",84,1);
        printMensagem("1 - PREMIUM",84,1);
        printMensagem("0 - NORMAL",84,1);
        System.out.println();
        printEspacos(88);
    }

    public void printTipoCordao() {
        printMensagem("INDIQUE O TIPO DE CORDÃO",84,1);
        printMensagem("1 - ATILHO",84,0);
        printMensagem("0 - CORDÃO",84,0);
        System.out.println();
        printEspacos(87);
    }

    public void paginateFaturas(List<Fatura> faturas, int pageSize, String email, int k, Sistema sistema) throws UtilizadorException, EncomendaException, TransportadoraException, ArtigoException, SistemaException {
        Utilizador utilizador = sistema.procuraUtilizador(email);

        if (faturas.isEmpty()) {
            System.out.println();
            System.out.println();
            printFatura();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(YELLOW + "                                                                                          NAO EXISTEM FATURAS !!" + RESET);
            System.out.println();
            System.out.println();
            System.out.println("                                                                                     Pressione enter para retroceder...");
            System.out.println();
            System.out.print("                                                                                                     ");
            Scanner ler = new Scanner(System.in);
            String c = ler.nextLine();
            return;
        }

        do {
            if (k == 0) {
                faturas = utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDIDO).collect(Collectors.toList());
                printCompras();
            }
            else {
                faturas = utilizador.getListaFaturas().stream().filter(fatura -> fatura.getTipo() == Atributos.VENDA).collect(Collectors.toList());
                printVendas();
            }

            Fatura[] menuItems = new Fatura[faturas.size()];
            faturas.toArray(menuItems);
            int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
            int currentPage = 1;
            int startIndex, endIndex;

            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            System.out.println(RED + "[FATURAS]\n" + RESET);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Fatura fatura = menuItems[i];
                System.out.println(fatura.showFatura());
            }
            System.out.println();
            System.out.println();
            System.out.println("                                                                                                 Pag." + currentPage + " de " + numPages);
            System.out.println();

            System.out.println(CYAN_BOLD + "                                                 Pressione" + RESET + " '+' " +
                    CYAN_BOLD + "para avancar," + RESET + " '-' " + CYAN_BOLD + "para a retroceder," + RESET + " 'v' " + CYAN_BOLD + "para ver um artigo de uma encomenda," + RESET + " 's' " + CYAN_BOLD +
                    "para sair" + RESET);
            System.out.println();
            System.out.print("                                                                                                      ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("+") && currentPage < numPages) {
                currentPage++;
            } else if (input.equals("-") && currentPage > 1) {
                currentPage--;
            } else if (input.equals("v")) {

                System.out.println();
                System.out.println(RED +"                                                                                INTRODUZA O ID DA ENCOMENDA QUE DESEJA VER"+ RESET);
                System.out.println();
                System.out.print("                                                                                                      ");

                int id = scanner.nextInt();

                Encomenda encomenda = sistema.procuraEncomenda(id);

                paginateArtigoEncomenda(encomenda, 2, email, id, sistema,1);
            }
            else if (input.equals("s")) {
                break;
            }

        } while (true);
    }

    public void paginateCompradorVendedor(List<Utilizador> lista, int pageSize ) throws ArtigoException, UtilizadorException, SistemaException, EncomendaException, TransportadoraException {
        Utilizador[] menuItems = lista.toArray(new Utilizador[0]);
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;
        do {
            printCompradorVendedor();
            System.out.println();
            System.out.println();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Utilizador utilizador = menuItems[i];
                System.out.println();
                System.out.println("                                                                                                        (" + key + ")");
                System.out.println();
                System.out.println(utilizador.toString());
            }

            if (numPages > 1) {
                System.out.println();
                System.out.println();
                System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
                System.out.println();
                System.out.println(CYAN_BOLD + "                                                                               Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar, " + RESET + " '-' " + CYAN_BOLD + "para a retroceder e " + RESET + " 's' " + CYAN_BOLD +
                        "para sair");
                resetColor();
                System.out.println();
                System.out.print("                                                                                                    ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;

                } else if (input.equals("s")) {
                    break;
                }
            } else if (numPages == 1) {
                System.out.println();
                System.out.println();
                System.out.println(YELLOW +"                                                                                               Pressione enter para sair..." + RESET);
                System.out.println();
                System.out.print("                                                                                                          ");

                Scanner scanner = new Scanner(System.in);
                String c = scanner.nextLine();
                break;
            }
        } while (true);
    }

    public void paginateMenuVendas(Map<String, Artigo> lista, int pageSize, String email, Sistema sistema) throws ArtigoException, UtilizadorException, TransportadoraException {
        Artigo[] menuItems = lista.values().toArray(new Artigo[0]);
        String c;
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;

        do {
            printVendas();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Artigo artigo = menuItems[i];
                showArtigo(artigo,sistema.getDataAtual().getYear());
            }

            if (numPages == 1){
                System.out.println();
                System.out.println();
                System.out.println(YELLOW + "                                                                                        Pressione enter para continuar..." + RESET);
                System.out.println();
                System.out.print("                                                                                                       ");
                Scanner ler = new Scanner(System.in);
                c = ler.nextLine();
                break;
            }

            if (numPages > 1) {
                System.out.println();
                System.out.println();
                System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
                System.out.println();
                System.out.println(CYAN_BOLD + "                                                           Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar," + RESET + " '-' " + CYAN_BOLD + "para a retroceder," + RESET + " 'r' " + CYAN_BOLD +
                        "para remover artigo e" + RESET + " 's' " + CYAN_BOLD + "para sair" + RESET);
                System.out.println();
                System.out.print("                                                                                                       ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("r")) {
                    System.out.println();
                    System.out.println(CYAN_BOLD +"                                                                                   INTRODUZA O ID DO ARTIGO QUE DESEJA REMOVER"+ RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");

                    String id = scanner.nextLine();

                    Utilizador utilizador = sistema.procuraUtilizador(email);
                    if(utilizador.getListaArtigos().containsKey(id)){
                        Artigo artigo = sistema.procuraArtigoVenda(id);
                        clear();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        showArtigo(artigo,sistema.getDataAtual().getYear());
                        System.out.println();
                        System.out.println();
                        System.out.println(CYAN_BOLD +"                                                                                           DESEJA REMOVER ESTE ARTIGO?"+ RESET);
                        sistema.removeArtigo(artigo);
                        System.out.println();
                        System.out.println("                                                                                                     1 - SIM");
                        System.out.println("                                                                                                     0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                        ");

                        int opcao = scanner.nextInt();
                        if (opcao == 1) {
                            sistema.removeArtigo(artigo);
                            System.out.println();
                            System.out.println(YELLOW +"                                                                                          ARTIGO REMOVIDO COM SUCESSO!" + RESET);
                            System.out.println();
                            System.out.println("                                                                                        Pressione enter para continuar...");
                            System.out.println();
                            System.out.print("                                                                                                       ");
                            Scanner ler = new Scanner(System.in);
                            c = ler.nextLine();
                            break;
                        }
                    }
                } else if (input.equals("s")) {
                    break;
                }
            }
        } while (true);
    }

    public void paginateMenuCompras(Map<String, Artigo> lista, int pageSize, String email, Sistema sistema) throws ArtigoException, UtilizadorException, TransportadoraException, EncomendaException, SistemaException {
        Artigo[] menuItems = lista.values().toArray(new Artigo[0]);
        String c;
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;
        do {
            printComprar();
            if (lista.isEmpty())
            {
                System.out.println("                                                                                        Não existem artigos para comprar!");
                System.out.println();
                System.out.println();
                System.out.println(YELLOW + "                                                                                        Pressione enter para continuar..." + RESET);
                System.out.println();
                System.out.print("                                                                                                       ");
                Scanner ler = new Scanner(System.in);
                c = ler.nextLine();
                break;
            }
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Artigo artigo = menuItems[i];
                showArtigo(artigo,sistema.getDataAtual().getYear());
            }

                System.out.println();
                System.out.println();
                System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
                System.out.println();
                System.out.println(CYAN_BOLD + "                                                           Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar," + RESET + " '-' " + CYAN_BOLD + "para a retroceder," + RESET + " 'c' " + CYAN_BOLD +
                        "para comprar artigo e" + RESET + " 's' " + CYAN_BOLD + "para sair" + RESET);
                System.out.println();
                System.out.print("                                                                                                       ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("c")) {

                    System.out.println();
                    System.out.println(CYAN_BOLD +"                                                                                   INTRODUZA O ID DO ARTIGO QUE DESEJA COMPRAR"+ RESET);
                    System.out.println();
                    System.out.print("                                                                                                       ");

                    String id = scanner.nextLine();

                    Artigo artigo = sistema.procuraArtigoVenda(id);
                    clear();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    showArtigo(artigo,sistema.getDataAtual().getYear());
                    System.out.println();
                    System.out.println();
                    System.out.println(CYAN_BOLD + "                                                                                           DESEJA COMPRAR ESTE ARTIGO?"+ RESET);
                    System.out.println();
                    System.out.println("                                                                                                     1 - SIM");
                    System.out.println("                                                                                                     0 - NAO");
                    System.out.println();
                    System.out.print("                                                                                                        ");

                    int opcao = scanner.nextInt();
                    if (opcao == 1) {
                        sistema.adicionaArtigoEncomenda(artigo, email);
                        System.out.println();
                        System.out.println(YELLOW +"                                                                                          ARTIGO COMPRADO COM SUCESSO!" + RESET);
                        System.out.println();
                        System.out.println();
                        System.out.println(CYAN_BOLD +"                                                                                           DESEJA COMPRAR OUTRO ARTIGO?"+ RESET);
                        System.out.println();
                        System.out.println("                                                                                                     1 - SIM");
                        System.out.println("                                                                                                     0 - NAO");
                        System.out.println();
                        System.out.print("                                                                                                        ");

                        int opcao2 = scanner.nextInt();
                        if (opcao2 == 1){
                            input.equals("c");
                        }
                        else if (opcao2 == 0) { break;}
                    }
                } else if (input.equals("s")) {
                    break;
                }

        } while (true);
    }

    public String paginateTransportadora(Map<String, Transportadora> lista, int pageSize, String email, Sistema sistema) throws TransportadoraException, UtilizadorException, ArtigoException {
        Transportadora[] menuItems = lista.values().toArray(new Transportadora[0]);
        int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
        int currentPage = 1;
        int startIndex, endIndex;
        do {
            printProcuraTrans();
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
                System.out.println(CYAN_BOLD + "                                                                               Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar e" + RESET + " '-' " + CYAN_BOLD + "para a retroceder ");
                System.out.println("                                                                              Para selecionar a transportadora, digite o nome dela");
                resetColor();
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
                    System.out.println(RED + "                                                        A TRANSPORTADORA COM O NOME "+ RESET + input + RED + " NÃO EXISTE" + RESET);
                    Scanner ler = new Scanner(System.in);
                    String c = ler.nextLine();
                    paginateTransportadora(sistema.getListaTransportadoras(), 1, email, sistema);
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
                    System.out.println(RED + "                                                        A TRANSPORTADORA COM O NOME "+ RESET + nomeTransportadora + RED + " NÃO EXISTE" + RESET);

                    Scanner ler = new Scanner(System.in);
                    String c = ler.nextLine();
                    paginateTransportadora(sistema.getListaTransportadoras(), 1, email, sistema);

                }
                else
                {
                    return nomeTransportadora;
                }
            }
        } while (true);
    }

    public void paginateEncomendas(List<Encomenda> encomendas, int pageSize, String email, Sistema sistema, int x) throws UtilizadorException, EncomendaException, TransportadoraException, ArtigoException, SistemaException {
        Utilizador utilizador = sistema.procuraUtilizador(email);

        if (encomendas.isEmpty()) {
            printClear(2);
            printBox();
            printClear(3);
            if (x==1) printMensagem("NAO EXISTEM ENCOMENDAS PENDENTES!!",88,3);
            if (x==2) printMensagem("NAO EXISTEM ENCOMENDAS EXPEDIDAS!!",88,3);
            if (x==3) printMensagem("NAO EXISTEM ENCOMENDAS FINALIZADAS!!",88,3);
            if (x==4) printMensagem("NAO EXISTEM ENCOMENDAS DEVOLVIDAS!!",88,3);
            printClear(2);
            printEnter("");
            Scanner ler = new Scanner(System.in);
            String c = ler.nextLine();
            return;
        }

        do {
            if (x==1) {encomendas = utilizador.getListaEncomendas(Atributos.PENDENTE); printPendentes();}
            if (x==2) {encomendas = utilizador.getListaEncomendas(Atributos.EXPEDIDA); printExpedidas();}
            if (x==3) {encomendas = utilizador.getListaEncomendas(Atributos.FINALIZADA); printFinalizadas();}
            if (x==4) {encomendas = utilizador.getListaEncomendas(Atributos.DEVOLVIDA); printDevolvidas();}

            Encomenda[] menuItems = new Encomenda[encomendas.size()];
            encomendas.toArray(menuItems);
            int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
            int currentPage = 1;
            int startIndex, endIndex;

            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            System.out.println(RED + "[ENCOMENDAS]\n" + RESET);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Encomenda encomenda = menuItems[i];
                System.out.println(showEncomenda(encomenda, sistema.getTempoDevolucao()));
            }
            printClear(2);
            System.out.println("                                                                                                 Pag." + currentPage + " de " + numPages);
            printClear(1);

            if (x==1) {

                System.out.println(CYAN_BOLD + "                             Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar," + RESET + " '-' " + CYAN_BOLD + "para a retroceder," + RESET + " 'c' " + CYAN_BOLD +
                        "para confirmar encomenda," + RESET + " 'r' " + CYAN_BOLD + "para ver/remover um artigo de uma encomenda," + RESET + " 's' " + CYAN_BOLD +
                        "para sair" + RESET);
                System.out.println();
                System.out.print("                                                                                                      ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("c")) {

                    System.out.println();
                    System.out.println(RED + "                                                                              INTRODUZA O ID DA ENCOMENDA QUE DESEJA CONFIRMAR" + RESET);
                    System.out.println();
                    System.out.print("                                                                                                      ");

                    int id = scanner.nextInt();

                    if (menuItems.length == 1) {
                        sistema.confirmaEncomenda(id, email);
                        printClear(2);
                        printBox();
                        printClear(3);
                        System.out.println(YELLOW + "                                                                                        ENCOMENDA CONFIRMADA COM SUCESSO!!");
                        printClear(2);
                        System.out.println("                                                                                        NAO EXISTEM ENCOMENDAS PENDENTES!!" + RESET);
                        printClear(2);
                        System.out.println("                                                                                        Pressione enter para retroceder...");
                        printClear(1);
                        System.out.print("                                                                                                        ");
                        Scanner ler = new Scanner(System.in);
                        String c = ler.nextLine();
                        break;
                    }
                    printClear(2);
                    System.out.println(YELLOW + "                                                                                      ENCOMENDA CONFIRMADA COM SUCESSO!!" + RESET);

                    sistema.confirmaEncomenda(id, email);

                    printClear(2);
                    System.out.println(CYAN_BOLD + "                                                                                      DESEJA CONFIRMAR MAIS ENCOMENDAS?" + RESET);
                    printClear(1);
                    System.out.println("                                                                                                   1 - SIM");
                    System.out.println("                                                                                                   0 - NAO");
                    printClear(1);
                    System.out.print("                                                                                                      ");

                    Scanner ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 1) {
                    } else break;
                } else if (input.equals("r")) {

                    printClear(1);
                    System.out.println(RED + "                                                                                INTRODUZA O ID DA ENCOMENDA QUE DESEJA EDITAR" + RESET);
                    printClear(1);
                    System.out.print("                                                                                                      ");

                    int id = scanner.nextInt();

                    Encomenda encomenda = sistema.procuraEncomenda(id);

                    paginateArtigoEncomenda(encomenda, 2, email, id, sistema,1);
                } else if (input.equals("s")) {
                    break;
                }
            }

            if (x==2) {

                System.out.println(CYAN_BOLD + "                                               Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar," + RESET + " '-' " + CYAN_BOLD + "para a retroceder," + RESET + "'v' " + CYAN_BOLD + "para ver os artigos de uma encomenda," + RESET + " 's' " + CYAN_BOLD +
                        "para sair" + RESET);
                System.out.println();
                System.out.print("                                                                                                      ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("v")) {
                    printClear(1);
                    System.out.println(RED +"                                                                                INTRODUZA O ID DA ENCOMENDA QUE DESEJA VER"+ RESET);
                    printClear(1);
                    System.out.print("                                                                                                      ");

                    int id = scanner.nextInt();

                    Encomenda encomenda = sistema.procuraEncomenda(id);

                    paginateArtigoEncomenda(encomenda, 2, email, id, sistema,2);
                }
                else if (input.equals("s")) {
                    break;
                }
            }

            if (x==3) {
                System.out.println(CYAN_BOLD + "                                 Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar," + RESET + " '-' " + CYAN_BOLD + "para a retroceder," + RESET + " 'd' " + CYAN_BOLD +
                        "para devolver encomenda," + RESET + " 'v' " + CYAN_BOLD + "para ver um artigo de uma encomenda," + RESET + " 's' " + CYAN_BOLD +
                        "para sair" + RESET);
                printClear(1);
                System.out.print("                                                                                                      ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("d")) {

                    printClear(1);
                    System.out.println(RED +"                                                                               INTRODUZA O ID DA ENCOMENDA QUE DESEJA DEVOLVER"+ RESET);
                    printClear(1);
                    System.out.print("                                                                                                      ");

                    int id = scanner.nextInt();

                    if (menuItems.length == 1) {
                        sistema.devolveEncomenda(email,id);
                        printClear(2);
                        printBox();
                        printClear(3);
                        System.out.println(YELLOW + "                                                                                        ENCOMENDA DEVOLVIDA COM SUCESSO!!");
                        printClear(2);
                        System.out.println("                                                                                        NAO EXISTEM ENCOMENDAS DEVOLVIDAS!!" + RESET);
                        printClear(2);
                        System.out.println("                                                                                        Pressione enter para retroceder...");
                        printClear(1);
                        System.out.print("                                                                                                        ");
                        Scanner ler = new Scanner(System.in);
                        String c = ler.nextLine();
                        break;
                    }
                    printClear(2);
                    System.out.println(YELLOW + "                                                                                      ENCOMENDA DEVOLVIDA COM SUCESSO!!" + RESET);

                    sistema.devolveEncomenda(email,id);

                    printClear(2);
                    System.out.println(CYAN_BOLD + "                                                                                      DESEJA DEVOLVER MAIS ENCOMENDAS?"+ RESET);
                    printClear(1);
                    System.out.println("                                                                                                   1 - SIM");
                    System.out.println("                                                                                                   0 - NAO");
                    printClear(1);
                    System.out.print("                                                                                                      ");

                    Scanner ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 1) {
                    }
                    else break;
                } else if (input.equals("v")) {

                    printClear(1);
                    System.out.println(RED +"                                                                                INTRODUZA O ID DA ENCOMENDA QUE DESEJA VER"+ RESET);
                    printClear(1);
                    System.out.print("                                                                                                      ");

                    int id = scanner.nextInt();

                    Encomenda encomenda = sistema.procuraEncomenda(id);

                    paginateArtigoEncomenda(encomenda, 2, email, id, sistema,2);
                }
                else if (input.equals("s")) {
                    break;
                }
            }

            if (x==4) {
                System.out.println(CYAN_BOLD + "                                                 Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar," + RESET + " '-' " + CYAN_BOLD + "para a retroceder," + RESET + RESET + " 'v' " + CYAN_BOLD + "para ver um artigo de uma encomenda," + RESET + " 's' " + CYAN_BOLD +
                        "para sair" + RESET);
                System.out.println();
                System.out.print("                                                                                                      ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("v")) {

                    printClear(1);
                    System.out.println(RED +"                                                                                INTRODUZA O ID DA ENCOMENDA QUE DESEJA EDITAR"+ RESET);
                    printClear(1);
                    System.out.print("                                                                                                      ");

                    int id = scanner.nextInt();

                    Encomenda encomenda = sistema.procuraEncomenda(id);

                    paginateArtigoEncomenda(encomenda, 2, email, id, sistema,2);
                }
                else if (input.equals("s")) {
                    break;
                }
            }

        } while (true);
    }

    public void paginateArtigoEncomenda(Encomenda encomenda, int pageSize, String email, int id_encomenda, Sistema sistema, int x) throws ArtigoException, UtilizadorException, SistemaException, EncomendaException, TransportadoraException {

        do {
            encomenda = sistema.procuraEncomenda(id_encomenda);
            List<Artigo> lista = encomenda.getListaArtigos();
            Artigo[] menuItems = new Artigo[lista.size()];
            lista.toArray(menuItems);
            int numPages = (int) Math.ceil((double) menuItems.length / pageSize);
            int currentPage = 1;
            int startIndex, endIndex;

            printBox();
            startIndex = (currentPage - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, menuItems.length);

            for (int i = startIndex; i < endIndex; i++) {
                int key = i + 1;
                Artigo artigo = menuItems[i];
                showArtigo(artigo,sistema.getDataAtual().getYear());
            }

            printClear(2);
            System.out.println("                                                                                                  Pag." + currentPage + " de " + numPages);
            printClear(1);

            if (x==1) {
                System.out.println(CYAN_BOLD + "                                               Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar," + RESET + " '-' " + CYAN_BOLD + "para a retroceder," + RESET +
                        " 'r' " + CYAN_BOLD + "para remover um artigo de uma encomenda," + RESET + " 's' " + CYAN_BOLD +
                        "para sair" + RESET);
                System.out.println();
                System.out.print("                                                                                                       ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("+") && currentPage < numPages) {
                    currentPage++;
                } else if (input.equals("-") && currentPage > 1) {
                    currentPage--;
                } else if (input.equals("r")) {
                    printClear(1);
                    System.out.println(RED + "                                                                                  INTRODUZA O ID DO ARTIGO QUE DESEJA REMOVER" + RESET);
                    printClear(1);
                    System.out.print("                                                                                                       ");

                    String id = scanner.nextLine();

                    if (menuItems.length == 1) {
                        sistema.removeArtigoEncomenda(id, email);
                        printBox();
                        printClear(3);
                        System.out.println(YELLOW + "                                                                                          ESTA ENCOMENDA FOI REMOVIDA!!" + RESET);
                        printClear(2);
                        System.out.println("                                                                                        Pressione enter para retroceder...");
                        printClear(1);
                        System.out.print("                                                                                                        ");
                        Scanner ler = new Scanner(System.in);
                        String c = ler.nextLine();
                        break;
                    }

                    printClear(2);
                    System.out.println(YELLOW + "                                                                                         ARTIGO REMOVIDO COM SUCESSO!!" + RESET);
                    printClear(1);

                    sistema.removeArtigoEncomenda(id, email);

                    printClear(1);
                    System.out.println(CYAN_BOLD + "                                                                                   DESEJA CONTINUAR A REMOVER MAIS ARTIGOS?" + RESET);
                    printClear(1);
                    System.out.println("                                                                                                    1 - SIM");
                    System.out.println("                                                                                                    0 - NAO");
                    printClear(1);
                    System.out.print("                                                                                                       ");

                    Scanner ler = new Scanner(System.in);
                    int op = ler.nextInt();

                    if (op == 0) break;
                } else if (input.equals("s")) {
                    break;
                }
            }

            if (x==2) {
                System.out.println(CYAN_BOLD + "                                                                      Pressione" + RESET + " '+' " +
                        CYAN_BOLD + "para avancar," + RESET + " '-' " + CYAN_BOLD + "para a retroceder," + RESET +
                        " 's' " + CYAN_BOLD +
                        "para sair" + RESET);
                printClear(1);
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

    public void showTshirt(Tshirt tshirt, int date) ///TODO VERIFICAR SE PODE SER PRIVATE
    {
        String tipo = "PREMIUM";
        int x = tshirt.getTransportadora().getTipo();
        if (x == 0) tipo = "NORMAL";
        System.out.println (Apresentacao.CYAN + "                                                                                   ⢀⣀⣠⣀⡀⠀⠀⠀⠀⢀⣀⣄⣀⡀            "  + Apresentacao.RED + "[Artigo Tshirt]" + Apresentacao.RESET + "\n" +
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

    public void showMala(Mala mala, int date) {
        String tipo = "PREMIUM";
        int x = mala.getTransportadora().getTipo();
        if (x == 0) tipo = "NORMAL";
        System.out.println (Apresentacao.CYAN + "                                                                                     ⡤⠷⣿⣯⣽⣿⠶⢤           "   + Apresentacao.RED +    "     [Artigo Mala]\n" +
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

    public void showSapatilha(Sapatilha sapatilha, int date) { //TODO VERIFICAR SE PODE SER PRIVATE
        String tipo = "PREMIUM";
        int x = sapatilha.getTransportadora().getTipo();
        if (x == 0) tipo = "NORMAL";
        System.out.println (Apresentacao.RED +    "                                                                                                             [Artigo Sapatilha]\n" +
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


    public void showArtigo(Artigo artigo, int date)
    {
        if (artigo.getClass().getSimpleName().equals("Tshirt"))
        {
            showTshirt((Tshirt) artigo,date);
        }
        if (artigo.getClass().getSimpleName().equals("Mala")) {
            showMala((Mala) artigo, date);
        }
        if (artigo.getClass().getSimpleName().equals("Sapatilha")) {
            showSapatilha((Sapatilha) artigo, date);
        }
    }

    public String showEncomenda(Encomenda encomenda, int tempoDevolucao) throws EncomendaException {
        StringBuilder string = new StringBuilder();
        string.append(Apresentacao.CYAN_BOLD + "ID Encomenda: " + Apresentacao.RESET + encomenda.getId() + Apresentacao.YELLOW + " | " +
                Apresentacao.CYAN_BOLD + "Artigos: " + Apresentacao.RESET);

        for(Artigo artigo: encomenda.getListaArtigos())
        {
            string.append(artigo.showArtigoLinha());
        }
        string.append(Apresentacao.YELLOW + "| " + Apresentacao.CYAN_BOLD + "Transportadora: " + Apresentacao.RESET + encomenda.getTransportadora().getNome() + Apresentacao.YELLOW + " | "
                + Apresentacao.CYAN_BOLD + "Vendedor: " + Apresentacao.RESET + encomenda.getVendedor().getNome() + Apresentacao.YELLOW + " | "
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


