Stock Chart App
---
- >Application in development

- >Frameworks used: JavaFX & SpringBoot

The application contains a list of companies from NASDAQ, whose symbols and names are retrieved from a local MySQL database.

After selecting the company, the appropriate interval and pressing the button - the data is downloaded using the api from TwelveData website (https://twelvedata.com/) and then displayed on a line chart.


- Output Size = 5000 queries
- Intervals = 1 day / 1 week / 1 month
- Symbol = NASDAQ companies only (in database)
---
Update : 
- Tests have been added