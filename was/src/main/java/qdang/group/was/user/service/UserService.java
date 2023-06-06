package qdang.group.was.user.service;


import qdang.group.was.user.service.dto.request.LoginInfo;
import qdang.group.was.user.service.dto.request.SignUpInfo;

public interface UserService {

    void signUp(SignUpInfo request);
    void login(LoginInfo request);

    boolean isPresentUserName(String username);
}
