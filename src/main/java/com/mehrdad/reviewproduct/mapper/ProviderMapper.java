package com.mehrdad.reviewproduct.mapper;


import com.mehrdad.reviewproduct.dto.ProviderDto;
import com.mehrdad.reviewproduct.model.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Component
public class ProviderMapper extends ModelMapper {

    public ProviderDto toProviderDto(Provider provider) {
        ProviderDto providerDto = map(provider, ProviderDto.class);
        return providerDto;
    }

    public Provider toProvider(ProviderDto providerDto) {
        Provider provider = map(providerDto, Provider.class);
        return provider;
    }
}
