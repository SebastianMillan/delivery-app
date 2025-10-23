package com.deliverysl.luxurydelivery.security.jwt.refresh;

import com.deliverysl.luxurydelivery.security.jwt.access.JwtProvider;
import com.deliverysl.luxurydelivery.user.dto.JwtUserResponse;
import com.deliverysl.luxurydelivery.user.mapper.UserMapper;
import com.deliverysl.luxurydelivery.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    @Value("${jwt.refresh.duration}")
    private int durationInMinutes;

    public JwtUserResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String refreshToken = refreshTokenRequest.refreshToken();
        return findByToken(refreshToken)
                .map(this::verify)
                .map(RefreshToken::getUser)
                .map(user -> {
                    deleteByUser(user);
                    refreshTokenRepository.flush();
                    return createRefreshAndResponse(user);
                }).orElseThrow(() -> new RefreshTokenException("Refresh token not found"));
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken newRefreshToken = new RefreshToken();
        newRefreshToken.setUser(user);
        newRefreshToken.setToken(UUID.randomUUID().toString());
        newRefreshToken.setExpiryDate(Instant.now().plusSeconds(durationInMinutes * 60));
        newRefreshToken.setCreatedAt(Instant.now());
        return refreshTokenRepository.save(newRefreshToken);
    }

    public JwtUserResponse createRefreshAndResponse(User user){
        String token = jwtProvider.generateToken(user);
        return userMapper.toJwtResponseDto(user,token, createRefreshToken(user).getToken());
    }

    public RefreshToken verify(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Expired refresh token: " + refreshToken.getToken() + ". Please, login again");
        }
        return refreshToken;
    }

    public int deleteByUser(User user) {
        return refreshTokenRepository.deleteByUser(user);
    }
}
