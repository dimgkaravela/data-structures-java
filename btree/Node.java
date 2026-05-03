class Node {
    private static final int Keys = 4;
    private int numKeys; // The number 
    //private Node parent;
    private Node children[] = new Node[Keys]; // The children of the node
    private NodeData keys[] = new NodeData[Keys-1]; // The number of keys stored in the node 
      
    public void  connectChild (int childNum, Node child){
        children[childNum] = child;
        if (child != null)
            child.parent = this;
    }

    public Node disconnectChild(int childNum){
        Node tempNode = children[childNum];
        children[childNum] = null;
        return tempNode;
    }

    public Node getChild(int childNum){
        return children[childNum];
    }

    public Node getParent(){
        return parent;
    }

    public boolean isLeaf(){
        return (children[0] == null) ? true : false;
    }

    public int getnumKeys(){
        return numKeys;
    }

    public void setnumKeys(int theValue){
        numKeys = theValue;
        return;
    }

    public NodeData getItem(int index){
        return keys[index];
    }   

    public NodeData setItem(int index, NodeData theValue){
        keys[index] = theValue;
        return keys[index];
    }

    public boolean isFull(){
        return (numKeys == Keys -1) ? true : false;
    }

    public int insertItem(Nodedata newItem){
        numKeys++;
        int newKey = newItem.dData;

        for (int j = Keys - 2; j >= 0; j--){
            if (keys[j] == null)
                continue;
            else{
                int itsKey = keys[j].ddata;
                if (newKey < itsKey)
                    keys[j+1] = keys[j];
                else{
                    keys[j+1] = newItem;
                    return j+1;
                }
            }
        }
        keys[0] = newItem;
        return 0;
    }

    public void insertAtFront(NodeData newItem){
        int newKey = newItem.dData;
        numKeys++;
        for (int j = numKeys - 1; j > 0; j--){
            keys[j] = keys[j-1];
            connectChild(j+1, disconnectChild(j));
        }
        connectChild(1, disconnectChild(0));
        keys[0] = newItem;
        connectChild(0,null);
        return;
    }

    public void printNode(){
        for (int j = 0; j < numKeys; j++){
            keys[j].printItem();
        }
    }

    public void printValue(int j){
        keys[j].printItem();
    } 

    public Node getSibling(int theValue){
        Node x = null;
        Node p = getParent();
        if (numKeys != 0){
            for(int i = 0; i <= p.numKeys; i++){
                if (p.children[i].keys[0].dData < theValue){
                    x = p.children[i];
                }
            }
        } else if(numKeys == 0){
            for (int i = 0; i <= p.numKeys; i++){
                if (p.children[i].keys[0] == null){
                    if (i != 0){
                        x = p.children[i-1];
                    }
                }
            }
        }
        return x;
    }

}

