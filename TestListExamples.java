import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;
import java.util.ArrayList;

class LongerThan2 implements StringChecker {
  public boolean checkString(String s) {
    if (s.length() > 2) {
      return true;
    }
    return false;
  }
}

class isHello implements StringChecker {
  public boolean checkString(String s) {
    if (s.equals("Hello")) {
      return true;
    }
    return false;
  }
}

public class TestListExamples {
	@Test 
	public void testGeneralFilter() {
    List<String> input1 = new ArrayList<String>();
    input1.add("Hello"); input1.add("Hell"); input1.add("He");
    List<String> expected = new ArrayList<String>();
    expected.add("Hello"); expected.add("Hell");

    StringChecker sc = new LongerThan2();
    input1 = ListExamples.filter(input1, sc);
    assertEquals(expected, input1);
	}

  @Test 
	public void testEmptyFilter() {
    List<String> input1 = new ArrayList<String>();
    input1.add("Hell"); input1.add("He");
    List<String> expected = new ArrayList<String>();

    StringChecker sc = new isHello();
    input1 = ListExamples.filter(input1, sc);
    assertEquals(expected, input1);
	}

  @Test 
	public void testGeneralMerge() {
    List<String> input1 = new ArrayList<String>();
    input1.add("a"); input1.add("b"); input1.add("d");
    List<String> input2 = new ArrayList<String>();
    input2.add("c"); input2.add("e"); input2.add("f");
    List<String> expected = new ArrayList<String>();
    expected.add("a"); expected.add("b"); expected.add("c");
    expected.add("d"); expected.add("e"); expected.add("f");

    List<String> result = ListExamples.merge(input1, input2);
    assertEquals(expected, result);
	}

  @Test 
	public void testEmptyFirstMerge() {
    List<String> input1 = new ArrayList<String>();
    List<String> input2 = new ArrayList<String>();
    input2.add("d"); input2.add("e"); input2.add("f");
    List<String> expected = new ArrayList<String>();
    expected.add("d"); expected.add("e"); expected.add("f");

    List<String> result = ListExamples.merge(input1, input2);
    assertEquals(expected, result);
	}
}
