package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class JavaHelpers
{

	//Time-stamps 
	/**
	 * Get current time-stamp in given format
	 * @param format format e.g. "yyyy MMM dd", 'yyyyMMdd_HHmmss' etc.
	 * @return String timestamp
	 * 
	 */
	public String getTimeStamp(String format) 
	{
		/*
		 * Example format are :
		 * 
		 * "yyyy MMM dd" for "2013 Nov 28"
		 * 
		 * "yyyyMMdd_HHmmss" for "20130131000000"
		 * 
		 * "yyyy MMM dd HH:mm:ss" for "2013 Jan 31 00:00:00"
		 * 
		 * "dd MMM yyyy" for "28 Nov 2017"
		 */
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Get current time-stamp in "_yyyyMMdd_HHmmss" format
	 * @return String timestamp
	 */
	public String timeStamp() 
	{
		return getTimeStamp("_yyyyMMdd_HHmmss");
	}

	/**
	* Update time string to required timezone time string
	 * 
	 * @param actualTimeFormat actualTimeFormat Time Format for time input
	 * 
	 * @param increamentMinute time
	 * 
	 * @param increamentHour expectedTimeFormat Time Format we want our result to be
	 * 
	 * @param increamentDate    icrementDate number by what we need to increment date to
	 * 
	 * @param expectedTimeFormat    increamentHour Amount of time we need to increment hour to
	 * 
	 * @param increamentSeconds    increamentMinute Amount of time we need to increment minutes to
	 * 
	 * @param time    increamentSeconds Amount of time we need to increment seconds
	 *               to
	 * 
	 * @return String converted time
	 * 
	 * @throws ParseException
	 * 
	 * 
	 * 
	 *                        Example for date format are :
	 * 
	 *                        "yyyy MMM dd" for "2013 Nov 28"
	 * 
	 *                        "yyyyMMdd_HHmmss" for "20130131000000"
	 * 
	 *                        "yyyy MMM dd HH:mm:ss" for "2013 Jan 31 00:00:00"
	 * 
	 *                        "dd MMM yyyy" for "28 Nov 2017"
	 * 
	 * 
	 * 
	 *                        Example for time format:
	 * 
	 *                        "HH:mm:ss" for "16:00:00"(24 hr format)
	 * 
	 *                        "hh:mm:ss" for "4:00:00"(12 hr format)
	 * 
	 * 
	 * 
	 */
	public String updateTime(	String actualTimeFormat, 
								String time, 
								String expectedTimeFormat, 
								int increamentDate,
								int increamentHour, 
								int increamentMinute, 
								int increamentSeconds
								) throws ParseException 
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		DateFormat resultDateFormat = new SimpleDateFormat(expectedTimeFormat);
		Date date = new SimpleDateFormat(actualTimeFormat).parse(time + " " + year); // we're parsing current year
																						// incase year not passed
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, increamentDate);
		calendar.add(Calendar.HOUR, increamentHour);
		calendar.add(Calendar.MINUTE, increamentMinute);
		calendar.add(Calendar.SECOND, increamentSeconds);
		return resultDateFormat.format(calendar.getTime());
	}

	/**
	 * @param date	Date
	 * @param pattern Date and Time Pattern (Format)
	 * @param locale Locale Region e.g (US, UK)
	 * @return
	 */
	public String changeLocalDate(Date date, String pattern, Locale locale) 
	{
		String dateString = null;
		SimpleDateFormat formatter = null;
		if (locale == null) {
			formatter = new SimpleDateFormat(pattern);
		} else {
			formatter = new SimpleDateFormat(pattern, locale);
		}

		dateString = formatter.format(date);
		return dateString;
	}
	
	
	//Java Methods 
	/**
	 * Get method name where this method is called
	 * @return String method name
	 */
	public String getMethodName() 
	{
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}

	
	//Data Reader 
	/** 
	 * Get Property value
	 * @param propertyfile propertyfile property file name
	 * @param propertyname propertyname property name
	 * @return String property value
	 */
	public static String getPropertyValue(String propertyfile, String propertyname) 
	{
		Properties prop = accessPropertiesFile(propertyfile);
		return prop.getProperty(propertyname);
	}

/*	*//**
	 * Get property value for squad
	 * @param propertyFile property file name
	 * @param propertyName property name
	 * @return String property value
	 *//*
	public static String getPropertyValueForSquad(String propertyFile, String propertyName)
	{
		Properties prop = accessPropertiesFile(propertyFile);
		return prop.getProperty(propertyName + "_" + Constants.SERVER_KEY + "_" + Constants.SERVER_PAY);
	}*/

	/**
	 * Access property file
	 * @param propertyfile propertyfile property file name
	 * @return Properties object
	 */
	public static Properties accessPropertiesFile(String propertyfile) 
	{
		Properties prop = new Properties();
		// try retrieve data from file 
		try 
		{
			prop.load(new FileInputStream(propertyfile));
		}
		// catch exception in case properties file does not exist 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return prop;
	}

	
	//Folder Operations
	/**
	 * Delete all files from given folder
	 * @param folderpath folder-path folder path
	 * 
	 */
	public void deleteAllFilesFromFolder(String folderpath)
	{
		File dir = new File(folderpath);
		if (!dir.isDirectory()) 
		{
			return;
		}
		File[] listFiles = dir.listFiles();
		for (File file : listFiles) 
		{
			try 
			{
				file.delete();
			}
			catch (Exception e)
			{
				//Exception occurred while deleting a file. We'll still move on.
			}
		}
	}

	
	//Run Executable file
	public void runExeFile(String filePath) throws IOException, InterruptedException 
	{
		Runtime.getRuntime().exec(filePath);
		Thread.sleep(6000);
	}

	
	//Data Processing
	/**
	 * Is list contains another list
	 * @param outer is array string
	 * @param inner is array string can compare to array string outer
	 */

	public static boolean isListContainsAnotherList(String[] outer, String[] inner) {
		return Arrays.asList(outer).containsAll(Arrays.asList(inner));
	}

	/**
	 * extract number from string given
	 *
	 * @param word is the String that we want to extract
	 *             Example : "Rp. 13.000 / bulan" > 13000
	 */
	public static int extractNumber(String word) {
		String str = word.replaceAll("[A-Z a-z . ' , ; ( ) : /]", "").trim();
		return Integer.parseInt(str);
	}

	/**
	 * remove number from string given and trim it
	 * @param word is the String that we want to extract
	 * Example : "Min. 2 Bln" > "Min.Bln"
	 */
	public static String removeNumber(String word)
	{
		return word.replaceAll("[0-9 \\s+ .]","").trim();
	}

	
	//Reading system properties

	/**
	 * Set system variable - set it from system variable first, if not found -set it from properties file
	 * @param name variable name
	 * @param propertyFileLocation properties file location
	 * @return variable value
	 */
	public static String setSystemVariable(String propertyFileLocation ,String name)
	{
		//Reading from system properties.
		String variable = System.getProperty(name);
		
		//if not specified via command line, take it from constants.properties file
		if (variable == null || variable.isEmpty()) {
			variable = JavaHelpers.getPropertyValue(propertyFileLocation, name);
		}
		return variable;
	}
	/** Concatenate two array and return the result
	 * @param first is first array
	 * @param second is second array
	 */
	public <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}


	/**
	 * Get Iamge Base64 encoded string
	 * @param imagePath image path e.g. /src/test/resources/
	 * @param name image name e.g. image.png
	 * @return base64 encoded string
	 * @throws URISyntaxException exception
	 * @throws IOException exception
	 */
	public String getImageB64(String imagePath, String name)throws URISyntaxException,IOException{
		File file=new File(System.getProperty("user.dir")+ imagePath + name);
		Path path=file.toPath();
		return Base64.getEncoder().encodeToString(Files.readAllBytes(path));
	}

}