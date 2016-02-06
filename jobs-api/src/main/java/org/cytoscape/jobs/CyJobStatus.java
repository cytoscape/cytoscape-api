package org.cytoscape.jobs;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2015 The Cytoscape Consortium
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
 * An enum used for the status of a CyJob
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule jobs-api
 */
public enum CyJobStatus {
	CANCELED("Canceled by the user or operator"),
	ERROR("Finished with errors or warnings"),
	FAILED("Failed"),
	FINISHED("Successfully finished"),
	PURGED("Job purged (no longer exists)"),
	RUNNING("Running"),
	QUEUED("In queue"),
	SUBMITTED("Submitted"),
	TERMINATED("Terminated");

	private final String name;
	CyJobStatus(String n) {
		this.name = n;
	}
	public String toString() {return name;}

	/**
	 * Static method to determine if a job is "done", where
	 * done means that it is no longer processing for any reason.
	 *
	 * @param status the status to check
	 * @return true if the status indicates the job is done
	 */
	public static boolean isDone(CyJobStatus status) {
		if (status.equals(CANCELED) || status.equals(ERROR) ||
		    status.equals(FAILED) || status.equals(FINISHED) ||
				status.equals(TERMINATED))
			return true;
		return false;
	}
}
