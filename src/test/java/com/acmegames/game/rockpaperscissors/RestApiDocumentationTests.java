package com.acmegames.game.rockpaperscissors;

import com.acmegames.game.rockpaperscissors.controller.PlayController;
import com.acmegames.game.rockpaperscissors.model.Item;
import com.acmegames.game.rockpaperscissors.model.Outcome;
import com.acmegames.game.rockpaperscissors.model.Play;
import com.acmegames.game.rockpaperscissors.model.PlayInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static com.acmegames.game.rockpaperscissors.MvcTestUtil.CONTENT_TYPE;
import static com.acmegames.game.rockpaperscissors.MvcTestUtil.isValidJsonObject;
import static com.acmegames.game.rockpaperscissors.MvcTestUtil.objectToString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlayController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class RestApiDocumentationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Random random;

    @Before
    public void setup() {
        random.setSeed(1);
    }

    @Test
    public void playActionShouldCreatePlayResource() throws Exception {
        mockMvc.perform(post("/play")
                .contentType(CONTENT_TYPE)
                .content(objectToString(new PlayInput(Item.SCISSORS))))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("play-post", responseFields(
                        fieldWithPath("id").type("int").description("Identifier of created `play` resource."),
                        fieldWithPath("userSelectedItem").type("Item").description("Item selected by user."),
                        fieldWithPath("computerSelectedItem").type("Item").description("Item selected by AI."),
                        fieldWithPath("outcome").type("Outcome").description("Game outcome.")
                ), requestFields(
                        fieldWithPath("selectedItem").type("Item").description("Item selected by user.")
                )));

        mockMvc.perform(get("/play"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("play-get"));

        mockMvc.perform(get("/play/0"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("play-get-by-id"));

        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("stats", responseFields(
                        fieldWithPath("games").type("int").description("Number of games played."),
                        fieldWithPath("loses").type("int").description("Number of games loosed by player."),
                        fieldWithPath("wins").type("int").description("Number of games won by player."),
                        fieldWithPath("ties").type("int").description("Number of games played with tie outcome.")
                )));
    }

}
