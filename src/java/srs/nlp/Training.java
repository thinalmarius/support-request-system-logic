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
import java.util.HashMap;
import java.util.Map;
import static srs.nlp.WordDetector.urgencyCalculator;
import static srs.nlp.WordDetector.wordAnalyzer;

/**
 *
 * @author Thinal
 */
public class Training {
    
    static Datalink_Service service = new Datalink_Service();
    static Datalink proxy = service.getDatalinkPort();
    
    static HashMap<String, Integer> notWords;
    static ArrayList<String> excludeWords;
    static ArrayList<Words> learnWords;
    static ArrayList<String> words;
    static ArrayList<Words> newWords;
    static HashMap<String, Integer> taggedWords;
    
        
    public static int map(String line){
        TokenizedLine tokenizedLine = WordDetector.lineParser(line);
        taggedWords = tokenizedLine.getTaggedWords();
        // max number that a keyword appeared in the line
        int maxLevel = wordAnalyzer(tokenizedLine);
        // number of keywords in the line
        int noOfLevels = taggedWords.size();
        
        int urgency = urgencyCalculator(maxLevel,noOfLevels);
        train(tokenizedLine);
        return urgency;
    }
    
    public static ArrayList<Words> train(TokenizedLine tokenizedLine){
        
        WordList wordList = new WordList();
        notWords=tokenizedLine.getNotWordsCounter();
        System.out.println(notWords.keySet());
        excludeWords=wordList.excludeList();
        learnWords=wordList.learnWords();
        Words newWordsMap = new Words();
        ArrayList<String> checkList=new ArrayList<String>();
        ArrayList<String> sortedList=new ArrayList<String>();
        
        ArrayList<String> word = new ArrayList<String>();
        
        int max=3;
        
        //compares the scores of the not tagged words
        //words with high scores will be selected
        for (Map.Entry<String, Integer> entry : notWords.entrySet())
        {
            Map.Entry<String, Integer> maxEntry = null;
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;

                if(maxEntry.getValue().compareTo(max)>0){
                words.add(maxEntry.getKey());

                }

            }
        }
        if(words!=null){
            //checks whether word contains in exclude list
            //if not, adds it into an array
            int i = words.size();
            int j = excludeWords.size();

            if(i<j){
                for(i=0;i<words.size();i++){
                    if(words.get(i)!=excludeWords.get(i)){
                        checkList.add(words.get(i));
                    }
                }
            }else if(j<i){
                for(j=0;j<excludeWords.size();j++){
                    if(words.get(j)!=excludeWords.get(j)){
                        checkList.add(words.get(i));
                    }
                }
            }
            
            //gets the scores of the filtered words
            int[] scores = new int[checkList.size()];

            for(int k=0;k<checkList.size();k++){
                scores[k]=notWords.get(checkList.get(k));
            }
            int n = scores.length;
            int temp = 0;

            //sorts word scoring 
            for (int k = 0; k < (n-1); k++) {
                for (int l = 1; l < (n-k); l++) {

                    if (scores[l - 1] < scores[l]) {
                        temp = scores[l - 1];
                        scores[l - 1] = scores[l];
                        scores[l] = temp;
                    }

                }
            }

            //gets first 5 words from according to scores
            for(Map.Entry<String, Integer> entry : notWords.entrySet()) {
                for(int k=0;k<=5;k++){
                    if(scores[k] != 0 && scores[k]==entry.getValue()) {
                        sortedList.add(entry.getKey());
                        break;
                    }
                }
            }

            for(int k=0;k<=5;k++){
                int score=0;

                for(Words w : learnWords){
                    word.add(w.getWord());

                    if(word.size()<learnWords.size()){
                        continue;
                    }
                    //checks new word array with stored word list

                    if(word.contains(sortedList.get(k))){
                        score=w.getScore()+1;
                        newWordsMap.setWord(sortedList.get(k));
                        newWordsMap.setScore(score);
                        newWords.add(newWordsMap);

                    }else if(!word.contains(sortedList.get(k))){
                        newWordsMap.setWord(sortedList.get(k));
                        newWordsMap.setScore(1);
                        newWords.add(newWordsMap);
                    }
                }
            }
            
            return  newWords;
        }else
            return null;
        
    }
    
    
}
