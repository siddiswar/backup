package com.advertise.utils;

/**
 * @author rahul
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.PointerByReference;

public class WindowUtils {

	private static final int MAX_TITLE_LENGTH = 1024;

	public static String getActiveWindowTitle() throws Exception {
		char[] buffer = new char[MAX_TITLE_LENGTH * 2];
		Thread.sleep(5000);
		User32DLL.GetWindowTextW(User32DLL.GetForegroundWindow(), buffer, MAX_TITLE_LENGTH);
		// System.out.println(User32DLL.GetForegroundWindow().nativeType().getName());
		return Native.toString(buffer);
	}

	static class User32DLL {
		// for windows
		// static { Native.register(System.getProperty("user32")); }
		static {
			Native.register(System.getProperty("dll_name"));
		}

		// for mac
		// static { Native.register("libm.dylib"); }
		public static native int GetWindowThreadProcessId(HWND hWnd, PointerByReference pref);

		public static native HWND GetForegroundWindow();

		public static native int GetWindowTextW(HWND hWnd, char[] lpString, int nMaxCount);
	}

	public static String getActiveWindowTitleUsingAutoit() throws IOException {
		Process p = Runtime.getRuntime().exec("bin//currentactivewindowtitle.exe");

		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

		String line;
		String currentActiveWindowTitleAutoIT = null;
		while ((line = input.readLine()) != null) {
			currentActiveWindowTitleAutoIT = line;
		}
		System.out.println("AutoIT :" + currentActiveWindowTitleAutoIT);
		return currentActiveWindowTitleAutoIT;
	}

	public static int getBrowserWindowCountUsingAutoIT(String browserName) throws IOException {
		int browserWindowCount = 0;

		Process p = Runtime.getRuntime().exec("bin//browserwindowcount.exe");
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		String allBrowserCountString = null;

		while ((line = input.readLine()) != null) {

			allBrowserCountString = line;
		}
		System.out.println("AutoIT :" + allBrowserCountString);
		
		String[] browserNameWithCount = allBrowserCountString.split(",");

		for (int i = 0; i < browserNameWithCount.length; i++) {

			String browserNameFromAutoit = browserNameWithCount[i].split(":")[0];

			String browserWindowCountFromAutoit = browserNameWithCount[i].split(":")[1];

			if (browserNameFromAutoit.equalsIgnoreCase(browserName)) {

				browserWindowCount = Integer.parseInt(browserWindowCountFromAutoit);

				break;
			}
		}

		return browserWindowCount;

	}
	
	public static String popHappenedWindowOrTab(String browserName) throws IOException{
		
		String windowOrTab;
		int windowCount=WindowUtils.getBrowserWindowCountUsingAutoIT(browserName);
		
		if(windowCount>1){
			windowOrTab="WINDOW";
		}else{
			windowOrTab="TAB";
		}
		
		return windowOrTab;
		
	}
}