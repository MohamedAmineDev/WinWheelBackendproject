package com.packages.WinWheelBackendproject.interfaces;

public interface IEmailingManagement {
    boolean sendAnEmail(String toEmail, String subject, String body);
}
