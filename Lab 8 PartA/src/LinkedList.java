/**
 * Simple example of linkedlist. Problem was already given
 */
public class LinkedList {

    private Node head;
    private Node tail;
    /**
     * adds new items to the end of the list
     * @param item string to add into the linkedlist
     */

    public void add(String item) {

        Node newItem = new Node(item);

        // handles the case where the new item
        // is the only thing in the list
        if (head == null) {
            head = newItem;
            tail = newItem;
            return;
        }

        tail.next = newItem;
        tail = newItem;
    }

    /**
     * Printing all of the items in the list
     */
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }
    /**
     * getter to find first element
     * @return return the value in first node
     */
    public String getFirst() {
        //returns Last item or null if empty
        return head != null ? head.item : null;
    }
    /**
     * getter to find last item
     * @return returns last item in the list
     */
    public String getLast() {
        if (head == null) {
            return null; // List is empty
        }

        Node current = head;
        while (current.next != null) {
            current = current.next; // Move to the next node
        }
        return current.item; // Return the last item's value
    }
    /**
     * check every conditions in the question and returns the second last node.
     * @return returns next to last element in the list
     */
    public String getPenultimate() {
        // If the list has zero or one item, return null
        if (head == null || head.next == null) {
            return null;
        }

        // To find the value in second last node
        Node presentNode = head;
        while (presentNode.next != null && presentNode.next.next != null) // checking last node and second last node is not null
        {
            presentNode = presentNode.next;
        }
        return presentNode.item;
    }


    class Node {
        String item;
        Node next;

        public Node(String item) {
            this.item = item;
            this.next = null;
        }
    }
}
