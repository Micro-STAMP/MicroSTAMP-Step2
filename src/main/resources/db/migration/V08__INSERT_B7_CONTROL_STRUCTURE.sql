INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",13,1,0,1,"Driver",NULL,7);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",14,1,0,1,"Auto-Hold Module",NULL,7);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",15,0,1,1,"Physical Vehicle",NULL,7);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",16,1,0,1,"Braking System",15,7);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",17,1,0,1,"Propulsion System",15,7);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",18,1,0,1,"Environment & Other Drivers",NULL,7);


INSERT INTO `connection` VALUES (6,0,1,13,15,7);
INSERT INTO `connection` VALUES (7,1,1,15,13,7);
INSERT INTO `connection` VALUES (8,0,1,13,14,7);
INSERT INTO `connection` VALUES (9,1,1,14,13,7);
INSERT INTO `connection` VALUES (10,0,1,13,17,7);
INSERT INTO `connection` VALUES (11,1,1,15,13,7);
INSERT INTO `connection` VALUES (12,0,1,14,16,7);
INSERT INTO `connection` VALUES (13,1,1,16,14,7);
INSERT INTO `connection` VALUES (14,1,1,17,14,7);
INSERT INTO `connection` VALUES (15,1,1,15,14,7);
INSERT INTO `connection` VALUES (16,0,1,18,15,7);
INSERT INTO `connection` VALUES (17,1,1,15,18,7);
INSERT INTO `connection` VALUES (18,1,1,18,13,7);


INSERT INTO `image` VALUES (2,'B7.png',7);


INSERT INTO `label` VALUES (16,"Brake",6);
INSERT INTO `label` VALUES (17,"Pedal response",7);
INSERT INTO `label` VALUES (18,"Enable AH",8);
INSERT INTO `label` VALUES (19,"Disable AH",8);
INSERT INTO `label` VALUES (20,"Brake pedal on",8);
INSERT INTO `label` VALUES (21,"Brake pedal off",8);
INSERT INTO `label` VALUES (22,"AH Enable",9);
INSERT INTO `label` VALUES (23,"AH Disabled",9);
INSERT INTO `label` VALUES (24,"Accelerate",10);
INSERT INTO `label` VALUES (25,"Shift",10);
INSERT INTO `label` VALUES (26,"Vehicle speed",11);
INSERT INTO `label` VALUES (27,"Visual feedback",11);
INSERT INTO `label` VALUES (28,"Hold",12);
INSERT INTO `label` VALUES (29,"Release",12);
INSERT INTO `label` VALUES (30,"Additional Pressure",12);
INSERT INTO `label` VALUES (31,"Wheel speed",13);
INSERT INTO `label` VALUES (32,"Accel pos.",14);
INSERT INTO `label` VALUES (33,"PRNDL",14);
INSERT INTO `label` VALUES (34,"Driver presence",15);
INSERT INTO `label` VALUES (35,"Inclination",15);
INSERT INTO `label` VALUES (36,"Visual cues",18);
INSERT INTO `label` VALUES (37,"Physical feedback",18);


