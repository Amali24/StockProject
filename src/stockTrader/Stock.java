package stockTrader;

public class Stock { 
	
	private String symbol; 		  // Stock Symbol (e.g. FB [Facebook])
	private double price;		  // Current price
	private int volume; 		  // Previous day volume
	private double pe;  		  // Price:Earning Ratio
	private double eps;			  // Earnings per Share
	private double week52low;	  // 52-week low price
	private double week52high;	  // 52-week high price
	private double daylow;		  // Daily Low
	private double dayhigh;       // Daily High
	private double movingav50day; // 50 Day Moving Average Price
	private String marketcap;	  // Total number of shares available for trade
	private String name;		  // Name of stock
	private String currency;	  // Currency of trades
	private double shortRatio;	  // Ratio of shorts to shares outstanding (shows market confidence in stock)
	private double previousClose; // Previous closing price
	private double open;		  // Most recent opening price
	private String exchange;	  // e.g NYSE/NASDAQ
	private double movingav200Day;
	private int avgDailyVolume;
	
	private double trend;
	private double ratioPriceToOpen = 1;
	private double ratioCurrentTo50Day = 1;
	private double ratio50Dayto200Day = 1;
	private double shortTermRec = 1;
	private double longTermRec = 1;
	private double shortConfidence = 1;
	
	
	/////////////Constructors///////////////
	
	// No arg constructor only exists in cases of programmer error
	public Stock(){
		this.symbol = "NOT FOUND";
		this.price = -1;	
		this.volume = -1; 
		this.pe = -1; 
		this.eps = -1; 
		this.week52low = -1; 
		this.week52high = -1; 
		this.daylow = -1; 
		this.dayhigh = -1; 
		this.movingav50day = -1; 
		this.marketcap = "NOT FOUND";
		this.name = "NOT FOUND";
		this.currency = "NOT FOUND";
		this.shortRatio = -1;
		this.previousClose = -1;
		this.open = -1;
		this.exchange = "NOT FOUND";
	}
	// Primary constructor provides input data for all properties
	public Stock(String symbol, double price, int volume, double pe, double eps, double week52low,      
					double week52high, double daylow, double dayhigh, double movingav50day, String marketcap, 
					String name, String currency, double shortRatio, double previousClose, double open, String exchange, double movingav200Day,
					int avgDailyVolume) {	
		this.symbol = symbol; 
		this.price = price;	
		this.volume = volume; 
		this.pe = pe; 
		this.eps = eps; 
		this.week52low = week52low; 
		this.week52high = week52high; 
		this.daylow = daylow; 
		this.dayhigh = dayhigh; 
		this.movingav50day = movingav50day; 
		this.marketcap = marketcap;
		this.name = name;
		this.currency = currency;
		this.shortRatio = shortRatio;
		this.previousClose = previousClose;
		this.open = open;
		this.exchange = exchange;
		this.movingav200Day = movingav200Day;
		this.avgDailyVolume = avgDailyVolume;
	} 
	
	////////////////////////////////////////
	
	
	public void calcPriceTrends(){
		ratioPriceToOpen = price / open;
		ratioCurrentTo50Day = price / movingav50day;
		ratio50Dayto200Day = movingav50day / movingav200Day;
	}
	
	public void calcConfidenceMetrics(){
		if (shortRatio > 1 && shortRatio < 10)
			shortConfidence = -1 / 100 * Math.pow(shortRatio, 2) + 1;
		
		else if(shortRatio > 10)
			shortConfidence = .001;
	}
		
		public void calcRecomendations(){
		//Recommendations based on trends
		shortTermRec = ratioCurrentTo50Day * shortConfidence;
		longTermRec = ratio50Dayto200Day * ratioCurrentTo50Day;
		
		if (ratioPriceToOpen > 3)
			shortTermRec = 10;
		if (ratioPriceToOpen < .3)
			shortTermRec = 0;		
	}

		
		
	/////GETTERS/////
	public String getSymbol() { 
		return this.symbol;		
	}
	public double getPrice() { 		
		return this.price;		
	} 
	public int getVolume() {    
		return this.volume;     
	}
	public double getPe() {    
		return this.pe;     
	} 
	public double getEps() { 
		return this.eps;     
	}
	public double getWeek52low() {    
		return this.week52low;    
	} 
 	public double getWeek52high() {  
		return this.week52high;    
	} 
 	public double getDaylow() {    
		return this.daylow;    
	} 
	public double getDayhigh() {    
		return this.dayhigh;     
	}
	public double getMovingav50day() {     
		return this.movingav50day;  
	} 
	public String getMarketcap() { 
		return this.marketcap;
	}
	public String getName(){
		return this.name;
	}
	public String getCurrency(){
		return this.currency;
	}
	public double getPreviousClose(){
		return this.previousClose;
	}
	public double getOpen(){
		return this.open;
	}
	public String getExchange(){
		if (this.exchange.equals("NMS"))
			return "NASDAQ";
		else if (this.exchange.equals("NYQ"))
			return "NYSE";
		else
			return "UNKNOWN MARKET(" + this.exchange + ")";
	}	
	public double getShortRatio(){
		return this.shortRatio;
	}
	public double getTrend(){
		return this.trend;
	}
	public double getMovingav200Day() {
		return movingav200Day;
	}
	public int getAvgDailyVolume(){
		return avgDailyVolume;
	}
	public double getShortTermRec(){
		return this.shortTermRec;
	}
	public double getLongTermRec(){
		return this.longTermRec;
	}
	
	////// Setters not included as all data is set upon creation and should not be manually altered //////
}