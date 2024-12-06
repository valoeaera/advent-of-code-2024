package advent

import advent.problem.Problem
import advent.problem.problem01.Problem01
import advent.problem.problem02.Problem02

class Advent {
    private static final Map<String, Problem> problems = [
        "1" : new Problem01(),
        "2" : new Problem02()
    ]

    static void main(String[] args) {
        Problem problem = problems.get(args[0])
        problem.parseInput()

        println "First Solution: ${problem.solve()}"
        println "Advanced Solution: ${problem.solveAdvanced()}"
    }
}