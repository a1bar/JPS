package datastore;

import edu.pjwstk.jps.datastore.ISimpleObject;

public abstract class SimpleObject<T> extends SBAObject implements
		ISimpleObject<T> {
	//public static ArrayList<SimpleObject> allSimpleObjects = new ArrayList<SimpleObject>();

	public T value;

	public SimpleObject(String name, T value) {
		super(name);
		this.value = value;
		//allSimpleObjects.add(this);
		//SBAStore.allObjects.add(this);
	}

	@Override
	public T getValue() {
		return value;
	}

	public String toString() {

		String str = "<" + getOID() + ", " + getName() + ", " + getValue()
				+ "> " + this.getClass().getName();
		return str;
	}
}
