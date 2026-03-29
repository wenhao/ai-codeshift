package com.company.project.service.impl;

import com.company.project.entity.po.ProductCategory;
import com.company.project.mapper.ProductCategoryGetMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductCategoryGetServiceImplTest {

    @Mock
    private ProductCategoryGetMapper productCategoryGetMapper;

    @InjectMocks
    private ProductCategoryGetServiceImpl productCategoryGetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}