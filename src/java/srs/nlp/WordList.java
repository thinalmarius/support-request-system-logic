/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs.nlp;

import datalayer.Datalink;
import datalayer.Datalink_Service;
import datalayer.Words;
import java.util.ArrayList;

/**
 *
 * @author Thinal
 */
public class WordList {
    static Datalink_Service service = new Datalink_Service();
    static Datalink proxy = service.getDatalinkPort();
    
    /* loads keywords */
    public ArrayList<String> wordLoader(){
        ArrayList<String> stopwords = (ArrayList<String>) proxy.getStopWords();
        return stopwords;
    }
    
    /* loads the words kept for learning */
    public ArrayList<Words> learnWords(){
        ArrayList<Words> newWords = (ArrayList<Words>)proxy.getNewWords();
        return newWords;
    }
    
    public ArrayList<String> excludeList(){
        ArrayList<String> words = (ArrayList<String>) proxy.excludeList();
        return words;
    }
    
    public ArrayList<String> getHardwareWords(){
        ArrayList<String> words = (ArrayList<String>) proxy.getHardwareCategory();
        return words;
    }
    
    public ArrayList<String> getSoftwareWords(){
        ArrayList<String> words = (ArrayList<String>) proxy.getSoftwareCategory();
        return words;
    }
}
