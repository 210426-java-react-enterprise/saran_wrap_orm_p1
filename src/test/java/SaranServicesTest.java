import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.dbentry.SqlInsert;
import com.revature.project1.models.AppUser;
import com.revature.project1.services.SaranServices;
import com.revature.project1.util.ConnectionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SaranServicesTest {

    private SaranServices sut;
    private SqlCreation mockSqlCreation;


    @Before
    public void setUp(){

        mockSqlCreation = mock(SqlCreation.class);
        sut = new SaranServices(mockSqlCreation);
    }

    @After
    public void tearDown(){
        sut = null;
        mockSqlCreation = null;
    }

    @Test (expected = NullPointerException.class)
    public void  test_InsertDB_WithNull(){

        sut.insertInDB(null);

    }

    @Test
    public void test_InsertDB_WithValid(){

        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);
        sut.insertInDB(test);

        Assert.assertNotEquals(0, test.getId());
    }

    @Test
    public void mockTest_InsertDB_WithValid() throws IllegalAccessException {


        doNothing().when(mockSqlCreation).insertNewObject(any(), any());



        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);

        sut.insertInDB(test);



        verify(mockSqlCreation, times(1)).insertNewObject(any(), any());
    }

}
