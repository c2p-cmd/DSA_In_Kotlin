package extras.trng;

import java.util.Map;

public class RunnerApp {
    public static void main(String[] args) throws InvalidResponseException {
        RandomORG r1 = new RandomORG();
        String map = r1.generateIntegerSequence(
                5,
                6,
                10,
                25,
                true,
                NumberBases.Decimal.getBase()
        );

        Map<String, String> map1 = r1.generateString(3, 8, "ABCDEGHIJKLMNOPQRSTUVWXYZ.#", false);

        String value1 = RandomORGKt.DATA;
        System.out.println(value1);

    }
}
