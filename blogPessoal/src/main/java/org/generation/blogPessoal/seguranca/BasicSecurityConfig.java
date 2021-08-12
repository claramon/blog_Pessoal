package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired //injeção de dependência
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService);
		auth.inMemoryAuthentication()
        .withUser("root")
        .password(passwordEncoder().encode("root"))
        .authorities("ROLE_USER");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{ //qdo o metodo for iniciado, instanciará o objeto http security  
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll() // autoriza o http, e libera os endpoints (caminhos dentro do controller) para que o cliente possa acessar sem a chave token, aqui para logar
		.antMatchers("/usuarios/cadastrar").permitAll() // e aqui, para cadastrar
		.anyRequest().authenticated()
		.and().httpBasic() // utiliza o padrão basic para gerar o token
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //indica o tipo de sessão a ser utilizada, e cria uma política na API, de não guardar a sessão
		.and().cors() 
		.and().csrf().disable(); // desabilitar o csrf (padrão)
	}
	
	
	
}
