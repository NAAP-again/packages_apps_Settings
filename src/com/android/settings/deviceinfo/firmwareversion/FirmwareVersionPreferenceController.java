/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.deviceinfo.firmwareversion;

import static com.android.settings.deviceinfo.firmwareversion.NAAPVersionPreferenceController.NAAP_PROP;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.DeviceInfoUtils;

public class FirmwareVersionPreferenceController extends BasePreferenceController {

    public FirmwareVersionPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        String summary = Build.VERSION.RELEASE_OR_PREVIEW_DISPLAY;
        final String versionDef = mContext.getString(R.string.device_info_default);
        final String romVersion = SystemProperties.get(NAAP_PROP, versionDef);
        if (!romVersion.equals(versionDef)) {
            final String[] buildDate = romVersion.split("-", 0);
            summary += " - " + buildDate[buildDate.length - 2];
        }
        final String patch = DeviceInfoUtils.getSecurityPatch();
        if (patch != null) summary += "\n" + patch;
        return summary;
    }
}
