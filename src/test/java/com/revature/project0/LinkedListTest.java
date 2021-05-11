package com.revature.project0;

import com.revature.project0.util.LinkedList;
import com.sun.org.apache.bcel.internal.generic.ExceptionThrower;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LinkedListTest {

    private LinkedList<String> practiceList;

    @Before
    public void setUpTest() {
        practiceList = new LinkedList<>();
    }

    @After
    public void tearDownTest(){ practiceList = null; }

    @Test
    public void test_addWithNonNullValue() {
        //Arrange (prepare the test)
        int expectedSize = 1;

        //Act (do the test)
        practiceList.add("data");

        //Assert (ensure the results
        int actualSize = practiceList.size();
        Assert.assertEquals(expectedSize, actualSize);

    }


    @Test(expected = Exception.class)
    public void test_addWithNullValue() {
        // Arrange
        // sometimes blank if there's nothing in particular to do

        // Act
        practiceList.add(null);

        // Assert
        // sometimes blank, especially if you expect an exception to be thrown
    }


    @Test
    public void test_pollWithEmptyList() {
        // Arrange
        // nothing to do here...

        // Act
        String actualResult = practiceList.poll();

        // Assert
        Assert.assertNull(actualResult);
    }


    @Test
    public void test_pollWithPopulatedList() {
        // Arrange
        practiceList.add("test data 1");
        practiceList.add("test data 2");

        String expectedResult = "test data 1";
        int expectedSize = 1;

        // Act
        String actualResult = practiceList.poll();
        int actualSize = practiceList.size();

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSize, actualSize);
    }


    @Test (expected = NullPointerException.class)
    public void test_peekWithEmptyList() {
        // Arrange
        //do nothing

        String expectedResult = null;
        int expectedSize = 0;

        // Act
        String actualResult = practiceList.peek();
        int actualSize = practiceList.size();

        // Assert
        Assert.assertEquals(expectedSize, actualSize);
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void test_peekWithPopulatedList() {
        // Arrange
        practiceList.add("test data 1");
        practiceList.add("test data 2");
        practiceList.add("test data 3");

        String expectedResult = "test data 1";
        int expectedSize = 3;

        // Act
        String actualResult = practiceList.peek();
        int actualSize = practiceList.size();

        // Assert
        Assert.assertEquals(expectedSize, actualSize);
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void test_containsWithEmptyList() {
        // Arrange
        int expectedSize = 0;
        String falseResult = "test data 6";

        // Act
        int actualSize = practiceList.size();

        // Assert
        Assert.assertFalse(practiceList.contains(falseResult));
    }


    @Test
    public void test_containsWithPopulatedList() {
        // Arrange
        practiceList.add("test data 1");
        practiceList.add("test data 2");
        practiceList.add("test data 3");
        practiceList.add("test data 4");
        practiceList.add("test data 5");

        String expectedResult1 = "test data 1";
        String expectedResult2 = "test data 2";
        String expectedResult3 = "test data 3";
        String expectedResult4 = "test data 4";
        String expectedResult5 = "test data 5";
        String falseResult = "test data 6";
        int expectedSize = 5;

        // Act
        //do nothing

        // Assert
        Assert.assertTrue(practiceList.contains(expectedResult1));
        Assert.assertTrue(practiceList.contains(expectedResult2));
        Assert.assertTrue(practiceList.contains(expectedResult3));
        Assert.assertTrue(practiceList.contains(expectedResult4));
        Assert.assertTrue(practiceList.contains(expectedResult5));
        Assert.assertFalse(practiceList.contains(falseResult));
        int actualSize = practiceList.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

}
