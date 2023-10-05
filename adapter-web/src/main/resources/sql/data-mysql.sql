/**
  Notice Board Data
 */
insert into q_notice_board
(board_name, anonymous_possible, is_admin_possible, is_manager_possible, is_member_possible, created_at, updated_at, is_deleted)
VALUES ('공지사항', 0, 1, 1, 0, now(), now(), 0),
       ("자유게시판", 1, 1, 1, 1, now(), now(), 0),
       ("질문게시판", 1, 1, 1, 1, now(), now(), 0),
       ("구인게시판", 1, 1, 1, 1, now(), now(), 0);