package support.verfication;

public class Verifier {
    public static void verifyEqual(String actualResult, String expectedResult){
        if(!actualResult.equals(expectedResult)){
            System.out.printf("Actual is %s\nExpected is %s\n", actualResult, expectedResult);
            throw new AssertionError("Actual result is diff with expected result");
        }
    }
}
