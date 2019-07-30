package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sample.Application;
import sample.SampleRepository;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UrlCharactersInIdTest {

    @Autowired
    private MockMvc http;

    @Test
    public void whenStringIdHasHashCharacterThenItCanBeRetrieved() throws Exception {
        findWithFindGivenId("with_#_in_id");
    }

    @Test
    public void whenStringIdHasPercentCharacterThenItCanBeRetrieved() throws Exception {
        findWithFindGivenId("with_%_in_id");
    }

    @Test
    public void whenStringDoesNotHaveHashOrPercentCharacterThenItCanBeRetrieved() throws Exception {
        findWithFindGivenId("no_percent_nor_hash");
    }

    private void findWithFindGivenId(String id) throws Exception {
        String location = http.perform(
                post("/" + SampleRepository.PATH)
                        .content("{\"id\":\"" + id + "\",\"value\":\"anything\"}")
        )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getRedirectedUrl();
        assertNotNull(location);

        http.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("anything"));
    }
}
