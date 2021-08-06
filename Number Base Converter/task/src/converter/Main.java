package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            String firstCommand = scanner.nextLine();

            if (firstCommand.equals("/exit")) {

                break;

            } else {

                String[] base = firstCommand.split(" ");
                int sourceBase = Integer.parseInt(base[0]);
                int targetBase = Integer.parseInt(base[1]);

                String secondCommand = "";

                while (true) {

                    System.out.println("Enter number in base " +
                            sourceBase + " to convert to base " + targetBase + " (To go back type /back)");

                    secondCommand = scanner.nextLine();

                    if (secondCommand.equals("/back")) break;


                    String result = "";


                    if (secondCommand.matches("[0-9a-z]+[\\.][0-9a-z]+")) {

                            String[] num = secondCommand.split("\\.");

                            BigInteger resInteger = new BigInteger(num[0], sourceBase);
                            String resultInteger = resInteger.toString(targetBase);

                            String resFractionalPart = num[1];

                            String resultFractionalPart = Fraction.convertBase(resFractionalPart, sourceBase, targetBase);

                            result = resultInteger + "." + resultFractionalPart;

                    } else {

                        BigInteger res = new BigInteger(secondCommand, sourceBase);
                        result = res.toString(targetBase);

                    }

                    System.out.println("Conversion result: " + result);

                }

            }


        }


    }

}
