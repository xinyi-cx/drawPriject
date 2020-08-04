package com.tt.manage.service.impl;

import com.tt.manage.entity.CodeDrawRefVip;
import com.tt.manage.mapper.CodeDrawRefVipMapper;
import com.tt.manage.service.CodeDrawRefVipService;
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
public class CodeDrawRefVipServiceImpl implements CodeDrawRefVipService {

    @Autowired
    private CodeDrawRefVipMapper codeDrawRefVipMapper;

    @Override
    public List<CodeDrawRefVip> selectCodeDrawRefList() {
        return codeDrawRefVipMapper.selectCodeDrawRefList();
    }

    @Override
    public void updateByDrawDigit(CodeDrawRefVip CodeDrawRefVip) {
        codeDrawRefVipMapper.updateByDrawDigit(CodeDrawRefVip);
    }

    @Override
    public CodeDrawRefVip selectCodeDrawRef(CodeDrawRefVip CodeDrawRefVip) {
        return codeDrawRefVipMapper.selectByParam(CodeDrawRefVip);
    }

}
