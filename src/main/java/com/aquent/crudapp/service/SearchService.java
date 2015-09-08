package com.aquent.crudapp.service;

import java.util.List;

import com.aquent.crudapp.domain.SearchResult;

/**
 * Search operations.
 */
public interface SearchService {

	/**
	 * Retrieves search results.
	 *
	 * @return list of search results
	 */
	List<SearchResult> search(String query);
}
