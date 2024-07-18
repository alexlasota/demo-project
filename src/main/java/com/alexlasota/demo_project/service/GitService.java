package com.alexlasota.demo_project.service;

import com.alexlasota.demo_project.client.GitHubClient;
import com.alexlasota.demo_project.model.GitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GitService {

    private final GitHubClient gitHubClient;

    public GitRepository getRepository(String owner, String repo) {
        return gitHubClient.getRepository(owner, repo);
    }
}