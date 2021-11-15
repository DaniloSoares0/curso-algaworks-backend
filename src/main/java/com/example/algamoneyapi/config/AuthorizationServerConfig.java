package com.example.algamoneyapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter   {

	//CLASSE RESPONSAVEL POR GERAR O TOKEN

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
				
		clients.inMemory() //jdbc
		//EM MEMORIA NÃO É BANCO DE DADOS
		//withClient = CLIENT/APLICAÇÃO/SERVIÇO QUE VAI PEDIR TOKEN, NO CASO EXEMPLO SERIA UMA APLICAÇÃO ANGULAR. AS INFORMAÇÕES
		//TEM QUE SER AS MESMAS DO POSTMAN. NO POSTMAN SERÁ CONFIGURADO UM POST COM ESTA URL localhost:8080/oauth/token
		//AUTHORIZATION TYPE BASIC AUTH USERNAME angular PASSWORD linha debaixo secret. Será então gerado um token
		.withClient("angular")
		.secret(passwordEncoder.encode("@ngul@r0"))
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(1800)
		.refreshTokenValiditySeconds(3600 * 24);
	/*	.and()
		.withClient("mobile")
		.secret(passwordEncoder.encode("m0b1l30")) // m0b1l30
		.scopes("read")
		.authorizedGrantTypes("password")
		.accessTokenValiditySeconds(1800);*/

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.authenticationManager(authenticationManager)
			.accessTokenConverter(accessTokenConverter())
			.tokenStore(tokenStore())
		    .userDetailsService(userDetailsService)
			.reuseRefreshTokens(false);
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();

		accessTokenConverter.setSigningKey("algaworks");

		return accessTokenConverter;
	}


	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

}