package org.cytoscape.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 * #%L
 * Cytoscape Event API (event-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import org.junit.Test;


/**
 */
public abstract class AbstractCyEventHelperTest {

	protected CyEventHelper helper;
	protected StubCyListener service;
	protected StubCyPayloadListener payloadService;

	@Test
	public void testFireSynchronous() {
		helper.fireEvent(new StubCyEvent("homer"));
		assertEquals(1, service.getNumCalls());
	}

	@Test
	public void testFireAsynchronous() {
		try {
		helper.fireEvent(new StubCyEvent("marge"));
		Thread.sleep(500); // TODO is there a better way to wait?
		assertEquals(1, service.getNumCalls());
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}

	// This is a performance test that counts the number of events fired in 1/5 second. 
	// We verify that the payload approach is at least 3 times faster than the
	// event/listener combo. 
	@Test
	public void testLD1fifthsecond() {
		final long duration = 200000000;

		long end = System.nanoTime() + duration;
		int syncCount = 0;
		while ( end > System.nanoTime() ) {
			helper.fireEvent(new StubCyEvent("homer"));
			syncCount++;
		}


		end = System.nanoTime() + duration;
		int payloadCount = 0;
		while ( end > System.nanoTime() ) {
			helper.addEventPayload("homer","marge",StubCyPayloadEvent.class);
			payloadCount++;
		}
		helper.flushPayloadEvents();

		System.out.println("payloadCount: " + payloadCount);
		System.out.println("syncCount:    " + syncCount);
		System.out.println("diff:         " + (float)payloadCount/(float)syncCount);
		
		assertTrue( payloadCount > (syncCount*3) );
	}

	@Test
	public void testSynchronousNoInstances() {
		helper.fireEvent(new FakeCyEvent());
	}

	@Test
	public void testAsynchronousNoInstances() {
		try {
		helper.fireEvent(new FakeCyEvent());
		Thread.sleep(500); // TODO is there a better way to wait?
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}

	@Test
	public void testSynchronousSilenced() {
		String source = "homer";
		helper.silenceEventSource(source);
		helper.fireEvent(new StubCyEvent(source));
		assertEquals(0, service.getNumCalls());
	}

	@Test
	public void testAsynchronousSilenced() {
		try {
		String source = "homer";
		helper.silenceEventSource(source);
		helper.fireEvent(new StubCyEvent(source));
		Thread.sleep(500); // TODO is there a better way to wait?
		assertEquals(0, service.getNumCalls());
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}

	@Test
	public void testSynchronousSilencedThenUnsilenced() {
		String source = "homer";
		helper.silenceEventSource(source);
		helper.fireEvent(new StubCyEvent(source));
		assertEquals(0, service.getNumCalls());
		helper.unsilenceEventSource(source);
		helper.fireEvent(new StubCyEvent(source));
		assertEquals(1, service.getNumCalls());
	}

	@Test
	public void testAsynchronousSilencedThenUnsilenced() {
		try {
		String source = "homer";
		helper.silenceEventSource(source);
		helper.fireEvent(new StubCyEvent(source));
		Thread.sleep(500); // TODO is there a better way to wait?
		assertEquals(0, service.getNumCalls());
		helper.unsilenceEventSource(source);
		helper.fireEvent(new StubCyEvent(source));
		Thread.sleep(500); // TODO is there a better way to wait?
		assertEquals(1, service.getNumCalls());
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}

	@Test
	public void testAddEventPayload() {
		try {
		helper.addEventPayload("source","homer",StubCyPayloadEvent.class);
		helper.addEventPayload("source","marge",StubCyPayloadEvent.class);
		Thread.sleep(500);
		assertTrue( payloadService.getNumCalls() >= 1 );
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}
	
	@Test
	public void testAddEventPayloadSilencedSource() {
		try {
		helper.silenceEventSource("source");
		helper.addEventPayload("source","homer",StubCyPayloadEvent.class);
		helper.addEventPayload("source","marge",StubCyPayloadEvent.class);
		Thread.sleep(500);
		assertTrue( payloadService.getNumCalls() == 0 );
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}

	@Test
	public void testAddEventPayloadNullSource() {
		try {
		helper.addEventPayload(null,"homer",StubCyPayloadEvent.class);
		Thread.sleep(500);
		assertEquals( 0, payloadService.getNumCalls() );
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}

	@Test
	public void testAddEventPayloadNullPayload() {
		try {
		helper.addEventPayload("source",null,StubCyPayloadEvent.class);
		Thread.sleep(500);
		assertEquals( 0, payloadService.getNumCalls() );
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}

	@Test
	public void testAddEventPayloadNullEventType() {
		try {
		helper.addEventPayload("source","bart",null);
		Thread.sleep(500);
		assertEquals( 0, payloadService.getNumCalls() );
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}

	@Test
	public void testDeadlock() {
		// The use of a CyEventHelper in the listener to add other event 
		// payload should not cause a deadlock.
		payloadService.setEventHelper(helper);
		try {
		helper.addEventPayload("source","barney",StubCyPayloadEvent.class);
		Thread.sleep(1100);
		System.out.println("deadlock payloadService num calls:" + payloadService.getNumCalls());
		assertTrue( payloadService.getNumCalls() > 1 );
		} catch ( InterruptedException ie ) { throw new RuntimeException(ie); }
	}
	
	@Test
	public void testPayloadBeforeNormal() {
		helper.addEventPayload("source","homer",StubCyPayloadEvent.class);
		helper.addEventPayload("source","marge",StubCyPayloadEvent.class);
		helper.fireEvent(new StubCyEvent("lisa"));
		// This tests that any accumulated payload events get fired before
		// normal events.
		assertTrue(payloadService.getNumCalls() == 1);
		assertTrue(service.getNumCalls() == 1);
	}
}
