package com.ljm.server;

import com.ljm.domain.Role;
import com.ljm.domain.User;

import java.util.Map;

public interface ILoginService {

    User addUser(Map<String, Object> map);

    Role addRole(Map<String, Object> map);

    User findByName(String name);
}
