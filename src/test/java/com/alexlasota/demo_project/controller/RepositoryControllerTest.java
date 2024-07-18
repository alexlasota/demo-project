package com.alexlasota.demo_project.controller;

import com.alexlasota.demo_project.model.GitRepository;
import com.alexlasota.demo_project.service.GitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RepositoryControllerTest {

    @Mock
    private GitService gitService;

    @InjectMocks
    private RepositoryController repositoryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(repositoryController).build();
    }

    @Test
    void getRepository_shouldReturnRepository() throws Exception {
        String owner = "testOwner";
        String repo = "testRepo";
        GitRepository gitRepository = new GitRepository();
        gitRepository.setFullName(owner + "/" + repo);

        when(gitService.getRepository(anyString(), anyString())).thenReturn(gitRepository);

        MvcResult result = mockMvc.perform(get("/repos/{owner}/{repo}", owner, repo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Response content: " + content);  // Debugging

        verify(gitService).getRepository(owner, repo);

        assertTrue(content.contains(owner + "/" + repo), "Response should contain repository name");
    }

    @Test
    void getRepository_shouldHandleNullResponse() throws Exception {
        String owner = "testOwner";
        String repo = "testRepo";
        when(gitService.getRepository(anyString(), anyString())).thenReturn(null);

        MvcResult result = mockMvc.perform(get("/repos/{owner}/{repo}", owner, repo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Response content: " + content);  // Debugging

        verify(gitService).getRepository(owner, repo);

        assertTrue(content.isEmpty() || content.equals("null"), "Response should be empty or 'null' for null repository");
    }
}