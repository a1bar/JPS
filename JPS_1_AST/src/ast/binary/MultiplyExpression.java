package ast.binary;

import ast.Expression;
import edu.pjwstk.jps.ast.binary.IMultiplyExpression;
import edu.pjwstk.jps.visitor.ASTVisitor;

public class MultiplyExpression extends BinaryExpression implements
		IMultiplyExpression {

	public MultiplyExpression(Expression expLeft, Expression expRight) {
		super(expLeft, expRight);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitMultiplyExpression(this);

	}

}
