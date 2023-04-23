import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CarregamentoFicheiro {
    
    public static void escreveFicheiro(Sistema sistema, String path) throws IOException{
        File ficheiro = new File(path);
        ficheiro.delete();
        ficheiro.createNewFile();
        ObjectOutputStream estado = new ObjectOutputStream(new FileOutputStream(ficheiro));
        estado.writeObject(sistema);
        estado.close();
    }

    public static Sistema lerFicheiro(String path) throws IOException, ClassNotFoundException{
        Sistema sistema = new Sistema();
        File ficheiro = new File(path);
        if(ficheiro.exists()){
            ObjectInputStream estado = new ObjectInputStream(new FileInputStream(ficheiro));
            sistema = (Sistema)estado.readObject();
            estado.close();
        }
        return sistema;
    }
}
