# Java Git Gazer
Quick and dirty Java version of my GitGaze application (originally written in Golang).

I figured since I'm applying for a Java position as well, I should write a Java version of the challenge.

# Notes:
I didn't remember what frameworks were in use, so I assumed:
- Spring
- Maven

To be honest though, this was my first time using Maven or Spring since 2015, so please excuse my code!

# Requirements:
- Maven

# Pre-Ignition:
Make sure to have a github oAuth token, or a github personal token, as rate
limiting cripples this application, as github limits non tokenized request to
60 per hour.

You can do that by following [this guide](https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line)!

Then, put the token in `src/com/github/grimthereaper/javagitgaze/githubutils/JsonReader.java:15`, in the token variable.

# How to run:
With Maven: `spring-boot:run`   
I believe this binds to `:8080`.

## Routes:
| Route | Method | Description |
| ----- | ------ | ----------- |
| `/api/v0/github/user/{username}/repositories` | `GET` | Gets the repositories | 
| `/api/v0/github/user/{username}/followers`    | `GET` | Gets the followers    |    
