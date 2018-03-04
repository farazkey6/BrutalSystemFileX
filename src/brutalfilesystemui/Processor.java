package brutalfilesystemui;

import java.io.File;

/**
 *
 * @author FaraZ
 */
public class Processor {

    Guide guide;
    int ID;
    File rehashFile = new File("C:\\Users\\FaraZ\\Documents\\NetBeansProjects\\Brutalfilesystemui\\rehash.txt");
    File backup = new File("C:\\Users\\FaraZ\\Documents\\NetBeansProjects\\Brutalfilesystemui\\backup.txt");
    FMInput meta = new FMInput(this);
    int density, volume;

    public Processor() {

        meta.instr();
        guide = new Guide(density, volume, this);
    }

    public void Import() {

        FDInput data = new FDInput(meta, meta.dataSize);
        data.instr();
    }

    public Record Export(String key) {

        int Hkey = Produce(key);
        System.out.println("Export Key = " + key);
        Record record = guide.Retrieve(Hkey, Integer.parseInt(key));
        return record;
    }

    public void Delete(String key) {

        int Hkey = Produce(key);
        while (Hkey >= guide.depth) {

            Hkey /= 2;
        }
        guide.pointers.get(Hkey).Delete(Integer.parseInt(key));
    }
    
    public void Backup(){
        
        Fclr clear = new Fclr(backup);
        clear.instr();
        guide.Backup();
    }

    public void ReHash() {

        Rehash rehash = new Rehash(rehashFile, meta, meta.dataSize);
        rehash.instr();
        Fclr clear = new Fclr(rehashFile);
        clear.instr();
    }

    public void Hash(String[] data, String[] metaData) {

        int Hkey = Produce(data[ID]);
        Record record = new Record(data, metaData, meta.dataSize, ID);

        guide.Store(Hkey, record);
    }

    public int Produce(String ID) {

        int t0 = 0, t1 = 0, t4 = 0, t7 = 0, t9 = 0;
        int key;
        String temp;

        for (int i = 0; i < ID.length(); i++) { //finds occurances

            if (ID.charAt(i) == '0') {
                t0++;
            } else if (ID.charAt(i) == '1') {
                t1++;
            } else if (ID.charAt(i) == '4') {
                t4++;
            } else if (ID.charAt(i) == '7') {
                t7++;
            } else if (ID.charAt(i) == '9') {
                t9++;
            }
        }

        temp = Integer.toBinaryString(t0) + Integer.toBinaryString(t1) + Integer.toBinaryString(t4) + Integer.toBinaryString(t7) + Integer.toBinaryString(t9);
        key = Integer.parseInt(temp);
        System.out.println("New key = " + temp);

        if (temp.length() > 6) {
            key /= 10;
        } else if (temp.length() < 6) {
            key *= 10;
        }

        temp = Integer.toString(key);
        key = Integer.parseInt(temp, 2);
        return key;
    }

    public void setID(int ID) {

        this.ID = ID;
    }

    public File getRehashFile() {

        return rehashFile;
    }
    
    public File getBackupFile(){
        
        return backup;
    }

    void setVolume(int volume) {

        this.volume = volume;
    }

    void setDensity(int density) {

        this.density = density;
    }
}
