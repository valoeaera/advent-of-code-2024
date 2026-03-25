package advent.problem

interface Problem {
    /**
     * This method returns the answer to the easy version of the problem.
     */
    public String solve()

    /**
     * This method returns the answer to the difficult version of the problem.
     */
    public String solveAdvanced()

    /**
     * This method parses the input file into the problem's preferred format.
     */
    public void parseInput()
}