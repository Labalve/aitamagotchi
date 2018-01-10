package aitamagotchi;

/**
 *
 * @author Labalve
 */
public class Gotchi {
    int hunger;
    String name;
    public Gotchi(String name){
        this.name = name;
        hunger = 0;
    }
    public void live(){
        this.hunger++;
        if(hunger == 10){
            System.out.println("I'm so hungry...");
        }
    }
    public void beFed(int amount){
        if(hunger - amount > 0) this.hunger -= amount;
        else this.hunger = 0;
        System.out.println("current hunger: " + this.hunger);
    }
}
