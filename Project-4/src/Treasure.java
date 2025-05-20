import java.util.Random;
/**
 * Represents a treasure in the dungeon that can provide a health boost.
 * The treasure is placed at a random position in the dungeon.
 */
class Treasure {
    private int row;
    private int col;
    private int healthBoost;
    /**
     * Constructor for the Treasure class.
     * The treasure is placed randomly in the dungeon, and health boost is set to the total number of boxes.
     * @param dungeonSize The size of the dungeon (width and height).
     */

    public Treasure(int dungeonSize) {
        Random rand = new Random();
        this.row = rand.nextInt(dungeonSize);// Random row in the dungeon
        this.col = rand.nextInt(dungeonSize);// Random column in the dungeon
        // healthboost point is number of boxes available in the dungeon
        this.healthBoost = dungeonSize * dungeonSize;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getHealthBoost() {
        return healthBoost;
    }

    @Override
    public String toString() {
        return "Congratulations!!! You got Treasure at " + row + ", " + col + " with health boost of " + healthBoost;
    }
}
