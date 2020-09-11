package one.digitalinnovation.Threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ExecutorExample {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {

        House house = new House(new Bedroom());

        List<? extends Future<String>> futures =
                new CopyOnWriteArrayList<>(house.getHouseActivity().stream()
                .map(activity -> threadPool.submit(() -> {
                        try {
                            return activity.perform();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                )
                .collect(Collectors.toList()));

        while (!futures.stream().allMatch(Future::isDone)) {
            int numberOfNonFinishedActivities = 0;
            for (Future<?> future : futures)  {
                if (future.isDone()) {
                    try {
                        System.out.println("Congrats, you done " + future.get());
                        futures.remove(future);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    numberOfNonFinishedActivities++;
                }
            }
            System.out.println("Number of non-finished activities: " + numberOfNonFinishedActivities);
            Thread.sleep(500);
        };

        threadPool.shutdown();

    }
}

class House {
    private List<Room> rooms;

    House(Room... rooms) {
        this.rooms = Arrays.asList(rooms);
    }

    List<Activity> getHouseActivity() {
        return this.rooms.stream().map(Room::getRoomActivity)
                .reduce(new ArrayList<Activity>(), (pivot, activities) -> {
                    pivot.addAll(activities);
                    return pivot;
                });
    }
}

interface Activity {
    String perform() throws InterruptedException;
}

abstract class Room {
    abstract List<Activity> getRoomActivity();
}

class Bedroom extends Room {

    @Override
    List<Activity> getRoomActivity() {

        return Arrays.asList(
                this::makeBed,
                this::sweepRoom,
                this::tidyWardrobe
        );

        // Other way to do the list
        // ArrayList<Activity> objects = new ArrayList<>();
        // objects.add(this::makeBed);
        // objects.add(this::sweepRoom);
        // objects.add(this::tidyWardrobe);

    }

    private String makeBed() throws InterruptedException {
        Thread.sleep(5000);
        String makeTheBad = "Making the bed";
        System.out.println(makeTheBad);
        return makeTheBad;
    }

    private String sweepRoom() throws InterruptedException {
        Thread.sleep(7000);
        String sweepRoom = "Sweeping the room";
        System.out.println(sweepRoom);
        return sweepRoom;
    }

    private String tidyWardrobe() throws InterruptedException {
        Thread.sleep(10000);
        String TidyWardrobe = "Tidying up the wardrobe";
        System.out.println(TidyWardrobe);
        return TidyWardrobe;
    }
}