import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StoreCredit {
    public static void calculate() throws IOException {
        Long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(CodeJam.class.getResourceAsStream("storecredit.txt")))) {
            Integer total = Integer.valueOf(br.readLine());
            for (int iteration = 1; iteration < total + 1; iteration++) {
                int credit = Integer.valueOf(br.readLine());
                int amountOfProducts = Integer.valueOf(br.readLine());
                String productsrRaw = br.readLine();
                String[] products = productsrRaw.split(" ");
                int[] parsedProducts = Arrays.stream(products).mapToInt(Integer::valueOf).toArray();
                int productOneIndex;
                int productTwoIndex = 0;
                boolean found = false;
                for (productOneIndex = 0; productOneIndex < amountOfProducts - 1; productOneIndex++) {
                    for (productTwoIndex = productOneIndex + 1; productTwoIndex <= amountOfProducts - 1; productTwoIndex++) {
                        if ((parsedProducts[productOneIndex] + parsedProducts[productTwoIndex]) == credit) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                System.out.println("Case #" + iteration + ": " + (productOneIndex + 1) + " " + (productTwoIndex + 1));
            }
            Long stop = System.currentTimeMillis();
            System.out.println(stop - start);

        }
    }
}
