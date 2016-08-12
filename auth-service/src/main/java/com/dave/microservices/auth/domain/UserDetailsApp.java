package com.dave.microservices.auth.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;


public class UserDetailsApp extends User implements UserDetails{

		/**
		 * 
		 */
		private static final long serialVersionUID = 7394467688467457485L;

		public UserDetailsApp(User user){
			super(user);
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			String roles=StringUtils.collectionToCommaDelimitedString(getRoles());			
			return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		}

		@Override
		public String getUsername() {
			return super.getUserName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}
