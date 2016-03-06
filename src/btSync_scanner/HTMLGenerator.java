package btSync_scanner;

public class HTMLGenerator {
	
	public String generateAncher(String countryCode)
	{
		return "<a  href='#" + countryCode + "'>" + countryCode + "</a>&nbsp;";
	}
	
	public String generateParagraph(String[] CountryCodes)
	{
		StringBuilder anchers = new StringBuilder();
		
		for(String countryCode : CountryCodes)
		{
			anchers.append(generateAncher(countryCode));
		}
		return "<p>" + anchers.toString() + "</p>";
	}
	
	public String generateCountry(String country)
	{
        return "<td  style='width: 165.5px;'>" + country + "</td>";
	}
	
	public String generateLink(Info info)
	{
		StringBuilder sb = new StringBuilder();
		for(String s : info.langs)
		{
			sb.append("[" + s + "] ");
		}
		
		return "<td  style='width: 1193.5px;'>[" + info.countryName + "]" + sb.toString() + "<a  target='_blank'  href='" + info.sampleUrl + "'>Sample</a>&nbsp;:&nbsp; <a  target='_blank' href='" + info.allUrl + "'>All</a><br></td>";
	}
	
	public String generateArticles(Info[] infos)
	{
		StringBuilder articles = new StringBuilder();
		for(Info info : infos)
		{
			articles.append("<article><h2><a  id='" + info.countryCode + "'>" + info.countryName + " &nbsp;</a> </h2><table  style='width: 100%'  border='1'><tbody><tr>");
			articles.append(generateCountry(info.folderName));
			articles.append(generateLink(info));
			articles.append(""
					+ "</tr></tbody></table></article>");
		}
		
		return articles.toString();
	}
	
	public String generateHTMLDocument(Info[] infos)
	{
		String[] countryCodes = new String[infos.length];
		int cnt = 0;
		
		for(Info info : infos)
		{
			countryCodes[cnt++] = info.countryCode;
		}
		
		return "<!DOCTYPE html>		<html  lang='en'>		  <head>		    <title>HTML5</title>		    <meta  charset='utf-8'>		    <!--[if lt IE 9]>		<script src='http://html5shiv.googlecode.com/svn/trunk/html5.js'></script><![endif]-->		    <style>		body {font-family: Verdana, sans-serif; font-size:0.8em;}		header, nav, section, article, footer		{border:1px solid grey; margin:5px; padding:8px;}		nav ul {margin:0; padding:0;}		nav ul li {display:inline; margin:5px;}		</style> </head>		  <body>		    <header>		      <h1>His Hands Reader - Mother Tongue literacy videos</h1>		      <p>The links below will sync the selected His Hands literacy videos to		        your device.&nbsp; Choose sample to get a small sample or choose All to		        get the whole set of videos. </p>		      <p>First install the <a  target='_blank'  href='http://www.getsync.com'>BitTorrent		          Sync</a> application on your phone, tablet or desktop.&nbsp; If you		        are using the Android <a  target='_blank'  href='http://www.hishandsreader.org'>HHR		          video viewing app</a> change the default sync location to be		        '/HHR'.&nbsp; And if using a phone change the default to sync only while		        WiFi connected so that you save your phone data.</p>		      <p>Second, then select the language you want from the list below and the		        sync will begin.&nbsp;&nbsp;&nbsp; </p>		      <p>If you do not see your language below... please volunteer to <a  target='_blank'		           href='http://www.hishandsreader.org/add-my-language-odk-collect.html'>add		          your language</a>... it only takes 30 hours!</p>		      <p>Have fun learning to read!</p>		      Bob Achgill<br>		    </header>		    <section>		      <h2>Countries:</h2>" 
				+ generateParagraph(countryCodes) + "<br>" + "</section>" 	
				+ 	generateArticles(infos) + "<footer>		      <p>© 2016 Robert F. Achgill. All rights reserved.</p>		      <p><a  target='_blank'  href='www.HisHandsReader.org'>www.HisHandsReader.org</a></p>		      <p><br>		      </p>		    </footer>		    </body>		</html>";
	}
}
