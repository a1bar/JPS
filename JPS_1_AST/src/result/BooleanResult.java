package result;

import edu.pjwstk.jps.result.IBooleanResult;

public class BooleanResult extends SimpleResult<Boolean> implements
		IBooleanResult {

	public BooleanResult(boolean value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BooleanResult
				&& ((BooleanResult) obj).getValue() == getValue()) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "" + value;
	}

}
