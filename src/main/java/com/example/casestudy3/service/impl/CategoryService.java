package com.example.casestudy3.service.impl;

import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.exception.AppException;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.repository.CategoryRepository;
import com.example.casestudy3.service.IService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CategoryService implements IService<Categories> {
    @Autowired
    private CategoryRepository categoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Categories create(Categories request) {
        Categories categories = new Categories();

        if (categoryRepository.existsByCode(request.getCode())){
                throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }
        categories.setCode(request.getCode());
        categories.setName(request.getName());
        categories.setStatus(1);

        return categoryRepository.save(categories);
    }

    @Override
    public Categories update(Categories request, UUID id) {
        Categories categories = new Categories();
        categories.setId(id);
        categories.setCode(request.getCode());
        categories.setName(request.getName());
        categories.setStatus(1);

        return categoryRepository.save(categories);
    }

    @Override
    public List<Categories> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
    }


    @Override
    public Boolean delete(UUID id) {
        Optional<Categories> optional = categoryRepository.findById(id);
        if (optional.isPresent()){
            Categories category = optional.get();
            categoryRepository.delete(category);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Categories> findByName(String name) {
        return categoryRepository.findAllByName(name);
    }

    public List<Categories> getCategoriesByStatus(Integer status) {
        return categoryRepository.findByStatus(status);
    }

    public List<Categories> getCategoriesByNameUsingEntityManager(String name) {
        String jpql = "SELECT c FROM Categories c WHERE c.name = :name";
        return entityManager.createQuery(jpql, Categories.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Categories> getCategoriesByStatusUsingCriteria(Integer status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Categories> cq = cb.createQuery(Categories.class);
        Root<Categories> category = cq.from(Categories.class);

        Predicate statusPredicate = cb.equal(category.get("status"), status);
        cq.where(statusPredicate);

        return entityManager.createQuery(cq).getResultList();
    }

    public Page<Categories> getCategoriesByStatus(Integer status, Pageable pageable) {
        return categoryRepository.findByStatus(status, pageable);
    }
}
