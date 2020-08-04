package com.tt.manage.service;


import com.tt.manage.entity.CodeDrawRefVip;

import java.util.List;

/**
 * - 服务层
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
public interface CodeDrawRefVipService {
    List<CodeDrawRefVip> selectCodeDrawRefList();

    void updateByDrawDigit(CodeDrawRefVip codeDrawRef);

    CodeDrawRefVip selectCodeDrawRef(CodeDrawRefVip codeDrawRef);
}
