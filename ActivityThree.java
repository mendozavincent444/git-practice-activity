import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActivityThree {

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public final static List<String> warCries = new ArrayList<>(Arrays.asList(
            "For freedom and justice!",
            "No retreat, no surrender!",
            "To victory, to triumph!",
            null));

    public static void main(String[] args) {


        char choice = 0;

        do {

            System.out.print("Enter distance: ");
            int distance = scanner.nextInt();

            System.out.print("Enter the number of horses: ");
            int horseCount = scanner.nextInt();


            List<Horse> horses = new ArrayList<>();

            for (int i = 0; i < horseCount; i++) {
                horses.add(
                        new Horse(
                                ("horse "+ (i + 1)).toLowerCase(),
                                random.nextBoolean(),
                                0,
                                warCries.get(random.nextInt(4))));
            }


            List<Horse> healthyHorses = horses
                    .stream()
                    .filter((horse -> horse.isHealthy()))
                    .collect(Collectors.toList());


            if (healthyHorses.size() < 2) {
                System.out.println("Not enough healthy horses.");
                choice = 'y';
                continue;
            }

            Random random = new Random();
            Deque<Horse> finishedHorses = new ArrayDeque<>();

            healthyHorses.parallelStream().forEach((horse) -> {

                horse.setName(horse.getName().toUpperCase());

                int distanceCovered = 0;
                int distanceLeft = distance;


                while (distanceLeft > 0) {
                    LocalDateTime timestamp = LocalDateTime.now();

                    String formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));

                    int randomDistance = random.nextInt(10) + 1;

                    distanceLeft = distanceLeft - randomDistance;

                    distanceCovered = distanceCovered + randomDistance;

                    horse.setDistanceTraveled(distanceCovered);

                    if (distanceLeft < 0) {
                        distanceLeft = 0;
                    }

                    System.out.println("[ " + formattedTimestamp + " ] " + horse.getName() + " ran " + randomDistance + " meter(s), distance remaining = " + distanceLeft);

                    if (distanceLeft == 0) {

                        String warCry = Optional.ofNullable(horse.getWarCry()).orElse("No war cry");

                        System.out.println(horse.getName() + " has finished the race." + " " + warCry);
                        finishedHorses.add(horse);
                    }
                }
            });

            System.out.println("Horse Ranking");
            finishedHorses.stream().forEach((horse -> System.out.println(horse.getName())));

            System.out.println("Aggregated distanceTraveled: " + healthyHorses
                    .stream()
                    .mapToInt((horse) -> horse.getDistanceTraveled())
                    .sum());

            System.out.print("Do you want to continue playing the horse racing game? (y) or (any key to quit): ");
            choice = scanner.next().charAt(0);

        } while (choice == 'y');



    }
}
