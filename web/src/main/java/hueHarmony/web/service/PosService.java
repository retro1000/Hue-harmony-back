package hueHarmony.web.service;

import hueHarmony.web.dto.PosProductDto;

import hueHarmony.web.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PosService {

    private final ProductRepository productRepository;

    public List<PosProductDto> getProducts() {

        return (productRepository.getProducts());
    }
}
