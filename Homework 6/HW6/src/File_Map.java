import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
   ArrayList<String> fnames;
   ArrayList<List<Integer>> occurances;

   public File_Map() {
       fnames = new ArrayList<String>();
       occurances = new ArrayList<List<Integer>>();
   }

    @Override
    public int size() {
        return fnames.size();
    }

    @Override
    public boolean isEmpty() {
        return fnames.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return fnames.contains((String)key);
    }

    @Override
    public boolean containsValue(Object value) {
        return occurances.contains((List<Integer>)value);
    }

    @Override
    public Object get(Object key) throws NoSuchElementException {
        int index = fnames.indexOf(key);
        if(-1 == index) throw new NoSuchElementException("NoSuchElement");
        return occurances.get(index);
    }

    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
        int index = fnames.indexOf(key);
        if(-1 == index){
            fnames.add((String)key);
            List<Integer> list = new ArrayList<Integer>();
            list.add((Integer)value);
            occurances.add(list);
        }
        else {
            List<Integer> list = occurances.get(index);
            list.add((Integer)value);
        }
        return value;
    }

    @Override
    public Object remove(Object key) throws NoSuchElementException {
        int index = fnames.indexOf(key);
        if(-1 == index) throw new NoSuchElementException("NoSuchElement");
        fnames.remove(index);
        List<Integer> list = occurances.get(index);
        occurances.remove(index);
        return list;
    }

    @Override
    public void putAll(Map m) {
        Set<String> set = m.keySet();
        for (String key : set) {
            put(key, m.get(key));
        }
    }

    @Override
    public void clear() {
        fnames.clear();
        occurances.clear();
    }

    @Override
    public Set<String> keySet() {
        return new HashSet<String>(fnames);
    }

    @Override
    public Collection values() {
        return new ArrayList<List<Integer>>(occurances);
    }

    @Override
    public Set<Entry> entrySet() {
        Set<Entry> set = new HashSet<Entry>();
        for(int i = 0; i < fnames.size(); i++) {
            set.add(new FM_Entry(fnames.get(i), occurances.get(i)));
        }
        return set;
    }

    @Override
    public String toString() {
        return entrySet().toString();
    }

    static class FM_Entry implements Entry {

        private String key;
        private List<Integer> value;

        public FM_Entry(String k, List<Integer> v) {
            key = k;
            value = v;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object v) {
            value = (List<Integer>)v;
            return value;
        }

        @Override
        public String toString() {
            return "[" + key + "=>" + value.toString() + "]";
        }
    }
}
