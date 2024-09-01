/**
 *Data provider class designed to provide various data only for tests.
 */
public class DataProvider {

    /**
     * returns structrue containing parameter pairs for resolution. sets parallel execution to true.
     */
    @org.testng.annotations.DataProvider( name = "resolutions", parallel = true)
    public Object[][] resolutions() {
        return new Object[][]{
                {1024, 768},
                {1440, 900},
                {1366, 768}
        };
    }
}
