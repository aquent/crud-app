package com.aquent.crudapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aquent.crudapp.data.dao.CompanyDao;
import com.aquent.crudapp.domain.Company;

/**
 * Default implementation of {@link CompanyService}.
 */
public class DefaultCompanyService implements CompanyService {

    private CompanyDao companyDao;
    private Validator validator;

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Company> listCompanies() {
        return companyDao.listCompanies();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Company readCompany(Integer id) {
        return companyDao.readCompany(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createCompany(Company company) {
        return companyDao.createCompany(company);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteCompany(Integer id) {
        companyDao.deleteCompany(id);
    }

    @Override
    public List<String> validateCompany(Company company) {
        Set<ConstraintViolation<Company>> violations = validator.validate(company);
        List<String> errors = new ArrayList<String>(violations.size());
        for (ConstraintViolation<Company> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
