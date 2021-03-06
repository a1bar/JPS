package result;

import edu.pjwstk.jps.result.IStringResult;

public class StringResult extends SimpleResult<String> implements IStringResult {

	public StringResult(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StringResult
				&& value.equals(((StringResult) obj).getValue()))
			return true;
		return false;
	}

	public String toString() {
		return value;
	}


}
