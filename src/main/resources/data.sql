 insert into provider (id,name,support_phone)
 values(10001,'Apple','02188547500');

 insert into product (id,commentable,cost,currency,insert_date,name,status,votable,provider_id)
 values(10001,'2',150000,'IRR','2012-09-17','IPhone12ProMax','1','1',10001);

 insert into product (id,commentable,cost,currency,insert_date,name,status,votable,provider_id)
 values(10002,'2',2000,'IRR','2020-09-20','IPod','1','1',10001);

 insert into product (id,commentable,cost,currency,insert_date,name,status,votable,provider_id)
 values(10003,'2',3000,'IRR','2020-10-03','IPadAir','1','1',10001);

 insert into product (id,commentable,cost,currency,insert_date,name,status,votable,provider_id)
 values(10004,'2',4000,'IRR','2021-01-03','AirPod','1','1',10001);

 insert into product (id,commentable,cost,currency,insert_date,name,status,votable,provider_id)
 values(10005,'2',5000,'IRR','2021-02-05','MacBookAir','1','1',10001);

 insert into user (ID,EMAIL,FIRST_NAME,LAST_NAME,PHONE,USERNAME)
 values(10001,'mhr.peykari@gmail.com','mehrdad','Peykari','09382052469','mehrdadinho');

 insert into comment (id,status,text,title,product_id,user_id)
 values(10001,1,'Amazing','best iphone ever made',10001,10001);

 insert into comment (id,status,text,title,product_id,user_id)
 values(10002,1,'بی نظیر','از تمام گوشی های اندروید بهتره',10001,10001);

 insert into comment (id,status,text,title,product_id,user_id)
 values(10003,1,'افتضاح','اینو بخری یعنی پولتو ریختی دور',10001,10001);

 insert into comment (id,status,text,title,product_id,user_id)
 values(10004,1,'ظاهر زیبا','ظاهر زیباییی داره به نظر موبایل خوبی میاد',10001,10001);

 insert into vote (id,score,status,product_id,user_id)
 values(10001,'2',1,10001,10001);

 insert into vote (id,score,status,product_id,user_id)
 values(10002,'4',1,10001,10001);

 insert into vote (id,score,status,product_id,user_id)
 values(10003,'3',1,10001,10001);

 insert into vote (id,score,status,product_id,user_id)
 values(10004,'2',1,10001,10001);

 insert into orders (id,status,user_id,date)
 values(10001,3,10001,'2020-01-20');

 insert into orders (id,status,user_id,date)
 values(10002,3,10001,'2021-01-03');

 insert into order_detail (id,count,order_id,product_id)
 values(10001,12,10001,10001);

 insert into order_detail (id,count,order_id,product_id)
 values(10002,50,10002,10001);

