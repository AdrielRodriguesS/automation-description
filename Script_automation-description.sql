CREATE SCHEMA projects;

USE projects;

create table projectdata(
id int(255) auto_increment,
project_name varchar(255),
project_client varchar(255),
serial_number varchar(255) NOT NULL,
project_supply varchar(255),
project_power varchar(255),
main_description text,
primary key (id)
);

create table pneumatics(
id int(255) auto_increment,
tag varchar(255),
pneumatic_type varchar(255),
pneumatic_id varchar(255),
pneumatic_description varchar(255),
associated_signal varchar(255),
fail_event varchar(255),
operation_restriction varchar(255),
hmi_description varchar(255),
movement_description text,
project_id int(255),
primary key (id),
foreign key (project_id) references project_data(id)
);

create table motors(
id int(255) auto_increment,
tag varchar(255),
motor_type varchar(255),
motor_id varchar(255),
motor_description varchar(255),
operation_restriction varchar(255),
hmi_description varchar(255),
movement_description text,
project_id int(255),
primary key (id),
foreign key (project_id) references project_data(id)
);

INSERT INTO project_data (project_name, project_client, serial_number, project_supply,
project_power, main_description)
VALUES ('Project1', 'Client1', 'Client1_001', '380Vca', '25kW', 'Machine 1'),
('Project2', 'Client2', 'Client2_002', '220Vca', '5kW', 'Machine 2');

INSERT INTO pneumatics (tag, pneumatic_type, pneumatic_id, pneumatic_description, associated_signal, 
fail_event, operation_restriction, hmi_description, movement_description, project_id)
VALUES ('40Y1', '5/2 WAY', 'CM-001P1', 'movement to close box', '32BG1', 'Alarm', 'Box in the magazine', 
'Closing Box', 'This pneumatic cilinder is responsible to close the box', '1'),
('40Y2', '5/3 WAY', 'CM-002P2', 'movement to open the box', '32BG2', 'Alarm', 'Box in the conveyor', 
'Opening Box', 'This pneumatic cilinder is responsible to open the box', '1');

INSERT INTO motors (tag, motor_type, motor_id, motor_description, 
operation_restriction, hmi_description, movement_description, project_id)
VALUES ('16M1', 'VFD', 'CM-001E1', 'Conveyor', '41BG1', 'Fail', 'Not applicable', 
'Product entrance', 'This motor is responsible to conveyor movement', '1'),
('17M1', 'Direct', 'CM-002E1', 'Conveyor', '42BG1', 'Fail', 'Not applicable', 
'Product exit', 'This motor is responsible to conveyor movement to exit products', '1');

