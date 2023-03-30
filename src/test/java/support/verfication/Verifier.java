package support.verfication;

public class Verifier {
    public static void verifyEqual(String actualResult, String expectedResult){
        if(!actualResult.equals(expectedResult)){
            System.out.printf("Actual is %s\nExpected is %s\n", actualResult, expectedResult);
            throw new AssertionError("Actual result is diff with expected result");
        }
    }

    public static void verifyActualContainsExpected(String actualResult, String expectedResult){
        if(!actualResult.contains(expectedResult)){
            System.out.printf("Actual is %s\nExpected is %s\n", actualResult, expectedResult);
            throw new AssertionError("Actual result is diff with expected result");
        }
    }

    public static void verifyExpectedContainsActual(String actualResult, String expectedResult){
        if(!expectedResult.contains(actualResult)){
            System.out.printf("Actual is %s\nExpected is %s\n", actualResult, expectedResult);
            throw new AssertionError("Actual result is diff with expected result");
        }
    }
    public static void verifyNotEmpty(String actual){
        if(actual.equals("")){
            System.out.print("Actual is empty");
            throw new AssertionError("Actual result is diff with expected result");
        }
    }

    public static Boolean verifyTrue(Boolean actual){
        return actual;
    }
}
