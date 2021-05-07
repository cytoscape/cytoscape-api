package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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

import java.util.Map;

import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.events.RowSetRecord;
import org.cytoscape.model.events.RowsSetEvent;
import org.cytoscape.model.events.RowsSetListener;

/**
 * A utility class that provides an implementation of {@link RowsSetListener}
 * for a particular column and {@link VisualProperty}.
 * @param <S> The generic type of this ViewUpdater.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule swing-application-api
 */
public abstract class AbstractViewUpdater<S> implements RowsSetListener {

	/** The {@link VisualProperty} that the {@link RowsSetListener} is provided for. */
	protected final VisualProperty<S> vp;
	/** The name of the column that the {@link RowsSetListener} is provided for.*/
	protected final String columnName;

	private final Map<CyRow, View<?>> rowViewMap;

	/**
	 * Constructor.
	 * 
	 * @param vp The visual property that should be set on the view when the row
	 * is changed.
	 * @param columnName The name of the column within the row that is being
	 * listened to.
	 * @param rowViewMap a map between the row that is being listened to and the
	 * view that the visual property should be set when the row is changed.
	 */
	public AbstractViewUpdater(VisualProperty<S> vp, String columnName, Map<CyRow, View<?>> rowViewMap) {
		this.vp = vp;
		this.columnName = columnName;
		this.rowViewMap = rowViewMap;
	}

	/**
	 * Called whenever {@link CyRow}s are changed. Will attempt to set the
	 * visual property on the view with the new value that has been set in the
	 * row.
	 * 
	 * @param e The {@link RowsSetEvent} to be processed.
	 */
	@SuppressWarnings("unchecked")
	public void handleEvent(RowsSetEvent e) {
		for (RowSetRecord record : e.getColumnRecords(columnName)) {
			View<?> v = rowViewMap.get(record.getRow());

			if (v != null)
				v.setVisualProperty(vp, (S) record.getValue());
		}
	}
}
