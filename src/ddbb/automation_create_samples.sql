/*
	This SQL file...
	
	1) Empties contents of tables.
	2) Creates relationships.
	3) Inserts sample data for testing.
	
	Make sure databases are created from Spring first, by running the project at least once, then fill in the data just by importing this from PMA.
*/

USE jobs_board_db;

DELETE FROM job_applications;
DELETE FROM registered_users;
DELETE FROM vacancies;
DELETE FROM company;

ALTER TABLE job_applications AUTO_INCREMENT = 1;
ALTER TABLE registered_users AUTO_INCREMENT = 1;
ALTER TABLE vacancies AUTO_INCREMENT = 1;
ALTER TABLE company AUTO_INCREMENT = 1;

ALTER TABLE vacancies ADD CONSTRAINT FGK_pub_id FOREIGN KEY IF NOT EXISTS (publisher) REFERENCES company(cid);
ALTER TABLE job_applications ADD CONSTRAINT FGK_JA_jid FOREIGN KEY IF NOT EXISTS (job_id) REFERENCES vacancies(vid);
ALTER TABLE job_applications ADD CONSTRAINT FGK_JA_uid FOREIGN KEY IF NOT EXISTS (user_idno) REFERENCES registered_users(ruid);
	-- "uid" and "user_id" are reserved words apparently, which is annoying for naming.

INSERT INTO company(name, description, contact_email, building_addr, logo_filename, contact_phone_local, contact_phone_mob) VALUES
	("Test company", "Test company that does stuff.", "hello@example.com", "Street 1 123", "test.png", "+59800000000", "+59890000000"),
	("Another one", "It also exists I guess.", "hello2@example.com", "Street 4 20", "test.png", "+59800000001", "+59890000001")
;

INSERT INTO vacancies(publisher, name, job_description, recruitment_slots, dt_recruitment_begin, dt_recruitment_end, category, terms_documentation, salary, vacancy_status) VALUES
	(2, "Sample job", "You do this and that.",0,NOW(),"2023-01-01 00:00:00","Full-time","Test doc 1.pdf",1200.0,0),
	(1, "Another one", "Blank description.",5,NOW(),"2023-02-01 04:00:00","Freelancer","Test doc 2.pdf",1680.0,1),
	(1, "Available job", "Sign up for this one pls.",99,NOW(),"2023-01-01 00:00:00","Part time","Test doc 1.pdf",-1,1)
;

INSERT INTO registered_users(name, surname, username, email, password, biography, home_addr, home_phone, mob_phone, profpic_filename, available_to_work) VALUES
	("Bruh", "Moment", "bruh_moment", "bruhmoment@bruh.com", "bruhbruh", "Bruh Moment", "127.0.0.1", "+59800000001", "+59890000002","test.png",false),
	("akjvnfd", "ajdshv", "test_guest", "test0@bruh.com", "test0", "ajsdkkvhf", "localhost", "+59800000002", "+59890000003","test.png",true)
;

INSERT INTO job_applications(job_id,user_idno,dt_apply) VALUES
	(1,1,NOW()),
	(1,2,NOW()),
	(2,1,NOW())
;