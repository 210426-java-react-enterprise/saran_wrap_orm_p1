import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.dbentry.SqlInsert;
import com.revature.project1.models.AppUser;
import com.revature.project1.services.SaranServices;
import com.revature.project1.util.ConnectionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class SaranServicesTest {

    private SaranServices sut;
    private SqlCreation mockSqlCreation;
    private Connection mockConnection;


    @Before
    public void setUp(){

        mockSqlCreation = mock(SqlCreation.class);
        mockConnection = mock(Connection.class);
        sut = new SaranServices(mockSqlCreation, mockConnection);
    }

    @After
    public void tearDown(){
        sut = null;
        mockSqlCreation = null;
        mockConnection = null;
    }

    @Test (expected = NullPointerException.class)
    public void  test_InsertObject_WithNull(){

        sut.insertObject(null);

    }

    @Test
    public void mockTest_InsertObject_WithValid() {


        doNothing().when(mockSqlCreation).insertNewObject(any(), any());



        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);

        sut.insertObject(test);



        verify(mockSqlCreation, times(1)).insertNewObject(any(), any());
    }

    @Test (expected = NullPointerException.class)
    public void  test_UpdateObjectWithKey_WithNull(){

        sut.updateObject(null, "", "");

    }

    @Test (expected = NullPointerException.class)
    public void  test_UpdateObject_WithNull(){

        sut.updateObject(null, "");

    }



    @Test
    public void mockTest_UpdateObject_WithValid() {


        doNothing().when(mockSqlCreation).setCondition(anyString());
        doNothing().when(mockSqlCreation).update(any(), any());
        when(mockSqlCreation.getStatement()).thenReturn("");


        sut.updateObject(AppUser.class, anyString());


        verify(mockSqlCreation, times(1)).setCondition(anyString());
        verify(mockSqlCreation, times(1)).update(any(), any());
        verify(mockSqlCreation, times(1)).getStatement();

    }

    @Test
    public void mockTest_UpdateObjectWithKey_WithValid(){


        doNothing().when(mockSqlCreation).setCondition(anyString(), anyString());
        doNothing().when(mockSqlCreation).update(any(), any());
        when(mockSqlCreation.getStatement()).thenReturn("");


        sut.updateObject(AppUser.class, anyString(), anyString());


        verify(mockSqlCreation, times(1)).setCondition(anyString(), anyString());
        verify(mockSqlCreation, times(1)).update(any(), any());
        verify(mockSqlCreation, times(1)).getStatement();

    }

    @Test (expected = NullPointerException.class)
    public void  test_SelectObject_WithNull(){

        sut.selectObject(null, "");

    }

    @Test
    public void mockTest_SelectObject_WithValid(){


        when(mockSqlCreation.select(any(), anyString(), any())).thenReturn(new ArrayList<>());

        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);

        sut.selectObject(test.getClass(), "username=gtomasel");

        verify(mockSqlCreation, times(1)).select(any(), anyString(), any());


    }

    @Test (expected = NullPointerException.class)
    public void  test_SelectAllObjects_WithNull(){

        sut.selectAllObjects(null);

    }

    @Test
    public void mockTest_SelectAllObjects_WithValid(){


        when(mockSqlCreation.selectAll(any(), any())).thenReturn(new ArrayList<>());

        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);

        sut.selectAllObjects(test.getClass());

        verify(mockSqlCreation, times(1)).selectAll(any(), any());


    }

    @Test (expected = NullPointerException.class)
    public void  test_DeleteObject_WithNull(){

        sut.deleteObject(null,"");

    }

    @Test
    public void mockTest_DeleteObject_WithValid(){


        when(mockSqlCreation.delete(any(), anyString(), any())).thenReturn("1");

        AppUser test = new AppUser("DTest", "Passw0rd", "DTest@mail.com", "Calex", "Tester", 23);

        sut.deleteObject(test.getClass(), "username=gtomasel");

        verify(mockSqlCreation, times(1)).delete(any(), anyString(), any());


    }



}
