package brutalfilesystemui;

import java.util.ArrayList;

/**
 *
 * @author FaraZ
 */
public class Bucket {

    final int density;
    final int volume;
    int p = 1;
    Processor processor;
    ArrayList<Block> blocks;

    public Bucket(int blockDensity, int volume, Processor processor) {

        this.density = blockDensity;
        this.volume = volume;
        this.processor = processor;
        blocks = new ArrayList();

        for (int i = 0; i < blockDensity; i++) {
            Block x = new Block(volume, processor);
            blocks.add(x);
        }
    }

    public void Store(Record record) {
        boolean done = false;
        int i = 0;

        if (record.getSize() <= volume) {
            if (record.getID() == Retrieve(record.getID()).getID()) {
                System.out.println("Updating...");
                Delete(record.getID());
            }
            while (i < density && !done) {
                System.out.println("Storing...");
                if (blocks.get(i).getVolume() >= blocks.get(i).getSize() + record.getSize()) {
                    blocks.get(i).Store(record);
                    done = true;
                    System.out.println("Stored in Block: " + i);
                } else {
                    i++;
                }
            }
            if (!done) {

                System.out.println("Flushing...");
                Flush();
                if (processor.guide.depth > p) {
                    System.out.println("Dividing...");
                    incP();
                    processor.guide.Divide(this);
                } else {
                    System.out.println("Extending");
                    processor.guide.Extend();
                }
                processor.ReHash();

                processor.guide.Store(processor.Produce(record.getIDS()), record);
            }

        }else{
            
            System.out.println("Error: Record is too big");
        }
    }

    public Record Retrieve(int ID) {

        boolean done = false;
        int i = 0;

        while (i < density && !done) {

            System.out.println("processing...");
            if (blocks.get(i).getSize() != 0) {

                if (blocks.get(i).Retrieve(ID).getID() != 0) {
                    done = true;
                    System.out.println("Record Found");
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }

        if (done) {

            System.out.println("Retrieved from block " + i);
            return blocks.get(i).Retrieve(ID);
        } else {
            String[] nulldata = new String[1], nullmeta = new String[1];
            nullmeta[0] = "ID";
            nulldata[0] = "0";
            Record record = new Record(nulldata, nullmeta, 1, 0);
            return record;
        }
    }

    public void Delete(int Hkey) {

        boolean found = false;
        int i = 0;

        System.out.println("Deleting...");
        while (i < density && !found) {

            if (blocks.get(i).getSize() != 0) {

                if (blocks.get(i).Retrieve(Hkey).getID() == Hkey) {

                    blocks.get(i).Delete(Hkey);
                    found = true;
                } else {
                    i++;
                }
            }
        }
        if (found) {

            blocks.get(i).Delete(Hkey);
        }
    }

    public void Flush() {

        for (int i = 0; i < density; i++) {

            blocks.get(i).Flush();
        }
    }

    public void Backup() {

        System.out.println("Backing up...Bucket");
        for (int i = 0; i < density; i++) {

            if (blocks.get(i).size != 0) {
                blocks.get(i).Backup();
            }
        }
    }

    public boolean InUse() {
        int i = 0;
        while (i < density) {

            if (blocks.get(i).getSize() != 0) {
                return true;
            }
            i++;
        }
        return false;
    }

    public int getP() {

        return p;
    }

    public void setP(int x) {

        p = x;
    }

    public void incP() {

        p += 1;
    }
}
