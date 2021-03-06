package com.mystore.petstore.mapper;

import com.mystore.petstore.entity.Account;


/**
 * 用户账户相关CRUD接口
 * @author siming
 *
 */
public interface AccountMapper {

  Account getAccountByUsername(String username);

  Account getAccountByUsernameAndPassword(String username, String password);

  void insertAccount(Account account);
  
  void insertProfile(Account account);
  
  void insertSignon(Account account);

  void updateAccount(Account account);

  void updateProfile(Account account);

  void updateSignon(Account account);

}
