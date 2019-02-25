package com.github.grimthereaper.javagitgaze.githubutils;

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;

// NOTE: This will be very similar to how I did my Golang one.
public class Github {

	private static final int    repositoryLimit          = 3;
	private static final int    repositoryStargazerLimit = 3;
	private static final int    repositoryDepth          = 3;
	private static final String repositoryURI            = "https://api.github.com/users/%s/repos";
	private static final String repositoryStargazerURI   = "https://api.github.com/repos/%s/%s/stargazers";

	private static final int    followerLimit = 5;
	private static final int    followerDepth = 3;
	private static final String followerURI   = "https://api.github.com/users/%s/followers";

	private static Repository[] getRepositories(String username) throws IOException {
		String repositoriesText = JsonReader.readJsonFromUrl(String.format(repositoryURI, username));
		Gson gson = new Gson();
		Repository[] repositories = gson.fromJson(repositoriesText, Repository[].class);

		if (repositories.length > repositoryLimit) {
			repositories = Arrays.copyOfRange(repositories, 0, repositoryLimit);
		}

		for (int i = 0; i < repositories.length; i++) {
			String starGazersText = JsonReader.readJsonFromUrl(String.format(repositoryStargazerURI, username, repositories[i].name));
			repositories[i].stargazers = gson.fromJson(starGazersText, User[].class);
			if (repositories[i].stargazers.length > repositoryStargazerLimit) {
				repositories[i].stargazers = Arrays.copyOfRange(repositories[i].stargazers, 0, repositoryLimit);
			}
		}

		return repositories;
	}

	private static Repository[] getRepositoriesRecursively(String username, int depth) throws IOException {
		if (depth >= repositoryDepth) {
			return null;
		}

		Repository[] repositories = getRepositories(username);
		for(int i = 0; i < repositories.length; i++) {
			for (int ii = 0; ii < repositories[i].stargazers.length; ii ++) {
				repositories[i].stargazers[ii].repositories = getRepositoriesRecursively(repositories[i].stargazers[ii].login, depth+1);
			}
		}

		return repositories;
	}

	public static User GetRepositoriesRecursively(String username) throws IOException {
		User user = new User(); 
		user.login = username;

		user.repositories = getRepositoriesRecursively(username, 0);

		return user;
	}


	//
	// Follower Section
	// 


	private static User[] getFollowers(String username) throws IOException {
		String json = JsonReader.readJsonFromUrl(String.format(followerURI, username));
		Gson gson = new Gson();
		User[] users = gson.fromJson(json, User[].class);

		if (users.length > followerLimit) {
			users = Arrays.copyOfRange(users, 0, followerLimit);
		}

		return users;
	}

	private static User[] getFollowersRecursively(String username, int depth) throws IOException {
		if (depth > followerDepth) {
			return null;
		}

		User[] followers = getFollowers(username);
		System.out.println(followers.length);
		for(int i = 0; i < followers.length; i++) {
			followers[i].followers = getFollowersRecursively(followers[i].login, depth+1);
		}

		return followers;
	}

	public static User GetFollowersRecursively(String username) throws IOException {
		User user = new User();
		user.login = username;

		user.followers = getFollowersRecursively(user.login, 0);

		return user;
	}	

}
