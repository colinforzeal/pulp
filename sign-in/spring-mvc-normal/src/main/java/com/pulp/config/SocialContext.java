package com.pulp.config;

//import com.pulp.user.signin.SimpleSignInAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.security.FacebookAuthenticationService;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.security.provider.SocialAuthenticationService;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.sql.DataSource;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@Configuration
@EnableSocial
public class SocialContext implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private Environment environment;


    /**
     * Configures the connection factories for Facebook, Twitter, Vk.
     * @param cfConfig
     * @param env
     */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new TwitterConnectionFactory(
                env.getProperty("twitter.consumer.key"),
                env.getProperty("twitter.consumer.secret")
        ));
//        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
//                env.getProperty("facebook.app.id"),
//                env.getProperty("facebook.app.secret")
//        ));
        FacebookAuthenticationService facebook = new FacebookAuthenticationService(
                environment.getProperty("facebook.app.id"),
                environment.getProperty("facebook.app.secret")
        );
        facebook.setDefaultScope("email");
        cfConfig.addConnectionFactory(facebook.getConnectionFactory());

//        cfConfig.addConnectionFactory(new VKontakteConnectionFactory(
//                env.getProperty("vk.consumer.key"),
//                env.getProperty("vk.consumer.secret")
//        ));
    }

    /**
     * The UserIdSource determines the account ID of the user. The example application
     * uses the username as the account ID.
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                Encryptors.noOpText()
        );
    }

    /**
     * This bean manages the connection flow between the account provider and
     * the example application.
     */
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
