package com.alexlasota.demo_project.service;

import com.alexlasota.demo_project.client.GitHubClient;
import com.alexlasota.demo_project.model.GitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GitServiceTest {

    @Mock
    private GitHubClient gitHubClient;

    @InjectMocks
    private GitService gitService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testGetRepository() {
        String owner = "testOwner";
        String repo = "testRepo";
        GitRepository mockRepository = new GitRepository();
        mockRepository.setFullName("testOwner/testRepo");
        mockRepository.setDescription("Test repository");
        mockRepository.setCloneUrl("https://github.com/testOwner/testRepo.git");
        mockRepository.setStars(100);
        mockRepository.setCreatedAt("2020-01-01T00:00:00Z");

        when(gitHubClient.getRepository(owner, repo)).thenReturn(mockRepository);

        GitRepository result = gitService.getRepository(owner, repo);

        assertEquals("testOwner/testRepo", result.getFullName());
        assertEquals("Test repository", result.getDescription());
        assertEquals("https://github.com/testOwner/testRepo.git", result.getCloneUrl());
        assertEquals(100, result.getStars());
        assertEquals("2020-01-01T00:00:00Z", result.getCreatedAt());
    }
}