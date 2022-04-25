##电竞社社区


##资料
[Spring 文档](http://c.biancheng.net/spring_boot/)


##工具
[Github地址]（https://github.com/alltheplayer/EsportsCommunity）
[bootstrap框架]（https://v3.bootcss.com/components/）
[okHttp请求]（https://square.github.io/okhttp/）

##sql 脚本
create table FORUSER
(
ID           INTEGER auto_increment,
ACCOUNT_ID   CHARACTER,
NAME         CHARACTER,
TOKEN        CHARACTER(36),
GMT_CREATE   BIGINT,
GMT_MODIFIED BIGINT,
constraint FORUSER_PK
primary key (ID)
);