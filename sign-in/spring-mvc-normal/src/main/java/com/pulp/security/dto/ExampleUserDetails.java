package com.pulp.security.dto;

import com.pulp.user.model.Role;
import com.pulp.user.model.Site;
import com.pulp.user.model.SocialMediaService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.concurrent.BackgroundInitializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.*;

public class ExampleUserDetails extends SocialUser {

    private Long id;

    private String firstName;

    private String lastName;

    private Role role;

    private SocialMediaService socialSignInProvider;

    private List<Site> sites;

    public ExampleUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    public SocialMediaService getSocialSignInProvider() {
        return socialSignInProvider;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", getUsername())
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("role", role)
                .append("socialSignInProvider", socialSignInProvider)
                .toString();
    }

    public List<Site> getSites() {
        return sites;
    }

    public static class Builder {

        private Long id;

        private String username;

        private String firstName;

        private String lastName;

        private List<Site> sites;

        private Role role;

        private SocialMediaService socialSignInProvider;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder sites(List<Site> sites){
            this.sites=sites;
            return this;
        }
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.authorities.add(authority);

            return this;
        }

        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }


        public ExampleUserDetails build() {
            ExampleUserDetails user = new ExampleUserDetails(username, id.toString(), authorities);

            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.socialSignInProvider = socialSignInProvider;
            user.sites=sites;
            return user;
        }


    }
}
