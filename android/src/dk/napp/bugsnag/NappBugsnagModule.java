/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package dk.napp.bugsnag;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.KrollDict;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;

import com.bugsnag.android.*;
import com.bugsnag.android.Bugsnag;

import java.lang.Exception;
import java.util.HashMap;

@Kroll.module(name="NappBugsnag", id="dk.napp.bugsnag")
public class NappBugsnagModule extends KrollModule
{

	// Standard Debugging variables
	private static final String LCAT = "NappBugsnagModule";
	private static final boolean DBG = TiConfig.LOGD;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public NappBugsnagModule()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		Log.d(LCAT, "inside onAppCreate");
		// put module init code that needs to run when the application is created
	}

	// Public API

	@Kroll.method
	public void startBugsnagWithConfiguration(HashMap props)
	{
		KrollDict propsDict = new KrollDict(props);

		// validation
		if(!propsDict.containsKey("apikey")){
			Log.e(LCAT, "NO API KEY PROVIDED");
			return;
		}

		String apikey = propsDict.getString("apikey");

		// start Bugsnag
		Bugsnag.init(TiApplication.getInstance().getApplicationContext(), apikey);

		// Notify Release Stages
		if(propsDict.containsKey("notifyReleaseStages")){
			String[] notifyReleaseStages = propsDict.getStringArray("notifyReleaseStages");
			Bugsnag.setNotifyReleaseStages(notifyReleaseStages);
		}
	}

	@Kroll.method
	public void notify(HashMap props)
	{
		KrollDict propsDict = new KrollDict(props);
		String name = propsDict.getString("name");
		String reason = propsDict.getString("reason");

		MetaData metaData = new MetaData();
		metaData.addToTab("TiCustomException", "reason", reason);
		metaData.addToTab("TiCustomException", "name", name);

		Log.d(LCAT, "notify name: " + name + " reason: " + reason);

		Bugsnag.notify(new Exception(name), metaData);
	}

	@Kroll.method
	public void addAttribute(HashMap props)
	{
		KrollDict propsDict = new KrollDict(props);

		String tab = propsDict.getString("tabName");
		String key = propsDict.getString("attribute");

		Object value = propsDict.get("data"); // @TODO: Check this ???

		Bugsnag.addToTab(tab, key, value);
	}

	@Kroll.method
	public void clearTabWithName(String value)
	{
		Bugsnag.clearTab(value);
	}

	@Kroll.method
	public void setContext(String value)
	{
		Bugsnag.setContext(value);
	}
}
