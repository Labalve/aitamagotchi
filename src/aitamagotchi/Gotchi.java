package aitamagotchi;

/**
 *
 * @author Labalve
 */
public class Gotchi {

    private static Gotchi instance;

    int hunger;
    int hungerLimit = 10;
    int loneliness;
    int lonelinessLimit = 20;
    int dirtiness;
    int dirtinessLimit = 30;
    int fedCount = 0;
    int fedLoveLimit = 15;
    int interactionCount = 0;
    int interactionLoveLimit = 15;
    int overallHungerCount;
    int anger = 0;
    int angerLimit = 200;
    int hungerGivingUpLimit = 180;
    int lonelinessGivingUpLimit = 180;
    int angerEndLoveLimit = 1500;
    boolean isInLove, isAngry, hasGivenUp, isVeryAngry;
    String name;
    String currentGeneralStatus = "<html>";
    gotchiDictionary dictionary = gotchiDictionary.getGotchiDictionary();

    public static Gotchi getGotchi(String name) {
        if (instance == null) {
            instance = new Gotchi(name);
        }
        return instance;
    }

    public static Gotchi getGotchi() {
        if (instance == null) {
            instance = new Gotchi("Gotchi");
        }
        return instance;
    }

    private Gotchi(String name) {
        name = name;
        isInLove = isAngry = hasGivenUp = isVeryAngry = false;
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
        setIfLoves();
        setIfGivenUp();
        setIfAngry();
    }

    private void setIfLoves(){
        if(isInLove && anger > angerEndLoveLimit) {
            isInLove = false;
        }
        if(!hasGivenUp && !isInLove && !isAngry && fedCount >= fedLoveLimit && interactionCount >= interactionLoveLimit){
            isInLove = true;
        }
    }
    
    private void setIfGivenUp(){
        if(!hasGivenUp && hunger >= hungerGivingUpLimit && loneliness >= lonelinessGivingUpLimit && anger < (angerLimit * 10)){
            hasGivenUp = true;
        }
    }
    
    private void setIfAngry() {
        if(hunger > (hungerLimit * 2) || loneliness > (lonelinessLimit * 2)) {
            anger += 1;
        }
        if(!hasGivenUp && !isAngry && anger >= angerLimit){
            isAngry = true;
        }
        if(!isVeryAngry && anger >= (angerLimit * 10)) {
            isVeryAngry = true; isInLove = false; hasGivenUp = false;
        }
    }
    
    private void calmDown() {
        anger -= 10;
        if(anger < 0) anger = 0;
        if(anger < angerLimit){
            isAngry = false; isVeryAngry = false;
        }
    }
    
    
    public void beFed(int amount) {
        fedCount++;
        calmDown();
        if (hunger - amount > 0) {
            hunger -= amount;
        } else {
            hunger = 0;
        }
        if (hunger < hungerLimit && currentGeneralStatus.contains(dictionary.hungerMessage)) {
            currentGeneralStatus = currentGeneralStatus.replace(dictionary.hungerMessage, "");
        }
    }

    public void beInteractedWith(int amount) {
        interactionCount++;
        calmDown();
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
        calmDown();
        if (dirtiness - amount > 0) {
            dirtiness -= amount;
        } else {
            dirtiness = 0;
        }
        if (dirtiness < dirtinessLimit && currentGeneralStatus.contains(dictionary.dirtinessMessage)) {
            currentGeneralStatus = currentGeneralStatus.replace(dictionary.dirtinessMessage, "");
        }
    }

    String getGeneralStatus() {
        String ret = "";
        if(!hasGivenUp){
            if(loneliness >= lonelinessLimit || hunger >= hungerLimit) ret += "sad ";
            if(dirtiness >= dirtinessLimit) ret += "dirty ";
            if(isAngry) ret += "angry ";
            if(isInLove) ret += "in_love ";
        }
        else {
            ret += "has_given_up ";
            if(dirtiness >= dirtinessLimit) ret += "dirty ";
        }
        return ret;
    }
}
