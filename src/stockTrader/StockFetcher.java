package stockTrader;
/*
 * 
 * Financial Data you can Download from Yahoo
 * 
			Pricing	
a: Ask	
b: Bid	
b2: Ask (Realtime)
b3: Bid (Realtime)	
p: Previous Close	
o: Open
c4: currency
	
			Dividends
y: Dividend Yield
d: Dividend per Share
r1: Dividend Pay Date
q: Ex-Dividend Date

			Date
c1: Change	
c: Change & Percent Change	
c6: Change (Realtime)	
k2: Change Percent (Realtime)	
p2: Change in Percent	
d1: Last Trade Date
d2: Trade Date
t1: Last Trade Time

			Averages
c8: After Hours Change (Realtime)	
c3: Commission	
g: Day’s Low	
h: Day’s High	
k1: Last Trade (Realtime) With Time	
l: Last Trade (With Time)	
l1: Last Trade (Price Only)	
t8: 1 yr Target Price	
m5: Change From 200 Day Moving Average
m6: Percent Change From 200 Day Moving Average
m7: Change From 50 Day Moving Average
m8: Percent Change From 50 Day Moving Average
m3: 50 Day Moving Average
m4: 200 Day Moving Average

			Misc
w1: Day’s Value Change
w4: Day’s Value Change (Realtime)	
p1: Price Paid	
m: Day’s Range	
m2: Day’s Range (Realtime)	
g1: Holdings Gain Percent
g3: Annualized Gain
g4: Holdings Gain
g5: Holdings Gain Percent (Realtime)
g6: Holdings Gain (Realtime)

			52 Week Pricing	
k: 52 Week High
j: 52 week Low	
j5: Change From 52 Week Low	
k4: Change From 52 week High	
j6: Percent Change From 52 week Low	

k5: Percent Change From 52 week High	
w: 52 week Range	
			
			Symbol Info 
v: More Info
j1: Market Capitalization
j3: Market Cap (Realtime)
f6: Float Shares
n: Name
n4: Notes
s: Symbol
s1: Shares Owned
x: Stock Exchange
j2: Shares Outstanding

			Volume
v: Volume	
a5: Ask Size	
b6: Bid Size	
k3: Last Trade Size
a2: Average Daily Volume		

			Misc

t7: Ticker Trend
t6: Trade Links
i5: Order Book (Realtime)
l2: High Limit
l3: Low Limit
v1: Holdings Value
v7: Holdings Value (Realtime)
s6 Revenue

			Ratios	

e: Earnings per Share
e7: EPS Estimate Current Year	
e8: EPS Estimate Next Year	
e9: EPS Estimate Next Quarter	
b4: Book Value	
j4: EBITDA	
p5: Price / Sales	
p6: Price / Book	
r: P/E Ratio	
r2: P/E Ratio (Realtime)	
r5: PEG Ratio	
r6: Price / EPS Estimate Current Year	
r7: Price / EPS Estimate Next Year	
s7: Short Ratio
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockFetcher {  
	
	/*
	* Returns a Stock Object that contains info about a specified stock.
	* @param 	symbol the company's stock symbol
	* @return 	a stock object containing info about the company's stock
	* @see Stock
	*/
	static Stock getStock(String symbol) {  
		String sym = symbol.toUpperCase();
		double price = 0.0;
		int volume = 0;
		double pe = 0.0;
		double eps = 0.0;
		double week52low = 0.0;
		double week52high = 0.0;
		double daylow = 0.0;
		double dayhigh = 0.0;
		double movingav50day = 0.0;
		String marketcapString = "";
		String name = "";
		String currency = "";
		double shortRatio = 0.0;
		double open = 0.0;
		double previousClose = 0.0;
		String exchange = "";
		double movingav200Day = 0.0;
		int avgDailyVolume = 0;
		try { 
			
			// Retrieve CSV File
			URL yahoo = new URL("http://finance.yahoo.com/d/quotes.csv?s="+ symbol + "&f=l1vrejkghm3j1nc4s7poxm4a2");
			URLConnection connection = yahoo.openConnection(); 
			InputStreamReader is = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(is);  
			
			// Parse CSV Into Array
			String line = br.readLine(); 
			//Only split on commas that aren't in quotes
			
			String[] stockinfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			// Handle Our Data
			StockHelper sh = new StockHelper();
			
			price = sh.handleDouble(stockinfo[0]);
			volume = sh.handleInt(stockinfo[1]);
			pe = sh.handleDouble(stockinfo[2]);
			eps = sh.handleDouble(stockinfo[3]);
			week52low = sh.handleDouble(stockinfo[4]);
			week52high = sh.handleDouble(stockinfo[5]);
			daylow = sh.handleDouble(stockinfo[6]);
			dayhigh = sh.handleDouble(stockinfo[7]);   
			movingav50day = sh.handleDouble(stockinfo[8]);
			marketcapString = stockinfo[9].replace("\"", "");
			name = stockinfo[10].replace("\"", "");
			currency = stockinfo[11].replace("\"", "");
			shortRatio = sh.handleDouble(stockinfo[12]);
			previousClose = sh.handleDouble(stockinfo[13]);
			open = sh.handleDouble(stockinfo[14]);
			exchange = stockinfo[15].replace("\"", "");
			movingav200Day = sh.handleDouble(stockinfo[16]);
			avgDailyVolume = sh.handleInt(stockinfo[17]);
			
//			if (marketcapString.endsWith("[A-Z]")){
//				char numbersPlace = '\0';
//				numbersPlace = marketcapString.charAt(marketcapString.length()-1);
//				marketcapString = marketcapString.substring(0, marketcapString.length()-1);
//				
//				marketcap = Double.parseDouble(marketcapString);
//				
//				
//			}
			
		} catch (IOException e) {
			Logger log = Logger.getLogger(StockFetcher.class.getName()); 
			log.log(Level.SEVERE, e.toString(), e);
			return null;
		}
		
		return new Stock(sym, price, volume, pe, eps, week52low, week52high, daylow, dayhigh, movingav50day, marketcapString, name,currency, shortRatio,previousClose,open,exchange, movingav200Day, avgDailyVolume);
		
	}
}
