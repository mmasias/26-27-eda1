public class Tests {
    public static void testListAsArray() {
        ListAsArray iAmAnArray = new ListAsArray(getTestData());
        iAmAnArray.insert(1, getTestData());
        iAmAnArray.insert(2, getTestData());
        iAmAnArray.insert(3, getTestData());
        iAmAnArray.insert(2, getTestData());

        iAmAnArray.show();

    }

    public static void testArrayAsList() {
        ArrayAsList iAmAList = new ArrayAsList();
        iAmAList.pushOnStart(getTestData());
        iAmAList.pushOnStart(getTestData());
        iAmAList.pushOnStart(getTestData());
        iAmAList.pushOnStart(getTestData());
        iAmAList.pushOnEnd(getTestData());
        iAmAList.popOnEnd();

    }

    private static int getTestData() {
        return (int) Math.random() * 10;
    }
}
