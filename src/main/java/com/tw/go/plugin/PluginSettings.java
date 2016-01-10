package com.tw.go.plugin;

public class PluginSettings {
    private String serverBaseURL;
    private String endPoint;
    private String username;
    private String password;
    private String oauthToken;
    private String reviewField = "Code-Review";

    public PluginSettings() {
    }

    public PluginSettings(String serverBaseURL, String endPoint, String username, String password, String oauthToken, String reviewField) {
        this.serverBaseURL = serverBaseURL;
        this.endPoint = endPoint;
        this.username = username;
        this.password = password;
        this.oauthToken = oauthToken;
        this.reviewField = reviewField;
    }

    public String getServerBaseURL() {
        return serverBaseURL;
    }

    public void setServerBaseURL(String serverBaseURL) {
        this.serverBaseURL = serverBaseURL;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getReviewField() {
        return reviewField;
    }

    public void setReviewField(String reviewField) {
        this.reviewField = reviewField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PluginSettings that = (PluginSettings) o;

        if (endPoint != null ? !endPoint.equals(that.endPoint) : that.endPoint != null) return false;
        if (oauthToken != null ? !oauthToken.equals(that.oauthToken) : that.oauthToken != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (serverBaseURL != null ? !serverBaseURL.equals(that.serverBaseURL) : that.serverBaseURL != null)
            return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (reviewField != null ? !reviewField.equals(that.reviewField) : that.reviewField != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverBaseURL != null ? serverBaseURL.hashCode() : 0;
        result = 31 * result + (endPoint != null ? endPoint.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (oauthToken != null ? oauthToken.hashCode() : 0);
        result = 31 * result + (reviewField != null ? reviewField.hashCode() : 0);
        return result;
    }
}
