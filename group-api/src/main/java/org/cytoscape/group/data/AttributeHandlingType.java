/* vim: set ts=2: */
/* %% Ignore-License */
/*
 * Copyright (c) 2008 The Regents of the University of California.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *   1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions, and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions, and the following
 *      disclaimer in the documentation and/or other materials provided
 *      with the distribution.
 *   3. Redistributions must acknowledge that this software was
 *      originally developed by the UCSF Computer Graphics Laboratory
 *      under support by the NIH National Center for Research Resources,
 *      grant P41-RR01081.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.cytoscape.group.data;

/**
 * The AttributeHandlingType enum contains the list of all of the
 * different ways that attributes can be aggregated. Note that there
 * is no explicit mapping from attribute type to allowable attribute
 * aggregation type.  That mapping is contained within the various
 * AttributeHandlingType arrays defined below.
 */
public enum AttributeHandlingType {
	/** No aggregation. */
	NONE("None"),
	/** Aggregated as comma-separated values. */
	CSV("Comma-separated Values"),
	/** Aggregated as tab-separated values. */
	TSV("Tab-separated Values"),
	/** Aggregated as most common value. */
	MCV("Most Common Value"),
	/** Aggregated as the sum of all values. */
	SUM("Sum"),
	/** Aggregated as the average of all values. */
	AVG("Average"),
	/** Aggregated as the minimum value. */
	MIN("Minimum value"),
	/** Aggregated as the maximum value. */
	MAX("Maximum value"),
	/** Aggregated as the median value. */
	MEDIAN("Median value"),
	/** Aggregated as a concatenation of all values. */
	CONCAT("Concatenate"),
	/** Aggregated as unique values. */
	UNIQUE("Unique Values"),
	/** Aggregated as a logical AND of all values. */
	AND("Logical AND"),
	/** Aggregated as a logical OR of all values. */
	OR("Logical OR"),
	/** Default, no aggregation. */
	DEFAULT("(no override)");

	private String name;
	private AttributeHandlingType(String s) { name = s; }
	/**
	 * Returns a human readable name for this enum value. 
	 * @return a human readable name for this enum value. 
	 */
	public String toString() { return name; }
}
