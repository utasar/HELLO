public class Driver {
    /**
     * Main method to implement simple linked list example.
     * @param args
     */
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add("Falcons");
        list.add("Bears");
        list.add("Titans");
        list.add("Eagles");
        list.add("Panthers");
        list.add("Cowboys");
        list.add("Steelers");
        list.add("49ers");
        list.add("Vikings");
        list.add("Saints");
        list.add("Seahawks");

        list.print();
        System.out.println("\n");
        System.out.print("First item:");
        System.out.println(list.getFirst());
        System.out.print("Last item:");
        System.out.println(list.getLast());
        System.out.print("Penultimate item: ");
        System.out.println(list.getPenultimate());
    }
}
