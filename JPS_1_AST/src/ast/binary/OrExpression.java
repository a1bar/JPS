package ast.binary;

import ast.Expression;
import edu.pjwstk.jps.ast.binary.IOrExpression;
import edu.pjwstk.jps.visitor.ASTVisitor;

public class OrExpression extends BinaryExpression implements IOrExpression {

	public OrExpression(Expression expLeft, Expression expRight) {
		super(expLeft, expRight);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(ASTVisitor visitor) {
		// TODO Auto-generated method stub

	}

}
