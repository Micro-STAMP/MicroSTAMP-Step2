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


INSERT INTO `connection` VALUES (6,3,1,7,15,7);
INSERT INTO `connection` VALUES (7,4,1,15,7,7);
INSERT INTO `connection` VALUES (8,3,1,7,13,7);
INSERT INTO `connection` VALUES (9,1,1,14,13,7);
INSERT INTO `connection` VALUES (10,0,1,13,17,7);
INSERT INTO `connection` VALUES (11,1,1,15,13,7);
INSERT INTO `connection` VALUES (12,0,1,14,16,7);
INSERT INTO `connection` VALUES (13,1,1,16,14,7);
INSERT INTO `connection` VALUES (14,1,1,17,14,7);
INSERT INTO `connection` VALUES (15,1,1,15,14,7);
INSERT INTO `connection` VALUES (16,0,1,13,15,7);
INSERT INTO `connection` VALUES (17,1,1,15,13,7);
INSERT INTO `connection` VALUES (18,0,1,13,14,7);


INSERT INTO `image` VALUES (2,'B7.png',7);


INSERT INTO `label` VALUES (16,"Visual cues",8);
INSERT INTO `label` VALUES (17,"Physical feedback",8);
INSERT INTO `label` VALUES (18,"AH Enable",9);
INSERT INTO `label` VALUES (19,"AH Disabled",9);
INSERT INTO `label` VALUES (20,"Accelerate",10);
INSERT INTO `label` VALUES (21,"Shift",10);
INSERT INTO `label` VALUES (22,"Vehicle speed",11);
INSERT INTO `label` VALUES (23,"Visual feedback",11);
INSERT INTO `label` VALUES (24,"Hold",12);
INSERT INTO `label` VALUES (25,"Release",12);
INSERT INTO `label` VALUES (26,"Additional Pressure",12);
INSERT INTO `label` VALUES (27,"Wheel speed",13);
INSERT INTO `label` VALUES (28,"Accel pos.",14);
INSERT INTO `label` VALUES (29,"PRNDL",14);
INSERT INTO `label` VALUES (30,"Driver presence",15);
INSERT INTO `label` VALUES (31,"Inclination",15);
INSERT INTO `label` VALUES (32,"Brake",16);
INSERT INTO `label` VALUES (33,"Pedal response",17);
INSERT INTO `label` VALUES (34,"Enable AH",18);
INSERT INTO `label` VALUES (35,"Disable AH",18);
INSERT INTO `label` VALUES (36,"Brake pedal on",18);
INSERT INTO `label` VALUES (37,"Brake pedal off",18);


