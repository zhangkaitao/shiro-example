package com.github.zhangkaitao.shiro.chapter16.web.controller;

import com.github.zhangkaitao.shiro.chapter16.service.CodeService;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-16
 * <p>Version: 1.0
 */
@RestController
public class AuthorizeController {

    @Autowired
    private CodeService codeService;

    @RequestMapping("/authorize")
    public HttpEntity authorize(HttpServletRequest request)
            throws URISyntaxException, OAuthSystemException {

        String username = "1";

        try {
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

            //build response according to response_type
            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);

            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                    OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);

            if (responseType.equals(ResponseType.CODE.toString())) {
                final String authorizationCode = oauthIssuerImpl.authorizationCode();
                codeService.addAuthCode(authorizationCode, username);
                builder.setCode(authorizationCode);
            }
            if (responseType.equals(ResponseType.TOKEN.toString())) {
                final String accessToken = oauthIssuerImpl.accessToken();
                codeService.addAccessToken(accessToken, username);

                builder.setAccessToken(accessToken);
                builder.setExpiresIn(codeService.getExpireIn());
            }

            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
            URI url = new URI(response.getLocationUri());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(url);
            ResponseEntity entity = new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
            return entity;
        } catch (OAuthProblemException e) {

            String redirectUri = e.getRedirectUri();

            if (OAuthUtils.isEmpty(redirectUri)) {
                ResponseEntity entity = new ResponseEntity("OAuth callback url needs to be provided by client!!!", HttpStatus.NOT_FOUND);
                return entity;
            }
            final OAuthResponse response =
                    OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND)
                            .error(e).location(redirectUri).buildQueryMessage();
            final URI location = new URI(response.getLocationUri());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            ResponseEntity entity = new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
            return entity;
        }
    }
}