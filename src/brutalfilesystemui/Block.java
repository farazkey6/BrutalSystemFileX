package brutalfilesystemui;

import brutalfilesystemui.Processor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author FaraZ
 */
public class Block {

    File rehash, backup;
    final int volume;
    int size;
    ArrayList<Record> records;

    public Block(int volume, Processor processor) {

        this.volume = volume;
        size = 0;
        records = new ArrayList<>();
        rehash = processor.getRehashFile();
        backup = processor.getBackupFile();

    }

    public void Store(Record record) {

        records.add(record);
        size += record.getSize();
    }

    public void Delete(int ID) {

        for (int i = 0; i < records.size(); i++) {

            if (records.get(i).getID() == ID) {

                size -= records.get(i).size;
                records.remove(i);
            }
        }
    }

    public Record Retrieve(int ID) {

        for (int i = 0; i < records.size(); i++) {
            System.out.println("looking for " + ID);
            if (records.get(i).getID() == ID) {

                return records.get(i);
            }else{
                i++;
            }
        }

        String[] nulldata = new String[1], nullmeta = new String[1];
        nullmeta[0] = "ID";
        nulldata[0] = "0";
        Record record = new Record(nulldata, nullmeta, 1, 0);
        return record;
    }

    public void Flush() {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(rehash, true));

            for (int i = 0; i < records.size(); i++) {

                for (int j = 0; j < records.get(i).length; j++) {


                    writer.write(records.get(i).data[j] + ";");
                }
                writer.newLine();
            }
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
    public void Backup() {

        System.out.println("Backing Up block");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(backup, true));

            for (int i = 0; i < records.size(); i++) {

                for (int j = 0; j < records.get(i).length; j++) {


                    writer.write(records.get(i).data[j] + ";");
                }
                writer.newLine();
            }
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

    public int getVolume() {

        return volume;
    }

    public int getSize() {

        return size;
    }
}
