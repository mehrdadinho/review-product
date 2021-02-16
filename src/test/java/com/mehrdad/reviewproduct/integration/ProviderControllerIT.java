package com.mehrdad.reviewproduct.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehrdad.reviewproduct.ReviewProductApplication;
import com.mehrdad.reviewproduct.model.Provider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewProductApplication.class)
@WebAppConfiguration
public class ProviderControllerIT {

    private static final Long PROVIDER_ID = 10001L;
    private static final Integer EXIST_ID = 0;

    private MediaType contentType = MediaType.parseMediaType("application/json");

    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getByIdResponseStatusAndContentType() throws Exception{
        mockMvc.perform(get("/api/provider/get/{id}", PROVIDER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string(containsString("Apple")));
    }
    @Test
    public void newSaveResponseStatus() throws Exception{
        Provider provider = new Provider(null,"Samsung","02133854752",null);

        mockMvc.perform(post("/api/provider/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(provider))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", greaterThan(EXIST_ID)));

        em.flush();
    }
    @Test
    public void updateResponseStatus() throws Exception{
        Provider provider = new Provider(null,"Huawei","09125485210",null);


        mockMvc.perform(put("/api/provider/update/{id}", PROVIDER_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(provider))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Huawei")));

        em.flush();
    }
}