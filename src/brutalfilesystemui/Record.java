package brutalfilesystemui;

import javax.swing.JOptionPane;

/**
 *
 * @author FaraZ
 */
public class Record {

    String[] data;
    String[] metaData;
    int size = 0;
    int ID;
    int length;
    String temp;

    public Record(String[] data, String[] metaData, int length, int ID) {

        this.metaData = new String[length];
        this.data = new String[length];
        this.ID = ID;
        this.length = length;

        for (int i = 0; i < length; i++) {

            this.metaData[i] = metaData[i];
            this.data[i] = data[i];
            temp = data[i];
            size += temp.length();

        }
    }

    public int getSize() {

        return size;
    }

    public int getID() {

        return Integer.parseInt(data[ID]);
    }
    
    public String getIDS(){
        
        return data[ID];
    }

    public void Export() {

        String temp ;
        temp = "Result: \n";
        for (int i = 0; i < length; i++) {

            temp += metaData [i] + ": " + data[i] + "\n";
        }
        
        JOptionPane.showMessageDialog(null, temp);
    }
}
