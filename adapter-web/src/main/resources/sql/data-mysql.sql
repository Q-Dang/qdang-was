/**
  Notice Board Data
 */
insert into q_notice_board
(board_name, anonymous_possible, is_admin_possible, is_manager_possible, is_member_possible, created_at, updated_at, is_deleted)
values ("공지게시판", 0, 1, 1, 0, now(), now(), 0),
       ("자유게시판", 1, 1, 1, 1, now(), now(), 0),
       ("구인게시판", 1, 1, 1, 1, now(), now(), 0),
       ("중고게시판", 1, 1, 1, 1, now(), now(), 0);

/**
  User Data
 */
insert into q_user
(user_role, login_id, password, username, join_at)
values ("ADMIN", "localAdmin", "$2a$10$F0SCM6AXOY99Xne9g3vrceLv6PzLT2PT0Ama7GUl36wOqTZGSuYPS", "관리자", now()), --- localAdmin / localAdmin1234
       ("MANAGER", "localManager", "$2a$10$xfctuF.Fao4PM.OPI87Ob.9Djs2d5Z.N3N38ZZ4Rmt7E3csrH/Nc6", "매니저", now()),  --- localManager / localManager1234
       ("MEMBER", "localMember1", "$2a$10$n90/z6pAhPycgeCUrMpZ5OCHtefH.3RE5F44VODzMydwFe3htwRyy", "회원1", now()),  --- localMember1 / localMember1111
       ("MEMBER", "localMember2", "$2a$10$2QXrmvQvTADGdqoQfdbyKO85AQCzcyKGZLaOAXpnQRb5Mxb5JSpQe", "회원2", now());  --- localMember2 / localMember2222