import java.util.Random;
/**
 * Represents an actor in the game, which can be either a hero or a monster.
 * Each actor has a name, health, maximum damage they can deal, and their position in the dungeon.
 */
public class Actor
{
    private String name;
    private int health;
    private int maxDamage;
    private int row;
    private int col;
    /**
     * Default constructor for creating a monster with predefined attributes.
     * Name is "Monster", health is 25, and maximum damage is 5.
     */
    public Actor()
    {
        this.name = "Monster";
        this.health = 25;
        this.maxDamage = 5;
    }
    /**
     * Overloaded constructor for creating a hero with a given name and health.
     * @param char_name The name of the character.
     * @param health The health of the character.
     */
    public Actor(String char_name, int health)
    {
        this.name = char_name;
        this.health = health;
        this.maxDamage = 10;
        this.row = 0;
        this.col = 0;
    }
    //setter getter section:
    // Setting up Hero name
    public void setName(String name)
    {
        this.name= name;
    }
    //getter for hero name
    public String getName()
    {
        return(name);
    }
    // getting and setting column
    public void setCol(int col)
    {
        this.col = col;
    }
    public int getCol()
    {
        return col;
    }
    //setter and getter for row
    public void setRow(int row)
    {
        this.row = row;
    }
    public int getRow()
    {
        return row;
    }
    // setter getter
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
//Methods for checking actor's state
    public boolean isAlive() {
        return this.health > 0;
    }

    public boolean hasEscaped(int dungeonSize)
    {
        return this.row == dungeonSize - 1 && this.col == dungeonSize - 1;
    }

    public boolean inSameRoom(Actor other)
    {
        return this.row == other.row && this.col == other.col;
    }
    /**
     * Hits another character, dealing a random amount of damage up to the character's maximum damage.
     * @param other The character to hit.
     */
    public void hit(Actor other)
    {
        Random rand = new Random();
        int damage = rand.nextInt(this.maxDamage) + 1;
        other.health -= damage;
        System.out.println(other.name+"Hit for "+damage);
    }
    /**
     * Moves the character in a specified direction within the bounds of the dungeon.
     * @param direction The direction to move ("north", "south", "east", "west").
     * @param dungeonSize The size of the dungeon.
     * @return True if the character successfully moves, false otherwise.
     */
    public boolean move(String direction, int dungeonSize)
    {
        switch (direction.toLowerCase()) {
            case "north":
                if (this.row > 0) {
                    this.row--;
                    return true;
                }
                break;
            case "south":
                if (this.row < dungeonSize - 1) {
                    this.row++;
                    return true;
                }
                break;
            case "east":
                if (this.col < dungeonSize - 1) {
                    this.col++;
                    return true;
                }
                break;
            case "west":
                if (this.col > 0) {
                    this.col--;
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return this.name + " at " + this.row + ", " + this.col + " with " + this.health + " health ";
    }
}
