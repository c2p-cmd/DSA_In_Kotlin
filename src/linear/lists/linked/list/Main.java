package linear.lists.linked.list;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        for (int i = 10; i < 45; i+=5) {
            list1.addDataToTail(i);
            list2.addDataToTail(i/5);
        }

        System.out.println("Elements: " + list1.getSize());
        System.out.println(list1.showListWithIndex());

        System.out.println("\nElements: " + list2.getSize());
        System.out.println(list2.showListWithIndex());

        list1.transferHeadTo(list2);

        System.out.println("\nElements: " + list1.getSize());
        System.out.println(list1.showListWithIndex());

        System.out.println("\nElements: " + list2.getSize());
        System.out.println(list2);

        int size = list2.getSize();
        for (int i = 0; i <= size; i++) {
            Integer temp = list2.deleteFromHead();
            if (temp != null) {
                System.out.println("i. " + temp);
            } else {
                System.out.println("null");
            }
        }
    }
}
