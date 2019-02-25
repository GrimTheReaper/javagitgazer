package com.github.grimthereaper.javagitgaze.githubutils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class User {
	public String       login;
	public int          id;
	public User[]       followers;
	public Repository[] repositories;
}
