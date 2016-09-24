/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs.nlp;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thinal
 */
public class WordDetectorTest {
    
    public WordDetectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lineParser method, of class WordDetector.
     */
    @Test
    public void testLineParser() {
        System.out.println("lineParser");
        String line = "failed and error";
        TokenizedLine expResult = new TokenizedLine();
        HashMap<String, Integer> taggedWords = expResult.newTaggedWords();
        taggedWords.put("failed", 1);
        //taggedWords.put("error", 1);
        expResult.setTaggedWords(taggedWords);
        TokenizedLine result = WordDetector.lineParser(line);
        HashMap<String, Integer> taggedResult = result.getTaggedWords();
        assertEquals(taggedWords, taggedResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of wordAnalyzer method, of class WordDetector.
     */
    @Test
    public void testWordAnalyzer() {
        System.out.println("wordAnalyzer");
        TokenizedLine token = new TokenizedLine();
        HashMap<String, Integer> tokennedWords = token.newTaggedWords();
        tokennedWords.put("error", 1);
        tokennedWords.put("failed", 2);
        tokennedWords.put("severe", 1);
        int expResult = 2;
        int result = WordDetector.wordAnalyzer(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of urgencyCalculator method, of class WordDetector.
     */
    @Test
    public void testUrgencyCalculator() {
        System.out.println("urgencyCalculator");
        int maxLevel = 1;
        int noOfLevels = 1;
        int expResult = 1;
        int result = WordDetector.urgencyCalculator(maxLevel, noOfLevels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of categorizeWord method, of class WordDetector.
     */
    @Test
    public void testCategorizeWord() {
        System.out.println("categorizeWord");
        String line = "motherboard";
        String expResult = "hardware";
        String result = WordDetector.categorizeWord(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
