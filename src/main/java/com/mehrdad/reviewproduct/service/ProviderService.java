package com.mehrdad.reviewproduct.service;

import com.mehrdad.reviewproduct.exception.RecordNotFoundException;
import com.mehrdad.reviewproduct.model.Provider;

/**
 * Created by m.peykari on 2/15/2021.
 */
public interface ProviderService {

    Provider save(Provider provider);
    Provider getById(Long id) throws RecordNotFoundException;
    Provider update(Long id, Provider provider) throws RecordNotFoundException;

}
