package com.github.grimthereaper.javagitgaze.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.grimthereaper.javagitgaze.githubutils.Github;
import com.github.grimthereaper.javagitgaze.githubutils.User;

@Controller
public class FollowerController {

	private static final User[] NO_USERS = {};
	
    @GetMapping("/api/v0/github/user/{username}/followers")
    @ResponseBody
    public User[] getFollowers(@PathVariable(name="username") String name) throws IOException {
    	User user = Github.GetFollowersRecursively(name);
    	if (user.followers == null) {
    		return NO_USERS;
    	}
    	return user.followers;
    }
}


