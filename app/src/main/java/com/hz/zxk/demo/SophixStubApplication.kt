package com.hz.zxk.demo

import android.content.Context
import android.util.Log
import androidx.annotation.Keep
import com.taobao.sophix.PatchStatus
import com.taobao.sophix.SophixApplication
import com.taobao.sophix.SophixEntry
import com.taobao.sophix.SophixManager


class SophixStubApplication : SophixApplication() {
    private val TAG = "SophixStubApplication"

    @Keep
    @SophixEntry(value = App::class)
    object RealApplicationStub {}

    override fun onCreate() {
        super.onCreate()
        SophixManager.getInstance().queryAndLoadNewPatch()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        initSophix()
    }

    private fun initSophix() {
        var appVersion = "0.0.0"
        try {
            appVersion = packageManager.getPackageInfo(packageName, 0)
                .versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val instance = SophixManager.getInstance()
        instance.setContext(this)
            .setAppVersion(appVersion)
            .setSecretMetaData(
                "28224821",
                "85ec458a884f53e67b542ae70007639d",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDIRZLUqkd3n9pNFYx+e5lWN3Wa3QlSDGjz1+8Sbt2RR3xR2qfkVjzWwO47fkobAUp1Zm0dsLHcweMcgCYo68fZK5q1YJuyom6n4CITn9eevXt14x9XRkBONIV57fjg/OOofU5O5IPx3+OCgpDr4kUuLjLK2g8kDY++rqTuuONYae0+9C+Nf1qKvaRWPizkGI+YbMbwc13OZmqn5VV+kSvFSFWL/3XUHWys8+c6yjaJaemYUjPt2HTj/lAtspA6lbu5DtXCdeS5QhF/hvFTyx+s1rKayZ/+h9l3RW0a/lcfmtia6m8uaNcNF3/mMl/Mj9Oj8M53wKUGTdh5B3C9ivARAgMBAAECggEAfJQCvTVbFYejZPX1mixKev4usvGyPY6orp5xAddjDl8Yb1aDv58BY1lKGEn70QahiZv4XlKuoTMeknkIw8W/YFEmgJMi5yDxko3yxKBWoVEe/nsU4NY7ZCp5qRomCdZly0Z/MsYlaY0q2eFcQs9S12I3iEXSy0uAUk4THMh362OF80kXlHuEGECGsr8dd8NKYVzgDFpB3ZWym+cIL9vFJke/yMwBa2ct7qjTiY5XOSZDPie5di8up26EykrEPPpXIAghVBjWtozHU1sooUTS4QJwEYYaYnGm+uxc9e5DNj6mHuwWdRBjq/vJn52Y/DjOt4tNn90FScGiU76Z+hXaTQKBgQD5+Sg7OTvfptisaeNfHqPHdWXQd6VYupMAJZ2jYtoNMtw6NW7p7tQpAOWqwjQfH7xIC7hxdfSKLSMul8whBWHqc6JiSyZ554O4YR4RM3DruuNKsSOX/7ovICkx1gZnhligOLjBu5AYSRtG9FHP0IbmzHg3NYh2ZQnRINVnGIDWgwKBgQDNGag+RIhZ15nyTr6Vyh+ZJ7XNg9YJmYiZjUPrz+VUs6K7hus1tkX/MbY+0TRh54yvlNvF6PzpHycQBdFYHo+UmHAtyUuk+N5bIJ32lfW9ugQ6eugsujx/WlX5GxapO5FtvnI5GXUqRRDKYkSUeCImkVcp3f/cDGiYHjkFtzN62wKBgQDO9NS1K66BfU54eHZxwxlPwseXr+cqLdYU0g/QPMBRGyGv45s/fKcUh5SeKokil5C3iVScQ09BsxsksTKkyCMIC9KOVK+3T9EDLwzIqvE7iZAyv7O7XVLVP2g7xXmp7lWj2TJF2zbylFxMA21Ug82cef6xntWrR/23sdpcr7HDZwKBgDRVAZqxveC+pGcxNGvf4OQhP8AqjWhxpmsXomb+6tML6s/Sgx13XrDYrFmm46i/fiQm5VTAOBf7fOoXsey8D4KoyqbEH+r+HS4/Sk7qwIMifYW2aptPAbS1Bg7DnKqdqmPIHjj3h++7I6egH0OKy5vvpb/nByvMdrupqn8M2bPVAoGAC0J2ODkowGtTG0L1QSnm3xglsXb57xfjctcLydBdzF/nf1KG2FVL4X+4JUbS2CjJkCKd/wD4tNLTqDzmeYSl4yLftxmYpjlbsG8TTphHq5uTw4ywsJjFpJHIe1eG5WDN8iwK2wZox6bFi8kMSxrHca43H/gRMUIVMtgNvreuztI="
            )
            .setEnableDebug(true)
            .setEnableFullLog()
            .setPatchLoadStatusStub { mode: Int, code: Int, info: String, handlePatchVersion: Int ->
                if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                    Log.i(TAG, "sophix load patch success!")
                } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                    Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                }
            }.initialize()
    }
}