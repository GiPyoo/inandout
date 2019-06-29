INSERT INTO USER (id, name, password, account, user_latest_time) VALUES (1,'kiki', '1111','1234567890' ,'2008-12-25 15:30:00');

INSERT INTO api_account(id, grid_info, amount, account_number, withdrawable_amount, next_transaction_date) VALUES (1, '0019', '1150000', '1234567890', '1150000', '');

INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (1,'월급', CURRENT_TIMESTAMP(),'1200000' ,'종암동','1200000','1200000','','0','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (2,'미스터피자', CURRENT_TIMESTAMP(),'1150000' ,'종암동','50000','0','','50000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (3,'블랑PC', CURRENT_TIMESTAMP(),'1147000' ,'용봉동','3000','0','','3000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (4,'역전할머니맥주', CURRENT_TIMESTAMP(),'1127000' ,'용봉동','20000','0','','20000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (5,'NIKE충장', CURRENT_TIMESTAMP(),'1027000' ,'충장로','100000','0','','100000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (6,'카페베네', CURRENT_TIMESTAMP(),'1022000' ,'운암동','5000','0','','5000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (7,'크로스핏용봉', CURRENT_TIMESTAMP(),'900000' ,'종암동','122000','0','','122000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (8,'gs25', CURRENT_TIMESTAMP(),'899000' ,'일곡동','1000','0','','1000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (9,'혼끼', CURRENT_TIMESTAMP(),'893000' ,'용봉동','6000','0','','6000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (10,'이비법인택시', CURRENT_TIMESTAMP(),'890000' ,'문흥동','3000','0','','3000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (11,'교촌치킨', CURRENT_TIMESTAMP(),'870000' ,'용봉동','20000','0','','20000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (12,'아모스', CURRENT_TIMESTAMP(),'861200' ,'종암동','8800','0','','8800','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (13,'한국맥도날드', CURRENT_TIMESTAMP(),'853700' ,'봉선동','7500','0','','7500','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (14,'씨제이올리브', CURRENT_TIMESTAMP(),'804700' ,'우남동','49000','0','','49000','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (15,'구글플레이', CURRENT_TIMESTAMP(),'803600' ,'삼각동','1100','0','','1100','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (16,'알파문구', CURRENT_TIMESTAMP(),'799400' ,'용봉동','4200','0','','4200','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (17,'CU역삼성정보점', CURRENT_TIMESTAMP(),'787200' ,'삼성동','12200','0','','12200','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (18,'소담채', CURRENT_TIMESTAMP(),'698800' ,'첨단2지구','88400','0','','88400','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (19,'아람약국', CURRENT_TIMESTAMP(),'695100' ,'오치동','3700','0','','3700','0');
INSERT INTO transaction_history(id, client, transaction_date, amount, place, cash, input_cash, transaction_type, output_cash, original_cash ) VALUES (20,'플랫폼PC', CURRENT_TIMESTAMP(),'691100' ,'종암동','4000','0','','4000','0');

INSERT INTO CATEGORY (id, name) VALUES (0, '미분류')
INSERT INTO CATEGORY (id, name) VALUES (1, '식비')
INSERT INTO CATEGORY (id, name) VALUES (2, '취미/여가')
INSERT INTO CATEGORY (id, name) VALUES (3, '카페/간식')
INSERT INTO CATEGORY (id, name) VALUES (4, '편의점/마트/잡화')
INSERT INTO CATEGORY (id, name) VALUES (5, '주거/통신/교통')
INSERT INTO CATEGORY (id, name) VALUES (6, '쇼핑')
INSERT INTO CATEGORY (id, name) VALUES (7, '교육')

INSERT INTO virtual_account (id, category_id, user_id,amount, name) VALUES (1, 0, 1, 0, '기타');
INSERT INTO virtual_account (id, category_id, user_id,amount, name) VALUES (2, 1, 1, 200000,'점심밥값');

