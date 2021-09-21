DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS disbursment;
create table users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(50),
    f_name varchar(50),
    l_name varchar(50),
    primary key(id)
 ); 
 
CREATE TABLE payment(
  id BIGINT NOT NULL AUTO_INCREMENT,
  amount DECIMAL(10,2) NOT NULL,
  estatus varchar(200) DEFAULT 'NEW',
  last_transaction DATE,  
  users_id BIGINT,
  FOREIGN KEY (users_id) REFERENCES users(id),
  PRIMARY KEY(id)
);

create table disbursment (
    id bigint NOT NULL AUTO_INCREMENT,
    amount decimal(19,2) NOT NULL,
    payment_id BIGINT,
    userss_id BIGINT,
    last_transaction DATE,
    FOREIGN KEY (userss_id) REFERENCES users(id),
    primary key (id)
   ); 
    
