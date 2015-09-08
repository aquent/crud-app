package com.aquent.crudapp.service;

import java.util.List;

import com.aquent.crudapp.data.dao.SearchDao;
import com.aquent.crudapp.domain.SearchResult;

public class DefaultSearchService implements SearchService {

	private SearchDao searchDao;

	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}

	@Override
	public List<SearchResult> search(String query) {
		String likeQuery = "%" + query.toLowerCase() + "%";

		return searchDao.search(likeQuery);
	}

}
