package com.joework.authserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    /*
     this method is used to configure the client credentials like clientId,
     secret( secret is exactly like passwords) and scope of that client
     NOTE: Clients are any httpclient like mobile, browser, IOT, etc
    */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//        clients.inMemory()
//                .withClient("client1")
//                .secret("pass123")
//                .scopes("read")
//                .authorizedGrantTypes("password"); // this will use the grantType => password
        clients.inMemory()
                .withClient("client1")
                .secret("pass123")
                .scopes("read")
                .authorizedGrantTypes("authorization_code")
                .redirectUris("http://localhost:9090"); // there is the client URI that the server should redirect to after success
    }


    // this method is used to attach the authentication manager to the authServerConfig
    // as the authentication manager is the link between the authServer and the UserDetailsService
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

}
