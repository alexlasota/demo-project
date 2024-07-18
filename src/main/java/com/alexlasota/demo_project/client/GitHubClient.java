package com.alexlasota.demo_project.client;

import com.alexlasota.demo_project.model.GitRepository;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "githubClient", url = "https://api.github.com")
public interface GitHubClient {

    @GetMapping("/repos/{owner}/{repo}")
    GitRepository getRepository(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}