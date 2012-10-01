
/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/

package org.cytoscape.event;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


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
