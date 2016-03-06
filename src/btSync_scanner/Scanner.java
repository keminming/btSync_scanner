package btSync_scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scanner {
	private String root;

	private String outputFolder;
	
	private String dependency;
	
	private HTMLGenerator htmlGen = new HTMLGenerator();
	
	private ContryLangMapper mapper = new ContryLangMapper();
	
	Scanner(String root)
	{
		this.root = root + "\\root";
		this.outputFolder = root + "\\out";
		this.dependency = root + "\\HHRPickALanguage-dependencies";
		mapper.init(this.dependency);
	}
	
	public void scan()
	{
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outputFolder + "\\HHRPickALanguage-HTML5.html")));
			
			File f = new File(root);
			
			Info[] infos = new Info[f.listFiles().length];

			int cnt = 0;
			for(File f1 : f.listFiles())
			{
				String folderName = f1.getName();
				String[] tokens = folderName.split("-");
				String countryCode = tokens[0];
				
				BufferedReader bw1 = new BufferedReader(new FileReader(f1.getAbsolutePath() + "/" + f1.getName() + ".txt"));
				String sample = bw1.readLine(); 
				String sampleUrl = sample.substring(sample.indexOf(':') + 1).trim();
				String all = bw1.readLine();
				String allUrl = all.substring(all.indexOf(':') + 1).trim();
				
				Info info = new Info();
				info.folderName = folderName;
				info.countryCode = countryCode;
				info.countryName = mapper.getCountryNameByCode(countryCode);
				info.sampleUrl = sampleUrl;
				info.allUrl = allUrl;
				info.langs = mapper.getLanguagesByCountryCode(countryCode);
				
				infos[cnt++] = info;
			}
			
			String html = htmlGen.generateHTMLDocument(infos);
			
			bw.write(html);
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(args[0]);
		sc.scan();
	}
}
