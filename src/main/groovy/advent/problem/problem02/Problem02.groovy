package advent.problem.problem02

import advent.problem.Problem

class Problem02 implements Problem {
    private static final String INPUT_PATH = "src/main/resources/02/input1.txt"

    private static class Slope {
        private int slopeNumber
        private List<String> levels = []

        public Slope(int slopeNumber, List<String> levels) {
            this.slopeNumber = slopeNumber
            this.levels = levels
        }

        public boolean isSafe() {
            int maxIncrease = 0
            int maxDecrease = 0
            boolean hasUnchangedLevel = false
            int currentLevelIndex = 1

            while (currentLevelIndex < this.levels.size() && (currentLevelIndex < 2 || checkGrade(maxIncrease, maxDecrease, hasUnchangedLevel))) {
                int thisValue = this.levels.get(currentLevelIndex).toInteger()
                int prevValue = this.levels.get(currentLevelIndex - 1).toInteger()
                int grade = thisValue - prevValue

                maxIncrease = Math.max(maxIncrease, grade)
                maxDecrease = Math.max(maxDecrease, grade * -1)
                hasUnchangedLevel = grade == 0

                currentLevelIndex++
            }

            return checkGrade(maxIncrease, maxDecrease, hasUnchangedLevel)
        }

        public boolean isSafeWithDampener() {
            if (!this.isSafe()) {
                boolean safeWithDamper = false
                int currentLevelIndex = 0

                while(!safeWithDamper && currentLevelIndex < this.levels.size()) {
                    safeWithDamper = new Slope(0, without(this.levels, currentLevelIndex)).isSafe()
                    currentLevelIndex++
                }

                return safeWithDamper
            }

            return true
        }

        private static boolean checkGrade(int increase, int decrease, boolean hasUnchangedLevel) {
            return increase <= 3 && decrease <= 3 && (increase == 0 || decrease == 0) && !hasUnchangedLevel
        }

        private static List without(List list, Integer index) {
            return list.subList(0, index) + list.subList(index + 1, list.size())
        }
    }

    List<Slope> slopes = []

    @Override
    public String solve() {
        return this.slopes.findAll((Slope slope) -> slope.isSafe()).size()
    }

    @Override String solveAdvanced() {
        return this.slopes.findAll((Slope slope) -> slope.isSafeWithDampener()).size()
    }

    @Override
    public void parseInput() {
        File file = new File(INPUT_PATH)
        file.withReader((BufferedReader reader) -> {
            String line
            int slopeNumber = 0
            while((line = reader.readLine()) != null) {
                final List<String> splitLine = line.split(" ")
                this.slopes.add(new Slope(++slopeNumber, splitLine))
            }
        })
    }
}