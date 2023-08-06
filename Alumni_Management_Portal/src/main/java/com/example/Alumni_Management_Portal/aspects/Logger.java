package com.example.Alumni_Management_Portal.aspects;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.dto.LoginRequestDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Aspect
public class Logger {

    FileWriter writer;
    LocalDateTime localDateTime;

    @Before("execution(* com.example.Alumni_Management_Portal.services.implementation.UserServiceImpl.authenticateUser(..))")
    public void before(JoinPoint joinPoint) throws IOException {
        String methodName = joinPoint.getSignature().getName();
        localDateTime = LocalDateTime.now();

        switch (methodName) {
            case "authenticateUser":
                LoginRequestDto loginRequest = (LoginRequestDto) joinPoint.getArgs()[0];
                String userActivity = "[ " + localDateTime + " ] => Login to account with email: " + loginRequest.getEmail();
                String fileName = localDateTime + loginRequest.getEmail() + ".txt";
                createFileToLogInto(fileName);
                writeLogIntoTheFile(userActivity);
                break;
        }
    }

//        System.out.println("After: " + joinPoint.getSignature().getDeclaringTypeName() + " : " + joinPoint.getSignature().getName() + " " + joinPoint.getArgs());
    @After("execution(* com.example.Alumni_Management_Portal.services.implementation.UserServiceImpl.authenticateUser(..))")
    public void after(JoinPoint joinPoint) throws IOException {
        String methodName = joinPoint.getSignature().getName();
        localDateTime = LocalDateTime.now();
        switch (methodName) {
            case "authenticateUser":
                String userActivity = "[ " + localDateTime + " ] => Login operation finished";
                writeLogIntoTheFile(userActivity);
                break;
        }
        JobDto jobDto = (JobDto) joinPoint.getArgs()[0];
        System.out.println(jobDto.getCompany());
        System.out.println(jobDto.getDescription());
    }

    private void createFileToLogInto(String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }

    private void writeLogIntoTheFile(String logActivity) throws IOException {
        writer.write(logActivity);
    }

    private void closeLogFile() throws IOException {
        writer.close();
    }
}
