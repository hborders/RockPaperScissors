package com.github.hborders.rockpaperscissors;

public class WonRoundCount implements Comparable<WonRoundCount> {
	private int rawWonRoundCount;

	WonRoundCount(int rawWonRoundCount) {
		this.rawWonRoundCount = rawWonRoundCount;
	}

	public void increment() {
		rawWonRoundCount++;
	}

	public boolean matches(WonRoundCount wonRoundCount) {
		return rawWonRoundCount == wonRoundCount.rawWonRoundCount;
	}

	@Override
	public int compareTo(WonRoundCount wonRoundCount) {
		return new Integer(rawWonRoundCount)
				.compareTo(wonRoundCount.rawWonRoundCount);
	}

	@Override
	public String toString() {
		return "WonRoundCount[" + rawWonRoundCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rawWonRoundCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WonRoundCount other = (WonRoundCount) obj;
		if (rawWonRoundCount != other.rawWonRoundCount)
			return false;
		return true;
	}

	public static class Provider {
		public WonRoundCount provide(int rawWonRoundCount) {
			return new WonRoundCount(rawWonRoundCount);
		}
	}
}
