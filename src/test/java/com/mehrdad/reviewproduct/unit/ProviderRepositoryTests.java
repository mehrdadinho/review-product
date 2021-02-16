package com.mehrdad.reviewproduct.unit;

import com.mehrdad.reviewproduct.model.Provider;
import com.mehrdad.reviewproduct.repository.ProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class ProviderRepositoryTests {

	@Autowired
	private ProviderRepository repository;

	Provider saved;

	@BeforeEach
	void init() {
		saved = repository.save(new Provider(null,"Samsung","02133854752",null));
	}

	@Test
	@Order(1)
	public void testSaveProvider() {
		assertThat(saved.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void testFindProviderById() {
		Provider provider = repository.findById(saved.getId()).get();
		assertThat(provider.getName()).isEqualTo("Samsung");
		assertThat(provider.getSupportPhone()).isEqualTo("02133854752");
	}

	@Test
	@Order(3)
	public void testFindAll() {
		List<Provider> providerList = repository.findAll();
		assertThat(providerList).size().isGreaterThan(0);
	}

	@Test
	@Order(4)
	public void testUpdateProduct() {
		Provider provider = repository.findById(saved.getId()).get();
		provider.setName("Nokia");
		repository.save(provider);
		Provider updatedProvider = repository.findById(saved.getId()).get();
		assertThat(updatedProvider.getName()).isEqualTo("Nokia");
	}

	@Test
	@Order(5)
	public void testDeleteProduct() {
		Provider provider = repository.findById(saved.getId()).get();
		repository.deleteById(provider.getId());
		Optional<Provider> deletedProvider = repository.findById(saved.getId());
		assertThat(deletedProvider.isPresent());

	}
}
