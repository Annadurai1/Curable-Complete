package com.curable.service;

import java.util.Arrays;
import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.curable.util.KeyCloackUtil;

@Service
public class KeycloakService {

	@Value("${keycloak-admin.realm}")
	private String ADMIN_REALM;

	@Value("${keycloak-admin.username}")
	private String USERNAME;

	@Value("${keycloak-admin.password}")
	private String PASSWORD;

	@Value("${keycloak-admin.clientId}")
	private String CLIENT_ID;

	@Value("${keycloak-admin.userrealm}")
	private String USER_REALM;

	@Value("${keycloak-admin.serverUrl}")
	private String url;

	private Keycloak getInstance() {
		return KeycloakBuilder.builder().serverUrl(url).realm(ADMIN_REALM).username(USERNAME).password(PASSWORD)
				.clientId(CLIENT_ID).build();
	}

	public void updatePassword(String userName, String password) {

		CredentialRepresentation credential = new CredentialRepresentation();
		credential.setType(CredentialRepresentation.PASSWORD);
		credential.setValue(password);
		credential.setTemporary(false);
		getInstance().realm(USER_REALM).users().get(userName).resetPassword(credential);
	}

	public void madeUserInActive(String userId, Boolean decision) {

		UserResource userResource = getInstance().realm(USER_REALM).users().get(userId);
		UserRepresentation user = userResource.toRepresentation();
		user.setEnabled(decision);
		userResource.update(user);
	}

	public String createKeyCloakUser(String userName, String password, String email) throws Exception {
		try {
			String result = null;
			if (userName != null) {
				if (checkKeyCloakUserExistenceByUserName(email)) {
					throw new Exception("User Already exists");
				}
				CredentialRepresentation credential = new CredentialRepresentation();
				credential.setType(CredentialRepresentation.PASSWORD);
				credential.setValue("cure@123");
				credential.setTemporary(false);
				UserRepresentation user = new UserRepresentation();
				user.setUsername(email);
				user.setEmailVerified(true);
				user.setEmail(email);
				user.setFirstName(userName);
				user.setLastName(userName);
				user.setCredentials(Arrays.asList(credential));
				user.setEnabled(true);
				result = KeyCloackUtil.getCreatedId(getInstance().realm(USER_REALM).users().create(user));
			}
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean checkKeyCloakUserExistenceByUserName(String userName) {
		try {
			List<UserRepresentation> result = getInstance().realm(USER_REALM).users().search(userName);

			if (result != null && !result.isEmpty()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw e;
		}
	}
}
