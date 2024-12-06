package advent.problem.problem01

import advent.problem.Problem

class Problem01 implements Problem {
    private static final String INPUT_PATH = "src/main/resources/01/input1.txt"
    private List<String> leftList = []
    private List<String> rightList = []

    @Override
    public String solve() {
        final List<String> sortedLeft = this.leftList.sort()
        final List<String> sortedRight = this.rightList.sort()
        int totalDifference = 0

        sortedLeft.eachWithIndex((String location, Integer index) -> {
            int diff = Math.abs(location.toInteger() - sortedRight.get(index).toInteger())
            totalDifference += diff
        })

        return totalDifference
    }

    @Override
    public String solveAdvanced() {
        Map<String, int> counts = [:]
        int similarityScore = 0

        this.rightList.each((String location) -> {
            int currentRightCount = counts.get(location) ?: 0
            counts.put(location, ++currentRightCount)
        })

        this.leftList.each((String location) -> {
            int totalRightCount = counts.get(location) ?: 0
            similarityScore += (location.toInteger() * totalRightCount)
        })

        return similarityScore
    }

    @Override
    public void parseInput() {
        File file = new File(INPUT_PATH)
        file.withReader((BufferedReader reader) -> {
            String line
            while((line = reader.readLine()) != null) {
                final List<String> splitLine = line.split(" ")
                this.leftList.add(splitLine.first())
                this.rightList.add(splitLine.last())
            }
        })
    }
} 