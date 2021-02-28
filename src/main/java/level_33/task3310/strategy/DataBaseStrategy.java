package level_33.task3310.strategy;

import java.util.Arrays;

public class DataBaseStrategy implements StorageStrategy{

    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final int DEFAULT_BUCKET_SIZE_LIMIT = 5;
    public DBBucket[] table = new DBBucket[DEFAULT_INITIAL_CAPACITY];
    private int bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;

    public DataBaseStrategy(){
        setTable(DEFAULT_INITIAL_CAPACITY);
    }

    public void setTable(int capacity){
        table = new DBBucket[capacity];
        for(int i=0;i<capacity;i++){
            table[i] = new DBBucket(i);
        }
    }

    public void setBucketSizeLimit(int bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public int hash(Long k){
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length){
        return hash & (length-1);
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        if(table[bucketIndex].getSize()>bucketSizeLimit) resize(2 * table.length);
    }

    public void resize(int newCapacity){
        Arrays.stream(table).forEach(x->x.setTableName("old_"+x.getTableName()));
        System.out.println(table[0].getTableName());
        DBBucket[] newTable = new DBBucket[newCapacity];
        DBBucket[] src = table;
        table = newTable;
        setTable(newCapacity);
        setBucketSizeLimit(2*bucketSizeLimit);
        transfer(src);
    }

    public void transfer(DBBucket[] src){
        for (int j = 0; j < src.length; j++) {
            while (!src[j].isEmpty()){
                Entry e = src[j].getEntry();
                System.out.println(e.value);
                put(e.key,e.value);
            }
            src[j].remove();
        }
    }

    public void removeAll(){
        for(DBBucket x: table){
            x.remove();
        }
    }

    @Override
    public boolean containsKey(Long key) {
        return Arrays.stream(table).anyMatch(x->x.containsKey(key));
    }

    @Override
    public boolean containsValue(String value) {
        return Arrays.stream(table).anyMatch(x->x.containsValue(value));
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash((long)key.hashCode());
        int i = indexFor(hash, table.length);
        addEntry(hash,key,value,i);
    }

    @Override
    public Long getKey(String value) {
        for(DBBucket x: table){
           return x.getKey(value);
        }
        return 0L;
    }

    @Override
    public String getValue(Long key) {
        for(DBBucket x: table){
            if(x.getValue(key)!=null) {
                return x.getValue(key);
            }
        }
        return null;
    }
}
