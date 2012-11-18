package envs;

import java.util.ArrayList;

import datastore.ComplexObject;
import datastore.SBAObject;
import datastore.SimpleObject;

import qres.collection.BagResult;
import qres.collection.SequenceResult;
import qres.single.BinderResult;
import qres.single.BooleanResult;
import qres.single.DoubleResult;
import qres.single.IntegerResult;
import qres.single.ReferenceResult;
import qres.single.SimpleResult;
import qres.single.StringResult;
import qres.single.StructResult;

import edu.pjwstk.jps.datastore.ISBAObject;
import edu.pjwstk.jps.datastore.ISBAStore;
import edu.pjwstk.jps.datastore.OID;
import edu.pjwstk.jps.interpreter.envs.IENVS;
import edu.pjwstk.jps.interpreter.envs.IENVSBinder;
import edu.pjwstk.jps.interpreter.envs.IENVSFrame;
import edu.pjwstk.jps.result.IAbstractQueryResult;
import edu.pjwstk.jps.result.IBagResult;
import edu.pjwstk.jps.result.ISingleResult;

public class ENVS implements IENVS {

	public ArrayList<IENVSFrame> stack = new ArrayList<IENVSFrame>();

	@Override
	public void init(OID rootOID, ISBAStore store) {
		// TODO Auto-generated method stub

	}

	@Override
	public IENVSFrame pop() {
		IENVSFrame frame = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		return frame;
	}

	@Override
	public void push(IENVSFrame frame) {
		stack.add(frame);
	}

	@Override
	public IBagResult bind(String name) {
		BagResult bag = new BagResult();
		boolean found = false;

		for (int i = stack.size() - 1; i > 0; i--) {
			IENVSFrame frame = stack.get(i);
			for (IENVSBinder bg : frame.getElements()) {
				if (name == bg.getName()) {
					bag.add((ISingleResult) bg.getValue());
					found = true;
				}
			}
			if (found) {
				break;
			}
		}
		return bag;
	}

	@Override
	public IENVSFrame nested(IAbstractQueryResult result, ISBAStore store) {

		ENVSFrame frame = new ENVSFrame();

		if (result instanceof ReferenceResult) {

			ISBAObject sbao = store.retrieve(((ReferenceResult) result)
					.getOIDValue());

			if (sbao instanceof ComplexObject) {

				for (OID oid : ((ComplexObject) sbao).getChildOIDs()) {
					ISBAObject object = store.retrieve(oid);
					ENVSBinder binder = new ENVSBinder(object.getName(), new ReferenceResult(oid));
					frame.add(binder);
				}

			} else if (sbao instanceof SimpleObject) {
				ENVSBinder binder = new ENVSBinder(sbao.getName(),
						new ReferenceResult(sbao.getOID()));
				frame.add(binder);
			}

		} else if (result instanceof BinderResult) {
			ENVSBinder binder = new ENVSBinder(
					(((BinderResult) result).getName()),
					((BinderResult) result).getValue());
			frame.add(binder);
		} else if (result instanceof StructResult) {
			for (ISingleResult s : ((StructResult) result).elements()) {
				nested(s, store);
			}
		} else if (result instanceof BooleanResult
				|| result instanceof DoubleResult
				|| result instanceof IntegerResult
				|| result instanceof StringResult) {
			// pusty zbior
		} else if (result instanceof SimpleResult) {
			// pusty zbior
		} else {
			// pusty zbior
		}

		return frame;

	}

}
