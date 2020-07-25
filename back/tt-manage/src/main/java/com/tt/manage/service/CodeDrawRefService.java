package com.tt.manage.service;


import com.tt.manage.entity.CodeDrawRef;

import java.util.List;

/**
 * - 服务层
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
public interface CodeDrawRefService {
    List<CodeDrawRef> selectCodeDrawRefList();

    void updateByDrawDigit(CodeDrawRef codeDrawRef);

    CodeDrawRef selectCodeDrawRef(CodeDrawRef codeDrawRef);
}
