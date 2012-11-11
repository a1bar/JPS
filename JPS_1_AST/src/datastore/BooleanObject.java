package datastore;

import edu.pjwstk.jps.datastore.IBooleanObject;

public class BooleanObject extends SimpleObject<Boolean> implements
		IBooleanObject {

	public BooleanObject(String name, boolean value) {
		super(name, value);
	}

}
