--liquibase formatted sql

--changeset mbuzon:11
INSERT INTO region_area
(
    id,
    name,
    slug
)
VALUES
(168, 'United Kingdom', 'united-kingdom-168');
--rollback delete from region_area where id = 168;

--changeset mbuzon:12
INSERT INTO region_group
(
    id,
    name,
    slug,
    region_area_id
)
VALUES
(169, 'Solicitors', 'solicitors-169', 168);
--rollback delete from region_group where id = 169;

--changeset mbuzon:13
INSERT INTO region
(
    id,
    name,
    slug,
    region_group_id
)
VALUES
(170, 'London', 'london-170', 169);
--rollback delete from region where id = 170;

--changeset mbuzon:14
INSERT INTO firm
(
    id,
    name,
    slug,
    image_url,
    website_url
)
VALUES
(960, 'Allen & Overy', 'allen--overy-960', 'https://via.placeholder.com/150', 'http://allen--overy-960.com'),
(4220, 'Osborne Clarke', 'osborne-clarke-4220', 'https://via.placeholder.com/150', 'http://osborne-clarke-4220.com'),
(6339, 'Slater and Gordon', 'slater-and-gordon-6339', 'https://via.placeholder.com/150', 'http://slater-and-gordon-6339.com'),
(11229, 'BDB Pitmans', 'bdb-pitmans-11229', 'https://via.placeholder.com/150', 'http://bdb-pitmans-11229.com'),
(13185, 'Leigh Day', 'leigh-day-13185', 'https://via.placeholder.com/150', 'http://leigh-day-13185.com'),
(13837, 'Michelmores', 'michelmores-13837', 'https://via.placeholder.com/150', 'http://michelmores-13837.com'),
(15467, 'Sackers', 'sackers-15467', 'https://via.placeholder.com/150', 'http://sackers-15467.com'),
(15630, 'Fletchers', 'fletchers-15630', 'https://via.placeholder.com/150', 'http://fletchers-15630.com');
--rollback delete from firm where id in (960, 4220, 6339, 11229, 13185, 13837, 15467, 15630);

--changeset mbuzon:15
INSERT INTO badge
(
    id,
    name,
    slug
)
VALUES
(8, 'Top 10 worldwide', 'top-10-worldwide-8'),
(9, 'Top 50 worldwide', 'top-50-worldwide-9');
--rollback delete from badge where id in (8,9);

--changeset mbuzon:16
INSERT INTO firm_badge
(
    firm_id,
    badge_id
)
VALUES
(960, 8),
(960, 9),
(6339, 8),
(6339, 9),
(11229, 8),
(13185, 8),
(13185, 9),
(13837, 8),
(13837, 9),
(15630, 9);
--rollback delete from firm_badge where badge_id in (8,9);

--changeset mbuzon:17
INSERT INTO firm_region
(
    id,
    firm_id,
    region_id,
    booking,
    cross_border_capability,
    client_satisfaction_rating,
    expertise_and_reputation_rating
)
VALUES
(988, 960, 170, false, 'GLOBAL', 709, 753),
(4248, 4220, 170, false, 'GLOBAL', 350, 70),
(6367, 6339, 170, false, 'LOCAL', 793, 266),
(11257, 11229, 170, false, 'GLOBAL', 442, 141),
(13213, 13185, 170, false, 'LOCAL', 654, 366),
(13865, 13837, 170, false, 'GLOBAL', 216, 795),
(15495, 15467, 170, true, 'GLOBAL', 470, 719),
(15658, 15630, 170, true, 'GLOBAL', 928, 221);
--rollback delete from firm_region where id in (988, 4248, 6367, 11257, 13213, 13865, 15495, 15658);

--changeset mbuzon:18
INSERT INTO ranking
(
    id,
    firm_id,
    trend,
    diversity,
    tier,
    position
)
VALUES
(19859, 960, 'STABLE_5', true, 'T_3', 0),
(28091, 4220, 'STABLE_2', true, 'T_2', 0),
(30793, 6339, 'DOWN', false, 'T_1', 0),
(36274, 11229, 'STABLE_2', true, 'T_1', 0),
(42084, 13185, 'DOWN', false, 'T_1', 0),
(44716, 13837, 'STABLE_4', true, 'T_3', 0),
(46179, 15467, 'STABLE_1', false, 'T_3', 0),
(47747, 15630, 'STABLE_3', true, 'T_2', 0);
--rollback delete from ranking where id in (19859, 28091, 30793, 36274, 42084, 44716, 46179, 47747);




