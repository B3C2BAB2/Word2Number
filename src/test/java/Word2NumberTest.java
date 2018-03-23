import enums.SimplifyEnum;
import exception.IllegalInputException;
import org.junit.Test;
import utils.Word2Number;

import java.util.Collections;
import java.util.List;

public class Word2NumberTest {
  @Test
  public void testTransform() {
    String testString;
    String[] testStringArray = new String[]{"one", "o", "zero", "five", "nine"};
    System.out.println();
    System.out.println("test transform");

    testString = Word2Number.transform(Collections.singletonList("zero"));
    System.out.println(testString);
    assert "0".equals(testString);

    testString = Word2Number.transform(Collections.singletonList("zero"), false);
    System.out.println(testString);
    assert "0".equals(testString);

    testString = Word2Number.transform(testStringArray);
    System.out.println(testString);
    assert "10059".equals(testString);

    testString = Word2Number.transform(testStringArray, false);
    System.out.println(testString);
    assert "10059".equals(testString);

    testString = Word2Number.transform("Hundred", false);
    System.out.println(testString);
    assert "100".equals(testString);

    testString = Word2Number.transform("Minus Hundred Dot One Two Three Four Five", true);
    System.out.println(testString);
    assert "-100.12345".equals(testString);

    testString = Word2Number.transform("Dot One Two Three Four Five");
    System.out.println(testString);
    assert "0.12345".equals(testString);

    testString = Word2Number.transform("one two");
    System.out.println(testString);
    assert "12".equals(testString);

    testString = Word2Number.transform("one thousand and five");
    System.out.println(testString);
    assert "1005".equals(testString);

    testString = Word2Number.transform("two thousand and sixteen");
    System.out.println(testString);
    assert "2016".equals(testString);

    testString = Word2Number.transform("nineteen ninety nine");
    System.out.println(testString);
    assert "1999".equals(testString);

    testString = Word2Number.transform("six billion");
    System.out.println(testString);
    assert "6000000000".equals(testString);

    testString = Word2Number.transform("seven million");
    System.out.println(testString);
    assert "7000000".equals(testString);

    testString = Word2Number.transform("point nine");
    System.out.println(testString);
    assert "0.9".equals(testString);

    testString = Word2Number.transform("one point nine");
    System.out.println(testString);
    assert "1.9".equals(testString);

    testString = Word2Number.transform("two point nine five four");
    System.out.println(testString);
    assert "2.954".equals(testString);

    testString = Word2Number.transform("eight thousand nine hundred and thirty one");
    System.out.println(testString);
    assert "8931".equals(testString);

    testString = Word2Number.transform("one hundred and twenty nine thousand million");
    System.out.println(testString);
    assert "129000000000".equals(testString);

    testString = Word2Number.transform("one million and eighty nine hundred hundred");
    System.out.println(testString);
    assert "1890000".equals(testString);

    testString = Word2Number.transform("hundred hundred");
    System.out.println(testString);
    assert "10000".equals(testString);

    testString = Word2Number.transform("thousand hundred");
    System.out.println(testString);
    assert "1100".equals(testString);

    testString = Word2Number.transform("negative point five");
    System.out.println(testString);
    assert "-0.5".equals(testString);

    Word2Number.SIMPLIFY_STANDARD= SimplifyEnum.HUNDRED;
    testString = Word2Number.transform("thousand hundred");
    System.out.println(testString);
    assert "11 hundred".equals(testString);

    Word2Number.SIMPLIFY_STANDARD=SimplifyEnum.THOUSAND;
    testString = Word2Number.transform("thousand hundred");
    System.out.println(testString);
    assert "1.1 thousand".equals(testString);

    Word2Number.SIMPLIFY_STANDARD=SimplifyEnum.MILLION;
    testString = Word2Number.transform("two million");
    System.out.println(testString);
    assert "2 million".equals(testString);

    Word2Number.SIMPLIFY_STANDARD=SimplifyEnum.BILLION;
    testString = Word2Number.transform("eleven billion");
    System.out.println(testString);
    assert "11 billion".equals(testString);

    Word2Number.SIMPLIFY_STANDARD=SimplifyEnum.NONE;
  }

  @Test
  public void testExtract() {
    List<List<String>> resultList;
    System.out.println();
    System.out.println("test extract");

    resultList = Word2Number.extract("the nineteen ninety nine was past nineteen years");
    resultList.forEach(x -> {
      x.forEach(y -> System.out.print(y + ' '));
      System.out.println();
    });
    assert resultList.size() == 2;
  }

  @Test
  public void testReplace() {
    String result;
    System.out.println();
    System.out.println("test replace");

    result = Word2Number.replace("the nineteen ninety nine has past nineteen years");
    System.out.println(result);
    assert "the 1999 has past 19 years".equals(result);

    result = Word2Number.replace("the score is point nine");
    System.out.println(result);
    assert "the score is 0.9".equals(result);

    result = Word2Number.replace("the score is one point nine");
    System.out.println(result);
    assert "the score is 1.9".equals(result);

    result = Word2Number.replace("the score is two point nine five four");
    System.out.println(result);
    assert "the score is 2.954".equals(result);

    result = Word2Number.replace("you got one point");
    System.out.println(result);
    assert "you got 1 point".equals(result);

    result = Word2Number.replace("minus one point five plus minus three point five equals minus five");
    System.out.println(result);
    assert "-1.5 plus -3.5 equals -5".equals(result);

    result = Word2Number.replace("i have ten thousand three hundred point");
    System.out.println(result);
    assert "i have 10300 point".equals(result);

    result = Word2Number.replace("i have ten thousand three hundred point thousand point");
    System.out.println(result);
    assert "i have 10300 point 1000 point".equals(result);

    result = Word2Number.replace("i have two point three billion dollars");
    System.out.println(result);
    assert "i have 2.3 billion dollars".equals(result);
  }

  @Test
  public void testException(){
    try {
      Word2Number.transform("asd");
    }catch (IllegalInputException e){

    }
  }
}
