package com.jake.rsboard.config.oauth;

import com.jake.rsboard.config.auth.PrincipalDetails;
import com.jake.rsboard.config.oauth.provider.FacebookUserInfo;
import com.jake.rsboard.config.oauth.provider.GoogleUserInfo;
import com.jake.rsboard.config.oauth.provider.NaverUserInfo;
import com.jake.rsboard.config.oauth.provider.OAuth2UserInfo;
import com.jake.rsboard.domain.User;
import com.jake.rsboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public PrincipalOauth2UserService(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder,@Lazy UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    // 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어 진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println("userRequest: " + userRequest);
        System.out.println("getClientRegistration: " + userRequest.getClientRegistration()); // registrationId로 어떤 OAuth 로 로그인 했는지 확인 가능.
        System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());

        OAuth2User oauth2User = super.loadUser(userRequest);
        // 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> Code 를 리턴(OAuth-Client 라이브러리) -> AccessToken 요청
        // userRequest 정보 -> loadUser 함수 -> 구글로 부터 회원프로필을 받아줌
        System.out.println("getAttributes: " + oauth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
        } else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            System.out.println("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
        } else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            System.out.println("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
        } else {
            System.out.println("우리는 구글과 페이스북, 네이버만 지원해요 ㅎㅎㅎ");
        }

        String provider = oAuth2UserInfo.getProvider(); // google
        String providerId = oAuth2UserInfo.getProviderId();
        String userId = provider + "_" + providerId; // google_숫자
        String password = bCryptPasswordEncoder.encode("eng1234");
        String email = oAuth2UserInfo.getEmail();
        String role = "ROLE_USER";

        // 회원가입을 강제로 진행
        User userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            System.out.println("oAuth 로그인이 최초입니다.");
            userEntity = User.builder()
                    .userId(userId)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        } else {
            System.out.println("oAuth 로그인을 이미 진행한 적이 있습니다. 당신은 자동 회원가입 되어있습니다.");
        }

        return new PrincipalDetails(userEntity, oauth2User.getAttributes());
    }
}
