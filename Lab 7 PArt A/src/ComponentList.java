import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic class that extends JPanel and can hold a list of JComponent objects.
 * The generic type T is bounded to JComponent or its subclasses.
 *
 * @param <T> the type of JComponent that this list will hold
 */
public class ComponentList<T extends JComponent> extends JPanel {

    private List<T> componentList;

    /**
     * Default constructor that creates an empty ComponentList.
     */
    public ComponentList() {
        componentList = new ArrayList<>();
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0)); // Center alignment with spacing
    }

    /**
     * Constructor that takes an ArrayList of components and adds them to the list.
     *
     * @param components the initial list of components to add
     */
    public ComponentList(ArrayList<T> components) {
        this(); // Call the no-argument constructor
        for (T component : components) {
            add(component);
        }
    }

    /**
     * Adds a new component to the ComponentList.
     *
     * @param component the component to add
     */
    public void add(T component) {
        componentList.add(component);
        super.add(component);
        revalidate(); // Revalidate the panel
        repaint(); // Repaint the panel
    }

    /**
     * Replaces the component at the specified index with a new component.
     *
     * @param index     the index to replace the component at
     * @param component the new component to insert at the index
     */
    public void setComponentAtIndex(int index, T component) {
        if (index >= 0 && index < componentList.size()) {
            componentList.set(index, component); // Update the internal list
            removeAll(); // Remove all components from the panel

            // Re-add all components in order
            for (T comp : componentList) {
                super.add(comp);
            }

            revalidate(); // Ensure the layout updates
            repaint(); // Redraw the panel
        }
    }
}
