import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.dbentry.SqlInsert;
import com.revature.project1.models.AppUser;
import com.revature.project1.services.SaranServices;
import com.revature.project1.util.ConnectionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class SaranServicesTest {

    private SaranServices sut;
    private SqlCreation mockSqlCreation;
    private ConnectionFactory mockConnectionFactory;


    @Before
    public void setUp(){

        mockSqlCreation = mock(SqlCreation.class);
        mockConnectionFactory = mock(ConnectionFactory.class);
        sut = new SaranServices(mockSqlCreation, mockConnectionFactory.getConnection());
    }

    @After
    public void tearDown(){
        sut = null;
        mockSqlCreation = null;
        mockConnectionFactory = null;
    }

    @Test (expected = NullPointerException.class)
    public void  test_InsertDB_WithNull(){

        sut.insertInDB(null);

    }

    @Test
    public void mockTest_InsertDB_WithValid() {


        doNothing().when(mockSqlCreation).insertNewObject(any(), any());



        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);

        sut.insertInDB(test);



        verify(mockSqlCreation, times(1)).insertNewObject(any(), any());
    }

    @Test (expected = NullPointerException.class)
    public void  test_UpdateDB_WithNull(){

        sut.updateObject(null, anyString());

    }

    @Test (expected = NullPointerException.class)
    public void  test_UpdateDBWithKey_WithNull(){

        sut.updateObject(null, anyString(), anyString());

    }

    @Test
    public void mockTest_UpdateDB_WithValid() {


        doNothing().when(mockSqlCreation).setCondition(anyString());
        doNothing().when(mockSqlCreation).update(any(), any());
        when(mockSqlCreation.getStatement()).thenReturn("");


        sut.updateObject(AppUser.class, anyString());


        verify(mockSqlCreation, times(1)).setCondition(anyString());
        verify(mockSqlCreation, times(1)).update(any(), any());
        verify(mockSqlCreation, times(1)).getStatement();

    }

    @Test
    public void mockTest_UpdateDBWithKey_WithValid(){


        doNothing().when(mockSqlCreation).setCondition(anyString(), anyString());
        doNothing().when(mockSqlCreation).update(any(), any());
        when(mockSqlCreation.getStatement()).thenReturn("");


        sut.updateObject(AppUser.class, anyString(), anyString());


        verify(mockSqlCreation, times(1)).setCondition(anyString(), anyString());
        verify(mockSqlCreation, times(1)).update(any(), any());
        verify(mockSqlCreation, times(1)).getStatement();

    }

    @Test (expected = NullPointerException.class)
    public void  test_SelectDB_WithNull(){

        sut.SelectDB(null, anyString());

    }

    @Test
    public void mockTest_SelectDB_WithValid(){


        when(mockSqlCreation.select(any(), anyString(), any())).thenReturn(new ArrayList<>());

        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);

        sut.SelectDB(test.getClass(), "username=gtomasel");

        verify(mockSqlCreation, times(1)).select(any(), anyString(), any());


    }

    @Test (expected = NullPointerException.class)
    public void  test_SelectDBAll_WithNull(){

        sut.SelectAllDB(null);

    }

    @Test
    public void mockTest_SelectAllDB_WithValid(){


        when(mockSqlCreation.selectAll(any(), any())).thenReturn(new ArrayList<>());

        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);

        sut.SelectAllDB(test.getClass());

        verify(mockSqlCreation, times(1)).selectAll(any(), any());


    }

    @Test (expected = NullPointerException.class)
    public void  test_Delete_WithNull(){

        sut.deleteDB(null, anyString());

    }

    @Test
    public void mockTest_Delete_WithValid(){


        when(mockSqlCreation.delete(any(), anyString(), any())).thenReturn("1");

        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);

        sut.deleteDB(test.getClass(), "username=gtomasel");

        verify(mockSqlCreation, times(1)).delete(any(), anyString(), any());


    }



}
