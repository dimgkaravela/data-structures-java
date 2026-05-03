//NIKOLETA TOUROUNOGLOU AM:5106
//DIMITRA CHRISTINA GKARAVELA AM:5051
import java.io.*;
import java.util.Arrays;

public class CuckooHT{

    private int m;    // hash table size
    private String[] T1;  // hash table 1
	private String[] T2;   // hash table 2
	private int n; 		// number of Strings inserted
	private double loadFactorThreshold;
	private int maxSwaps;
	private int[] cont;
	
	public int words(){
		return n;
	}
    
	public CuckooHT(int size,double loadFactorThreshold, int maxSwaps){
        this.m = size;
        this.T1 = new String[size];
		this.T2 = new String[size];
		n = 0;
		this.loadFactorThreshold = loadFactorThreshold;
		this.maxSwaps = maxSwaps;
		this.cont = new int[1000000];
		
    }
	
	//HASH 
	private int hash1(String key){
        return (Math.abs(key.hashCode()) % m);
    }
	private int hash2(String  key){
        return ((Math.abs(key.hashCode()/m)) % m);
    }
	//LOAD FACTOR 
	public double loadFactor(){
		return (double) 100*n/m;
	}
	
	
  
	//INSERT
	public void insert(String key){
		if(key==null){
			return;
		}
		if(search(key)){ // if its already inside
			//n++;
			return;
		}
		if(loadFactor() > loadFactorThreshold){
			rehash(2*m);
		}
		int swaps = 0;
		String temp;
		int g = hash1(key);
		int j = hash2(key);
		while(swaps< maxSwaps){
			if(T1[g]==null){ 
				T1[g] = key;
				cont[g]++;
				n++;
				return;
			}
			temp = key; 
			key = T1[g];
			T1[g] = temp;
			j = hash2(key); 
			if(T2[j]==null){
				T2[j] = key;	
				cont[j]++;
				n++;
				return;
			}
			temp = key;
			key = T2[j];
			T2[j] = temp;
			g = hash1(key);
			swaps++;
			if(swaps == maxSwaps){
				//System.out.println("Too many swaps");
				return;
			}
		}
	}
	public void insert(String key, String[] newT1, String[] newT2){
		int g = hash1(key);
		int j = hash2(key);
		
		if(newT1[g]==null){
			newT1[g] = key;
			n++;
			return;
		}
		if(newT2[j] == null){
			newT2[j] = key;
			n++;
			return;
		}
	}
		
	private void rehash(int newSize){
		String[] newT1 = new String[newSize];
		String[] newT2 = new String[newSize];
		//n = 0;
		for(int i = 0; i < m; i++){
			if(T1[i]!=null){
				insert(T1[i],newT1,newT2);
			}
			if(T2[i]!=null){
				insert(T2[i],newT1, newT2);
			}
		}
		
		T1 = newT1;
		T2 = newT2;
		m = newSize;	
	}
	//SEARCH
	public boolean search(String key){ 
		int i = hash1(key);
		int k = hash2(key);

		
		if(T1[i]!=null && T1[i].equals(key)){
			cont[i]++;
			return true;
		}
		if(T2[k]!=null && T2[k].equals(key)){
			cont[k]++;
			return true;
		}
		cont[k]=0;
		cont[i]=0;
		return false;
	}
	
	public void delete(String key){
		int i = hash1(key);
		int j = hash2(key);
		
		if(T1[i]!=null && T1[i].equals(key)){
			T1[i] = null;
			n--;
			return;
		}else if(T2[j]!=null && T2[j].equals(key)){
			T2[j] = null;
			n--;
			return;
		}
		return;
	}

	public void print(){
		for(int i=0; i<m; i++){
			System.out.println("Index " + i + ": " + T1[i] + " " + T2[i]);
		}
	}
	public int contains(String key){
		int i = hash1(key);
		int j = hash2(key);
		int counter=0;
		//if(T1[i]!=null && T1[i].equals(key)){
			//counter += cont[i];
		
	//	}if(T1[i]!=null && T1[i].equals(key)){
			counter += cont[j];
		//int counter = cont[i] + cont[j];
		//}
		return counter;
	}


	public static void main(String[] args) {
		System.out.println("Test Hash Table with Chaining");
        
        int M = 3000; // initial hash table size
        CuckooHT T = new CuckooHT(M, 80, 100);

        In.init();
        long startTime = System.currentTimeMillis();
        while (!In.empty()) {
            String s = In.getString();
            Integer count = (Integer) T.contains(s);
            if ( count != null ) {
                T.insert(s);
            } else {
                T.insert(s);
            }
        }
        //T.print();
        long endTime = System.currentTimeMillis();
        long chtTime = endTime - startTime;
        System.out.println("construction time = " + chtTime);
        
		System.out.println("load factor = " + T.loadFactor());

        System.out.println("number of words = " + T.words());
        
		
        System.out.println("search 'and' : " + T.search("and") + ". Contains 'and' " + T.contains("and")+ " time.");
        System.out.println("search 'astonished':  " + T.search("astonished") + ". Contains 'astonished' " + T.contains("astonished")+ " time.");
        System.out.println("search 'boat' :  " + T.search("boat") + ". Contains 'boat' " + T.contains("boat")+ " time.");
        System.out.println("search 'carol' :  " + T.search("carol") + ". Contains 'carol' " + T.contains("carol")+ " time.");
        System.out.println("search 'city' :  " + T.search("city")+ ". Contains 'city' " + T.contains("city")+ " time.");
        System.out.println("search 'scrooge' :  " + T.search("scrooge")+ ". Contains 'scrooge' " + T.contains("scrooge")+ " time.");
        System.out.println("search 'the' :  " + T.search("the")+ ". Contains 'the' " + T.contains("the")+ " time.");
        System.out.println("search 'train' :  " + T.search("train") + ". Contains 'train' " + T.contains("train")+ " time.");
        System.out.println("search 'wondered' :  " + T.search("wondered")+ ". Contains 'wondered' " + T.contains("wondered") + " time.");
        
        
        endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total running time = " + totalTime); 
    }
}


