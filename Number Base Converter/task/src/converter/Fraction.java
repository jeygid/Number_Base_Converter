package converter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Fraction {

    protected static Map<String, Integer> alphabet = new HashMap<>() {{

        put("a", 10);
        put("b", 11);
        put("c", 12);
        put("d", 13);
        put("e", 14);
        put("f", 15);
        put("g", 16);
        put("h", 17);
        put("i", 18);
        put("j", 19);
        put("k", 20);
        put("l", 21);
        put("m", 22);
        put("n", 23);
        put("o", 24);
        put("p", 25);
        put("q", 26);
        put("r", 27);
        put("s", 28);
        put("t", 29);
        put("u", 30);
        put("v", 31);
        put("w", 32);
        put("x", 33);
        put("y", 34);
        put("z", 35);

    }};

    public static String convertBase(String fraction, int sourceBase, int targetBase) {

        // Additional zeroes for correct length

        if (fraction.length() < 6) {
            StringBuilder fractionBuilder = new StringBuilder(fraction);
            for (int i = 0; i <= 7 - fractionBuilder.length(); i++) {
                fractionBuilder.append("0");
            }
            fraction = fractionBuilder.toString();
        }


        StringBuilder intermediateResult1 = new StringBuilder();
        StringBuilder intermediateResult2 = new StringBuilder();
        String finalResult = "";

        if (sourceBase == targetBase) {

            intermediateResult2 = new StringBuilder("0." + fraction);

        } else {

            /**
             * 1 stage
             * Converting the fraction to decimal base
             * */

            if (sourceBase != 10) {

                double sum = 0;
                int valInt = 0;

                for (int i = 1; i <= 6; i++) {

                    String valString = fraction.substring(i - 1, i);

                    char valChar = valString.charAt(0);

                    if (Character.getNumericValue(valChar) >= 10 && Character.getNumericValue(valChar) <= 35) {

                        valInt = alphabet.get(valString);

                    } else {

                        valInt = Integer.parseInt(valString);

                    }

                    double t = valInt * Math.pow(sourceBase, -i);

                    sum += t;

                }

                intermediateResult1 = new StringBuilder("0." + String.valueOf(sum).substring(2));

            } else {

                intermediateResult1 = new StringBuilder("0." + fraction);

            }

            /**
             * 2 stage
             * Converting to targeting base
             * */

            if (targetBase != 10) {

                String intermediateValue = "";
                String[] intermediateArray;

                BigDecimal val;
                BigDecimal startValue = new BigDecimal(intermediateResult1.toString());

                intermediateResult2 = new StringBuilder();

                for (int i = 0; i <= 5; i++) {

                    val = startValue.multiply(BigDecimal.valueOf(targetBase));

                    intermediateArray = val.toPlainString().split("\\.");

                    intermediateValue = intermediateArray[0];

                    int interValue = Integer.parseInt(intermediateValue);

                    if (interValue >= 10 && interValue <= 35) {
                        for (Map.Entry<String, Integer> entry : alphabet.entrySet()) {
                            if (entry.getValue().equals(interValue)) {
                                intermediateValue = entry.getKey();
                            }
                        }
                    }

                    intermediateResult2.append(intermediateValue);

                    startValue = new BigDecimal("0." + intermediateArray[1]);

                }

                intermediateResult2 = new StringBuilder("0." + intermediateResult2);


            } else {

                intermediateResult2 = intermediateResult1;

            }
        }

        // You can use intermediateResult2 if you want to get integer and fractional part together

        String[] resultArray = intermediateResult2.toString().split("\\.");
        finalResult = resultArray[1].substring(0,5);


        return finalResult;

    }
}
