import java.text.DecimalFormat;
import java.util.InputMismatchException;
public class Apresentacao
{
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String CYAN_BOLD = "\033[1;36m";   // Cyan Bold
    public static final String CYAN = "\033[0;36m";

    public static final int ECRA=42;

    public static String LINE =
            "#################################################################################################";

    public static void clear(int number){
        for (int i = 0; i < number; ++i) System.out.println();
    }

    public void line(int numeroDeLinhas){
        for (int i=0;i<numeroDeLinhas;i++) System.out.println(LINE);
    }

    public void cyan(){
        System.out.println("\033[0;36m");
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
        System.out.println("     "+CYAN_BOLD+i+")"+RESET+"  "+s);
    }

    public void printMenu(String[] opcoes){
        clear(ECRA);
        red();
        System.out.println("VINTAGE");
        //System.out.println("");
        //System.out.println("");
        //System.out.println("");
        //System.out.println("");
        //System.out.println("");

        resetColor();
        int number= ((ECRA - 18) - opcoes.length) / 2;
        int i=1;
        clear(number);
        for(String s: opcoes){
            printOpcao(i,s);
            i++;
        }
        cyan();
        clear(number);
        line(1);
        resetColor();
        System.out.println("     Opção pretendida: ");
    }

    public void printLogin() {
        clear(ECRA);

    }

}
