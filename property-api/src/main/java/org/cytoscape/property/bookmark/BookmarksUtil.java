package org.cytoscape.property.bookmark;

/*
 * #%L
 * Cytoscape Property API (property-api)
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

import java.util.List;
/**
 * A set of utility methods to manipulate the bookmarks.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule property-api
 */
public interface BookmarksUtil {

	/**
	 * Traverse bookmark tree and get a list of data sources from the specified
	 * category.
	 * 
	 * @param categoryName the specified category to get a list of data sources from.
	 * @param categoryList the list of categories to traverse for the specified category.
	 * @return a list of data sources from the specified category.
	 */
	List<DataSource> getDataSourceList(String categoryName, List<Category> categoryList);

	/**
	 * Select specific category from a list of categories.
	 * 
	 * @param categoryName the category to select.
	 * @param categoryList the list of categories to select from.
	 * @return the {@link Category} that was selected.
	 */
	Category getCategory(String categoryName, List<Category> categoryList);

	/**
	 * Given the attribute name, return the value in a bookmark 
	 * 
	 * @param source a bookmark object
	 * @param attrName an attribute name
	 * 
	 * @return The value related to the attribute name
	 */
	String getAttribute(DataSource source, String attrName); 

	/**
	 * Store a bookmark object in bookmarks object
	 * 
	 * @param pBookmarks bookmarks object
	 * @param pCategoryName category name
	 * @param pDataSource a single bookmark
	 */
	void saveBookmark(Bookmarks pBookmarks, String pCategoryName,
			DataSource pDataSource);

	
	/**
	 * Store a bookmark object in bookmarks object
	 * 
	 * @param pBookmarks bookmarks object
	 * @param pCategoryName category name
	 * @param pDataSource a single bookmark
	 * @param pProvider the name of provider
	 */
	void saveBookmark(Bookmarks pBookmarks, String pCategoryName,
			DataSource pDataSource, String pProvider);

	/**
	 * Delete a bookmark (pDataSource) from the category (pCategoryName) in the bookmarks object (pBookmarks)
	 * 
	 * @param pBookmarks Bookmark object, which hold a set of bookmark
	 *  
	 * @param pCategoryName category name
	 * 
	 * @param pDataSource a single bookmark object
	 * 
	 * @return True if the bookmark is deleted successfully, False otherwise.
	 */
	boolean deleteBookmark(Bookmarks pBookmarks, String pCategoryName, DataSource pDataSource);

	/**
	 * Check if a bookmark is in the bookmarks.
	 * 
	 * @param pBookmarks bookmarks object
	 * @param pCategoryName category name
	 * @param pDataSource a bookmark
	 * 
	 * @return True if the bookmark is in bookmarks, False otherwise
	 */
	boolean containsBookmarks(Bookmarks pBookmarks, String pCategoryName, DataSource pDataSource);

	/**
	 * Get the provider name from DataSource object
	 * 
	 * @param pDataSource a single bookmark
	 */	
	String getProvider(DataSource pDataSource);
}
