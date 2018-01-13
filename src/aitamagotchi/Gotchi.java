package aitamagotchi;

import java.util.Random;

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
        this.name = name;
        isInLove = isAngry = hasGivenUp = isVeryAngry = false;
    }

    public void live() {
        hunger++;
        loneliness++;
        dirtiness++;
        setIfLoves();
        setIfGivenUp();
        setIfAngry();
    }

    private void setIfLoves() {
        if (isInLove && anger > angerEndLoveLimit) {
            isInLove = false;
        }
        if (!hasGivenUp && !isInLove && !isAngry && fedCount >= fedLoveLimit && interactionCount >= interactionLoveLimit) {
            isInLove = true;
        }
    }

    private void setIfGivenUp() {
        if (!hasGivenUp && hunger >= hungerGivingUpLimit && loneliness >= lonelinessGivingUpLimit && anger < (angerLimit * 10)) {
            hasGivenUp = true;
        }
    }

    private void setIfAngry() {
        if (hunger >= (hungerLimit * 2) || loneliness >= (lonelinessLimit * 2)) {
            anger += 1;
        }
        if (!hasGivenUp && !isAngry && anger >= angerLimit) {
            isAngry = true;
        }
        if (!isVeryAngry && anger >= (angerLimit * 10)) {
            isVeryAngry = true;
            isInLove = false;
            hasGivenUp = false;
        }
    }

    private void calmDown() {
        anger -= 10;
        if (anger < 0) {
            anger = 0;
        }
        if (anger < angerLimit) {
            isAngry = false;
            isVeryAngry = false;
        }
    }

    public void beFed() {
        beFed(hunger);
    }

    public void beFed(int amount) {
        fedCount++;
        calmDown();
        if (hunger - amount > 0) {
            hunger -= amount;
        } else {
            hunger = 0;
        }
    }

    public void beInteractedWith() {
        beInteractedWith(loneliness);
    }

    public void beInteractedWith(int amount) {
        interactionCount++;
        calmDown();
        if (loneliness - amount > 0) {
            loneliness -= amount;
        } else {
            loneliness = 0;
        }
    }

    public void beCleaned() {
        beCleaned(dirtiness);
    }

    public void beCleaned(int amount) {
        calmDown();
        if (dirtiness - amount > 0) {
            dirtiness -= amount;
        } else {
            dirtiness = 0;
        }
    }

    String getGeneralStatus() {
        String ret = "";
        if (!hasGivenUp) {
            if (loneliness >= lonelinessLimit || hunger >= hungerLimit) {
                ret += "sad ";
            }
            if (dirtiness >= dirtinessLimit) {
                ret += "dirty ";
            }
            if (isAngry) {
                ret += "angry ";
            }
            if (isInLove) {
                ret += "in_love ";
            }
        } else {
            ret += "has_given_up ";
            if (dirtiness >= dirtinessLimit) {
                ret += "dirty ";
            }
        }
        return ret;
    }

    String saySomething() {
        String statusBatch = getGeneralStatus() + ((hunger >= hungerLimit && !hasGivenUp) ? "hungry " : "")
                + ((loneliness >= lonelinessLimit && !hasGivenUp) ? "lonely " : "")
                + ((hunger >= hungerLimit && isAngry) ? "hangry " : "")
                + ((hunger >= hungerLimit && isInLove) ? "love_hungry " : "")
                + ((isAngry && isInLove) ? "angry_inLove " : "");
        String[] statuses = statusBatch.split(" ");
        Random rand = new Random();
        return thinkOfSomethingToSay(statuses[rand.nextInt(statuses.length)]);
    }

    private String thinkOfSomethingToSay(String feeling) {
        String ret = "";
        switch (feeling) {
            case "hangry":
                ret = dictionary.hangryMessage;
                break;
            case "love_hungry":
                ret = dictionary.love_hungryMessage;
                break;
            case "angry_inLove":
                ret = dictionary.angry_inLoveMessage;
                break;
            case "sad":
                ret = dictionary.sadMessage;                        
                break;
            case "angry":
                ret = dictionary.angryMessage;
                break;
            case "hungry":
                ret = dictionary.hungryMessage;
                break;
            case "lonely":
                ret = dictionary.lonelyMessage;
                break;
            case "dirty":
                ret = dictionary.dirtyMessage;
                break;
            case "in_love":
                ret = dictionary.in_loveMessage;
                break;
            case "has_given_up":
                ret = dictionary.has_given_upMessage;
                break;
            default:
                ret = dictionary.defaultMessage;
                break;
        }
        return ret;
    }
}
