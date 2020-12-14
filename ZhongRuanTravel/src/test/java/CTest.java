import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.dao.IRouteDao;
import com.icis.dao.UserDao;
import com.icis.dao.impl.IRouteDaoImpl;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.PageBean;
import com.icis.pojo.Route;
import com.icis.service.IRouteService;
import com.icis.service.Impl.IRouteServiceImpl;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;


public class CTest {
    private UserDao userDao=new UserDaoImpl();
    private ObjectMapper mapper=new ObjectMapper();
    private IRouteDao iRouteDao=new IRouteDaoImpl();
    private IRouteService iRouteService=new IRouteServiceImpl();


    @Test
    public void getcata(){
        List<Category> cateory = userDao.getCateory();
        //System.out.println(cateory);
        String string = null;
        try {
            string = mapper.writeValueAsString(cateory);
            System.out.println(string);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void getRoute(){
        List<Route> routeByCid = iRouteService.getRouteByCid(4);
        for (Route route : routeByCid) {
            System.out.println(route.getRid()+"--"+route.getRname());
        }
    }



    @Test
    public void tt(){
        List<Route> indexCategory = iRouteService.getIndexRoute("1");
        for (Route category : indexCategory) {
            System.out.println(category);
        }
    }


    @Test
    public void rr(){
  /*      Scanner scanner=new Scanner(System.in);
        int a = scanner.nextInt();*/
        for (int i = 0; i < 4; i++) {
            if (i==1){
                System.out.println(1);
            }
            if (i==2){
                System.out.println(2);
            }
             if (i==3){
                System.out.println(3);
            }


        }
    }

}
