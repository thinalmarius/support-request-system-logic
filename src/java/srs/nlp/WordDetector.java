/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs.nlp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;




/**
 *
 * @author Thinal
 */
public class WordDetector {
    
    /*public static void main(String args[]){
        String mail="Motherboard has failed and RAM failed. Please check.";
        lineParser(mail);
    }*/
    
    public static int lineParser(String line) {
        
	TokenizedLine tl = new TokenizedLine();
        WordList wl = new WordList();
        ArrayList<String> stopwords = wl.wordLoader();
	String temp = line.toLowerCase().replaceAll("\\p{P}", "");
	List<String> array = Arrays.asList(temp.split("\\s+"));
	tl.setLineTag(array.get(0));
	int beforeWordLocation = array.size();
        
	// build taggedword library
	HashMap<String, Integer> taggedWords = tl.newTaggedWords();
	HashMap<String, Integer> notWordCounter = tl.newNotWordsCounter();
	
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
        
        tl.setTaggedWords(taggedWords);
        tl.setNotWordsCounter(notWordCounter);
        
        Training train = new Training(tl,wl);
        // max number that a keyword appeared in the line
        int maxLevel = wordAnalyzer(tl);
        // number of keywords in the line
        int noOfLevels = taggedWords.size();
        
        int urgency = urgencyCalculator(maxLevel,noOfLevels);
        
        return urgency;
		
    }
    
    public static int wordAnalyzer(TokenizedLine tL){
        
        HashMap<String, Integer> analyzer = tL.getTaggedWords();
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
    
}
