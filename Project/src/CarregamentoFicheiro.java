import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.Scanner;

public class CarregamentoFicheiro {
    
    public static void escreveFicheiro(Sistema sistema, String path) throws IOException, CarregamentoFicheiroException{
        String pasta = Paths.get(path).toAbsolutePath().getParent().toString();
        String pasta2 = pasta.replace("/src","/Estados");
        File dir = new File(pasta2);
        if (dir.exists()) {
            File ficheiro = new File(pasta2 + File.separator + path);
            ficheiro.delete();
            ficheiro.createNewFile();
            ObjectOutputStream estado = new ObjectOutputStream(new FileOutputStream(ficheiro));
            estado.writeObject(sistema);
            estado.close();
        }
        else {
            dir.mkdirs();
            File ficheiro = new File(pasta2 + File.separator + path);
            ficheiro.delete();
            ficheiro.createNewFile();
            ObjectOutputStream estado = new ObjectOutputStream(new FileOutputStream(ficheiro));
            estado.writeObject(sistema);
            estado.close();

        }
    }

    public static Sistema lerFicheiro(String path) throws IOException, ClassNotFoundException, CarregamentoFicheiroException{
        Sistema sistema = new Sistema();
        String pasta = Paths.get(path).toAbsolutePath().getParent().toString();
        String pasta2 = pasta.replace("/src","/Estados");
        File ficheiro = new File(pasta2 + File.separator + path);
        if(ficheiro.exists()){
            ObjectInputStream estado = new ObjectInputStream(new FileInputStream(ficheiro));
            sistema = (Sistema)estado.readObject();
            estado.close();
            return sistema;
        }
        else throw new CarregamentoFicheiroException("O ficheiro não existe!!");
    }
}
