/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs.nlp;

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
        String line = "";
        TokenizedLine expResult = null;
        TokenizedLine result = WordDetector.lineParser(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wordAnalyzer method, of class WordDetector.
     */
    @Test
    public void testWordAnalyzer() {
        System.out.println("wordAnalyzer");
        TokenizedLine token = null;
        int expResult = 0;
        int result = WordDetector.wordAnalyzer(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of urgencyCalculator method, of class WordDetector.
     */
    @Test
    public void testUrgencyCalculator() {
        System.out.println("urgencyCalculator");
        int maxLevel = 0;
        int noOfLevels = 0;
        int expResult = 0;
        int result = WordDetector.urgencyCalculator(maxLevel, noOfLevels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of categorizeWord method, of class WordDetector.
     */
    @Test
    public void testCategorizeWord() {
        System.out.println("categorizeWord");
        String line = "";
        String expResult = "";
        String result = WordDetector.categorizeWord(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
