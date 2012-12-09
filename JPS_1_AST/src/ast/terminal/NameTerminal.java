package ast.terminal;

import operatory.Interpreter;
import edu.pjwstk.jps.ast.terminal.INameTerminal;

public class NameTerminal extends TerminalExpression implements INameTerminal {

	public NameTerminal(String name) {
		super(name);
	}

	public String getName() {
		return (String) value;
	}

	@Override
	public void accept(Interpreter t) {
		t.visitNameTerminal(this);
	}

}
