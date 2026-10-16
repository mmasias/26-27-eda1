public class Main {
    public static void main(String[] args) {
        ArrayDesdeLista array = new ArrayDesdeLista(5);
        array.set(0, 10);
        array.set(1, 20);
        array.set(4, 50);
        System.out.println(array);
        System.out.println(array.length());
        try {
            array.set(5, 99);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}