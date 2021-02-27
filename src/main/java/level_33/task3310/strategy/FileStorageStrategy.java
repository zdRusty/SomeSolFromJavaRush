package level_33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy{


    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long  DEFAULT_BUCKET_SIZE_LIMIT = 10;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private int size;
    private long maxBucketSize = 2*bucketSizeLimit;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public int hash(Long k){
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }
    public int indexFor(int hash, int length){
        return hash & (length-1);
    }

    public Entry getEntry(Long key){
        int hash = (key == null) ? 0 : hash((long)key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)].getEntry();
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.getKey()) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }
    public void resize(int newCapacity){
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
        setBucketSizeLimit(2*bucketSizeLimit);
    }
    public void transfer(FileBucket[] newTable){
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            if (src[j] != null) {
                Entry e = src[j].getEntry();
                src[j].remove();
                src[j] = null;
                do {
                    int i = indexFor(e.hash, newCapacity);
                    if (newTable[i]==null) newTable[i] = new FileBucket();
                    Entry next = e.next;

                    e.next = newTable[i].getEntry();
                    newTable[i].putEntry(e);
                    e = next;
                } while (e != null);
            }
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        if(table[bucketIndex]!=null) {
            Entry e = table[bucketIndex].getEntry();
            e.next = new Entry(hash, key, value, e);

        } else {
            table[bucketIndex] = new FileBucket();
            createEntry(hash, key, value, bucketIndex);
        }
        if(table[bucketIndex].getFileSize()>bucketSizeLimit) resize(2 * table.length);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        FileBucket[] tab = table;
        for (FileBucket fileBucket : tab) {
            if(fileBucket!=null) {
                for (Entry e = fileBucket.getEntry(); e != null; e = e.next)
                    if (value.equals(e.getValue()))
                        return true;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash((long)key.hashCode());
        int i = indexFor(hash, table.length);
        if(table[i]!=null) {
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.getKey()) == key || key.equals(k))) {
                    e.value = value;
                }
            }
        }else {
            addEntry(hash, key, value, i);
        }
    }

    @Override
    public Long getKey(String value) {
        for(FileBucket x: table){
            if(x!=null&&x.getEntry().value.equals(value)){
                return x.getEntry().key;
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        for(FileBucket x: table){
            if(x!=null&&x.getEntry().key.equals(key)){
                return x.getEntry().value;
            }
        }
        return null;
    }
}