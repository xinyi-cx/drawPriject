package com.tt.prize.service;

import com.tt.prize.Exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public interface CustomUserService {
    boolean uploadExcelFile(String fileName, MultipartFile file) throws MyException;

    String downloadExcelFile(Date startTime, Date endTime, HttpServletRequest request, HttpServletResponse response) throws MyException;
}
