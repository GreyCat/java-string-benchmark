package ru.greycat;

import java.util.Arrays;

import ru.greycat.algorithms.strip.*;
import ru.greycat.stringdestinations.*;
import ru.greycat.stringsources.*;

public class TestStringStrip {
    public static void assureCorrectness(int iterations, StringSource src, StripAlgorithm... alg) throws Exception {
        String answer;
        System.out.print("Assuring that all algorithms are correct... ");
        for (int i = 0; i < iterations; i++) {
            String question = src.nextString();
            answer = alg[0].strip(question);
            for (int j = 1; j < alg.length; j++) {
                if (!answer.equals(alg[j].strip(question))) {
                    throw new RuntimeException("Incorrect algorithm " + alg[j].getClass().getSimpleName() + " on sample string \"" + question + "\"");
                }
            }
        }
        System.out.println("OK");
    }

    static class Result implements Comparable<Result> {
        private String name;
        private double result;

        Result(String name, double result) {
            this.name = name;
            this.result = result;
        }

        @Override
        public int compareTo(Result other) {
            if (result < other.result) {
                return -1;
            } else if (result > other.result) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void benchmarkSpeed(int iterations, StringSource src, StringDestination dst, StripAlgorithm... alg) throws Exception {
        Result[] speed = new Result[alg.length];

        System.out.println("\nBenchmarking at " + iterations + " strings");

        for (int j = 0; j < alg.length; j++) {
            StripAlgorithm a = alg[j];
            System.out.print(a.getClass().getSimpleName() + "\t");
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < iterations; i++)
                dst.collect(a.strip(src.nextString()));
            long t2 = System.currentTimeMillis();
            long dt = t2 - t1;
            speed[j] = new Result(a.getClass().getSimpleName(), iterations * 1000.0 / dt);
            System.out.println("" + dt + "\t" + speed[j].result);
            System.gc();
        }

        Arrays.sort(speed);
        System.out.println("\nRankings:");
        Result prev = null;
        for (int j = 0; j < speed.length; j++) {
            System.out.format("%2d. %-40s%.2f\t%.2fx\t+%.1f%%\n",
                    speed.length - j, speed[j].name,
                    speed[j].result,
                    speed[j].result / speed[0].result,
                    (prev != null ? (speed[j].result / prev.result - 1) * 100 : 0)
            );
            prev = speed[j];
        }
    }

    public static void main(String[] args) throws Exception {
//      StringSource src = new SingleString();
//        StringSource src = new MultipleStrings(1000000, 0.01);
        StringSource src = new MultipleStrings(1000000, 1);
        StringDestination dst = new SimpleCount();

        StripAlgorithm alg[] = new StripAlgorithm[] {
//                new StringReplaceAll(),
//                new MatcherReplace(),
//                new StringBuilderCodePoint(),
//                new StringBuilderChar(),
//                new ArrayOfCharFromStringCharAt(),
//                new ArrayOfCharFromArrayOfChar(),
//                new RatchetFreak1(),
//                new RatchetFreak2(),
//                new ArrayOfByteUTF8String(),
//                new ArrayOfByteUTF8Const(),
//                new ArrayOfByteWindows1251(),
//                new EdStaub1(),
//                new RatchetFreak2EdStaub1GreyCat1(),
//                new RatchetFreak2EdStaub1GreyCat2(),
//                new EdStaub1GreyCat1(),
                new Voo1(),
        };

        assureCorrectness(10000, src, alg);
        benchmarkSpeed(1000000, src, dst, alg);
        benchmarkSpeed(10000000, src, dst, alg);
    }
}
