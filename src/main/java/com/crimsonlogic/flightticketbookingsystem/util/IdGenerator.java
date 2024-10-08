package com.crimsonlogic.flightticketbookingsystem.util;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IdGenerator implements IdentifierGenerator {
	private static final int MIN_ID = 100000;
	private static final int MAX_ID = 999999;
	private Random random = new Random();

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		long id = MIN_ID + random.nextInt(MAX_ID - MIN_ID + 1);
		return id;
	}
}
