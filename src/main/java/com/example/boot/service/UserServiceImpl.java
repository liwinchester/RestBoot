package com.example.boot.service;


import com.example.boot.dao.UserDao;
import com.example.boot.model.Role;
import com.example.boot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

   private final UserDao userDao;

   UserServiceImpl(UserDao userDao){
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public User addUser(User user, String[] roleIds) {
      Set<Role> rolesSet = new HashSet<>();
      for (String id: roleIds) {
         rolesSet.add(userDao.getRoleById(Long.parseLong(id)));
      }
      user.setRoles(rolesSet);
      userDao.addUser(user);
      return user;
   }

   @Transactional
   @Override
   public User updateUser(User user, String[] roleIds) {
      Set<Role> rolesSet = new HashSet<>();
      for (String id: roleIds) {
         rolesSet.add(userDao.getRoleById(Long.parseLong(id)));
      }
      user.setRoles(rolesSet);
      userDao.updateUser(user);
      return user;
   }

   @Transactional
   @Override
   public void removeUser(long id) {
      userDao.removeUser(id);
   }

   @Transactional
   @Override
   public User getUserById(long id) {
      return userDao.getUserById(id);
   }

   @Transactional
   @Override
   public List<User> listUser() {
      return userDao.listUser();
   }

   @Transactional
   @Override
   public List<Role> listRoles() {
      return userDao.listRoles();
   }

   @Transactional
   @Override
   public User getUserByName(String userName) {
      return userDao.getUserByName(userName);
   }

   @Transactional
   @Override
   public Role getRoleById(long id) {
      return userDao.getRoleById(id);
   }
}
