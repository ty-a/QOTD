# QOTD
A simple java-based QOTD server. 

# Running
First compile the files, then you can run it using java Server

Since the QOTD Protocol uses port 17, you will need superuser priveleges to run it.

This loads the quotes from an SQLite DB named "quotes.db" located in the running directory. 

The DB has a single table named "Quotes" with the primary key being an auto_incremented int starting at 1 and 
a text field named "quote". In randomly choosing a quote, we assume there are no empty spaces. 