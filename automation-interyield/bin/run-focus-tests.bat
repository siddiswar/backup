cd C:\Program Files (x86)\Jenkins\workspace\automation-interyield-focus-tests\target\
xcopy automation-interyield-1.0-SNAPSHOT C:\automation-interyield /E
cd C:\automation-interyield
java -cp "lib/*;conf/" org.testng.TestNG  conf\testng.xml -groups DesktopPopTests