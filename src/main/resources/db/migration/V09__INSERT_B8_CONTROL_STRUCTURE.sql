INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",19,1,0,1,"JAXA Ground Station",NULL,8);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",20,1,0,1,"NASA Ground Station",NULL,8);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("Controller",21,1,0,1,"International Space Station (ISS)",NULL,8);

INSERT INTO component (dtype, id, border, is_control_structure, is_visible, name, father_id, control_structure_id)
VALUES("ControlledProcess",22,1,0,1,"Autonomous H-II Transfer Vehicle (HTV)",NULL,8);


INSERT INTO `connection` VALUES (19,0,1,19,20,8);
INSERT INTO `connection` VALUES (20,1,1,20,19,8);
INSERT INTO `connection` VALUES (21,0,1,20,21,8);
INSERT INTO `connection` VALUES (22,1,1,21,20,8);
INSERT INTO `connection` VALUES (23,0,1,21,22,8);
INSERT INTO `connection` VALUES (24,1,1,22,21,8);


INSERT INTO `image` VALUES (3,'B8.png',8);


INSERT INTO `label` VALUES (38,"Abort",19);
INSERT INTO `label` VALUES (39,"Retreat",19);
INSERT INTO `label` VALUES (40,"Hold",19);
INSERT INTO `label` VALUES (41,"FRGF Separation",19);
INSERT INTO `label` VALUES (42,"Acknowledgements",20);
INSERT INTO `label` VALUES (43,"HTV Status",20);
INSERT INTO `label` VALUES (44,"Abort",21);
INSERT INTO `label` VALUES (45,"Retreat",21);
INSERT INTO `label` VALUES (46,"Hold",21);
INSERT INTO `label` VALUES (47,"FRGF Separation Enable",21);
INSERT INTO `label` VALUES (48,"FRGF Separation Hold",21);
INSERT INTO `label` VALUES (49,"FRGF Separation",21);
INSERT INTO `label` VALUES (50,"HTV Mode",22);
INSERT INTO `label` VALUES (51,"HTV Fault Status",22);
INSERT INTO `label` VALUES (52,"Crew status",22);
INSERT INTO `label` VALUES (53,"Telemetry",22);
INSERT INTO `label` VALUES (54,"Free Drift",23);
INSERT INTO `label` VALUES (55,"Capture",23);
INSERT INTO `label` VALUES (56,"Abort",23);
INSERT INTO `label` VALUES (57,"Retreat",23);
INSERT INTO `label` VALUES (58,"Hold",23);
INSERT INTO `label` VALUES (59,"FRGF Separation Enable",23);
INSERT INTO `label` VALUES (60,"FRGF Separation Inhibit",23);
INSERT INTO `label` VALUES (61,"FRGF Separation",23);
INSERT INTO `label` VALUES (62,"HTV Mode",24);
INSERT INTO `label` VALUES (63,"HTV Fault Status",24);
INSERT INTO `label` VALUES (64,"Position (visual)",24);
INSERT INTO `label` VALUES (65,"Speed (visual)",24);




