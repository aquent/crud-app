package com.aquent.crudapp.data.dao;

import java.util.List;

import com.aquent.crudapp.domain.SearchResult;

/**
 * Search companies and people
 */
public interface SearchDao {

	/**
     * Retrieves the companies and people which match the query.
     *
     * @return list of search results
     */
    List<SearchResult> search(String query);
}
