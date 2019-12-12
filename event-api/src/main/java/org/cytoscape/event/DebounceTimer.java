package org.cytoscape.event;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * A timer that can be used to ensure that time-consuming tasks, usually triggered
 * by events, do not run too often. It can be used by code that fires events to avoid
 * firing too many events, or by code listening for events to avoid running the handler
 * too often.
 */
public class DebounceTimer {

	public static final int DEFAULT_DELAY_MILLISECONDS = 120;
	
	private static final Object DEFAULT_KEY = new Object();
	
	private final Map<Object,ScheduledFuture<?>> store;
	
	private final ScheduledThreadPoolExecutor executor;
	private final int delay; // milliseconds
	
	
	/**
	 * Creates a new DebounceTimer with the default delay.
	 * When this DebounceTimer is no longer needed the {@link DebounceTimer#shutdown()} 
	 * method should be called to release resources.
	 */
	public DebounceTimer() {
		this(DEFAULT_DELAY_MILLISECONDS);
	}
	
	/**
	 * Creates a new DebounceTimer with the given delay (in milliseconds).
	 * When this DebounceTimer is no longer needed the {@link DebounceTimer#shutdown()} 
	 * method should be called to release resources.
	 */
	public DebounceTimer(int delayMilliseconds) {
		// Synchronize the store because the future runs on a different thread than the one calling debounce().
		this.store = Collections.synchronizedMap(new HashMap<>());
		this.delay = delayMilliseconds;
		
		executor = new ScheduledThreadPoolExecutor(1, r -> {
			Thread thread = Executors.defaultThreadFactory().newThread(r);
			thread.setName(DebounceTimer.class.getSimpleName() + "_" + thread.getName());
			return thread;
		});
	}

	
	/**
	 * Starts a timer that will run the runnable after a short delay (on a separate thread). 
	 * If another call to this method occurs before the timer expires then the timer will be 
	 * reset. When there are multiple quick calls to this method and the time between
	 * each call is less than the delay then the runnable will only run when the delay is reached.
	 * This method is non-blocking.
	 * 
	 * <br><br>
	 * Code example:
	 * 
	 * <pre>
	 * public void handleEvent(RowsSetEvent e) {
	 *     if(e.containsColumn(CyNetwork.SELECTED)) {
	 *         debounceTimer.debounce(() -> updateUI());
	 *     }
	 * }
	 * </pre>
	 */
	public synchronized void debounce(Runnable runnable) {
		debounce(DEFAULT_KEY, runnable);
	}
	
	/**
	 * Starts a timer that will run the runnable after a short delay (on a separate thread). 
	 * If another call to this method (with the same key) occurs before the timer expires then the timer will be 
	 * reset. When there are multiple quick calls to this method and the time between
	 * each call is less than the delay then the runnable will only run when the delay is reached.
	 * This method is non-blocking.
	 * 
	 * <br><br>
	 * Code example:
	 * 
	 * <pre>
	 * public void handleEvent(RowsSetEvent e) {
	 *     if(e.containsColumn(CyNetwork.SELECTED)) {
	 *         CyNetworkView networkView = applicationManager.getCurrentNetworkView();
	 *         if(networkView != null) {
	 *             debounceTimer.debounce(networkView, () -> updateUI(networkView));
	 *         }
	 *     }
	 * }
	 * </pre>
	 * 
	 * @param key A key object to associate with the runnable, each key object will get its own timer.
	 * @throws RejectedExecutionException if this timer has been shutdown
	 * @throws NullPointerException if key or runnable are null
	 */
	public synchronized void debounce(Object key, Runnable runnable) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(runnable);
		
		ScheduledFuture<?> future = store.get(key);
		if(future != null) {
			future.cancel(false);
		}
		
		Runnable r = () -> {
			store.remove(key);
			runnable.run();
		};
		
		future = executor.schedule(r, delay, TimeUnit.MILLISECONDS);
		store.put(key, future);
	}
	
	
	/**
	 * Releases resources.
	 */
	public void shutdown() { 
		executor.shutdown();
	}
	
	
	public boolean isShutdown() {
		return executor.isShutdown();
	}
	
}
