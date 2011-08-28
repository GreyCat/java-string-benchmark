Java string operations benchmark
================================

This small project is a simple benchmark for simple string-oriented
algorithms in order to find a most optimal solution, given execution
in a Java Virtual Machine.

The problem
-----------

The problem is stripping all the control characters in a given string
- that is, removing these characters, replacing then with nothing.

Definition of "control character" is vague, but for most purposes, it
would be acceptable to assume that characters with ASCII/Unicode codes
[0..31] are control characters and all others are not.

Structure of project
--------------------

Benchmark consists of running several algorithms
repeatedly. Basically, everything that's going to be tested is just
hardcoded in main() method of `TestStringStrip.java`. There are 3
basic types of modules needed for testing:

* *String sources* - modules that somehow get the strings ready and
supply them as needed. Need to implement interface `StringSource`
which defines only one simple method: `String nextString()`. Using
various string sources dramatically affects which strings are
processed, thus affects optimization routes JVM takes and ultimately
affects performance. Examples for these modules include passing the
same constant string over and over, generation of random strings with
given properties, reading strings from a local file or receiving them
from a network connection.

* *Processing algorithms* - modules that implement processing
algorithms itself. These classes must implement `StripAlgorithm` and
thus define a single method `String strip(String)`.

* *String destinations* - modules that consume strings after being
processed by algorithm. If there would be no consumers for strings,
JVM could ultimately optimize the whole processing thing out, so no
processing would happen at all and algorithm would "work"
instantly. Simplest form of string destination is `SimpleCount` which
does simplest and fastest non-trivial string content analysis
operation which won't be optimized out. More complex examples could
include writing a string to local file or sending it over the network.

Given this structure, basically, testing consists of:

* Create one or more of objects to test (StringSource, StripAlgorithm,
StringDestination), for example:

    StringSource src = new SingleString();
    StringDestination dst = new SimpleCount();
    StripAlgorithm alg[] = new StripAlgorithm[] {
            new StringReplaceAll(),
            new MatcherReplace(),
            new StringBuilderCodePoint(),
    };

* Run tests for algorithms' correctness (checks that all outputs for
all algorithms match):

    assureCorrectness(10000, src, alg);

* Run tests to measure algorithms' speed:

    benchmarkSpeed(1000000, src, dst, alg);
