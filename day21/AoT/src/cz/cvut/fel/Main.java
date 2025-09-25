package cz.cvut.fel;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<String> inputs = List.of("379A");
        List<String> inputs = List.of("029A", "980A", "179A", "456A", "379A");
        List<String> myInputs = List.of("140A", "180A", "176A", "805A", "638A");


        char[][] numeric = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'},
                {'X', '0', 'A'},
        };
        final Coord numericStart = new Coord(2, 3);
        char[][] directional = {
                {'X', '^', 'A'},
                {'<', 'v', '>'}
        };
        final Coord directionalStart = new Coord(2, 0);

        int result = 0;
        for (String input : inputs) {
            Controller startingController = new Controller(numeric, numericStart);
            final String firstControllerResult = startingController.getInstructions(input);
//            System.out.println(firstControllerResult);

            Controller secondcontroller = new Controller(directional, directionalStart);;
            final String secondControllerResult = secondcontroller.getInstructions(firstControllerResult);
//            System.out.println(secondControllerResult);

            Controller thirdController = new Controller(directional, directionalStart);;
            final String thirdControllerResult = thirdController.getInstructions(secondControllerResult);
//            System.out.println(thirdControllerResult);

            final int stringValue = getIntFromString(input);
            final int lenghtOfCode = thirdControllerResult.length();

            System.out.println(lenghtOfCode + "*" + stringValue + " = " + stringValue * lenghtOfCode);
            result += (stringValue * lenghtOfCode);
        }

        System.out.println(result);
    }

    private static int getIntFromString(String input) {
        return Integer.parseInt(input.substring(0, input.length() - 1));
    }
}
