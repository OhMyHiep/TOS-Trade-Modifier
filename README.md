# Overview
This project is built using Java, Maven, and Python. This application was developed for personal use to automate the formatting of Excel workbooks, specifically for handling paper trades, options trades, and similar financial data. Users can paste trade data into the Trade.xlsx workbook, which is then transformed into the desired format on a separate sheet. Additional features include the ability to insert screenshots directly into specific cells within the workbook.

## Features
- Automates formatting of paper and options trades in Excel into a separate sheet
- Allows users to insert screenshots into specific cells within the workbook.

## Installation
Clone this repository: `git clone https://github.com/OhMyHiep/TOS-Trade-Modifier.git`


## Usage
### Running Java Formatter
#### This Project Uses JDK 17 And Maven To Manage Dependencies
1. Ensure JDK 17 or higher is installed on your machine.
2. Install Maven from the official site. For Mac users ```brew install maven```.
3. Use ```cd your/path/to/project/root``` to go to the root folder.
4. Use ```mvn clean install``` This will let maven clean previous artifacts, download all of the dependencies in the ```pom.xml``` file and build the project.
5. Input your excel data into Trade.xlsx. The algorithm is dependent on the format of how the original data is pasted into excel. Data should be present in Trades.xlsx for your reference.
6. Run the file ```mvn exec:java -Dexec.mainClass="com.tos.Main"```.


### Running Python Image Insert
1. Switch to branch to python code ```git switch PythonInsertImage```.
2. Activate the Python virtual environment ```source venv/bin/activate```.
3. Formatting the directory of the files is unique to each individual. Change base_dir and excel_path in the source code to reflect your directory.
4. Run the file ```python main.py```.


