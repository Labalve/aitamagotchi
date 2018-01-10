package aitamagotchi;

/**
 *
 * @author Labalve
 */
public class gotchiDictionary {
    
    private static gotchiDictionary instance;
    
    String hungerMessage = "I'm so hungry...<br>";
    String lonelinessMessage = "I'm so lonely...<br>";
    String dirtinessMessage = "I'm so dirty...<br>";
    
    private gotchiDictionary(){}
    
    public static gotchiDictionary getGotchiDictionary(){
        if(instance == null){
            instance = new gotchiDictionary();
        }
        return instance;
    }
}
