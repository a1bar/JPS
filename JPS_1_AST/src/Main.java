import edu.pjwstk.jps.ast.*;

/** @author �ukasz Kobyli�ski (s6709), Borys Wojciechowski (s6427) **/
public class Main {

	public static void main(String[] args) {
		Expression ex = new DotExpression(new DotExpression(
				new WhereExpression(new NameTerminal("osoba"),
						new NameTerminal("�onaty")),
				new NameTerminal("ksi��ka")), new NameTerminal("autor"));
	}
}
