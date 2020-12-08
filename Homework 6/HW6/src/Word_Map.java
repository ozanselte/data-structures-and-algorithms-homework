import java.util.*;

public class Word_Map implements Map<String, File_Map>, Iterable<String>
{

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];

    private Node first;
    private int size;

    public Word_Map() {
        clear();
    }

    @Override
    public Iterator iterator() {
        return new WM_Iterator(first);
    }

    static class WM_Iterator implements Iterator<String> {
        private Node ptr;

        WM_Iterator(Node node) {
            ptr = node;
        }

        @Override
        public boolean hasNext() {
            return null != ptr;
        }

        @Override
        public String next() throws IndexOutOfBoundsException {
            if(!hasNext()) {
                throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
            }
            String key = ptr.getKey();
            ptr = ptr.getNext();
            return key;
        }
    }

    static class Node {
        private String key;
        private File_Map value;
        private Node next;

        Node(String k, File_Map v) {
            setKey(k);
            setValue(v);
            setNext(null);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String k) {
            key = k;
        }

        public File_Map getValue() {
            return value;
        }

        public void setValue(File_Map v) {
            value = v;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            next = n;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key) {
        return null != get(key);
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) {
        Node temp = first;
        while(null != temp) {
            if(temp.getValue().equals(value)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    @Override
    public File_Map get(Object key) {
        int h = hash((String)key);
        while(true) {
            if(CURRCAP <= h) h %= CURRCAP;
            if(null == table[h]) {
                return null;
            }
            else if(table[h].getKey().equals(key)) {
                return table[h].getValue();
            }
            h++;
        }
    }

    @Override
    /*
    Use linear probing in case of collision
    * */
    public File_Map put(String key, File_Map value) {
        int h = hash(key);
        while(null != table[h]) {
            h++;
            if(CURRCAP <= h) {
                h -= CURRCAP;
            }
        }
        table[h] = new Node(key, value);
        table[h].setNext(first);
        first = table[h];
        size++;
        checkSize();
        return value;
    }

    @Override
    /*You do not need to implement remove function
    * */
    public File_Map remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {
        Set<String> set = m.keySet();
        for (String key : set) {
            put(key, (File_Map)m.get(key));
        }
    }

    @Override
    public void clear() {
        CURRCAP = INITCAP;
        this.table = new Node[CURRCAP];
        size = 0;
        first = null;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set<String> keySet() {
        Set<String> set = new HashSet<String>();
        Node temp = first;
        while(null != temp) {
            set.add(temp.getKey());
            temp = temp.getNext();
        }
        return set;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        Collection<File_Map> collection = new ArrayList<File_Map>();
        Node temp = first;
        while(null != temp) {
            collection.add(temp.getValue());
            temp = temp.getNext();
        }
        return collection;
    }

    @Override
    /*You do not need to implement entrySet function
     * */
    public Set entrySet() {
        return null;
    }

    private int hash(String key) {
        int mod = key.hashCode() % CURRCAP;
        return 0 <= mod ? mod : mod + CURRCAP;
    }

    private void checkSize() {
        if(LOADFACT <= size / (double)CURRCAP) {
            CURRCAP *= 2;
            table = new Node[CURRCAP];
            Node temp = first;
            first = null;
            size = 0;
            while(null != temp) {
                put(temp.getKey(), temp.getValue());
                temp = temp.getNext();
            }
        }
    }

    public void putEntry(String word, String fileName, Integer position) {
        File_Map fileMap;
        if(containsKey(word)) {
            fileMap = get(word);
        }
        else {
            fileMap = new File_Map();
            put(word, fileMap);
        }
        fileMap.put(fileName, position);
    }

    public List<Integer> getEntry(String word, String fileName) throws NoSuchElementException {
        File_Map fileMap = get(word);
        if(null == fileMap) throw new NoSuchElementException("NoSuchElement");
        return (List<Integer>)fileMap.get(fileName);
    }
}
