package org.cytoscape.property.bookmark;

import java.util.List;
/**
 * A set of utility methods to manipulate the bookmarks.
 * @CyAPI.Api.Interface
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

}
