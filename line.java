public class LineTokenResponse {
    private String accessToken;

    private String tokenType;

    private Integer expiresIn;

    private String idToken;

    private String refreshToken;

    private String scope;
}

public LineTokenResponse lineToken(String pCode, String pState) {
	if (ObjectUtils.isEmpty(request)
			|| StringUtils.isEmpty(pCode)
			|| StringUtils.isEmpty(pState)) {
		return Optional.empty();
	}

	String url = "https://api.line.me/oauth2/v2.1/token";
	HttpPost httpPost = new HttpPost(url);

	httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

	StringBuilder builder = new StringBuilder();
	builder.append("grant_type=authorization_code");
	builder.append("&");
	builder.append("client_id={your_client_id}");
	builder.append("&");
	builder.append("client_secret={your_secret}");
	builder.append("&");
	builder.append("redirect_uri={your_redirect_url}"); //URLENCODE
	builder.append("&");
	builder.append("code=").append(pCode);

	try {
		httpPost.setEntity(new StringEntity(builder.toString(),  "UTF8"));

		HttpResponse httpResponse = httpClient.execute(httpPost);
		String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			LineTokenResponse response = JsonMapper.convertJsonToObject(json, LineTokenResponse.class);
			return response;
		}
	} catch (Exception e) {
		// Write error message
	}
	return null;
}
