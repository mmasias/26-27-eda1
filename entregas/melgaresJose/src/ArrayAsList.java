public class ArrayAsList {
    private int[] itemsList;
    private int currentLength;

    private final int INIT_SIZE = 16;

    public ArrayAsList(){
        this.itemsList = new int[INIT_SIZE];
        this.currentLength = 0;
    }

    public ArrayAsList(int initialSize){
        this.itemsList = new int[initialSize];
        this.currentLength = 0;
    }

    public void pushOnStart(int data){
        
        evaluateIfExpand();

        for(int i = 1; i < currentLength + 1; i++) 
            itemsList[i] = itemsList[i+1];

        itemsList[0] = data;
        currentLength++;
    }

    public void pushOnEnd(int data){
        evaluateIfExpand();

        itemsList[currentLength] = data;
        currentLength++;
    }

    public void popOnEnd(){
        assert currentLength > 0;

        currentLength--;
    }

    public void popOnStart(){
        assert currentLength > 0;

        for (int i = 0; i < currentLength - 1; i++) {
            itemsList[i]=itemsList[i+1];
        }
        currentLength--;
    }

    private void evaluateIfExpand() {
        if(currentLength == itemsList.length )
            expandList();
    }

    public int getData(int position){
        return itemsList[position];
    }
    

    private void expandList(){
        int[] helper = new int[itemsList.length*2];
        copyItems(helper);
        itemsList = helper;
    }

    private void copyItems(int[] helper) {
        for(int i = 0; i < currentLength; i++){
            helper[i] = itemsList[i];
        }
    }
}
