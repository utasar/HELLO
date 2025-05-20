/**
 * @author Utsav Acharya 11/18/2024
 */
import java.util.Random;
/**
 * 
 * The  Player class represents a player in a game with a name, health, and maximum damage they can deal.
*/
public class Player {
    private String name;
    private int health;
    private int maxDamage;
/**
     * Constructs a new player with the specified name and maximum damage.
     * @param name the name of the player
     * @param maxDamage the maximum damage the player can deal
     */
    public Player(String name, int maxDamage) 
    {
        this.name = name;
        this.maxDamage = maxDamage;
        this.health = 100;
    }
/**
     * Deals damage to the specified opponent player. The damage is a random value between 0 and the player's maximum damage.
     *
     * @param opponent the player to deal damage to
*/
public void dealDamage(Player opponent) {
    Random rand = new Random();
    int damage = rand.nextInt(this.maxDamage + 1);
    opponent.health -= damage;
    if (opponent.health < 0) {
        opponent.health = 0;
    }
}
/**
     * Returns a string  including the player's name and health which replaces .
     *
     * @return a string representation of the player with health point
*/
@Override
public String toString() {
    return name + ": HP " + health;
}
/**
     * Returns the health of the player.
     *
     * @return the health of the player
     */
public int getHealth() {
    return health;
}

public static void main(String[] args) {
    Player player1 = new Player("One Punch Man", 20);
    Player player2 = new Player("Alien", 5);
    // printing full health
    System.out.print(player1+" ");
    System.out.print(player2);
    System.out.println();
    // fighting
    while (player1.getHealth() > 0 && player2.getHealth() > 0) {
        player1.dealDamage(player2);
        player2.dealDamage(player1);
        System.out.print(player1+" ");
        System.out.print(player2);
        System.out.println();
    }

    if (player1.getHealth() > 0) {
        System.out.println("Winner is "+ player1.name +": HP "+player1.health);
    } else {
        System.out.println("Winner is "+player2.name +": HP "+ player2.health);
    }
}
}
