package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirl.core.dto.UserDto;
import ru.itgirl.core.entity.User;
import ru.itgirl.core.repository.UserRepository;
import ru.itgirl.core.service.UserCoreService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCoreServiceImpl implements UserCoreService {
    private final UserRepository userRepository;



}
