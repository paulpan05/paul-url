package paulurl.shortener.dao;

import paulurl.shortener.model.User;

public interface UserDao {
  int insertUser(String username, String email, String password, String first_name, String last_name);
  User retrieveUser(String username);
  int changeUsername();
  int changeEmail();
  int changePassword();
  int changeFirstname();
  int changeLastname();
}
