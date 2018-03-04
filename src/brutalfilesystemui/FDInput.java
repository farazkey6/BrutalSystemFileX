package brutalfilesystemui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author FaraZ
 */
public class FDInput {

    InputStream input;
    File file;
    BufferedReader br;
    String data[];
    FMInput metaData;
    int dataSize;

    public FDInput(FMInput metaData, int dataSize) {

        this.dataSize = dataSize;
        this.metaData = metaData;
        data = new String[dataSize];
    }

    public void instr() {

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(fc);
        file = fc.getSelectedFile();
        
        
//        try{
//            input = new ObjectInputStream(new FileInputStream(file));
//        }catch (IOException ex){
//            
//        }
        try {
            br = new BufferedReader(new FileReader(file.getPath()));
        } catch (FileNotFoundException ex) {
        }

        String line;
        try {
            while ((line = br.readLine()) != null) {
                Tokenizer(line);
            }
        } catch (IOException ex) {
        }

    }

    public void Tokenizer(String s) {

        int n;
        int f = -1;
        for (int i = 0; i < dataSize; i++) {

            n = s.indexOf(";", f + 1);
            data[i] = s.substring(f + 1, n);
            f = n;
        }
        
        metaData.processor.Hash(data, metaData.meta);
    }
}
