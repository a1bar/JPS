package ast.binary;

import ast.Expression;
import edu.pjwstk.jps.ast.binary.IInExpression;
import edu.pjwstk.jps.visitor.ASTVisitor;

public class InExpression extends BinaryExpression implements IInExpression {

	public InExpression(Expression expLeft, Expression expRight) {
		super(expLeft, expRight);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitInExpression(this);

	}

}
