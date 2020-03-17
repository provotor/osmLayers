package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public abstract class BaseMockTest extends SpringTest {
    protected MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    protected <T> T getRequest(String uri, MultiValueMap<String, String> valByParam, Class<T> clazzResult) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/" + uri)
                .params(valByParam)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        return mapFromJson(content, clazzResult);
    }

    protected <T> T getRequest(String uri, Class<T> clazzResult) throws Exception {
        return getRequest(uri, (MultiValueMap<String, String>) CollectionUtils.toMultiValueMap(Collections.EMPTY_MAP), clazzResult);
    }

    protected <T, K> T postRequest(String uri, Class<T> clazzResult, K postContent) throws Exception {
        String s = mapToJson(postContent);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/" + uri)
                .content(s).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        if (clazzResult != null)
            return mapFromJson(content, clazzResult);
        else
            return null;
    }
}
