package blog.explained.so;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ItemControllerTests {

  @Autowired
  private MockMvc mvc;

  @Test
  @WithMockUser(authorities = "USER")
  public void givenUserAuthority_thenShouldNotContainOwnerInResponse() throws Exception {

    mvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").exists())
      .andExpect(jsonPath("$.name").exists())
      .andExpect(jsonPath("$.owner").doesNotExist());

  }

  @Test
  @WithMockUser(authorities = "ADMIN")
  public void givenAdminAuthority_thenShouldContainOwnerInResponse() throws Exception {

    mvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").exists())
      .andExpect(jsonPath("$.name").exists())
      .andExpect(jsonPath("$.owner").exists());

  }

}
