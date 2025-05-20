/**
 * @author UA Utsav Acharya
 * Enhancement done for the project for surprise treasure in one of the random position, with the HP (health point ) equivalent to the number of boxes in the catacomb
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class CatacombCrawler {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Actor hero = createActor(scnr);
        int dungeonSize = getDungeonSize(scnr);
        ArrayList<Actor> monsters = createMonsters(dungeonSize);
        System.out.println(hero);
        Treasure treasure = createTreasure(dungeonSize,monsters);
        //MAIN  GAME LOOP
        while (hero.isAlive() && !hero.hasEscaped(dungeonSize)) {
            int nearbyMonsters = countNearbyMonsters(hero, monsters);
            System.out.println("You smell " + nearbyMonsters + " monster(s) nearby.");
            System.out.println("===========================================");
            System.out.print("Which way do you want to go (north, south, east, west): ");
            String direction = scnr.nextLine();

            if (hero.move(direction, dungeonSize)) {
                hero.setHealth(hero.getHealth() - 2);
                //code enhancement to boost the health if treasure found on any position
                if (hero.getRow() == treasure.getRow() && hero.getCol() == treasure.getCol())
                {
                    System.out.println("++++++++++++++++++++++");
                    System.out.println(treasure);
                    System.out.println("++++++++++++++++++++++");
                    hero.setHealth(hero.getHealth() + treasure.getHealthBoost());
                }
                // System.out.println(hero.getName()+" at "+hero.getRow()+", "+hero.getCol()+" with "+hero.getHealth());
                System.out.println(hero);
                fight(hero, monsters);// Fight monsters in the same room
            } else {
                System.out.println("You can't move that way!");
                System.out.println(hero);
                System.out.println("------------------------------");
                System.out.print("Which way do you want to go (north, south, east, west): ");
                direction = scnr.nextLine();
                System.out.println(hero);
            }
        }

        if (hero.isAlive() && hero.hasEscaped(dungeonSize)) {
            System.out.println("Congratulations! " + hero.getName() + " has escaped the catacombs!");
            System.out.println("==================END OF GAME=========================");
        } else {
            System.out.println(hero.getName() + " has died. Game over.");
            System.out.println("==================END OF GAME=========================");
        }
        scnr.close();
    }
    /**
     * Prompts the user to character name
     * @param scnr the Scanner object used to read the user's input
     * @return a new Actor instance with the user's name and a default health of 100
     */
    public static Actor createActor(Scanner scnr)
    {
        System.out.print("What is your name, heroic adventurer? ");
        String name = scnr.nextLine();
        return new Actor(name, 100);
    }
    /**
     * Prompts the user to input the size of the dungeon and ensures the input is within the valid range.
     * @param scnr the Scanner object used to read the user's input
     * @return the size of the dungeon, which is an integer between 5 and 10 inclusive
     */
    public static int getDungeonSize(Scanner scnr)
    {
        int size = 0;
        System.out.print("How wide of a catacomb do you want to face (5-10)? ");
        while (size < 5 || size > 10) {
            if (scnr.hasNextInt()) {
                size = scnr.nextInt();
                scnr.nextLine(); // consume the newline
                if (size < 5 || size > 10) {
                    System.out.println("That is not a valid catacomb size! ");
                    System.out.print("How wide of a catacomb do you want to face (5-10)? ");
                    System.out.println("===========================================");
                }
            } else {
                scnr.next(); // consume the invalid input
                System.out.println("That is not a valid catacomb size! ");
                System.out.print("How wide of a catacomb do you want to face (5-10)? ");
                System.out.println("===========================================");
            }
        }
        return size;
    }
    /**
     * Creates a list of monsters to be placed in a dungeon of a given size.
     *This method generates a number of monsters based on the size of the dungeon.
     * The number of monsters is calculated as (dungeonSize * dungeonSize) / 6. Monster is placed
     * at a random position within the dungeon, ensuring that no monster is placed at the origin
     * If a randomly generated position is (0,0), the monster is created again
     * @param dungeonSize the size of the dungeon, representing both its width and height
     * @return an ArrayList of Actor objects representing the monsters
     */
    public static ArrayList<Actor> createMonsters(int dungeonSize)
    {
        ArrayList<Actor> monstersList = new ArrayList<>();
        int numMonsters = (dungeonSize * dungeonSize) / 6;
        Random rand = new Random();

        for (int i = 0; i < numMonsters; i++) {
            Actor monster = new Actor();
            int tempRow = rand.nextInt(dungeonSize);
            int tempCol = rand.nextInt(dungeonSize);
            if (!(tempCol==0&&tempRow==0))// is monster is created in 00
            {
                monstersList.add(monster);
                monster.setRow(tempRow);
                monster.setCol(tempCol);
                //     System.out.println("monsters in position "+tempRow+" , "+tempCol);
            }
            else
                i=i-1;

        }
        return monstersList;
    }
    /**
     * Creates a treasure in the dungeon, ensuring it does not occupy the same position as any monster or the starting position (0, 0).
     *
     * @param dungeonSize The size of the dungeon, representing both its width and height.
     * @param monsters A list of monsters in the dungeon to avoid placing the treasure at their positions.
     * @return A new Treasure instance located at a valid position in the dungeon.
     */
    public static Treasure createTreasure(int dungeonSize,ArrayList<Actor> monsters) {
        Treasure treasure;
        HashSet<String> occupiedPositions = new HashSet<>(); // Used Hashset for easier
        for (Actor monster : monsters) {
            occupiedPositions.add(monster.getRow() + "," + monster.getCol());
        }

        do {
            treasure = new Treasure(dungeonSize);
        } while (occupiedPositions.contains(treasure.getRow() + "," + treasure.getCol()) || (treasure.getRow() == 0 && treasure.getCol() == 0));

        // System.out.println(treasure);// to be commented later get the coordinate of bonus health
        // System.out.println("=============================");
        return treasure;
    }
    /**
     * Initiates a fight between the hero and the monsters in the same room.
     * @param hero the hero Actor who is fighting the monsters
     * @param monstersList the list of all monster Actors
     */
    public static void fight(Actor hero, ArrayList<Actor> monstersList) {
        for (int i = monstersList.size() - 1; i >= 0; i--) {
            Actor monster = monstersList.get(i);
            if (hero.inSameRoom(monster)) {
                // System.out.println("Fighting " + monster +" with health:" +monster.getHealth()+" Your HP: "+hero.getHealth());
                System.out.println(hero+" versus "+monster);
                System.out.println("===========================================");
                while (hero.isAlive() && monster.isAlive()) {
                    hero.hit(monster);
                    if (monster.isAlive()) {
                        monster.hit(hero);
                        System.out.println(">>>>>War>>>>>");
                    }
                }
                if (!hero.isAlive()) {
                    System.out.println(hero.getName()+"at ("+hero.getRow()+","+hero.getCol() + " health "+hero.getHealth()+" has been slain by " + monster.getName() +" with HP "+monster.getHealth()+ "!");
                    System.out.println("===========================================");
                    break;
                }
                if (!monster.isAlive()) {
                    System.out.println(monster.getName()+" "+i + " is defeated!");
                    System.out.println("===========================================");
                    monstersList.remove(i);
                    System.out.println(hero);
                    System.out.println("===========================================");
                }
            }
        }
    }
    /**
     * Counts the number of monsters that are adjacent to the given hero.
     * @param hero the hero Actor whose surroundings are being checked for monsters
     * @param monstersList the list of all monster Actors
     * @return the number of monsters adjacent to the hero
     */
    public static int countNearbyMonsters(Actor hero, ArrayList<Actor> monstersList)
    {
        int count = 0;
        for (Actor monster : monstersList) {
            if ((monster.getRow() == hero.getRow() - 1 && monster.getCol() == hero.getCol()) ||
                    (monster.getRow() == hero.getRow() + 1 && monster.getCol() == hero.getCol()) ||
                    (monster.getRow() == hero.getRow() && monster.getCol() == hero.getCol() - 1) ||
                    (monster.getRow() == hero.getRow() && monster.getCol() == hero.getCol() + 1)) {
                count++;
            }
        }
        return count;
    }
}
