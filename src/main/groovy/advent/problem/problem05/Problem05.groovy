package advent.problem.problem05

import advent.problem.Problem

class Problem05 implements Problem {
	private static final String INPUT_PATH = 'src/main/resources/05/input1.txt'

	private TreeMap<Integer, Rule> rulesByPageNumber = [:]
	private List<List<Integer>> proposedUpdates = []

	@Override
	String solve() {
		int count = 0
		this.proposedUpdates.each { update ->
			final List<Rule> rules = update.findResults {
				this.rulesByPageNumber.getOrDefault(it, null)
			} as List<Rule>
			final int rulesPassed = rules.count { it.doesUpdateMeetRule(update) }

			if (rulesPassed == rules.size()) {
				count += retrieveMiddlePage(update)
			}
		}
		return count
	}

	@Override
	String solveAdvanced() {
		int count = 0
		this.proposedUpdates.each { update ->
			List<Rule> rules = []
			List<Integer> pagesWithoutRules = []
			update.each { page ->
				final Rule rule = this.rulesByPageNumber.getOrDefault(page, null)
				if (rule) {
					rules.add(rule)
				} else {
					pagesWithoutRules.add(page)
				}
			}
			final int rulesPassed = rules.count { it.doesUpdateMeetRule(update) }

			if (rulesPassed < rules.size()) {
				rules.sort(Comparator.naturalOrder())
				final List<Integer> fixedPages = rules.collect { it.page } + pagesWithoutRules
				count += retrieveMiddlePage(fixedPages)
			}
		}
		return count
	}

	@Override
	void parseInput() {
		boolean isRules = true

        File file = new File(INPUT_PATH)
        file.withReader((BufferedReader reader) -> {
            String line
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                	isRules = false
                } else if (isRules) {
                	final List<Integer> newRule = line.split(/\|/).collect { Integer.parseInt(it) }
                	Rule rule1 = this.retrieveRule(newRule[0])
                	Rule rule2 = this.retrieveRule(newRule[1])
                	rule1.after.add(newRule[1])
                	rule2.before.add(newRule[0])
                	this.rulesByPageNumber.put(newRule[0], rule1)
                	this.rulesByPageNumber.put(newRule[1], rule2)
                } else {
                	this.proposedUpdates.add(line.split(',').collect { Integer.parseInt(it) })
                }
            }
        })
	}

	/**
	 * This method retrieves the existing Rule object for the given page or creates a new one if it doesn't exist.
	 * 
	 * @param page The page to retrieve the rules for
	 * @return A Rule object for that page
	 */
	private Rule retrieveRule(Integer page) {
		return this.rulesByPageNumber.keySet().contains(page) ? this.rulesByPageNumber.get(page) : new Rule(page)
	}

	/**
	 * This method retrieves the middle page of a list that contains an odd number of pages.
	 * 
	 * @param pages The list of pages
	 * @return The middle page
	 */
	private static int retrieveMiddlePage(List<Integer> pages) {
		return pages[Math.floor(pages.size() / 2)]
	}
}