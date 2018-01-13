/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aitamagotchi;

/**
 *
 * @author Labalve
 */
public class Message {
    
    static Gotchi tama = Gotchi.getGotchi("Ziomek");
    
    static int ID = 0;
    
    int timeToBeDisplayed = 5;
    int id;
    int timeDisplayed = 0;
    String content;
    Message() {
        this.content = tama.saySomething();
        id = ID++;
    }
    Message(String content) {
        this.content = content;
        id = ID++;
    }
    boolean isEnding(){
        if(++timeDisplayed > timeToBeDisplayed) return true;
        else return false;
    }
    String getContent(){
        return content;
    }
}
