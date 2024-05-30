INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",23,1,0,1,"Driver",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",24,0,0,1,"Brake Pedal",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",25,0,0,1,"Multi-function Switch",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",26,0,0,1,"Instrument Cluster",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",27,0,0,1,"Accelerator Pedal",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",28,1,0,1,"Adaptive Cruise Control(ACC) Module",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",29,1,0,1,"Brake Control Module",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Sensor",30,0,0,1,"Radar",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",31,1,0,1,"Powertrain Control Module",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",32,0,0,1,"Service Brakes",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Sensor",33,0,0,1,"Whell Speed Sensor",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",34,0,0,1,"Electronic Throttle Body",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",35,1,0,1,"Vehicle",NULL,6);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",36,1,0,1,"Lead Vehicle",NULL,6);

INSERT INTO `connection` VALUES (25,0,1,23,24,6);
INSERT INTO `connection` VALUES (26,0,1,24,29,6);
INSERT INTO `connection` VALUES (27,0,1,29,32,6);
INSERT INTO `connection` VALUES (28,0,1,32,35,6);
INSERT INTO `connection` VALUES (29,0,1,23,26,6);
INSERT INTO `connection` VALUES (30,0,1,26,28,6);
INSERT INTO `connection` VALUES (31,1,1,29,28,6);
INSERT INTO `connection` VALUES (32,1,1,33,29,6);
INSERT INTO `connection` VALUES (33,1,1,35,33,6);
INSERT INTO `connection` VALUES (34,1,1,26,23,6);
INSERT INTO `connection` VALUES (35,1,1,28,26,6);
INSERT INTO `connection` VALUES (36,1,1,30,28,6);
INSERT INTO `connection` VALUES (37,1,1,28,31,6);
INSERT INTO `connection` VALUES (38,1,1,23,27,6);
INSERT INTO `connection` VALUES (39,1,1,27,31,6);
INSERT INTO `connection` VALUES (40,1,1,31,34,6);
INSERT INTO `connection` VALUES (41,0,1,34,31,6);
INSERT INTO `connection` VALUES (42,1,1,34,35,6);
INSERT INTO `connection` VALUES (43,2,0,36,30,6);

INSERT INTO `image` VALUES (4,'B6.png',6);

INSERT INTO `label` VALUES (66,"Brake Cmd",25);
INSERT INTO `label` VALUES (67,"Braking Signal",26);
INSERT INTO `label` VALUES (68,"Brake Cmd",27);
INSERT INTO `label` VALUES (69,"Friction",28);
INSERT INTO `label` VALUES (70,"On",29);
INSERT INTO `label` VALUES (71,"Off",29);
INSERT INTO `label` VALUES (72,"Set",29);
INSERT INTO `label` VALUES (73,"Cancel",29);
INSERT INTO `label` VALUES (74,"Inc",29);
INSERT INTO `label` VALUES (75,"Dec",29);
INSERT INTO `label` VALUES (76,"On",30);
INSERT INTO `label` VALUES (77,"Off",30);
INSERT INTO `label` VALUES (78,"Set",30);
INSERT INTO `label` VALUES (79,"Cancel",30);
INSERT INTO `label` VALUES (80,"Inc",30);
INSERT INTO `label` VALUES (81,"Dec",30);
INSERT INTO `label` VALUES (82,"Braking status",31);
INSERT INTO `label` VALUES (83,"Vehicle speed",31);
INSERT INTO `label` VALUES (84,"Wheel Speed",32);
INSERT INTO `label` VALUES (85,"Wheel Speed",33);
INSERT INTO `label` VALUES (86,"ACC On",34);
INSERT INTO `label` VALUES (87,"ACC Off",34);
INSERT INTO `label` VALUES (88,"ACC Canceled",34);
INSERT INTO `label` VALUES (89,"ACC Active",34);
INSERT INTO `label` VALUES (90,"ACC On",35);
INSERT INTO `label` VALUES (91,"ACC Off",35);
INSERT INTO `label` VALUES (92,"ACC Canceled",35);
INSERT INTO `label` VALUES (93,"ACC Active",35);
INSERT INTO `label` VALUES (94,"Distance to lead vehicle",36);
INSERT INTO `label` VALUES (95,"Acceleration Signal",37);
INSERT INTO `label` VALUES (96,"Accelerate Cmd",38);
INSERT INTO `label` VALUES (97,"Acceleration Signal",39);
INSERT INTO `label` VALUES (98,"Throttle opening",40);
INSERT INTO `label` VALUES (99,"Throttle position",41);
INSERT INTO `label` VALUES (100,"Friction",42);
INSERT INTO `label` VALUES (101,"Distance to lead vehicle",43);