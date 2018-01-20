package za.co.entelect.bootcamp.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.services.publisher.PublisherService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PublisherController.class)
public class PublisherControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PublisherService publisherService;

    @Test
    public void testReport() throws Exception {
        List<Publisher> publishers = new ArrayList<>();
        publishers.add(mock(Publisher.class));
        publishers.add(mock(Publisher.class));

        when(publisherService.findAll()).thenReturn(publishers);

        mvc.perform(get("/report").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    public void testReportsIsInvalidUrl() throws Exception {
        mvc.perform(get("/reports").accept(MediaType.TEXT_HTML))
                .andExpect(status().isNotFound());
    }
}
