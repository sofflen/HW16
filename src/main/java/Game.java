import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Game {

    public void rockPaperScissors() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        int ai = 0;
        int winByAI = 0;
        int winByPlayer = 0;
        File file = new File(File.separator + "RPS_results");
        File file2 = new File(File.separator + "RPS_results" + File.separator + "results.txt");
        String separator = "===============";
        String victory = "achieved Epic Victory Royale!";

        if (!file.exists())
            file.mkdirs();
        try {
            file2.createNewFile();
        } catch (IOException ignored) {
        }

        System.out.printf("%s%n%s%n%s%n%s%n", separator, "Welcome to  Rock Paper Scissors game!",
                "Please, enter the number of won rounds needed for epic Victory Royale!", separator);
        int rounds = sc.nextInt();
        System.out.printf("%s%n%s%n%s%n%s%n%s%n", separator,
                "Choose one of the followings:", "1 - Rock, 2 - Paper, 3 - Scissors",
                "If you want to stop the game, enter <0>", separator);

        while (true) {
            String result = String.format("Total score: You >> %d, AI >> %d", winByPlayer, winByAI);
            double random = Math.random();
            if (random < 0.34)
                ai = 1;
            else if (random > 0.33 && random < 0.67)
                ai = 2;
            else if (random > 0.66)
                ai = 3;

            if (winByAI == rounds) {
                String totalResult = String.format("%n%s%n%s %s%n%s%n%s%n", separator, "AI has",
                        victory, result, separator);
                System.out.println(totalResult);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file2, true))) {
                    bw.append(dtf.format(now));
                    bw.append(totalResult);
                } catch (IOException ignored) {
                }
                break;
            } else if (winByPlayer == rounds) {
                String totalResult = String.format("%n%s%n%s %s%n%s%n%s%n", separator, "You have",
                        victory, result, separator);
                System.out.println(totalResult);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file2, true))) {
                    bw.append(dtf.format(now));
                    bw.append(totalResult);
                } catch (IOException ignored) {
                }
                break;
            }
            int choose = sc.nextInt();
            if (choose == 0) {
                if (isYorN())
                    break;
            } else if (ai == 1 && choose == 3 || ai == 2 && choose == 1 || ai == 3 && choose == 2) {
                ++winByAI;
                System.out.println("AI >> " + ai);
                System.out.println("You've lost the round!");
            } else if (ai == choose) {
                System.out.println("AI >> " + ai);
                System.out.println("Tie!");
            } else if (choose > 3) {
                System.out.println("Please, choose from 1 to 3!");
            } else {
                ++winByPlayer;
                System.out.println("AI >> " + ai);
                System.out.println("You've won the round!");
            }
        }

    }

    private boolean isYorN() {
        Scanner sc = new Scanner(System.in);
        System.out.println("The game's not over yet. Do you really want to quit? Y/N");
        String answer = sc.next();

        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("Thanks for game! Good luck!");
            return true;
        } else if (answer.equalsIgnoreCase("N")) {
            System.out.println("Then let's continue!");
            return false;
        } else
            isYorN();
        return true;
    }
}

