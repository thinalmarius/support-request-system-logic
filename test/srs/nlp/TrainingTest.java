/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs.nlp;

import datalayer.Words;
import java.util.ArrayList;
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
public class TrainingTest {
    
    public TrainingTest() {
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
     * Test of map method, of class Training.
     */
    @Test
    public void testMap() {
        System.out.println("map");
        String line = "failed";
        int expResult = 1;
        int result = Training.map(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of train method, of class Training.
     */
    @Test
    public void testTrain() {
        System.out.println("train");
        TokenizedLine tokenizedLine = null;
        ArrayList<Words> expResult = null;
        ArrayList<Words> result = Training.train(tokenizedLine);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
