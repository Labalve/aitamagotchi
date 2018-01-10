package aitamagotchi;

/**
 *
 * @author Labalve
 */
public class Gotchi {

    int hunger;
    int hungerLimit = 10;
    int loneliness;
    int lonelinessLimit = 20;
    int dirtiness;
    int dirtinessLimit = 30;
    String name;
    String currentGeneralStatus = "<html>";
    gotchiDictionary dictionary = gotchiDictionary.getGotchiDictionary();

    public Gotchi(String name) {
        this.name = name;
    }

    public void live() {
        hunger++;
        loneliness++;
        dirtiness++;
        if (hunger == hungerLimit) {
            currentGeneralStatus += dictionary.hungerMessage;
        }
        if (loneliness == lonelinessLimit) {
            currentGeneralStatus += dictionary.lonelinessMessage;
        }
        if (dirtiness == dirtinessLimit) {
            currentGeneralStatus += dictionary.dirtinessMessage;
        }
    }

    public void beFed(int amount) {
        if (hunger - amount > 0) {
            hunger -= amount;
        } else {
            hunger = 0;
        }
        if (hunger < hungerLimit && currentGeneralStatus.contains(dictionary.hungerMessage)) {
            currentGeneralStatus = currentGeneralStatus.replace(dictionary.hungerMessage, "");
        }
    }
//        System.out.println("current hunger: " + hunger);

    public void beInteractedWith(int amount) {
        if (loneliness - amount > 0) {
            loneliness -= amount;
        } else {
            loneliness = 0;
        }
        if (loneliness < lonelinessLimit && currentGeneralStatus.contains(dictionary.lonelinessMessage)) {
            currentGeneralStatus = currentGeneralStatus.replace(dictionary.lonelinessMessage, "");
        }
    }

    public void beCleaned(int amount) {
        if (dirtiness - amount > 0) {
            dirtiness -= amount;
        } else {
            dirtiness = 0;
        }
        if (dirtiness < dirtinessLimit && currentGeneralStatus.contains(dictionary.dirtinessMessage)) {
            currentGeneralStatus = currentGeneralStatus.replace(dictionary.dirtinessMessage, "");
        }
    }
}
