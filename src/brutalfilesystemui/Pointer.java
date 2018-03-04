package brutalfilesystemui;

/**
 *
 * @author FaraZ
 */
public class Pointer {

    Bucket bucket;

    public Pointer(Bucket bucket) {

        this.bucket = bucket;
    }

    public void Store(Record record) {

        bucket.Store(record);
    }

    public Record Retrieve(int ID) {

        return bucket.Retrieve(ID);
    }

    public void Delete(int Hkey) {

        bucket.Delete(Hkey);
    }
    
    public void Backup(){
        
        bucket.Backup();
    }

    public void setBucket(Bucket bucket) {

        this.bucket = bucket;
    }
}
