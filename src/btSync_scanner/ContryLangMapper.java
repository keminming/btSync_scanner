package btSync_scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContryLangMapper {

	private Set<String> countries = new HashSet();
	
	private Map<String, List<String>> contryToLangsMap = new HashMap<String,List<String>>();
	
	private Map<String, String> contryCodeToNameMap = new HashMap();
	
	public void init(String dependencies)
	{
		try {
			FileReader rd = new FileReader(dependencies + "\\Countries.csv");
			BufferedReader bw = new BufferedReader(rd);
			String line;
			while((line = bw.readLine()) != null)
			{
				String[] tokens = line.split(",");
				countries.add(tokens[0]);
				contryCodeToNameMap.put(tokens[0], tokens[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileReader rd1 = new FileReader(dependencies + "\\languageswlatlong.csv");
			BufferedReader bw1 = new BufferedReader(rd1);
			String line;
			while((line = bw1.readLine()) != null)
			{
				String[] tokens = line.split(",");
				if(contryToLangsMap.containsKey(tokens[2]))
				{
					contryToLangsMap.get(tokens[2]).add(tokens[1]);
				}
				else
				{
					List<String> l = new ArrayList<String>();
					l.add(tokens[1]);
					contryToLangsMap.put(tokens[2], l);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<String> getLanguagesByCountryCode(String countryCode)
	{
		return contryToLangsMap.get(countryCode);
	}
	
	String getCountryNameByCode(String countryCode)
	{
		return contryCodeToNameMap.get(countryCode);
	}
}
