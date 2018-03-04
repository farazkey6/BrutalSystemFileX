/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalfilesystemui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author FaraZ
 */
public class Fclr {

    OutputStream os;
    File file;
    InputStream input;
    BufferedReader br;

    public Fclr(File file) {

        this.file = file;
    }

    public void instr() {

        try {
            br = new BufferedReader(new FileReader(file.getPath()));
        } catch (FileNotFoundException ex) {
        }
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
            }
            out2(line);
        } catch (IOException ex) {
        }

    }

    public void out2(String s) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("");

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }




    }
}
