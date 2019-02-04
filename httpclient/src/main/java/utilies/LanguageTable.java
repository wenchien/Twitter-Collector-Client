package utilies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LanguageTable {
	public static final String[] langTableCombo = {"English", "Japanese", "Arabic", 
									  "Bengali", "Czech", "Danish", 
									  "German", "Greek", "Spanish", 
									  "Persian", "Finnish", "Filipino", 
									  "French", "Hebrew", "Hindi", 
									  "Hungarian", "Indonesian", "Italian", 
									  "Korean", "Malay", "Dutch", 
									  "Norwegian", "Polish", "Portuguese", 
									  "Romanian", "Russian", "Swedish", 
									  "Thai", "Turkish", "Ukrainian", 
									  "Urdu", "Vietnamese", "Simplified Chinese", 
									  "Traditional Chinese"};
	public static HashMap<String, String> langTable = new HashMap<String, String>();
	static {
		langTable.put("English", "lang:en");
		langTable.put("Japanese", "lang:ja");
		langTable.put("Arabic", "lang:ar");
		langTable.put("Bengali", "lang:bn");
		langTable.put("Czech", "lang:cs");
		langTable.put("Danish", "lang:da");
		langTable.put("German", "lang:de");
		langTable.put("Greek", "lang:el");
		langTable.put("Spanish", "lang:es");
		langTable.put("Persian", "lang:fa");
		langTable.put("Finnish", "lang:fi");
		langTable.put("Filipino", "lang:fil");
		langTable.put("French", "lang:fr");
		langTable.put("Hebrew", "lang:he");
		//
		langTable.put("Hindi", "lang:hi");
		langTable.put("Hungarian", "lang:hu");
		langTable.put("Indonesian", "lang:id");
		langTable.put("Italian", "lang:it");
		langTable.put("Korean", "lang:ko");
		langTable.put("Malay", "lang:msa");
		langTable.put("Dutch", "lang:nl");
		langTable.put("Norwegian", "lang:no");
		langTable.put("Polish", "lang:pl");
		//
		langTable.put("Portuguese", "lang:pt");
		langTable.put("Romanian", "lang:ro");
		langTable.put("Russian", "lang:ru");
		langTable.put("Swedish", "lang:sv");
		langTable.put("Thai", "lang:th");
		langTable.put("Turkish", "lang:tr");
		langTable.put("Ukrainian", "lang:uk");
		langTable.put("Urdu", "lang:ur");
		langTable.put("Vietnamese", "lang:vi");
		langTable.put("Simplified Chinese", "lang:zh-cn");
		langTable.put("Traditional Chinese", "lang:zh-tw");
	}
}
