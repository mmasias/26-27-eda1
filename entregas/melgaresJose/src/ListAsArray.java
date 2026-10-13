public class ListAsArray {
    private int arraySize;
    private Node first;

    public ListAsArray(int initialSize){

        Node helperNode = new Node(0);
        this.first = helperNode;
        this.arraySize = initialSize;
        fillArray(helperNode);
    }

    private void fillArray(Node helperNode) {
        for(int i = 0; i<arraySize; i++) {
            Node newNode = new Node(0);
            helperNode.nextNode = newNode;
            helperNode = newNode;
        }
    }

    private Node getPosition(int position) {
        assert position < arraySize;

        Node pointer = first;

        for (int i = 0; i < position; i++){
            pointer = pointer.nextNode;
        }

        return pointer;
    }

    public void insert(int position, int valor){
        Node temp = getPosition(position);
        temp.data = valor;
    }

    public int getData(int position){
        return getPosition(position).data;
    }

    public int length(){
        return arraySize;
    }


    public void show(){
        System.out.print("[");
        for (int i = 0; i<arraySize; i++) {
            System.out.print( getData(i) + ",");
        }
        System.out.print("]");
    }
}
