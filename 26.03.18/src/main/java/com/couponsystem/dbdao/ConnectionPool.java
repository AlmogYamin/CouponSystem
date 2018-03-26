package com.couponsystem.dbdao;

import org.springframework.stereotype.Component;

@Component
public class ConnectionPool {

	private int avilableConnection = 5;

	public synchronized ConnectionPool getConnection() throws InterruptedException {
		while (avilableConnection <= 0) {
			System.out.println("There are no free connection , please wait");
			wait();
		}
		avilableConnection = avilableConnection - 1;
		return this;
	}
	public synchronized void returnConnection() {
		avilableConnection = avilableConnection +1;
		notify();
	}

}
