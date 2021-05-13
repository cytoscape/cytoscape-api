package org.cytoscape.jobs;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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
 * This object stores information about the status of a {@link CyJob}, including
 * the actual job status ({@link CyJobStatus.Status}) and any message returned
 * from the remote execution.
 *
 * @CyAPI.Api.Class
 * @CyAPI.InModule jobs-api
 */
public class CyJobStatus {
	protected Status status;
	protected String message;

	/**
	 * This enum contains the primary status returned from the remote execution.
	 */
	public enum Status {
		CANCELED("Canceled by the user or operator"),
		ERROR("Finished with errors or warnings"),
		FAILED("Failed"),
		FINISHED("Successfully finished"),
		PURGED("Job purged (no longer exists)"),
		RUNNING("Running"),
		QUEUED("In queue"),
		SUBMITTED("Submitted"),
		TERMINATED("Terminated"),
		UNKNOWN("Status unknown due to communications failure");
	
		private final String name;
		Status(String n) {
			this.name = n;
		}
		public String getName() {return name;}
	}

	public CyJobStatus(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * Return the {@link Status} of the remote job
	 *
	 * @return the {@link Status}
	 */
	public Status getStatus() { return status; }

	/**
	 * Return any message associated with the job status.
	 *
	 * @return a message, or null if no message was included
	 */
	public String getMessage() { return message; }

	/**
	 * Return a string including the status and the message, if any.
	 *
	 * @return a string describing the status
	 */
	public String toString() {
		String str = status.getName();
		if (message != null) {
			str += ": "+message;
		}
		return str;
	}

	/**
	 * Static method to determine if a job is "done", where
	 * done means that it is no longer processing for any reason.
	 *
	 * @param status the status to check
	 * @return true if the status indicates the job is done
	 */
	public boolean isDone() {
		if (status.equals(Status.CANCELED) || status.equals(Status.ERROR) ||
		    status.equals(Status.FAILED) || status.equals(Status.FINISHED) ||
				status.equals(Status.TERMINATED))
			return true;
		return false;
	}
}
