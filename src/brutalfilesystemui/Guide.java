package brutalfilesystemui;

import java.util.ArrayList;

/**
 *
 * @author FaraZ
 */
public class Guide {

    int depth, density, volume;
    Processor processor;
    ArrayList<Pointer> pointers;
    ArrayList<Bucket> buckets;

    public Guide(int density, int volume, Processor processor) {

        depth = 1;
        pointers = new ArrayList();
        buckets = new ArrayList();
        this.processor = processor;
        this.density = density;
        this.volume = volume;

        for (int i = 0; i < Math.pow(2, depth); i++) {

            Bucket bucket = new Bucket(density, volume, processor);
            Pointer pointer = new Pointer(bucket);

            buckets.add(bucket);
            pointers.add(pointer);
        }
    }

    public void Store(int Hkey, Record record) {

        while (Hkey >= Math.pow(2, depth)) {

            Hkey /= 2;
        }
        System.out.println("Depth = " + Math.pow(2, depth));
        System.out.println("Store in Pointer: " + Hkey);
        pointers.get(Hkey).bucket.Store(record);
    }

    public Record Retrieve(int Hkey, int key) {

        while (Hkey >= Math.pow(2, depth)) {

            Hkey /= 2;
        }
        System.out.println("Retrieve from Pointer " + Hkey);
        return pointers.get(Hkey).Retrieve(key);
    }
    
    public void Backup(){
        
        for (int i = 0; i < pointers.size(); i++){
            
            pointers.get(i).Backup();
        }
    }

    public void Extend() {

        if (depth < 6) {
            incDepth();
            pointers.removeAll(pointers);
            buckets.removeAll(buckets);

            Bucket bucket0 = new Bucket(this.density, this.volume, processor);
            buckets.add(bucket0);
            Bucket bucket1 = new Bucket(this.density, this.volume, processor);
            buckets.add(bucket1);

            for (int i = 0; i < Math.pow(2, depth); i++) {

                Pointer pointer0 = new Pointer(bucket0);
                pointers.add(pointer0);
                Pointer pointer1 = new Pointer(bucket1);
                pointers.add(pointer1);
            }

            processor.ReHash();
        }else{
            
            System.out.println("Error: System Full!");
        }
    }

    public void Divide(Bucket bucket) {

        for (int i = 0; i < pointers.size(); i++) {

            if (pointers.get(i).bucket.equals(bucket)) {

                Bucket newBucket = new Bucket(density, volume, processor);
                pointers.get(i).setBucket(newBucket);
                pointers.get(i).bucket.setP(depth);
            }
        }
    }

    public void incDepth() {

            depth += 1;
    }
}
