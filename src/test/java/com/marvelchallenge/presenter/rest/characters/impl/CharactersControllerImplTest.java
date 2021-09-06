package com.marvelchallenge.presenter.rest.characters.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.marvelchallenge.gateway.characters.CharactersClient;
import com.marvelchallenge.gateway.characters.CharactersGateway;
import com.marvelchallenge.gateway.characters.CharactersGatewayImpl;
import com.marvelchallenge.gateway.dto.MarvelApiResponse;
import com.marvelchallenge.models.Character;
import com.marvelchallenge.usecase.GetCharacterById;
import com.marvelchallenge.usecase.GetCharactersIds;
import com.marvelchallenge.usecase.impl.GetCharacterByIdImpl;
import com.marvelchallenge.usecase.impl.GetCharactersIdsImpl;
import com.marvelchallenge.utils.MockLoaderUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharactersControllerImpl.class)
class CharactersControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CharactersClient charactersClient;

    @TestConfiguration
    static class AdditionalConfig {
        @Bean
        public GetCharactersIds getCharactersIds() {
            CharactersClient charactersClient = mock(CharactersClient.class);
            MarvelApiResponse<Character> mock = MockLoaderUtils
                    .getMockTypeReference("character-list.json"
                    , new TypeReference<>() {
                    });
            when(charactersClient.getAll(anyString(), anyString(), anyString()))
                    .thenReturn(mock);
            return new GetCharactersIdsImpl(new CharactersGatewayImpl(charactersClient));
        }

        @Bean
        public GetCharacterById getCharacterById() {
            CharactersClient charactersClient = mock(CharactersClient.class);
            MarvelApiResponse<Character> mock = MockLoaderUtils
                    .getMockTypeReference("character-1010699.json"
                    , new TypeReference<>() {
                    });
            when(charactersClient.getById(anyInt(), anyString(), anyString(), anyString()))
                    .thenReturn(mock);
            return new GetCharacterByIdImpl(new CharactersGatewayImpl(charactersClient));
        }

    }

    @Test
    void getAll() throws Exception {

        String responseString = mockMvc.perform(get("/characters")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<Integer> response = MockLoaderUtils.stringToObject(responseString
                , new TypeReference<>() {
                });
        assertNotNull(response);
        assertEquals(20, response.size());
    }

    @Test
    void getCharactersIds() throws Exception {

        String responseString = mockMvc.perform(get("/characters/1010699")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Character response = MockLoaderUtils.stringToObject(responseString
                , new TypeReference<>() {
                });
        assertNotNull(response);
        assertEquals(1010699, response.getId());
    }
}