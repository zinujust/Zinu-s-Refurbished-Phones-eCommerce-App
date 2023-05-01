package app.zinu.com.entity;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MyGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String id = UUID.randomUUID().toString().replace("-", "");
		String newId = id.subSequence(0, 9).toString();
		
		return newId;
	}

}
