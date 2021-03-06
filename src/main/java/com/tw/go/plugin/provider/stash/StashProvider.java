package com.tw.go.plugin.provider.stash;

import com.google.gson.GsonBuilder;
import com.tw.go.plugin.PluginSettings;
import com.tw.go.plugin.provider.Provider;
import com.tw.go.plugin.util.AuthenticationType;
import com.tw.go.plugin.util.HTTPUtils;
import com.tw.go.plugin.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class StashProvider implements Provider {
    public static final String PLUGIN_ID = "stash.pr.status";
    public static final String STASH_PR_POLLER_PLUGIN_ID = "stash.pr";

    public static final String IN_PROGRESS_STATE = "INPROGRESS";
    public static final String SUCCESSFUL_STATE = "SUCCESSFUL";
    public static final String FAILED_STATE = "FAILED";

    @Override
    public String pluginId() {
        return PLUGIN_ID;
    }

    @Override
    public String pollerPluginId() {
        return STASH_PR_POLLER_PLUGIN_ID;
    }

    @Override
    public void updateStatus(String url, PluginSettings pluginSettings, String branch, String revision, String pipelineStage,
                             String result, String trackbackURL) throws Exception {
        String endPointToUse = pluginSettings.getEndPoint();
        String usernameToUse = pluginSettings.getUsername();
        String passwordToUse = pluginSettings.getPassword();

        if (StringUtils.isEmpty(endPointToUse)) {
            endPointToUse = System.getProperty("go.plugin.build.status.stash.endpoint");
        }
        if (StringUtils.isEmpty(usernameToUse)) {
            usernameToUse = System.getProperty("go.plugin.build.status.stash.username");
        }
        if (StringUtils.isEmpty(passwordToUse)) {
            passwordToUse = System.getProperty("go.plugin.build.status.stash.password");
        }

        String updateURL = String.format("%s/rest/build-status/1.0/commits/%s", endPointToUse, revision);

        Map<String, String> params = new HashMap<String, String>();
        params.put("state", getState(result));
        params.put("key", pipelineStage);
        params.put("name", pipelineStage);
        params.put("url", trackbackURL);
        params.put("description", "");
        String requestBody = new GsonBuilder().create().toJson(params);

        HTTPUtils.postRequest(updateURL, AuthenticationType.BASIC, usernameToUse, passwordToUse, requestBody);
    }

    String getState(String result) {
        result = result == null ? "" : result;
        String state = IN_PROGRESS_STATE;
        if (result.equalsIgnoreCase("Passed")) {
            state = SUCCESSFUL_STATE;
        } else if (result.equalsIgnoreCase("Failed")) {
            state = FAILED_STATE;
        } else if (result.equalsIgnoreCase("Cancelled")) {
            state = FAILED_STATE;
        }
        return state;
    }
}
