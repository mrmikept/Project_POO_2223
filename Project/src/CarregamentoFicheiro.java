import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CarregamentoFicheiro {
    
    public static void escreveFicheiro(Sistema sistema, String path){
        File ficheiro = new File(path);
        ficheiro.delete();
        ficheiro.createNewFile();
        ObjectOutputStream estado = new ObjectOutputStream(new FileOutputStream(ficheiro));
        estado.writeObject(sistema);
        estado.close();
    }

    public static Sistema carregaFicheiro(String path){
        Sistema sistema = new sistema();
        File ficheiro = new File(path);
        if(ficheiro.exists()){
            ObjectInputStream estado = new ObjectInputStream(new FileInputStream(ficheiro));
            sistema = (Sistema)estado.readObject();
            estado.close();
        }
        return sistema;
    }
}
