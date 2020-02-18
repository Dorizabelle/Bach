// Dorizabelle Northecide 
// February 11, 2020
// Programming Project 1
// Introduction to Comp Sci II

import java.util.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebLinks {

	public static void main(String[] args) {
		double totalHS = 0;
		double totalTS = 0;
		double totalLHS = 0;

		int countS = 0;
		int countA = 0;
		Set<String> sections = new HashSet<String>();
		Set<String> articles = new HashSet<String>();
		
		Set<String> treeArticles = new TreeSet<String>();
		Set<String> linkedArticles = new LinkedHashSet<String>();

		
		try {
			Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Wikipedia:Vital_articles/Level/5").maxBodySize(0).get();
			Elements links = doc.select("a[href][title]");
				for(Element page: links) {
					if(page.attr("abs:href").toString().contains(("/wiki/Wikipedia:Vital_articles/Level/5/"))) {
							if(!sections.contains(page.attr("abs:href").toString())){
								//System.out.println(countS + " SEC " + page.attr("abs:href").toString());
								sections.add(page.attr("abs:href").toString());
								//System.out.println("\nya" + sections.size());
								countS++;
//---------------------------------------------------------------------------------------------------------------------------------------------								
								try {
									Document docIn = Jsoup.connect(page.attr("abs:href").toString()).maxBodySize(0).get();
									Elements linksIn = docIn.select("a[href][title]");
										
										for(Element pageI: linksIn) {
											if(!pageI.attr("abs:href").toString().contains(("index.php?")) && 
													!pageI.attr("abs:href").toString().contains("User") &&
													!pageI.attr("abs:href").toString().contains("Template") &&
													!pageI.attr("abs:href").toString().contains("Special") &&
													!pageI.attr("abs:href").toString().contains("Main_Page") &&
													!pageI.attr("abs:href").toString().contains("shop.wikimedia.org")&&
													!pageI.attr("abs:href").toString().contains("Category") &&
													!pageI.attr("abs:href").toString().contains("Portal") &&
													!pageI.attr("abs:href").toString().contains("Help") &&
													!pageI.attr("abs:href").toString().contains("Privacy_policy") &&
													!pageI.attr("abs:href").toString().contains("Wikipedia:") &&
													!pageI.attr("abs:href").toString().contains("Wikipedia_talk:") &&
													!pageI.attr("abs:href").toString().contains("ru.wikipedia.org")) {
													//System.out.println(countA + " ART " + pageI.attr("abs:href").toString());
													String hrefLink = pageI.attr("abs:href").toString();
													if(!articles.contains(hrefLink)){
														//System.out.println(countA + " ART " + hrefLink);
														long begin = System.currentTimeMillis();
														articles.add(hrefLink);
														long end = System.currentTimeMillis();
														//System.out.println("\nYE" + articles.size());
														countA++;
														long difference = end-begin;
														totalHS = totalHS+difference;
//---------------------------------------------------------------------------------------------------------------------------------------------											
														begin = System.currentTimeMillis();
														treeArticles.add(hrefLink);
														end = System.currentTimeMillis();
														//System.out.println("\nYE" + articles.size());
														difference = end-begin;
														totalTS = totalTS+difference;
//---------------------------------------------------------------------------------------------------------------------------------------------											
														begin = System.currentTimeMillis();
														linkedArticles.add(hrefLink);
														end = System.currentTimeMillis();
														//System.out.println("\nYE" + articles.size());
														difference = end-begin;
														totalLHS = totalLHS+difference;
													
													}
											}	

										}

								} catch (IOException e) {
									e.printStackTrace();
								}
								
//---------------------------------------------------------------------------------------------------------------------------------------------											
							}
					}	
				}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("The Program completed in " + (totalHS/10.0) + "ms with the hash set");
		System.out.println("The Program completed in " + (totalTS/10.0) + "ms with the tree set");
		System.out.println("The Program completed in " + (totalLHS/10.0) + "ms with the Linked Hash Set");

		//System.out.println("Total Sections: " + countS);
		//System.out.println("Total links: " + countA);

	}

}