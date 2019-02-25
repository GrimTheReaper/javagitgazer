package com.github.grimthereaper.javagitgaze.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.grimthereaper.javagitgaze.githubutils.Github;
import com.github.grimthereaper.javagitgaze.githubutils.Repository;
import com.github.grimthereaper.javagitgaze.githubutils.User;

@Controller
public class RepositoryController {

	private static final Repository[] NO_REPOSITORIES = {};
	
    @GetMapping("/api/v0/github/user/{username}/repositories")
    @ResponseBody
    public Repository[] getRepositories(@PathVariable(name="username") String name) throws IOException {
    	User user = Github.GetRepositoriesRecursively(name);
    	if (user.repositories == null) {
    		return NO_REPOSITORIES;
    	}
    	return user.repositories;
    }
	
}
