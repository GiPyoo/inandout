INSERT INTO USER (id, name, password, account, user_latest_time) VALUES (1,'kiki', '1111','1234567890', CURRENT_TIMESTAMP() );

INSERT INTO api_account(id, grid_info, amount, accountNumber, withdrawableAmount, nextTransactionDate)
VALUES (1, '0019', '1150000', '1234567890', '1150000', '');
INSERT INTO transaction_history(id, client, transactionDate, amount,place, cash, inputCash, transactionType, outputCash, originalCash )
VALUES (1,'월급', '20190624121300','1200000' ,'종암동','1200000','1200000','','0','0');
INSERT INTO transaction_history(id, client, transactionDate, amount,place, cash, inputCash, transactionType, outputCash, originalCash )
VALUES (1,'미스터피자', '20190625131000','1150000' ,'종암동','50000','0','','50000','0');
INSERT INTO CATEGORY (id, name) VALUES (0, '미분류')
INSERT INTO CATEGORY (id, name) VALUES (1, '식비')
INSERT INTO CATEGORY (id, name) VALUES (2, '취미/여가')
INSERT INTO CATEGORY (id, name) VALUES (3, '카페/간식')
INSERT INTO CATEGORY (id, name) VALUES (4, '편의점/마트/잡화')
INSERT INTO CATEGORY (id, name) VALUES (5, '주거/통신')
INSERT INTO CATEGORY (id, name) VALUES (6, '쇼핑')
INSERT INTO CATEGORY (id, name) VALUES (7, '교육')

INSERT INTO virtual_account (id, category_id, user_id, name, amount) VALUES (1, 0, 1,'귀찮음의 산물', 1000000);
INSERT INTO virtual_account (id, category_id, user_id,name, amount) VALUES (2, 1, 1, '병재의 허세용 식비 통장', 200000);

