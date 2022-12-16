package utilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.IOException;
import java.net.ServerSocket;

public class AppiumServerManager
{
	private static AppiumDriverLocalService service=null;

	public static AppiumDriverLocalService startServer()
	{
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag)
		{
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	public static void stopServer()
	{
		if(service!=null)
		{
			service.stop();
		}
	}

	public static boolean checkIfServerIsRunnning(int port)
	{
		ServerSocket serverSocket;
		try
		{
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		}
		catch (IOException e)
		{
			return true;
		}
		return false;
	}

	private AppiumServerManager()
	{
		throw new IllegalStateException("AppiumServerManager class");
	}

	/**
	 * Start the appium server on given port
	 * @throws InterruptedException
	 */
	public static synchronized void startAppium() throws InterruptedException {

		ThreadManager.setAppiumServiceBuilder(new AppiumServiceBuilder().usingPort(ThreadManager.getAppiumPort()).withIPAddress("0.0.0.0"));
		ThreadManager.getAppiumLocalService();
		ThreadManager.setAppiumLocalService(AppiumDriverLocalService.buildService(ThreadManager.getAppiumServiceBuilder()));
		ThreadManager.getAppiumLocalService().start();
		Thread.sleep(10000); //breathing time for appium server to up/running properply to avoid any unforeseen appium server issues

	}

	/**
	 * Stop the appium server
	 */
	public static synchronized void stopAppium() throws InterruptedException {

		if (ThreadManager.getAppiumLocalService()!= null) {
			ThreadManager.getAppiumLocalService().stop();
			Thread.sleep(10000); //breathing time for appium server to up/running properply to avoid any unforeseen appium server issues
		}

	}
}
