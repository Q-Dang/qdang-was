package com.qdang.application.user.port.in;

import com.qdang.application.user.domain.TokenCollection;

public interface RefreshTokenUseCase {

	TokenCollection refreshToken(String accessToken, String refreshToken);
}
