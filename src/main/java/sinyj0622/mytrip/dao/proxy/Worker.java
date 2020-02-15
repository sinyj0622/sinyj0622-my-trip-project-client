package sinyj0622.mytrip.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Worker {
	Object execute(ObjectOutputStream out, ObjectInputStream in) throws Exception;
}
