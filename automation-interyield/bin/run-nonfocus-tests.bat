cd C:\Program Files (x86)\Jenkins\workspace\automation-interyield-nonfocus-tests\target\automation-interyield-1.0-SNAPSHOT
java -cp "lib/*;conf/" org.testng.TestNG  conf\testng.xml -groups AdCountTests,SnoozeAlarmTests,BackCatcher1Tests,Backcatcher2Tests,MatureContentTests,OptOutTests,SleepTests,SnoozeTests