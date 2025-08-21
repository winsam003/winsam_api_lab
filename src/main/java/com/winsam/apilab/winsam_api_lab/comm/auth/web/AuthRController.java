package com.winsam.apilab.winsam_api_lab.comm.auth.web;

import com.winsam.apilab.winsam_api_lab.comm.auth.entity.Memb_infoVO;
import com.winsam.apilab.winsam_api_lab.comm.auth.entity.TokenDTO;
import com.winsam.apilab.winsam_api_lab.comm.auth.payload.AuthRControllerPayload;
import com.winsam.apilab.winsam_api_lab.comm.auth.service.AuthWebService;
import com.winsam.apilab.winsam_api_lab.comm.auth.token.TokenProvider;
//import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * ************************************************************************
 * File Name   : authRController
 * Author      : Dev-004
 * Date        : 2025-07-02
 * Description :
 * -------------------------- Modification Log ----------------------------
 * 버젼 :                  수정자 :
 * 날짜 :
 * 내용 :
 * ------------------------------------------------------------------------
 * 1.0 :  : 작성자 :
 * 내용 :
 * ************************************************************************
 */
@RestController
@RequestMapping("/auth")
//@Tag(name = "Auth API")
public class AuthRController {

    @Autowired
    AuthWebService authWebService;

    TokenProvider tokenProvider = new TokenProvider();

    @Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.secret}")
    private String googleClientSecret;
    @Value("${google.redirect-uri}")
    private String GOOGLE_REDIRECT_URI;

    @PostMapping("/token")
    public ResponseEntity<?> token(@ModelAttribute AuthRControllerPayload.AuthLoginRequest req){

        Map<String, String> returnToken = authWebService.getAuthJwtToken(req);

        return ResponseEntity.ok(returnToken);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody AuthRControllerPayload.TokenRefreshRequest req) {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setRefreshToken(req.getRefreshToken());

        if (!tokenProvider.validateToken(tokenDTO.getRefreshToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }


        Map<String, String> returnToken = authWebService.getNewAuthJwtToken(req);

        return ResponseEntity.ok(returnToken);
    }

    public Map<String, String> setCookie(HttpServletResponse response, Map<String, Object> userInfo) {

        /*
        * userInfo 에 저장할 정보
        * name // 구글
        * nickName // 디비
        * userRole // 디비
        * picture // 구글
        * email // 구글
        * email_verified // 구글
        * */

        Map<String, String> TokenInfo = new HashMap<>();

        // 1. 쿠키에 저장할 정보 set
        AuthRControllerPayload.AuthLoginRequest req =  new AuthRControllerPayload.AuthLoginRequest();
        req.setUserInfo(userInfo);

        // 2. 정보 JWT 로 변환
        Map<String, String> returnToken = authWebService.getAuthJwtToken(req);

        String accessToken = returnToken.get("accessToken");
        String refreshToken = returnToken.get("refreshToken");

        // 3. JWT 쿠키에 add
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 60); // 1시간 유효
        accessTokenCookie.setSecure(true); // Secure 추가

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 7일 유효
        refreshTokenCookie.setSecure(true); // Secure 추가

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        TokenInfo.put("accessToken", accessToken);
        TokenInfo.put("refreshToken", refreshToken);

        return TokenInfo;
    }

    @GetMapping("/google")
    public void loginUrlGoogle(HttpServletResponse response) throws IOException {
        String redirectUri = URLEncoder.encode(GOOGLE_REDIRECT_URI,"UTF-8");
//        String redirectUri = URLEncoder.encode("http://blog.winsam.xyz/auth/google/callback", "UTF-8");
//        String redirectUri = URLEncoder.encode("http://localhost:8080/auth/google/callback", "UTF-8");
//        String redirectUri = "http://blog.winsam.xyz/auth/google/callback";

        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=" + googleClientId + // 내 식별 아이디
                "&redirect_uri=" + redirectUri + // 데이터 콜백받을 url
                "&response_type=code" + // code는 Authorization Code Grant 방식 사용 (인증코드 방식)
                "&scope=openid%20email%20profile" + // 사용자 동의 받기
                "&access_type=offline"; // refresh token 받기

        response.sendRedirect(reqUrl);
    }

    @GetMapping("/google/callback")
    public ResponseEntity<?> googleCallback(@RequestParam("code") String code, HttpServletResponse response) {
        // 받은 인가 코드(code)를 가지고 토큰 요청 등 처리
        Map<String, Object> userInfo = authWebService.processGoogleOAuthCode(code);

        // 1. 구글에서 받은 유저정보를 디비에 있는지 확인 [패스]
        userInfo = this.hasNickName(userInfo);

        // 2. 있다면 JWT 토큰 생성 후 쿠키 저장 후 hasNickName 를 Y 로 프론트 응답 [패스]
        if(userInfo.get("nickName") != null && !"".equals(userInfo.get("nickName"))) {
            return this.successLogin(userInfo, response);
        } else {
        // 3. 없다면 hasNickName 를 N 으로 프론트로 응답
            return this.needNickName(userInfo, response);
        }

    }


    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // 즉시 만료시킴
        response.addCookie(cookie);

        Cookie refreshCookie = new Cookie("refreshToken", null);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(0);
        response.addCookie(refreshCookie);

        return ResponseEntity.ok("로그아웃 성공");
    }

    @PostMapping("/set-nickname")
    public ResponseEntity<?> setNickNameAndLogin(@RequestBody TokenDTO TokenDTO, HttpServletResponse response) {
        // 1. 디비에 닉네임과 함께 저장

        TokenDTO.setUserRole("GUEST");
        TokenDTO.setEmail_verified(true);
        TokenDTO.setSocial_root("Google");

        authWebService.setMemberInfo(TokenDTO);

        // 2. JWT 발급, 쿠키 저장
        Map<String, Object> userInfo = new HashMap<>();

        userInfo.put("name", TokenDTO.getUserName());
        userInfo.put("nickName", TokenDTO.getUserNickName());
        userInfo.put("picture", TokenDTO.getPicture());
        userInfo.put("email", TokenDTO.getUserEmail());
        userInfo.put("userRole", TokenDTO.getUserRole());
        userInfo.put("email_verified", TokenDTO.isEmail_verified());

        return successLogin(userInfo, response);
    }


    // 닉네임 포함 모든 정보가 존재할 시 쿠키에 JWT 저장
    public ResponseEntity<?> successLogin(Map<String, Object> userInfo, HttpServletResponse response) {

        Map<String, String> tokenInfo = this.setCookie(response, userInfo);

        String script = "<script>" +
                "window.opener.postMessage({" +
                "type: 'google-auth-token', " +
                "hasNickName: '" + "Y" + "', " +
                "accessToken: '" + tokenInfo.get("accessToken") + "', " +
                "}, '*');" +
                "window.close();" +
                "</script>";

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(script);
    }

    // 닉네임이 없을 경우 닉네임 필요 요청
    public ResponseEntity<?> needNickName(Map<String, Object> userInfo, HttpServletResponse response) {
        String script = "<script>" +
                "window.opener.postMessage({" +
                "type: 'google-auth-token', " +
                "hasNickName: '" + "N" + "', " +
                "userName: '" + userInfo.get("name") + "', " +
                "userEmail: '" + userInfo.get("email") + "', " +
                "picture: '" + userInfo.get("picture") + "', " +
                "}, '*');" +
                "window.close();" +
                "</script>";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8")
                .body(script);
    }

    public Map<String, Object> hasNickName(Map<String, Object> userInfo) {

        Memb_infoVO memb_infoVO = new Memb_infoVO();

        memb_infoVO.setUseremail(userInfo.get("email").toString());

        memb_infoVO = authWebService.getMemberInfo(memb_infoVO);

        String nickName = (memb_infoVO != null && memb_infoVO.getUsernickname() != null) ? memb_infoVO.getUsernickname() : "";
        String userRole = (memb_infoVO != null && memb_infoVO.getUserrole() != null) ? memb_infoVO.getUserrole() : "";

        userInfo.put("nickName", nickName);
        userInfo.put("userRole", userRole);

        return userInfo;
    }


}
