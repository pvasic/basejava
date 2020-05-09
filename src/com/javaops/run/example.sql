SELECT * FROM resume;
SELECT * FROM resume r
                  LEFT JOIN contact c
                            ON r.uuid = c.resume_uuid;
SELECT * FROM contact c
where resume_uuid= '47580585-d6bd-4338-a86a-aaf5bb6b4fba';
DELETE FROM text_section where resume_uuid = 'f0326422-4eaf-4dd6-85c2-de38a8ebf9df';
UPDATE contact SET value = 'VAL', type = 'SKYPE' WHERE resume_uuid = '47580585-d6bd-4338-a86a-aaf5bb6b4fba';




SELECT * FROM resume r
                  LEFT JOIN contact c
                            ON r.uuid = c.resume_uuid
WHERE r.uuid ='f0326422-4eaf-4dd6-85c2-de38a8ebf9df';

SELECT r.*, c.* FROM resume r
                         LEFT JOIN
                     (SELECT * FROM contact
                      WHERE resume_uuid = 'f0326422-4eaf-4dd6-85c2-de38a8ebf9df') c
                     ON r.uuid = c.resume_uuid;


INSERT INTO text_section (resume_uuid, type, content) VALUES ('f0326422-4eaf-4dd6-85c2-de38a8ebf9df','OBJECTIVE', 'jjjjjjjjjj');

SELECT * FROM text_section;

INSERT INTO list_section (resume_uuid, type, items) VALUES ('a886c403-b807-4c56-b1de-f7298258c8ac','ACHIEVEMENT', E'jjjjjjjjjj\\nttttttttt\\nbbbbbbbbb');

SELECT * FROM text_section;