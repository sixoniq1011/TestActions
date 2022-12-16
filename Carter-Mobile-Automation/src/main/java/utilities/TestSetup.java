package utilities;



import java.io.IOException;


public class TestSetup
{
	private static JavaHelpers javahelpers = new JavaHelpers();

	/**
	 * Setup actions
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void setupActions() throws IOException, InterruptedException {
		//javahelpers.deleteAllFilesFromFolder(Constants.SCREENSHOT_LOCATION);

		AppiumServerManager.startServer();
	}

	/**
	 * Tear-down actions
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void tearDownActions()
	{

		AppiumServerManager.stopServer();
	}

}

