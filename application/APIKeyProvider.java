package application;

public interface APIKeyProvider {
    String getAPIKey();
}


class API implements APIKeyProvider{
	public String getAPIKey() {
        String apiKey = "x2KGyywxRlCvM6OIfM19UQ==";
		return apiKey;
	}
}