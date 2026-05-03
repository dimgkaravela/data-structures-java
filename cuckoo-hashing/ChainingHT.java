import java.io.*;

public class ChainingHT<Key,Value> {

    private class Node {
        Key key;
        Value value;
        Node next;    // next node of linked list 
 
        Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int m;          // hash table size
    private Node[] T;       // hash table
    private int n;          // number of distinct items inserted

    // hash function
    private int hash(Key key) {
        return ((key.hashCode() & 0x7fffffff) % m);
    }

    // constructor: initialize empty hash table of size M
    ChainingHT(int M) {
        m = M;
        n = 0;
        T = new ChainingHT.Node[m];
    }
    
    public int words() {
        return n;
    }
    
    public double loadFactor() {
        return (double) 100 * n / m;
    }

    // insert key with associated value
    public void insert(Key key, Value value) {
		int k =hash(key);
		Node node = new Node(key,value,null);
		
        if (T[k] == null){
            T[k] = node;
			n++ ;
		}else {
            Node x = T[k];
			boolean flag=false;
            while(x.next!=null){
				if(x.key.equals(key)== true){
					x.value=value;
					flag=true;
				}
				
				x=x.next;
			}
			if(x.key.equals(key)== true){
				flag=true;
				x.value=value;
				}
			if(flag==false){
				x.next=node;
				n++ ;
			}
        }
		if (loadFactor() >  80) {
			    System.out.println("resize: "+m);
				resize(2 * m);
			}
			
    }
	private void resize(int size){
		Node[] oldT;
		oldT= new ChainingHT.Node[m];
		for(int i=0; i<m;i++){
			oldT[i]=T[i];
		}
		T = new ChainingHT.Node[size];
		int oldm=m;
		m=size;
		n=0;
		for(int i=0; i<oldm; i++){
			while(oldT[i]!=null){
				insert(oldT[i].key,oldT[i].value);
				oldT[i]=oldT[i].next;
			}
		}
		
	}

    // return the value associated with key
    public Value contains(Key key) {
		int k = hash(key);
		Node nodes=T[k];
		if(nodes!=null){
			while(!nodes.key.equals(key) && nodes.next!=null){
				nodes=nodes.next;
			}
			if (nodes.key.equals(key)){
				return nodes.value;
			}
		}
        return null; // change appropriately
    }

    // print hash table 
    void print() {
        System.out.println("");
        for (int j = 0; j < m; j++) {
            Node x = T[j];
            System.out.print("T[" + j + "] = " );
            while (x != null) {
                System.out.print("(" + x.key + "," + x.value + ") " );
                x = x.next;
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Hash Table with Chaining");
        
        int M = 3000; // initial hash table size
        ChainingHT T = new ChainingHT<String,Integer>(M);

        
        In.init();
        long startTime = System.currentTimeMillis();
        while (!In.empty()) {
            String s = In.getString();
            Integer count = (Integer) T.contains(s);
            if ( count != null ) {
                T.insert(s, count + 1);
            } else {
                T.insert(s, 1);
            }
        }
        //T.print();
        long endTime = System.currentTimeMillis();
        long chtTime = endTime - startTime;
        System.out.println("construction time = " + chtTime);
        
		System.out.println("load factor = " + T.loadFactor());

        System.out.println("number of words = " + T.words());
        
		
        System.out.println("contains 'and' " + T.contains("and") + " times");
        System.out.println("contains 'astonished' " + T.contains("astonished") + " times");
        System.out.println("contains 'boat' " + T.contains("boat") + " times");
        System.out.println("contains 'carol' " + T.contains("carol") + " times");
        System.out.println("contains 'city' " + T.contains("city") + " times");
        System.out.println("contains 'scrooge' " + T.contains("scrooge") + " times");
        System.out.println("contains 'the' " + T.contains("the") + " times");
        System.out.println("contains 'train' " + T.contains("train") + " times");
        System.out.println("contains 'wondered' " + T.contains("wondered") + " times");
        
        
        endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total running time = " + totalTime); 
    }
}
