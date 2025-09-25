package cz.cvut.fel;

import java.util.HashMap;
import java.util.function.Function;

public class Controller {
    private final char[][] keyMap;
    private final Coord start;
    private final HashMap<Character, Coord> keyLocations = new HashMap<>();

    public Controller(char[][] keyMap, Coord start) {
        this.keyMap = keyMap;
        this.start = start;

        for (int i = 0; i < keyMap.length; i++) {
            for (int j = 0; j < keyMap[i].length; j++) {
                char key = keyMap[i][j];
                keyLocations.put(key, new Coord(j, i));
            }
        }
    }

    public String getInstructions(String input) {
        StringBuilder result = new StringBuilder();
        Coord curLocation = start.copy();

        for (char c : input.toCharArray()) {
            Coord target = keyLocations.get(c).copy();
            final String appendix = goToLocation(curLocation, target, new StringBuilder());
            result.append(appendix);
            curLocation = target;
        }
        return result.toString();
    }

    private String goToLocation(Coord curLocation, Coord nextLocation, StringBuilder result) {
        if (curLocation.equals(nextLocation)) {
            result.append(ManualController.confirm());
            return result.toString();
        }

        boolean wasLastMoveX = result.length() > 0 && (
                result.charAt(result.length() - 1) == ManualController.goLeft() ||
                        result.charAt(result.length() - 1) == ManualController.goRight()
        );

        if (wasLastMoveX) {
            if (tryMoveX(curLocation, nextLocation, result)) return goToLocation(curLocation, nextLocation, result);
            if (tryMoveY(curLocation, nextLocation, result)) return goToLocation(curLocation, nextLocation, result);
        }else
        {
            if (tryMoveY(curLocation, nextLocation, result)) return goToLocation(curLocation, nextLocation, result);
            if (tryMoveX(curLocation, nextLocation, result)) return goToLocation(curLocation, nextLocation, result);
        }
        return null;
    }

    private boolean tryMoveY(Coord curLocation, Coord nextLocation, StringBuilder result) {
        final int moveY = Integer.compare(nextLocation.getY(), curLocation.getY());
        if (moveY != 0) {
            if (keyMap[curLocation.getY() + moveY][curLocation.getX()] != 'X') {
                curLocation.setY(curLocation.getY() + moveY);
                char appendix = moveY > 0 ? ManualController.goDown() : ManualController.goUp();
                result.append(appendix);
                return true;
            }
        }
        return false;
    }

    private boolean tryMoveX(Coord curLocation, Coord nextLocation, StringBuilder result) {
        final int moveX = Integer.compare(nextLocation.getX(), curLocation.getX());
        if (moveX != 0) {
            if (keyMap[curLocation.getY()][curLocation.getX() + moveX] != 'X') {
                curLocation.setX(curLocation.getX() + moveX);
                char appendix = moveX > 0 ? ManualController.goRight() : ManualController.goLeft();
                result.append(appendix);
                return true;
            }
        }
        return false;
    }

    private static class ManualController{
        public static char goRight(){
            return '>';
        }

        public static char goLeft(){
            return '<';
        }

        public static char goUp(){
            return '^';
        }

        public static char goDown(){
            return 'v';
        }

        public static char confirm(){
            return 'A';
        }
    }
}
