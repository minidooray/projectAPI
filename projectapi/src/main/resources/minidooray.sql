create table Accounts(
	account_id varchar(30) unique primary key,
    account_pwd varchar(255) not null,
    account_name varchar(20) not null,
    account_email varchar(100) unique not null,
    account_status varchar(100)
);

create table Projects(
	project_id BIGINT auto_increment primary key,
    project_admin_id varchar(30) not null,
    project_name varchar(255) not null,
    project_description varchar(1024) not null,
    project_status varchar(100)
);

create table Project_members(
	account_id varchar(30) not null,
    project_id BIGINT not null
);

create table Tags(
	project_id BIGINT not null,
	tag_id BIGINT primary key auto_increment,
    tag_content varchar(255)
);

create table Milestones(
	project_id BIGINT not null,
	milestone_id BIGINT primary key auto_increment,
    milestone_content varchar(100) not null,
    milestone_start_at date,
    milestone_end_at date
);

create table Tasks(
	task_id BIGINT primary key auto_increment,
    task_title varchar(255) not null,
    task_content varchar(5096),
    task_manager_id varchar(30),
    task_milestone_id BIGINT,
	task_start_at Date,
    task_end_at Date,
    project_id BIGINT
);

create table TaskTags(
	task_id BIGINT,
    tag_id BIGINT
);

create table Comments(
	comment_id BIGINT primary key auto_increment,
    task_id BIGINT not null,
    comment_content varchar(1024) not null,
    comment_account_id varchar(30) not null,
    comment_created_at Date
);