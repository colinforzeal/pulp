package com.pulp.user.model.user_connection_model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public abstract class AbstractUserConnection<P> implements RemoteUser,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "accessToken")
	private String accessToken;
	@Column(name = "displayName")
	private String displayName;
	@Column(name = "expireTime")
	private Long expireTime;
	@Column(name = "imageUrl")
	private String imageUrl;
	@Column(name = "profileUrl")
	private String profileUrl;
	@Column(name = "rank")
	private int rank;
	@Column(name = "refreshToken")
	private String refreshToken;
	@Column(name = "secret")
	private String secret;


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public abstract String getProviderId();

	public abstract void setProviderId(String providerId);

	public abstract String getProviderUserId();

	public abstract void setProviderUserId(String providerUserId);

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public abstract String getUserId();

	public abstract void setUserId(String userId);

	protected abstract P getId();
}
