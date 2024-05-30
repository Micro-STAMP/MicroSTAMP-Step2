INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",46,1,0,1,"ATC Manager",NULL,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",47,1,0,1,"Controller A",NULL,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",48,0,0,1,"Controller B",NULL,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",49,1,0,1,"ITP Flight Crew",NULL,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",50,1,0,1,"Ref Flight Crew",NULL,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",51,1,0,1,"ITP Aircraft",NULL,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",52,1,0,1,"ITP Equipment",51,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",53,1,0,1,"TCAS/Transponder",51,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",54,1,0,1,"GNSSU Receiver",51,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",55,1,0,1,"ADS-B",51,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",56,1,0,1,"Reference Aicraft",NULL,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",57,1,0,1,"TCAS/Transponder",56,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Sensor",58,1,0,1,"Other Sensors",56,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",59,1,0,1,"ADS-B",56,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",60,1,0,1,"GNSSU Receiver",56,1);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",61,1,0,1,"GPS Constellation",NULL,1);

INSERT INTO `connection` VALUES (68,3,1,1,46,1);
INSERT INTO `connection` VALUES (69,4,1,46,1,1);
INSERT INTO `connection` VALUES (70,0,1,46,47,1);
INSERT INTO `connection` VALUES (71,1,1,47,46,1);
INSERT INTO `connection` VALUES (72,0,1,47,49,1);
INSERT INTO `connection` VALUES (73,1,1,49,47,1);
INSERT INTO `connection` VALUES (74,0,1,49,51,1);
INSERT INTO `connection` VALUES (75,1,1,51,49,1);
INSERT INTO `connection` VALUES (76,0,1,53,57,1);
INSERT INTO `connection` VALUES (77,1,1,57,53,1);
INSERT INTO `connection` VALUES (78,0,1,54,55,1);
INSERT INTO `connection` VALUES (79,1,1,59,55,1);
INSERT INTO `connection` VALUES (80,1,1,61,54,1);
INSERT INTO `connection` VALUES (81,2,0,46,48,1);
INSERT INTO `connection` VALUES (82,2,0,48,46,1);
INSERT INTO `connection` VALUES (83,2,0,47,48,1);
INSERT INTO `connection` VALUES (84,2,0,48,47,1);
INSERT INTO `connection` VALUES (85,2,0,47,50,1);
INSERT INTO `connection` VALUES (86,2,0,50,47,1);
INSERT INTO `connection` VALUES (87,2,0,48,49,1);
INSERT INTO `connection` VALUES (88,2,0,48,50,1);
INSERT INTO `connection` VALUES (89,0,1,50,56,1);
INSERT INTO `connection` VALUES (90,1,1,56,50,1);
INSERT INTO `connection` VALUES (91,1,1,60,59,1);
INSERT INTO `connection` VALUES (92,1,1,61,60,1);

INSERT INTO `image` VALUES (6,'B1.png',1);

INSERT INTO `label` VALUES (140,"Policy",68);
INSERT INTO `label` VALUES (141,"Certification Information",69);
INSERT INTO `label` VALUES (142,"Instruction",70);
INSERT INTO `label` VALUES (143,"Procedures",70);
INSERT INTO `label` VALUES (144,"Training, Reviews",70);
INSERT INTO `label` VALUES (145,"Status Reports",71);
INSERT INTO `label` VALUES (146,"Incident Reports",71);
INSERT INTO `label` VALUES (147,"Flight",72);
INSERT INTO `label` VALUES (148,"Instructions",72);
INSERT INTO `label` VALUES (149,"ITP Clearance",72);
INSERT INTO `label` VALUES (150,"Request Clearance",73);
INSERT INTO `label` VALUES (151,"Transcribe ITP Info",73);
INSERT INTO `label` VALUES (152,"Maneuver Command",74);
INSERT INTO `label` VALUES (153,"Attitude Information",75);
INSERT INTO `label` VALUES (154,"TCAS Interrogations",77);
INSERT INTO `label` VALUES (155,"Ref Aircraft",79);
INSERT INTO `label` VALUES (156,"State(speed, heading, alt, etc)",79);
INSERT INTO `label` VALUES (157,"Information",79);
INSERT INTO `label` VALUES (158,"Time/State Data",80);
INSERT INTO `label` VALUES (159,"Airspace Transfer",83);
INSERT INTO `label` VALUES (160,"Airspace Transfer",84);
INSERT INTO `label` VALUES (161,"Flight Instructions",85);
INSERT INTO `label` VALUES (162,"Request/Transmit Information",86);
INSERT INTO `label` VALUES (163,"Flight Instructions",87);
INSERT INTO `label` VALUES (164,"Maneuver Command",89);
INSERT INTO `label` VALUES (165,"Attitude Information",90);