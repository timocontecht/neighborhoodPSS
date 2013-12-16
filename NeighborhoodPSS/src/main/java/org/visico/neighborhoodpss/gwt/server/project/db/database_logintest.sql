INSERT INTO PROJECT (name, latitude,longitude) VALUES
	("Project 1", 52.260173,6.788813),
	("Project 2", 49.569982,10.964647),
	("Project 3", 40.736332,-73.984097);
	

INSERT INTO USER (name, email, password) VALUES
	("User 1", "user1@email.com", "pass1"),
	("User 2", "user2@email.com", "pass2");
	
INSERT INTO PROJECT_USER (project_id, user_id) 
	Select PROJECT.id, USER.id FROM PROJECT, USER WHERE 
	PROJECT.name = "Project 1"
	 AND USER.name = "User 1";
	
INSERT INTO PROJECT_USER (project_id, user_id)
	Select PROJECT.id, USER.id FROM PROJECT, USER WHERE 
	PROJECT.name = "Project 2"
	 AND USER.name = "User 2";
	 
INSERT INTO PROJECT_USER (project_id, user_id)
	Select PROJECT.id, USER.id FROM PROJECT, USER WHERE 
	PROJECT.name = "Project 3"
	 AND USER.name = "User 1";
	 
INSERT INTO PROJECT_USER (project_id, user_id)
	Select PROJECT.id, USER.id FROM PROJECT, USER WHERE 
	PROJECT.name = "Project 3"
	 AND USER.name = "User 2";
	