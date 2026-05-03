//import java.util.Random;
import java.util.*;  

public class BTreeTest{

	public static void main(String[] args){
		BTree theTree = new BTree();
		
		System.out.println("Give me how many digits you want");
		Scanner sca= new Scanner(System.in); 
		int n = sca.nextInt();
		Random random = new Random();
		long startTime = System.currentTimeMillis();
		//insert
		for (int a = 1; a < n+1; a++) {
			Node present = theTree.find(a);
			if (present != null){
				
			}else{
				theTree.insert(a);
			}
		}
		//print tree
		Node root = theTree.getRoot();
		System.out.println("Tree:");
		theTree.recprintTree(root,0,0);
		//delete
		System.out.println("Enter value to delete: ");
		Scanner scan= new Scanner(System.in); 
		int value = scan.nextInt();
		Node del = theTree.find(value);
		if (del != null){
			if(theTree.delete(del,value)!=null)
				System.out.println("Deleted " + value);
			else{
				System.err.println("Not Deleted!!!");
			}
		}
		else{
			System.out.println("Could not find " + value);
		}
		System.out.println("Tree:");
		theTree.recprintTree(root,0,0);
		//search
		System.out.println("Would you like to search for a value?");
		Scanner sc= new Scanner(System.in);
		String str = sc.nextLine();
		if(str.equals("yes")){
			System.out.println("Enter value to find: ");
			Scanner scann= new Scanner(System.in); 
			int valuee = scann.nextInt();
			Node found = theTree.find(valuee);
			if (found != null){
				System.out.println("Found " + value);
			}
			else{
					System.out.println("Could not find " + value);
			}		
		}
		
		long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(" Time passed = " + totalTime);
		System.out.println("Thank you!!!");
		System.exit(0);
	}
}