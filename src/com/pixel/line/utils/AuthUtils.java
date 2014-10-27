package com.pixel.line.utils;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow.Builder;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;

public class AuthUtils {

	private final String clientId = "814797378906-n2ltnat28o4beptaajm2o92vp297lvle.apps.googleusercontent.com";
	private final String clientSecret = "1IVlDfTDcPG_KSxJCJYf49hJ";

	private final HttpTransport httpTransport = new UrlFetchTransport();
	private final JacksonFactory jsonFactory = new JacksonFactory();

	public static String getRedirectUri(HttpServletRequest request) throws ServletException, IOException {
		GenericUrl url = new GenericUrl(request.getRequestURI().toString());
		url.setRawPath("/oauth2callback");
		return url.build();
	}

	public AuthorizationCodeFlow initializeFlow(Iterable<String> scopeList) throws IOException {
		Builder flowBuilder = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientId, clientSecret,
				(Collection<String>) scopeList);
		final DataStore<StoredCredential> dataStore = AppEngineDataStoreFactory.getDefaultInstance().getDataStore("credentialDS");
		flowBuilder.setCredentialDataStore(dataStore);
		return flowBuilder.build();
	}

}
