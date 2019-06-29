INSERT INTO USER (id, name, password, account, user_latest_time) VALUES (1,'kiki', '1111','1234567890' ,'2008-12-25 15:30:00');

INSERT INTO api_account(id, grid_info, amount, account_number, withdrawable_amount, next_transaction_date) VALUES (1, '0019', '1150000', '1234567890', '1150000', '');

INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (1,'월급', '2019-01-01 10:10:10','1200000' ,'종암동','1200000','1200000','','0','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (2,'미스터피자', '2019-02-26 19:38:34','1150000' ,'종암동','50000','0','','50000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (3,'블랑PC', '2019-03-03 15:20:34','1147000' ,'용봉동','3000','0','','3000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (4,'역전할머니맥주', '2019-04-09 13:03:24','1127000' ,'용봉동','20000','0','','20000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (5,'NIKE충장', '2019-04-26 18:49:34','1027000' ,'충장로','100000','0','','100000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (6,'카페베네', '2019-05-03 11:08:29','1022000' ,'운암동','5000','0','','5000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (7,'크로스핏용봉', '2019-05-15 21:30:34','900000' ,'종암동','122000','0','','122000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (8,'gs25', '2019-05-26 19:38:34','899000' ,'일곡동','1000','0','','1000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (9,'혼끼', '2019-05-30 12:04:34','893000' ,'용봉동','6000','0','','6000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (10,'이비법인택시', '2019-06-01 08:20:11','890000' ,'문흥동','3000','0','','3000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (11,'교촌치킨', '2019-06-07 19:43:34','870000' ,'용봉동','20000','0','','20000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (12,'아모스', '2019-06-15 23:38:52','861200' ,'종암동','8800','0','','8800','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (13,'한국맥도날드', '2019-06-19 23:38:34','853700' ,'봉선동','7500','0','','7500','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (14,'씨제이올리브', '2019-06-20 19:22:34','804700' ,'우남동','49000','0','','49000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (15,'구글플레이', '2019-06-26 12:38:32','803600' ,'삼각동','1100','0','','1100','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (16,'알파문구', '2019-06-26 19:38:34','799400' ,'용봉동','4200','0','','4200','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (17,'CU역삼성정보점', '2019-06-26 21:38:34','787200' ,'삼성동','12200','0','','12200','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (18,'소담채', '2019-06-27 11:13:34','698800' ,'첨단2지구','88400','0','','88400','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (19,'아람약국', '2019-06-28 14:38:11','695100' ,'오치동','3700','0','','3700','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (20,'플랫폼PC', '2019-06-29 16:25:34','691100' ,'종암동','4000','0','','4000','0');

INSERT INTO CATEGORY (id, name) VALUES (0, '미분류')
INSERT INTO CATEGORY (id, name) VALUES (1, '식비')
INSERT INTO CATEGORY (id, name) VALUES (2, '취미/여가')
INSERT INTO CATEGORY (id, name) VALUES (3, '카페/간식')
INSERT INTO CATEGORY (id, name) VALUES (4, '편의점/마트/잡화')
INSERT INTO CATEGORY (id, name) VALUES (5, '주거/통신/교통')
INSERT INTO CATEGORY (id, name) VALUES (6, '쇼핑')
INSERT INTO CATEGORY (id, name) VALUES (7, '교육')

INSERT INTO virtual_account (id, category_id, user_id,amount, name) VALUES (1, 0, 1, 0, '기타');
INSERT INTO virtual_account (id, category_id, user_id,amount, name) VALUES (2, 1, 1, 200000,'밥값');
INSERT INTO virtual_account (id, category_id, user_id,amount, name) VALUES (3, 2, 1, 100000,'10만원 한도');
INSERT INTO virtual_account (id, category_id, user_id,amount, name) VALUES (4, 5, 1, 100000,'걸어다니기');


