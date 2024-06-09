package com.yashdevs.optiTracker.dto;

public record LoginResponseDto (String jwtToken, String expireTime, Long userId) {}
