package advent

import advent.problem.Problem
import advent.problem.problem01.Problem01

class Advent {
    private static final Map<String, Problem> problems = [
        "1" : new Problem01()
    ]

    static void main(String[] args) {
        println problems.get(args[0]).solve()
    }
}