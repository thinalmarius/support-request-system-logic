/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs.nlp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Thinal
 */
public class WordDetector {
    
    /*public static void main(String args[]){
        String mail="Motherboard has failed and RAM failed. Please check.";
        lineParser(mail);
    }*/
    static TokenizedLine tokenline = new TokenizedLine();
    static WordList wordlist = new WordList();
    
    public static TokenizedLine lineParser(String line) {
        
	ArrayList<String> stopwords = wordlist.wordLoader();
	String temp = line.toLowerCase().replaceAll("\\p{P}", "");
	List<String> array = Arrays.asList(temp.split("\\s+"));
	tokenline.setLineTag(array.get(0));
        
	//int beforeWordLocation = array.size();
        
	// build taggedword library
	HashMap<String, Integer> taggedWords = tokenline.newTaggedWords();
	HashMap<String, Integer> notWordCounter = tokenline.newNotWordsCounter();
	
	// tags the words according to the relative to the key word
	// and also count the number of times a word appear 
	for (int i = 0; i < array.size(); i++) {
            String word = array.get(i);
		
            if (stopwords.contains(word)) {
                // Counts the number of same word
                if (taggedWords.containsKey(word)){
                    taggedWords.put(word, taggedWords.get(word) + 1);                    
                } else{
                    taggedWords.put(word, 1);
                }
                    
            // tags the words which are not relative    
            }else if (!stopwords.contains(word)){
                
		if (notWordCounter.containsKey(word)) {                  
                    // Counts the number of same word
                    notWordCounter.put(word, notWordCounter.get(word) + 1);
                } else {
                    notWordCounter.put(word, 1);
                }
            }
			
        }
        
        tokenline.setTaggedWords(taggedWords);
        tokenline.setNotWordsCounter(notWordCounter);
        
        return tokenline;
		
    }
    
    public static int wordAnalyzer(TokenizedLine token){
        
        HashMap<String, Integer> analyzer = token.getTaggedWords();
        Map.Entry<String, Integer> maxEntry = null;
        int maxValue = 0;
        //Check the number of occurences of the tagged words in the string
        for (Map.Entry<String, Integer> entry : analyzer.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
                maxValue = maxEntry.getValue();
            }
        }
        
        return maxValue;
    }
    
    public static int urgencyCalculator(int maxLevel, int noOfLevels){
        
        int urgency=-1;
        if(maxLevel==0 && noOfLevels==0){
            urgency=0;
        }else if(maxLevel==1 && noOfLevels ==1 ){
            urgency=1;
        }else if((maxLevel==1 && noOfLevels ==2)||(maxLevel==2 && noOfLevels ==1) ){
            urgency=2;
        }else if(maxLevel<=2 && noOfLevels <3 ){
            urgency=3;
        }else if(maxLevel==2 && noOfLevels==3){
            urgency=4;
        }else
            urgency=5;
        
        return urgency;
    }
    
    public static String categorizeWord(String line){
        
        String temp = line.toLowerCase().replaceAll("\\p{P}", "");
	List<String> array = Arrays.asList(temp.split("\\s+"));
        ArrayList<String> hardware = wordlist.getHardwareWords();
        ArrayList<String> software = wordlist.getSoftwareWords();
        String category=null;
        int hardwareLevel=0;
        int softwareLevel=0;
        
        for (int i = 0; i < array.size(); i++) {
            String word = array.get(i);
            
            if(hardware.contains(word)){
                hardwareLevel=hardwareLevel+1;
            }else if(software.contains(word)){
                softwareLevel=softwareLevel+1;
            }
        }
        
        if(hardwareLevel<softwareLevel){
            category="software";
        }else
            category="hardware";
        
        return category;
    }
    
}
