package com.aquent.crudapp.service;

import java.util.List;

import com.aquent.crudapp.domain.Company;

/**
 * Company operations.
 */
public interface CompanyService {

    /**
     * Retrieves all of the company records.
     *
     * @return list of company records
     */
    List<Company> listCompanies();

    /**
     * Creates a new company record.
     *
     * @param company the values to save
     * @return the new company ID
     */
    Integer createCompany(Company company);

    /**
     * Retrieves a company record by ID.
     *
     * @param id the company ID
     * @return the company record
     */
    Company readCompany(Integer id);

    /**
     * Updates an existing company record.
     *
     * @param company the new values to save
     */
    void updateCompany(Company company);

    /**
     * Deletes a company record by ID.
     *
     * @param id the company ID
     */
    void deleteCompany(Integer id);

    /**
     * Validates populated company data.
     *
     * @param company the values to validate
     * @return list of error messages
     */
    List<String> validateCompany(Company company);
}
