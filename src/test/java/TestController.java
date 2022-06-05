import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import main.Application;
import main.service.impl.StudentManagerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestExecutionListeners({
    TransactionalTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
@TestPropertySource(locations = "/application-test.properties")
public class TestController {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private StudentManagerServiceImpl studentManagerService;

  @Test
  public void addStudentTest() throws Exception {
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api")
        .param("name", "viktor")
        .param("birthdate", "28.03.2016")
        .param("group", "y-113");
    ResultActions result = mockMvc.perform(request);
    result.andExpect(MockMvcResultMatchers.status().is(200));

    request = MockMvcRequestBuilders.post("/api")
        .param("name", "viktor")
        .param("birthdate", "28 03-2016")
        .param("group", "y-113");
    result = mockMvc.perform(request);
    result.andExpect(MockMvcResultMatchers.status().is(500));
  }

  @Test
  public void getStudentsTest() throws Exception {
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api");
    ResultActions result = mockMvc.perform(request);
    result.andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  @DatabaseSetup("/students-data.xml")
  public void deleteStudentTest() throws Exception {
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/api")
        .param("id", "1");
    ResultActions result = mockMvc.perform(request);
    result.andExpect(MockMvcResultMatchers.status().is(200));
  }


}
