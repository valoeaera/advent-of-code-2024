package advent.problem.problem03

import advent.problem.Problem

import java.util.regex.Matcher
import java.util.regex.Pattern

class Problem03 implements Problem {
    private static final String INPUT_PATH = "src/main/resources/03/input1.txt"
    /**
     * This regex finds all values like "mul(2,4)" where '2' and '4' can be any 1-3 digit number.
     */
    private static final String VALID_MULTIPLY_OPERATION = /mul\((\d{1,3}+),(\d{1,3}+)\)/
    private static final String VALID_DO_OPERATION = /do\(\)/
    private static final String VALID_DONT_OPERATION = /don't\(\)/

    private String lines = ""

    @Override
    public String solve() {
        return solveForString(lines)
    }

    @Override String solveAdvanced() {
        String enabledOperations = ""

        this.lines.split(VALID_DO_OPERATION).each((String lineWithDont) -> {
            enabledOperations += lineWithDont.split(VALID_DONT_OPERATION)[0]
        })

        return solveForString(enabledOperations)
    }

    @Override
    public void parseInput() {
        File file = new File(INPUT_PATH)
        file.withReader((BufferedReader reader) -> {
            String line
            while((line = reader.readLine()) != null) {
                this.lines += line
            }
        })
    }

    private static String solveForString(String input) {
        int multiplicationTotal = 0

        Matcher multiplyMatcher = input =~ VALID_MULTIPLY_OPERATION
        multiplyMatcher.each((List<String> match) -> {
            multiplicationTotal += (match[1].toInteger() * match[2].toInteger())
        })

        return multiplicationTotal
    }
}