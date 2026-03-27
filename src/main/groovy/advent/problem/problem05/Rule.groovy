package advent.problem.problem05

class Rule implements Comparable {
	Integer page
	List<Integer> before
	List<Integer> after

	Rule(Integer page) {
		this.page = page
		this.before = []
		this.after = []
	}

	/**
	 * This method determines if the update follows all rules for this page.
	 * An update follows all of this page's rules if no page that should
	 * print after it prints before it and vise versa.
	 * 
	 * @param updatePages The proposed update pages
	 * @return Whether the update is valid by this rule
	 */
	boolean doesUpdateMeetRule(List<Integer> updatePages) {
		final Tuple2<List, List> splitPages = splitListOnElement(updatePages, this.page)
		final boolean anyBeforePageOutOfOrder = splitPages.v1.any { this.after.contains(it) }
		final boolean anyAfterPageOutOfOrder = splitPages.v2.any { this.before.contains(it) }
		return !(anyBeforePageOutOfOrder || anyAfterPageOutOfOrder)
	}

	private static Tuple2<List, List> splitListOnElement(List list, Object element) {
		final int thisPageIndex = list.findIndexOf { it == element }
		switch (thisPageIndex) {
			case 0:
				return [[], list.subList(1, list.size())]
			case (list.size() - 1):
				return [list.subList(0, thisPageIndex), []]
			default:
				return [list.subList(0, thisPageIndex), list.subList(thisPageIndex + 1, list.size())]
		}
	}

	/**
	 * Note: this method assumes two things.
	 * 1) Rules are bi-directional. For a rule "A|B", there exists a rule such that
	 *     - Rule.page == 'A' and Rule.after contains 'B'
	 *     - Rule.page == 'B' and Rule.before contains 'A'
	 * 2) Pages that do not appear in any given rule do not have an associated Rule object
	 * (this.after or this.before *must* contain o)
	 * 
	 * I implemented {@link Problem05} with this in mind so this works.
	 */
	@Override
	int compareTo(Object o) {
		final Rule otherRule = o as Rule
		if (this.page == otherRule.page) {
			return 0
		}
		if (this.after.contains(otherRule.page)) {
			return -1
		}
		if (this.before.contains(otherRule.page)) {
			return 1
		}
	}
}