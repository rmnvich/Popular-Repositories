package com.splunk.test.core.network.token

import com.splunk.test.core.network.BuildConfig
import javax.inject.Inject

class TokenProviderImpl @Inject constructor() : TokenProvider {

    // ⚠️ WARNING: Never store sensitive tokens like this in a production environment.
    // This is only hardcoded to simplify the review process.
    // In a real application, consider using GitHub Actions Secrets or remote configuration.
    override fun getToken(): String = BuildConfig.GITHUB_TOKEN
}