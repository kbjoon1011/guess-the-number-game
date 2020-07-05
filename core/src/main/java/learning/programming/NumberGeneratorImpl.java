package learning.programming;

import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator {

    // == Fields ==
    private final Random random = new Random();
    private int MaxNumber = 100;
    @Override
    public int next() {
        return random.nextInt(MaxNumber);
    }

    @Override
    public int getMaxNumber() {
        return MaxNumber;
    }
}
