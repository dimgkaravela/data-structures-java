class TreeNode {
	private int[] keys= new int[3];
	private TreeNode[] children= new TreeNode[4];
	private int keyCount;
	private TreeNode parent;

	  // Constructor to create a new leaf node
	public TreeNode(int key) {
		keys[0] = key;
		keyCount = 1;
	}

	  // Constructor to create a new internal node
	public  TreeNode(int key1, int key2, TreeNode left, TreeNode right) {
		keys[0] = key1;
		keys[1] = key2;
		keyCount = 2;
		children[0] = left;
		children[1] = right;
		left.parent = this;
		right.parent = this;
	  }

	  // Getters and setters go here
	public int[] getkeys() {
		return keys;
	}

	public void setkeys1(int[] keys) {
		this.keys = keys;
	}

	public TreeNode[] getChildren() {
		return children;
	}

	public void setChildren(TreeNode[] children) {
		this.children = children;
	}

	public int getKeyCount() {
		return keyCount;
	}

	public void setKeyCount(int keyCount) {
		this.keyCount = keyCount;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

}

class TwoFourTree {
	TreeNode root;

	public void insert(int key) {
		if (root == null) {
			root = new TreeNode(key);
		    return;
		}
		TreeNode node = root;
		TreeNode[] children = node.getChildren();
		int[] keys1 = node.getkeys1();
		while (node != null) {
			if (node.getKeyCount() < 3) {
				// insert the key into the current node and return
				insertKey(node, key);
				return;
			}else {
				// split the node and promote the middle key
				int midKey = node.getkeys1keys1[1];
				TreeNode left = new TreeNode(node.keys1[0]);
				left.children[0] = node.children[0];
				left.children[1] = node.children[1];
				TreeNode right = new TreeNode(node.keys1[2]);
				right.children[0] = node.children[2];
				right.children[1] = node.children[3];

				if (key < midKey) {
				  insertKey(left, key);
				} else {
				  insertKey(right, key);
				}

				node.keys1[0] = midKey;
				node.keyCount = 1;
				node.children[0] = left;
				node.children[1] = right;
				node.children[2] = null;
				node.children[3] = null;

				// move up to the parent node
				node = node.parent;
			}
		}
    }

	private void insertKey(TreeNode node, int key) {
		int i;
		for (i = 0; i < node.keyCount; i++) {
			if (key < node.keys1[i]) {
				break;
		    }
		}
		for (int j = node.keyCount; j > i; j--) {
			node.keys1[j] = node.keys1[j - 1];
		}
		node.keys1[i] = key;
		node.keyCount++;
	  }

	void printTree() {
		printTree(root, 0);
	}

	void printTree(TreeNode node, int level) {
		if (node == null) {
			return;
		}
		for (int i = 0; i < level; i++) {
			System.out.print("    ");
		}
		for (int i = 0; i < node.keyCount; i++) {
			System.out.print(node.keys1[i] + " ");
		}
		System.out.println();
		for (int i = 0; i <= node.keyCount; i++) {
			printTree(node.children[i], level + 1);
		}
	}
	public static void main(String[] args) {
		TwoFourTree tree = new TwoFourTree();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);
		tree.insert(50);
		tree.insert(60);
	}
}

