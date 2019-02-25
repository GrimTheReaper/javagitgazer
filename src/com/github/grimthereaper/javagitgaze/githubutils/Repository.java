package com.github.grimthereaper.javagitgaze.githubutils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Repository {
	public int    id;
	public String name;
	public User   owner;
	public User[] stargazers;
}
