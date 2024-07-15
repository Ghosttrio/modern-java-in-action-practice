package parallel;

import java.util.stream.LongStream;

public class Side {

    public long sideEffectSum(long n) {
        Accmulator accmulator = new Accmulator();

        LongStream.rangeClosed(1, n).parallel().forEach(accmulator::add);

        return accmulator.total;
    }

    public class Accmulator {

        public long total = 0;

        public void add(long value) {
            total += value;
        }
    }

}
