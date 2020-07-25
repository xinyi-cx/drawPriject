package com.tt.manage.service.impl;

import com.tt.manage.entity.CodeDrawRef;
import com.tt.manage.mapper.CodeDrawRefMapper;
import com.tt.manage.service.CodeDrawRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * - 服务层实现
 *
 * @author mohanwen
 * @date 2020-01-02 18:24:39
 */
@Service
public class CodeDrawRefServiceImpl implements CodeDrawRefService {

    @Autowired
    private CodeDrawRefMapper codeDrawRefMapper;

    @Override
    public List<CodeDrawRef> selectCodeDrawRefList() {
        return codeDrawRefMapper.selectCodeDrawRefList();
    }

    @Override
    public void updateByDrawDigit(CodeDrawRef codeDrawRef) {
        codeDrawRefMapper.updateByDrawDigit(codeDrawRef);
    }

    @Override
    public CodeDrawRef selectCodeDrawRef(CodeDrawRef codeDrawRef) {
        return codeDrawRefMapper.selectByParam(codeDrawRef);
    }

}
