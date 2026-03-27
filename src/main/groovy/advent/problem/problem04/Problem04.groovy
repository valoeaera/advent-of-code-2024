package advent.problem.problem04

import advent.problem.Problem

class Problem04 implements Problem {
	private static final String INPUT_PATH = 'src/main/resources/04/input1.txt'

	private List<String> lines = []

	@Override
	public String solve() {
		int count = 0
		this.lines.eachWithIndex { String line, Integer lineIndex ->
			final List<Integer> xIndices = findAllIndicesOf(line, 'X')
			count += xIndices?.sum { int xIndex ->
				final String mSlots = this.findSurroundingCharacters(lineIndex, xIndex, 1)
				final String aSlots = this.findSurroundingCharacters(lineIndex, xIndex, 2)
				final String sSlots = this.findSurroundingCharacters(lineIndex, xIndex, 3)

				return (0..8).count { mSlots[it] == 'M' && aSlots[it] == 'A' && sSlots[it] == 'S' }
			} ?: 0
		}
		return count
	}

	@Override
	public String solveAdvanced() {
		int count = 0
		this.lines.eachWithIndex { String line, Integer lineIndex ->
			final List<Integer> aIndices = findAllIndicesOf(line, 'A')
			count += aIndices?.sum { int aIndex ->
				final String surroundingCharacters = this.findSurroundingCharacters(lineIndex, aIndex, 1)
				return isCrossMAS(surroundingCharacters) ? 1 : 0
			} ?: 0
		}
		return count
	}

	@Override
    public void parseInput() {
    	List<String> fileLines = []
        File file = new File(INPUT_PATH)
        file.withReader((BufferedReader reader) -> {
            String line
            while((line = reader.readLine()) != null) {
                fileLines.add(line)
            }
        })

        final int lineLength = fileLines.first().size() + 6
        // Pad the input to prevent indexing issues
        (1..3).each { this.lines.add(buildBufferLine(lineLength)) }
        fileLines.each { line -> this.lines.add("...${line}...") }
        (1..3).each { this.lines.add(buildBufferLine(lineLength)) }
    }

    /**
     * This method finds the indices of every occurence of the given character in the given string.
     * 
     * @param line The string to check
     * @param character The chracter to check for
     * @return A list of the index of time the character appears in the string
     */
    private static List<Integer> findAllIndicesOf(String line, String character) {
    	(0..line.size() - 1).findAll { index -> line[index] == character }
    }

    /**
     * This method returns a String containing the characters surrounding a passed character index in a straight line.
     * 
     * @param verticalIndex The index of the current line within the list of lines
     * @param horizontalIndex The index of the current character within the current line
     * @param distance The distance in a straight line to select surrounding characters
     */
 	private String findSurroundingCharacters(int verticalIndex, int horizontalIndex, int distance) {
 		final List<Integer> surroundingIndices = [(-1 * distance), 0, distance]
 		return surroundingIndices.collect { verticalAdjust ->
 			surroundingIndices.collect { horizontalAdjust ->
 				this.lines[verticalIndex + verticalAdjust][horizontalIndex + horizontalAdjust]
			}.join()
		}.join()
 	}

 	/**
 	 * This method determines if a set of surrounding characters is a valid cross-MAS.
 	 * 
 	 * @param surroundingCharacters A string containing the characters surrounding an 'A'
 	 * @return Whether the characters contain a valid cross-MAS
 	 */
 	private static boolean isCrossMAS(String surroundingCharacters) {
 		// A Cross-MAS is a A with 'M's and 'S's kitty-corner each other
 		final Set<String> validOptions = ['MS', 'SM']
 		final boolean downward = validOptions.contains(surroundingCharacters[0] + surroundingCharacters[8])
		final boolean upward = validOptions.contains(surroundingCharacters[2] + surroundingCharacters[6])
		return upward && downward
 	}

    /**
     * This method builds a buffer line of '.' characters of the given length.
     * 
     * @param length The desired length of the buffer string
     * @return The buffer string
     */
    private static String buildBufferLine(int length) {
    	return (1..length).collect { '.' }.join()
    }
}