package brutalfilesystemui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author FaraZ
 */
public class Rehash {

    InputStream input;
    File file;
    BufferedReader br;
    String data[];
    FMInput metaData;
    int dataSize;

    public Rehash(File file, FMInput metaData, int dataSize) {

        this.dataSize = dataSize;
        this.metaData = metaData;
        this.file = file;
        data = new String[dataSize];
    }

    public void instr() {

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

    private void Tokenizer(String s) {

        if (s != null) {
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
}
