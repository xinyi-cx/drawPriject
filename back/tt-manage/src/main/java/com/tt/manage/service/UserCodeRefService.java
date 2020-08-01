package com.tt.manage.service;


import com.tt.manage.entity.UserCodeRef;

import java.io.InputStream;
import java.util.List;

/**
 * - 服务层
 *
 * @author mohanwen
 * @date 2020-07-18 18:24:39
 */
public interface UserCodeRefService {

    List<UserCodeRef> addUserCodeRefsByPath(InputStream is);

    List<UserCodeRef> selectUserCodeRefList(UserCodeRef ref);

}
