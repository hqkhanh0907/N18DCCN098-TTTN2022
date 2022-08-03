package com.example.demo.service.implement;

import com.example.demo.model.VerificationToken;
import com.example.demo.repository.VerificationTokenRepository;
import com.example.demo.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public void deleteUserTokens(int userId) {
        List<VerificationToken> verificationTokens = verificationTokenRepository.findAll();
        verificationTokens.forEach(verificationToken -> {
            if (verificationToken.getAccount().getId() == userId) {
                verificationTokenRepository.delete(verificationToken);
            }
        });
    }
}
