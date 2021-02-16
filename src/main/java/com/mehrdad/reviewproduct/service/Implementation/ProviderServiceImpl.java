package com.mehrdad.reviewproduct.service.Implementation;

import com.mehrdad.reviewproduct.exception.RecordNotFoundException;
import com.mehrdad.reviewproduct.model.Provider;
import com.mehrdad.reviewproduct.repository.ProviderRepository;
import com.mehrdad.reviewproduct.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository repository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Provider save(Provider product) {
        product.setId(null);
        product.setProducts(null);
        return repository.save(product);
    }
    
    @Override
    public Provider getById(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Provider not found for Id: "+id));
    }

    @Override
    public Provider update(Long id,Provider provider) throws RecordNotFoundException {
        Provider fetchEntity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Provider not found for Id: "+id));
        fetchEntity.setName(provider.getName());
        fetchEntity.setSupportPhone(provider.getSupportPhone());
        return repository.save(fetchEntity);
    }

}
