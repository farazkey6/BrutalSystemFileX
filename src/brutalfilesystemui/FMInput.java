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
public class FMInput {

    InputStream input;
    File file;
    BufferedReader br;
    String[] metaData = new String[100], data = new String[100], meta;
    int n = 0;
    int volume;
    int density;
    int dataSize;
    Processor processor;
    FDInput dataObject;

    public FMInput(Processor processor) {

        this.processor = processor;
        dataSize = 0;
    }

    public void instr() {

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(fc);
        file = fc.getSelectedFile();

        try {
            input = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException ex) {
        }

        try {
            br = new BufferedReader(new FileReader(file.getPath()));
        } catch (FileNotFoundException ex) {
        }
        String line;
        try {

            while ((line = br.readLine()) != null) {
                metaData[n] = line;
                n += 1;
            }

            volume = Integer.parseInt(metaData[0]);
            density = Integer.parseInt(metaData[1]);
            dataSize = n - 3;

            processor.setDensity(density);
            processor.setVolume(volume);
            
            meta = new String[dataSize];

            for (int i = 0; i < dataSize; i++) {

                meta[i] = metaData[i + 3];
                if (meta[i].equals("ID")) {

                    processor.setID(i);
                }
            }

        } catch (IOException ex) {
        }

    }
}
