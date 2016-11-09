package codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class RopeIntranet {
    public static void calculate() throws IOException {
        Long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(StoreCredit.class.getResourceAsStream("ropeintranet.txt")))) {
            int amountOfCases = Integer.valueOf(br.readLine());
            int currentCase = 1;
            while (currentCase < amountOfCases + 1) {
                int amountOfFloors = Integer.valueOf(br.readLine());
                Map<Integer, Integer> windowsA = new HashMap<>();
                int iterations = amountOfFloors;
                while (iterations != 0) {
                    String windowsUnparsed = br.readLine();
                    String[] windows = windowsUnparsed.split(" ");
                    windowsA.put(Integer.valueOf(windows[0]), Integer.valueOf(windows[1]));
                    iterations--;
                }
                int amountOfInterSections = 0;
                Iterator<Integer> windowIterator = windowsA.keySet().iterator();
                while (windowIterator.hasNext()) {
                    boolean remove = false;
                    int windowA = windowIterator.next();
                    int windowB = windowsA.get(windowA);
                    for (int windowToCheck : windowsA.keySet()) {
                        if ((windowToCheck > windowA && windowsA.get(windowToCheck) < windowB) || (windowToCheck < windowA && windowsA.get(windowToCheck) > windowB)) {
                            amountOfInterSections++;
                            remove = true;
                        }
                    }
                    if (remove) {
                        windowIterator.remove();
                    }
                }
                System.out.println("Case #" + currentCase + ": " + amountOfInterSections);
                currentCase++;
            }
        }
        Long stop = System.currentTimeMillis();
        System.out.println(stop - start);
    }
}

