package cz.cvut.fel;

public class Main {
    public static void main(String[] args) {
        char[][] numeric = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'},
                {'X', '0', 'A'},
        };

        char[][] directional = {
                {'X', '^', 'A'},
                {'<', 'v', '>'}
        };

        Controller startingController = new Controller(numeric);
        Controller secondcontroller = new Controller(directional);

    }
}
