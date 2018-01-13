package aitamagotchi;

/**
 *
 * @author Labalve
 */
public class gotchiDictionary {
    
    private static gotchiDictionary instance;
    
    String sadMessage = "I'm so sad...";
    String angryMessage = "I'm as mad as Hell!";
    String hungryMessage = "I'm starving!";
    String lonelyMessage = "Hello... Is there anybody in there?";
    String dirtyMessage = "I don't mind some dirt on me, it's cool";
    String in_loveMessage = "You're the best, my Creator!";
    String has_given_upMessage = "Vanitas vanitatum et omnia vanitas...";
    String hangryMessage = "DO YOU WANT ME TO STARVE TO DEATH OR WHAT?!";
    String love_hungryMessage = "You are so nice to me... Please feed me...";
    String angry_inLoveMessage = "Oh, it's you! Maybe you'll forget to feed me again? Don't feel like talking, do you?";
    String defaultMessage = "It's good to be here.";
    
    private gotchiDictionary(){}
    
    public static gotchiDictionary getGotchiDictionary(){
        if(instance == null){
            instance = new gotchiDictionary();
        }
        return instance;
    }
}
