package level_35.task3512;

public class Generator<T> {

    Class<T> tClass;

    public Generator (Class<T> tClass){
        this.tClass = tClass;
    }

    T newInstance() {
        try {
            return tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
