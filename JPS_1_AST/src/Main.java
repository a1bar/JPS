import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.pjwstk.jps.result.ISingleResult;
import edu.pjwstk.jps.result.IStructResult;

import qres.QResStack;
import qres.collection.BagResult;
import qres.single.BinderResult;
import qres.single.BooleanResult;
import qres.single.DoubleResult;
import qres.single.IntegerResult;
import qres.single.StringResult;
import qres.single.StructResult;
import ast.Expression;
import ast.binary.CommaExpression;
import ast.binary.DotExpression;
import ast.binary.EqualsExpression;
import ast.binary.GreaterThanExpression;
import ast.binary.InExpression;
import ast.binary.WhereExpression;
import ast.terminal.IntegerTerminal;
import ast.terminal.NameTerminal;
import ast.terminal.StringTerminal;
import ast.unary.BagExpression;
import ast.unary.CountExpression;

/**
 * @author �ukasz Kobyli�ski (s6709)
 * @author Borys Wojciechowski (s6427)
 **/
public class Main {

	public static void main(String[] args) {
		// mini-projekt 1
		Expression exampleFromPdf = new DotExpression(new DotExpression(
				new WhereExpression(new NameTerminal("osoba"),
						new NameTerminal("�onaty")),
				new NameTerminal("ksi��ka")), new NameTerminal("autor"));

		// zad. 1
		Expression expression1 = new WhereExpression(new NameTerminal("osoba"),
				new GreaterThanExpression(new CountExpression(new NameTerminal(
						"imie")), new IntegerTerminal(1)));

		// zad. 2
		Expression expression2 = new WhereExpression(new NameTerminal("firma"),
				new InExpression(new NameTerminal("lokalizacja"),
						new BagExpression(new CommaExpression(
								new StringTerminal("Warszawa"),
								new StringTerminal("��d�")))));

		// zad. 3
		Expression expression3 = new InExpression(new BagExpression(
				new CommaExpression(new IntegerTerminal(1),
						new IntegerTerminal(2))), new BagExpression(
				new CommaExpression(new CommaExpression(new IntegerTerminal(1),
						new IntegerTerminal(2)), new IntegerTerminal(3))));

		// zad. 4
		Expression expression4 = new DotExpression(new WhereExpression(
				new NameTerminal("firma"), new EqualsExpression(
						new NameTerminal("nazwa"), new StringTerminal("XYZ"))),
				new WhereExpression(new NameTerminal("zatrudnia"),
						new EqualsExpression(new NameTerminal("nazwisko"),
								new StringTerminal("Kowalski"))));
		
		QRES_zadanie_1();		
		QRES_zadanie_2();

	}

	public void QRES_example() {
		QResStack qres = new QResStack();
		qres.push(new IntegerResult(1));
		qres.push(new IntegerResult(2));
		qres.push(new IntegerResult(3));
		IntegerResult multiRight = (IntegerResult) qres.pop(); // 3
		IntegerResult multiLeft = (IntegerResult) qres.pop(); // 2
		IntegerResult multiRes = new IntegerResult(multiLeft.getValue()
				* multiRight.getValue());
		qres.push(multiRes);
		IntegerResult plusRight = (IntegerResult) qres.pop(); // 6
		IntegerResult plusLeft = (IntegerResult) qres.pop(); // 1
		IntegerResult plusRes = new IntegerResult(plusLeft.getValue()
				+ plusRight.getValue());
		qres.push(plusRes);
		qres.push(new IntegerResult(4));
		IntegerResult minusRight = (IntegerResult) qres.pop(); // 4
		IntegerResult minusLeft = (IntegerResult) qres.pop(); // 7
		IntegerResult minusRes = new IntegerResult(minusLeft.getValue()
				- minusRight.getValue());
		qres.push(minusRes);
	}
	
	public static void QRES_zadanie_1() {
		System.out.println("zadanie_1");
		
		QResStack qres1 = new QResStack();
		qres1.push(new IntegerResult(1)); // 1
		qres1.push(new DoubleResult(2.1)); // 2
		DoubleResult structRight = (DoubleResult) qres1.pop();
		IntegerResult structLeft = (IntegerResult) qres1.pop();
		StructResult s1 = new StructResult();
		s1.add(structLeft);
		s1.add(structRight);
		qres1.push(s1); // 3
		StructResult s2 = (StructResult) qres1.pop();
		
		List<ISingleResult> list_1 = s2.elements();
		
		BagResult b1 = new BagResult();
		b1.add(list_1.get(0));
		b1.add(list_1.get(1));
		qres1.push(b1); // 4
		
		System.out.println(s2);
		System.out.println(b1);
		
		qres1.push(new IntegerResult(3)); // 5
		qres1.push(new IntegerResult(4)); // 6
		IntegerResult plusRight2 = (IntegerResult) qres1.pop();
		IntegerResult plusLeft2 = (IntegerResult) qres1.pop();
		IntegerResult plusRes2 = new IntegerResult(plusLeft2.getValue()
				+ plusRight2.getValue());
		qres1.push(plusRes2); // 7
		qres1.push(new StringResult("test")); // 8
		StringResult structRight2 = (StringResult) qres1.pop();
		IntegerResult structLeft2 = (IntegerResult) qres1.pop();
		StructResult s3 = new StructResult();
		s3.add(structLeft2);
		s3.add(structRight2);
		qres1.push(s3); // 9
		
		System.out.println(s3);
		
		StructResult s4 = (StructResult) qres1.pop();
		List<ISingleResult> list_2 = s4.elements();
		BagResult b2 = new BagResult();
		b2.add(list_2.get(0));
		b2.add(list_2.get(1));
		qres1.push(b2); // 10
		
		System.out.println(b2);
		
		//iloczyn kartezjanski
		BagResult b3 = (BagResult) qres1.pop();
		BagResult b4 = (BagResult) qres1.pop();
		
		ArrayList<ISingleResult> arr1 = (ArrayList<ISingleResult>) b3.getElements();
		ArrayList<ISingleResult> arr2 = (ArrayList<ISingleResult>) b4.getElements();
		
		StructResult b3res = new StructResult();
		StructResult b3res2 = new StructResult();
		StructResult b4res = new StructResult();
		StructResult b4res2 = new StructResult();
		
		b3res.add(arr1.get(0));
		b3res.add(arr2.get(0));
		
		b3res2.add(arr1.get(0));
		b3res2.add(arr2.get(1));
		
		b4res.add(arr1.get(1));
		b4res.add(arr2.get(0));
		
		b4res2.add(arr1.get(1));
		b4res2.add(arr2.get(1));
		
		BagResult b5 = new BagResult();
		b5.add(b3res);
		b5.add(b3res2);
		b5.add(b4res);
		b5.add(b4res2);
		
		qres1.push(b5); // 11	
		
		System.out.println(b5);
		
		BagResult b6 = (BagResult) qres1.pop();
		BinderResult br1 = new BinderResult("nazwa", b6);
		qres1.push(br1); // 12
		
		System.out.println(br1);		
	}
	
	public static void QRES_zadanie_2(){
		System.out.println("\nzadanie_2");
		
		QResStack qres = new QResStack();
        qres.push(new StringResult("ala")); // 1
        qres.push(new StringResult("ma")); // 2
        StringResult string1 = (StringResult) qres.pop();
        StringResult string2 = (StringResult) qres.pop();
        StructResult struct1 = new StructResult();
        struct1.add(string2);
        struct1.add(string1);
        qres.push(struct1); // 3
        
        System.out.println(struct1);
        
        qres.push(new StringResult("kota")); // 4
        
        StringResult string3 = (StringResult) qres.pop();
        StructResult struct2 = (StructResult) qres.pop();
        
        StringResult string4 = (StringResult) struct2.elements().get(0);
        StringResult string5 = (StringResult) struct2.elements().get(1);
        
        StructResult struct3 = new StructResult();
        struct3.add(string4);
        struct3.add(string5);
        struct3.add(string3);
        qres.push(struct3); // 5
        
        System.out.println(struct3);
        
        StructResult struct4 = (StructResult) qres.pop();
		
		List<ISingleResult> list_1 = struct4.elements();
		
		BagResult b1 = new BagResult();
		b1.add(list_1.get(0));
		b1.add(list_1.get(1));
		b1.add(list_1.get(2));
		qres.push(b1); // 4
		
		System.out.println(b1);
		
		qres.push(new IntegerResult(8)); // 7
        qres.push(new IntegerResult(10)); // 8
        IntegerResult multiRight = (IntegerResult) qres.pop();
        IntegerResult multiLeft = (IntegerResult) qres.pop();
        IntegerResult multiRes = new IntegerResult(multiLeft.getValue()
                        * multiRight.getValue());
        qres.push(multiRes); // 9
        qres.push(new BooleanResult(false)); // 10     
        BooleanResult bool1 = (BooleanResult) qres.pop();
        IntegerResult int4 = (IntegerResult) qres.pop();
        StructResult struct5 = new StructResult();
        struct5.add(int4);
        struct5.add(bool1);
        qres.push(struct5); // 11
		
		System.out.println(struct5);
		
		StructResult struct6 = (StructResult) qres.pop();
		BagResult b2 = (BagResult) qres.pop();
		
		List<ISingleResult> list_2 = struct6.elements(); //struct
		ArrayList<ISingleResult> arr1 = (ArrayList<ISingleResult>) b2.getElements(); //bag
		
		StructResult b3res = new StructResult();
		StructResult b3res2 = new StructResult();
		StructResult b4res = new StructResult();
		
		b3res.add(arr1.get(0));
		b3res.add(list_2.get(0));
		b3res.add(list_2.get(1));
		
		b3res2.add(arr1.get(1));
		b3res2.add(list_2.get(0));
		b3res2.add(list_2.get(1));
		
		b4res.add(arr1.get(2));
		b4res.add(list_2.get(0));
		b4res.add(list_2.get(1));
		
		BagResult b5 = new BagResult();
		b5.add(b3res);
		b5.add(b3res2);
		b5.add(b4res);
		
		qres.push(b5); // 12	
		
		System.out.println(b5);	
	}
	
	public void QRES_zadanie_3(){
		
	}
}
