package interpreter.qres;

import java.util.Stack;

import edu.pjwstk.jps.interpreter.qres.IQResStack;
import edu.pjwstk.jps.result.IAbstractQueryResult;

public class QResStack implements IQResStack {
	
	public Stack<IAbstractQueryResult> stack = new Stack<IAbstractQueryResult>();

	@Override
	public IAbstractQueryResult pop() {
		return stack.pop();
	}

	@Override
	public void push(IAbstractQueryResult value) {
		stack.push(value);
	}
	
	public String toString(){
		String str = "";
		int i = 0;
		for (IAbstractQueryResult result : stack) {
			if (i != 0) {
				str += ", ";
			}
			str += result;
			i++;
		}
		return str;
	}

}
