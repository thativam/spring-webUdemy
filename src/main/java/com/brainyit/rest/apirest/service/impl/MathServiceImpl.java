package com.brainyit.rest.apirest.service.impl;

import com.brainyit.rest.apirest.service.MathService;
import org.springframework.stereotype.Service;

@Service
public class MathServiceImpl implements MathService {
    public Double add(Double a, Double b) {
        return Double.sum(a, b);
    }
}
