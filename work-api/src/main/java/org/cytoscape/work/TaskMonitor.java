package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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


/**
 * Used by a {@code Task}'s implementation {@code run} method
 * to inform users of the status of its execution. 
 *
 * @author Samad Lotia
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
public interface TaskMonitor {
	/**
	 * Sets the title of the <code>Task</code>.
	 * The title is a succinct, user-friendly description of the task's
	 * purpose.
	 *
	 * <p>
	 * Titles should be user-friendly descriptions. Implementation-specific
	 * details and debugging information should be avoided in titles.
	 * </p>
	 *
	 * <p>
	 * Each task implementation should call this method
	 * and should typically be called once at the beginning
	 * of the {@code run} method.
	 * </p>
	 * 
	 * <p>
	 * In the Swing task dialog, the appearance of the task's title
	 * depends on the task's position in the task iterator and the order
	 * of calls to setTitle().  If the task is the first in the task iterator, 
	 * its title (as set by setTitle()) will be shown during the course of the 
	 * entire task iterator's execution at the top of the dialog in large text.
	 * The title of a subsequent task (or a subsequent call to setTitle() in the 
	 * first task) will be shown only during the course of its execution as the 
	 * secondary title.  The secondary title is smaller text underneath
	 * the primary title. The behavior of primary and secondary titles
	 * does not change the semantics of task titles.
	 * The behavior of primary and secondary task titles are subject to change in future Cytoscape releases.
	 * </p>
	 * @param title Succinct description of the Task's purpose.
	 */
	public void setTitle(String title);

	/**
	 * Sets the progress completed by the <code>Task</code>.
	 *
	 * @param progress A value between <code>0.0</code> and <code>1.0</code>. Any negative value
     * sets the progress bar to an indefinite state.
	 */
	public void setProgress(double progress);

	/**
	 * Sets the status message that describes what the task is currently doing.
	 * Status messages are succinct, user-friendly descriptions of a stage of the task's execution.
	 * These messages should not contain formatting information such as HTML tags,
	 * since it is not guaranteed that the actual Task Monitor implementation will be able to render it
	 * (for instance, the messages may be displayed in a console).
	 * Status messages should not contain implementation details or debugging information either.
	 *
	 * <p>
	 * This method is a shorthand for {@code showMessage(Level.INFO, statusMessage)}.
	 * </p>
	 *
	 * <p>
	 * This method can be called throughout the course of the <code>run</code> method.
	 * </p>
	 *
	 * <p>
	 * In the Swing task dialog, status messages are shown temporarily
	 * at the bottom until another invocation of {@code setStatusMessage}
	 * or {@code showMessage}
	 * or until the end of the task's execution. All status messages
	 * can be recalled by the user by opening the task history window.
	 * </p>
	 * @param statusMessage A succinct description of what the task is currently doing.
	 */
	public void setStatusMessage(String statusMessage);

	/**
	 * Used by the {@code showMessage} method to indicate the severity of the message.
	 */
	public static enum Level {
		/**
		 * A message that is informational to the user.
		 */
		INFO,

		/**
		 * A message that warns the user about recoverable errors the task has experienced.
		 */
		WARN,

		/**
		 * A message that informs the user that a non-recoverable error has occured.
		 * Typically after an error message has been shown, the task should stop
		 * execution.
		 */
		ERROR
	}

	/**
	 * Sets the status message that describes what the task is currently doing.
	 * Status messages are succinct, user-friendly descriptions of a stage of the task's execution.
	 * These messages should not contain formatting information such as HTML tags,
	 * since it is not guaranteed that the actual Task Monitor implementation will be able to render it
	 * (for instance, the messages may be displayed in a console).
	 * Status messages should not contain implementation details or debugging information either.
	 *
	 * <p>
	 * This method can be called throughout the course of the <code>run</code> method.
	 * </p>
	 *
	 * <p>
	 * In the Swing task dialog, messages are shown temporarily
	 * at the bottom until another invocation of {@code setStatusMessage}
	 * or {@code showMessage}
	 * or until the end of the task's execution. All messages
	 * can be recalled by the user by opening the task history window.
	 * </p>
	 * @param level The severity of the message
	 * @param message A succinct description of what the task is currently doing.
	 */
	public void showMessage(Level level, String message);
}
