package com.alexlasota.demo_project.controller;

import com.alexlasota.demo_project.model.GitRepository;
import com.alexlasota.demo_project.service.GitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repos")
@RequiredArgsConstructor
public class RepositoryController {

    private final GitService gitService;

    @GetMapping("/{owner}/{repo}")
    public GitRepository getRepository(@PathVariable String owner, @PathVariable String repo) {
        return gitService.getRepository(owner, repo);
    }
}